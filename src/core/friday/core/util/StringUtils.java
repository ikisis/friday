package friday.core.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * @file			rn.StringUtils.java
 * @filetype		java source file
 * @brief			
 * @author			JongOh Lee
 * @version			1.0
 * @history
 * 성		명		일			자			근  거  자  료			변  경  내  용
 * ----------------	-------------------		-------------------		--------------------------	
 * JongOh Lee :		Mar 23, 2015:			product 개발:			신규 작성
 *
 */
public abstract class StringUtils
{
	private static final String EMPTY_STRING= "";
	private static final String NULL_STRING= "null";
	private static final String ARRAY_START= "{";
	private static final String ARRAY_END= "}";
	private static final String EMPTY_ARRAY= ARRAY_START+ ARRAY_END;
	private static final String ARRAY_ELEMENT_SEPARATOR= ", ";

	private static final String FOLDER_SEPARATOR= "/";
	private static final String WINDOWS_FOLDER_SEPARATOR= "\\";
	private static final String TOP_PATH= "..";
	private static final String CURRENT_PATH= ".";
	private static final char EXTENSION_SEPARATOR= '.';

    /** 문자열 유형을 나타내는 상수 : IP 주소 문자열*/
    private static final int IP_ADDRESS= 0;

	/** 문자열 유형을 나타내는 상수 : 이메일 주소 문자열 */
    public static final int EMAIL_ADDRESS= 1;

    /** 문자열 유형을 나타내는 상수 : 웹 주소 문자열*/
    public static final int WEB_ADDRESS= 2;

    /** 문자열 유형을 나타내는 상수 : 우편번호 문자열*/
    public static final int ZIP_CODE= 3;

    /** 문자열 유형을 나타내는 상수 : 주민등록번호 문자열*/
    public static final int SS_NO= 4;

    /** 문자열 유형을 나타내는 상수 : 사업자등록번호 문자열*/
    public static final int ER_NO= 5;

    /** 문자열 유형을 나타내는 상수 : 전화번호 문자열*/
    public static final int TEL_NO= 6;

    /** 문자열 유형을 나타내는 상수 : 주민등록번호 문자열*/
    public static final int SS_SNO= 7;
    
    
    /** IP 주소 문자열 포맷 */
    public static final String IP_ADDRESS_PATTERN =
    	"((([2][0-4][0-9])|([2][5][0-5])|([1][0-9]{2})|([1-9][0-9])|([0-9]))\\.){3}(([2][0-4][0-9])|([2][5][0-5])|([1][0-9]{2})|([1-9][0-9])|([0-9]))";

    /** 이메일 주소 문자열 포맷 */
    public static final String EMAIL_ADDRESS_PATTERN= ".+@.+\\..+(\\..+){0,1}";

    /** 웹 주소 문자열 포맷 */
    public static final String WEB_ADDRESS_PATTERN= "http://.+";

    /** 우편번호 문자열 포맷 */
    public static final String ZIP_CODE_PATTERN= "[0-9]{3}-[0-9]{3}";

    /** 주민등록번호 문자열 포맷 */
    public static final String SSNO_PATTERN= "[0-9]{2}(([0][1-9])|([1][0-2]))[0|1|2|3][0-9]-[1|2|3|4][0-9]{6}";

    /** 주민등록번호 연속문자열 포맷 */
    public static final String SSSNO_PATTERN= "[0-9]{2}(([0][1-9])|([1][0-2]))[0|1|2|3][0-9][1|2|3|4][0-9]{6}";
    
    
    /** 사업자등록번호 문자열 포맷 */
    public static final String ERNO_PATTERN= "[0-9]{3}-[0-9]{2}-[0-9]{5}";

    /** 전화번호 문자열 포맷 */
    public static final String TELNO_PATTERN= "[0](([2])|([0-9]{2}))-[1-9][0-9]{2,3}-[0-9]{4}";
    
    /** 시스템 개행문자 */
    public static final String LINE_SEP = System.getProperty("line.separator");
    
    /** 시스템 개행문자 길이 */
    public static final int LINE_SEP_LEN = LINE_SEP.length();
    
    /** 빈 문자열 배열 */
    public static final String[] EMPTY_STRING_ARRAY= new String[0];
    
    private static Random rgen= new Random();

    private static class SortNode
    {
        public int getLeft()
        {
            return left;
        }

        public int getRight()
        {
            return right;
        }

        private int left;
        private int right;

        public SortNode( int i, int j)
        {
            left= i;
            right= j;
        }
    }
    
    /**
     * 문자열이 빈문자열인지 판단한다. 전달된 문자열이 <code>null</code>이거나 길이가 0인 문자열 또는 공백문자로만 채워쳐 있는 경우
     * 빈문자열로 판단한다.
     * @param str 검사할 문자열
     * @return 빈문자열인지 여부
     */
	public static boolean isEmpty( final CharSequence str)
	{
		if( null== str ) return true;
		for( int i= 0, n= str.length(); i< n; ++i)
		{
			if( Character.isWhitespace( str.charAt( i)))
				continue;
			return false;
		}
		return true;
	}
	
	public static boolean isEmpty( String str)
	{
		str= str!= null ? str.trim() : str;
		return isEmpty( (CharSequence)str);
	}
	
	/**
	 * 문자열의 길이가 있는지 판단한다. 전달된 문자열이 <code>null</code>이 아니고 길이가 0보다 큰 경우 길이가 있는 것으로 판단한다. 
	 * @param str 검사할 문자열
	 * @return 문자열의 길이가 있는지 여부 
	 */
	public static boolean hasLength( CharSequence str)
	{
		return ( str!= null && str.length() >0);
	}
	
	/**
	 * 문자열의 길이가 있는지  판단한다. 전달된 문자열이 <code>null</code>이 아니고 길이가 0보다 큰 경우 길이가 있는 것으로 판단한다.
	 * @param str 검사할 문자열
	 * @return 문자열의 길이가 있는지 여부
	 */
	public static boolean hasLength( String str)
	{
		return hasLength( (CharSequence)str);
	}
	
	/**
	 * 문자열의 내용이 있는지 판단한다. 전달된 문자열이 <code>null</code>이 아니고 길이가 있어야하며 공백문자로만 채워져 있지 않아야 
	 * 문자열에 내용이 있는 것으로 판단한다. 
	 * @param str 검사할 문자열
	 * @return 문자열에 내용이 있는지 여부
	 * @see #hasLength(CharSequence)
	 */
	public static boolean hasText( CharSequence str)
	{
		if( !hasLength( str))	return false;
		int strLen= str.length();
		for( int i= 0; i< strLen; i++)
		{
			if( !Character.isWhitespace( str.charAt( i)))
				return true;
		}
		return false;
	}
	
	/**
	 * 문자열의 내용이 있는지 판단한다. 전달된 문자열이 <code>null</code>이 아니고 길이가 있어야하며 공백문자로만 채워져 있지 않아야 
	 * 문자열에 내용이 있는 것으로 판단한다. 
	 * @param str 검사할 문자열
	 * @return 문자열에 내용이 있는지 여부 
	 */
	public static boolean hasText( String str)
	{
		return hasText( (CharSequence)str);
	}
	
	/**
	 * 문자열에 공백문자가 있는지 판단한다. <code>null</code>이 아닌 길이가 있는 문자열에 공백문자가 있어야 공백문자를 포함하는 것으로 
	 * 판단한다. 전달된 문자열이 <code>null</code>이거나 길이가 없는 경우도 공백문자를 포함하지 않은 것으로 판단한다.
	 * @param str 검사할 문자열
	 * @return 공백문자를 포함하는지 여부
	 * @see #hasText(CharSequence)
	 */
	public static boolean containsWhitespace( CharSequence str)
	{
		if( !hasLength( str))	return false;
		int strLen= str.length();
		for( int i= 0; i< strLen; i++)
		{
			if( Character.isWhitespace( str.charAt( i)))
				return true;
		}
		return false;
	}
	
	/**
	 * 문자열에 공백문자가 있는지 판단한다. <code>null</code>이 아닌 길이가 있는 문자열에 공백문자가 있어야 공백문자를 포함하는 것으로 
	 * 판단한다. 전달된 문자열이 <code>null</code>이거나 길이가 없는 경우도 공백문자를 포함하지 않은 것으로 판단한다.
	 * @param str 검사할 문자열
	 * @return 공백문자를 포함하는지 여부
	 * @see #hasText(CharSequence)
	 */
	public static boolean containsWhitespace( String str)
	{
		return containsWhitespace( (CharSequence)str);
	}
	
	/**
	 * 문자열의 앞부분과 뒷부분에 있는 공백문자를 모두 제거한다. 문자열 중간에 있는 공백문자는 제거되지 않는다.
	 * @param str 공백을 제거할 문자열
	 * @return 공백이 제거된 문자열
	 * @see #hasLength(String)
	 */
	public static String trimWhitespace( String str)
	{
		if( !hasLength( str))	return str;
		StringBuilder sb= new StringBuilder( str);
		while( sb.length()> 0 && Character.isWhitespace( sb.charAt( 0)))
			sb.deleteCharAt( 0);
		while( sb.length()> 0 && Character.isWhitespace( sb.charAt( sb.length()- 1)))
			sb.deleteCharAt( sb.length()- 1);
		return sb.toString();
	}
	
	/**
	 * 문자열에 포함된 모든 공백문자를 제거한다. 
	 * @param str 공백을 제거할 문자열
	 * @return 공백이 제거된 문자열
	 * @see #hasLength(String)
	 */
	public static String trimAllWhitespace( String str)
	{
		if( !hasLength( str))	return str;
		StringBuffer sb= new StringBuffer( str);
		int index= 0;
		while( sb.length()> index)
		{
			if( Character.isWhitespace( sb.charAt( index)))
				sb.deleteCharAt( index);
			else
				index++;
		}
		
		return sb.toString();
	}
	
	/**
	 * 전달된 문자열의 앞부분에 포함된 공백문자를 제거한다. 문자열 중간에 있는 공백문자는 제거되지 않는다.
	 * @param str 공백을 제거할 문자열
	 * @return 공백이 제거된 문자열
	 * @see #hasLength(String) 
	 */
	public static String trimLeadingWhitespace( String str)
	{
		if( !hasLength( str))	return str;
		StringBuilder sb= new StringBuilder( str);
		while( sb.length()> 0 && Character.isWhitespace( sb.charAt( 0)))
			sb.deleteCharAt( 0);
		return sb.toString();
	}
	
