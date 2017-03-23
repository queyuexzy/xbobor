package cn.system.basic.manage.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import cn.system.basic.global.baseAbstract.BaseAction;
import cn.system.basic.manage.bean.MenuBean;
import cn.system.basic.manage.bean.RoleBean;
import cn.system.basic.manage.dao.MenuDao;
import cn.system.basic.manage.dao.RoleDao;
import cn.system.basic.manage.dao.RoleMenuDao;
import cn.system.basic.manage.dao.UserMenuDao;
import cn.tools.CommonSendMeg;
import cn.tools.FastDateFormatHelper;
import cn.tools.ajax.AjaxResponse;
import cn.tools.flexigrid.bean.FlexiGrid;
import cn.tools.jackjson.JackJson;

/**
 * Role management.
 * 
 * @author luoyinzhuo
 */
public class RoleAction extends BaseAction{
	private static final long serialVersionUID = -4201915237360590002L;

	/**
	 * Direct to the index page.
	 * 
	 * @return The index page tag.
	 */
	public String toIndex() {
		return TOINDEX;
	}
	
	/** The data access object to manage {@link RoleBean}. */
	@Autowired
	private RoleDao mRoleDao;
	/** The data access object to manage {@link MenuBean}. */
	@Autowired
	private MenuDao mMenuDao;
	/** The data access object to manage {@link RoleMenuBean}. */
	@Autowired
	private RoleMenuDao mRoleMenuDao;
	
	/**
	 * Used to search role for flexigrid use.
	 */
	public void searchRoleForFlexigrid() {
		final String flexiGridQuery = getFromRequestParameter(FlexiGrid.KEY_QUERY_JSON);
		final String result;
		if (flexiGridQuery != null && flexiGridQuery.length() > 0) {
			FlexiGrid flexiGrid = JackJson.fromJsonToObject(flexiGridQuery,
					FlexiGrid.class);
			mRoleDao.getRoleBeanList(flexiGrid);
			result = JackJson.fromObjectToJson(flexiGrid);
		} else {
			result = JackJson
					.fromObjectToJson(AjaxResponse.AJAX_CODE_ILLEGAL_PARAM);
		}
		CommonSendMeg.writeMsg(result);
	}
	
	/**
	 * Add a role.
	 */
	public void addRole() {
		final AjaxResponse ajaxResponse;
		// Check the incoming parameters one by one.
		if (validateRoleName()) {
			final String roleName = getFromRequestParameter(RoleDao.KEY_ROLE_NAME);
			if (mRoleDao.getRoleBeanByRoleName(roleName) != null) {
				ajaxResponse = new AjaxResponse(
						AjaxResponse.AJAX_CODE_RESULT_REPEAT);
			} else {
				// Construct a bean.
				RoleBean role = new RoleBean();
				role.setRoleName(roleName);
				role.setCreatorId(getCurrentUserId());
				role.setCreateTime(FastDateFormatHelper.DEFAULT
						.format(new Date()));
				role.setUpdateUserId(getCurrentUserId());

				mRoleDao.addRoleBean(role);
				ajaxResponse = new AjaxResponse(AjaxResponse.AJAX_CODE_SUCCESS);
			}
		} else {
			ajaxResponse = new AjaxResponse(
					AjaxResponse.AJAX_CODE_ILLEGAL_PARAM);
		}
		CommonSendMeg.writeMsg(JackJson.fromObjectToJson(ajaxResponse));
	}
	
	/**
	 * Used to get a role specified by id.
	 */
	public void getRoleById() {
		final AjaxResponse ajaxResponse;
		// Check the incoming parameters one by one.
		if (validateId()) {
			RoleBean role = mRoleDao.getRoleBeanById(Integer
					.valueOf(getFromRequestParameter(RoleDao.KEY_ID)));
			if (role == null) {
				ajaxResponse = new AjaxResponse(
						AjaxResponse.AJAX_CODE_RESULT_NOT_FOUND);
			} else {
				ajaxResponse = new AjaxResponse(AjaxResponse.AJAX_CODE_SUCCESS);
				ajaxResponse.setData(role);
			}
		} else {
			ajaxResponse = new AjaxResponse(
					AjaxResponse.AJAX_CODE_ILLEGAL_PARAM);
		}
		CommonSendMeg.writeMsg(JackJson.fromObjectToJson(ajaxResponse));
	}
	
