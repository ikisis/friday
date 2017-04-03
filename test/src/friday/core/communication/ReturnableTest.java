package friday.core.communication;

import org.junit.Test;

import friday.core.communication.contents.ThreadEventSequenceGettable;

public class ReturnableTest {

	@Test
	public void test() {
		
		
		ThreadEventSequenceGettable a = new ThreadEventSequenceGettable();
		
		a.pollReturned();
		
		
	}

}
