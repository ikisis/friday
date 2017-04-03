package friday.core.communication.contents;

import java.util.ArrayList;

import friday.core.communication.Returnable;
import friday.core.event.thread.model.ThreadEventSequence;

public class ThreadEventSequenceGettable extends Returnable<ArrayList<ThreadEventSequence>>{

	private static final long serialVersionUID = 5066477628782913593L;
	
	public final ArrayList<String> threadEventIds = new ArrayList<String>();

	
	
}
