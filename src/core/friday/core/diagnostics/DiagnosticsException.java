/**
 * 작성된 날짜: Apr 10, 2015
 * Copyright 2014 BankwareGlobal co. All rights reserved.
 */
package friday.core.diagnostics;

/**
 * @file			bxt.diagnostics.DiagnosticsException.java
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
public class DiagnosticsException extends Exception
{
	private static final long serialVersionUID= 1829399433697700013L;

	public DiagnosticsException( final String message, final Throwable cause)
	{
		super( message, cause);
	}
	
	public DiagnosticsException( final String message)
	{
		super( message);
	}
	
	public DiagnosticsException( final Throwable cause)
	{
		super( cause);
	}
}
