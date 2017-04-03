package friday.core.config;

import java.io.FileInputStream;
import java.util.TreeMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import friday.core.config.elements._Agent;
import friday.core.config.elements.rule._RecordingRule;
import friday.core.config.elements.rule._RecordingRuleType;
import friday.core.config.elements.tracer._Target;
import friday.core.config.elements.tracer.snap._CustomTarget;
import friday.core.config.elements.tracer.snap._SnapFieldTarget;
import friday.core.config.elements.tracer.snap._SnapParameterTarget;
import friday.core.config.elements.tracer.snap._SnapReturnVariableTarget;
import friday.core.config.elements.tracer.snap._SnapThisTarget;
import friday.core.context.Central;
import friday.core.context.environment.Instrumentation;
import friday.core.context.environment.RuleManager;
import friday.core.context.environment.RuleManager.RecordablePattern;
import friday.core.instrument.instrumentor.TracerCollectingEnabler.MethodTransformer;
import friday.core.instrument.instrumentor.trace.pattern.SnapFieldWithMethodReturn;
import friday.core.instrument.instrumentor.trace.pattern.SnapParameterWithMethodStart;
import friday.core.instrument.instrumentor.trace.pattern.SnapReturnVariable;
import friday.core.instrument.instrumentor.trace.pattern.SnapThisWithMethodReturn;
import friday.core.util.QuietlyUtils;


public class XmlFileInitializer implements friday.core.config.CentralConfigurer.Initializer, ConfConstants{
	
	_Agent agentConfig;
	
	public XmlFileInitializer() {
		
		String filePath = Central.INFO.getProperty(AGENT_CONFIG_XML_FILE_PATH);
		
		FileInputStream fis = null;
		
		try {
			
			fis = new FileInputStream(filePath);
			 
			JAXBContext context = JAXBContext.newInstance(_Agent.class);
			
			Unmarshaller m = context.createUnmarshaller();
			
			this.agentConfig = (_Agent)m.unmarshal(fis);
			 
		} catch (Throwable e) {
			
			throw new RuntimeException(e);
			
		}finally{
			
			QuietlyUtils.close(fis);
			
		}
		
	}
	



	@Override
	public void initRuleManager(RuleManager rule) {
		
		TreeMap<Integer, RecordablePattern> map = new TreeMap<Integer, RecordablePattern>();
		
		for(_RecordingRule r : this.agentConfig.getRules().getRecording().getClassNameRules().getRules())
			map.put(r.getOrder(), new RecordablePattern(r.getType()==_RecordingRuleType.INCLUDE, r.getRegex()));
		
		for(Integer i : map.keySet()){
			rule.getClassRecordablePattern().add(map.get(i));
		}
		
		map.clear();
				
		for(_RecordingRule r : this.agentConfig.getRules().getRecording().getMethodNameRules().getRules())
			map.put(r.getOrder(), new RecordablePattern(r.getType()==_RecordingRuleType.INCLUDE, r.getRegex()));
		
		for(Integer i : map.keySet()){
			rule.getMethodRecordablePattern().add(map.get(i));
		}
		
	}

	@Override
	public void initInstrumentation(Instrumentation inst) {
		for(_Target t : this.agentConfig.getTracer().getInstrument().getTargets().getTargets()){
			String targetClassDesc = t.getClassName();
			
			for(_SnapThisTarget st : t.getSnapThisTargets().getSnapThisTargets()){
				
				inst.addMethodTransformer(targetClassDesc, st.getMethodDesc(), new SnapThisWithMethodReturn());
			}
			
			for(_SnapReturnVariableTarget st : t.getSnapReturnVariableTargets().getSnapReturnVariableTargets()){
				
				inst.addMethodTransformer(targetClassDesc, st.getMethodDesc(), new SnapReturnVariable());
			}
			
			for(_SnapFieldTarget st : t.getSnapFieldTargets().getSnapFieldTargets()){
				String[] fieldNames = new String[st.getFieldNames().size()];
				st.getFieldNames().toArray(fieldNames);
				inst.addMethodTransformer(targetClassDesc, st.getMethodDesc(), new SnapFieldWithMethodReturn(fieldNames));

			}
			
			for(_SnapParameterTarget st : t.getSnapParameterTargets().getSnapParameterTargets()){
				Integer[] indexes = new Integer[st.getParameterIndexes().size()];
				st.getParameterIndexes().toArray();
				inst.addMethodTransformer(targetClassDesc, st.getMethodDesc(), new SnapParameterWithMethodStart(indexes));
			}
			
			for(_CustomTarget ct : t.getCustomTargets().getCustomTargets()){
				
				
				try {
					Class<?> clazz = Thread.currentThread().getContextClassLoader().loadClass(ct.getClassName());
					inst.addMethodTransformer(targetClassDesc, ct.getMethodDesc(), (MethodTransformer) clazz.newInstance());
				} catch (Throwable e) {
					throw new RuntimeException(e);
				} 
			}
			
		}
	}
}
