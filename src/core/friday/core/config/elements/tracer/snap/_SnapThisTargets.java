package friday.core.config.elements.tracer.snap;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class _SnapThisTargets {

	@XmlElement(name="snap-this-target")
	private ArrayList<_SnapThisTarget> snapThisTargets = new ArrayList<_SnapThisTarget>();

	public ArrayList<_SnapThisTarget> getSnapThisTargets() {
		return snapThisTargets;
	}

	public void setSnapThisTargets(ArrayList<_SnapThisTarget> snapThisList) {
		this.snapThisTargets = snapThisList;
	}
	
	
	
}