	/**
	 * 전달된 문자열의 뒷부분에 포함된 공백문자를 제거한다. 문자열 중간에 있는 공백문자는 제거되지 않는다.
	 * @param str 공백을 제거할 문자열
	 * @return 공백이 제거된 문자열
	 * @see #hasLength(String) 
	 */
	public static String trimTrailingWhitespace( String str)
	{
		if( !hasLength( str))	return str;
		StringBuilder sb= new StringBuilder( str);
		while( sb.length()> 0 && Character.isWhitespace(  sb.charAt( sb.length()- 1)))
			sb.deleteCharAt( sb.length()- 1);
		
		return sb.toString();
	}
	
	/**
	 * 전달된 문자열의 앞부분에 특정 문자 <code>leadingChar</code>가 발견되면 반복적으로 이를 제거한다. 문자열 중간에 발견된 
	 * <quote>특정문자</quote>는 제거되지 않는다.
	 * <p>예)
	 * <blockquote><pre>
	 * String string= StringUtils.trimLeadingCharacter( "FFFCFW001", 'F');
	 * // 결과 문자열 :  "CFW001" 
	 * </pre></blockquote>
	 * </p>
	 * @param str 원본 문자열
	 * @param leadingChar 제거할 특정문자
	 * @return 원본 문자열에서 앞부분의 특정문자가 제거된 문자열
	 * @see #hasLength(String)
	 */
	public static String trimLeadingCharacter( String str, char leadingChar)
	{
		if( !hasLength( str))	return str;
		StringBuilder sb= new StringBuilder( str);
		while( sb.length()> 0 && sb.charAt( 0)== leadingChar)
			sb.deleteCharAt( 0);
		
		return sb.toString();
	}
	
	/**
	 * 전달된 문자열의 뒷부분에 특정 문자 <code>leadingChar</code>가 발견되면 반복적으로 이를 제거한다. 문자열 중간에 발견된 
	 * <quote>특정문자</quote>는 제거되지 않는다.
	 * <p>예)
	 * <blockquote><pre>
	 * String string= StringUtils.trimLeadingCharacter( "CFW001FCFFF", 'F');
	 * // 결과 문자열 :  "CFW001FC" 
	 * </pre></blockquote>
	 * </p>
	 * @param str 원본 문자열
	 * @param leadingChar 제거할 특정문자
	 * @return 원본 문자열에서 앞부분의 특정문자가 제거된 문자열
	 * @see #hasLength(String)
	 */
	public static String trimTrailingCharacter( String str, char trailingChar)
	{
		if( !hasLength( str))	return str;
		StringBuilder sb= new StringBuilder( str);
		while( sb.length()> 0 && sb.charAt( sb.length()- 1)== trailingChar)
			sb.deleteCharAt( sb.length()- 1);
		return sb.toString();
	}
	
	/**
	 * 전달된 문자열이 특정문자열  <code>prefix</code>로 시작하는지를 판단한다. 
	 * 비교할 특정문자열의 모든 문자는 대소문자를 구분하지 않는다.
	 * <p>예)
	 * <blockquote><pre>
	 * boolean isStartsWithFWC= StringUtils.startsWithIgnoreCase( "FWC001SVC", "FWC");
	 * boolean isStartsWithfwC= StringUtils.startsWithIgnoreCase( "FWC001SVC:, :"fwC");
	 * // isStartsWithFWC와 isStartsWithfwc 모두 true 이다. 
	 * </pre></blockquote>
	 * </p>
	 * @param str 검사할 문자열
	 * @param prefix 비교할 문자열
	 * @return 검사할 문자열이 비교할 문자열로 시작하는지 여부
	 */
	public static boolean startsWithIgnoreCase( String str, String prefix)
	{
		if( str== null || prefix== null)		return false;
		if( str.startsWith( prefix))			return true;
		if( str.length()< prefix.length())	return false;
		
		String lcStr= str.substring( 0,prefix.length()).toLowerCase();
		String lcPrefix= prefix.toLowerCase();
		return lcStr.equals( lcPrefix);
	}
	
	/**
	 * 전달된 문자열이 특정문자열 <code>suffix</code>로 끝나는지를 판단한다.
	 * 비교할 특정문자열의 모든 문자는 대소문자를 구분하지 않는다.
	 * <p>예)
	 * <blockquote><pre>
	 * boolean isEndsWithSVC= StringUtils.endsWithIgnoreCase( "FWC001SVC", "SVC");
	 * boolean isEndsWithsvC= StringUtils.endsWithIgnoreCase( "FWC001SVC", "svC");
	 * // isEndsWithSVC와 isEndsWithsvC 모두 true 이다.
	 * </pre></blockquote>
	 * </p>
	 * @param str 검사할 문자열
	 * @param suffix 비교할 문자열
	 * @return 검사할 문자열이 비교할 문자열로 끝나는지 여부
	 */
	public static boolean endsWithIgnoreCase( String str, String suffix)
	{
		if( str== null || suffix== null)		return false;
		if( str.endsWith( suffix))				return true;
		if( str.length()< suffix.length())	return false;
		
		String lcStr= str.substring( str.length()- suffix.length()).toLowerCase();
		String lcSuffix= suffix.toLowerCase();
		return lcStr.equals( lcSuffix);
	}
	
	/**
	 * 전달된 문자열 <code>str</code>이 특정 인덱스 <code>index</code>부터 비교할 문자열 <code>substring</code> 전체를 포함하는지 
	 * 판단한다.
	 * <p>예)
	 * <blockquote><pre>
	 * boolean matched= StringUtils.substringMatch( "FWC001SVC01", 3, "001SVC");
	 * // matched 는 true 이다. 
	 * </pre></blockquote>
	 * </p>
	 * @param str 검사할 문자열
	 * @param index 비교 시작 인덱스
	 * @param substring 비교할 문자열
	 * @return 검사할 문자열이 시작 인덱스 <code>index</code> 
	 */
	public static boolean substringMatch( CharSequence str, int index, CharSequence substring)
	{
		for( int j= 0; j< substring.length(); j++)
		{
			int i= index+ j;
			if( i>= str.length() || str.charAt( i)!= substring.charAt( j))
				return false;
		}
		return true;
	}
	
	/**
	 * 전달된 문자열 <code>str</code>에 특정 문자열 <code>sub</code>이 몇 번 반복적으로 나타나는지를 검사한다. 
	 * @param str 검사할 문자열
	 * @param sub 비교할 문자열
	 * @return 검사할 문자열에 포함된 비교 문자열의 갯수
	 */
	public static int countOccurrencesOf( String str, String sub)
	{
		if( str== null || sub== null || str.length()== 0 || sub.length()== 0)	return 0;
		int count= 0;
		int pos= 0;
		int idx;
		while( ( idx= str.indexOf( sub, pos))!= -1)
		{
			++count;
			pos= idx+ sub.length();
		}
		return count;
	}
	
	/**
	 * 전달된 원본 문자열 <code>inString</code>에서 변경대상 문자열 <code>oldSubstring</code>을 찾아
	 * 변경할 문자열 <code>newSubstring</code>로 모두 변경한다. 
	 * @param inString 원본 문자열
	 * @param oldSubstring 변경대상 문자열
	 * @param newSubstring  변경할 문자열
	 * @return 원본 문자열에서 변경대상 문자열 <code>oldSubstring</code>이 변경할 문자열 <code>newSubstring</code>로 모두 변경된
	 * 문자열 
	 */
	public static String replace( String inString, String oldSubstring, String newSubstring)
	{
		if( !hasLength( inString) || !hasLength( oldSubstring) || newSubstring== null)	return inString;
		StringBuilder sb= new StringBuilder();
		int pos= 0;
		int index= inString.indexOf( oldSubstring);
		int patLen= oldSubstring.length();
		while( index>= 0)
		{
			sb.append( inString.substring( pos, index));
			sb.append( newSubstring);
			pos= index+ patLen;
			index= inString.indexOf( oldSubstring, pos);
		}
		sb.append( inString.substring( pos));
		return sb.toString();
	}
	
	/**
	 * 전달된 원본 문자열 <code>inString</code>에서 특정 문자열 <code>toDeleteSubstring</code>을 모두 제거한다.
	 * @param inString 원본 문자열
	 * @param toDeleteSubstring 제거할 문자열
	 * @return 원본 문자열 <code>inString</code>에서 제거할 문자열 <code>toDeleteSubstring</code>이 모두 제거된 문자열
	 */
	public static String delete( String inString, String toDeleteSubstring)
	{
		return replace( inString, toDeleteSubstring, "");
	}
	
	/**
	 * 전달된 원본 문자열 <code>inString</code>에서 제거할 문자 집합 <code>charsToDelete</code>에 포함된 모든 문자를 제거한다.
	 * <p>예)
	 * <blockquote><pre>
	 * String string= StringUtils.deleteAny( "FWC001SVCIn", "Cn");
	 * // 결과 문자열 : "FW001SVI"
	 * </pre></blockquote>
	 * </p> 
	 * @param inString 원본 문자열
	 * @param charsToDelete 제거할 문자 집합
	 * @return 원본 문자열 <code>inString</code>에서 제거할 문자들이 모두 제거된 문자열
	 * @see #hasLength(String)
	 */
	public static String deleteAny( String inString, String charsToDelete)
	{
		if( !hasLength( inString) || !hasLength( charsToDelete))	return inString;
		StringBuilder sb= new StringBuilder();
		for( int i= 0; i< inString.length(); i++)
		{
			char c= inString.charAt( i);
			if( charsToDelete.indexOf( c)== -1)	sb.append( c);
		}
		return sb.toString();
	}
	
	/**
	 * 전달된 문자열을 작은 따옴표(')로 감싼 형태로 변경한다.
	 * @param str 원본 문자열
	 * @return 작은 따옴표(')로 둘러 쌓이도록 변경된 문자열
	 */
	public static String quote( String str)
	{
		return str!= null ? "'"+ str+ "'" : null;
	}
	
	/**
	 * 전달된 객체가 문자열 <code>java.lang.String</code> 타입인 경우 이를 작업 따옴표(')로 감싼다.
	 * @param obj
	 * @return
	 */
	public static Object quoteIfString( Object obj)
	{
		return obj instanceof String ? quote( (String)obj) : obj;
	}
	
