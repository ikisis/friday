package friday.core.event.thread;

import friday.core.event.thread.model.ElapsedTime;

public class ElapsedTimeBuilder {
	
	private long total = 0;
	
	private long min = Long.MIN_VALUE;
	
	private long max = Long.MAX_VALUE;
	
	private int count = 0;
	
	
	private long startTime;
	
	
	public void start(long startTime){
		this.startTime = startTime;
	}

	public void end(long endTime){
		
		long elapsedTime = endTime - startTime;
		
		total += elapsedTime;
		
		min = min > elapsedTime ? elapsedTime : min;
		
		max = max < elapsedTime ? elapsedTime : max;
		
		count ++;
		
	}
	
	public ElapsedTime build(){
		return new ElapsedTime(total, min, max, count);
	}
	
	
}
