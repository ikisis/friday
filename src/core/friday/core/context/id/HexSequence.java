package friday.core.context.id;

public class HexSequence {
	
	public static char PAD_CH = '0';
	
	final static char[] digits = GUIDUtils.digits;
	
	final static int digitsSize = digits.length;
	
	final private int[] seeder;

	final private char[] key;

	public HexSequence(int size) {
		
		this.seeder = new int[size];
		
		this.key = new char[size];
		
		for(int i = 0; i < size;i++){
			key[i] = PAD_CH;
		}
	}
	
	
	public String next(){
		
		int i = seeder.length -1;
		
		seeder[i] ++;
		
		if(seeder[i] == digitsSize){
			
			do{
				
				seeder[i] = 0;

				key[i] = digits[seeder[i]];
				
				seeder[--i]++;
				
			}while(seeder[i] == digitsSize);
			
		}
		
		key[i] = digits[seeder[i]];
		
		
		return new String(key);
		
	}
	
}
