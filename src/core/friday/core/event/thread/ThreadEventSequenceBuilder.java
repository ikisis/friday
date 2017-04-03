package friday.core.event.thread;

import java.util.LinkedHashMap;
import java.util.Stack;

import friday.core.event.IEvent;
import friday.core.event.IEventAppendable;
import friday.core.event.thread.model.ThreadEventSequence;
import friday.core.monitor.AgentMonitor;

public class ThreadEventSequenceBuilder implements IEvent, IEventAppendable<IThreadEvent>{
	
	public final String threadEventId;
	
	final String threadName;
	
	final long threadStartTime;
	
	long threadEndTime;
	
	final LinkedHashMap<String,CallPathBuilder> callPathMap = new LinkedHashMap<String, CallPathBuilder>();
	
	final Stack<CallPathBuilder> stack = new Stack<CallPathBuilder>();
	
	public ThreadEventSequenceBuilder(String threadEventId) {
		
		this.threadEventId = threadEventId;
		
		threadStartTime = System.currentTimeMillis();
		
		threadName = Thread.currentThread().getName();
		
		AgentMonitor.getInstance().THREAD.registerProcessingThread(this);
		
	}

	@Override
	public void append(IThreadEvent event) {
		
		event.configure(this);
		
	}
	
	public ThreadEventSequence build(){
		
		ThreadEventSequence tes = new ThreadEventSequence(threadEventId, threadName, threadStartTime, threadEndTime);
		
		for(String key : callPathMap.keySet()){
			
			tes.callPatterns.add(callPathMap.get(key).build());
			
		}
		
		return tes;
	}

}
