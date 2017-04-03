package friday.core.event.thread;

import friday.core.event.thread.model.CallPath;

public class CallPathBuilder {

	final public String path;
	
	final public String signature;
	
	final public long firstTime;
		
	public int startCount;
	
	public int endCount;
	
	final public ElapsedTimeBuilder systemTime = new ElapsedTimeBuilder();
	
	final public ElapsedTimeBuilder cpuTime = new ElapsedTimeBuilder();
	
	public CallPathBuilder(String path, String signature, long firstTime) {
		
		this.path = path;
		this.signature = signature;
		this.firstTime = firstTime;
		
	}
	
	public CallPath build(){
		return new CallPath(path, signature, firstTime, systemTime.build(), cpuTime.build(), startCount, endCount);
	}
	
}
