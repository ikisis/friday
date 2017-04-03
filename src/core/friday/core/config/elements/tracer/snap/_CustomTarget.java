package friday.core.config.elements.tracer.snap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"className","methodDesc"})
public class _CustomTarget {

	@XmlAttribute(name="method-desc")
	private String methodDesc;

	public String getMethodDesc() {
		return methodDesc;
	}
	
	public void setMethodDesc(String methodDesc) {
		this.methodDesc = methodDesc;
	}
	
	@XmlAttribute(name="custom-class-name")
	private String className;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
	
	
}
