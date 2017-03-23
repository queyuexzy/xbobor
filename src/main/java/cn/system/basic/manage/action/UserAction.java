package cn.system.basic.manage.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import cn.system.basic.global.baseAbstract.BaseAction;
import cn.system.basic.manage.bean.DeptBean;
import cn.system.basic.manage.bean.MenuBean;
import cn.system.basic.manage.bean.PostBean;
import cn.system.basic.manage.bean.RoleBean;
import cn.system.basic.manage.bean.UserBean;
import cn.system.basic.manage.dao.DeptDao;
import cn.system.basic.manage.dao.MenuDao;
import cn.system.basic.manage.dao.PostDao;
import cn.system.basic.manage.dao.RoleDao;
import cn.system.basic.manage.dao.UserDao;
import cn.system.basic.manage.dao.UserMenuDao;
import cn.system.basic.manage.dao.UserRoleDao;
import cn.tools.CommonSendMeg;
import cn.tools.FastDateFormatHelper;
import cn.tools.SecurityTools;
import cn.tools.ajax.AjaxResponse;
import cn.tools.flexigrid.bean.FlexiGrid;
import cn.tools.jackjson.JackJson;

/**
 * User management.
 * 
 * @author luoyinzhuo
 */
public class UserAction extends BaseAction {
	private static final long serialVersionUID = -6746424507792484906L;

	/**
	 * Direct to the index page.
	 * 
	 * @return The index page tag.
	 */
	public String toIndex() {
		return TOINDEX;
	}

	/** The data access object to manage {@link UserBean}. */
	@Autowired
	private UserDao mUserDao;
	/** The data access object to manage {@link DeptBean}. */
	@Autowired
	private DeptDao mDeptDao;
	/** The data access object to manage {@link MenuBean}. */
	@Autowired
	private MenuDao mMenuDao;
	/** The data access object to manage {@link PostBean}. */
	@Autowired
	private PostDao mPostDao;
	/** The data access object to manage {@link RoleBean}. */
	@Autowired
	private RoleDao mRoleDao;
	/** The data access object to manage {@link UserRoleBean}. */
	@Autowired
	private UserRoleDao mUserRoleDao;
	/** The data access object to manage {@link UserMenuBean}. */
	@Autowired
	private UserMenuDao mUserMenuDao;

	/**
	 * Used to search user for flexigrid use.
	 */
	public void searchUserForFlexiGrid() {
		final String flexiGridQuery = getFromRequestParameter(FlexiGrid.KEY_QUERY_JSON);
		final String result;
		if (flexiGridQuery != null && flexiGridQuery.length() > 0) {
			FlexiGrid flexiGrid = JackJson.fromJsonToObject(flexiGridQuery,
					FlexiGrid.class);
			mUserDao.getUserBeanList(flexiGrid);
			result = JackJson.fromObjectToJson(flexiGrid);
		} else {
			result = JackJson
					.fromObjectToJson(AjaxResponse.AJAX_CODE_ILLEGAL_PARAM);
		}
		CommonSendMeg.writeMsg(result);
	}

	/**
	 * Used to search department for ztree use.
	 */
	public void searchDeptForZtree() {
		final String strPid = getFromRequestParameter(DeptDao.KEY_ID);
		final AjaxResponse ajaxResponse;
		if (strPid == null) {
			List<DeptBean> deptList = mDeptDao
					.getDeptBeanListByParentId(DeptDao.ROOT_DEPT_ID);
			ajaxResponse = new AjaxResponse(AjaxResponse.AJAX_CODE_SUCCESS);
			ajaxResponse.setData(deptList);
		} else if (StringUtils.isNumeric(strPid) && strPid.length() > 0) {
			List<DeptBean> deptList = mDeptDao
					.getDeptBeanListByParentId(Integer.valueOf(strPid));
			ajaxResponse = new AjaxResponse(AjaxResponse.AJAX_CODE_SUCCESS);
			ajaxResponse.setData(deptList);
		} else {
			ajaxResponse = new AjaxResponse(
					AjaxResponse.AJAX_CODE_ILLEGAL_PARAM);
		}
		CommonSendMeg.writeMsg(JackJson.fromObjectToJson(ajaxResponse));
	}

