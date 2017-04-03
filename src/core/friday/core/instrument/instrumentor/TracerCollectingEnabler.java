package friday.core.instrument.instrumentor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import friday.core.asm.FieldVisitor;
import friday.core.asm.Opcodes;
import friday.core.asm.tree.ClassNode;
import friday.core.asm.tree.InsnList;
import friday.core.asm.tree.MethodNode;
import friday.core.asm.tree.ParameterNode;

public class TracerCollectingEnabler extends ClassNode{
	
	private final Map<String, MethodTransformer> methodTransformerMap;
	
	private Map<String, String> fieldTypeMap = new HashMap<String, String>();
	
	public TracerCollectingEnabler(Map<String, MethodTransformer> methodTransformerMap) {
		
		super(Opcodes.ASM5);
		this.methodTransformerMap = methodTransformerMap;
		
	}
	
    @Override
    public FieldVisitor visitField(final int access, final String name, final String desc, final String signature, final Object value) {
    	
    	this.fieldTypeMap.put(name, desc);
    	
    	return super.visitField(access, name, desc, signature, value);
    	
    }
    
    @Override
    public void visitEnd() {
    	
    	for(MethodNode method : this.methods){
    		
    		MethodTransformer transformer = this.methodTransformerMap.get(method.name + method.desc);
    		
    		if(transformer != null){
    			
    			InsnList il = transformer.reform(new TargetMethodInfo(method));
    			
    			method.instructions = il==null || il.size() == 0?method.instructions:il;
    		}
    		
    	}
    	
    	super.visitEnd();
    	
    }
    
    public class TargetMethodInfo{
    	
    	private final Map<String, String> fieldTypeMap = Collections.unmodifiableMap(TracerCollectingEnabler.this.fieldTypeMap);

    	private final String className = TracerCollectingEnabler.this.name;
    	
    	private final int access;
    	
    	private final String methodName;
    	
    	private final String desc;
    	
    	private final InsnList instructions;
    	
    	private TargetMethodInfo(MethodNode methodNode){
    		
    		List<String> list = new ArrayList<String>();
    		
    		if(methodNode.parameters != null)
	    		for(ParameterNode parameterNode : methodNode.parameters){
	    			list.add(parameterNode.name);
	    		}
    		
    		this.access = methodNode.access;
    		
    		this.methodName = methodNode.name;
    		
    		this.desc = methodNode.desc;
    		
    		this.instructions = methodNode.instructions;
    	}

		public Map<String, String> getFieldTypeMap() {
			return fieldTypeMap;
		}

		public String getClassName() {
			return className;
		}

		public int getAccess() {
			return access;
		}

		public String getMethodName() {
			return methodName;
		}

		public String getDesc() {
			return desc;
		}

		public InsnList getInstructions() {
			return instructions;
		}
    	
    }
    
    public static interface MethodTransformer extends Opcodes, RecConstants, ASMConstants{
    	
    	InsnList reform(TargetMethodInfo target);
    	
    }
	

}