	/** 
	 * 경로를 모두 포함한 이름(qualified name) <code>qualifiedName</code>에서 경로구분 문자로 온점(.)을 사용하여 경로부분을 제거한다. 
	 * 패키지명을 포함하는 클래스명(fully qualified class name)에서 클래스명을 추출하는 용도로 사용될 수 있다.
	 *  <p>예)
	 *  <blockquote><pre>
	 *  String className= StringUtils.unqualify( "java.lang.String");
	 *  // 결과 문자열 : "String"
	 *  </pre></blockquote>
	 *  </p>
	 * @param qualifiedName 경로를 포함한 이름(qualified name)
	 * @return 경로를 포함한 이름에서 경로부분이 제거된 이름
	 */
	public static String unqualify( String qualifiedName)
	{
		return unqualify( qualifiedName, '.');
	}
	
	/**
	 * 경로를 모두 포함한 이름(qualified name) <code>qualifiedName</code>에서 경로구분 문자 <code>separator</code>를 기준으로 
	 * 경로부분을 제거한다.
	 * @param qualifiedName 경로를 포함한 이름(qualified name)
	 * @param separator 경로구분 문자
	 * @return 경로를 포함한 전체 이름에서 경로부분이 제거된 이름
	 */
	public static String unqualify( String qualifiedName, char separator)
	{
		return qualifiedName.substring( qualifiedName.lastIndexOf( separator)+ 1);
	}
	
	/**
	 * 원본 문자열 <code>str</code>의 첫번째 문자를 대문자로 변경한다. 
	 * @param str 변경할 문자열
	 * @return 대문자로 변경된 문자열
	 */
	public static String capitalize( String str)
	{
		return changeFirstCharacterCase( str, true);
	}
	
	/**
	 * 원본 문자열 <code>str</code>의 첫번째 문자를 소문자로 변경한다. 
	 * @param str 변경할 문자열
	 * @return 소문자로 변경된 문자열
	 */
	public static String uncapitalize( String str)
	{
		return changeFirstCharacterCase( str, false);
	}
	
	/**
	 * 원본 문자열 <code>str</code>의 첫번째 문자를 대문자 또는 소문자로 변경한다. 
	 * <code>capitalize</code>가 <code>true</code>이면 대문자로 변경하고 false이면 소문자로 변경한다.
	 * @param str 변경할 문자열 
	 * @param capitalize 대소문자로 변결할지 여부 
	 * @return 변경된 문자열
	 */
	public static String changeFirstCharacterCase( String str, boolean capitalize)
	{
		if( str== null || str.length()== 0)	return str;
		StringBuilder sb= new StringBuilder( str.length());
		if( capitalize)	sb.append( Character.toUpperCase( str.charAt( 0)));
		else					sb.append( Character.toLowerCase( str.charAt( 0)));
		sb.append( str.substring( 1));
		return sb.toString();
	}
	
	/**
	 * 전달된 파일 경로 <code>path</code>에서 파일이름을 추출한다.
	 * @param path 파일경로
	 * @return 추출된 파일이름, 파일경로가 <code>null</code>인 경우 <code>null</code>을 리턴
	 */
	public static String getFilename( String path)
	{
		if( path== null)	return null;
		int separatorindex= path.lastIndexOf( FOLDER_SEPARATOR);
		return separatorindex!= -1 ? path.substring( separatorindex+ 1) : path;
	}
	
	/**
	 * 전달된 파일경로 <code>path</code>에서 파일 확장자 부분을 추출한다.
	 * @param path 파일경로
	 * @return 추출된 파일 확장자, 파일경로가 <code>null</code>이거나 확장자를 구분할 수 있는 구분자(.)가 포함되어 있지 않은 경우 
	 * <code>null</code>을 리턴
	 */
	public static String getFilenameExtension( String path)
	{
		if( path== null)		return null;
		int extIndex= path.lastIndexOf( EXTENSION_SEPARATOR);
		if( extIndex== -1)	return null;
		int folderIndex=  path.lastIndexOf( FOLDER_SEPARATOR);
		if( folderIndex> extIndex)	return null;
		return path.substring( extIndex+ 1);
	}
	
	/**
	 * 전달된 파일경로 <code>path</code>에서 파일확장자를 제외한 전제경로(파일명 포함)를 추출한다.
	 * @param path 파일경로
	 * @return 추출된 파일명을 포함한 파일경로, 파일경로가 <code>null</code>이거나 확장자를 구분할 수 있는 구분자(.)가 포함되어 있지
	 * 않은 경우 <code>null</code>을 리턴
	 */
	public static String stripFilenameExtension( String path)
	{
		if( path== null)	return null;
		int extIndex= path.lastIndexOf( EXTENSION_SEPARATOR);
		if( extIndex== -1)		return path;
		int folderIndex= path.lastIndexOf( FOLDER_SEPARATOR);
		if( folderIndex> extIndex)	return path;
		return path.substring( 0, extIndex);
	}

	/**
	 * 전달된 상대경로 <code>relativePath</code>에 절대경로 <code>path</code>를 적용한다. 
	 * <p>예)
	 * <blockquote><pre>
	 * String path= StringUtils.applyRelativePath( "/home/Documents/rout.out", "dump/rout.in")
	 * // 결과 문자열 : "/home/documents/dump/rout.in"
	 * </pre></blockquote>
	 * </p>
	 * @param path 절대경로
	 * @param relativePath 상태경로
	 * @return 완성된 절대경로
	 */
	public static String applyRelativePath( String path, String relativePath)
	{
		if( path== null || relativePath== null) return null;
		int separatorIndex= path.lastIndexOf( FOLDER_SEPARATOR);
		if( separatorIndex!= -1)
		{
			String newPath= path.substring( 0, separatorIndex);
			if( !relativePath.startsWith( FOLDER_SEPARATOR))
				newPath+= FOLDER_SEPARATOR;
			return newPath+ relativePath;
		}
		return relativePath;
	}
	
	/**
	 * 전달된 경로 <code>path</code>에 포함된 상대위치를 표현하기 위한 구분자{'..', '.')들을 반영하여 접근가능한 형태의 경로로 변경한다.
	 * <p>예)
	 * <blockquote><pre>
	 * String path= StringUtils.cleanPath( "file://home/product/Documents/resources/../sub/rout.in");
	 * // 결과 문자열 : "file://home/product/Documents/sub/rout.in"
	 * 
	 * String path= StringUtils.cleanPath( "file://home/product/Documents/sub/./rout.in");
	 * // 결과 문자열 : "file://home/product/Documents/sub/rout.in" 
	 * </pre></blockquote>
	 * </p> 
	 * @param path 적용할 경로, 윈도우즈 운영체제에서 사용하는 경로구분 문자 '\'는 '/'로 대체된다. 
	 * @return 변경된 경로
	 */
	public static String cleanPath( String path)
	{
		if( path== null)	return null;
		String pathToUse= replace( path, WINDOWS_FOLDER_SEPARATOR, FOLDER_SEPARATOR);
		
		int prefixIndex= pathToUse.indexOf( ":");
		String prefix= "";
		if( prefixIndex!= -1)
		{
			prefix= pathToUse.substring( 0, prefixIndex+ 1);
			pathToUse= pathToUse.substring( prefixIndex+ 1);
		}
		
		if( pathToUse.startsWith( FOLDER_SEPARATOR))
		{
			prefix= prefix+ FOLDER_SEPARATOR;
			pathToUse= pathToUse.substring( 1);
		}
		
		String[] pathArray= delimitedListToStringArray( pathToUse, FOLDER_SEPARATOR);
		List<String> pathElements= new LinkedList<String>();
		int tops= 0;
		
		for( int i= pathArray.length- 1; i>= 0; i--)
		{
			String element= pathArray[i];
			if( CURRENT_PATH.equals( element))
				;
			else if( TOP_PATH.equals( element))
				tops++;
			else
			{
				if( tops> 0)
					tops--;
				else
					pathElements.add( 0, element);
			}
		}
		
		for( int i= 0; i< tops; i++)
			pathElements.add( 0, TOP_PATH);
		
		return prefix+ collectionToDelimitedString( pathElements, FOLDER_SEPARATOR);
	}
	
	/**
	 * 전달된 경로들 <code>path</code>, <code>target</code>이 실제로 같은 위치를 의미하는지 판단한다.
	 * @param path 전달 경로
	 * @param target 비교대상 경로
	 * @return 전달 경로<code>path</code>가 비교대상 경로<code>target</code>가 같은 위치를 의미하는지 여부
	 * @see #cleanPath(String)
	 */
	public static boolean pathEquals( String path, String target)
	{
		return cleanPath( path).equals( cleanPath( target));
	}
	
	/**
	 * 전달된 로케일문자열 <code>localeString</code>을 적용하여 {@link Locale}을 생성한다.
	 * <p>예)
	 * <blockquote><pre>
	 * Locale locale= StringUtils.parseLocaleString( "en_US_WIN");
	 * String language= locale.getLanguage();
	 * String country= locale.getCountry();
	 * String variant= locale.getVariant();
	 * 
	 * // 결과 language : en
	 * // 결과 country : US
	 * // 결과 variant : WIN
	 * </pre></blockquote>
	 * </p> 
	 * @param localeString 로케일 문자열, 
	 * Locale 생성을 위한 language, country, variant 구분 문자로 '_' 또는 ' '(공백문자)를 사용할 수 있다.
	 * @return 생성된 <code>Locale</code> 객체
	 * @see Locale#Locale(String, String, String)
	 */
	public static Locale parseLocaleString( String localeString)
	{
		for( int i= 0; i< localeString.length(); i++)
		{
			char ch= localeString.charAt( i);
			if( ch!= '_' && ch!= ' ' && !Character.isLetterOrDigit( ch))
				throw new IllegalArgumentException( "Locale value \""+ localeString+ "\" contains invalid characters.");
		}
		
		String[] parts= tokenizeToStringArray( localeString, "_ ", false, false);
		String language= (parts.length> 0 ? parts[0] : "");
		String country= (parts.length> 1 ? parts[1] : "");
		String variant= "";
		if( parts.length>= 2)
		{
			int endIndexOfCountryCode= localeString.indexOf( country)+ country.length();
			variant= trimLeadingWhitespace( localeString.substring( endIndexOfCountryCode));
			if( variant.startsWith( "_"))
				variant= trimLeadingCharacter( variant, '_');
		}
		return language.length()> 0 ? new Locale( language, country, variant) : null;
	}

	/**
	 * 전달된 <code>locale</code>에 대응되는 RFC 3066 호환 language tag 문자열을 생성한다..
	 * @param locale 로케일
	 * @return RFC 3066 호환 language tag 문자열
	 */
	public static String toLanguageTag( Locale locale)
	{
		return locale.getLanguage()+ ( hasText( locale.getCountry()) ? "-"+ locale.getCountry() : "");
	}
	
