package friday.core.config.elements.tracer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class _Instrument {

	@XmlElement(name="targets")
	_Targets targets = new _Targets();

	public _Targets getTargets() {
		return targets;
	}

	public void setTargets(_Targets targets) {
		this.targets = targets;
	}
	
	
}
