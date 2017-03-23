/*  
 * @(#) PointArgsBean.java Create on 2015年3月5日 上午11:13:59   
 *   
 * Copyright 2015 by xl.   
 */

/**
 * 
 */
package cn.system.basic.global.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Used to wrap AOP arguments.
 * 
 * @author zhangying
 * @date 2015年3月5日 上午11:13:59
 */
public class PointArgsBean {
	private final Map<String, String> args = new HashMap<String, String>();

	/**
	 * Put a value into the map.
	 * 
	 * @param key
	 * The key.
	 * @param value
	 * The value.
	 * @data:2015年3月5日上午11:19:07
	 * @author:zhangying
	 */
	public void put(String key, String value) {
		args.put(key, value);
	}

	/**
	 * Get the value by the key.
	 * 
	 * @param key
	 * The key.
	 * @return The value against the key.
	 * @throws PointArgsException
	 * Throw when the key not exist in the map.
	 * @data:2015年3月5日上午11:19:41
	 * @author:zhangying
	 */
	public Object get(String key) throws PointArgsException {
		if (args.containsKey(key)) {
			return args.get(key);
		} else {
			throw new PointArgsException(String.format("缺少参数！ key:%s", key));
		}
	}

	/**
	 * The Exception for the {@link PointArgsBean}.
	 * 
	 * @author zhangying
	 * @date 2015年3月5日 上午11:20:59
	 */
	public class PointArgsException extends Exception {
		private static final long serialVersionUID = -6256680004572890007L;

		/**
		 * Constructor.
		 * 
		 * @param message
		 * The message.
		 */
		public PointArgsException(String message) {
			super(message);
		}
	}
}