	/**
	 * Used to search menu for ztree use.
	 */
	public void searchMenuForZtree() {
		final String strPid = getFromRequestParameter(MenuDao.KEY_ID);
		final AjaxResponse ajaxResponse;
		if (strPid == null || MenuDao.ROOT_DEPT_ID.toString().equals(strPid)) {
			List<MenuBean> menuList = mMenuDao
					.getMenuBeanListByParentId(MenuDao.ROOT_DEPT_ID);
			ajaxResponse = new AjaxResponse(AjaxResponse.AJAX_CODE_SUCCESS);
			ajaxResponse.setData(menuList);
		} else if (StringUtils.isNumeric(strPid) && strPid.length() > 0) {
			List<MenuBean> menuList = mMenuDao
					.getMenuBeanListByParentId(Integer.valueOf(strPid));
			ajaxResponse = new AjaxResponse(AjaxResponse.AJAX_CODE_SUCCESS);
			ajaxResponse.setData(menuList);
		} else {
			ajaxResponse = new AjaxResponse(
					AjaxResponse.AJAX_CODE_ILLEGAL_PARAM);
		}
		CommonSendMeg.writeMsg(JackJson.fromObjectToJson(ajaxResponse));
	}

	/**
	 * Used to search role for ztree use.
	 */
	public void searchRoleForZtree() {
		final AjaxResponse ajaxResponse;
		List<RoleBean> roleList = mRoleDao.getRoleBeanList();
		ajaxResponse = new AjaxResponse(AjaxResponse.AJAX_CODE_SUCCESS);
		ajaxResponse.setData(roleList);
		CommonSendMeg.writeMsg(JackJson.fromObjectToJson(ajaxResponse));
	}

	/**
	 * Used to search post.
	 */
	public void searchPost() {
		List<PostBean> postList = mPostDao.getPostBeanList();
		final AjaxResponse ajaxResponse = new AjaxResponse(
				AjaxResponse.AJAX_CODE_SUCCESS);
		ajaxResponse.setData(postList);
		CommonSendMeg.writeMsg(JackJson.fromObjectToJson(ajaxResponse));
	}

	/**
	 * Used to add a user.
	 */
	public void addUser() {
		final AjaxResponse ajaxResponse;
		if (validateUserName() && validateSex() && validateBirthday()
				&& validateJournalType() && validateSeparationFlag()) {
			UserBean userBean = new UserBean();
			userBean.setUserName(getFromRequestParameter(UserDao.KEY_USER_NAME));
			userBean.setRealName(getFromRequestParameter(UserDao.KEY_REAL_NAME));
			userBean.setSex(Integer
					.valueOf(getFromRequestParameter(UserDao.KEY_SEX)));
			userBean.setBirthday(getFromRequestParameter(UserDao.KEY_BIRTHDAY));
			userBean.setAddress(getFromRequestParameter(UserDao.KEY_ADDRESS));
			userBean.setJournalType(Integer
					.valueOf(getFromRequestParameter(UserDao.KEY_JOURNAL_TYPE)));
			userBean.setTelephone(getFromRequestParameter(UserDao.KEY_TELEPHONE));
			userBean.setMobile(getFromRequestParameter(UserDao.KEY_MOBILE));
			userBean.setEmail(getFromRequestParameter(UserDao.KEY_EMAIL));
			userBean.setZipCode(getFromRequestParameter(UserDao.KEY_ZIP_CODE));
			userBean.setEducation(getFromRequestParameter(UserDao.KEY_EDUCATION));
			userBean.setSeparationFlag(Integer
					.valueOf(getFromRequestParameter(UserDao.KEY_SEPARATION_FLAG)));

			final String strDeptId = getFromRequestParameter(UserDao.KEY_DEPT_ID);
			if (StringUtils.isNumeric(strDeptId) && strDeptId.length() > 0) {
				userBean.setDeptId(Integer.valueOf(strDeptId));
			}

			final String strPostId = getFromRequestParameter(UserDao.KEY_POST_ID);
			if (StringUtils.isNumeric(strPostId) && strPostId.length() > 0) {
				userBean.setPostId(Integer.valueOf(strPostId));
			}

			userBean.setCreateTime(FastDateFormatHelper.DEFAULT
					.format(new Date()));
			if (mUserDao.addUserBean(userBean)) {
				ajaxResponse = new AjaxResponse(AjaxResponse.AJAX_CODE_SUCCESS);
			} else {
				ajaxResponse = new AjaxResponse(
						AjaxResponse.AJAX_CODE_RESULT_REPEAT);
			}
		} else {
			ajaxResponse = new AjaxResponse(
					AjaxResponse.AJAX_CODE_ILLEGAL_PARAM);
		}
		CommonSendMeg.writeMsg(JackJson.fromObjectToJson(ajaxResponse));
	}

