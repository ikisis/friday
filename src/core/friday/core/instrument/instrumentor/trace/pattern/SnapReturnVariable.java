package friday.core.instrument.instrumentor.trace.pattern;

import friday.core.asm.MethodVisitor;
import friday.core.asm.Type;
import friday.core.asm.commons.LocalVariablesSorter;
import friday.core.asm.tree.AbstractInsnNode;
import friday.core.asm.tree.FieldInsnNode;
import friday.core.asm.tree.InsnList;
import friday.core.asm.tree.MethodInsnNode;
import friday.core.asm.tree.VarInsnNode;

import friday.core.instrument.instrumentor.TracerCollectingEnabler.MethodTransformer;
import friday.core.instrument.instrumentor.TracerCollectingEnabler.TargetMethodInfo;

public class SnapReturnVariable implements MethodTransformer{

	@Override
	public InsnList reform(TargetMethodInfo target) {
		
		InsnList replaced = new InsnList();
		
		LocalVariablesSorter lvs = new LocalVariablesSorter(target.getAccess(), target.getDesc(), new MethodVisitor(ASM5) {});
		
		Type callerReturnType = Type.getReturnType(target.getDesc());
		
		AbstractInsnNode insn = target.getInstructions().getFirst();
		
		while(insn!=null){
			
			if(insn.getOpcode() >= IRETURN && insn.getOpcode() <= ARETURN){
				
				AbstractInsnNode prev = insn.getPrevious();

				if(prev instanceof MethodInsnNode){
					
					MethodInsnNode m = (MethodInsnNode)prev;

					if(!Type.getReturnType(m.desc).equals(Type.VOID_TYPE) || m.name.equals("<init>")){
						
						meetReturnableMethodInsn(replaced, callerReturnType, lvs);
						
					}
					
				}else if(prev instanceof VarInsnNode){

					if(ILOAD <=prev.getOpcode() && prev.getOpcode() <= ALOAD){
						
						meetLoadVariableInsn(replaced, callerReturnType, (VarInsnNode) prev);
												
					}
					
				}else if(prev instanceof FieldInsnNode && prev.getPrevious() instanceof VarInsnNode){
					
					if(callerReturnType.getDescriptor().length() == 1)
						replaced.add(new MethodInsnNode(INVOKESTATIC, R_HANDLER_CLASS_NAME, R_HANDLER_METHOD_NAME_VARIABLE, "(" + callerReturnType.getDescriptor() +")V", false));
					else
						replaced.add(new MethodInsnNode(INVOKESTATIC, R_HANDLER_CLASS_NAME, R_HANDLER_METHOD_NAME_VARIABLE, R_HANDLER_METHOD_DESC_VARIABLE, false));
					
					replaced.add(prev.getPrevious());
					
					replaced.add(prev);
					
				}
				
			}

			insn.accept(lvs);
			
			replaced.add(insn);
			
			insn = insn.getNext();
			
		}
		
		return replaced;
		
	}
	
	private void meetReturnableMethodInsn(InsnList replaced, Type callerReturnType, LocalVariablesSorter lvs){
		int nextLocal = lvs.newLocal(callerReturnType);
		
		Integer store = DESC2STORE.get(callerReturnType.getDescriptor());
		
		store = store==null?ASTORE:store;
		
		replaced.add(new VarInsnNode(store, nextLocal));
		
		Integer load = DESC2LOAD.get(callerReturnType.getDescriptor());
		
		load = load==null?ALOAD:load;
		
		replaced.add(new VarInsnNode(load, nextLocal));
		
		if(callerReturnType.getDescriptor().length() == 1)
			replaced.add(new MethodInsnNode(INVOKESTATIC, R_HANDLER_CLASS_NAME, R_HANDLER_METHOD_NAME_VARIABLE, "(" + callerReturnType.getDescriptor() +")V", false));
		else
			replaced.add(new MethodInsnNode(INVOKESTATIC, R_HANDLER_CLASS_NAME, R_HANDLER_METHOD_NAME_VARIABLE, R_HANDLER_METHOD_DESC_VARIABLE, false));
		
		replaced.add(new VarInsnNode(load, nextLocal));
			
	}
	
	private void meetLoadVariableInsn(InsnList replaced, Type callerReturnType, VarInsnNode original){
		
		if(callerReturnType.getDescriptor().length() == 1)
			replaced.add(new MethodInsnNode(INVOKESTATIC, R_HANDLER_CLASS_NAME, R_HANDLER_METHOD_NAME_VARIABLE, "(" + callerReturnType.getDescriptor() +")V", false));
		else
			replaced.add(new MethodInsnNode(INVOKESTATIC, R_HANDLER_CLASS_NAME, R_HANDLER_METHOD_NAME_VARIABLE, R_HANDLER_METHOD_DESC_VARIABLE, false));
		
		replaced.add(new VarInsnNode(original.getOpcode(), original.var));
	}

}
