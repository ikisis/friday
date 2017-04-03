/**
 * 작성된 날짜: Apr 9, 2015
 * Copyright 2014 BankwareGlobal co. All rights reserved.
 */
package friday.core.diagnostics;

import java.io.Serializable;
import java.lang.management.LockInfo;
import java.lang.management.MonitorInfo;
import java.lang.management.ThreadInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @file			bxt.diagnostics.ThreadInformation.java
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
public class ThreadInformation implements Serializable
{
	private static final long serialVersionUID= -2177598941022648270L;
	
	private final long id;
	
	private final String name;
	
	private Thread.State state;
	
	private final List<StackFrameInformation> stackTrace;
	
	private final List<LockInformation> ownableSynchronizers;
	
	private final long waitCount;
	
	private final long waitTime;
	
	private final long blockedCount;
	
	private final long blockedTime;
	
	private final boolean suspended;
	
	private final boolean inNative;
	
	private final LockInformation lockInformation;
	
	private final long lockOwnerId;
	
	private final long cpuTime;
	
	private final long userTime;
	
	public ThreadInformation( final ThreadInfo info, long cpuTime, long userTime)
	{
		this.id= info.getThreadId();
		this.name= info.getThreadName();
		this.state= info.getThreadState();
		this.waitCount= info.getWaitedCount();
		this.waitTime= info.getWaitedTime();
		this.blockedCount= info.getBlockedCount();
		this.blockedTime= info.getBlockedTime();
		this.cpuTime= cpuTime;
		this.userTime= userTime;
		this.suspended= info.isSuspended();
		this.inNative= info.isInNative();
		this.lockOwnerId= info.getLockOwnerId();
		LockInfo linfo= info.getLockInfo();
		this.lockInformation= linfo!= null ? new LockInformation( linfo) : null;
		this.stackTrace= fillStackTrace( info);
		LockInfo[] sync= info.getLockedSynchronizers();
		if( sync!= null && sync.length> 0)
		{
			ownableSynchronizers= new ArrayList<LockInformation>();
			for( LockInfo lockInfo: sync)
				ownableSynchronizers.add( new LockInformation( lockInfo));
		}
		else
			ownableSynchronizers= null;
	}
	
	public long getId()
	{
		return this.id;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public Thread.State getState()
	{
		return this.state;
	}
	
	public List<StackFrameInformation> getStackTrace()
	{
		return this.stackTrace;
	}
	
	public List<LockInformation> getOwnableSynchronizers()
	{
		return this.ownableSynchronizers;
	}
	
	public long getWaitCount()
	{
		return this.waitCount;
	}
	
	public long getWaitTime()
	{
		return this.waitTime;
	}
	
	public long getBlockedCount()
	{
		return this.blockedCount;
	}
	
	public long getBlockedTime()
	{
		return this.blockedTime;
	}
	
	public long getCpuTime()
	{
		return this.cpuTime;
	}
	
	public long getUserTime()
	{
		return this.userTime;
	}
	
	public boolean isSuspended()
	{
		return this.suspended;
	}
	
	public boolean isInNative()
	{
		return this.inNative;
	}
	
	public LockInformation getLockInformation()
	{
		return this.lockInformation;
	}
	
	public long getLockOwnerId()
	{
		return this.lockOwnerId;
	}
	
	private List<StackFrameInformation> fillStackTrace( final ThreadInfo info)
	{
		StackTraceElement[] ste= info.getStackTrace();
		if( ste== null || ste.length<= 0) return null;
		List<StackFrameInformation> result= new ArrayList<StackFrameInformation>();
		SortedMap<Integer, LockInformation> lockInfoMap= new TreeMap<Integer, LockInformation>();
		for( MonitorInfo minfo: info.getLockedMonitors())
		{
			int idx= minfo.getLockedStackDepth();
			if( idx>= 0) lockInfoMap.put( idx, new LockInformation( minfo));
		}
		for( int i= 0; i < ste.length; i++)
			result.add( new StackFrameInformation( ste[i], lockInfoMap.get( i)));
		return result;
	}

}
