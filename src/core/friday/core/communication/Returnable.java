package friday.core.communication;

import java.io.Serializable;

import friday.core.util.Var;

public abstract class Returnable<T extends Serializable> extends Consumable{

	private static final long serialVersionUID = -4055854999792553750L;
	
	private transient Waker waker = Waker.newInstance(this);
	
	private long key = waker.key;
	
	private Var<T> result = new Var<T>(null);
	
	public T pollReturned(){
		
		synchronized (result) {
			
			if(result.get() == null)
				waker.setWait();
			
		}
		
		return result.get();
		
	}
	
	@SuppressWarnings("unchecked")
	public void setResult(Serializable result){
		this.result.set((T)result);
	}
	
	public Returned createReturned(){
		
		Returned returned = new Returned(key, result.get());
		
		return returned;
	}
	
	public class Returned extends Consumable{

		private static final long serialVersionUID = 7992839877613870069L;
		
		long key;
		
		T result;
		
		public Returned(long key, T result){
			this.key = key;
			this.result = result;
		}
		
		@Override
		public void consume(){
			
			synchronized (Waker.wakerPool) {
				
				Waker waker = Waker.wakerPool.get(key);
				
				waker.result.setResult(result);
				
				waker.setNotify();
				
			}
			
		}

	}

}
