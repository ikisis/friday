package friday.core.communication;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Test;

public class ServerTest {
	
	public static final int SVR_PORT = 7805;

	@Test
	public void test() throws IOException {
		
		
		ServerSocket server = new ServerSocket(7805);
		
		//System.out.println(server.getInetAddress().getHostAddress());
		
		
		while(true){
			final Socket soc = server.accept();
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					
					
					try {
						
						
						ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(soc.getOutputStream()));
						
												
						ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(soc.getInputStream()));
				
						
						Consumable consumable = null;
						
						int i = 0;
						
						long startTime = System.currentTimeMillis();
						
						while((consumable = (Consumable) ois.readObject()) != null){
							
							long lapTime = System.currentTimeMillis() - startTime;
							
							System.out.println(lapTime + "  >>" + consumable.toString());
							
							if((i++ % 5) == 0){
								oos.writeObject(new TestConsumable("enough!!"));
							}
							
							startTime  = System.currentTimeMillis();
							
						}
						
						
					} catch(Throwable e) {
						throw new RuntimeException(e);
					}
					
					
				}
			}).start();
		}
		
		
	}

}

