package friday.core.monitor;

import java.lang.Thread.State;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.ArrayList;
import java.util.HashMap;

import friday.core.event.process.ThreadStatusSnapshot;
import friday.core.event.thread.ThreadEventSequenceBuilder;
import friday.core.monitor.ActiveThread.Instantiated;
import friday.core.monitor.ActiveThread.ThreadCpuTimeTrace;

public class ThreadManager {
	
	final private ArrayList<Tracer> tracers = new ArrayList<Tracer>();
	
	public void addTracer(Tracer tracer){
		synchronized (tracers) {
			tracers.add(tracer);
		}
	}
	
	public ArrayList<Tracer> takeTracers(){
		ArrayList<Tracer> ret = new ArrayList<Tracer>();
		
		synchronized (tracers) {
			ret.addAll(tracers);
			tracers.clear();
		}
		
		return ret;
	}
	
	final private HashMap<String, ThreadEventSequenceBuilder> processingThreads = new HashMap<String, ThreadEventSequenceBuilder>();
	
	public void registerProcessingThread(ThreadEventSequenceBuilder tesb){
		
		synchronized (processingThreads) {
			processingThreads.put(tesb.threadEventId, tesb);
		}
		
	}
	
	public void removeProcessingThread(String threadEventId){
		
		synchronized (processingThreads) {
			processingThreads.remove(threadEventId);
		}
		
	}
	
	public ThreadEventSequenceBuilder getProcessingThread(String threadEventId){
		ThreadEventSequenceBuilder tesb = null;
		
		synchronized (processingThreads) {
			tesb = processingThreads.get(threadEventId);
		}
		
		return tesb;
	}
	
	private ThreadMXBean mBean = ManagementFactory.getThreadMXBean();

	final private ArrayList<ActiveThread> activeThreads = new ArrayList<ActiveThread>();
	
	final private HashMap<String, ThreadCpuTimeTrace> removableThreads = new HashMap<String, ActiveThread.ThreadCpuTimeTrace>();

	public Instantiated registerThread(String eventThreadId){
		
		ActiveThread activeThread = new ActiveThread(eventThreadId, mBean.getCurrentThreadCpuTime(), mBean.getCurrentThreadUserTime());
		
		synchronized (activeThreads) {
			activeThreads.add(activeThread);
		}
		
		return activeThread.instantiated;
		
	}
	
	public void removeThread(String eventThreadId){
		synchronized (removableThreads) {
			removableThreads.put(eventThreadId, new ThreadCpuTimeTrace(mBean.getCurrentThreadCpuTime(), mBean.getCurrentThreadUserTime()));
		}
	}
	
	public ArrayList<ThreadStatusSnapshot> takeThreadStatusSnapshot(){
		
		ArrayList<ThreadStatusSnapshot> ret = new ArrayList<ThreadStatusSnapshot>();
		
		ArrayList<ActiveThread> actives = new ArrayList<ActiveThread>();
		
		synchronized (this.activeThreads) {
			
			actives.addAll(activeThreads);
			
		}
	
		HashMap<String, ThreadCpuTimeTrace> removavles = new HashMap<String, ActiveThread.ThreadCpuTimeTrace>();
		
		synchronized (removableThreads) {
			removavles.putAll(removableThreads);
			removableThreads.clear();
		}
		
		for(ActiveThread at : actives){
			
			long cpuTime = -1L;
			
			long userCpuTime = -1L;
			
			Thread.State threadState = State.TERMINATED;
						
			if(removavles.containsKey(at.eventThreadId)){
				
				if(!at.isPassed){
					
					cpuTime = at.startCpuTime;
					cpuTime = at.startUserCpuTime;
					at.isPassed = true;
					
					ThreadCpuTimeTrace tctt = removavles.get(at.eventThreadId);

					synchronized (removableThreads) {
						removableThreads.put(at.eventThreadId, tctt);
					}
					
				}else{
					ThreadCpuTimeTrace tctt = removavles.get(at.eventThreadId);
					
					cpuTime = tctt.cpuTime;
					userCpuTime = tctt.userCpuTime;
					
					synchronized (activeThreads) {
						activeThreads.remove(at);
					}
				}
				
			}else{
				at.isPassed = true;
				cpuTime = mBean.getThreadCpuTime(at.systemThreadId);
				userCpuTime = mBean.getThreadUserTime(at.systemThreadId);
				
				ThreadInfo ti = mBean.getThreadInfo(at.systemThreadId);
				
				if(ti != null)threadState = ti.getThreadState();
				
			}
			
			ret.add(new ThreadStatusSnapshot(
					at.systemThreadId, 
					at.eventThreadId, 
					at.threadName, 
					cpuTime, 
					userCpuTime,
					at.instantiated.takeCount(),
					threadState
					));
			
		}
		
		return ret;
		
	}
	
	
}
