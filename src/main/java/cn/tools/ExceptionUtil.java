package cn.tools;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtil {
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
