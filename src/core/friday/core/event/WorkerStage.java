package friday.core.event;

import friday.core.monitor.AgentMonitor;

public class WorkerStage extends AbstractWorkerStage{
	
	private static final int DEFAULT_WORKER_COUNT = 32;
	
	IEvent notNull = new IEvent() {};
	
	private static volatile WorkerStage ins;
	
	public static WorkerStage get(){
		
		if(ins == null)synchronized (WorkerStage.class) {if(ins == null)ins = new WorkerStage();}
		
		return ins;
	}
		
	private WorkerStage(){

		for(int i = 0;i<DEFAULT_WORKER_COUNT; i++){
			new Thread(new Wokrer(i)).start();
		}
		
	}
	
	private static ThreadLocal<WorkerReport> workerLocal = new ThreadLocal<WorkerReport>();
	
	protected void process(EventBridge eb){
		
		Events events = null;
		
		IEvent last = notNull;

		
		while((events = eb.supplyLine.poll()) != null){
			
			
			for(IEvent e : events){
				
				synchronized (eb.esb) {
					
					eb.esb.append(e);
					
				}
				
				last = e;
				
			}
			
			workerLocal.get().eventCount += events.size();

			
		}
		
		if(last instanceof IEventTerminable){
			
			synchronized (eb.isTerminated) {
				eb.isTerminated.set(true);
			}
			
		}else{
			
			eb.flush();
			
		}
		
				
	}
	
	class Wokrer extends AbstractWokrer{
		
		private final Integer id;
		
		Wokrer(Integer id){
			this.id = id;
		}
		

		@Override
		protected void before(EventBridge eb){
			workerLocal.set(new WorkerReport(id, System.currentTimeMillis()));
		}
		
		@Override
		protected void after(EventBridge eb){
			
			try{
				
				WorkerReport throughput = workerLocal.get();
			
				throughput.endTime = System.currentTimeMillis();
				
				if(throughput.eventCount > 0)
					AgentMonitor.getInstance().WORK.addWorkResult(throughput);
			
			}finally{
				
				workerLocal.remove();
				
			}
		}
		
		
	}
	
}
