package cn.system.basic.global.baseAbstract;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cn.system.basic.global.GlobalConstants;
import cn.system.basic.manage.bean.UserBean;
import cn.tools.DateHelper;
import cn.tools.ajax.AjaxResponse;
import cn.tools.jackjson.JackJson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 最基本action 其他aciton 需时他的子类
 * 
 * @author zhl
 * @date 20112011-8-24下午02:30:06
 * 
 */
public abstract class BaseAction extends ActionSupport implements
		ServletResponseAware {

	private static final long serialVersionUID = 8961759601977025276L;

	protected HttpServletResponse response;

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;

	}

	protected Logger logger = LoggerFactory.getLogger(BaseAction.class);
	/*
	 * 跳转到主页面
	 */
	protected final static String TOINDEX = "toIndex";

	/*
	 * 跳转到编辑页面
	 */
	protected final static String TOEDIT = "toEdit";

	/*
	 * 退出系统
	 */
	protected final static String LOGOUT = "logout";
	
	/*
	 * 修改密码
	 */
	protected final static String CHANGE_PASSWORD = "changePassword";

	/*
	 * to 欢迎页面
	 */
	protected final static String TOWELCOME = "toWelcome";

	/*
	 * 没有权限页面
	 */
	protected final static String NOPRIVILEGE = "noPrivilege";

	/**
	 * 放入context数据
	 * 
	 * @author zhl
	 * @date 20112011-8-24下午02:20:42
	 * 
	 * @param key
	 * @param value
	 */
	protected void putToContext(String key, Object value) {
		ActionContext.getContext().put(key, value);
	}

	/**
	 * 从context中取数据
	 * 
	 * @author zhl
	 * @date 20112011-8-24下午02:28:42
	 * 
	 * @param key
	 * @return
	 */
	protected Object getFromContext(String key) {
		return ActionContext.getContext().get(key);
	}

	/**
	 * 放入session数据
	 * 
	 * @author zhl
	 * @date 20112011-8-24下午02:20:56
	 * 
	 * @param key
	 * @param value
	 */
	protected void putToSession(String key, Object value) {
		ServletActionContext.getRequest().getSession().setAttribute(key, value);
	}

	/**
	 * 从session中取数据
	 * 
	 * @author zhl
	 * @date 20112011-8-24下午02:26:22
	 * 
	 * @param key
	 * @return
	 */
	protected static Object getFromSession(String key) {
		return ServletActionContext.getRequest().getSession().getAttribute(key);
	}

	/**
	 * Invalidate the session.
	 * 
	 * @author Luo Yinzhuo
	 * @date 2014年8月25日 下午2:37:57
	 */
	protected static void invalidateSession() {
		ServletActionContext.getRequest().getSession().invalidate();
	}

	/**
	 * 从request Attribute中取数据
	 * 
	 * @author zhl
	 * @date 20112011-8-24下午04:00:17
	 * 
	 * @param key
	 * @return
	 */
	protected Object getFromRequstAttribute(String key) {
		return ServletActionContext.getRequest().getAttribute(key);
	}

	/**
	 * 从request Parameter中取数据
	 * 
	 * @author zhl
	 * @date 20112011-8-25下午09:33:11
	 * 
	 * @param key
	 * @return
	 */
	protected String getFromRequestParameter(String key) {
		return ServletActionContext.getRequest().getParameter(key);
	}

	/**
	 * 设置页面提示信息
	 * 
	 * @author zhl
	 * @date 20112011-8-24下午02:21:03
	 * 
	 * @param msgs
	 */
	protected void setMegs(String[] msgs) {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute(GlobalConstants.GLOBAL_MESSAGES, msgs);
	}

	/**
	 * Get current logged in user.
	 * 
	 * @return The current user or null.
	 */
	public UserBean getCurrentUser() {
		Object user = getFromSession(GlobalConstants.CURRENT_USER);
		if (user != null && user instanceof UserBean) {
			return (UserBean) user;
		}
		return null;
	}

	/**
	 * Get current user id.
	 * 
	 * @author Luo Yinzhuo
	 * @return The current user id.
	 * @date 2014年8月25日 下午2:40:41
	 */
	public int getCurrentUserId() {
		UserBean user = getCurrentUser();
		return user != null ? user.getId() : 0;
	}

	/**
	 * 
	 * @Title: downloadFile
	 * @author zhaoxin1
	 * @Description: 下载文件
	 * @date 2014年9月25日 上午10:30:38
	 */
	protected void downloadFile(String filePath) throws Exception {
		File f = new File(filePath);
		if (!f.exists()) {
			response.sendError(404, "File not found!");
			return;
		}
		BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
		byte[] buf = new byte[1024];
		int len = 0;
		response.reset();
		response.setContentType("application/x-msdownload");
		response.setHeader("Content-Disposition",
				"attachment; filename=" + f.getName());
		OutputStream out = response.getOutputStream();
		while ((len = br.read(buf)) > 0)
			out.write(buf, 0, len);
		br.close();
		out.close();
	}

	/**
	 * 
	 * @Title: downloadFile
	 * @author zhaoxin1
	 * @Description: 下载文件
	 * @date 2014年9月25日 上午10:30:38
	 */
	protected void downloadFile(HSSFWorkbook hwb, String fileFame,
			String extension) throws Exception {
		response.reset();
		response.setContentType("application/x-msdownload");
		response.setHeader(
				"Content-Disposition",
				"attachment; filename="
						+ fileFame
						+ DateHelper
								.getNowDate(DateHelper.FMT_DATE_DATETIME_TIGHT)
						+ "." + extension);
		OutputStream out = response.getOutputStream();
		hwb.write(out);
		out.close();
	}

	/** The validate function prefix. */
	protected static final String VALIDATE_PREFIX = "validate";

	/**
	 * Extract the specified parameters in the request.
	 * 
	 * @param keys
	 *            The specified parameter keys.
	 * @return The request parameter.
	 * @throws RequestParameterException
	 *             If any error occurs.
	 * @author Luo Yinzhuo
	 * @date 2015年2月10日 下午3:06:56
	 */
	protected RequestParameter extractParameter(String[] keys)
			throws RequestParameterException {
		final RequestParameter param = new RequestParameter();
		for (String key : keys) {
			final String value = getFromRequestParameter(key);
			final String methodName = VALIDATE_PREFIX.concat(
					key.substring(0, 1).toUpperCase()).concat(key.substring(1));

			try {
				Method validate = this.getClass().getMethod(methodName,
						String.class);
				if (Boolean.TRUE.equals(validate.invoke(this, value))) {
					param.put(key, value);
				} else {
					final AjaxResponse ajaxResponse = new AjaxResponse(
							AjaxResponse.AJAX_CODE_ILLEGAL_PARAM);
					ajaxResponse.setData(String.format("参数错误！ key:%s value:%s",
							key, (value != null ? value : "null")));
					throw new RequestParameterException(
							JackJson.fromObjectToJson(ajaxResponse));
				}
			} catch (NoSuchMethodException e) {
				final AjaxResponse ajaxResponse = new AjaxResponse(
						AjaxResponse.AJAX_CODE_MISSING_VALIDATE_FUNCTION);
				ajaxResponse.setData(String.format("缺少验证函数！ key:%s", key));
				throw new RequestParameterException(
						JackJson.fromObjectToJson(ajaxResponse));
			} catch (SecurityException e) {
				final AjaxResponse ajaxResponse = new AjaxResponse(
						AjaxResponse.AJAX_CODE_ACCESS_VALIDATE_FUNCTION_FAILED);
				ajaxResponse.setData(String.format("无法访问验证函数！ key:%s", key));
				throw new RequestParameterException(
						JackJson.fromObjectToJson(ajaxResponse));
			} catch (IllegalAccessException e) {
				final AjaxResponse ajaxResponse = new AjaxResponse(
						AjaxResponse.AJAX_CODE_ACCESS_VALIDATE_FUNCTION_FAILED);
				ajaxResponse.setData(String.format("无法访问验证函数！ key:%s", key));
				throw new RequestParameterException(
						JackJson.fromObjectToJson(ajaxResponse));
			} catch (IllegalArgumentException e) {
				final AjaxResponse ajaxResponse = new AjaxResponse(
						AjaxResponse.AJAX_CODE_ILLEGAL_PARAM);
				ajaxResponse.setData(String.format("参数错误！ key:%s value:%s",
						key, (value != null ? value : "null")));
				throw new RequestParameterException(
						JackJson.fromObjectToJson(ajaxResponse));
			} catch (InvocationTargetException e) {
				final AjaxResponse ajaxResponse = new AjaxResponse(
						AjaxResponse.AJAX_CODE_ACCESS_VALIDATE_FUNCTION_FAILED);
				ajaxResponse.setData(String.format("无法访问验证函数！ key:%s", key));
				throw new RequestParameterException(
						JackJson.fromObjectToJson(ajaxResponse));
			}
		}
		return param;
	}

	/**
	 * A wrapper class to wrap the parameter in the request.
	 * 
	 * @author Luo Yinzhuo
	 * @date 2015年2月10日 下午3:55:41
	 */
	public class RequestParameter {
		/** The parameter map. */
		private final Map<String, String> parameterMap = new HashMap<String, String>();

		/**
		 * Get the specified key's parameter.
		 * 
		 * @param key
		 *            The parameter key.
		 * @return The parameter.
		 * @throws RequestParameterException
		 *             If the parameter doesn't exist.
		 * @author Luo Yinzhuo
		 * @date 2015年2月10日 下午3:56:23
		 */
		public String get(String key) throws RequestParameterException {
			if (parameterMap.containsKey(key)) {
				return parameterMap.get(key);
			} else {
				final AjaxResponse ajaxResponse = new AjaxResponse(
						AjaxResponse.AJAX_CODE_MISSING_PARAMETER);
				ajaxResponse.setData(String.format("缺少参数！ key:%s", key));
				throw new RequestParameterException(
						JackJson.fromObjectToJson(ajaxResponse));
			}
		}

		/**
		 * Put a key-parameter pair.
		 * 
		 * @param key
		 *            The key.
		 * @param value
		 *            The parameter.
		 * @author Luo Yinzhuo
		 * @date 2015年2月10日 下午3:57:11
		 */
		void put(String key, String value) {
			parameterMap.put(key, value);
		}
	}

	/**
	 * The exception occurs during extraction request parameters.
	 * 
	 * @author Luo Yinzhuo
	 * @date 2015年2月10日 下午3:30:59
	 */
	public static class RequestParameterException extends Exception {
		private static final long serialVersionUID = 4333335657286971633L;

		public RequestParameterException(String message) {
			super(message);
		}
	}

	/**
	 * Validate the flexiGrid query json.
	 * 
	 * @param flexiGridQuery
	 * The flexiGrid query json.
	 * @return True if valid, otherwise false.
	 * @author Luo Yinzhuo
	 * @date 2015年2月10日 下午3:00:25
	 */
	public boolean validateQuery_json(String flexiGridQuery) {
		return flexiGridQuery != null && flexiGridQuery.length() > 0;
	}
	
	/**
	 * 验证不能为空的参数
	 * 
	 * @return 参数中有null则返回true，相反返回false
	 * @author Le Sheng
	 * @date 2015年4月8日 下午2:55:22
	 */
	public boolean hasBlankParam(Object... objects) {
		if (null == objects) {
			return false;
		}
		
		for (Object object : objects) {
			if (null == object) {
				return true;
			}
		}
		return false;
	}
}
