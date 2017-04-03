package friday.core.context.environment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import friday.core.instrument.InstrumentorFactory.InnerFactory;
import friday.core.instrument.instrumentor.TracerCollectingEnabler.MethodTransformer;

public class Instrumentation {
	
	final ArrayList<InnerFactory> InstrumentorInnerFactories = new ArrayList<InnerFactory>();
	
	final Map<String, Map<String, MethodTransformer>> methodTransformerMap = new HashMap<String, Map<String,MethodTransformer>>();
	
	public void addInnerFactory(InnerFactory factory){
		this.InstrumentorInnerFactories.add(factory);
	}

	public ArrayList<InnerFactory> getInstrumentorInnerFactories() {
		return InstrumentorInnerFactories;
	}
	
	public Map<String, MethodTransformer> getMethodTransformerMap(String className){
		return this.methodTransformerMap.get(className);	
	}
	
	public void addMethodTransformer(String className, String signature, MethodTransformer methodTransformer){
		
		Map<String, MethodTransformer> map = this.methodTransformerMap.get(className);
		
		if(map == null){
			map = new HashMap<String, MethodTransformer>();
			this.methodTransformerMap.put(className, map);
		}
		
		map.put(signature, methodTransformer);
		
	}

}
