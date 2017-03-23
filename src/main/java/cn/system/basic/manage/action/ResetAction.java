package cn.system.basic.manage.action;

import java.util.Map;
import cn.system.basic.global.baseAbstract.BaseAction;
import cn.tools.CommonSendMeg;
import cn.tools.ajax.AjaxResponse;
import cn.tools.jackjson.JackJson;
import com.opensymphony.xwork2.ActionContext;

/**
 * 设置重启时间
 * 
 * @author luoyinzhuo
 */
public class ResetAction extends BaseAction {
	private static final long serialVersionUID = -6746424507792484906L;

	/**
	 * Direct to the index page.
	 * 
	 * @return The index page tag.
	 */
	public String toIndex() {
		return TOINDEX;
	}

	/** 重启时间Key */
	public final static String KEY_RESET_TIME = "resetTime";

	/**
	 * 更新重启时间
	 * 
	 * @author Luo Yinzhuo
	 * @date 2015年5月28日 上午9:54:22
	 */
	public void updateResetTime() {
		final Map<String, Object> application = ActionContext.getContext()
				.getApplication();
		AjaxResponse ajaxResponse;
		final String time = getFromRequestParameter("time");
		application.put(KEY_RESET_TIME, time);
		ajaxResponse = new AjaxResponse(AjaxResponse.AJAX_CODE_SUCCESS);
		ajaxResponse.setData(time);
		CommonSendMeg.writeMsg(JackJson.fromObjectToJson(ajaxResponse));
	}

	/**
	 * 获取系统重启时间
	 * 
	 * @return 系统重启时间
	 * @author Luo Yinzhuo
	 * @date 2015年5月28日 下午2:56:48
	 */
	public final static Object getResetTime() {
		return ActionContext.getContext().getApplication().get(KEY_RESET_TIME);
	}
}
