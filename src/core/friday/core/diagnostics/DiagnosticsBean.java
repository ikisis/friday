/**
 * 작성된 날짜: Apr 10, 2015
 * Copyright 2014 BankwareGlobal co. All rights reserved.
 */
package friday.core.diagnostics;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import friday.core.msg.Logger;

/**
 * @file			bxt.diagnostics.DiagnosticsBean.java
 * @filetype		java source file
 * @brief			
 * @author			JongOh Lee
 * @version			1.0
 * @history
 * 성		명		일			자			근  거  자  료			변  경  내  용
 * ----------------	-------------------		-------------------		--------------------------	
 * JongOh Lee :		Apr 10, 2015:			product 개발:			신규 작성
 *
 */
public class DiagnosticsBean implements Diagnostics
{
	private final ThreadMXBean threadsMXBean= ManagementFactory.getThreadMXBean();
	
	private CPUTimeCollector cpuTimeCollector;
	
	private boolean osMXBeanAvailable= true;
	
	private ObjectName osMXBeanName;
	
	private static final MBeanServer mbeanServer= ManagementFactory.getPlatformMBeanServer();
	
	private static final String cpuLoadAttributeName= "processCpuLoad";
	private static final String systemCpuLoadAttributeName= "systemCpuLoad";
	
	private static final DiagnosticsBean instance;
	
	static
	{
		instance= new DiagnosticsBean();
	}
	
	public static Diagnostics getInstance()
	{
		return instance;
	}
	
	private DiagnosticsBean()
	{
		init();
		Runtime.getRuntime().addShutdownHook( new Thread( new Runnable() {
			public void run()
			{
				try{ close();}
				catch( IOException e)
				{
					e.printStackTrace();
				}
			}
		}));
	}
	
	private void init()
	{
		if( this.threadsMXBean.isThreadCpuTimeSupported())
		{
			if( !this.threadsMXBean.isThreadCpuTimeEnabled())
				this.threadsMXBean.setThreadCpuTimeEnabled( true);
			try
			{
				Class.forName( "com.sun.management.OperatingSystemMXBean");
				this.osMXBeanName= new ObjectName( "java.lang", "type", "OperatingSystem");				
				DiagnosticsBean.mbeanServer.getAttribute( this.osMXBeanName, cpuLoadAttributeName);
				DiagnosticsBean.mbeanServer.getAttribute( this.osMXBeanName, systemCpuLoadAttributeName);				
			}
			catch( Exception e)
			{
//				System.err.println( "com.sun.management.OperatingSystemMXBean is not available.");
				Logger.WARN("com.sun.management.OperatingSystemMXBean is not available.");
				
				this.osMXBeanAvailable= false;
			}
			if( !this.osMXBeanAvailable)
			{
				this.cpuTimeCollector= new CPUTimeCollector( 
						ManagementFactory.getThreadMXBean(), ManagementFactory.getOperatingSystemMXBean());
				Thread th= new Thread( this.cpuTimeCollector, "CPU Time Computation Thread");
				th.setDaemon( true);
				th.start();
			}
		}
		
		if( this.threadsMXBean.isThreadContentionMonitoringSupported())
		{
			if( !this.threadsMXBean.isThreadContentionMonitoringEnabled())
				this.threadsMXBean.setThreadContentionMonitoringEnabled( true);
		}
	}
	
	@Override
	public MemoryInformation memoryInformation() throws Exception	
	{
		return new MemoryInformation();
	}
	
	@Override
	public String[] threadNames() throws Exception
	{
		long[] ids= this.threadsMXBean.getAllThreadIds();
		ThreadInfo[] infos= this.threadsMXBean.getThreadInfo( ids, 0);
		String[] result= new String[infos.length];
		for( int i= 0; i < infos.length; i++)
			result[i]= infos[i].getThreadName();
		return result;
	}
	
	@Override
	public ThreadInformation getThreadInformation( long id, int maxDepth) throws Exception
	{
		ThreadMXBean tbean= ManagementFactory.getThreadMXBean();
		return new ThreadInformation( 
				tbean.getThreadInfo( id, maxDepth), tbean.getThreadCpuTime( id), tbean.getThreadUserTime( id));
	}
	
	@Override
	public ThreadDump threadDump() throws Exception
	{
		checkThreadCapabilities();
		return new ThreadDump( ManagementFactory.getThreadMXBean());
	}
	
	private void checkThreadCapabilities()
	{
		if( this.threadsMXBean.isThreadContentionMonitoringSupported() 
				&& !this.threadsMXBean.isThreadContentionMonitoringEnabled())
			this.threadsMXBean.setThreadContentionMonitoringEnabled( true);
	}
	
	@Override
	public Boolean hasDeadlock() throws Exception
	{
		long[] ids= this.threadsMXBean.findDeadlockedThreads();
		return ids!= null && ids.length> 0;
	}
	
	@Override
	public HealthSnapshot healthSnapshot() throws Exception
	{
		HealthSnapshot snapshot= new HealthSnapshot();
		MemoryInformation memInfo= memoryInformation();
		MemoryUsageInformation memUse= memInfo.getHeapMemoryUsage();
		snapshot.heapUsedRatio= memUse.getUsedRatio();
		snapshot.heapUsed= memUse.getUsed();
		memUse= memInfo.getNonHeapMemoryUsage();
		snapshot.nonheapUsedRatio= memUse.getUsedRatio();
		snapshot.nonheapUsed= memUse.getUsed();
		snapshot.deadlocked= hasDeadlock();
		snapshot.liveThreads= this.threadsMXBean.getThreadCount();
		snapshot.processCpuLoad= cpuLoad();
		snapshot.systemCpuLoad= osMXBeanDoubleValue( "SystemCpuLoad");
		long freeRam= osMXBeanLongValue( "FreePhysicalMemorySize");
		if( freeRam>= 0L)
		{
			long totalRam= osMXBeanLongValue( "TotalPhysicalMemorySize");
			snapshot.ramUsed= totalRam- freeRam;
			snapshot.ramUsedRatio= (double)snapshot.ramUsed/ (double)totalRam;
		}
		else
		{
			snapshot.ramUsed= -1L;
			snapshot.ramUsedRatio= -1;
		}
		return snapshot;
	}
	
	@Override
	public Double cpuLoad()
	{
		if( this.osMXBeanAvailable)
			return osMXBeanDoubleValue( "ProcessCpuLoad");
		return this.cpuTimeCollector!= null ? this.cpuTimeCollector.getLoad() : -1;
	}
	
	public void close() throws IOException
	{
		if( this.cpuTimeCollector!= null)
			this.cpuTimeCollector.terminate();
	}
	
	private double osMXBeanDoubleValue( final String attribute)
	{
		if( this.osMXBeanAvailable)
		{
			try{ return (Double)DiagnosticsBean.mbeanServer.getAttribute( this.osMXBeanName, attribute);}
			catch( Exception e)
			{
				System.err.println( "can not retrieve requested attribute ["+ attribute+ "]. "+ e.getMessage());
			}
		}
		return -1;
	}
	
	private long osMXBeanLongValue( final String attribute)
	{
		if( this.osMXBeanAvailable)
		{
			try{ return (Long)DiagnosticsBean.mbeanServer.getAttribute( this.osMXBeanName, attribute);}
			catch( Exception e)
			{
				System.err.println( "can not retrieve requested attribute ["+ attribute+ "]. "+ e.getMessage());
			}
		}
		return -1;
	}
	
}
