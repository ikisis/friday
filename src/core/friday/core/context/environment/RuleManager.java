package friday.core.context.environment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.regex.Pattern;

import org.apache.commons.ognl.Node;
import org.apache.commons.ognl.Ognl;
import org.apache.commons.ognl.OgnlContext;
import org.apache.commons.ognl.OgnlException;

import friday.core.util.StringUtils;

public class RuleManager {
	
	private final Map<String, Map<ExpressionNode, String>> tracerMap= new HashMap<String, Map<ExpressionNode,String>>();
	
	public Map<String, Map<ExpressionNode, String>> getTracerMap(){
		return this.tracerMap;
	}
		
	void initTraceTargetMap(String tracerMapConfigLocation){
		
		if(tracerMapConfigLocation == null)return;
		
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(tracerMapConfigLocation);
			
			Properties props = new Properties();
			
			props.load(fis);
			for( Entry<Object, Object> entry: props.entrySet()){
				
				String expression= (String)entry.getKey();
				String node= (String)entry.getValue();
				
                if( !StringUtils.hasText( expression) || !StringUtils.hasText( node))
					continue;
				parse( expression, node);
			}
			
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		} finally{
			if(fis!=null)try {fis.close();} catch (IOException e) {}
		}
		
	}
	public static final String openToken= "${";
	public static final String closeToken= "}";
	
	public void parse( String expression, String node){
		
        if( StringUtils.hasText( node) && StringUtils.hasText( expression)){
			int start= expression.indexOf( openToken);
			int end= expression.indexOf( closeToken);
			
			if( start> -1 && end> -1 && end> start){
				String type= expression.substring( 0, start);
				String expr= expression.substring( start+ openToken.length(), end);
				
				Map<ExpressionNode, String> targets= this.tracerMap.get( type);
				if( targets== null) targets= new HashMap<ExpressionNode, String>();				
                try{ targets.put( new ExpressionNode( expr), node);}catch( OgnlException e){}
				this.tracerMap.put( type, targets);
			}				
		}
	}
	
    public static class ExpressionNode
    {
            private Node expr;

            private OgnlContext ognlContext;

            ExpressionNode( String expression) throws OgnlException
            {
                    ognlContext= (OgnlContext)Ognl.createDefaultContext( null);
                    this.expr= (Node)Ognl.parseExpression( expression);
            }

            public <T> Object getValue( T rootObject) throws OgnlException
            {
                    return Ognl.getValue( this.expr, this.ognlContext, rootObject);
            }
    }

	private List<RecordablePattern> classRecordablePattern = new ArrayList<RecordablePattern>();
	
	private List<RecordablePattern> methodRecordablePattern = new ArrayList<RecordablePattern>();
	
	public boolean isRecordable(String className){
		boolean isRecordable = false;
		
		if(classRecordablePattern != null){
			
			Iterator<RecordablePattern> iter = this.classRecordablePattern.iterator();
			
			while(iter.hasNext()){
								
				Boolean bool = isRecordable(iter.next(),className);

				if(bool != null)isRecordable = bool;
				
			}
			
		}
		
		return isRecordable;	
	}
	
	public boolean isRecordable(int access, String methodName){
		
//		if(methodName.equals("<init>"))return false;
//		if(methodName.equals("<clinit>"))return false;
		if(Modifier.isAbstract(access))return false;
		
		boolean isRecordable = true;
		
		if(methodRecordablePattern != null)
			for(RecordablePattern rp : this.methodRecordablePattern){
				Boolean bool = isRecordable(rp, methodName);

				if(bool != null)isRecordable = bool;
			}
		
		return isRecordable;
	}
	
	private Boolean isRecordable(RecordablePattern pattern, String string){
		try{
			if(pattern.isRecordable != null && pattern.regularExpression != null)
				if(Pattern.compile(pattern.regularExpression).matcher(string).find())
					return pattern.isRecordable;		
			
		}catch(Throwable ignored){}
		
		return null;
	}
	
	public List<RecordablePattern> getClassRecordablePattern() {
		return classRecordablePattern;
	}
	
	public void setClassRecordablePattern(List<RecordablePattern> classRecordablePattern) {
		this.classRecordablePattern = classRecordablePattern;
	}
	
	public List<RecordablePattern> getMethodRecordablePattern() {
		return methodRecordablePattern;
	}

	public void setMethodRecordablePattern(List<RecordablePattern> methodRecordablePattern) {
		this.methodRecordablePattern = methodRecordablePattern;
	}
	
	public static class RecordablePattern implements Serializable{
		
		private static final long serialVersionUID = 4936934119891577717L;

		final Boolean isRecordable;final String regularExpression;
		
		public RecordablePattern(Boolean isRecordable, String regularExpression){
			this.isRecordable = isRecordable;
			this.regularExpression = regularExpression;
		}

		public Boolean getIsRecordable() {return isRecordable;}

		public String getRegularExpression() {return regularExpression;}
	}
}
