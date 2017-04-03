package friday.core.util;

import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.PrivilegedAction;

public abstract class ThreadUtils {

	public static Thread newThread( Runnable runnable, String threadName, boolean isDaemon)
	{
		SecurityManager security= System.getSecurityManager();
		ThreadGroup group= security!= null ? security.getThreadGroup() : Thread.currentThread().getThreadGroup();
		Thread th= new Thread( group, runnable, threadName, 0);
		if( isDaemon)
			th.setDaemon( true);
		else
			th.setDaemon( false);
		if( th.getPriority()!= Thread.NORM_PRIORITY)
			th.setPriority( Thread.NORM_PRIORITY);
		return th;
	}
	
	public static Thread newPrivilegedThread( final Runnable runnable, String threadName, boolean isDaemon)
	{
		final ClassLoader classLoader= Thread.currentThread().getContextClassLoader();
		final AccessControlContext acc= AccessController.getContext();
		return newThread( new Runnable(){
			public void run()
			{
				AccessController.doPrivileged( new PrivilegedAction<Object>(){
					public Object run()
					{
						Thread.currentThread().setContextClassLoader( classLoader);
						runnable.run();
						return null;
					}
				}, acc);
			}
		}, threadName, isDaemon);
	}	
}
