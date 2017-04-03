/**
 * 작성된 날짜: Apr 9, 2015
 * Copyright 2014 BankwareGlobal co. All rights reserved.
 */
package friday.core.diagnostics;

import java.io.Serializable;
import java.lang.management.LockInfo;

/**
 * @file			bxt.diagnostics.LockInformation.java
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
public class LockInformation implements Serializable
{
	private static final long serialVersionUID= 2320966484324185703L;
	
	private final String className;
	
	private final int identityHashcode;
	
	public LockInformation( final String className, final int identityHashcode)
	{
		this.className= className;
		this.identityHashcode= identityHashcode;
	}
	
	public LockInformation( final LockInfo lockInfo)
	{
		this( lockInfo.getClassName(), lockInfo.getIdentityHashCode());
	}
	
	public String getClassName()
	{
		return this.className;
	}
	
	public int getIdentityHashcode()
	{
		return this.identityHashcode;
	}
	
	@Override
	public String toString()
	{
		return new StringBuilder().append( getClass().getSimpleName())
				.append( "[className=").append( this.className)
				.append( ", identityHashcode=").append( identityHashcode).append( "]")
				.toString();
	}
}
