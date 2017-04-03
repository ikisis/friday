package friday.core.config.serializable;

import friday.core.instrument.instrumentor.TracerCollectingEnabler.MethodTransformer;
import friday.core.instrument.instrumentor.trace.pattern.SnapFieldWithMethodReturn;

public class SnapFieldTracerTargetRule extends AbstractTracerTargetRule{

	private static final long serialVersionUID = 5141026444496679514L;
	
	private String[] fieldNames;

	public SnapFieldTracerTargetRule(String targetClassNmae, String methodDesc, String[] fieldNames) {
		super(targetClassNmae, SnapType.FIELD, methodDesc);
		this.fieldNames = fieldNames;
	}

	public String[] getFieldNames() {
		return fieldNames;
	}

	public void setFieldNames(String[] fieldNames) {
		this.fieldNames = fieldNames;
	}

	@Override
	public
	MethodTransformer newMethodTransformer() {
		return new SnapFieldWithMethodReturn(fieldNames);
	}

}
