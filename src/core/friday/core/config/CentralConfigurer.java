package friday.core.config;

import friday.core.context.Central;
import friday.core.context.environment.Instrumentation;
import friday.core.context.environment.RuleManager;
import static friday.core.context.InitConstants.*;

public class CentralConfigurer {
		
	
	public static void start(){
		
		Initializer initializer = null;
		
		String className = Central.INFO.getProperty(PROP_AGENT_INIT_CLASS_NAME);
		
		try {
			
			Class<?> clazz = Thread.currentThread().getContextClassLoader().loadClass(className);
			
			initializer = (Initializer) clazz.newInstance();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(className + " is not found");
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		
		initializer.initRuleManager(Central.RULE);
		initializer.initInstrumentation(Central.INST);
		
	}
	

	abstract public static interface Initializer{
		public void initRuleManager(RuleManager rule);
		public void initInstrumentation(Instrumentation inst);
	}

}
