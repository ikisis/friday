package friday.core.cmd;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.nio.charset.Charset;

import org.junit.Test;

public class RuntimeCmdTest {

	@Test
	public void test() throws IOException {
		
		
		String cmdPrepix = "tasklist /v /fi \"pid eq ";
		
		String cmdPostpix = "\" /fo list";
		
		System.out.println(Charset.defaultCharset());
		System.out.println(System.getProperty("file.encoding"));
		System.out.println(System.getProperty("os.name"));
		String temp = ManagementFactory.getRuntimeMXBean().getName();
		
		String lPid = temp.substring(0, temp.indexOf("@"));
		
		System.out.println(lPid);
		
		
		Process p = Runtime.getRuntime().exec(cmdPrepix + lPid + cmdPostpix);
		
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(),"euc-kr"));
		
		StringBuilder sb = new StringBuilder();
		
		String line = null;
		
		while((line = br.readLine())!= null){
			sb.append(line +"\r");
		}
		
		System.out.println(sb.toString());
		
	}

}
