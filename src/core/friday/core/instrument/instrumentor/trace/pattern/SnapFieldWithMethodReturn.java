package friday.core.instrument.instrumentor.trace.pattern;

import java.util.ArrayList;

import friday.core.asm.tree.AbstractInsnNode;
import friday.core.asm.tree.FieldInsnNode;
import friday.core.asm.tree.InsnList;
import friday.core.asm.tree.MethodInsnNode;
import friday.core.asm.tree.VarInsnNode;

import friday.core.instrument.instrumentor.TracerCollectingEnabler.MethodTransformer;
import friday.core.instrument.instrumentor.TracerCollectingEnabler.TargetMethodInfo;

public class SnapFieldWithMethodReturn implements MethodTransformer{
	
	private ArrayList<String> fieldNames = new ArrayList<String>();
	
	public SnapFieldWithMethodReturn() {}
	
	public SnapFieldWithMethodReturn(String[] fieldNames){
		if(fieldNames == null || fieldNames.length == 0)throw new IllegalArgumentException("fieldName is empty");
		for (String fieldName : fieldNames) {
			addFieldName(fieldName);
		}
	}

	@Override
	public InsnList reform(TargetMethodInfo target) {
		
		InsnList replaced = new InsnList();
		
		AbstractInsnNode insn = target.getInstructions().getFirst();
		
		while(insn!=null){
			
			if(insn.getOpcode() >= IRETURN && insn.getOpcode() <= RETURN){
				
				for(String fn : this.fieldNames){
					
					String ownerType = target.getClassName();
					String fieldName = fn;
					String fieldType = target.getFieldTypeMap().get(fieldName);
					
					validate(fieldName, fieldType);
					
					replaced.add(new VarInsnNode(ALOAD, 0));
					replaced.add(new FieldInsnNode(GETFIELD, ownerType, fieldName, fieldType));
					replaced.add(new MethodInsnNode(INVOKESTATIC, R_HANDLER_CLASS_NAME, R_HANDLER_METHOD_NAME_VARIABLE, R_HANDLER_METHOD_DESC_VARIABLE, false));
				
				}
			}

			replaced.add(insn);
			
			insn = insn.getNext();
			
		}
		
		return replaced;
	}

	private void validate(String fieldName2, String fieldType) {
		if(fieldName2 == null || fieldType == null)throw new IllegalStateException("check fieldName"); 
	}

	public ArrayList<String> getFieldNames() {
		return fieldNames;
	}

	public void addFieldName(String fieldName){
		this.fieldNames.add(fieldName);
	}

}
