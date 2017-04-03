package friday.core.instrument;

import friday.core.asm.ClassReader;
import friday.core.asm.ClassVisitor;
import friday.core.asm.ClassWriter;
import friday.core.asm.Opcodes;

import friday.core.context.Central;
import friday.core.instrument.InstrumentorFactory.InnerFactory;

public abstract class Instrumentor implements Opcodes{
	
	
	public byte[] transform(byte[] classBytes) {
		
		ClassWriter cw = new ClassWriterNoCL(ClassWriter.COMPUTE_MAXS|ClassWriter.COMPUTE_FRAMES);
		
		//ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS|ClassWriter.COMPUTE_FRAMES);
		
		ClassReader cr = new ClassReader(classBytes);
		
		ClassVisitor cv = getClassVisitorStarter(cw);
		
		cv = wrapMore(cv);
		
		try{	cr.accept(cv, ClassReader.EXPAND_FRAMES);	}catch(Throwable th){
			
			System.out.println("[bpf]>>" + cr.getClassName() + " instrumentation failed");
			
			throw new RuntimeException(th);
			
		}
		return cw.toByteArray();
	}
	
	abstract protected ClassVisitor getClassVisitorStarter(ClassWriter cw);
	
	protected ClassVisitor wrapMore(ClassVisitor cv){
		
		for(InnerFactory factory : Central.INST.getInstrumentorInnerFactories()){
			cv = factory.getClassVisitor(cv);
		}
		
		return cv;
	}

}
