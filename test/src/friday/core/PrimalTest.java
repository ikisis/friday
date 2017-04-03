package friday.core;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import friday.core.event.IEvent;
import friday.core.event.thread.IThreadEvent;

public class PrimalTest {

	@Test
	public void test() throws UnknownHostException, IOException {
		
		System.out.println(Long.MAX_VALUE);
		
		System.out.println("0 to 9: " + (int)'0' + "~"+ (int)'9');
		
		System.out.println("A to Z: " + (int)'A' + "~"+ (int)'Z');
		
		System.out.println("a to z: " + (int)'a' + "~"+ (int)'z');
		
		

		
		String digits = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabctdeghijklmnopqrstuvwxyz";
		
		System.out.println(digits.length());
		
		char[] digitsChar = digits.toCharArray();
		
		System.out.println(digitsChar);
		
		digits.indexOf('1');
		


		
//		int i = 0;
//		
//		System.out.println(++i == 0);
//		
//		OutputStream os = null;
//		
//		
//		IEvent e = null;
//		
//		String host = "localhost";
//		int port = 0;
////		Socket s = new Socket(host, port);
////		
////		ServerSocket ss = new ServerSocket(port);
//		
//		
//		try {
//			
//			Thread.sleep(1000000);
//			
//		} catch (InterruptedException e1) {
//			Thread.currentThread().interrupt();
//		}
		
	}

}