	/**
	 * Used to get a user specified by id.
	 */
	public void getUserById() {
		final AjaxResponse ajaxResponse;
		// Check the incoming parameters one by one.
		if (validateId()) {
			UserBean user = mUserDao.getUserBeanById(Integer
					.valueOf(getFromRequestParameter(UserDao.KEY_ID)));
			if (user == null) {
				ajaxResponse = new AjaxResponse(
						AjaxResponse.AJAX_CODE_RESULT_NOT_FOUND);
			} else {
				ajaxResponse = new AjaxResponse(AjaxResponse.AJAX_CODE_SUCCESS);
				ajaxResponse.setData(user);
			}
		} else {
			ajaxResponse = new AjaxResponse(
					AjaxResponse.AJAX_CODE_ILLEGAL_PARAM);
		}
		CommonSendMeg.writeMsg(JackJson.fromObjectToJson(ajaxResponse));
	}

	/**
	 * Used to update a user specified by id.
	 */
	public void updateUserById() {
		final AjaxResponse ajaxResponse;
		// Check the incoming parameters one by one.
		if (validateId()) {
			UserBean user = mUserDao.getUserBeanById(Integer
					.valueOf(getFromRequestParameter(UserDao.KEY_ID)));

			if (user == null) {
				ajaxResponse = new AjaxResponse(
						AjaxResponse.AJAX_CODE_RESULT_NOT_FOUND);
			} else {
				if (validateRealName()) {
					user.setRealName(getFromRequestParameter(UserDao.KEY_REAL_NAME));
				}

				if (validateSex()) {
					user.setSex(Integer
							.valueOf(getFromRequestParameter(UserDao.KEY_SEX)));
				}

				if (validateBirthday()) {
					user.setBirthday(getFromRequestParameter(UserDao.KEY_BIRTHDAY));
				}

				if (validateAddress()) {
					user.setAddress(getFromRequestParameter(UserDao.KEY_ADDRESS));
				}

				if (validateJournalType()) {
					user.setJournalType(Integer
							.valueOf(getFromRequestParameter(UserDao.KEY_JOURNAL_TYPE)));
				}

				if (validateTelephone()) {
					user.setTelephone(getFromRequestParameter(UserDao.KEY_TELEPHONE));
				}

				if (validateMobile()) {
					user.setMobile(getFromRequestParameter(UserDao.KEY_MOBILE));
				}

				if (validateEmail()) {
					user.setEmail(getFromRequestParameter(UserDao.KEY_EMAIL));
				}

				if (validateZipCode()) {
					user.setZipCode(getFromRequestParameter(UserDao.KEY_ZIP_CODE));
				}

				if (validateEducation()) {
					user.setEducation(getFromRequestParameter(UserDao.KEY_EDUCATION));
				}

				if (validateSeparationFlag()) {
					user.setSeparationFlag(Integer
							.valueOf(getFromRequestParameter(UserDao.KEY_SEPARATION_FLAG)));
				}

				final String strDeptId = getFromRequestParameter(UserDao.KEY_DEPT_ID);
				if ("null".equals(strDeptId)) {
					user.setDeptId(null);
				} else if (StringUtils.isNumeric(strDeptId)
						&& strDeptId.length() > 0) {
					user.setDeptId(Integer.valueOf(strDeptId));
				}

				final String strPostId = getFromRequestParameter(UserDao.KEY_POST_ID);
				if ("null".equals(strPostId)) {
					user.setPostId(null);
				} else if (StringUtils.isNumeric(strPostId)
						&& strPostId.length() > 0) {
					user.setPostId(Integer.valueOf(strPostId));
				}

				user.setEditTime(FastDateFormatHelper.DEFAULT
						.format(new Date()));
				if (mUserDao.updateUserBeanById(user)) {
					ajaxResponse = new AjaxResponse(
							AjaxResponse.AJAX_CODE_SUCCESS);
				} else {
					ajaxResponse = new AjaxResponse(
							AjaxResponse.AJAX_CODE_RESULT_NOT_FOUND);
				}
			}
		} else {
			ajaxResponse = new AjaxResponse(
					AjaxResponse.AJAX_CODE_ILLEGAL_PARAM);
		}
		CommonSendMeg.writeMsg(JackJson.fromObjectToJson(ajaxResponse));
	}

