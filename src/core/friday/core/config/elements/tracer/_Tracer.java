package friday.core.config.elements.tracer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class _Tracer {
	
	@XmlElement(name="instrument")
	_Instrument instrument = new _Instrument();

	public _Instrument getInstrument() {
		return instrument;
	}

	public void setInstrument(_Instrument instrument) {
		this.instrument = instrument;
	}
	
	

}
