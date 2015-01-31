package tykkidream.keel.base.pattern;

public interface NullObject {

	public class Common {
		public static boolean isNull(Object obj){
			if (null == obj  || obj instanceof NullObject) {
				return true;
			}
			
			return false;
		}
		
		public static boolean isNotNull(Object obj){
			return !isNull(obj);
		}
	}
}
