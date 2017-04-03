package friday.core;

import org.junit.Test;

public class TTT {
	
	@Test
	public void test() {
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
			}
		});
		
		t.start();
		
		t.interrupt();
	}

}
