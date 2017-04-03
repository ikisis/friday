/**
 * 작성된 날짜: Apr 8, 2015
 * Copyright 2014 BankwareGlobal co. All rights reserved.
 */
package friday.core.diagnostics;

/**
 * @file			bxt.diagnostics.Diagnostics.java
 * @filetype		java source file
 * @brief			
 * @author			JongOh Lee
 * @version			1.0
 * @history
 * 성		명		일			자			근  거  자  료			변  경  내  용
 * ----------------	-------------------		-------------------		--------------------------	
 * JongOh Lee :		Apr 8, 2015:			product 개발:			신규 작성
 *
 */
public interface Diagnostics 
{
	/**
	 * Get the diagnostics info for the whole JVM.
	 * @return a {@link MemoryInformation} instance.
	 * @throws Exception if any error occurs.
	 */
	MemoryInformation memoryInformation() throws Exception;

	/**
	 * Get the names of all live threads in the current JVM.
	 * @return an arrray of thread names as strings.
	 * @throws Exception if any error occurs.
	 */
	String[] threadNames() throws Exception;

	/**
	 * Get threda information, including detection of deadlocks
	 * @param id
	 * @param maxDepth
	 * @return a {@link ThreadInformation}
	 * @throws Exception if any error occurs.
	 */
	ThreadInformation getThreadInformation( long id, int maxDepth) throws Exception;
	
	/**
	 * Get a full thread dump, including detection of deadlocks.
	 * @return a {@link ThreadDump} instance.
	 * @throws Exception if any error occurs.
	 */
	ThreadDump threadDump() throws Exception;
	
	/**
	 * Determine whether a deadlock is detected in the JVM.
	 * @return <code>true</code> if a deadlock is detected, <code>false</code> otherwise.
	 * @throws Exception if any error occurs.
	 */
	Boolean hasDeadlock() throws Exception;
	
	/**
	 * Get a summarized snapshot of the JVM health.
	 * @return a {@link HealthSnapshot} instance.
	 * @throws Exception if any error occurs.
	 */	
	HealthSnapshot healthSnapshot() throws Exception;
	
	/**
	 * case JVM 1.7 +=
	 * Use JMX processCpuLoad Attribute of OperatingSystemMXBean
	 * 
	 * case JVM 1.6 -=
	 * Get an approximation of the current CPU load. The computed value is equal to
	 * <code>sum<sub>i</sub>(thread_used_cpu<sub>i</sub>) / interval</code>, for all the
	 * live threads of the JVM at the time of the computation. Thus, errors may occur,
	 * since many threads may have been created then died between two computations.
	 * However, in most cases this is a reasonable approximation, whose computation does not
	 * tax the CPU too heavily.
	 * @return the cpu load as a double value in the range <code>[0, 1]</code> 
	 * (ratio of <code>totalCpuTime / computationInterval</code>), or -1d if CPU time measurement is not available for the JVM.
	 */	
	Double cpuLoad();

}
