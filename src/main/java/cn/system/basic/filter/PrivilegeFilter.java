package cn.system.basic.filter;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cn.system.basic.global.GlobalConstants;
import cn.system.basic.global.baseAbstract.BaseAction;
import cn.system.basic.manage.bean.UserBean;
import cn.tools.CommonSendMeg;
import cn.tools.ajax.AjaxResponse;
import cn.tools.jackjson.JackJson;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * Check if the user has the access right for the request.
 * 
 * @author Luo Yinzhuo
 * @date 2014年9月4日 下午2:17:41
 */
public class PrivilegeFilter extends BaseAction implements Interceptor {
	private static final long serialVersionUID = 4800407239576519809L;
	private static Logger logger = LoggerFactory.getLogger(PrivilegeFilter.class);

	@Override
	public void destroy() {
		logger.info("PrivilegeFilter destroy");
	};

	@Override
	public void init() {
		logger.info("PrivilegeFilter start");
	}

	/**
	 * 判断用户是否有权限访问此连接
	 */
	@Override
	public String intercept(ActionInvocation action) throws Exception {
		return action.invoke();
	}
}
