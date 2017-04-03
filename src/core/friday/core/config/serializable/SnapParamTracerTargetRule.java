package friday.core.config.serializable;

import friday.core.instrument.instrumentor.TracerCollectingEnabler.MethodTransformer;
import friday.core.instrument.instrumentor.trace.pattern.SnapParameterWithMethodStart;

public class SnapParamTracerTargetRule extends AbstractTracerTargetRule{

	private static final long serialVersionUID = -1236865850184132533L;
	
	private Integer[] indexes;

	public SnapParamTracerTargetRule(String targetClassNmae, String methodDesc, Integer[] indexes) {
		super(targetClassNmae, SnapType.PARAM, methodDesc);
		this.indexes = indexes;
	}

	public Integer[] getIndexes() {
		return indexes;
	}

	public void setIndexes(Integer[] indexes) {
		this.indexes = indexes;
	}

	@Override
	public MethodTransformer newMethodTransformer() {
		return new SnapParameterWithMethodStart(indexes);
	}

}
