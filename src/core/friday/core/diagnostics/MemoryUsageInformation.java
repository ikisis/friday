/**
 * 작성된 날짜: Apr 9, 2015
 * Copyright 2014 BankwareGlobal co. All rights reserved.
 */
package friday.core.diagnostics;

import java.io.Serializable;
import java.lang.management.MemoryUsage;

/**
 * @file			bxt.diagnostics.MemoryUsageInformation.java
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
public class MemoryUsageInformation implements Serializable
{
	private static final long serialVersionUID= -4266953437299227463L;
	
	private final long init;
	
	private final long committed;
	
	private final long used;
	
	private final long max;
	
	public MemoryUsageInformation( final MemoryUsage memUsage)
	{
		this.init= memUsage.getInit();
		this.committed= memUsage.getCommitted();
		this.used= memUsage.getUsed();
		this.max= memUsage.getMax();
	}

	public long getInit()
	{
		return init;
	}

	public long getCommitted()
	{
		return committed;
	}

	public long getUsed()
	{
		return used;
	}

	public long getMax()
	{
		return max;
	}
	
	public double getUsedRatio()
	{
		if( max<= 0) return -1;
		return (double)used/ (double)max;
	}

	@Override
	public String toString()
	{
		StringBuilder sb= new StringBuilder();
		sb.append( "MemoryUsageInformation [init=").append( init);
		sb.append(  ", committed=").append( committed);
		sb.append( ", used=").append( used);
		sb.append(  ", max=").append( max).append( "]");
		return sb.toString();
	}
}
