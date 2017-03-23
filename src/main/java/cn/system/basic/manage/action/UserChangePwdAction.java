package cn.system.basic.manage.action;

import org.springframework.beans.factory.annotation.Autowired;
import cn.system.basic.global.baseAbstract.BaseAction;
import cn.system.basic.manage.bean.UserBean;
import cn.system.basic.manage.dao.UserDao;
import cn.tools.CommonSendMeg;
import cn.tools.SecurityTools;
import cn.tools.ajax.AjaxErrorCode;
import cn.tools.ajax.AjaxResponse;
import cn.tools.jackjson.JackJson;

/**
 * Change user password.
 * 
 * @author Luo Yinzhuo
 * @date 2014年8月22日 下午2:52:24
 */
public class UserChangePwdAction extends BaseAction {
	private static final long serialVersionUID = -1765315224411546384L;
	
	/** The user change password page enter. */
	public final static String PAGE_ENTER = "userChangePwd";

	/** The data access object to manage {@link UserBean}. */
	@Autowired
	private UserDao mUserDao;

	/**
	 * Direct to the index page.
	 * 
	 * @return The index page tag.
	 */
	public String toIndex() {
		return TOINDEX;
	}

	/** The key to map old password in the request. */
	private static final String KEY_OLD_PASSWORD = "oldPassword";
	/** The key to map new password in the request. */
	private static final String KEY_NEW_PASSWORD = "newPassword";

	private static final AjaxErrorCode OLD_PASSWORD_ERROR = new AjaxErrorCode(
			-500, "旧密码错误", AjaxErrorCode.ACTION_CODE);
	private static final AjaxErrorCode NEW_PASSWORD_ERROR = new AjaxErrorCode(
			-501, "新密码不合法", AjaxErrorCode.ACTION_CODE);

	/**
	 * Edit the password.
	 * 
	 * @author Luo Yinzhuo
	 * @date 2014年8月29日 上午11:54:47
	 */
	public void editPassword() {
		final AjaxResponse ajaxResponse;
		if (validateOldPassword()) {
			final String strNewPassword = getFromRequestParameter(KEY_NEW_PASSWORD);
			if (strNewPassword == null || strNewPassword.trim().length() < 6) {
				ajaxResponse = new AjaxResponse(NEW_PASSWORD_ERROR);
			} else {
				UserBean user = getCurrentUser();
				user.setPassword(SecurityTools.getMD5(strNewPassword));
				mUserDao.updateUserBeanById(user);
				ajaxResponse = new AjaxResponse(AjaxResponse.AJAX_CODE_SUCCESS);
			}
		} else {
			ajaxResponse = new AjaxResponse(OLD_PASSWORD_ERROR);
		}
		CommonSendMeg.writeMsg(JackJson.fromObjectToJson(ajaxResponse));
	}

	/**
	 * Validate the old password.
	 * 
	 * @author Luo Yinzhuo
	 * @return True if valid, otherwise false.
	 * @date 2014年8月29日 上午11:57:58
	 */
	private boolean validateOldPassword() {
		final String strOldPassword = getFromRequestParameter(KEY_OLD_PASSWORD);
		return strOldPassword != null
				&& SecurityTools.getMD5(strOldPassword).equals(
						getCurrentUser().getPassword());
	}
}
