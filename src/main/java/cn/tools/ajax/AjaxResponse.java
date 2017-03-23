package cn.tools.ajax;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import cn.system.basic.manage.action.ResetAction;

/**
 * Represent a ajax response.
 * 
 * @author Luo Yinzhuo
 */
public final class AjaxResponse {

	/** Code stands for a successful ajax response. */
	public static final AjaxErrorCode AJAX_CODE_SUCCESS = new AjaxErrorCode(1,
			"操作成功", AjaxErrorCode.SYSTEM_CODE);
	/** Code stands for a failed ajax response. */
	public static final AjaxErrorCode AJAX_CODE_FAIL = new AjaxErrorCode(0,
			"操作失败", AjaxErrorCode.ACTION_CODE);

	/** Code stands for the requester has not logged in. */
	public static final AjaxErrorCode AJAX_CODE_NO_LOGIN = new AjaxErrorCode(
			-100, "未登录", AjaxErrorCode.SYSTEM_CODE);
	/** Code stands for the requester has no right for the request. */
	public static final AjaxErrorCode AJAX_CODE_NO_RIGHT = new AjaxErrorCode(
			-101, "无权限", AjaxErrorCode.SYSTEM_CODE);

	/** Code stands for the requester has send illegal parameter in the request. */
	public static final AjaxErrorCode AJAX_CODE_ILLEGAL_PARAM = new AjaxErrorCode(
			-200, "参数不合法", AjaxErrorCode.ACTION_CODE);
	/** Code stands for the request has no result found. */
	public static final AjaxErrorCode AJAX_CODE_RESULT_NOT_FOUND = new AjaxErrorCode(
			-201, "查询不到记录", AjaxErrorCode.ACTION_CODE);
	/** Code stands for the request would cause to make a repeat result. */
	public static final AjaxErrorCode AJAX_CODE_RESULT_REPEAT = new AjaxErrorCode(
			-202, "记录重复", AjaxErrorCode.ACTION_CODE);
	/** Code specified for error occurs in a line data when importing file. */
	public static final AjaxErrorCode AJAX_CODE_ILLEGAL_LINE = new AjaxErrorCode(
			-203, "行信息数据格式不正确", AjaxErrorCode.ACTION_CODE);
	/** Code specified for error occurs in file upload when file size too large. */
	public static final AjaxErrorCode AJAX_CODE_FILE_TOO_LARGE = new AjaxErrorCode(
			-204, "文件尺寸过大", AjaxErrorCode.ACTION_CODE);
	/** Code specified for error occurs in save file to tfs. */
	public static final AjaxErrorCode AJAX_CODE_SAVE_TFS_FAILED = new AjaxErrorCode(
			-205, "图片无法保存到TFS", AjaxErrorCode.ACTION_CODE);
	public static final AjaxErrorCode AJAX_CODE_CHILD_EXIST = new AjaxErrorCode(
			-206, "当前节点下存在子节点", AjaxErrorCode.ACTION_CODE);

	/** Code specified for ajax error function. */
	private static final AjaxErrorCode AJAX_CODE_ERROR_INFO = new AjaxErrorCode(
			-300, "Ajax访问异常", AjaxErrorCode.ACTION_CODE);

	/** Code stands for the action missing validate function. */
	public static final AjaxErrorCode AJAX_CODE_MISSING_VALIDATE_FUNCTION = new AjaxErrorCode(
			-400, "参数缺少validate函数", AjaxErrorCode.ACTION_CODE);
	/** Code stands for the action access validate function failed. */
	public static final AjaxErrorCode AJAX_CODE_ACCESS_VALIDATE_FUNCTION_FAILED = new AjaxErrorCode(
			-401, "无法访问validate函数", AjaxErrorCode.ACTION_CODE);
	/** Code stands for the service get parameter failed. */
	public static final AjaxErrorCode AJAX_CODE_MISSING_PARAMETER = new AjaxErrorCode(
			-402, "缺少参数", AjaxErrorCode.ACTION_CODE);

	/** The error code. */
	private AjaxErrorCode errorCode;
	/** 系统重启时间 */
	private final Object resetTime;

	/**
	 * Construct a new instance.
	 * 
	 * @param errorCode
	 * The error code.
	 */
	public AjaxResponse(AjaxErrorCode errorCode) {
		this.errorCode = errorCode;
		this.resetTime = ResetAction.getResetTime();
	}
	
	public Object getResetTime() {
		return resetTime;
	}



	/** The data for the response. */
	private Object data;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * The error code system flag.
	 * 
	 * @return True is a system error code, otherwise false.
	 */
	public boolean isSystem() {
		return this.errorCode.system;
	}

	/**
	 * Get the code.
	 * 
	 * @return The code.
	 */
	public int getCode() {
		return this.errorCode.code;
	}

	/**
	 * Get the message.
	 * 
	 * @return The message.
	 */
	public String getMessage() {
		return this.errorCode.message;
	}

	/** For the javaScript method ajaxResponseValidate() in head.jsp. */
	private static final String ajaxResponseValidate;

	static {
		StringBuffer temp = new StringBuffer();
		Field[] fields = AjaxResponse.class.getFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			if (field.getType().equals(AjaxErrorCode.class)) {
				try {
					AjaxErrorCode ajaxErrorCode = (AjaxErrorCode) field
							.get(AjaxResponse.class);
					if (ajaxErrorCode.code < 0 && ajaxErrorCode.system) {
						temp.append(String
								.format("\n\telse if(data.code == %d){\n\twindow.location.href = path + \"/return.jsp?errorInfo=%s\";\n\t}",
										ajaxErrorCode.code, URLEncoder.encode(
												ajaxErrorCode.message, "UTF-8")));
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}

		ajaxResponseValidate = temp.toString();
	}

	/**
	 * Get the javascript method ajaxResponseValidate() in head.jsp.
	 * 
	 * @return The javascript method.
	 */
	public static String getAjaxResponseValidate() {
		return ajaxResponseValidate;
	}

	/**
	 * Get the ajax error info.
	 * 
	 * @return The ajax error info.
	 */
	public static String getAjaxErrorInfo() {
		return AJAX_CODE_ERROR_INFO.message;
	}
}
