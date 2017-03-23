package cn.system.basic.global;

public class GlobalConstants {
	// 常量，对应BaseAction 保存 页面提示信息
	public final static String GLOBAL_MESSAGES = "system.message";

	// 常量，当前系统登录的用户，存放在session用户信息的中的key
	public final static String CURRENT_USER = "current.user";

	/*
	 * 所有bean都有此属性
	 */
	public final static String TABLE_NAME = "tableName";

	/**
	 * ajax请求的需有此标记 requesType=ajax 表示为ajax请求
	 */
	public final static String KEY_REQUEST_TYPE = "requestType";

	/** The ajax request flag. */
	public final static String FLAG_REQUEST_TYPE_AJAX = "ajax";
	/** The key of error info. */
	public static final String KEY_ERROR_INFO = "errorInfo";
}
