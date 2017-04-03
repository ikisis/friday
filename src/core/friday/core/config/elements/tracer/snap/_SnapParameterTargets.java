package friday.core.config.elements.tracer.snap;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class _SnapParameterTargets {

	@XmlElement(name="snap-parameter-target")
	private ArrayList<_SnapParameterTarget> snapParameterTargets = new ArrayList<_SnapParameterTarget>();

	public ArrayList<_SnapParameterTarget> getSnapParameterTargets() {
		return snapParameterTargets;
	}

	public void setSnapParameterTargets(
			ArrayList<_SnapParameterTarget> snapParameterTargets) {
		this.snapParameterTargets = snapParameterTargets;
	}
	
	
}
