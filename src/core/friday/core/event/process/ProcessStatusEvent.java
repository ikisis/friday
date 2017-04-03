package friday.core.event.process;

import java.util.ArrayList;

import friday.core.communication.Consumable;
import friday.core.context.Central;
import friday.core.diagnostics.HealthSnapshot;
import friday.core.event.IEvent;
import friday.core.event.WorkerReport;
import friday.core.event.thread.model.ThreadEventSequence;
import friday.core.monitor.MethodIdentifier;
import friday.core.monitor.Tracer;
import friday.core.msg.SystemMessage;

public class ProcessStatusEvent extends Consumable implements IEvent{

	private static final long serialVersionUID = 779229977092502857L;

	public String pid;

	public long systemTime;
	
	public HealthSnapshot health;
	
	public ArrayList<WorkerReport> workerReports = new ArrayList<WorkerReport>();
	
	public ArrayList<SystemMessage> logs = new ArrayList<SystemMessage>();
	
	public ArrayList<MethodIdentifier> loadedMethods = new ArrayList<MethodIdentifier>();
	
	public ArrayList<ThreadStatusSnapshot> threadInfos = new ArrayList<ThreadStatusSnapshot>();
	
	public ArrayList<ThreadEventSequence> finishedThreads = new ArrayList<ThreadEventSequence>();
	
	public ArrayList<Tracer> tracers = new ArrayList<Tracer>();
	
	public ProcessStatusEvent(long systemTime, HealthSnapshot health) {
		pid = Central.INFO.getPid();
		this.systemTime = systemTime;
		this.health = health;
	}
}
