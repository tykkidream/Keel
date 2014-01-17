package dream.keel.util;

public class StringUtils {
	public static String capitalize(String source){		
		return source.substring(0,1).toUpperCase() + source.substring(1).toLowerCase();
	}
	
	public static String capitalizes(String source){
		String[] s = source.split(" ");
		
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < s.length; i++) {
			builder.append(s[i].substring(0,1).toUpperCase() + s[i].substring(1).toLowerCase());
			if(i < s.length -1){
				builder.append(" ");
			}
		}
		
		return builder.toString();
	}
}
