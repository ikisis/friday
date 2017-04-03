package friday.core.monitor;

import java.util.ArrayList;

import friday.core.event.WorkerReport;


public class WorkerManager {
	
	private final ArrayList<WorkerReport> workHistory = new ArrayList<WorkerReport>();
	
	public void addWorkResult(WorkerReport report){
		synchronized (workHistory) {
			workHistory.add(report);
		}
	}
	
	public ArrayList<WorkerReport> takeWorkerReports(){
		
		ArrayList<WorkerReport> flushed = new ArrayList<WorkerReport>();
		
		synchronized (workHistory) {
			
			flushed.addAll(workHistory);
			
			workHistory.clear();
			
		}
		
		return flushed;
	}
	
}
