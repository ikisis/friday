package friday.core.event.thread.model;

import java.io.Serializable;

public class CallPath implements Serializable{

	private static final long serialVersionUID = 893635811464003900L;

	final public String path;
	
	final public String signature;
	
	final public long firstTime;
		 
	final public ElapsedTime systemTime;
	
	final public ElapsedTime cpuTime;
	
	final public int startCount;
	
	final public int endCount;
		
	public CallPath(
			String path, 
			String signature, 
			long firstTime, 
			ElapsedTime systemTime, 
			ElapsedTime cpuTime,
			int startCount,
			int endCount
			){
		this.path = path;
		this.signature = signature;
		this.firstTime = firstTime;
		this.systemTime = systemTime;
		this.cpuTime = cpuTime;
		this.startCount = startCount;
		this.endCount = endCount;
	}

}
