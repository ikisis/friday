/**
 * 작성된 날짜: Apr 9, 2015
 * Copyright 2014 BankwareGlobal co. All rights reserved.
 */
package friday.core.diagnostics;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.Map;

/**
 * @file			bxt.diagnostics.AbstractThreadDumpWriter.java
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
public abstract class AbstractThreadDumpWriter implements ThreadDumpWriter
{
	protected final PrintWriter out;
	
	protected final String indentString;
	
	protected int indentLevel;
	
	protected StringBuilder indent= new StringBuilder();
	
	public AbstractThreadDumpWriter( final Writer writer, final String indentString)
	{
		if( writer== null) throw new IllegalArgumentException( "writer cannot be null.");
		this.out= ( writer instanceof PrintWriter) ? (PrintWriter)writer : new PrintWriter( writer);
		this.indentString= indentString;
	}
	
	public static String padRight( final String source, final char padChar, final int maxLen)
	{
		return padRight( source, padChar, maxLen, true);
	}
	
	public static String padRight( final String source, final char padChar, final int maxLen, final boolean truncate)
	{
		String s= source;
		if( s== null) s= "";
		if( s.length()> maxLen)	return truncate ? s= s.substring( 0, maxLen) : s;
		StringBuilder sb= new StringBuilder( s);
		while( sb.length()< maxLen)
			sb.append( padChar);
		return sb.toString();
	}
	
	public static String padLeft( final String source, final char padChar, final int maxLen)
	{
		String s= source;
		if( s== null) s= "";
		int length= s.length();
		if( length> maxLen)
			return source;
		StringBuilder sb= new StringBuilder();
		for( int i= 0; i< maxLen- length; i++)
			sb.append( padChar);
		sb.append( s);
		return sb.toString();
	}
	
	@Override
	public void printThreadDump( final ThreadDump dump)
	{
		printDeadlocks( dump);
		for( Map.Entry<Long, ThreadInformation> entry: dump.getThreads().entrySet())
			printThread( entry.getValue());
	}
	
	protected String simpleName( final ThreadInformation info)
	{
		return new StringBuilder().append( "thread id ").append( info.getId())
				.append( " \"").append( info.getName()).append( '"').toString();
	}

	protected String simpleName( final LockInformation info)
	{
		return new StringBuilder().append( info.getClassName()).append( '@')
				.append( Integer.toHexString( info.getIdentityHashcode())).toString();
	}
	
	@Override
	public void printString( final String message)
	{
		out.print(  message);
	}
	
	protected void incIndent()
	{
		indentLevel++;
		this.indent= new StringBuilder();
		for( int i= 0; i < indentLevel; i++)
			this.indent.append( this.indentString);
	}
	
	protected void decIndent()
	{
		indentLevel--;
		this.indent= new StringBuilder();
		for( int i= 0; i < indentLevel; i++)
			this.indent.append( indentString);
	}
	
	protected String getIndent()
	{
		return indent.toString();
	}
	
}
