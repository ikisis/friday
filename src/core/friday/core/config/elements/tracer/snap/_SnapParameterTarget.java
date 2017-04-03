package friday.core.config.elements.tracer.snap;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class _SnapParameterTarget {

	@XmlAttribute(name="method-desc")
	private String methodDesc;

	public String getMethodDesc() {
		return methodDesc;
	}

	public void setMethodDesc(String methodDesc) {
		this.methodDesc = methodDesc;
	}
	
	@XmlElement(name="param-index")
	private ArrayList<Integer> parameterIndexes = new ArrayList<Integer>();

	public ArrayList<Integer> getParameterIndexes() {
		return parameterIndexes;
	}

	public void setParameterIndexes(ArrayList<Integer> parameterIndexes) {
		this.parameterIndexes = parameterIndexes;
	}
	
	
}
