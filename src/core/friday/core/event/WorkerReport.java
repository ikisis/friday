package friday.core.event;

import java.io.Serializable;

public class WorkerReport implements Serializable{
	
	private static final long serialVersionUID = 3332624836994364269L;

	final public Integer id;
	
	final public long startTime;
	
	public long eventCount;
	
	public long endTime;
	
	WorkerReport(Integer id, long startTime) {
		this.id = id;
		this.startTime = startTime;
		
	}
}

