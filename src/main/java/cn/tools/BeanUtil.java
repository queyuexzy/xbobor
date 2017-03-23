package cn.tools;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.Assert;

/**
 * 用于把bean转换成map
 * 
 * @author zhl
 * @date 20112011-8-26上午09:25:51
 * 
 */
public class BeanUtil {
	@SuppressWarnings({ "rawtypes", "unused" })
	private static Class[] classes;

	/**
	 * bean的所有属性转换成map形式
	 * 
	 * @author zhl
	 * @date 20112011-8-26上午09:07:37
	 * 
	 * @param bean
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map<String, Object> beanAllToMap(Object bean) {
		Assert.notNull(bean, "bea can't null!");
		Map<String, Object> result = new HashMap<String, Object>();

		Class dataBeanClass = bean.getClass();
		Field dataBeanField[] = dataBeanClass.getDeclaredFields();

		String f_name = "";// 类变量名称
		String m_name = "";// 方法名称
		Method gmethod = null;// get方法名称
		Object value;// 取到的值
		try {
			for (int i = 0; i < dataBeanField.length; i++) {
				f_name = dataBeanField[i].getName();
				System.out.println(f_name);
				
				if("serialVersionUID".equals(f_name)){
					continue;
				}
				if(f_name.startsWith("FINAL_")){
					continue;
				}
				if(f_name.contains("$")){
					continue;
				}
				m_name = getBeanGetMethodName(f_name);
				gmethod = dataBeanClass.getMethod(m_name);
				value = gmethod.invoke(bean);// 取值
				result.put(f_name, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 转换 bean中有值的字段 -1 null 空串 不转换
	 * 
	 * @author zhl
	 * @date 20112011-8-26上午09:16:07
	 * 
	 * @param bean
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map<String, Object> beanToMap(Object bean) {
		Assert.notNull(bean, "bea can't null!");
		Map<String, Object> result = new HashMap<String, Object>();
		Class dataBeanClass = bean.getClass();
		Field dataBeanField[] = dataBeanClass.getDeclaredFields();
		String f_name = "";// 类变量名称
		String m_name = "";// 方法名称
		Method gmethod = null;// get方法名称
		Object value;// 取到的值
		try {
			for (int i = 0; i < dataBeanField.length; i++) {
				f_name = dataBeanField[i].getName();
				if("serialVersionUID".equals(f_name)){
					continue;
				}
				if(f_name.startsWith("FINAL_")){
					continue;
				}
				if(f_name.contains("$")){
					continue;
				}
				m_name = getBeanGetMethodName(f_name);
				gmethod = dataBeanClass.getMethod(m_name);
				value = gmethod.invoke(bean);// 取值
				if (checkIsNeed(value)) {
					result.put(f_name, value);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 转换 bean中有值的字段 -1 null 空串 不转换
	 * 
	 * @author zhl
	 * @date 20112011-8-26上午09:16:07
	 * 
	 * @param bean
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map<String, Object> beanToQueryMap(Object bean) {
		Assert.notNull(bean, "bea can't null!");
		Map<String, Object> result = new HashMap<String, Object>();
		Class dataBeanClass = bean.getClass();
		Field dataBeanField[] = dataBeanClass.getDeclaredFields();
		String f_name = "";// 类变量名称
		String m_name = "";// 方法名称
		Method gmethod = null;// get方法名称
		Object value;// 取到的值
		try {
			for (int i = 0; i < dataBeanField.length; i++) {
				f_name = dataBeanField[i].getName();
				if("serialVersionUID".equals(f_name)){
					continue;
				}
				if(f_name.startsWith("FINAL_")){
					continue;
				}
				if(f_name.contains("$")){
					continue;
				}
				m_name = getBeanGetMethodName(f_name);
				gmethod = dataBeanClass.getMethod(m_name);
				value = gmethod.invoke(bean);// 取值
				if (checkIsNeed(value)) {
					if (value instanceof String) {
						result.put(f_name, "%" + value + "%");
					} else {
						result.put(f_name, value);
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 取bean 指定的信息
	 * 
	 * @author zhl
	 * @date 20112011-8-26上午09:31:05
	 * 
	 * @param bean
	 * @return
	 */
	public static Object getBeanFieldValue(Object bean, String fieldName) {
		Assert.notNull(bean, "bea can't null!");
		Object result = null;
		String getMethodName = getBeanGetMethodName(fieldName);
		try {
			Method gmethod = bean.getClass().getMethod(getMethodName);
			result = gmethod.invoke(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 给bean中放入值
	 * 
	 * @author zhl
	 * @date 20112011-8-27下午05:43:06
	 * 
	 * @param bean需要放入的对象
	 * @param fieldName	set方法属性名称
	 * @param classes	set方法中传入的class
	 * @param filedValue	放入的值
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Object setValueToObject(Object bean, String fieldName, Class[] classes, Object... filedValue) {

		Assert.notNull(bean, "bean is null, please chece");
		String setMethodName = getBeanSetMethodName(fieldName);
		try {
			Method setMethod = bean.getClass().getMethod(setMethodName, classes);
			if (setMethod != null) {
				setMethod.invoke(bean, filedValue);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}

	/*
	 * 检查是否需要
	 */
	private static boolean checkIsNeed(Object value) {
		boolean result = true;
		if (value == null) {
			result = false;
		}
		if (value instanceof String) {
			if (value.toString().trim().length() == 0) {
				result = false;
			}
		}
		if (value instanceof Integer) {
			int intValue = (Integer) value;
			result = intValue != -1;
		}
		if (value instanceof Long) {
			long longValue = (Long) value;
			result = longValue != -1l;
		}
		return result;
	}

	/*
	 * 取bean的get方法的名称
	 */
	private static String getBeanGetMethodName(String fieldName) {
		Assert.notNull(fieldName);
		return "get" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);// 得到该列的get方法
	}

	/*
	 * 取bean的get方法的名称
	 */
	private static String getBeanSetMethodName(String fieldName) {
		Assert.notNull(fieldName);
		return "set" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);// 得到该列的set方法
	}
}