	/**
	 * Used to delete a user specified by id.
	 */
	public void deleteUserById() {
		final AjaxResponse ajaxResponse;
		// Check the incoming parameters one by one.
		if (validateId()) {
			mUserDao.deleteUserBeanById(Integer
					.valueOf(getFromRequestParameter(UserDao.KEY_ID)));
			ajaxResponse = new AjaxResponse(AjaxResponse.AJAX_CODE_SUCCESS);
		} else {
			ajaxResponse = new AjaxResponse(
					AjaxResponse.AJAX_CODE_ILLEGAL_PARAM);
		}
		CommonSendMeg.writeMsg(JackJson.fromObjectToJson(ajaxResponse));
	}

	/**
	 * Used to delete a user specified by id.
	 */
	public void updateInitUserPasswordById() {
		final AjaxResponse ajaxResponse;
		// Check the incoming parameters one by one.
		if (validateId()) {
			UserBean user = mUserDao.getUserBeanById(Integer
					.valueOf(getFromRequestParameter(UserDao.KEY_ID)));
			if (user == null) {
				ajaxResponse = new AjaxResponse(
						AjaxResponse.AJAX_CODE_RESULT_NOT_FOUND);
			} else {
				user.setPassword(SecurityTools.getMD5(UserDao.PASSWORD_DEFAULT));
				user.setEditTime(FastDateFormatHelper.DEFAULT
						.format(new Date()));
				if (mUserDao.updateUserBeanById(user)) {
					ajaxResponse = new AjaxResponse(
							AjaxResponse.AJAX_CODE_SUCCESS);
				} else {
					ajaxResponse = new AjaxResponse(
							AjaxResponse.AJAX_CODE_RESULT_NOT_FOUND);
				}
			}
		} else {
			ajaxResponse = new AjaxResponse(
					AjaxResponse.AJAX_CODE_ILLEGAL_PARAM);
		}
		CommonSendMeg.writeMsg(JackJson.fromObjectToJson(ajaxResponse));
	}

	/**
	 * Validate the user id in the request parameter.
	 * 
	 * @return True if valid, otherwise false.
	 */
	private boolean validateId() {
		final String strId = getFromRequestParameter(UserDao.KEY_ID);
		return StringUtils.isNumeric(strId) && strId.length() > 0;
	}

	/**
	 * Validate the user name in the request parameter.
	 * 
	 * @return True if valid, otherwise false.
	 */
	private boolean validateUserName() {
		final String userName = getFromRequestParameter(UserDao.KEY_USER_NAME);
		return userName != null && Pattern.matches("[0-9_a-zA-Z]+", userName);
	}

	/**
	 * Validate the real name in the request parameter.
	 * 
	 * @return True if valid, otherwise false.
	 */
	private boolean validateRealName() {
		final String realName = getFromRequestParameter(UserDao.KEY_REAL_NAME);
		return realName != null;
	}

	/**
	 * Validate the sex in the request parameter.
	 * 
	 * @return True if valid, otherwise false.
	 */
	private boolean validateSex() {
		final String strSex = getFromRequestParameter(UserDao.KEY_SEX);
		return StringUtils.isNumeric(strSex)
				&& (Integer.valueOf(strSex) == UserDao.GENDER_MALE || Integer
						.valueOf(strSex) == UserDao.GENDER_FEMALE);
	}

