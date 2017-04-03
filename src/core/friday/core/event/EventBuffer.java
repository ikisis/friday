package friday.core.event;

import java.util.Queue;

import friday.core.context.id.HexSequence;
import friday.core.util.Var;

public class EventBuffer {
	
	private static final HexSequence hexSequence = new HexSequence(8);
	
	private static final int DEFAULT_BUFFER_SIZE = 4096;
	
	private final int bufferSize = DEFAULT_BUFFER_SIZE;
	
	private final Var<Events> buffer;
	
	private final Queue<Events> portal;
	
	public final String ID;
	
	public EventBuffer(Queue<Events> queue){
		
		synchronized (hexSequence) {
			ID = hexSequence.next();
		}
		
		buffer  = new Var<Events>(new Events(DEFAULT_BUFFER_SIZE));
		portal = queue;
	}
	
	public void append(IEvent event){
		
		synchronized (buffer) {
			
			if(buffer.get().size() >= bufferSize){
				
				flush();
				
			}
			
			buffer.get().add(event);
			
		}
		
	}

	public void flush() {
		
		synchronized (buffer) {
			
			if(buffer.get().size() == 0)return;
			
			portal.offer(buffer.get());
			
			buffer.set(new Events(bufferSize));
			
		}
		
	}
	
	
}
