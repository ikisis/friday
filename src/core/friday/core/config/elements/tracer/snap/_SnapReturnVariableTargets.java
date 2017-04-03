package friday.core.config.elements.tracer.snap;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;


@XmlAccessorType(XmlAccessType.FIELD)
public class _SnapReturnVariableTargets {

	@XmlElement(name="snap-return-variable-target")
	private ArrayList<_SnapReturnVariableTarget> snapReturnVariableTargets = new ArrayList<_SnapReturnVariableTarget>();

	public ArrayList<_SnapReturnVariableTarget> getSnapReturnVariableTargets() {
		return snapReturnVariableTargets;
	}

	public void setSnapReturnVariableTargets(
			ArrayList<_SnapReturnVariableTarget> snapReturnVariableTargets) {
		this.snapReturnVariableTargets = snapReturnVariableTargets;
	}
	
	
}
