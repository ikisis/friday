/**
 * 작성된 날짜: Apr 10, 2015
 * Copyright 2014 BankwareGlobal co. All rights reserved.
 */
package friday.core.diagnostics;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
import java.util.Map;

/**
 * @file			bxt.diagnostics.TextThreadDumpWriter.java
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
public class TextThreadDumpWriter extends AbstractThreadDumpWriter
{
	private static final String BR= "\n";
	
	private final String title;
	
	public TextThreadDumpWriter( final Writer writer, final String title)
	{
		super( writer, "  ");
		this.title= title!= null ? title : "Thread dump";
	}
	
	@Override
	public void printDeadlocks( final ThreadDump dump)
	{
		String hr= padRight( "", '-', 80);
		long[] ids= dump.getDeadLockedThreads();
		if( ids== null || ids.length<= 0) 
			return;
		out.println( hr);
		out.println( "Deadlock detected"+ BR);
		Map<Long, ThreadInformation> threads= dump.getThreads();
		for( long id: ids)
		{
			ThreadInformation tinfo= threads.get( id);
			LockInformation lock= tinfo.getLockInformation();
			ThreadInformation owner= threads.get( tinfo.getLockOwnerId());
			out.println( "- "+ simpleName( tinfo)+ " is waiting to lock "+ 
					simpleName( lock)+ " which is held by "+ simpleName( owner));
		}
		out.println( "Stack trace information for the threads listed above"+ BR);
		for( long id: ids)
			printThread( threads.get( id));
		out.println( hr+ BR);
	}
	
	@Override
	public void printThread( final ThreadInformation tinfo)
	{
		StringBuilder sb= new StringBuilder();
		sb.append( "\"").append( tinfo.getName()).append( '"').append( " - ").append( tinfo.getId());
		sb.append( " - state: ").append( tinfo.getState());
		sb.append( " - blocked count: ").append(  tinfo.getBlockedCount());
		sb.append( " - blocked time: ").append( tinfo.getBlockedTime());
		sb.append( " - wait count: ").append( tinfo.getWaitCount());
		sb.append( " - wait time: ").append( tinfo.getWaitTime());
		sb.append( " - cpu time: ").append( tinfo.getCpuTime());
		sb.append( " - user time: ").append( tinfo.getUserTime());
		if( tinfo.isSuspended())
			sb.append( " - suspended");
		if( tinfo.isInNative())
			sb.append( " - in native code");
		sb.append( BR);
		
		incIndent();
		List<StackFrameInformation> stackTrace= tinfo.getStackTrace();
		if( stackTrace!= null && !stackTrace.isEmpty())
		{
			int count= 0;
			for( StackFrameInformation sfi: stackTrace)
			{
				sb.append( getIndent()).append( "at ").append( sfi).append( BR);
				if( count== 0 && tinfo.getLockInformation()!= null)
					sb.append( getIndent()).append( "- waiting on ").append( simpleName( tinfo.getLockInformation())).append( BR);
				LockInformation lock= sfi.getLock();
				if( lock!= null)
					sb.append( getIndent()).append( "- locked ").append( simpleName( lock)).append( BR);
				count++;
			}
		}
		
		List<LockInformation> synchronizers= tinfo.getOwnableSynchronizers();
		if( synchronizers!= null && !synchronizers.isEmpty())
		{
			sb.append( BR).append( getIndent()).append( "Locked ownable synchronizers:").append( BR);
			for( LockInformation lock: synchronizers)
				sb.append( getIndent()).append( "- ").append( simpleName( lock)).append( BR);
		}
		decIndent();
		out.println( sb);
	}
	
	@Override
	public void printThreadDump( final ThreadDump dump)
	{
		String hr= padRight( "", '-', title.length());
		out.println( hr);
		out.println( title);
		out.println( hr+ BR);
		super.printThreadDump( dump);
	}
	
	public static String printToString( final ThreadDump dump, final String title)
	{
		String result= null;
		ThreadDumpWriter writer= null;
		try
		{
			StringWriter sw= new StringWriter();
			writer= new TextThreadDumpWriter( sw, title);
			writer.printThreadDump( dump);
			result= sw.toString();
		}
		finally
		{
			if( writer!= null)	try{ writer.close();}catch( IOException e){ e.printStackTrace();}
		}
		return result;
	}
	
	@Override
	public void close() throws IOException
	{
		out.close();
	}
}
