package friday.core.monitor;

import java.io.Serializable;

public class Tracer implements Serializable{

	private static final long serialVersionUID = 3129205375751699522L;
	
	final public String threadEventId;
	
	final public String key;
	
	final public String value;
	
	public Tracer(String threadEventId, String key, String value){
		this.threadEventId = threadEventId;
		this.key = key;
		this.value = value;
	}

}
