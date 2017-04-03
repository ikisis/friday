package friday.core.instrument;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import friday.core.context.Central;
import friday.core.msg.Logger;

public class ProfilingEnabler implements ClassFileTransformer{

	@Override
	public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classBytes) throws IllegalClassFormatException {
		
		if(!Central.RULE.isRecordable(className))return classBytes;
						
		Instrumentor instrumentor = InstrumentorFactory.getInstrumentor();
		
		try{ return instrumentor.transform(classBytes); }catch(Throwable ignored){
			
			Logger.ERROR("fail to instrument : " + className);
			
		}
		
		return classBytes;
		
	}
	

	
}
