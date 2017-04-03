package friday.core.communication;

public class Hello extends Consumable{
	
	public static enum Type{
		MASTER,
		AGENT
	}

	private static final long serialVersionUID = 6863796092371252379L;
	
	public final String agentId;
	
	public final String pid;
	
	public final String pName;
	
	public final long startTime;
	
	public final Type type;
	
	public Hello(String agentId, String pid, String pName, long startTime, Type type){
		this.agentId = agentId;
		this.pid = pid;
		this.pName = pName;
		this.startTime = startTime;
		this.type = type;
	}

}
