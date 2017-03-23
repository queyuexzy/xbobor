/*  
 * @(#) ExceptionHelper.java Create on 2011-12-5 上午10:37:56   
 *   
 * Copyright 2011 by xl.   
 */

package cn.tools;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 
 * @author : zhanghongliang
 * @date : 2011-12-5 上午10:37:56
 */
public class ExceptionHelper {
	/**
	 * 以字符串形式返回异常堆栈信息
	 * 
	 * @param e
	 * @return 异常堆栈信息字符串
	 */
	public static String getStackTrace(Exception e) {
		StringWriter writer = new StringWriter();
		e.printStackTrace(new PrintWriter(writer, true));
		return writer.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			throw new Exception("自定义异常");
		} catch (Exception ex) {
			System.out.println(getStackTrace(ex));
		}
	}
}
