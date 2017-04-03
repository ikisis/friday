package friday.core.instrument.instrumentor;

import java.util.Map;

import friday.core.asm.Opcodes;

import friday.core.util.MapUtils;

public interface ASMConstants extends Opcodes{
	
	String DESC_BOOLEAN = "Z";
	String DESC_BYTE = "B";
	String DESC_CHAR = "C";
	String DESC_SHORT = "S";
	String DESC_INT = "I";
	String DESC_FLOAT = "F";
	String DESC_LONG = "J";
	String DESC_DOUBLE = "D";

	Map<String, Integer> DESC2LOAD = MapUtils.<String, Integer>convert( new Object[][] {
			new Object[] { DESC_DOUBLE, DLOAD },
			new Object[] { DESC_FLOAT, FLOAD },
			new Object[] { DESC_LONG, LLOAD },
			new Object[] { DESC_BOOLEAN, ILOAD },
			new Object[] { DESC_BYTE, ILOAD },
			new Object[] { DESC_CHAR, ILOAD },
			new Object[] { DESC_SHORT, ILOAD },
			new Object[] { DESC_INT, ILOAD },
		} );
	
	Map<String, Integer> DESC2STORE = MapUtils.<String, Integer>convert( new Object[][] {
			new Object[] { DESC_DOUBLE, DSTORE },
			new Object[] { DESC_FLOAT, FSTORE },
			new Object[] { DESC_LONG, LSTORE },
			new Object[] { DESC_BOOLEAN, ISTORE },
			new Object[] { DESC_BYTE, ISTORE },
			new Object[] { DESC_CHAR, ISTORE },
			new Object[] { DESC_SHORT, ISTORE },
			new Object[] { DESC_INT, ISTORE },
		} );
}
