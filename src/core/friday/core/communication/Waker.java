package friday.core.communication;

import java.util.HashMap;

import friday.core.util.Var;

public class Waker {
	
	static final HashMap<Long, Waker> wakerPool = new HashMap<Long, Waker>();
	
	private static final Var<Long> SEED = new Var<Long>(0L);
	
	public static Waker newInstance(Returnable<?> returnable){
		return new Waker(returnable);
	}
	
	
	
	public final long key;
	
	public Returnable<?> result;
	
	private Object lock = new Object();
	
	private Waker(Returnable<?> returnable){
		
		synchronized (SEED) {
			
			key = SEED.get();
			
			SEED.set(key + 1L);
			
		}
		
		
		this.result = returnable;
		
		synchronized (wakerPool) {
			
			wakerPool.put(key, this);
		}
		
	}
	
	
	
	void setWait(){
		
		synchronized (lock) {
			
			try {	lock.wait(10000);	} catch (InterruptedException e) {
				
				Thread.currentThread().interrupt();
				
			}
			
		}
		
	}
	
	void setNotify(){
		synchronized (lock) {
			lock.notifyAll();
		}
	}
	

}
