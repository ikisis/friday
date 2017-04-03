package friday.core.util;

public abstract class StopWatch {
	
	private static ThreadLocal<StopWatchContext> tl = new ThreadLocal<StopWatch.StopWatchContext>(){
		@Override
		public StopWatchContext initialValue(){
			return new StopWatchContext();
		}
	};
	
	public static void start(){
		tl.remove();
		
		tl.get().startTime = System.nanoTime();
		
	}
	
	public static long end(){
		return System.nanoTime() - tl.get().startTime;
	}
	
	public static void reset(){
		tl.remove();
	}
	
	
	private static class StopWatchContext{
		public long startTime;
	}

}
