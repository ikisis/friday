package friday.core.monitor;

import friday.core.communication.AgentCommunicator;
import friday.core.diagnostics.Diagnostics;
import friday.core.diagnostics.DiagnosticsBean;
import friday.core.diagnostics.HealthSnapshot;
import friday.core.event.process.ProcessStatusEvent;
import friday.core.monitor.AgentMonitor.IAgentMonitorHandler;

class DefaultAgentMonitorHandler implements IAgentMonitorHandler{

	private final Diagnostics diagnostics;
	
	public DefaultAgentMonitorHandler() {
		
		this.diagnostics = DiagnosticsBean.getInstance();
		
	}

	
	@Override
	public void run(long systemTime) {
				
		HealthSnapshot health = null;
		
		try {health = diagnostics.healthSnapshot();} catch (Exception e) {
			
			//Logger.ERROR(e.toString());
			
			throw new RuntimeException(e);
			
		}
						
		ProcessStatusEvent event = new ProcessStatusEvent(systemTime, health);
		
		event.loadedMethods = AgentMonitor.getInstance().RESOURCE.takeLoadedMethods();
		
		event.threadInfos.addAll(AgentMonitor.getInstance().THREAD.takeThreadStatusSnapshot());
		
		event.finishedThreads = AgentMonitor.getInstance().takeBufferedThreadDetails();
				
		event.tracers = AgentMonitor.getInstance().THREAD.takeTracers();
		
		event.logs = AgentMonitor.getInstance().LOG.takeLogs();
		
		event.workerReports = AgentMonitor.getInstance().WORK.takeWorkerReports();
		
		
		
		AgentCommunicator.getInstance().CHAT.send(event);
		
		
		
	}

}
