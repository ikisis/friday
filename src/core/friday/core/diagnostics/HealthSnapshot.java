/**
 * 작성된 날짜: Apr 10, 2015
 * Copyright 2014 BankwareGlobal co. All rights reserved.
 */
package friday.core.diagnostics;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * @file			bxt.diagnostics.HealthSnapshot.java
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
public class HealthSnapshot implements Serializable
{
	private static final long serialVersionUID= -4366167677907540455L;

	private static final double MBytes= 1024* 1024;
	
	double heapUsedRatio= -1;
	
	long heapUsed= -1L;
	
	double nonheapUsedRatio= -1;
	
	long nonheapUsed= -1L;
	
	boolean deadlocked;
	
	int liveThreads= -1;
	
	double processCpuLoad= -1;
	
	double systemCpuLoad= -1;
	
	double ramUsedRatio= -1;
	
	long ramUsed= -1L;
	
	public double getHeapUsedRatio()
	{
		return this.heapUsedRatio;
	}
	
	public double getNonheapUsedRatio()
	{
		return this.nonheapUsedRatio;
	}
	
	public boolean isDeadLocked()
	{
		return this.deadlocked;
	}
	
	public long getHeapUsed()
	{
		return this.heapUsed;
	}
	
	public long getNonheapUsed()
	{
		return this.nonheapUsed;
	}
	
	public int getLiveThreads()
	{
		return this.liveThreads;
	}
	
	public double getCpuLoad()
	{
		return this.processCpuLoad;
	}
	
	public double getSystemCpuLoad()
	{
		return this.systemCpuLoad;
	}
	
	public double getRamUsedRatio()
	{
		return this.ramUsedRatio;
	}
	
	public long getRamUsed()
	{
		return this.ramUsed;
	}
	
	@Override
	public String toString()
	{
		StringBuilder sb= new StringBuilder( getClass().getSimpleName()).append( '[')
		.append( "heapUsedRatio= ").append( this.heapUsedRatio)
		.append( ", heapUsed= ").append( this.heapUsed)
		.append( ", nonheapUsedRatio= ").append( this.nonheapUsedRatio)
		.append( ", nonheapUsed= ").append( this.nonheapUsed)
		.append( ", deallocked= ").append( this.deadlocked)
		.append( ", liveThreads= ").append( this.liveThreads)
		.append( ", cpuLoad= ").append( this.processCpuLoad)
		.append( ", systemCpuLoad= ").append( this.systemCpuLoad)
		.append( ", ramUsedRatio= ").append( this.ramUsedRatio)
		.append( ", ramUsed= ").append( this.ramUsed);
		return sb.append( ']').toString();
	}
	
	public String toFormattedString()
	{
		return toFormattedString( Locale.getDefault());
	}
	
	public String toFormattedString( Locale locale)
	{
		locale= locale== null ? Locale.getDefault() : locale;
		NumberFormat nf= NumberFormat.getNumberInstance( locale);
		nf.setGroupingUsed( true);
		nf.setMaximumFractionDigits( 1);
		nf.setMinimumFractionDigits( 1);
		nf.setMinimumIntegerDigits( 1);
		StringBuilder sb= new StringBuilder();
		sb.append( getClass().getSimpleName()).append( '\n');
		sb.append( "heapUsedRatio=").append( format2( this.heapUsedRatio, nf));
		sb.append( "\nheapUsed=").append( format( this.heapUsed/MBytes, nf)).append( "MB");
		sb.append( "\nnonheapUsedRatio=").append( format2( this.nonheapUsedRatio, nf));
		sb.append( "\nnonheapUsed=").append( format( this.nonheapUsed/MBytes, nf)).append( "MB");
		sb.append( "\ndeadlocked=").append( this.deadlocked);
		sb.append( "\nliveThreads=").append( this.liveThreads);
		sb.append( "\ncpuLoad=").append( format2( this.processCpuLoad, nf));
		sb.append( "\nsystemCpuLoad=").append( format2( this.systemCpuLoad, nf));
		sb.append( "\nramUsedRatio=").append( format2( this.ramUsedRatio, nf));
		sb.append( "\nramUsed=").append( format( this.ramUsed/MBytes, nf)).append( "MB");
		return sb.toString();
	}
	
	private String format( final double value, final NumberFormat nf)
	{
		return AbstractThreadDumpWriter.padLeft( nf.format( value), ' ', 6);
	}
	
	private String format2( final double value, final NumberFormat nf)
	{
		return value< 0 ? "  n/a " : new StringBuilder( format( 100* value, nf)).append( " %").toString();
	}

	@Override
	public int hashCode()
	{
		final int prime= 31;
		int result= 1;
		result= prime * result + ( deadlocked ? 1231 : 1237);
		result= prime * result + (int)( heapUsed ^ ( heapUsed >>> 32));
		long temp;
//		temp= Double.doubleToLongBits( heapUsedRatio);
//		result= prime * result + (int)( temp ^ ( temp >>> 32));
		result= prime * result + liveThreads;
		result= prime * result + (int)( nonheapUsed ^ ( nonheapUsed >>> 32));
//		temp= Double.doubleToLongBits( nonheapUsedRatio);
//		result= prime * result + (int)( temp ^ ( temp >>> 32));
		temp= Double.doubleToLongBits( processCpuLoad);
		result= prime * result + (int)( temp ^ ( temp >>> 32));
		result= prime * result + (int)( ramUsed ^ ( ramUsed >>> 32));
//		temp= Double.doubleToLongBits( ramUsedRatio);
//		result= prime * result + (int)( temp ^ ( temp >>> 32));
		temp= Double.doubleToLongBits( systemCpuLoad);
		result= prime * result + (int)( temp ^ ( temp >>> 32));
		return result;
	}

	@Override
	public boolean equals( Object obj)
	{
		if( this == obj)
			return true;
		if( obj == null)
			return false;
		if( getClass() != obj.getClass())
			return false;
		HealthSnapshot other= (HealthSnapshot)obj;
		if( deadlocked != other.deadlocked)
			return false;
		if( heapUsed != other.heapUsed)
			return false;
//		if( Double.doubleToLongBits( heapUsedRatio) != Double.doubleToLongBits( other.heapUsedRatio))
//			return false;
		if( liveThreads != other.liveThreads)
			return false;
		if( nonheapUsed != other.nonheapUsed)
			return false;
//		if( Double.doubleToLongBits( nonheapUsedRatio) != Double.doubleToLongBits( other.nonheapUsedRatio))
//			return false;
		if( Double.doubleToLongBits( processCpuLoad) != Double.doubleToLongBits( other.processCpuLoad))
			return false;
		if( ramUsed != other.ramUsed)
			return false;
//		if( Double.doubleToLongBits( ramUsedRatio) != Double.doubleToLongBits( other.ramUsedRatio))
//			return false;
		if( Double.doubleToLongBits( systemCpuLoad) != Double.doubleToLongBits( other.systemCpuLoad))
			return false;
		return true;
	}
	
	
	
	
}
