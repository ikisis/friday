package friday.core.event.thread;

import java.lang.management.ManagementFactory;

public class MethodConstructorEvent implements IThreadEvent{
		
	public String signature;
		
	public long systemTime;
	
	public long cpuTime;
	
	public MethodConstructorEvent(String signature){
		
		this.signature = signature;
				
		this.systemTime = System.currentTimeMillis();
		
		this.cpuTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
				
	}

	public MethodConstructorEvent(String signature, long systemTime, long cpuTime){
		
		this.signature = signature;
				
		this.systemTime = systemTime;
		
		this.cpuTime = cpuTime;
				
	}
	
	@Override
	public void configure(ThreadEventSequenceBuilder esb) {
				
		CallPathBuilder callPathBuilder = null;
		
		if(esb.stack.isEmpty()){
			
			callPathBuilder = new CallPathBuilder(signature, signature, systemTime);
			
			esb.callPathMap.put(signature, callPathBuilder);
			
		}else{
			
			String key = esb.stack.peek().path + signature;
			
			callPathBuilder = esb.callPathMap.get(key);
			
			if(callPathBuilder == null){
				
				callPathBuilder = new CallPathBuilder(key, signature, systemTime);
				
				esb.callPathMap.put(key, callPathBuilder);

			}
			
		}
		
		callPathBuilder.startCount = callPathBuilder.startCount + 1;
		
		callPathBuilder.systemTime.start(systemTime);
		
		callPathBuilder.cpuTime.start(cpuTime);
		
		esb.stack.push(callPathBuilder);
		
	}

}
