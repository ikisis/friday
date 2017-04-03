package friday.core.config.elements.rule;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class _Rules {
	
	@XmlElement(name="recording")
	private _RecordingRules recording = new _RecordingRules();

	public _RecordingRules getRecording() {
		return recording;
	}

	public void setRecording(_RecordingRules recording) {
		this.recording = recording;
	}
	
	
	

}