	/**
	 * 전달된 원본 문자열 배열 <code>array</code>에 추가문자열 <code>str</code>을 추가한다.
	 * @param array 원본 문자열 배열
	 * @param str 추가할 문자열
	 * @return 원본 문자열 배열에 추가할 문자열이 추가된 문자열 배열
	 */
	public static String[] addStringToArray( String[] array, String str)
	{
		if( isEmpty( array))	return new String[]{ str};
		String[] newArr= new String[array.length+ 1];
		System.arraycopy( array, 0, newArr, 0, array.length);
		newArr[array.length]= str;
		return newArr;
	}
	
	/**
	 * 전달된 원본 문자열 배열 <code>array1</code>에 추가 문자열 배열 <code>array2</code>을 추가한다.
	 * @param array1 원본 문자열 배열
	 * @param array2 추가할 문자열 배열
	 * @return 원본 문자열 배열에 추가할 문자열 배열이 추가된 문자열, 
	 * 원본 문자열 배열과 추가할 문자열 배열이 모두 <code>null</code>인 경우 <code>null</code>이 리턴
	 */
	public static String[] concatenateStringArrays( String[] array1, String[] array2)
	{
		if( isEmpty( array1))		return array2;
		if( isEmpty( array2))		return array1;
		String[] newArr= new String[array1.length+ array2.length];
		System.arraycopy( array1, 0, newArr, 0, array1.length);
		System.arraycopy( array2, 0, newArr, array1.length, array2.length);
		return newArr;
	}

	/**
	 * 전달된 원분 문자열 배열 <code>array1</code>에 병합할 문자열 배열 <code>array2</code>를 병합한다. 
	 * 내용이 같은 문자열은 중복되지 않는다. 병합된 문자열 배열의 순서는 전달된 상태로 유지된다.
	 * @param array1 원본 문자열 배열
	 * @param array2 병합할 문자열 배열
	 * @return 원본 문자열 배열에 병합할 문자열 배열이 병합된 문자열, 
	 * 원본 문자열 배열과 병합할 문자열 배열이 모두 <code>null</code>인 경우 <code>null</code>이 리턴
	 */
	public static String[] mergeStringArrays( String[] array1, String[] array2)
	{
		if( isEmpty( array1))		return array2;
		if( isEmpty( array2))		return array1;
		List<String> result= new ArrayList<String>();
		result.addAll( Arrays.asList( array1));
		for( String str: array2)
		{
			if( !result.contains( str))		result.add( str);
		}
		return toStringArray( result);
	}
	
	/**
	 * 전달된 문자열 배열 <code>array</code>을 정렬한다.(modified mergesort) 
	 * @param array 정렬할 문자열 배열
	 * @return 정렬된 문자열 배열
	 */
	public static String[] sortStringArray( String[] array)
	{
		if( isEmpty( array))	return new String[0];
		Arrays.sort( array);
		return array;
	}
	
    /**
     * 전달된 문자열 배열 <code>src</code>의 특정 범위 <code>left</code> ~ <code>right</code>를 오름차순 또는 내림차순으로 
     * 정렬한다.(Quick Sort)
     * <h4> Example </h4>
     * <blockquote><pre>
     * String src[] = {"6","5","3","1","7","9","2","4"};
     * System.out.println("source = " + StringUtil.arrayToCommaDelimitedString(src));
     * StringUtil.sort(src, 0, src.length-1, true);
     * System.out.println("result = " + StringUtil.arrayToCommaDelimitedString(src));
     * 위 결과는 다음과 같다. 
     * source = 6,5,3,1,7,9,2,4
     * result = 1,2,3,4,5,6,7,9</pre></blockquote>
     * @param src 정렬하고자 하는 대상 String array
     * @param left 정렬하려고 하는 범위의 시작 위치
     * @param right 정렬하려고 하는 범위의 끝 위치
     * @param flag 'true'이면 사전순 정렬이 되고, 'false'이면 역순이 된다.
     * @return sorting 된 결과
     */
    @SuppressWarnings( { "unchecked", "rawtypes" })
	public static String[] sort( String src[], int left, int right, boolean flag)
    {
        if( src== null) return src;
        if( left< 0) left= 0;
        if( right>= src.length) right= src.length- 1;
        Stack stack= new Stack();
        SortNode sortnode= new SortNode( left, right);
        stack.push( sortnode);
        //boolean flag1= false;
        if( flag)
            do
            {
                if( stack.empty()) break;
                SortNode sortnode1= (SortNode)stack.pop();
                int k1= sortnode1.getLeft();
                int i2= sortnode1.getRight();
                int k= rand( k1, i2);
                swap( src, k1, k);
                int i1= k1;
                for( int k2= k1+ 1; k2<= i2; k2++)
                    if( src[k2].compareToIgnoreCase( src[k1])< 0) swap( src, ++i1, k2);

                swap( src, k1, i1);
                if( i1< i2) stack.push( new SortNode( i1+ 1, i2));
                if( i1> k1) stack.push( new SortNode( k1, i1- 1));
            }
            while( true);
        else
            do
            {
                if( stack.empty()) break;
                SortNode sortnode2= (SortNode)stack.pop();
                int l1= sortnode2.getLeft();
                int j2= sortnode2.getRight();
                int l= rand( l1, j2);
                swap( src, l1, l);
                int j1= l1;
                for( int l2= l1+ 1; l2<= j2; l2++)
                    if( src[l2].compareToIgnoreCase( src[l1])> 0) swap( src, ++j1, l2);

                swap( src, l1, j1);
                if( j1< j2) stack.push( new SortNode( j1+ 1, j2));
                if( j1> l1) stack.push( new SortNode( l1, j1- 1));
            }
            while( true);
        return src;
    }
	
	/**
	 * 전달된 Collection <code>collection</code>을 문자열 배열로 변환한다. 
	 * @param collection 변환할 Collection 객체
	 * @return 변환된 문자열 배열, 
	 * 변환할 Collection 객체 <code>collection</code>가 <code>null</code>인 경우 <code>null</code>을 리턴
	 */
	public static String[] toStringArray( Collection<String> collection)
	{
		if( collection== null)	return null;
		return collection.toArray( new String[collection.size()]);
	}
	
	/**
	 * 전달된 Enumeration <code>enumeration</code>을 문자열 배열로 변환한다.
	 * @param enumeration 변환할 Enumeration 객체
	 * @return 변환된 문자열 배열, 
	 * 변환할 Enumeration 객체 <code>enumeration</code>가 <code>null</code>인 경우 <code>null</code>을 리턴
	 */
	public static String[] toStringArray( Enumeration<String> enumeration)
	{
		if( enumeration== null)	return null;
		List<String> list= Collections.list( enumeration);
		return list.toArray( new String[list.size()]);
	}
	
	/**
	 * 전달된 문자열 배열 <code>array</code>의 구성요소(문자열)의 공백을 모두 제거한다.
	 * @param array 원본 문자열 배열
	 * @return 구성요소의 공백이 모두 제거된 문자열 배열, 
	 * 전달된 원본 문자열 배열이 <code>null</code>이거나 내용이 없는 경우 길이가 0인 문자열 배열이 리턴 
	 */
	public static String[] trimArrayElements( String[] array)
	{
		if( isEmpty( array))	return new String[0];
		String[] result= new String[array.length];
		for( int i= 0; i< array.length; i++)
		{
			String element= array[i];
			result[i]= ( element!= null ? element.trim() : null);
		}
		return result;
	}
	
	/**
	 * 전달된 문자열 배열 <code>array</code>에서 중복된 구성요소(문자열)들을 하나만 남기고 제거한다.
	 * @param array 원본 문자열 배열
	 * @return 중복이 제거된 문자열 배열
	 * 전달된 원본 문자열 배열이 <code>null</code>이거나 내용이 없는 경우 길이가 0인 문자열 배열이 리턴
	 */
	public static String[] removeDuplicateStrings( String[] array)
	{
		if( isEmpty( array))	return array;
		Set<String> set= new TreeSet<String>();
		for( String element: set)
			set.add( element);
		return toStringArray( set);
	}
	
	/**
	 * 전달된 원본 문자열 <code>toSplit</code>를 기준 문자열 <code>delimiter</code>를 기준으로 앞, 뒤로 분할하여 길이가 2인 
	 * 문자열 배열로 변환한다.
	 * <p>
	 * <blockquote><pre>
	 * String[] splitted= StringUtils.split( "FWC001SVCIn", "SVC");
	 * // 결과 splitted[0] : "FWC001"
	 * // 결과 splitted[1] : "In"
	 * </pre></blockquote>
	 * </p>
	 * @param toSplit 원본 문자열
	 * @param delimiter 원본 문자열을 나눌 기준 문자열
	 * @return 원본 문자열에서 기준 문자열 앞, 뒤로 분할된 길이가 2인 문자열 배열
	 * 원본 문자열 또는 기준 문자열이 <code>null</code>이거나 길이가 0인 경우 <code>null</code>을 리턴
	 * @see #hasLength(String)
	 */
	public static String[] split( String toSplit, String delimiter)
	{
		if( !hasLength( toSplit) || !hasLength( delimiter))		return null;
		int offset= toSplit.indexOf( delimiter);
		if( offset< 0)	return null;
		String beforeDelimiter= toSplit.substring( 0, offset);
		String afterDelimiter= toSplit.substring( offset+ delimiter.length());
		return new String[]{ beforeDelimiter, afterDelimiter};
	}
	
	/**
	 * 전달된 문자열 배열 <code>array</code>의 구성문자열들을 기준 문자열 <code>delimiter</code>을 분할하여 
	 * <code>Properties</code> 객체를 생성한다. 분할된 문자열의 첫번째 문자열이 프로퍼티의 key로 사용되며 두번째 문자열이 프로퍼티의
	 * value로 사용된다. 
	 * @param array 원본 문자열 배열
	 * @param delimiter 분할 기준 문자열
	 * @return 원본 문자열 배열과 분할 기준 문자열을 이용하여 생성된 <code>Properties</code> 객체, 
	 * 원본 문자열 배열이 <code>null</code>이거나 길이가 0인 경우 <code>null</code>을 리턴
	 */
	public static Properties splitArrayElementsIntoProperties( String[] array, String delimiter)
	{
		return splitArrayElementsIntoProperties( array, delimiter, null);
	}
	
