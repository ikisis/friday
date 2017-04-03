package friday.core.context.id;

import static friday.core.context.id.GUIDUtils.*;

public class KEYS {
	
	private static HexSequence globalSeeder = new HexSequence(6);
	
	private HexSequence seeder = new HexSequence(6);
	
	private long startTime = System.currentTimeMillis();
	
	private String GUID;
	
	public KEYS(Object obj){
//		this.GUID = createIpHexString() + createPidHexString() + decToHex(startTime) + padHex(decToHex(System.identityHashCode(this)), 6);
		this.GUID = createIpHexString() + createPidHexString() + decToHex(startTime) + nextGH();

	}
	
	public static synchronized String nextGH(){
		return globalSeeder.next();
	}
	
	public String next(){
		return seeder.next();
	}
	
	public long baseTime(){
		return this.startTime;
	}
	
	public String guid(){
		return GUID;
	}
}
