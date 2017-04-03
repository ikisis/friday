/**
 * 작성된 날짜: Apr 9, 2015
 * Copyright 2014 BankwareGlobal co. All rights reserved.
 */
package friday.core.diagnostics;

import java.io.Serializable;

/**
 * @file			bxt.diagnostics.StackFrameInformation.java
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
public class StackFrameInformation implements Serializable
{
	private static final long serialVersionUID= -8979130017914582983L;
	
	private final StackTraceElement stackTraceElement;
	
	private final LockInformation lock;
	
	public StackFrameInformation( final StackTraceElement stackTraceElement, final LockInformation lock)
	{
		this.stackTraceElement= stackTraceElement;
		this.lock= lock;
	}
	
	public StackTraceElement getStackTraceElement()
	{
		return this.stackTraceElement;
	}
	
	public LockInformation getLock()
	{
		return this.lock;
	}

	@Override
	public String toString()
	{
		return new StringBuilder().append( this.stackTraceElement).toString();
	}
}
