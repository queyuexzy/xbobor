package cn.system.basic.manage.action;

import cn.system.basic.global.baseAbstract.BaseAction;
import cn.tools.ajax.AjaxResponse;
/**
 * The login page.
 * 
 * @author Luo Yinzhuo
 * @date 2014年8月22日 下午3:14:20
 */
public class LoginAction extends BaseAction {
	private static final long serialVersionUID = -6133447934521944427L;
	/**
	 * Login system.
	 * 
	 * @throws Exception
	 */
	public void login() throws Exception {
		AjaxResponse ajaxResponse;
		String tel = this.getFromRequestParameter("tel");
		if(null == tel || "".equals(tel)){
			ajaxResponse = new AjaxResponse(AjaxResponse.AJAX_CODE_ILLEGAL_PARAM);
		}else{
//			this.putToSession("tel", tel);
//			ajaxResponse = new AjaxResponse(AjaxResponse.AJAX_CODE_SUCCESS);
//			CartBean cartBean = cartService.getCartBeanByUserId(tel);
//			if(null == cartBean){
//				cartService.addCartBeanByUserId(tel);
//			}
		}
//		CommonSendMeg.writeMsg(JackJson.fromObjectToJson(ajaxResponse));
	}
}
