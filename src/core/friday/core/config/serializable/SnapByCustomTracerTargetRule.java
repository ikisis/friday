package friday.core.config.serializable;

import friday.core.instrument.instrumentor.TracerCollectingEnabler.MethodTransformer;

public class SnapByCustomTracerTargetRule extends AbstractTracerTargetRule{

	private static final long serialVersionUID = 7767897148129429158L;

	private String customeHandlerClass;
	
	public SnapByCustomTracerTargetRule(String targetClassNmae, String methodDesc, String customeHandlerClass) {
		super(targetClassNmae, SnapType.BY_CUSTOM, methodDesc);
		setCustomeHandlerClass(customeHandlerClass);
	}

	public String getCustomeHandlerClass() {
		return customeHandlerClass;
	}

	public void setCustomeHandlerClass(String customeHandlerClass) {
		this.customeHandlerClass = customeHandlerClass;
	}

	@Override
	public
	MethodTransformer newMethodTransformer() {
		
		try {
			Class<?> clazz = Thread.currentThread().getContextClassLoader().loadClass(customeHandlerClass);
			
			return (MethodTransformer)clazz.newInstance();
			
		} catch (Throwable e) {
			throw new RuntimeException(e);
		} 
		
	}
	
	

}
