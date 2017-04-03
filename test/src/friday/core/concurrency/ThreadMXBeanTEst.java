package friday.core.concurrency;

import static org.junit.Assert.*;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.ArrayList;

import org.junit.Test;

import friday.core.util.StopWatch;

public class ThreadMXBeanTEst {

	@Test
	public void test() {
		
		ThreadMXBean bean = ManagementFactory.getThreadMXBean();
		
		System.out.println(bean.getClass().getName());
		
		Thread t = Thread.currentThread();
		
		
		StopWatch.start();
		
		
		ArrayList<Long> repo = new ArrayList<Long>();
		
		long cpuTime = 0;
		
		for(int i = 0; i < 10000; i++){
			
			cpuTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
			
			//cpuTime = ManagementFactory.getThreadMXBean().getThreadCpuTime(Thread.currentThread().getId());
			
			//cpuTime = bean.getCurrentThreadCpuTime();


			repo.add(cpuTime);
			
		}
		
		System.out.println(StopWatch.end());
		
	}

}
