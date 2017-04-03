package friday.core.event.thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

import friday.core.event.EventBridge;
import friday.core.event.EventBuffer;
import friday.core.monitor.ActiveThread.Instantiated;
import friday.core.monitor.AgentMonitor;

public class LifeCycle {
	
	final public EventBuffer eventBuffer;
	
	final public ThreadMXBean mBean;
	
	final public Instantiated instantiated;
		
	public LifeCycle(){
		
		eventBuffer = EventBridge.bind();
				
		eventBuffer.append(new ThreadEventSequenceBuilder(eventBuffer.ID));
				
		mBean = ManagementFactory.getThreadMXBean();
		
		this.instantiated = AgentMonitor.getInstance().THREAD.registerThread(eventBuffer.ID);
		
	}
			
	private short depth = 0;
		
	public void up(){
			
		depth ++;

	}
	
	public int down(){				
		
		if(--depth == 0)end();

		return depth;
			
	}
	
	private void end(){
		
		try{
			
			eventBuffer.append(new ThreadEventTerminator());
			
			AgentMonitor.getInstance().THREAD.removeThread(eventBuffer.ID);

				
		}catch(Throwable ignored){
			
		}
		
	}
	
}
