package friday.core.config.elements.rule;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class _NameRules {

	@XmlElement(name="rule")
	ArrayList<_RecordingRule> rules = new ArrayList<_RecordingRule>();

	public ArrayList<_RecordingRule> getRules() {
		return rules;
	}

	public void setRules(ArrayList<_RecordingRule> rules) {
		this.rules = rules;
	}
	
}
