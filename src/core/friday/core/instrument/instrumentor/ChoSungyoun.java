package friday.core.instrument.instrumentor;

import friday.core.asm.ClassVisitor;
import friday.core.asm.ClassWriter;
import friday.core.asm.Label;
import friday.core.asm.MethodVisitor;
import friday.core.asm.Type;
import friday.core.asm.commons.LocalVariablesSorter;
import friday.core.asm.commons.TryCatchBlockSorter;

import friday.core.context.Central;
import friday.core.instrument.Instrumentor;
import friday.core.monitor.AgentMonitor;
import friday.core.util.KeyUtils;


public class ChoSungyoun extends Instrumentor implements RecConstants{
	


	@Override
	protected ClassVisitor wrapMore(ClassVisitor cv) {
				
		return super.wrapMore(cv);
		
	}


	@Override
	protected ClassVisitor getClassVisitorStarter(ClassWriter cw) {
		return new ClassVisitor(ASM5, cw) {
			
			protected String className;
			
			@Override
		    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
		    	className = name;
		    	super.visit(version, access, className, signature, superName, interfaces);
		    }
			
			@Override
		    public MethodVisitor visitMethod(final int access, final String methodName, final String methodDescriptor, final String signature, final String[] exceptions) {
										
				MethodVisitor mv = super.visitMethod(access, methodName, methodDescriptor, signature, exceptions);
				
				if(!Central.RULE.isRecordable(access, methodName))return mv;
										
				final LocalVariablesSorter lvs = new LocalVariablesSorter(access, methodDescriptor, mv);
				
				final TryCatchBlockSorter tcbs = new TryCatchBlockSorter(lvs, access, methodName, methodDescriptor, signature, exceptions);
				
				return new MethodVisitor(ASM5, tcbs) {	
							
							Label tryStart = new Label(),tryEnd = new Label(),catchBlock = new Label(),exitBlock = new Label();
														
							@Override
							public void visitCode(){
								
								super.visitCode();
								
								String methodSignature = KeyUtils.generateSignature(className, methodName, methodDescriptor);
								
								String msKey = AgentMonitor.getInstance().RESOURCE.getMethodKey(methodSignature);
								
								this.visitLdcInsn(msKey);
								
								this.visitMethodInsn(
										INVOKESTATIC, 
										R_HANDLER_CLASS_NAME, 
										methodName.equals("<init>")?R_HANDLER_METHOD_NAME_NEW:R_HANDLER_METHOD_NAME_START, 
										R_HANDLER_METHOD_DESC_START, 
										false
										);
								
								this.visitTryCatchBlock(tryStart, tryEnd, catchBlock, CATCH_HANDLER_CLASS_NAME);

								this.visitLabel(tryStart);
								
							}
							
							@Override
							public void visitMaxs(int maxStack, int maxLocals){
								
								this.visitLabel(tryEnd);
								
								this.visitJumpInsn(GOTO, exitBlock);
								
								this.visitLabel(catchBlock);
								
								int i = lvs.newLocal(Type.getType(CATCH_HANDLER_TYPE_NAME));
								
								this.visitMethodInsn(INVOKESTATIC, R_HANDLER_CLASS_NAME, R_HANDLER_METHOD_NAME_THROW, R_HANDLER_METHOD_DESC_THROW, false);
								
								this.visitVarInsn(ASTORE, i);
								
								this.visitVarInsn(ALOAD, i);
								
								this.visitInsn(ATHROW);

								this.visitLabel(exitBlock);
								
								super.visitMaxs(maxStack, maxLocals);
								
							}
							
							@Override
						    public void visitInsn(int opcode) {
								/**
							    int IRETURN = 172; // 
							    int LRETURN = 173; // -
							    int FRETURN = 174; // -
							    int DRETURN = 175; // -
							    int ARETURN = 176; // -
							    int RETURN = 177; // -
							    */
								if(opcode >= IRETURN && opcode <= RETURN){
									this.visitMethodInsn(INVOKESTATIC, R_HANDLER_CLASS_NAME, R_HANDLER_METHOD_NAME_RETURN, R_HANDLER_METHOD_DESC_RETURN, false);
								}
								
								super.visitInsn(opcode);
							}
							
							@Override
				    	    public void visitLineNumber(int line, Label start) {
				    	    				
				    	    	
//				    			if(line < 6){
//				    				this.visitInsn(ICONST_0 + line);
//				    			}else if(6 <= line && line < 128){
//				    				this.visitIntInsn(BIPUSH, line);
//				    			}else{
//				    				this.visitIntInsn(SIPUSH, line);
//				    			}
//				    			
//				    			this.visitMethodInsn(INVOKESTATIC, R_HANDLER_CLASS_NAME, R_HANDLER_METHOD_NAME_LINE, R_HANDLER_METHOD_DESC_LINE, false);

								super.visitLineNumber(line, start);
				    			
				    	    }

						};
			}
		};
	}


	
}
