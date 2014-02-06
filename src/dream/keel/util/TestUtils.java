package dream.keel.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestUtils {
	public static ApplicationContext applicationContext = null;
	
	public static ApplicationContext getApplicationContext(){
		if (applicationContext == null) {
			System.out.println(">>>>>     初始化Spring的Application     <<<<<");
			applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		}
		return applicationContext;
	}
}
