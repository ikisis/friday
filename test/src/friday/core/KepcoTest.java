package friday.core;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import friday.core.instrument.instrumentor.ChoSungyoun;

public class KepcoTest {

	public static void main(String[] args) throws IOException {
		
		
		ChoSungyoun cho = new ChoSungyoun();
				
		//bxm.batch.core.launch.support.CommandLineJobRunner
		
		InputStream is = ClassLoader.getSystemResourceAsStream("bxm/batch/core/launch/support/CommandLineJobRunner.class");
		
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		
		byte[] buff = new byte[1024];
		
		int len = -1;
		
		while((len = is.read(buff)) != -1){
			baos.write(buff, 0, len);
		}
		
		is.close();
		
		
		System.out.println(baos.size());
		
		cho.transform(baos.toByteArray());
		
	}
}
