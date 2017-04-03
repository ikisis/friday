package friday.core.communication.contents;

import friday.core.communication.Returnable;
import friday.core.config.serializable.AgentConfig;

public class AgentConfigGettable extends Returnable<AgentConfig>{

	private static final long serialVersionUID = -3393305990892566813L;

	public final String agentId;
	
	public AgentConfigGettable(String agentId) {
		this.agentId = agentId;
	}
	
}
