package friday.core.config.elements.tracer;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class _Targets {

	@XmlElement(name="target")
	ArrayList<_Target> targets = new ArrayList<_Target>();

	public ArrayList<_Target> getTargets() {
		return targets;
	}

	public void setTargets(ArrayList<_Target> targets) {
		this.targets = targets;
	}
	
	
	
}
