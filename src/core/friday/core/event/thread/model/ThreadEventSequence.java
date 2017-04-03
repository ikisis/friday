package friday.core.event.thread.model;

import java.io.Serializable;
import java.util.ArrayList;

public class ThreadEventSequence implements Serializable{

	private static final long serialVersionUID = 820863095241707377L;

	public final String threadEventId;

	final public String threadName;
	
	final public long startTime;
	
	final public long baseTime;
	
	final public ArrayList<CallPath> callPatterns = new ArrayList<CallPath>();
	
	public ThreadEventSequence(String threadEventId, String threadName, long startTime, long baseTime){
		this.threadEventId = threadEventId;
		this.threadName = threadName;
		this.startTime = startTime;
		this.baseTime = baseTime; 
	}
	
	
}
