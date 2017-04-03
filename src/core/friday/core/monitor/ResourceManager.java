package friday.core.monitor;

import java.util.ArrayList;
import java.util.HashMap;

import friday.core.context.id.HexSequence;

public class ResourceManager {

	final public static int METHOD_KEY_DIGIT = 6;
	
	final HexSequence methodKeyGen = new HexSequence(METHOD_KEY_DIGIT);
	
	final HashMap<String, String> methodKeyMap = new HashMap<String, String>();
	
	final ArrayList<MethodIdentifier> methodKeyBuffer = new ArrayList<MethodIdentifier>();
	
	public ArrayList<MethodIdentifier> takeLoadedMethods(){
		
		ArrayList<MethodIdentifier> flushed = new ArrayList<MethodIdentifier>();
		
		synchronized (methodKeyBuffer) {
			
			flushed.addAll(methodKeyBuffer);
			
			methodKeyBuffer.clear();
			
		}
		
		return flushed;
		
	}
	

	public String getMethodKey(String methodSignature){
		
		String key = null;
		
		synchronized (this.methodKeyMap) {
			
			key = this.methodKeyMap.get(methodSignature);
			
			if(key == null){
				
				key = methodKeyGen.next();
				
				this.methodKeyMap.put(methodSignature, key);
				
				synchronized (methodKeyBuffer) {
					
					this.methodKeyBuffer.add(new MethodIdentifier(key, methodSignature));
					
				}
			}
			
		}
		
		return key;
		
	}
	
	final public static int CALL_PATTERN_KEY_DIGIT = 6;

	final HexSequence callPatternKeyGen = new HexSequence(METHOD_KEY_DIGIT);

	final HashMap<String, String> callPatternMap = new HashMap<String, String>();
	
	public String getCallPatternKey(String callPattern){
		
		String key = null;
		
		synchronized (callPatternMap) {
			
			key = this.callPatternMap.get(callPattern);
			
			if(key == null){
				
				key = this.callPatternKeyGen.next();
				
				this.callPatternMap.put(callPattern, key);
				
			}
		}
		
		return key;
		
	}
	
		
}
