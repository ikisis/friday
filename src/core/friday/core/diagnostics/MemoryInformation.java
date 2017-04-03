/**
 * 작성된 날짜: Apr 9, 2015
 * Copyright 2014 BankwareGlobal co. All rights reserved.
 */
package friday.core.diagnostics;

import java.io.Serializable;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;

/**
 * @file			bxt.diagnostics.MemoryInformation.java
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
public class MemoryInformation implements Serializable
{
	private static final long serialVersionUID= 4757468683159784279L;
	
	private MemoryUsageInformation heapMemoryUsage;
	private MemoryUsageInformation nonHeapMemoryUsage;
	
	public MemoryInformation()
	{
		collectMemoryUsage();
	}
	
	public MemoryUsageInformation getHeapMemoryUsage()
	{
		return this.heapMemoryUsage;
	}
	
	public MemoryUsageInformation getNonHeapMemoryUsage()
	{
		return this.nonHeapMemoryUsage;
	}
	
	private void collectMemoryUsage()
	{
		MemoryMXBean mbean= ManagementFactory.getMemoryMXBean();
		this.heapMemoryUsage= new MemoryUsageInformation( mbean.getHeapMemoryUsage());
		this.nonHeapMemoryUsage= new MemoryUsageInformation( mbean.getNonHeapMemoryUsage());
	}
	
	@Override
	public String toString()
	{
		StringBuilder sb= new StringBuilder();
		sb.append( "heap    : ").append( heapMemoryUsage).append( '\n');
		sb.append( "non-heap: ").append( nonHeapMemoryUsage).append( '\n');
		return sb.toString();
	}

}
