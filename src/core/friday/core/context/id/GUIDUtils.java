package friday.core.context.id;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;

public abstract class GUIDUtils {
	
	final static char[] digits = { 
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
		,'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
		};
	
	//4자리 : 14776335
	
	public static char[] getDigits(){
		return digits;
	}
	
	private final static String INSTANCE_KEY = createIpHexString() + createPidHexString();

	public static String getInstanceKey(){
		return INSTANCE_KEY;
	}
	

	public static String createIpHexString(){
		
		StringBuilder sb = new StringBuilder(8);
		
		InetAddress localInetAddress = null;

		try{
			
			localInetAddress = InetAddress.getLocalHost();
			
		}catch( final UnknownHostException uhe ){
			
			try{
				localInetAddress = InetAddress.getByName( "localhost" );
			}catch ( final UnknownHostException e ){}
			
		}
		
		byte serverIP[] = localInetAddress.getAddress();
		
		for( int k=0 ; k<4 ; k++ ){
			int i = serverIP[k] & 0xff;
			sb.append(padHex(decToHex(i), 2));
		}
		
		return sb.toString();
	}
	
	public static String createPidHexString(){
		String sPid = ManagementFactory.getRuntimeMXBean().getName();
		
		int lPid = Integer.parseInt(sPid.substring(0, sPid.indexOf('@')));
		
		return padHex(Integer.toHexString(lPid),4);
	}
	
	
	public static String padHex(final String s, final int i){
		final StringBuilder buffer = new StringBuilder();
		for( int j=0, n=i-s.length() ; j<n ; ++j ){
			buffer.append( '0' );
		}
		buffer.append( s );
		return buffer.toString();
	}
	


	public static String decToHex(int i){
		
		StringBuilder sb = new StringBuilder();
		
		int d = digits.length;
		
		
		int n;
		
		do{
			
			n = i%d;
			i /= d;
			sb.append(digits[n]);
			
		}while(i > 0);
		
		
		return sb.toString();
	}
	
	public static String decToHex(long i){
		
		StringBuilder sb = new StringBuilder();
		
		long d = digits.length;
		
		
		long n;
		
		do{
			
			n = i%d;
			i /= d;
			sb.append(digits[(int)n]);
			
		}while(i > 0);
		
		
		return sb.toString();
	}
}
