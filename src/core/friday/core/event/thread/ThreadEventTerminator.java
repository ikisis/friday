package friday.core.event.thread;

import friday.core.event.IEventTerminable;
import friday.core.monitor.AgentMonitor;

public class ThreadEventTerminator implements IThreadEvent, IEventTerminable{
	
	long systemTime;
	
	public ThreadEventTerminator() {
		
		systemTime = System.currentTimeMillis();
		
	}

	@Override
	public void configure(ThreadEventSequenceBuilder tesb) {
		
		tesb.threadEndTime = systemTime;
				
		AgentMonitor.getInstance().addThreadDetail(tesb.build());
		
		AgentMonitor.getInstance().THREAD.removeProcessingThread(tesb.threadEventId);
		
	}

}
