package friday.core.context.id;

import static friday.core.context.id.GUIDUtils.*;

public class GUID32 {
	
	private final static String STATIC_BASE = getInstanceKey();
	
	final static char[] digits = getDigits();
		
	private char[] key = new char[32];
	
	private int baseLength = 0;

	private int[] seeder = null;

	final private int digitsSize = digits.length;

	
	public GUID32(Object obj){
		init(obj);
	}
	public GUID32(){
		init(new Object());
	}

	
	private void init(Object obj){
				
		StringBuilder baseBuilder = new StringBuilder(32);
		baseBuilder
		.append(STATIC_BASE)
		.append(decToHex(System.currentTimeMillis()))
		.append(padHex(decToHex(System.identityHashCode(obj)), 6));
		
		this.baseLength = baseBuilder.length();
		
		for(int i =0; i < 32; i++){
			if(i < baseBuilder.length()){
				key[i] = baseBuilder.charAt(i);
			}else{
				key[i] = '0';
			}
		}
		
		this.seeder = new int[key.length - this.baseLength];
		
	}


	public String next(){
		int i = seeder.length -1;
		
		
		seeder[i] ++;
		
		if(seeder[i] == digitsSize){
			
			do{
				
				seeder[i] = 0;

				key[i+this.baseLength] = digits[seeder[i]];
				
				seeder[--i]++;
				
			}while(seeder[i] == digitsSize);
			
		}
		
		key[i+this.baseLength] = digits[seeder[i]];
		
		return new String(key);
		
	}
	

	
}
