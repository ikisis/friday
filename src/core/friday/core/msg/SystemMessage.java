package friday.core.msg;

import java.io.Serializable;

import friday.core.msg.Logger.LogLevel;

public class SystemMessage implements Serializable{

	private static final long serialVersionUID = -358458338036201138L;
	
	public final LogLevel logLevel;
	
	public final long systemTime;
	
	public final String msg;
	
	public SystemMessage(LogLevel logLevel, long systemTime, String msg) {
		this.logLevel = logLevel;
		this.systemTime = systemTime;
		this.msg = msg;
		
	}

}
