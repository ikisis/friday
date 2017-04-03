package friday.core.monitor;

import java.io.Serializable;

public class MethodIdentifier implements Serializable{

	private static final long serialVersionUID = 2941312228005608091L;

	public final String key;
	
	public final String signature;
	
	public MethodIdentifier(String key, String signature){
		this.key = key;
		this.signature = signature;
	}
	
}
