package friday.core.instrument.instrumentor.trace.pattern;

import java.util.HashSet;
import java.util.Set;

import friday.core.asm.Opcodes;
import friday.core.asm.Type;
import friday.core.asm.tree.AbstractInsnNode;
import friday.core.asm.tree.InsnList;
import friday.core.asm.tree.MethodInsnNode;
import friday.core.asm.tree.VarInsnNode;

import friday.core.instrument.instrumentor.TracerCollectingEnabler.MethodTransformer;
import friday.core.instrument.instrumentor.TracerCollectingEnabler.TargetMethodInfo;

public class SnapParameterWithMethodStart implements MethodTransformer{
	
	private Set<Integer> parameterIndexes = new HashSet<Integer>();
	
	public SnapParameterWithMethodStart() {}
	
	public SnapParameterWithMethodStart(Integer[] parameterIndexs){
		if(parameterIndexs == null || parameterIndexs.length == 0)throw new IllegalArgumentException("fieldName is empty");
		for (Integer index : parameterIndexs) {
			addParemeterIndex(index);
		}
	}

	@Override
	public InsnList reform(TargetMethodInfo target) {
		
		InsnList replaced = new InsnList();

		inject(target, replaced);
        
		AbstractInsnNode insn = target.getInstructions().getFirst();
		
		while(insn!=null){
			
			replaced.add(insn);
			
			insn = insn.getNext();
			
		}
		
		return replaced;
	}
	
	private void inject(TargetMethodInfo target, InsnList replaced){
		
		Type[] args = Type.getArgumentTypes(target.getDesc());
		
		int currentLocal = (Opcodes.ACC_STATIC & target.getAccess()) == 0 ? 1 : 0;
        
        for (int i = 0; i < args.length; i++) {
        	        	
        	if(this.parameterIndexes.contains(i)){
        	
        		Integer load = DESC2LOAD.get(args[i].getDescriptor());
        		
        		load = load==null?ALOAD:load;
        		        		        		
        		replaced.add(new VarInsnNode(load, currentLocal));
        		
        		if(args[i].getDescriptor().length() == 1)
        			replaced.add(new MethodInsnNode(INVOKESTATIC, R_HANDLER_CLASS_NAME, R_HANDLER_METHOD_NAME_VARIABLE, "(" + args[i].getDescriptor() +")V", false));
        		else
        			replaced.add(new MethodInsnNode(INVOKESTATIC, R_HANDLER_CLASS_NAME, R_HANDLER_METHOD_NAME_VARIABLE, R_HANDLER_METHOD_DESC_VARIABLE, false));

        	}
        	
        	currentLocal += args[i].getSize();
        	
        }
	}

	public Set<Integer> getParameterIndexes() {
		return parameterIndexes;
	}

	public void addParemeterIndex(Integer index){
		this.parameterIndexes.add(index);
	}
}
