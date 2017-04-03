package f.r.i.rc;

import java.util.Map;

import org.apache.commons.ognl.OgnlException;

import friday.core.context.Central;
import friday.core.context.environment.RuleManager.ExpressionNode;
import friday.core.event.thread.LifeCycle;
import friday.core.event.thread.MethodEndEvent;
import friday.core.event.thread.MethodConstructorEvent;
import friday.core.event.thread.MethodStartEvent;
import friday.core.monitor.AgentMonitor;
import friday.core.monitor.Tracer;
import friday.core.msg.Logger;

public abstract class H {
	
	protected static final ThreadLocal<LifeCycle> tl = new ThreadLocal<LifeCycle>(){
		
		@Override protected LifeCycle initialValue(){return new LifeCycle();}
		
	};
	
	private static LifeCycle lifeCycle(){return tl.get();}

	public static void i(final String signature){
		
		LifeCycle lc = lifeCycle();
	
		try{
						
			lc.eventBuffer.append(new MethodConstructorEvent(
					signature, 
					System.currentTimeMillis(),
					lc.mBean.getCurrentThreadCpuTime()
					));

			
		}finally{
			
			lc.up();
			
		}
		
		lc.instantiated.increase();
		
	}
	
	public static void s(final String signature){
		
		LifeCycle lc = lifeCycle();
		
		try{
						
			lc.eventBuffer.append(new MethodStartEvent(
					signature, 
					System.currentTimeMillis(),
					lc.mBean.getCurrentThreadCpuTime()
					));

			
		}finally{
			
			lc.up();
			
		}
		
	}

	public static void r(){
		
		LifeCycle lc = lifeCycle();
		
		try{
				
			lc.eventBuffer.append(new MethodEndEvent(
					MethodEndEvent.END_RET_CHAR,
					System.currentTimeMillis(), 
					lc.mBean.getCurrentThreadCpuTime()
					));

			
		}finally{
			
			if(lc.down() == 0){
				
				tl.remove();
				
			}
			
		}
		
	}
	
	public static void e(){

		LifeCycle lc = lifeCycle();
		
		try{
									
			lc.eventBuffer.append(new MethodEndEvent(
					MethodEndEvent.END_EXCPN_CHAR,
					System.currentTimeMillis(), 
					lc.mBean.getCurrentThreadCpuTime()
					));
			
		}finally{
			
			if(lc.down() == 0){
				
				tl.remove();
				
			}
			
		}
		
	}
	
	/**
	 * method call touched line
	 * @param line
	 */
	public static void l(int line){
			
	}
	
	/**
	 * method call snaps variable
	 * @param target
	 */
	private static void t0(Object target){
				
		String typeName= target.getClass().getName();
		
		Map<ExpressionNode, String> exprMap= Central.RULE.getTracerMap().get( typeName);

		if( exprMap== null)
			return;
		
        for( Map.Entry<ExpressionNode, String> exprs: exprMap.entrySet()){
        	
            Object tval= null;
            
            try{ tval= exprs.getKey().getValue( target);	}catch( OgnlException e){
            		System.out.println(target.getClass().getName());
                    Logger.WARN( "Tracer value binding value obtain failed..." );
                    
            }
            
            if( tval== null)continue;
            
            String targetValue= tval.toString();
            
            Tracer tracer = new Tracer(lifeCycle().eventBuffer.ID, exprs.getValue(),  targetValue);
            
            AgentMonitor.getInstance().THREAD.addTracer(tracer);
                        
        }

	}
	
	public static void t(Object target){t0(target);}
	
	public static void t(byte target){t0(target);}
	
	public static void t(short target){t0(target);}

	public static void t(int target){t0(target);}

	public static void t(float target){t0(target);}

	public static void t(boolean target){t0(target);}

	public static void t(long target){t0(target);}

	public static void t(double target){t0(target);}

	public static void t(char target){t0(target);}

}
