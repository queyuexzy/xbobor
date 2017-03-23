package cn.system.basic.filter;

import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import cn.system.basic.global.baseAbstract.BaseAction;
import cn.system.basic.manage.bean.UserBean;
import cn.tools.Util;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * 记录用户操作信息日志的拦截器
 * 
 * @author zhl
 * 
 */
@Controller
@Scope("prototype")
public class LogFilter extends BaseAction implements Interceptor {
	/**
	 * 记录操作日志
	 */
	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		String result = arg0.invoke();
		return result;
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init() {
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1573729708573532059L;
	static Logger log = LoggerFactory.getLogger(LogFilter.class);

}
