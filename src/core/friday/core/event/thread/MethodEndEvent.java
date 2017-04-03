package friday.core.event.thread;

import java.lang.management.ManagementFactory;

public class MethodEndEvent implements IThreadEvent{
	
	public char type;
	
	public static final char END_RET_CHAR = 'r';
	
	public static final char END_EXCPN_CHAR = 'e';
	
	public long systemTime;
	
	public long cpuTime;
	
	public MethodEndEvent(char type){
		
		this.type = type;
		
		this.systemTime = System.currentTimeMillis();
		
		this.cpuTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
		
	}
	
	public MethodEndEvent(char type , long systemTime, long cpuTime){
		
		this.type = type;
		
		this.systemTime = systemTime;
		
		this.cpuTime = cpuTime;

		
	}

	@Override
	public void configure(ThreadEventSequenceBuilder esb) {
		
		esb.stack.peek().endCount = esb.stack.peek().endCount + 1;
		
		esb.stack.peek().systemTime.end(systemTime);
		
		esb.stack.peek().cpuTime.end(cpuTime);
		
		esb.stack.pop();
		
	}

	
}
