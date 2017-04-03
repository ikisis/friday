package friday.core.communication;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.Test;

public class ClientTest {

	@Test
	public void test() throws UnknownHostException, IOException, InterruptedException {
		
		Socket soc = new Socket("192.168.20.29", ServerTest.SVR_PORT);
		
//		ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(soc.getOutputStream()));
//		
//		
//		final ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(soc.getInputStream()));
		
		ObjectOutputStream oos = new ObjectOutputStream(soc.getOutputStream());
		
		
		final ObjectInputStream ois = new ObjectInputStream(soc.getInputStream());
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				Consumable consumable = null;
				
				
				try {
					while((consumable = (Consumable) ois.readObject())!=null){
						
						System.out.println(consumable.toString());
						
					}
				} catch (Throwable e) {
					throw new RuntimeException(e);
				} 
				
			}
		}).start();
		
		int i = 0;
		
		while(true){
		
			long startTime = System.currentTimeMillis();
			
			oos.writeObject(new TestConsumable("hi(" + i++ + ")"));
			
			System.out.println("elapsed Time : " + (System.currentTimeMillis() - startTime));
			
			oos.flush();
			
			Thread.sleep(1000);
		}
		
	}

}

class TestConsumable extends Consumable{

	private static final long serialVersionUID = -6287894131907821763L;
	
	public final String contents;
	
	public TestConsumable(String contents) {
		this.contents = contents;
	}
	
	@Override
	public String toString(){
		return "TC : " + contents;
	}
	
}
