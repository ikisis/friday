package friday.core.config.serializable;

import java.io.Serializable;

public class TracerKeyRule implements Serializable{

	private static final long serialVersionUID = 4366697554507420789L;

	private String targetClassName;
		
	private String name;
	
	private String tracerKeyName;
	
	public TracerKeyRule(String targetClassName, String name, String tracerKeyName){
		this.targetClassName = targetClassName;
		this.name = name;
		this.tracerKeyName = tracerKeyName;
	}

	public String getTargetClassName() {
		return targetClassName;
	}

	public void setTargetClassName(String targetClassName) {
		this.targetClassName = targetClassName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTracerKeyName() {
		return tracerKeyName;
	}

	public void setTracerKeyName(String tracerKeyName) {
		this.tracerKeyName = tracerKeyName;
	}
	
	
	
}
