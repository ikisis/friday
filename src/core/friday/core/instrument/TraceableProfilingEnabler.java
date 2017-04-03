package friday.core.instrument;

import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.Map;

import friday.core.asm.ClassReader;
import friday.core.asm.ClassWriter;
import friday.core.asm.tree.ClassNode;

import friday.core.context.Central;
import friday.core.instrument.instrumentor.TracerCollectingEnabler;
import friday.core.instrument.instrumentor.TracerCollectingEnabler.MethodTransformer;

public class TraceableProfilingEnabler extends ProfilingEnabler{

	@Override
	public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classBytes) throws IllegalClassFormatException {
		try{
			
			Map<String, MethodTransformer> methodTransformerMap = Central.INST.getMethodTransformerMap(className);
			
			if(methodTransformerMap != null){
				
				ClassNode classNode = new TracerCollectingEnabler(methodTransformerMap);
				
				new ClassReader(classBytes).accept(classNode, ClassReader.EXPAND_FRAMES);
				
				ClassWriter cw = new ClassWriterNoCL(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
				
				classNode.accept(cw);
				
				classBytes = cw.toByteArray();
	
			}
			
			return super.transform(loader, className, classBeingRedefined, protectionDomain, classBytes);
			
		}catch(Throwable th){
			th.printStackTrace(System.out);
		}
		
		return classBytes;
	}
}
