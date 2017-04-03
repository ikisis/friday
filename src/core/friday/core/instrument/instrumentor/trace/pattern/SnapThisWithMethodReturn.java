package friday.core.instrument.instrumentor.trace.pattern;

import friday.core.asm.tree.AbstractInsnNode;
import friday.core.asm.tree.InsnList;
import friday.core.asm.tree.MethodInsnNode;
import friday.core.asm.tree.VarInsnNode;

import friday.core.instrument.instrumentor.TracerCollectingEnabler.MethodTransformer;
import friday.core.instrument.instrumentor.TracerCollectingEnabler.TargetMethodInfo;


public class SnapThisWithMethodReturn implements MethodTransformer{

	@Override
	public InsnList reform(TargetMethodInfo target) {
		
		InsnList replaced = new InsnList();
		
		AbstractInsnNode insn = target.getInstructions().getFirst();
		
		while(insn!=null){
			
			if(insn.getOpcode() >= IRETURN && insn.getOpcode() <= RETURN){

				replaced.add(new VarInsnNode(ALOAD, 0));
				replaced.add(new MethodInsnNode(INVOKESTATIC, R_HANDLER_CLASS_NAME, R_HANDLER_METHOD_NAME_VARIABLE, R_HANDLER_METHOD_DESC_VARIABLE, false));
				
			}

			replaced.add(insn);
			
			insn = insn.getNext();
			
		}
		
		return replaced;
	}

}