	/**
	 * 전달된 문자열 배열 <code>array</code>의 구성문자열들을 기준 문자열 <code>delimiter</code>을 분할하여 
	 * <code>Properties</code> 객체를 생성한다. 
	 * 분할된 문자열의 첫번째 문자열이 프로퍼티의 key로 사용되며 두번째 문자열이 프로퍼티의 value로 사용된다.
	 * 프로퍼티의 key와 value에 사용되는 문자열에서 제거할 문자 집합 <code>charsToDelete</code>에 포함된 문자는 모두 제거된다. 
	 * @param array 원본 문자열 배열
	 * @param delimiter 분할 기준 문자열
	 * @param charsToDelete 제거할 문자 집합
	 * @return 원본 문자열 배열과 분할 기준 문자열을 이용하여 생성된 <code>Properties</code> 객체, 
	 * 원본 문자열 배열이 <code>null</code>이거나 길이가 0인 경우 <code>null</code>을 리턴
	 */
	public static Properties splitArrayElementsIntoProperties( String[] array, String delimiter, String charsToDelete)
	{
		if( isEmpty( array))	return null;
		Properties result= new Properties();
		for( String element: array)
		{
			if( charsToDelete== null)	element= deleteAny( element, charsToDelete);
			String[] splittedElement= split( element, delimiter);
			if( splittedElement== null)	continue;
			result.setProperty( splittedElement[0].trim(), splittedElement[1].trim());
		}	
		return result;
	}
	
	/**
	 * <p>
	 * 전달된 문자열 <code>str</code>을 <code>java.util.StringTokenizer</code>를 이용하여 분할(tokenize)한다. 
	 * 기준 문자 집합 <code>delimiter</code>에 포한된 모든 문자는 문자 하나하나가 token을 나누는 기준으로 사용된다.
	 * <code>delimiter</code> 문자열 전체를 이용하여 문자열의 분할하기 위해서는 <code>delimitedListToStringArray</code>를 사용한다.
	 * 분할된 문자열들은 공백이 제거된다.
	 * 길이가 0인 문자열은 결과 배열에 포함되지 않는다.
	 * <blockquote><pre>
	 * String[] splitted= StringUtils.tokenizeToStringArray( "FWC001SVCIn", "SVC");
	 * // 결과 문자열 배열 : { "FW", "001", "In"}
	 * </pre></blockquote>
	 *  </p>
	 * @param str 원본 문자열
	 * @param delimiter 분할 기준 문자 집합
	 * @return 분할된 문자열 배열
	 * @see java.util.StringTokenizer
	 * @see java.lang.String#trim()
	 * @see #delimitedListToStringArray 
	 */
	public static String[] tokenizeToStringArray( String str, String delimiter)
	{
		return tokenizeToStringArray( str, delimiter, true, true);
	}
	
	/**
	 * <p>
	 * 전달된 문자열 <code>str</code>을 <code>java.util.StringTokenizer</code>를 이용하여 분할(tokenize)한다. 
	 * 기준 문자 집합 <code>delimiter</code>에 포한된 모든 문자는 문자 하나하나가 token을 나누는 기준으로 사용된다.
	 * <code>delimiter</code> 문자열 전체를 이용하여 문자열의 분할하기 위해서는 <code>delimitedListToStringArray</code>를 사용한다.
	 *  분할된 문자열들은 공백이 제거된다.
	 * <code>ignoreEmptyTokens</code>가 true인 경우 길이가 0인 문자열도 결과 배열에 포함된다. 
	 * <blockquote><pre>
	 * 예)
	 * String[] splitted= StringUtils.tokenizeToStringArray( "FWC001SVCIn", "SVC");
	 * // 결과 문자열 배열 : { "FW", "001", "In"}
	 * </pre></blockquote>
	 *  </p>
	 * @param str 원본 문자열
	 * @param delimiter 분할 기준 문자 집합
	 * @param ignoreEmptyTokens 길이가 0인 문자열을 결과 배열에 포함할 지 여부
	 * @return 분할(tokenize)된 문자열 배열
	 * @see java.util.StringTokenizer
	 * @see java.lang.String#trim()
	 * @see #delimitedListToStringArray 
	 */
	public static String[] tokenizeToStringArray( String str, String delimiter, boolean trimTokens, boolean ignoreEmptyTokens)
	{
		if( str== null)	return null;
		StringTokenizer st= new StringTokenizer( str, delimiter);
		List<String> tokens= new ArrayList<String>();
		while( st.hasMoreTokens())
		{
			String token= st.nextToken();
			if( trimTokens)	token= token.trim();
			if( !ignoreEmptyTokens || token.length()> 0)		tokens.add( token);
		}
		return toStringArray( tokens);
	}
	
	/**
	 * 전달된 문자열 <code>str</code>를 기준 문자열 <code>delimiter</code>를 기준으로 분할한다.
	 * <p>예)
	 * <blockquote><pre>
	 * 예)
	 * String[] splitted= StringUtils.delimitedListToStringArray( "FWC001SVCIn", "SVC");
	 * // 결과 문자열 배열 : { "FWC001", "In"}
	 * </pre></blockquote>
	 *  </p>
	 * @param str 원본 문자열
	 * @param delimiter 분할 기준 문자열
	 * @return 기준 문자열로 분할된 문자열 배열
	 */
	public static String[] delimitedListToStringArray( String str, String delimiter)
	{
		return delimitedListToStringArray( str, delimiter, null);
	}

	/**
	 * 전달된 문자열 <code>str</code>를 기준 문자열 <code>delimiter</code>를 기준으로 분할하고 문자열에서 제거할 문자 집합 
	 * <code>charsToDelete</code>에 포함된 모든 문자를 제거한다.
	 * @param str 원본 문자열
	 * @param delimiter 분할 기준 문자열
	 * @param charsToDelete 제거할 문자 집합
	 * @return 기준 문자열로 분할된 문자열 배열
	 * @see #deleteAny(String, String)
	 */
	public static String[] delimitedListToStringArray( String str, String delimiter, String charsToDelete)
	{
		if( str== null)				return new String[0];
		if( delimiter== null)		return new String[]{ str};
		List<String> result= new ArrayList<String>();
		if( "".equals( delimiter))
		{
			for( int i= 0; i< str.length(); i++)
				result.add( deleteAny( str.substring( i, i+ 1), charsToDelete));
		}
		else
		{
			int pos= 0;
			int delPos;
			while( ( delPos= str.indexOf( delimiter, pos))!= -1)
			{
				result.add( deleteAny( str.substring( pos, delPos), charsToDelete));
				pos= delPos+ delimiter.length();
			}
			if( str.length()> 0 && pos<= str.length())
				result.add(  deleteAny( str.substring( pos), charsToDelete));
		}
		return toStringArray( result);
	}
	
	/**
	 * 전달된 문자열 <code>str</code>을 반점(',')이용하여 분할한다 - CSV Separate.
	 * @param str 원본 문자열
	 * @return 분할된 문자열 배열
	 */
	public static String[] commaDelimitedListToStringArray( String str)
	{
		return delimitedListToStringArray( str, ",");
	}
	
	/**
	 * 전달된 문자열 <code>str</code>을 반점(',')을 이용하여 분할(CSV separating)하여 <code>java.util.Set</code>로 변환한다.
	 * <code>Set</code>의 특성상 중복이 허용되지 않는다.
	 * @param str 원본 문자열
	 * @return 분할된 문자열 집합
	 */
	public static Set<String> commaDelimitedListToSet( String str)
	{
		Set<String> set= new TreeSet<String>();
		String[] tokens= commaDelimitedListToStringArray( str);
		for( String token: tokens)
			set.add( token);
		return set;
	}
	
	/**
	 * 전달된 <code>Collection</code>객체 <code>coll</code>의 구성요소를 구분 문자열 <code>delim</code>로 연결된 단일 문자열로 
	 * 변환하고 각 구성요소는  앞 부분에는 접두어 <code>prefix</code>, 뒷 부분에는 접미어 <code>suffix</code>를 추가한다. 
	 * @param coll 원본 집합
	 * @param delim 구분 문자열
	 * @param prefix 접두어
	 * @param suffix 접미어
	 * @return 변환된 단일 문자열, <code>coll</code>이 <code>null</code>이거나 길이가 0인 경우 빈문자열("")을 리턴
	 */
	@SuppressWarnings( "rawtypes")
	public static String collectionToDelimitedString( Collection coll, String delim, String prefix, String suffix)
	{
		if( isEmpty( coll))		return "";
		StringBuilder sb= new StringBuilder();
		Iterator it= coll.iterator();
		while( it.hasNext())
		{
			sb.append( prefix).append( it.next()).append( suffix);
			if( it.hasNext())	sb.append( delim);
		}
		return sb.toString();
	}
	
	/**
	 * 전달된 <code>Collection</code>객체 <code>coll</code>의 구성요소를 구분 문자열 <code>delim</code>로 연결된 단일 문자열로 
	 * 변환한다. 
	 * @param coll 원본 집합
	 * @param delim 구분 문자열
	 * @return 변환된 단일 문자열, <code>coll</code>이 <code>null</code>이거나 길이가 0인 경우 빈문자열("")을 리턴
	 */
	@SuppressWarnings( "rawtypes")
	public static String collectionToDelimitedString( Collection coll, String delim)
	{
		return collectionToDelimitedString( coll, delim, "", "");
	}
	
	/**
	 * 전달된 <code>Collection</code>객체 <code>coll</code>의 구성요소를 구분 문자열 반점(',')으로 연결된 단일 문자열로 변환한다. 
	 * @param coll 원본 집합
	 * @return 변환된 단일 문자열, <code>coll</code>이 <code>null</code>이거나 길이가 0인 경우 빈문자열("")을 리턴
	 */
	@SuppressWarnings( "rawtypes")
	public static String collectionToCommaDelimitedString( Collection coll)
	{
		return collectionToDelimitedString( coll, ",");
	}
	
	/**
	 * 전달된 문자열 배열 <code>arr</code>의 구성요소를 구분 문자열 <code>delim</code>로 연결된 단일 문자열로 변환한다. 
	 * @param arr 원본 문자열 배열
	 * @return 변환된 단일 문자열, <code>arr</code>이 <code>null</code>이거나 길이가 0인 경우 빈문자열("")을 리턴
	 */
	public static String arrayToDelimitedString( Object[] arr, String delim)
	{
		if( isEmpty( arr))		return "";
		if( arr.length== 1)		return nullSafeToString( arr[0]);
		StringBuilder sb= new StringBuilder();
		for( int i= 0; i< arr.length; i++)
		{
			if( i> 0)		sb.append( delim);
			sb.append( arr[i]);
		}
		return sb.toString();
	}
	