	/**
	 * Validate the birthday in the request parameter.
	 * 
	 * @return True if valid, otherwise false.
	 */
	private boolean validateBirthday() {
		final String birthday = getFromRequestParameter(UserDao.KEY_BIRTHDAY);
		return birthday != null
				&& Pattern.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}", birthday);
	}

	/**
	 * Validate the address in the request parameter.
	 * 
	 * @return True if valid, otherwise false.
	 */
	private boolean validateAddress() {
		final String address = getFromRequestParameter(UserDao.KEY_ADDRESS);
		return address != null;
	}

	/**
	 * Validate the journal type in the request parameter.
	 * 
	 * @return True if valid, otherwise false.
	 */
	private boolean validateJournalType() {
		final String strJournalType = getFromRequestParameter(UserDao.KEY_JOURNAL_TYPE);
		return StringUtils.isNumeric(strJournalType)
				&& (Integer.valueOf(strJournalType) == UserDao.JOURNAL_TYPE_WORKER
						|| Integer.valueOf(strJournalType) == UserDao.JOURNAL_TYPE_MANAGER || Integer
						.valueOf(strJournalType) == UserDao.JOURNAL_TYPE_LEADER);
	}

	/**
	 * Validate the telephone in the request parameter.
	 * 
	 * @return True if valid, otherwise false.
	 */
	private boolean validateTelephone() {
		final String telephone = getFromRequestParameter(UserDao.KEY_TELEPHONE);
		return telephone != null;
	}

	/**
	 * Validate the mobile in the request parameter.
	 * 
	 * @return True if valid, otherwise false.
	 */
	private boolean validateMobile() {
		final String mobile = getFromRequestParameter(UserDao.KEY_MOBILE);
		return mobile != null;
	}

	/**
	 * Validate the email in the request parameter.
	 * 
	 * @return True if valid, otherwise false.
	 */
	private boolean validateEmail() {
		final String email = getFromRequestParameter(UserDao.KEY_EMAIL);
		return email != null;
	}

	/**
	 * Validate the zip code in the request parameter.
	 * 
	 * @return True if valid, otherwise false.
	 */
	private boolean validateZipCode() {
		final String zipCode = getFromRequestParameter(UserDao.KEY_ZIP_CODE);
		return zipCode != null;
	}

	/**
	 * Validate the education in the request parameter.
	 * 
	 * @return True if valid, otherwise false.
	 */
	private boolean validateEducation() {
		final String education = getFromRequestParameter(UserDao.KEY_EDUCATION);
		return education != null;
	}

	/**
	 * Validate the separation flag in the request parameter.
	 * 
	 * @return True if valid, otherwise false.
	 */
	private boolean validateSeparationFlag() {
		final String strSeparationFlag = getFromRequestParameter(UserDao.KEY_SEPARATION_FLAG);
		return StringUtils.isNumeric(strSeparationFlag)
				&& (Integer.valueOf(strSeparationFlag) == UserDao.SEPARATION_NO || Integer
						.valueOf(strSeparationFlag) == UserDao.SEPARATION_YES);
	}

	/**
	 * Used to search role to grant it to user.
	 */
	public void searchRoleForUser() {
		final String strId = getFromRequestParameter(UserDao.KEY_ID);

		final AjaxResponse ajaxResponse;
		if (StringUtils.isNumeric(strId) && strId.length() > 0) {
			UserBean user = mUserDao.getUserBeanById(Integer.valueOf(strId));
			if (user == null) {
				ajaxResponse = new AjaxResponse(
						AjaxResponse.AJAX_CODE_RESULT_NOT_FOUND);
			} else {
				List<RoleBean> roleList = mRoleDao
						.getRoleBeanListForUserId(Integer.valueOf(strId));
				ajaxResponse = new AjaxResponse(AjaxResponse.AJAX_CODE_SUCCESS);
				ajaxResponse.setData(roleList);
			}
		} else {
			ajaxResponse = new AjaxResponse(
					AjaxResponse.AJAX_CODE_ILLEGAL_PARAM);
		}
		CommonSendMeg.writeMsg(JackJson.fromObjectToJson(ajaxResponse));
	}

	/**
	 * Used to update role for user.
	 */
	public void updateRoleForUser() {
		final String strId = getFromRequestParameter(UserDao.KEY_ID);
		final String strRoleId = getFromRequestParameter(UserRoleDao.KEY_ROLE_ID);

		final AjaxResponse ajaxResponse;
		if (StringUtils.isNumeric(strId) && strId.length() > 0
				&& strRoleId != null) {
			UserBean user = mUserDao.getUserBeanById(Integer.valueOf(strId));
			if (user == null) {
				ajaxResponse = new AjaxResponse(
						AjaxResponse.AJAX_CODE_RESULT_NOT_FOUND);
			} else {
				final List<Integer> roleIds = new ArrayList<Integer>();
				final String[] strRoleIds = strRoleId.split(",");
				for (int i = 0; i < strRoleIds.length; i++) {
					if (StringUtils.isNumeric(strRoleIds[i])
							&& strRoleIds[i].length() > 0) {
						roleIds.add(Integer.valueOf(strRoleIds[i]));
					}
				}

				mUserRoleDao.updateUserRoleBeanList(Integer.valueOf(strId),
						roleIds, getCurrentUserId());
				ajaxResponse = new AjaxResponse(AjaxResponse.AJAX_CODE_SUCCESS);
			}
		} else {
			ajaxResponse = new AjaxResponse(
					AjaxResponse.AJAX_CODE_ILLEGAL_PARAM);
		}
		CommonSendMeg.writeMsg(JackJson.fromObjectToJson(ajaxResponse));
	}

	/**
	 * Used to search menu to grant it to user.
	 */
	public void searchMenuForUser() {
		final AjaxResponse ajaxResponse;
		if (validateId()) {
			final String strId = getFromRequestParameter(UserDao.KEY_ID);
			UserBean user = mUserDao.getUserBeanById(Integer.valueOf(strId));
			if (user == null) {
				ajaxResponse = new AjaxResponse(
						AjaxResponse.AJAX_CODE_RESULT_NOT_FOUND);
			} else {
				List<MenuBean> menuList = mMenuDao
						.getMenuBeanListForUserId(Integer.valueOf(strId));
				ajaxResponse = new AjaxResponse(AjaxResponse.AJAX_CODE_SUCCESS);
				ajaxResponse.setData(menuList);
			}
		} else {
			ajaxResponse = new AjaxResponse(
					AjaxResponse.AJAX_CODE_ILLEGAL_PARAM);
		}
		CommonSendMeg.writeMsg(JackJson.fromObjectToJson(ajaxResponse));
	}

	/**
	 * Used to update menu for user.
	 */
	public void updateMenuForUser() {
		final String strId = getFromRequestParameter(UserDao.KEY_ID);
		final String strMenuId = getFromRequestParameter(UserMenuDao.KEY_MENU_ID);

		final AjaxResponse ajaxResponse;
		if (StringUtils.isNumeric(strId) && strId.length() > 0
				&& strMenuId != null) {
			UserBean user = mUserDao.getUserBeanById(Integer.valueOf(strId));
			if (user == null) {
				ajaxResponse = new AjaxResponse(
						AjaxResponse.AJAX_CODE_RESULT_NOT_FOUND);
			} else {
				final List<Integer> menuIds = new ArrayList<Integer>();
				final String[] strMenuIds = strMenuId.split(",");
				for (int i = 0; i < strMenuIds.length; i++) {
					if (StringUtils.isNumeric(strMenuIds[i])
							&& strMenuIds[i].length() > 0) {
						menuIds.add(Integer.valueOf(strMenuIds[i]));
					}
				}

				mUserMenuDao.updateUserMenuBeanList(Integer.valueOf(strId),
						menuIds, getCurrentUserId());
				ajaxResponse = new AjaxResponse(AjaxResponse.AJAX_CODE_SUCCESS);
			}
		} else {
			ajaxResponse = new AjaxResponse(
					AjaxResponse.AJAX_CODE_ILLEGAL_PARAM);
		}
		CommonSendMeg.writeMsg(JackJson.fromObjectToJson(ajaxResponse));
	}
}
