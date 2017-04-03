package friday.core.event;

public class EventAppenderAdapter implements IEventAppendable<IEvent>{
	
	private IEventAppendable<IEvent> esb = new EmptyEventSequenceBuilder();

	@Override
	public void append(IEvent event) {
		
		esb.append(event);
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private class EmptyEventSequenceBuilder implements IEventAppendable<IEvent>{

		@Override
		public void append(IEvent event) {
			
			if(event instanceof IEventAppendable){
				
				esb = (IEventAppendable)event;
				
			}else{
				
				throw new RuntimeException("You should send me a IEventSequeneBuilder first.");
				
			}
			
		}
		
	}

}