	/**
	 * Update a role.
	 */
	public void updateRoleById() {
		final AjaxResponse ajaxResponse;
		// Check the incoming parameters one by one.
		if (validateId()) {
			RoleBean role = mRoleDao.getRoleBeanById(Integer
					.valueOf(getFromRequestParameter(RoleDao.KEY_ID)));

			if (role == null) {
				ajaxResponse = new AjaxResponse(
						AjaxResponse.AJAX_CODE_RESULT_NOT_FOUND);
			} else {
				final String roleName = getFromRequestParameter(RoleDao.KEY_ROLE_NAME);
				if (mRoleDao.getRoleBeanByRoleName(roleName) != null) {
					ajaxResponse = new AjaxResponse(
							AjaxResponse.AJAX_CODE_RESULT_REPEAT);
				} else {
					role.setRoleName(roleName);
					role.setUpdateUserId(getCurrentUserId());

					mRoleDao.updateRoleBeanById(role);
					ajaxResponse = new AjaxResponse(
							AjaxResponse.AJAX_CODE_SUCCESS);
				}
			}
		} else {
			ajaxResponse = new AjaxResponse(
					AjaxResponse.AJAX_CODE_ILLEGAL_PARAM);
		}
		CommonSendMeg.writeMsg(JackJson.fromObjectToJson(ajaxResponse));
	}
	
	/**
	 * Used to delete a role specified by id.
	 */
	public void deleteRoleById() {
		final AjaxResponse ajaxResponse;
		// Check the incoming parameters one by one.
		if (validateId()) {
			mRoleDao.deleteRoleBeanById(Integer
					.valueOf(getFromRequestParameter(RoleDao.KEY_ID)));
			ajaxResponse = new AjaxResponse(AjaxResponse.AJAX_CODE_SUCCESS);
		} else {
			ajaxResponse = new AjaxResponse(
					AjaxResponse.AJAX_CODE_ILLEGAL_PARAM);
		}
		CommonSendMeg.writeMsg(JackJson.fromObjectToJson(ajaxResponse));
	}
	
	/**
	 * Validate the role id in the request parameter.
	 * 
	 * @return True if valid, otherwise false.
	 */
	private boolean validateId() {
		final String strId = getFromRequestParameter(RoleDao.KEY_ID);
		return StringUtils.isNumeric(strId) && strId.length() > 0;
	}

	/**
	 * Validate the role name in the request parameter.
	 * 
	 * @return True if valid, otherwise false.
	 */
	private boolean validateRoleName() {
		final String roleName = getFromRequestParameter(RoleDao.KEY_ROLE_NAME);
		return roleName != null && roleName.length() > 0;
	}
	
	/**
	 * Used to search menu to grant it to role.
	 */
	public void searchMenuForRole() {
		final AjaxResponse ajaxResponse;
		if (validateId()) {
			final String strId = getFromRequestParameter(RoleDao.KEY_ID);
			RoleBean role = mRoleDao.getRoleBeanById(Integer.valueOf(strId));
			if (role == null) {
				ajaxResponse = new AjaxResponse(
						AjaxResponse.AJAX_CODE_RESULT_NOT_FOUND);
			} else {
				List<MenuBean> menuList = mMenuDao
						.getMenuBeanListForRoleId(Integer.valueOf(strId));
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
	 * Used to update menu for role.
	 */
	public void updateMenuForRole() {
		final String strMenuId = getFromRequestParameter(UserMenuDao.KEY_MENU_ID);

		final AjaxResponse ajaxResponse;
		if (validateId()
				&& strMenuId != null) {
			final String strId = getFromRequestParameter(RoleDao.KEY_ID);
			RoleBean role = mRoleDao.getRoleBeanById(Integer.valueOf(strId));
			if (role == null) {
				ajaxResponse = new AjaxResponse(
						AjaxResponse.AJAX_CODE_RESULT_NOT_FOUND);
			} else {
				final List<Integer> menuIds = new ArrayList<Integer>();
				final String[] strRoleIds = strMenuId.split(",");
				for (int i = 0; i < strRoleIds.length; i++) {
					if (StringUtils.isNumeric(strRoleIds[i])
							&& strRoleIds[i].length() > 0) {
						menuIds.add(Integer.valueOf(strRoleIds[i]));
					}
				}

				mRoleMenuDao.updateRoleMenuBeanList(Integer.valueOf(strId),
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
