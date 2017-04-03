package friday.core.monitor;

import java.io.Serializable;

public class ActiveThread implements Serializable{
	
	private static final long serialVersionUID = 6510479719717294047L;
	
	public final long systemThreadId;
	
	public final String eventThreadId;
	
	public final String threadName;
	
	public boolean isPassed = false;
	
	public long startCpuTime;
	
	public long startUserCpuTime;
	
	private int instantiationCount = 0;
	
	public final Instantiated instantiated = new Instantiated();	
	
	public ActiveThread(String eventThreadId, long startCpuTime, long startUserCpuTime){
		
		Thread t = Thread.currentThread();
		
		this.systemThreadId = t.getId();
		this.eventThreadId = eventThreadId;
		this.threadName = t.getName();
		this.startCpuTime = startCpuTime;
		this.startUserCpuTime = startUserCpuTime;
		
		
	}
	
	public static class ThreadCpuTimeTrace{
		public long cpuTime;
		
		public long userCpuTime;
		
		public ThreadCpuTimeTrace(long cpuTime, long userCpuTime){
			this.cpuTime = cpuTime;
			this.userCpuTime = userCpuTime;
		}
	}
	
	public class Instantiated{
		
		private final Object lock = new Object();
		
		public void increase(){
			synchronized (lock) {
				
				instantiationCount ++;
				
			}
		}
		
		public int takeCount(){
			
			int ret = instantiationCount;
			synchronized (lock) {
				
				instantiationCount = 0;
				
			}
			
			return ret;
		}
		
		
	}
	

}