	/**
	 * 전달된 문자열 배열 <code>arr</code>의 구성요소를 구분 문자열 반점(',')으로 연결된 단일 문자열로 변환한다. 
	 * @param arr 원본 문자열 배열
	 * @return 변환된 단일 문자열, <code>arr</code>이 <code>null</code>이거나 길이가 0인 경우 빈문자열("")을 리턴
	 */
	public static String arrayToCommaDelimitedString( String[] arr)
	{
		return arrayToDelimitedString( arr, ",");
	}
	
    /**
     * 전달된 문자가 한글문자인지 판단한다.
     * @param inputChar 판단할 문자
     * @return 한글문자 인지 여부
     */
    public static boolean isHangul( char inputChar)
    {
        String unicodeBlock= Character.UnicodeBlock.of( inputChar).toString();
        return ( "HANGUL_JAMO".equals( unicodeBlock)
        		|| "HANGUL_SYLLABLES".equals( unicodeBlock)
        		|| "HANGUL_COMPATIBILITY_JAMO".equals( unicodeBlock)) ? true : false;
    }

    /**
     * <code>full</code>이 <code>true</code>인 경우  전달된 문자열 전체가 한글문자로 구성되어있는지 판단하고, 
     * <code>false</code>인 경우 한글문자를 포함하고 있는지 판단한다.
     * @param inString 검사할 문자열
     * @param full 전체 검사 여부
     * @return 한글 문자열 여부
     * <code>full</code>이 <code>true</code>인 경우 모든 글자가 한글이어야 <code>true</code>를 리턴
     * <code>false</code>인 경우 한글자만 한글이면 <code>true</code>를 리턴
     * <code>inString</code>이 <code>null</code>이거나 길이가 0인 경우 <code>false</code>를 리턴
     * @see #hasLength(String)
     */
    public static boolean isHangul( String inString, boolean full)
    {
    	if( !hasLength( inString)) return false;
        char[] chars= inString.toCharArray();

        if( ! full)
        {
            for( int i= 0; i< chars.length; i++)
            {
                if( isHangul( chars[i])) return true;
            }
            return false;
        }

        for( int i= 0; i< chars.length; i++)
        {
            if( ! isHangul( chars[i])){ return false; }
        }
        return true;
    }

    /**
     * 전달된 문자열 <code>inString</code>이 숫자, 부호 등이 아닌 문자로만 구성되어 있는지 확인한다.
     * <p>예)
     * <blockquote><pre>
     * boolean isLetter= StringUtils.isLetter( "FWC");
     * // 결과 : true
     * boolean isLetter= StringUtils.isLetter( "FWC001");
     * // 결과 : false
     * boolean isLetter= StringUtils.isLetter( "FWC-SVC");
     * // 결과 : false  
     * </pre></blockquote>
     * @param inString 검사할 문자열
     * @return 문자로만 구성되어 있는지 여부, <code>inString</code>이 <code>null</code>인 경우 <code>false</code>리턴
     */
    public static boolean isLetter( String inString)
    {
        if( inString== null)
        	return false;

        char[] chars= inString.toCharArray();
        for( int i= 0; i< chars.length; i++)
        {
            if( ! Character.isLetter( chars[i])){ return false; }
        }
        return true;
    }

    /**
     * 전달된 문자열 <code>inString</code>이 문자, 부호 등이 아닌 숫자로만 구성되어 있는지 확인한다. 
     * <p>예)
     * <blockquote><pre>
     * boolean isDigit= StringUtils.isDigit( "001");
     * // 결과 : true
     * boolean isDigit= StringUtils.isDigit( "FW001");
     * // 결과 : false
     * boolean isDigit= StringUtils.isDigit( "02-001");
     * // 결과 : false  
     * </pre></blockquote>
     * @param inString 검사할 문자열
     * @return 숫자로만 구성되어 있는지 여부, <code>inString</code>이 <code>null</code>인 경우 <code>false</code>리턴
     */
    public static boolean isDigit( String inString)
    {
        if( inString== null)
        	return false;

        char[] chars= inString.toCharArray();
        for( int i= 0; i< chars.length; i++)
        {
            if( ! Character.isDigit( chars[i])){ return false; }
        }
        return true;
    }

    /**
     * 전달된 문자열 <code>inString</code>이 부호 등이 아닌 문자 또는 숫자로만 구성되어 있는지 확인한다. 
     * <p>예)
     * <blockquote><pre>
     * boolean isLetterOrDigit= StringUtils.isLetterOrDigit( "FWC001SVCIn");
     * // 결과 : true
     * boolean isLetterOrDigit= StringUtils.isLetterOrDigit( "FWC-001SVCIn");
     * // 결과 : false
     * </pre></blockquote>
     * @param inString 검사할 문자열
     * @return 문자 또는 숫자로만 구성되어 있는지 여부, <code>inString</code>이 <code>null</code>인 경우 <code>false</code>리턴
     */
    public static boolean isLetterOrDigit( String inString)
    {
        if( inString== null)
        	return false;

        char[] chars= inString.toCharArray();
        for( int i= 0; i< chars.length; i++)
        {
            if( ! Character.isLetterOrDigit( chars[i])){ return false; }
        }
        return true;
    }

    /**
     * 전달된 객체 <code>obj</code>가 <code>null</code>인 경우 이를 변환 객체<code>rvalue</code>로 변환한다.
     * @param obj 원본 객체
     * @param rvalue 변환 객체
     * @return 원본 객체 <code>obj</code>가 <code>null</code>인 경우 변환된 객체, 
     * <code>rvalue</code>가 <code>null</code>일 경우 <code>null</code>리턴
     */
    public static Object nvl( Object obj, Object rvalue)
    {
        return obj== null ? rvalue : obj;
    }

    /**
     * 전달된 문자열 <code>str</code>이 <code>null</code>인 경우 이를 변환 문자열<code>rStr</code>로 변환한다.
     * @param str 원본 문자열
     * @param rStr 변환 문자열
     * @return 원본 문자열 <code>str</code>이 <code>null</code>인 경우 변환된 문자열,
     * <code>rStr</code>이 <code>null</code>일 경우 <code>null</code>리턴
     */
    public static String nvl( String str, String rStr)
    {
        return str== null ? rStr : str;
    }

    /**
     * 전달된 문자열 <code>str</code>이 <code>null</code>인 경우 이를 빈 문자열("")로 변환한다.
     * @param str 원본 문자열
     * @return 원본 문자열 <code>str</code>이 <code>null</code>인 경우 변환된 빈 문자열
     */
    public static String nvl(String str)
    {
        return str == null ? "" : str;
    }

    /**
     * 전달된 문자열 <code>string</code>이 특정 패턴에 부합하는지를 검사한다.
     * <pre>
     * 검사 패턴 종류  
     *   - StringUtils.IP_ADDRESS			: IP 주소 
     *   - StringUtils.EMAIL_ADDRESS	: 이메일 주소
     *   - StringUtils.WEB_ADDRESS		: web 주소
     *   - StringUtils.ZIP_CODE				: 우편 번호
     *   - StringUtils.SS_NO					: 주민 등록 번호
     *   - StringUtils.SS_SNO				: 주민 등록 번호(연속유형)
     *   - StringUtils.ER_NO					: 사업자 등록 번호
     *   - StringUtils.TEL_NO				: 전화 번호
     * </pre>      
     * <p>예)
     * <blockquote><pre>
     * StringUtils.isFormattedString( "123-456", Global.ZIP_CODE);
     * // 결과 : true
     * </pre></blockquote>
     * </p>
     * @param string 검사할 문자열
     * @param patternId 패턴 종류
     * @return 패턴에 부합하는지 여부, 패턴이 검사 패턴 종류에 포함되지 않은 경우 <code>false</code> 리턴
     */
    public static boolean isFormattedString( String string, int patternId)
    {
        switch( patternId)
        {
        	case IP_ADDRESS:
            	return isFormattedString( string, IP_ADDRESS_PATTERN);
        	case EMAIL_ADDRESS:
            	return isFormattedString( string, EMAIL_ADDRESS_PATTERN);
        	case WEB_ADDRESS:
            	return isFormattedString( string, WEB_ADDRESS_PATTERN);
        	case ZIP_CODE:
            	return isFormattedString( string, ZIP_CODE_PATTERN);
        	case SS_NO:
            	return isFormattedString( string, SSNO_PATTERN);
        	case ER_NO:
            	return isFormattedString( string, ERNO_PATTERN);
        	case TEL_NO:
            	return isFormattedString( string, TELNO_PATTERN);
        	case SS_SNO:
        		return isFormattedString( string, SSSNO_PATTERN);
        	default:
            	return false;
        }
    }

    /**
     * 전달된 문자열 <code>string</code>이 원하는 패턴 포맷에 부합하는지를 검사한다.
     * <p>예)
     * <blockquote><pre>
     * StringUtils.isFormattedString( "123/456", "[0-9]{3}/[0-9]{3}");
     * // 결과 : true
     * </pre></blockquote>
     * @param string 검사할 문자열
     * @param pattern 패턴 포맷 문자열
     * @return 패턴에 부합하는지 여부. 
     * 검사할 문자열 <code>string</code> 또는 패턴 포맷 문자열 <code>pattern</code>이 <code>null</code> 인 경우 
     * <code>false</code> 리턴
     */
    public static boolean isFormattedString( String string, String pattern)
    {
        if( string== null || pattern== null)	return false;
        return string.matches( pattern);
    }

    /**
     * 전달된 문자열 <code>string</code>을 우편번호 패턴으로 변환한다.
     * @param string 원본 문자열
     * @return 우편번호 패턴으로 변환된 문자열, 
     * 원본 문자열 <code>string</code>이 <code>null</code>이거나 길이가 6자리가 아닌 경우 숫자로 구성된 문자열이 아닌 경우
     * <code>null</code> 리턴  
     */
    public static String toZipCodePattern( String string)
    {
        if( string== null) return "";

        if( string.length()!= 6|| ! isDigit( string)) return "";

        StringBuffer buffer= new StringBuffer();

        buffer.append( string.substring( 0, 3));
        buffer.append( '-');
        buffer.append( string.substring( 3, 6));

        return buffer.toString();
    }

