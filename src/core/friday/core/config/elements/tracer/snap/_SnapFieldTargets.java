package friday.core.config.elements.tracer.snap;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class _SnapFieldTargets {

	@XmlElement(name="snap-field-target")
	private ArrayList<_SnapFieldTarget> snapFieldTargets = new ArrayList<_SnapFieldTarget>();

	public ArrayList<_SnapFieldTarget> getSnapFieldTargets() {
		return snapFieldTargets;
	}

	public void setSnapFieldTargets(ArrayList<_SnapFieldTarget> snapFieldTargets) {
		this.snapFieldTargets = snapFieldTargets;
	}
	
}
