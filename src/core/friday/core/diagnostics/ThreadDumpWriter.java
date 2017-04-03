/**
 * 작성된 날짜: Apr 9, 2015
 * Copyright 2014 BankwareGlobal co. All rights reserved.
 */
package friday.core.diagnostics;

import java.io.Closeable;

/**
 * @file			bxt.diagnostics.ThreadDumpWriter.java
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
public interface ThreadDumpWriter extends Closeable
{
	void printString( String message);
	
	void printDeadlocks( ThreadDump threadDump);
	
	void printThread( ThreadInformation threadInfo);
	
	void printThreadDump( ThreadDump threadDump);
}
