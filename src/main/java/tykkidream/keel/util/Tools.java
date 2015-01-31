package tykkidream.keel.util;

import tykkidream.keel.base.pattern.NullObject;

public class Tools {

	public static boolean toolIsNull(Object obj){
		return NullObject.Common.isNull(obj);
	}
	
	public static boolean toolIsNotNull(Object obj){
		return NullObject.Common.isNotNull(obj);
	}
}
