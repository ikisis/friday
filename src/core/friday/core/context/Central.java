package friday.core.context;

import friday.core.context.environment.Information;
import friday.core.context.environment.Instrumentation;
import friday.core.context.environment.RuleManager;

public class Central {
	
	public static final Information INFO = new Information();
		
	public static final RuleManager RULE = new RuleManager();
	
	public static final Instrumentation INST = new Instrumentation();
				
}