    /**
     * 전달된 문자열 <code>string</code>을 사업자 등록번호 패턴으로 변환한다.
     * @param string 원본 문자열
     * @return 사업자 등록번호 패턴으로 변환된 문자열, 
     * 원본 문자열 <code>string</code>이 <code>null</code>이거나 길이가 10자리가 아닌 경우 숫자로 구성된 문자열이 아닌 경우
     * <code>null</code> 리턴  
     */
    public static String toErNoPattern( String string)
    {
        if( string== null){ return ""; }

        if( string.length()!= 10|| ! isDigit( string)){ return ""; }

        StringBuffer buffer= new StringBuffer();

        buffer.append( string.substring( 0, 3));
        buffer.append( '-');
        buffer.append( string.substring( 3, 5));
        buffer.append( '-');
        buffer.append( string.substring( 5, 10));

        return buffer.toString();
    }

    /**
     * 전달된 문자열 <code>string</code>을 주민등록번호 패턴으로 변환한다.
     * @param string 원본 문자열
     * @return 주민등록번호 패턴으로 변환된 문자열, 
     * 원본 문자열 <code>string</code>이 <code>null</code>이거나 길이가 13자리가 아닌 경우 숫자로 구성된 문자열이 아닌 경우
     * <code>null</code> 리턴  
     */
    public static String toSsNoPattern( String string)
    {
        if( string== null){ return ""; }

        if( string.length()!= 13|| ! isDigit( string)){ return ""; }

        StringBuffer buffer= new StringBuffer();

        buffer.append( string.substring( 0, 6));
        buffer.append( '-');
        buffer.append( string.substring( 6));

        return buffer.toString();
    }

    /**
     * 전달된 문자열 <code>string</code>에서 숫자가 아닌 문자, 문장 부호, 공백문자(" ") 등을 모두 제거한다.
     * @param string 원본 문자열
     * @return 숫자로만 이루어진 문자열, 원본 문자열이 <code>null</code>인 경우 빈 문자열 ("")을 리턴
     */
    public static String toPureDigit( String string)
    {
        if( string== null) return "";
        return string.replaceAll( "[^0-9]", "");
    }

    /**
     * 전달된 double value <code>dvalue</code>를 포맷을 적용하여 변환한다.
     * <p>포맷팅을 통해 표시되지 않는 숫자 부분은 반올림을 적용한다.</p>
     * <h4>Example 1</h4>
     *  <blockquote><pre>
     * StringUtils.numberFormat( 1994.379, "#,###.##");
     * //결과 : "1,994.38" 
     * StringUtils.numberFormat( 1994.30, "#,###.##");
     * //결과 : "1,994.3"
     * </pre></blockquote>
     * <h4> Example 2 - 유효자리 표시</h4>
     * <blockquote><pre>
     * StringUtils.numberFormat( -211994.378, "#,####.00");
     * // 결과 : -21,1994.38 
     * StringUtils.numberFormat( -211994.1, "#,####.00");
     * // 결과 :  -21,1994.10
     * </pre></blockquote>
     * 숫자 앞에 통화기호를 넣어 사용할 수도 있다. 다음의 Example 3은 다양한 통화기호를 이용하는 방법이다.
     * <h4>Example 3 - 통화 표시</h4>
     *<blockquote><pre>
     * StringUtils.numberFormat( 1215.33, "＄#,###.00");
     * StringUtils.numberFormat( -1215.33, "＄#,###.00");
     * StringUtils.numberFormat( 1215.33, "￦#,###.00");
     * StringUtils.numberFormat( -1215.33, "￦#,###.00");
     * StringUtils.numberFormat( 1215.33, "￡#,###.00");
     * StringUtils.numberFormat( -1215.33, "￡#,###.00");
     * StringUtils.numberFormat( 1215.33, "￥#,###.00");
     * StringUtils.numberFormat( -1215.33, "￥#,###.00");
     * 
     * //결과는 다음과 같다. 
     * //＄1,215.33
     * //-＄1,215.33
     * //￦1,215.33
     * //-￦1,215.33
     * //￡1,215.33
     * //-￡1,215.33
     * //￥1,215.33
     * //-￥1,215.33
     * </pre></blockquote>
     * 다음은 자릿수를 지정하여 %형을 표시하는 예제입니다.
     * <h4> Example 4 - 문자 포함 표시 </h4>
     * <blockquote><pre> 
     * StringUtils.numberFormat( 0.15334, "#,###.00%");
     * StringUtils.numberFormat( 0.153, "#,###.00%");
     * StringUtils.numberFormat( 0.153, "#,###.##%");
     * //결과는 다음과 같다. 
     * //15.33%
     * //15.30%
     * //15.3%
     * </pre></blockquote>
     * @param dvalue 변환 대상 숫자
     * @param format 변환 포맷 문자열 
     * @return 포맷이 적용되어 변환된 결과
     * @see DecimalFormat#format(double)
	 * @see <a href="http://java.sun.com/docs/books/tutorial/i18n/format/decimalFormat.html">Java Tutorial</a>
     */
    public static String numberFormat( double dvalue, String format)
    {
        DecimalFormat decimalformat= new DecimalFormat( format);
        return decimalformat.format( dvalue);
    }

    /**
     * 전달된 float value <code>fvalue</code>를 포맷을 적용하여 변환한다.
     * <p> 포맷팅을 통해 표시되지 않는 숫자 부분은 반올림을 적용한다.</p>
     * <h4>Example 1</h4>
     *  <blockquote><pre>
     * StringUtils.numberFormat( 1994.379, "#,###.##");
     * //결과 : "1,994.38" 
     * StringUtils.numberFormat( 1994.30, "#,###.##");
     * //결과 : "1,994.3"
     * </pre></blockquote>
     * <h4> Example 2 - 유효자리 표시</h4>
     * <blockquote><pre>
     * StringUtils.numberFormat( -211994.378, "#,####.00");
     * // 결과 : -21,1994.38 
     * StringUtils.numberFormat( -211994.1, "#,####.00");
     * // 결과 :  -21,1994.10
     * </pre></blockquote>
     * 숫자 앞에 통화기호를 넣어 사용할 수도 있다. 다음의 Example 3은 다양한 통화기호를 이용하는 방법이다.
     * <h4>Example 3 - 통화 표시</h4>
     *<blockquote><pre>
     * StringUtils.numberFormat( 1215.33, "＄#,###.00");
     * StringUtils.numberFormat( -1215.33, "＄#,###.00");
     * StringUtils.numberFormat( 1215.33, "￦#,###.00");
     * StringUtils.numberFormat( -1215.33, "￦#,###.00");
     * StringUtils.numberFormat( 1215.33, "￡#,###.00");
     * StringUtils.numberFormat( -1215.33, "￡#,###.00");
     * StringUtils.numberFormat( 1215.33, "￥#,###.00");
     * StringUtils.numberFormat( -1215.33, "￥#,###.00");
     * 
     * //결과는 다음과 같다. 
     * //＄1,215.33
     * //-＄1,215.33
     * //￦1,215.33
     * //-￦1,215.33
     * //￡1,215.33
     * //-￡1,215.33
     * //￥1,215.33
     * //-￥1,215.33
     * </pre></blockquote>
     * 다음은 자릿수를 지정하여 %형을 표시하는 예제입니다.
     * <h4> Example 4 - 문자 포함 표시 </h4>
     * <blockquote><pre> 
     * StringUtils.numberFormat( 0.15334, "#,###.00%");
     * StringUtils.numberFormat( 0.153, "#,###.00%");
     * StringUtils.numberFormat( 0.153, "#,###.##%");
     * //결과는 다음과 같다. 
     * //15.33%
     * //15.30%
     * //15.3%
     * </pre></blockquote>
     * @param dvalue 변환 대상 숫자
     * @param format 변환 포맷 문자열 
     * @return 포맷이 적용되어 변환된 결과
     * @see DecimalFormat#format(double)
	 * @see <a href="http://java.sun.com/docs/books/tutorial/i18n/format/decimalFormat.html">Java Tutorial</a>
     */
    public static String numberFormat( float fvalue, String format)
    {
        DecimalFormat decimalformat= new DecimalFormat( format);
        return decimalformat.format( fvalue);
    }

    /**
     * 전달된 long value <code>lvalue</code>를 포맷을 적용하여 변환한다.
     * <h4>Example 1</h4>
     *  <blockquote><pre>
     * StringUtils.numberFormat( 1994, "#,###");
     * //결과 : "1,994" 
     * </pre></blockquote>
     * @param lvalue 변환 대상 숫자
     * @param format 변환 포맷 문자열 
     * @return 포맷이 적용되어 변환된 결과
     * @see DecimalFormat#format(double)
	 * @see <a href="http://java.sun.com/docs/books/tutorial/i18n/format/decimalFormat.html">Java Tutorial</a>
     */
    public static String numberFormat( long lvalue, String format)
    {
        DecimalFormat decimalformat= new DecimalFormat( format);
        return decimalformat.format( lvalue);
    }

    /**
     * 전달된 int value <code>ivalue</code>를 포맷을 적용하여 변환한다.
     * <h4>Example 1</h4>
     *  <blockquote><pre>
     * StringUtils.numberFormat( 1994, "#,###");
     * //결과 : "1,994" 
     * </pre></blockquote>
     * @param lvalue 변환 대상 숫자
     * @param format 변환 포맷 문자열 
     * @return 포맷이 적용되어 변환된 결과
     * @see DecimalFormat#format(double)
	 * @see <a href="http://java.sun.com/docs/books/tutorial/i18n/format/decimalFormat.html">Java Tutorial</a>
     */
    public static String numberFormat( int ivalue, String format)
    {
        DecimalFormat decimalformat= new DecimalFormat( format);
        return decimalformat.format( ivalue);
    }

    /**
     * 전달된 short value <code>svalue</code>를 포맷을 적용하여 변환한다.
     * <h4>Example 1</h4>
     *  <blockquote><pre>
     * StringUtils.numberFormat( 1994, "#,###");
     * //결과 : "1,994" 
     * </pre></blockquote>
     * @param lvalue 변환 대상 숫자
     * @param format 변환 포맷 문자열 
     * @return 포맷이 적용되어 변환된 결과
     * @see DecimalFormat#format(double)
	 * @see <a href="http://java.sun.com/docs/books/tutorial/i18n/format/decimalFormat.html">Java Tutorial</a>
     */
    public static String numberFormat( short svalue, String format)
    {
        DecimalFormat decimalformat= new DecimalFormat( format);
        return decimalformat.format( svalue);
    }

    /**
     * 전달된 문자열 <code>string</code>이 공백 문자와 개행, 탭 으로만 구성되어 있는지 확인한다. 
     * trim의 결과로 길이가 0인 경우 공백으로만 구성되어 있는 것으로 간주한다.
     * @param string 검사할 문자열
     * @return 공백여부, 검사할 문자열 <code>string</code>이 <code>null</code>이면 <code>false</code> 리턴
     */
    public static boolean isSpace( String string)
    {
        if( string== null) return false;
        return string.trim().length()<= 0;
    }

