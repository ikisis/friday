package friday.core.config.serializable;

import friday.core.instrument.instrumentor.TracerCollectingEnabler.MethodTransformer;
import friday.core.instrument.instrumentor.trace.pattern.SnapThisWithMethodReturn;

public class SnapThisTracerTargetRule extends AbstractTracerTargetRule{

	private static final long serialVersionUID = -1260844726303600748L;

	public SnapThisTracerTargetRule(String targetClassNmae, String methodDesc) {
		super(targetClassNmae, SnapType.THIS, methodDesc);
	}

	@Override
	public
	MethodTransformer newMethodTransformer() {
		return new SnapThisWithMethodReturn();
	}
	
}
