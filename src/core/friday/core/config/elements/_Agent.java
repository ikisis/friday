package friday.core.config.elements;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import friday.core.config.elements.rule._Rules;
import friday.core.config.elements.tracer._Tracer;

@XmlRootElement(name="agent-config")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"rules", "tracer"})
public class _Agent {
	
	@XmlElement(name="rules")
	private _Rules rules = new _Rules();

	public _Rules getRules() {
		return rules;
	}

	public void setRules(_Rules rules) {
		this.rules = rules;
	}
	
	@XmlElement(name="tracer")
	_Tracer tracer = new _Tracer();

	public _Tracer getTracer() {
		return tracer;
	}

	public void setTracer(_Tracer tracer) {
		this.tracer = tracer;
	}

	
}