    /**
     * 전달된 문자열 <code>str</code>의 왼쪽에 길이 <code>length</code> 보다 부족한 부분을 패딩문자열 <code>pad</code>로 채운다. 
     * 길이 <code>length</code>가 전달된 문자열 <code>str</code>의 길이 보다 작은 경우 길이만큼 문자열을 sub-string한다.  
     * <h4>  Example </h4>
     * <blockquote><pre> 
     * StringUtils.lpad( "abcdefg", 12, null);
     * StringUtils.lpad( null, 12, "123");
     * StringUtils.lpad( "abcdefg", 12, "123");
     * StringUtils.lpad( "abcdefg", 12, "1");
     * StringUtils.lpad( "abcdefg", 12, "123456890");
     * StringUtils.lpad( "abcdefg", 4, "123");
     * 위 결과는 다음과 같다.
     *      abcdefg
     * 123123123123
     * 12312abcdefg
     * 11111abcdefg
     * 12345abcdefg
     * abcd</pre></blockquote>
     * @param str 원본 문자열
     * @param length 생성하고자 하는 전체 길이
     * @param pad 패딩 문자열 - <code>null</code>인 경우 공백문자(' ')가 적용
     * @return String 길이에 맞게 왼쪽에 패딩문자열로 채운 문자열
     */
    public static String lpad( String str, int length, String pad)
    {
        if( str== null) str= "";
        if( pad== null) pad= " ";
        int j= str.length();
        if( j> length) return str.substring( 0, length);
        if( j== length) return str;
        int k= pad.length();
        int l= length- j;
        int i1= l/ k;
        int j1= l% k;
        StringBuffer stringbuffer= new StringBuffer();
        for( ; i1> 0; i1--)
            stringbuffer.append( pad);

        if( j1> 0) stringbuffer.append( pad.substring( 0, j1));
        stringbuffer.append( str);
        return stringbuffer.toString();
    }

    /**
     * 전달된 문자열 <code>str</code>의 오른쪽에 길이 <code>length</code> 보다 부족한 부분을 패딩문자열 <code>pad</code>로 채운다. 
     * 길이 <code>length</code>가 전달된 문자열 <code>str</code>의 길이 보다 작은 경우 길이만큼 문자열을 sub-string한다.  
     * <h4>  Example </h4>
     * <blockquote><pre> 
     * StringUtils.rpad( "abcdefg", 12, null);
     * StringUtils.rpad( null, 12, "123");
     * StringUtils.rpad( "abcdefg", 12, "123");
     * StringUtils.rpad( "abcdefg", 12, "1");
     * StringUtils.rpad( "abcdefg", 12, "123456890");
     * StringUtils.rpad( "abcdefg", 4, "123");
     * 위 결과는 다음과 같다.
     * abcdefg     
     * 123123123123
     * abcdefg12312
     * abcdefg11111
     * abcdefg12345
     * defg        </pre></blockquote>
     * @param str 원본 문자열
     * @param length 생성하고자 하는 전체 길이
     * @param pad 패딩 문자열 - <code>null</code>인 경우 공백문자(' ')가 적용
     * @return String 길이에 맞게 오른쪽에 패딩문자열로 채운 문자열
     */
    public static String rpad( String str, int length, String pad)
    {
        if( str== null) str= "";
        if( pad== null) pad= " ";
        int j= str.length();
        if( j> length) return str.substring( j- length);
        if( j== length) return str;
        int k= pad.length();
        int l= length- j;
        int i1= l/ k;
        int j1= l% k;
        StringBuffer stringbuffer= new StringBuffer();
        stringbuffer.append( str);
        for( ; i1> 0; i1--)
            stringbuffer.append( pad);

        if( j1> 0) stringbuffer.append( pad.substring( 0, j1));
        return stringbuffer.toString();
    }

    private static int rand( int i, int j)
    {
        return i+ Math.abs( rgen.nextInt())% ( ( j- i)+ 1);
    }

    private static Object[] swap( Object aobj[], int i, int j)
    {
        Object obj= aobj[i];
        aobj[i]= aobj[j];
        aobj[j]= obj;
        return aobj;
    }
    
    private static boolean isEmpty( Object[] array)
    {
    	return array== null || array.length== 0;
    }
    
    @SuppressWarnings( "rawtypes")
	private static boolean isEmpty( Collection collection)
	{
		return collection== null || collection.isEmpty();
	}

	@SuppressWarnings( { "rawtypes", "unused"})
	private static boolean isEmpty( Map map)
	{
		return map== null || map.isEmpty();
	}
	
	public static String nullSafeToString( Object obj)
	{
		if( obj== null)		return EMPTY_STRING;
		if( obj instanceof String)			return (String)obj;
		if( obj instanceof Object[])		return nullSafeToString( (Object[])obj);
		if( obj instanceof boolean[])	return nullSafeToString( (boolean[])obj);
		if( obj instanceof byte[])			return nullSafeToString( (byte[])obj);
		if( obj instanceof char[])			return nullSafeToString( (char[])obj);
		if( obj instanceof double[])			return nullSafeToString( (double[])obj);
		if( obj instanceof float[])			return nullSafeToString( (float[])obj);
		if( obj instanceof int[])			return nullSafeToString( (int[])obj);
		if( obj instanceof long[])			return nullSafeToString( (long[])obj);
		if( obj instanceof short[])			return nullSafeToString( (short[])obj);
		String str= obj.toString();
		return str!= null ? str : EMPTY_STRING;
	}

	private static String nullSafeToString( Object[] array)
	{
		if( array== null)		return NULL_STRING;
		int length= array.length;
		if( length== 0)		return EMPTY_ARRAY;
		StringBuilder sb= new StringBuilder();
		for( int i= 0; i< length; i++)
		{
			if( i== 0)	sb.append( ARRAY_START);
			else			sb.append( ARRAY_ELEMENT_SEPARATOR);
			
			sb.append( String.valueOf( array[i]));
		}
		sb.append( ARRAY_END);
		return sb.toString();
	}
	
	private static String nullSafeToString( boolean[] array)
	{
		if( array== null)		return NULL_STRING;
		int length= array.length;
		if( length== 0)		return EMPTY_ARRAY;
		StringBuilder sb= new StringBuilder();
		for( int i= 0; i< length; i++)
		{
			if( i== 0)	sb.append( ARRAY_START);
			else			sb.append( ARRAY_ELEMENT_SEPARATOR);
			
			sb.append( array[i]);
		}
		sb.append( ARRAY_END);
		return sb.toString();
	}
	
	private static String nullSafeToString( byte[] array)
	{
		if( array== null)		return NULL_STRING;
		int length= array.length;
		if( length== 0)		return EMPTY_ARRAY;
		StringBuilder sb= new StringBuilder();
		for( int i= 0; i< length; i++)
		{
			if( i== 0)	sb.append( ARRAY_START);
			else			sb.append( ARRAY_ELEMENT_SEPARATOR);
			
			sb.append( array[i]);
		}
		sb.append( ARRAY_END);
		return sb.toString();
	}
	
	private static String nullSafeToString( char[] array)
	{
		if( array== null)		return NULL_STRING;
		int length= array.length;
		if( length== 0)		return EMPTY_ARRAY;
		StringBuilder sb= new StringBuilder();
		for( int i= 0; i< length; i++)
		{
			if( i== 0)	sb.append( ARRAY_START);
			else			sb.append( ARRAY_ELEMENT_SEPARATOR);
			
			sb.append( "'").append( array[i]).append( "'");
		}
		sb.append( ARRAY_END);
		return sb.toString();
	}
	
	private static String nullSafeToString( double[] array)
	{
		if( array== null)		return NULL_STRING;
		int length= array.length;
		if( length== 0)		return EMPTY_ARRAY;
		StringBuilder sb= new StringBuilder();
		for( int i= 0; i< length; i++)
		{
			if( i== 0)	sb.append( ARRAY_START);
			else			sb.append( ARRAY_ELEMENT_SEPARATOR);
			
			sb.append( array[i]);
		}
		sb.append( ARRAY_END);
		return sb.toString();
	}
	
	private static String nullSafeToString( float[] array)
	{
		if( array== null)		return NULL_STRING;
		int length= array.length;
		if( length== 0)		return EMPTY_ARRAY;
		StringBuilder sb= new StringBuilder();
		for( int i= 0; i< length; i++)
		{
			if( i== 0)	sb.append( ARRAY_START);
			else			sb.append( ARRAY_ELEMENT_SEPARATOR);
			
			sb.append( array[i]);
		}
		sb.append( ARRAY_END);
		return sb.toString();
	}
	
	private static String nullSafeToString( int[] array)
	{
		if( array== null)		return NULL_STRING;
		int length= array.length;
		if( length== 0)		return EMPTY_ARRAY;
		StringBuilder sb= new StringBuilder();
		for( int i= 0; i< length; i++)
		{
			if( i== 0)	sb.append( ARRAY_START);
			else			sb.append( ARRAY_ELEMENT_SEPARATOR);
			
			sb.append( array[i]);
		}
		sb.append( ARRAY_END);
		return sb.toString();
	}
	
	/**
	 * 배열의 문자열 표현을 가져온다. 
	 * @param array 대상 배열
	 * @return 문자열 표현
	 */
	public static String nullSafeToString( long[] array)
	{
		if( array== null)		return NULL_STRING;
		int length= array.length;
		if( length== 0)		return EMPTY_ARRAY;
		StringBuilder sb= new StringBuilder();
		for( int i= 0; i< length; i++)
		{
			if( i== 0)	sb.append( ARRAY_START);
			else			sb.append( ARRAY_ELEMENT_SEPARATOR);
			
			sb.append( array[i]);
		}
		sb.append( ARRAY_END);
		return sb.toString();
	}
	
	private static String nullSafeToString( short[] array)
	{
		if( array== null)		return NULL_STRING;
		int length= array.length;
		if( length== 0)		return EMPTY_ARRAY;
		StringBuilder sb= new StringBuilder();
		for( int i= 0; i< length; i++)
		{
			if( i== 0)	sb.append( ARRAY_START);
			else			sb.append( ARRAY_ELEMENT_SEPARATOR);
			
			sb.append( array[i]);
		}
		sb.append( ARRAY_END);
		return sb.toString();
	}
}
