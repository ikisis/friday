package friday.core.event;

import java.util.concurrent.ConcurrentLinkedQueue;

import friday.core.util.Var;

public class EventBridge {
	
	private final EventBuffer eventBuffer;
	
	public final ConcurrentLinkedQueue<Events> supplyLine = new ConcurrentLinkedQueue<Events>();
	
	public final EventAppenderAdapter esb = new EventAppenderAdapter();
	
	public final Var<Boolean> isTerminated = new Var<Boolean>(false);

	private EventBridge() {
		
		eventBuffer = new EventBuffer(supplyLine);
		
	}
	
	public static EventBuffer bind(){
		
		EventBridge eb = new EventBridge();
		
		WorkerStage.get().register(eb);
		
		return eb.eventBuffer;
		
	}
	
	public void flush(){
		eventBuffer.flush();
	}

}
