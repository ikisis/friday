package friday.core.config.serializable;

import java.io.Serializable;
import java.util.ArrayList;

public class AgentConfig implements Serializable{

	private static final long serialVersionUID = 6086357661537956929L;
	
	private ArrayList<RecordingRule> recordingRule;
	
	private ArrayList<TracerKeyRule> tracerKeyRule;
	
	private ArrayList<AbstractTracerTargetRule> tracerTargetRule;

	public ArrayList<RecordingRule> getRecordingRule() {
		return recordingRule;
	}

	public void setRecordingRule(ArrayList<RecordingRule> recordingRule) {
		this.recordingRule = recordingRule;
	}

	public ArrayList<TracerKeyRule> getTracerKeyRule() {
		return tracerKeyRule;
	}

	public void setTracerKeyRule(ArrayList<TracerKeyRule> tracerKeyRule) {
		this.tracerKeyRule = tracerKeyRule;
	}

	public ArrayList<AbstractTracerTargetRule> getTracerTargetRule() {
		return tracerTargetRule;
	}

	public void setTracerTargetRule(ArrayList<AbstractTracerTargetRule> tracerTargetRule) {
		this.tracerTargetRule = tracerTargetRule;
	}
	
	

}
