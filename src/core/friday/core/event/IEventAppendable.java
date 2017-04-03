package friday.core.event;

public interface IEventAppendable<T extends IEvent> {

	public void append(T event);
		
}
