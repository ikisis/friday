package friday.core.communication;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;

import friday.core.communication.Returnable.Returned;
import friday.core.util.QuietlyUtils;

public class ChatterBox implements Closeable{
	
	final private LinkedBlockingQueue<Consumable> queue = new LinkedBlockingQueue<Consumable>();
	
	final private ObjectInputStream receiver;
	
	final private ObjectOutputStream sender;
	
	final private Socket soc;
	
	ChatterBox(Socket soc){
		
		try {
			
			this.receiver = new ObjectInputStream(soc.getInputStream());
			
			this.sender = new ObjectOutputStream(soc.getOutputStream());
			
			this.soc = soc;
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		

		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				while(true){
					
					Consumable msg = null;
					
					try {	msg = queue.take();	} catch (InterruptedException e1) {
						
						Thread.currentThread().interrupt();
						
					}
					
					if(msg!=null){
						synchronized (sender) {
							
							try {  
																
								sender.writeObject(msg);
																
							} catch (IOException e) {
								
								throw new RuntimeException(e);
								
							}
						}	
					}
				}
			}
			
		}).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				while(true){
					receive();
				}
				
			}
		}).start();
		
		
	}
	
	public void send(final Consumable msg){
		
		queue.offer(msg);
		
	}
	
	@SuppressWarnings("rawtypes")
	public boolean receive(){
		
		try {
			
			final Consumable msg = (Consumable) this.receiver.readObject();
			
			msg.consume();
			
			if(msg instanceof Returnable){
				
				Returned returned = ((Returnable<?>) msg).createReturned();
				
				send(returned);
				
			}
			
		} catch (Throwable th) {
			throw new RuntimeException(th);
		}
		
		return true;
		
	}
	
	@Override
	public void close(){
		
		QuietlyUtils.close(soc, sender, receiver);
		
	}
	
}
