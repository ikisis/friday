package friday.core.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 맵 관련 유틸들
 * @author swp
 *
 */
public class MapUtils {
	/**
	 * <code>args</code>를 변환하여 {@link Map}으로 반환한다.
	 * 
	 * <pre>
	 * 
	 * MapUtils.<String, String>convert( new Object[][] {
	 * 	new Object[] { "key1", "value1" },
	 * 	new Object[] { "key2", "value2" },
	 * 	new Object[] { "key3", "value3" }
	 * } );
	 * 
	 * </pre>
	 * 
	 * @param args 변환할 객체 배열들
	 * 
	 * @return 변환된 {@link Map} 객체
	 */
	@SuppressWarnings("unchecked")
	public static <K, V> Map<K, V> convert(final Object[][] args) {
		final HashMap<K, V> ret = new HashMap<K, V>();

		if ( null != args ){
			for ( final Object[] mapping : args ){
				final K key = (K) mapping[0];
				final V val = (V) mapping[1];
				ret.put( key, val );
			}
		}

		return ret;
	}
	
}
