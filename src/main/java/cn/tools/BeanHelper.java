/*  
 * @(#) BeanHelper.java Create on 2011-12-14 下午12:31:19
 *
 * Copyright 2011 by xl.
 */

package cn.tools;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;

/**
 * 
 * @author zhanghongliang
 * @date 2011-12-14
 */
public class BeanHelper {

	/**
	 * 设置属性
	 * 
	 * @param bean
	 * @param name
	 * @param value
	 */
	public static void setProperty(Object bean, String name, Object value) {
		try {
			BeanUtils.setProperty(bean, name, value);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 设置属性
	 * 
	 * @param bean
	 * @param name
	 * @param value
	 */
	public static Object getProperty(Object bean, String name) {
		Object result = null;
		try {
			result = BeanUtils.getProperty(bean, name);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 用来对象之间的同属性拷贝
	 * 
	 * @param sourceO
	 * @param clazz
	 * @param targetO
	 * @data:2011-11-29下午2:30:18
	 * @author:zhanghongliang
	 */
	@SuppressWarnings("rawtypes")
	public static void populate(Object sourceO, Class<?> clazz, Object targetO) {
		Field[] fields = clazz.getDeclaredFields();
		if (fields != null && fields.length > 0) {
			for (Field field : fields) {
				field.setAccessible(true);
				try {
					Object value = field.get(sourceO);
					setProperty(targetO, field.getName(), value);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		Class[] classes = clazz.getDeclaredClasses();
		if (classes != null && classes.length > 0) {
			for (Class class1 : classes) {
				populate(sourceO, class1, targetO);
			}
		}
	}

	/**
	 * bean to map
	 * 
	 * @param obj
	 * @param clazz
	 * @param map
	 * @data:2011-12-14下午12:32:22
	 * @author:zhanghongliang
	 */
	@SuppressWarnings("rawtypes")
	private static void beanToMap(Object obj, Class<?> clazz, Map<String, Object> map) {
		if (obj != null && clazz != null && map != null) {
			Field[] fields = clazz.getDeclaredFields();
			if (fields != null && fields.length > 0) {
				for (Field field : fields) {
					field.setAccessible(true);
					try {
						Object value = field.get(obj);
						 if (value != null) {
						map.put(field.getName(), value);
						 }
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}
			Class[] classes = clazz.getDeclaredClasses();
			if (classes != null && classes.length > 0) {
				for (Class class1 : classes) {
					populate(obj, class1, map);
				}
			}
		}
	}
	
	public static Map<String, Object> beanToMap(Object obj) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (obj != null) {
			beanToMap(obj, obj.getClass(), result);
		}
		return result;
	}
}
