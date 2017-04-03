package friday.core.instrument;

import friday.core.asm.ClassVisitor;

import friday.core.instrument.instrumentor.ChoSungyoun;

public class InstrumentorFactory {
	
	public static Instrumentor getInstrumentor(){
				
		return new ChoSungyoun();
	}
	
	public static interface InnerFactory{
		public abstract ClassVisitor getClassVisitor(ClassVisitor cv);
	}
	
}
