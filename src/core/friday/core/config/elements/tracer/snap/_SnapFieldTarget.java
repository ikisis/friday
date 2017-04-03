package friday.core.config.elements.tracer.snap;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class _SnapFieldTarget {

	@XmlAttribute(name="method-desc")
	private String methodDesc;

	public String getMethodDesc() {
		return methodDesc;
	}

	public void setMethodDesc(String methodDesc) {
		this.methodDesc = methodDesc;
	}
	
	@XmlElement(name="field-name")
	private ArrayList<String> fieldNames = new ArrayList<String>();

	public ArrayList<String> getFieldNames() {
		return fieldNames;
	}

	public void setFieldNames(ArrayList<String> fieldNames) {
		this.fieldNames = fieldNames;
	}
	
	
}
