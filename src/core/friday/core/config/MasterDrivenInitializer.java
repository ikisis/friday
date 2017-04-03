package friday.core.config;

import friday.core.config.CentralConfigurer.Initializer;
import friday.core.config.serializable.AbstractTracerTargetRule;
import friday.core.config.serializable.AgentConfig;
import friday.core.config.serializable.RecordingRule;
import friday.core.config.serializable.TracerKeyRule;
import friday.core.context.Central;
import friday.core.context.environment.Instrumentation;
import friday.core.context.environment.RuleManager;
import friday.core.context.environment.RuleManager.RecordablePattern;


import static friday.core.context.environment.RuleManager.*;

import friday.core.communication.AgentCommunicator;
import friday.core.communication.contents.AgentConfigGettable;

public class MasterDrivenInitializer implements Initializer{
	
	private AgentConfig agentConfig;
	
	public MasterDrivenInitializer() {
		
		AgentConfigGettable returnable = new AgentConfigGettable(Central.INFO.getAgentId());
		
		AgentCommunicator.getInstance().CHAT.send(returnable);
		
		this.agentConfig = returnable.pollReturned();
		
	}
	

	@Override
	public void initRuleManager(RuleManager rule) {
				
		for( RecordingRule r : agentConfig.getRecordingRule()){
			
			rule.getClassRecordablePattern().add(new RecordablePattern(r.getType() == RecordingRule.Type.INCLUDE, r.getClassLikeKey()));
		}
		
		for(TracerKeyRule r : agentConfig.getTracerKeyRule()){			
			
			rule.parse(
					r.getTargetClassName() + openToken + r.getName()  + closeToken, 
					r.getTracerKeyName() 
					);
			
		}
	}

	@Override
	public void initInstrumentation(Instrumentation inst) {
		
		for(AbstractTracerTargetRule r : agentConfig.getTracerTargetRule()){
			
			inst.addMethodTransformer(r.getTargetClassName(), r.getMethodDesc(), r.newMethodTransformer());

		}
	}

}
