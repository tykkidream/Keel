package tykkidream.keel.base.pattern;

import java.io.Serializable;

public interface DataTransferObject<A,B> extends Serializable{
	
	public B translate(A a);
	
	public A translate();
}
