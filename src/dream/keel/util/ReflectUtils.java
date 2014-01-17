package dream.keel.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 利用反射进行操作的一个工具类
 * 
 * @author Saber
 * 
 */
public class ReflectUtils {

	/**
	 * 利用反射获取指定对象里面的指定属性
	 * 
	 * @param obj
	 *            目标对象
	 * @param fieldName
	 *            目标属性名
	 * @return 目标字段
	 */
	private static Field getField(Object obj, String fieldName) {
		Field field = null;
		for (Class<?> clazz = obj.getClass(); clazz != Object.class; clazz = clazz
				.getSuperclass()) {
			try {
				field = clazz.getDeclaredField(fieldName);
				break;
			} catch (NoSuchFieldException e) {
				// 这里不用做处理，子类没有该字段可能对应的父类有，都没有就返回null。
			}
		}
		return field;
	}

	/**
	 * 利用反射获取目标对象的指定的属性。
	 * 
	 * @param obj 目标对象
	 * @param fieldName 指定的属性
	 * @return 目标属性的值
	 */
	public static Object getFieldValue(Object obj, String fieldName) {
		Object result = null;
		Field field = getField(obj, fieldName);
		if (field != null) {
			field.setAccessible(true);
			try {
				result = field.get(obj);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 利用反射设置目标对象的指定属性为指定的值。
	 * 
	 * @param obj 目标对象
	 * @param fieldName 目标的指定属性名
	 * @param fieldValue 新值
	 */
	public static void setFieldValue(Object obj, String fieldName,
			String fieldValue) {
		Field field = getField(obj, fieldName);
		if (field != null) {
			try {
				field.setAccessible(true);
				field.set(obj, fieldValue);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 利用反射获取指定对象的指定方法
	 * 
	 * @param obj
	 *            目标对象
	 * @param methodName
	 *            目标方法名
	 * @return 目标方法
	 */
	public static Method getMethod(Object obj, String methodName,
			Class<?>... parameterTypes) {
		Method method = null;
		for (Class<?> clazz = obj.getClass(); clazz != Object.class; clazz = clazz
				.getSuperclass()) {
			try {
				method = clazz.getDeclaredMethod(methodName, parameterTypes);
				break;
			} catch (Exception e) {

			}
		}
		return method;
	}

	/**
	 * 利用反射利用并运行指定对象的指定方法
	 * 
	 * @param obj
	 *            目标对象
	 * @param methodName
	 *            目标方法名
	 * @param args
	 *            目标方法的参数
	 * @return 目标方法的执行结果
	 */
	public static Object invokeMethod(Object obj, String methodName,
			Object[] args) {
		Object result = null;
		Method method = null;

		try {
			method = getMethod(obj, methodName, ParameterClass(args));
			if (method != null) {
				method.setAccessible(true);
				result = method.invoke(obj, args);
			}
		} catch (Exception e) {

		}
		return result;
	}

	/**
	 * 判断是基本类型或包装类
	 * 
	 * @param clazz
	 * @return
	 */
	public static Class<?>[] ParameterClass(Object[] args) {
		if(args == null){
			return null;
		}
		
		Class<?>[] parameterClass = new Class<?>[args.length];
		for (int i = 0; i < parameterClass.length; i++) {
			parameterClass[i] = args[i].getClass();
		}

		return parameterClass;
	}

	/**
	 * 判断是基本类型或包装类
	 * 
	 * @param clazz
	 * @return
	 */
	public static boolean isWrapClass(Class<?> clazz) {
		try {
			return ((Class<?>) clazz.getField("TYPE").get(null)).isPrimitive();
		} catch (Exception e) {
			return false;
		}
	}
}
