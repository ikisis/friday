package friday.core.util;

import java.io.Serializable;

public final class Var<T> implements Serializable{

	private static final long serialVersionUID = 1038342446072948013L;
	
	T var;
	
	public Var(T var){
		this.var = var;
	}
	
	public T get(){
		return this.var;
	}
	
	public void set(T var){
		this.var = var;
	}
	
}
