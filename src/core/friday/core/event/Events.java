package friday.core.event;

import java.util.ArrayList;
import java.util.Iterator;

public class Events implements Iterable<IEvent>{

	final private ArrayList<IEvent> items;
	
	public Events(int size) {
		this.items = new ArrayList<IEvent>(size);
	}

	public int size() {
		return this.items.size();
	}

	public boolean add(IEvent e) {
		return this.items.add(e);
	}

	@Override
	public Iterator<IEvent> iterator() {
		return this.items.iterator();
	}


	
}
