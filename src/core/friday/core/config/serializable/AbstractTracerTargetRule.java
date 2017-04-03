package friday.core.config.serializable;

import java.io.Serializable;

import friday.core.instrument.instrumentor.TracerCollectingEnabler.MethodTransformer;

public abstract class AbstractTracerTargetRule implements Serializable{

	private static final long serialVersionUID = 8473493879231951794L;
	
	public static enum SnapType{
		THIS,
		RETURN,
		PARAM,
		FIELD,
		BY_CUSTOM
	}
	
	public AbstractTracerTargetRule(String targetClassNmae, SnapType snapType, String methodDesc){
		this.targetClassName = targetClassNmae;
		this.snapType = snapType;
		this.methodDesc = methodDesc;
	}
	
	private String targetClassName;
	
	private SnapType snapType;
	
	private String methodDesc;
	
	abstract public MethodTransformer newMethodTransformer();

	public String getTargetClassName() {
		return targetClassName;
	}

	public void setTargetClassName(String targetClassName) {
		this.targetClassName = targetClassName;
	}

	public SnapType getSnapType() {
		return snapType;
	}

	public void setSnapType(SnapType snapType) {
		this.snapType = snapType;
	}

	public String getMethodDesc() {
		return methodDesc;
	}

	public void setMethodDesc(String methodDesc) {
		this.methodDesc = methodDesc;
	}

}
