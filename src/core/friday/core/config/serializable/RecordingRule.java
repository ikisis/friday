package friday.core.config.serializable;

import java.io.Serializable;

public class RecordingRule implements Serializable{
	
	public static enum Type{
		INCLUDE,
		EXCLUDE
	}

	private static final long serialVersionUID = 13855232193673502L;
	
	private Type type;
	
	private String classLikeKey;
	
	private String methodLikeKey;
	
	public RecordingRule(Type type, String classLikeKey, String methodLikeKey){
		this.type = type;
		this.classLikeKey = classLikeKey;
		this.methodLikeKey = methodLikeKey;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getClassLikeKey() {
		return classLikeKey;
	}

	public void setClassLikeKey(String classLikeKey) {
		this.classLikeKey = classLikeKey;
	}

	public String getMethodLikeKey() {
		return methodLikeKey;
	}

	public void setMethodLikeKey(String methodLikeKey) {
		this.methodLikeKey = methodLikeKey;
	}
	
	

}
