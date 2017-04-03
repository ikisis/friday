/**
 * 작성된 날짜: Apr 10, 2015
 * Copyright 2014 BankwareGlobal co. All rights reserved.
 */
package friday.core.diagnostics;

import java.lang.management.OperatingSystemMXBean;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @file			bxt.diagnostics.CPUTimeCollector.java
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
public class CPUTimeCollector implements Runnable
{
	public static final String INTERVAL_PROPPERTY_NAME= "bxtdiag.cpuload.comp.interval";
	
	private static final long DEFAULT_INTERVAL= 1000L;
	
	private AtomicLong totalCpuTime= new AtomicLong( 0L);
	private AtomicLong load= new AtomicLong( 0L);
	
	private final ThreadMXBean threadMXBean;
	private final OperatingSystemMXBean systemMXBean;
	
	protected static final long INTERVAL= loadCpuLoadComIntervalProperty();
	protected final Object monitor= new Object();
	private boolean terminated;
	
	private static long loadCpuLoadComIntervalProperty()
	{
		long interval= DEFAULT_INTERVAL;
		String intervalProp= System.getProperty( INTERVAL_PROPPERTY_NAME);
		if( intervalProp!= null && !"".equals( intervalProp))
		{
			try{ interval= Long.parseLong( intervalProp);}catch( NumberFormatException e)
			{
				System.err.println( "invalid cpuLoad computation interval ["+ intervalProp+ "]");
				System.err.println( "default interval value 1000L millis applied");
			}
		}
		
		if( interval< DEFAULT_INTERVAL)
		{
			System.err.println( "invalid cpuLoad computation interval ["+ intervalProp+ "]");
			System.err.println( "default interval value 1000L millis applied");
			interval= DEFAULT_INTERVAL;
		}		
		return interval;
	}
	
	public CPUTimeCollector( final ThreadMXBean threadMXBean, final OperatingSystemMXBean systemMXBean)
	{
		this.threadMXBean= threadMXBean;
		this.systemMXBean= systemMXBean;
	}
	
	@Override
	public void run()
	{
		try
		{
			while( !terminated)
			{
				long oldValue= totalCpuTime.get();
				long start= System.currentTimeMillis();
				long[] ids= threadMXBean.getAllThreadIds();
				long time= 0L;
				for( long id: ids)
				{
					long l= threadMXBean.getThreadCpuTime( id);
					if( l>= 0L)	time+= l;
				}
				long cpuTime= time/ 1000000L;
				totalCpuTime.set( cpuTime);
				
				double rd= (double)( cpuTime- oldValue) / (double)( INTERVAL* systemMXBean.getAvailableProcessors());
				load.set( Double.doubleToLongBits( rd));
				long waitTime= INTERVAL- ( System.currentTimeMillis()- start);
				synchronized( monitor)
				{
					this.monitor.wait( waitTime<= 0L ? INTERVAL : waitTime);
				}
			}
		}
		catch( Throwable th)
		{
			System.err.println( "CPUTimeCollector thread interrupted.");
			th.printStackTrace();
		}
	}
	
	public double getLoad()
	{
		return Math.min( Double.longBitsToDouble( load.get()), 1d);
	}
	
	public void terminate()
	{
		synchronized( this.monitor)
		{
			this.terminated= true;
			this.monitor.notify();
		}
	}

}
