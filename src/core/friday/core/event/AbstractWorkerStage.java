package friday.core.event;

import java.util.concurrent.LinkedBlockingQueue;

import friday.core.util.Var;

public abstract class AbstractWorkerStage {
	
	private final LinkedBlockingQueue<EventBridge> cycle = new LinkedBlockingQueue<EventBridge>();
	
	public void register(EventBridge eb){
		cycle.offer(eb);
	}
	
	protected abstract class AbstractWokrer implements Runnable{
		
		public Var<Boolean> isFired = new Var<Boolean>(false);

		@Override
		public void run() {
			
			while(!isFired.get()){
				
				try {
					
					EventBridge eb = cycle.take();
										
					before(eb);
					
					process(eb);
					
					after(eb);	
					
					if(!eb.isTerminated.get())cycle.offer(eb);
	
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				} catch (Throwable th){
					//TODO loging
					
				}
				
			}
		}
		
		abstract protected void before(EventBridge eb);
		
		abstract protected void after(EventBridge eb);
	}
	
	abstract protected void process(EventBridge eb);
	
}
