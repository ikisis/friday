/**
 * 작성된 날짜: Apr 9, 2015
 * Copyright 2014 BankwareGlobal co. All rights reserved.
 */
package friday.core.diagnostics;

import java.io.Serializable;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Map;
import java.util.TreeMap;

/**
 * @file			bxt.diagnostics.ThreadDump.java
 * @filetype		java source file
 * @brief			
 * @author			JongOh Lee
 * @version			1.0
 * @history
 * 성		명		일			자			근  거  자  료			변  경  내  용
 * ----------------	-------------------		-------------------		--------------------------	
 * JongOh Lee :		Apr 9, 2015:			product 개발:			신규 작성
 *
 */
public class ThreadDump implements Serializable
{
	private static final long serialVersionUID= 2527612611980362867L;
	
	private final Map<Long, ThreadInformation> threads;
	
	private final long[] deadLockedThreads;
	
	public ThreadDump( final ThreadMXBean tbean)
	{
		ThreadInfo[] tis= tbean.dumpAllThreads( tbean.isObjectMonitorUsageSupported(), tbean.isSynchronizerUsageSupported());
		if( tis== null || tis.length<= 0) threads= null;
		else
		{
			threads= new TreeMap<Long, ThreadInformation>();
			for( ThreadInfo ti: tis)
				threads.put( ti.getThreadId(), new ThreadInformation( 
						ti, tbean.getThreadCpuTime( ti.getThreadId()), tbean.getThreadUserTime( ti.getThreadId())));
		}
		long[] ids= tbean.isSynchronizerUsageSupported() ? tbean.findDeadlockedThreads() : null;
		this.deadLockedThreads= ids!= null && ids.length>= 0 ? ids : null; 
	}
	
	public Map<Long, ThreadInformation> getThreads()
	{
		return this.threads;
	}
	
	public long[] getDeadLockedThreads()
	{
		return this.deadLockedThreads;
	}
	
	@Override
	public String toString()
	{
		return TextThreadDumpWriter.printToString( this, "ThreadDump");
	}
}
