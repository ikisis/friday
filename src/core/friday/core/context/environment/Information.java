package friday.core.context.environment;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.lang.management.ManagementFactory;
import java.util.Properties;

import friday.core.context.Central;
import friday.core.context.InitConstants;
import friday.core.util.QuietlyUtils;
import static friday.core.context.InitConstants.*;
public class Information {
	
	private String agentId;
	
	private String pName;
	
	private String pid;
	
	private Properties properties = new Properties();
	
	public Information(){
		this.pid = ManagementFactory.getRuntimeMXBean().getName();
	}
	
	public void init(String filePath){
		InputStream is = null;
		
		
		try {
			
			if(filePath == null)throw new RuntimeException("License path is null");
			
			
			
			is = new FileInputStream(filePath + InitConstants.LICENSE_FILE_NAME);

			if(is!=null)properties.load(is);
			
			init(properties);
			
			String tracerMapConfigLocation = properties.getProperty(PROP_TRACER_MAP_CONFIG_LOCATION);
			
			if(tracerMapConfigLocation != null);
				Central.RULE.initTraceTargetMap( tracerMapConfigLocation );
			
		} catch (Throwable e) {
			
			throw new RuntimeException(e);
			
		} finally{
			QuietlyUtils.close(is);
		}
		
		
	}
	
	public void init(Properties prop){
		this.properties = prop;
		this.agentId = properties.getProperty(PROP_AGENT_ID_NAME);
		
		String cmdLine = System.getProperty("sun.java.command");
		StringBuilder sb = new StringBuilder();
		StringReader sr = new StringReader(cmdLine);
		int i = -1;
		try {
			while((i = sr.read()) != -1){
				char c = (char)i;
				
				if(c == ' ')break;
				
				sb.append(c);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		if(sb.length() != 0){
			
			String[] tokens = sb.toString().split("[.]");
			this.pName = tokens[tokens.length -1];
		}
				
	}
	
	public String getProperty(String key){
		return properties.getProperty(key);
	}
	
	public String getAgentId(){
				
		return this.agentId;
	}

	public String getPid() {

		return this.pid;
	}
	
	public String getProcessName(){
		return this.pName;
	}

}
