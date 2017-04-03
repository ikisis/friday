package friday.core.communication;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.channels.SocketChannel;

import org.junit.Test;

public class SocketTest {

	@Test
	public void test() throws UnknownHostException, IOException {
		
		String host = null;
		int port = 0;
		Socket s = new Socket(host, port);
		
		SocketChannel ch = s.getChannel();
		
		
		
	}

}
