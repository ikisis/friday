package friday.core.event.thread.model;

import java.io.Serializable;

public class ElapsedTime implements Serializable{

	private static final long serialVersionUID = 6233935183681911570L;

	final public long total;
	
	final public long min;
	
	final public long max;
	
	final public int count;
	
	public ElapsedTime(long total, long min, long max, int count){
		this.total = total;
		this.min = min; 
		this.max = max;
		this.count = count;
	}
	
}
