package friday.core.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.SecureRandom;

public class KeyUtils {
	
	
	public static String generateSignature(String className, String methodName, String methodDescriptor){
		
		String signature = className + "@" + methodName + methodDescriptor;
		
		signature = signature.substring(0, signature.indexOf(')')+1);
		
		return signature;
		
	}
	
	// 16진수화한 IP
	private static String hexServerIP = null;
	
	// GUID에 사용될 난수의 SEED생성기
	private static final SecureRandom SEEDER = new SecureRandom();
	
	
	/**
	 * 바이트 배열에서 정수값으로 변환한다.
	 * 
	 * @param bytes 변환할 바이트 배열
	 * 
	 * @return 변환된 정수
	 */
	public static int getInt(final byte bytes[]){
		int i= 0;
		int j= 24;
		for( int k=0 ; 0<=j ; ++k ){
			int l = bytes[k] & 0xff;
			i += l << j;
			j -= 8;
		}
		return i;
	}
	
	/**
	 * <code>s</code>을 자릿수 <code>i</code>로 Padding한다.
	 * 
	 * 자릿수를 넘는 문자열인 경우에는 기존 문자열을 그대로 반환한다.
	 * 
	 * Padding문자는 '0'이다.
	 * 
	 * @param s 확장할 문자열
	 * @param i 자릿수
	 * 
	 * @return Padding한 문자열
	 */
	private static String padHex(final String s, final int i){
		final StringBuilder buffer = new StringBuilder();
		for( int j=0, n=i-s.length() ; j<n ; ++j ){
			buffer.append( '0' );
		}
		buffer.append( s );
		return buffer.toString();
	}
	
	/**
	 * <code>i</code>를 <code>j</code>자릿수의 16진수로 바꾸어 반환한다.
	 * 
	 * @param i 변환할 수
	 * @param j 자릿수
	 * 
	 * @return 변환된 문자열
	 */
	public static
	String hexFormat(final int i, final int j){
		final String s = Integer.toHexString( i );
		return padHex( s, j );
	}
	
	/**
	 * 32 byte의 GUID를 생성한다.
	 * 
	 * 생성된 값은 개발자가 인식되는 용도로 사용되는 것이 아니라 프로그램에 의해서 사용된다.
	 * 
	 * 내부 구성
	 * +-----------------+--------------+----------------------+------------------+
	 * | Current Time(8) | Server IP(8) | Object Hash Value(8) | Random Number(8) |
	 * +-----------------+--------------+----------------------+------------------+
	 * 
	 * @param o 해쉬를 만들기 위한 객체
	 * @return 생성된 GUID
	 */
	public static final String generateGUID(final Object o){
		final StringBuilder guid = new StringBuilder( 32 );

		// 시간값
		long timeNow = System.currentTimeMillis();
		int timeLow = (int) timeNow& 0xFFFFFFFF;
		guid.append( hexFormat( timeLow, 8 ) );

		// 서버 IP
		if ( null == hexServerIP ){
			InetAddress localInetAddress = null;
			try{
				// get the inet address
				localInetAddress = InetAddress.getLocalHost();
			}catch( final UnknownHostException uhe ){
				try{
					localInetAddress = InetAddress.getByName( "localhost" );
				}catch ( final UnknownHostException e ){
					e.printStackTrace();
					return null;
				}
			}

			byte serverIP[] = localInetAddress.getAddress();

			hexServerIP = hexFormat( getInt( serverIP ), 8 );
		}
		guid.append( hexServerIP );

		// 객체 해쉬값
		guid.append( hexFormat( System.identityHashCode( o ), 8 ) );

		// 난수
		int node= -1;
		synchronized( SEEDER ){
			node = SEEDER.nextInt();
		}
		guid.append( hexFormat( node, 8 ) );
		return guid.toString();
	}

}
