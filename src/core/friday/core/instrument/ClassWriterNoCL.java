package friday.core.instrument;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import friday.core.asm.ClassReader;
import friday.core.asm.ClassVisitor;
import friday.core.asm.ClassWriter;
import friday.core.asm.Opcodes;

public class ClassWriterNoCL extends ClassWriter {
	
	private final Map<String, Set<String>> type2supertypes= new HashMap<String, Set<String>>();
	
	private final Map<String, String> type2superclass= new HashMap<String, String>();
	
	private final Map<String, Boolean> type2isInterface= new HashMap<String, Boolean>();
	
	private ClassLoader loader;

	public ClassWriterNoCL(int flags, ClassLoader loader) {
		super(flags);
		this.loader = loader == null ? Thread.currentThread().getContextClassLoader():loader;
	}
	
	public ClassWriterNoCL(int flags) {
		super(flags);
		this.loader = Thread.currentThread().getContextClassLoader();
	}
	
    protected String getCommonSuperClass(final String type1, final String type2) {
    	
		if( getSuperTypes( type2).contains( type1)) 
			return type1;

		if( this.getSuperTypes( type1).contains( type2)) 
			return type2;

		if( isInterface( type1) || isInterface( type2)) 
			return "java/lang/Object";
		else
		{
			String type= type1;
			do 
			{
				type= getSuperclass( type);
				if( type== null) return "java/lang/Object";
			} 
			while( !this.getSuperTypes( type2).contains( type));
			 
			return type;
		}
    }
    
	private Set<String> getSuperTypes( String type)
	{
		if( !this.type2supertypes.containsKey(type)) 
			initializeTypeHierarchyFor(type);

		Set<String> types= this.type2supertypes!= null ? this.type2supertypes.get(type) : null;
		return types!= null ? types : new HashSet<String>();
	}

	private String getSuperclass( String type) 
	{
		if( !this.type2superclass.containsKey( type)) 
			initializeTypeHierarchyFor( type);

		String sc= this.type2superclass!= null ? this.type2superclass.get( type) : null; 
		return sc;
	}
	
	private boolean isInterface( String type) 
	{
		if( !this.type2isInterface.containsKey( type)) 
			initializeTypeHierarchyFor(type);

		Boolean inf= this.type2isInterface!= null ? this.type2isInterface.get(type) : null;
		return inf!= null ? inf : false;
	}
    
	private void initializeTypeHierarchyFor( final String internalTypeName){
		
		InputStream is = null;
		
		
		try {
			
			is = this.loader.getResourceAsStream(internalTypeName + ".class");
			
			ClassReader classReader = new ClassReader(is);
			
			classReader.accept( new ClassVisitor(Opcodes.ASM5){
				@Override
				public void visit( int version, int access, String name, String signature, String superName, String[] interfaces) 
				{
					super.visit( version, access, name, signature, superName, interfaces);
					ClassWriterNoCL.this.type2superclass.put( internalTypeName, superName);
					ClassWriterNoCL.this.type2isInterface.put(internalTypeName, (access & Opcodes.ACC_INTERFACE) > 0);
					Set<String> superTypes= new HashSet<String>();
					superTypes.add( internalTypeName);

					if( superName!= null) 
					{
						superTypes.add( superName);
						superTypes.addAll( ClassWriterNoCL.this.getSuperTypes( superName));
					}

					for( String superInterface : interfaces) 
					{
						superTypes.add( superInterface);
						superTypes.addAll( ClassWriterNoCL.this.getSuperTypes( superInterface));
					}
					
					ClassWriterNoCL.this.type2supertypes.put( internalTypeName, superTypes);
				}
			}, ClassReader.SKIP_CODE | ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES);
			
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally{
			if(is!=null){
				try {
					is.close();
				} catch (IOException e) {
					/* ignored */
				}
			}
		}
	}
}
