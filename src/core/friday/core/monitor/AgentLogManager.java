package friday.core.monitor;

import java.util.ArrayList;

import friday.core.msg.SystemMessage;

public class AgentLogManager {
	
	private final ArrayList<SystemMessage> logs = new ArrayList<SystemMessage>();
	
	public void append(SystemMessage sysmsg){
		
		synchronized (logs) {
			logs.add(sysmsg);
		}
		
	}
	
	public ArrayList<SystemMessage> takeLogs(){
		
		ArrayList<SystemMessage> ret = new ArrayList<SystemMessage>();
		
		synchronized (logs) {
			
			ret.addAll(logs);
			logs.clear();
		}
		
		return ret;
	}

}
