package friday.core.communication;

import java.io.Serializable;

public abstract class Consumable implements Serializable{
	
	private static final long serialVersionUID = -2810376483364760123L;
	
	public static void addConsumer(Class<? extends Consumable> consumable, Consumer<?> consumer){
		
		synchronized (Consumer.CONSUMER_MAP) {
			
			Consumer.CONSUMER_MAP.put(consumable.getName(), consumer);
			
		}
		
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void consume(){
		
		Consumer consumer = Consumer.CONSUMER_MAP.get(getClass().getName());
		
		//TODO remove
		if(consumer == null){
			Consumer.run2string(this);
			return;
		}
		
		
		consumer.run(this);
		
	}

}
