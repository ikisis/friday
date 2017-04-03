package friday.core.communication;

import java.net.Socket;

import friday.core.communication.Hello.Type;
import friday.core.communication.contents.ThreadEventSequenceGettable;
import friday.core.communication.contents.ThreadEventSequenceGettableConsumer;
import friday.core.context.Central;
import static friday.core.context.InitConstants.*;


public class AgentCommunicator {
	
	private static volatile AgentCommunicator ins;
	
	public static AgentCommunicator getInstance(){
		
		if(ins == null)synchronized (AgentCommunicator.class) {if(ins == null)ins = new AgentCommunicator();}
		
		return ins;
		
	}
	
	public static void on(){
		getInstance();
	}
	
	public final ChatterBox CHAT;
	
	private AgentCommunicator(){
		
		Consumable.addConsumer(ThreadEventSequenceGettable.class, new ThreadEventSequenceGettableConsumer());
		
		String host = Central.INFO.getProperty(MASTER_HOST_NAME);
		
		String pStr = Central.INFO.getProperty(MASTER_PORT);
		
		if(host == null || pStr == null)throw new RuntimeException("can't read master access information");
		
		int port = Integer.parseInt(pStr);
		
		CHAT = createChatterBox(host, port);
				
		CHAT.send(new Hello(Central.INFO.getAgentId(), Central.INFO.getPid(), Central.INFO.getProcessName(), System.currentTimeMillis(), Type.AGENT));
		
	}
	
	
	private ChatterBox createChatterBox(String host, int port){
		
		ChatterBox chatterBox = null;
		
		try {
			
			Socket soc = new Socket(host, port);
			
			chatterBox = new ChatterBox(soc);
			
		} catch (Throwable e) {
			
			throw new RuntimeException(e);
			
		}
		
		return chatterBox;
		
	}
	
	

}
