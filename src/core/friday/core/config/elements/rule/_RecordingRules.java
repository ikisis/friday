package friday.core.config.elements.rule;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class _RecordingRules {

	@XmlElement(name="class-name-rule")
	private _NameRules classNameRules = new _NameRules();

	public _NameRules getClassNameRules() {
		return classNameRules;
	}

	public void setClassNameRules(_NameRules classNameRule) {
		this.classNameRules = classNameRule;
	}
	
	@XmlElement(name="method-name-rule")
	private _NameRules methodNameRules = new _NameRules();

	public _NameRules getMethodNameRules() {
		return methodNameRules;
	}

	public void setMethodNameRules(_NameRules methodNameRules) {
		this.methodNameRules = methodNameRules;
	}
	
	
}
