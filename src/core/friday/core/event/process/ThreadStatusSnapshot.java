package friday.core.event.process;

import java.io.Serializable;

public class ThreadStatusSnapshot implements Serializable{

	private static final long serialVersionUID = -3641853972184724222L;

	final public long systemThreadId;
	
	final public String threadEventId;
	
	final public String threadName;
	
	final public long cpuTime;
	
	final public long userCpuTime;
	
	final public int instantiationCount;
	
	final public Thread.State state;
	
	public ThreadStatusSnapshot(long systemThreadId, String threadEventId, String threadName, long cpuTime, long userCpuTime, int instantiationCount, Thread.State state) {
		this.systemThreadId = systemThreadId;
		this.threadEventId = threadEventId;
		this.threadName = threadName;
		this.cpuTime = cpuTime;
		this.userCpuTime = userCpuTime;
		this.instantiationCount = instantiationCount;
		
		this.state = state;
	}
	
}
