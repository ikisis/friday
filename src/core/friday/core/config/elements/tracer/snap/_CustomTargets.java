package friday.core.config.elements.tracer.snap;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class _CustomTargets {

	@XmlElement
	private ArrayList<_CustomTarget> customTargets = new ArrayList<_CustomTarget>();

	public ArrayList<_CustomTarget> getCustomTargets() {
		return customTargets;
	}

	public void setCustomTargets(ArrayList<_CustomTarget> customTargets) {
		this.customTargets = customTargets;
	}
	
	
	
}
