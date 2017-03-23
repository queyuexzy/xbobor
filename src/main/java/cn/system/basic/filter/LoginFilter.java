package cn.system.basic.filter;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.system.basic.global.GlobalConstants;
import cn.system.basic.global.baseAbstract.BaseAction;
import cn.tools.ajax.AjaxResponse;

import com.exception.NotLoginException;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

@Controller
@Scope("prototype")
/*
 * 判断用户session信息的拦截器
 */
public class LoginFilter extends BaseAction implements Interceptor {
	private static final long serialVersionUID = -7910829141441069810L;
	private static Logger logger = LoggerFactory.getLogger(LoginFilter.class);

	@Override
	public void destroy() {
		logger.info("LoginFilter destroy");
	}

	@Override
	public void init() {
		logger.info("LoginFilter start");
	}

	/**
	 * 判断用户是否已经登录 如果session失效，需重新登录 若为ajax请求，session -5 失效时写回页面 值 -1 || null 为异常
	 * 
	 * @author zhl
	 * @date 2011-8-31上午11:00:33
	 * 
	 * @param arg0
	 * @return
	 * @throws NotLoginException 
	 */
	@Override
	public String intercept(ActionInvocation arg0) throws NotLoginException {
		// 从session中取出用户信息，如果为null，跳转到login.jsp，不为null，继续执行方法
		System.out.println(ServletActionContext.getRequest().getRequestURI());
		///mainMethod
		String requestURI = ServletActionContext.getRequest().getRequestURI();
		
		//登陆才可访问的链接
		if (requestURI.indexOf("yes") > 0 ){
			HttpSession session = ServletActionContext.getRequest().getSession();
			Object tel = session.getAttribute("tel");
			if(null == tel || "".equals(tel)){
				ServletActionContext.getRequest().getSession().invalidate();
				return LOGOUT;
			}
		}
		try {
			return arg0.invoke();
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
}
