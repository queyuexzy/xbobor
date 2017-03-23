package cn.system.basic.filter;

import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cn.system.basic.global.baseAbstract.BaseAction;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
/**
 * 清除服务器端缓存的拦截器
 * @author zhl
 *
 */
public class BaseFilter extends BaseAction implements Interceptor{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1671123146973547291L;
	private static Logger logger = LoggerFactory.getLogger(BaseFilter.class);

	@Override
	public void destroy() {
		logger.info("BaseFilter destroy");
	}

	@Override
	public void init() {
		logger.info("BaseFilter start");
	}
	/**
	 * 清除缓存
	 */
	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Cache-Control", "no-store");
		response.setDateHeader("Expires", 0);
		return  arg0.invoke();
	}
}
