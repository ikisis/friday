package friday.core.config.elements.rule;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"regex","type", "order"})
public class _RecordingRule {
	
	@XmlAttribute(name="order")
	private int order;
	
	@XmlAttribute(name="type")
	private _RecordingRuleType type;
	
	@XmlAttribute(name="regex")
	private String regex;

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public _RecordingRuleType getType() {
		return type;
	}

	public void setType(_RecordingRuleType type) {
		this.type = type;
	}

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}
	
}
