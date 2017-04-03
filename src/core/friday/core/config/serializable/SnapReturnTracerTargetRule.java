package friday.core.config.serializable;

import friday.core.instrument.instrumentor.TracerCollectingEnabler.MethodTransformer;
import friday.core.instrument.instrumentor.trace.pattern.SnapReturnVariable;

public class SnapReturnTracerTargetRule extends AbstractTracerTargetRule{

	private static final long serialVersionUID = -2927453670741349299L;

	public SnapReturnTracerTargetRule(String targetClassNmae, String methodDesc) {
		super(targetClassNmae, SnapType.RETURN, methodDesc);
	}

	@Override
	public
	MethodTransformer newMethodTransformer() {
		return new SnapReturnVariable();
	}

}
