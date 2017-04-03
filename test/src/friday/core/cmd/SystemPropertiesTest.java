package friday.core.cmd;

import static org.junit.Assert.*;

import java.util.Properties;

import org.junit.Test;

public class SystemPropertiesTest {

	@Test
	public void test() {
		
		System.out.println(System.getProperty("os.arch"));
		
		System.out.println(System.getProperty("os.name"));
		
		System.out.println("---------------------------------------");
		
		
		for(Object key : System.getProperties().keySet()){
			System.out.println(key.toString() + " = " + System.getProperty((String)key));
		}
		
		
		
	}

}
