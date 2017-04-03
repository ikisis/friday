package friday.core.communication.contents;

import java.util.ArrayList;

import friday.core.communication.Consumer;
import friday.core.event.thread.ThreadEventSequenceBuilder;
import friday.core.event.thread.model.ThreadEventSequence;
import friday.core.monitor.AgentMonitor;

public class ThreadEventSequenceGettableConsumer extends Consumer<ThreadEventSequenceGettable>{

	@Override
	public void run(ThreadEventSequenceGettable consumable) {
		
		ArrayList<ThreadEventSequence> result = new ArrayList<ThreadEventSequence>();
		
		for(String threadEventId : consumable.threadEventIds){
			
			ThreadEventSequenceBuilder tesb = AgentMonitor.getInstance().THREAD.getProcessingThread(threadEventId);
			
			synchronized (tesb) {
				result.add(tesb.build());
			}
			
		}
		
		consumable.setResult(result);
		
	}

}
