package friday.core.cputime;

import static org.junit.Assert.*;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

import org.junit.Test;

public class ThreadCpuTimeTest {

	@Test
	public void test() throws InterruptedException {
		
		ThreadMXBean bean = ManagementFactory.getThreadMXBean();
		
		System.out.println(bean.isCurrentThreadCpuTimeSupported());
		
		System.out.println(bean.isThreadContentionMonitoringEnabled());
		
		long tID = Thread.currentThread().getId();
		
		for(;;){
			
//			System.out.println(bean.getCurrentThreadCpuTime() + ":" + bean.getCurrentThreadUserTime());
			
			long cpuTime = bean.getCurrentThreadCpuTime();
			
			long userCpuTime = bean.getCurrentThreadUserTime();
			
			System.out.println(cpuTime + ":" + userCpuTime + ":" + (cpuTime - userCpuTime));

			Thread.sleep(500);
			
		}
		
	}

}
