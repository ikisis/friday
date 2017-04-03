package friday.core.config.elements.tracer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import friday.core.config.elements.tracer.snap._CustomTargets;
import friday.core.config.elements.tracer.snap._SnapFieldTargets;
import friday.core.config.elements.tracer.snap._SnapParameterTargets;
import friday.core.config.elements.tracer.snap._SnapReturnVariableTargets;
import friday.core.config.elements.tracer.snap._SnapThisTargets;

@XmlAccessorType(XmlAccessType.FIELD)
public class _Target {

	@XmlAttribute(name="class-name")
	private String className;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	

	@XmlElement(name="snap-this-targets")
	private _SnapThisTargets snapThisTargets = new _SnapThisTargets();

	public _SnapThisTargets getSnapThisTargets() {
		return snapThisTargets;
	}

	public void setSnapThisTargets(_SnapThisTargets snapThisTargets) {
		this.snapThisTargets = snapThisTargets;
	}
	
	@XmlElement(name="snap-return-variable-targets")
	private _SnapReturnVariableTargets snapReturnVariableTargets = new _SnapReturnVariableTargets();

	public _SnapReturnVariableTargets getSnapReturnVariableTargets() {
		return snapReturnVariableTargets;
	}

	public void setSnapReturnVariableTargets(
			_SnapReturnVariableTargets snapReturnVariableTargets) {
		this.snapReturnVariableTargets = snapReturnVariableTargets;
	}
	
	@XmlElement(name="snap-parameter-targets")
	private _SnapParameterTargets snapParameterTargets = new _SnapParameterTargets();

	public _SnapParameterTargets getSnapParameterTargets() {
		return snapParameterTargets;
	}

	public void setSnapParameterTargets(_SnapParameterTargets snapParameterTargets) {
		this.snapParameterTargets = snapParameterTargets;
	}
	
	@XmlElement(name="snap-field-targets")
	private _SnapFieldTargets snapFieldTargets = new _SnapFieldTargets();

	public _SnapFieldTargets getSnapFieldTargets() {
		return snapFieldTargets;
	}

	public void setSnapFieldTargets(_SnapFieldTargets snapFieldTargets) {
		this.snapFieldTargets = snapFieldTargets;
	}
	
	@XmlElement(name="custom-targets")
	private _CustomTargets customTargets = new _CustomTargets();

	public _CustomTargets getCustomTargets() {
		return customTargets;
	}

	public void setCustomTargets(_CustomTargets customTargets) {
		this.customTargets = customTargets;
	}
	
	
	
}
