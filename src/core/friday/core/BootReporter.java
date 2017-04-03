package friday.core;

import friday.core.context.Central;
import friday.core.msg.Logger;

public class BootReporter {

	public static void sysproperties(){
		
		Logger.INFO("AgentId : " + Central.INFO.getAgentId());
		Logger.INFO("Process Name : " + Central.INFO.getProcessName());
		Logger.INFO("Process ID : " + Central.INFO.getPid());
		Logger.INFO("OS Architecture : " + System.getProperty("os.arch"));
		Logger.INFO("OS Name : " + System.getProperty("os.name"));
		
		for(Object key : System.getProperties().keySet()){
			Logger.INFO(key.toString() + " = " + System.getProperty((String)key));
		}		
	}
	
}
