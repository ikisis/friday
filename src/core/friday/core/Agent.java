package friday.core;

import java.lang.instrument.Instrumentation;
import java.net.MalformedURLException;

import friday.core.communication.AgentCommunicator;
import friday.core.config.CentralConfigurer;
import friday.core.context.Central;
import friday.core.context.environment.ClassPathAppender;
import friday.core.instrument.TraceableProfilingEnabler;
import friday.core.monitor.AgentMonitor;
import friday.core.util.PathUtils;

public class Agent {
	
	public static void premain(final String arg, final Instrumentation inst){
		
		instrument(PathUtils.getProfilerHomePath(), inst);
				
	}
	
	public static void instrument(final String filePath, final Instrumentation inst){
		
		try{
		
			ClassPathAppender.run(filePath);

			Central.INFO.init(filePath);
			
			AgentCommunicator.on();
			
			CentralConfigurer.start();
			
			AgentMonitor.on();
			
			inst.addTransformer(new TraceableProfilingEnabler(), true);
			
			Reloader.reloadAll(inst);
			
			BootReporter.sysproperties();
		
		}catch(Throwable th){
			
			System.out.println(">>friday Agent loading failed >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			
			th.printStackTrace(System.out);
			
			System.out.println("<<friday Agent loading failed <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");

			return;
			
		}
		
	}
	
	public static void agentmain(String agentArgs, Instrumentation inst){
						
		instrument(PathUtils.getProfilerHomePath(), inst);


	}
	
	public static void main(String[] args) throws MalformedURLException {
			
		AgentInstaller.attach();
		
	}

}
