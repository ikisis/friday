package friday.core.communication;

import java.util.HashMap;

public abstract class Consumer<T extends Consumable> {

	final static HashMap<String, Consumer<?>> CONSUMER_MAP = new HashMap<String, Consumer<?>>();
	
	protected static void run2string(Consumable consumable){
		System.out.println(consumable.toString());
	}
	
	public abstract void run(T consumable);
	
}
