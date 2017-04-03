package friday.core.monitor;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import friday.core.event.thread.model.ThreadEventSequence;

public class AgentMonitor {
	
	private static long INTERVAL = 1000;
	
	private static volatile AgentMonitor ins;
	
	public static AgentMonitor getInstance(){
		if(ins == null)synchronized (AgentMonitor.class) {if(ins == null)ins = new AgentMonitor();}
		return ins;
	}
	
	public static void on(){
		
		getInstance();
	}
	
	public final ResourceManager RESOURCE = new ResourceManager();	

	public final ThreadManager THREAD = new ThreadManager();

	public final AgentLogManager LOG = new AgentLogManager();
	
	public final WorkerManager WORK = new WorkerManager();
	
	private ArrayList<ThreadEventSequence> threadDetails = new ArrayList<ThreadEventSequence>();
	
	public void addThreadDetail(ThreadEventSequence tes){
		synchronized (threadDetails) {
			threadDetails.add(tes);
		}
	}
	
	public ArrayList<ThreadEventSequence> takeBufferedThreadDetails(){
		ArrayList<ThreadEventSequence> ret = new ArrayList<ThreadEventSequence>();
		
		synchronized (threadDetails) {
			ret.addAll(threadDetails);
			threadDetails.clear();
		}
		
		return ret;
		
	}
	
	
	private ArrayList<IAgentMonitorHandler> handlers = new ArrayList<AgentMonitor.IAgentMonitorHandler>();
	
	protected AgentMonitor(){
		
		synchronized (handlers) {
			handlers.add(new DefaultAgentMonitorHandler());
		}
		
		new Thread(new Runnable() {
			
			private final Executor exec = Executors.newSingleThreadExecutor();
						
			@Override
			public void run() {
				
				while(true){
					
					final long systemTime = System.currentTimeMillis();
					
					
					try{
						
						exec.execute(new Runnable() {@Override public void run() {	handle(systemTime);	}});
						
						sleep();
						
					}catch(Throwable ignored){
						
					}
					
				}
				
			}
		}).start();
	}
	
	private void handle(long systemTime){
		
		for(IAgentMonitorHandler handler : handlers){
			
			try{	handler.run(systemTime);	}catch(Throwable ignored){
				
			}
			
		}
		
	}
	
	private void sleep(){
		try {Thread.sleep(INTERVAL);} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
	
	public interface IAgentMonitorHandler{
		public void run(long systemTime);
	}
	
	
}
