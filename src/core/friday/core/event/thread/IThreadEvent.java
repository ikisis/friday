package friday.core.event.thread;

import friday.core.event.IEvent;

public interface IThreadEvent extends IEvent{

	public void configure(ThreadEventSequenceBuilder esb);
	
}
