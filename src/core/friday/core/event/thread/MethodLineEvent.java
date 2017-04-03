package friday.core.event.thread;

public class MethodLineEvent{

	public static final int EXIT_LINE_NO = -1;

	public String id;
	
	public int line;
	
	public MethodLineEvent(int line){
		this.line = line;
	}

}
