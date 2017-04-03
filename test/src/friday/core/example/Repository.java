package friday.core.example;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

public class Repository {

	@Test
	public void test() {
		
		Repo r = new Repo();
		
		
		/*
		 * java.lang.String@codePointBefore(I)
		 * 
		 * java/lang/String@codePointBefore(I)
		 * 
		 * 
		 * 
		 * --------------canonize
		 * className = java.lang.String
		 * 
		 * methodName = codePointBefore
		 * 
		 * paramList = {int}
		 * 
		 * methodKey = java.lang.String@codePointBefore(int) 
		 * 
		 * 
		 * 
		 * 
		 */
		
		String signature = "java/lang/String@codePointBefore(I)";
		
		String className = null;
		
		
		String methodKey = "java.lang.String@codePointBefore(int)";
		
		String key = null;
		ClassInfos value = null;
		r.classMap.put(key, value);
		
		
		
	}

}

class Repo{
	
	// key  :  java.lang.String , value : ClassInfos
	final HashMap<String, ClassInfos> classMap = new HashMap<String, ClassInfos>();
	
	// key : java.lang.String@codePointBefore(int), value MethodInfos
	final HashMap<String, MethodInfos> methodMap = new HashMap<String, MethodInfos>();
	
}

class ClassInfos{
	
	// key : hostname, value : HostInfos
	HashMap<String, HostInfos> hostMap = new HashMap<String, HostInfos>(); 
	
}


class MethodInfos{
	
	// key : java.lang.String@codePointBefore(int), value : threadEventId
	HashMap<String, String> threadEventIdMap = new HashMap<String, String>();
	
}

class HostInfos{
	
	// key : processId + @ + currentMilisec, value : ProcessInfos
	HashMap<String, ProcessInfos> processMap = new HashMap<String, ProcessInfos>();
	
}

class ProcessInfos{
	
	/**
	 * processId + @ + currentMilisec
	 */
	String processId;
	
	// key : java.lang.String@codePointBefore(int), value : MethodInfos
	HashMap<String, MethodInfos> methodMap = new HashMap<String, MethodInfos>();
	
	
	
}
