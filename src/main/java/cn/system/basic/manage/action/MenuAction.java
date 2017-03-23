package cn.system.basic.manage.action;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import cn.system.basic.global.baseAbstract.BaseAction;
import cn.system.basic.manage.bean.MenuBean;
import cn.system.basic.manage.dao.MenuDao;
import cn.tools.CommonSendMeg;
import cn.tools.FastDateFormatHelper;
import cn.tools.ajax.AjaxResponse;
import cn.tools.jackjson.JackJson;

/**
 * The system menu action.
 * 
 * @author Luo Yinzhuo
 */
public class MenuAction extends BaseAction {

	private static final long serialVersionUID = -5175567436146088215L;

	/**
	 * Direct to the index page.
	 * 
	 * @return The index page tag.
	 */
	public String toIndex() {
		return TOINDEX;
	}

	/** The data access object to manage {@link MenuBean}. */
	@Autowired
	private MenuDao mMenuDao;

	/**
	 * Used to search menu for ztree use.
	 */
	public void searchMenuForZtree() {
		final String strPid = getFromRequestParameter(MenuDao.KEY_ID);
		final AjaxResponse ajaxResponse;
		if (strPid == null || MenuDao.ROOT_DEPT_ID.toString().equals(strPid)) {
			List<MenuBean> menuList = mMenuDao.getMenuBeanListByParentId(MenuDao.ROOT_DEPT_ID);
			ajaxResponse = new AjaxResponse(AjaxResponse.AJAX_CODE_SUCCESS);
			ajaxResponse.setData(menuList);
		} else if (StringUtils.isNumeric(strPid) && strPid.length() > 0) {
			List<MenuBean> menuList = mMenuDao.getMenuBeanListByParentId(Integer.valueOf(strPid));
			ajaxResponse = new AjaxResponse(AjaxResponse.AJAX_CODE_SUCCESS);
			ajaxResponse.setData(menuList);
		} else {
			ajaxResponse = new AjaxResponse(AjaxResponse.AJAX_CODE_ILLEGAL_PARAM);
		}
		CommonSendMeg.writeMsg(JackJson.fromObjectToJson(ajaxResponse));
	}

	/**
	 * Add a menu.
	 */
	public void addMenu() {
		final AjaxResponse ajaxResponse;
		// Check the incoming parameters one by one.
		if (validateMenuName() && validateMenuType() && validateParentId()) {
			final int parentId = Integer.valueOf(getFromRequestParameter(MenuDao.KEY_PARENT_ID));

			if (parentId == MenuDao.ROOT_DEPT_ID || mMenuDao.getMenuBeanById(parentId) != null) {
				// Construct a bean.
				MenuBean menu = new MenuBean();
				menu.setMenuName(getFromRequestParameter(MenuDao.KEY_MENU_NAME));
				menu.setPId(parentId);
				menu.setShowIP(getFromRequestParameter(MenuDao.KEY_SHOW_IP));

				final int menuType = Integer.valueOf(getFromRequestParameter(MenuDao.KEY_MENU_TYPE));
				menu.setMenuType(menuType);
				menu.setCreatorId(getCurrentUserId());
				menu.setCreateTime(FastDateFormatHelper.DEFAULT.format(new Date()));
				menu.setUpdateUserId(getCurrentUserId());

				if (menuType == MenuDao.TYPE_PAGE) {
					if (validatePageURLAndPageEnter()) {
						menu.setPageURL(getFromRequestParameter(MenuDao.KEY_PAGE_URL));
						menu.setPageEnter(getFromRequestParameter(MenuDao.KEY_PAGE_ENTER));
						mMenuDao.addMenuBean(menu);
						ajaxResponse = new AjaxResponse(AjaxResponse.AJAX_CODE_SUCCESS);
					} else {
						ajaxResponse = new AjaxResponse(AjaxResponse.AJAX_CODE_ILLEGAL_PARAM);
					}
				} else {
					mMenuDao.addMenuBean(menu);
					ajaxResponse = new AjaxResponse(AjaxResponse.AJAX_CODE_SUCCESS);
				}
			} else {
				ajaxResponse = new AjaxResponse(AjaxResponse.AJAX_CODE_RESULT_NOT_FOUND);
			}
		} else {
			ajaxResponse = new AjaxResponse(AjaxResponse.AJAX_CODE_ILLEGAL_PARAM);
		}
		CommonSendMeg.writeMsg(JackJson.fromObjectToJson(ajaxResponse));
	}

	/**
	 * Update a menu by its id.
	 */
	public void updateMenuById() {
		final AjaxResponse ajaxResponse;
		// Check the incoming parameters one by one.
		if (validateId()) {
			final int id = Integer.valueOf(getFromRequestParameter(MenuDao.KEY_ID));

			MenuBean menu = mMenuDao.getMenuBeanById(id);
			if (menu == null) {
				ajaxResponse = new AjaxResponse(AjaxResponse.AJAX_CODE_RESULT_NOT_FOUND);
			} else {
				if (validateMenuName()) {
					menu.setMenuName(getFromRequestParameter(MenuDao.KEY_MENU_NAME));
				}
				menu.setShowIP(getFromRequestParameter(MenuDao.KEY_SHOW_IP));

				if (validateIdx()) {
					final int idx = Integer.valueOf(getFromRequestParameter(MenuDao.KEY_IDX));
					if (idx >= 1 && idx <= menu.get_maxIdx() && idx != menu.getIdx()) {
						menu.set_oldIdx(menu.getIdx());
						menu.setIdx(idx);
					}
				}

				if (menu.getMenuType() == MenuDao.TYPE_PAGE && validatePageURLAndPageEnter()) {
					menu.setPageURL(getFromRequestParameter(MenuDao.KEY_PAGE_URL));
					menu.setPageEnter(getFromRequestParameter(MenuDao.KEY_PAGE_ENTER));
				}
				menu.setUpdateUserId(getCurrentUserId());

				mMenuDao.updateMenuBeanById(menu);
				ajaxResponse = new AjaxResponse(AjaxResponse.AJAX_CODE_SUCCESS);
			}
		} else {
			ajaxResponse = new AjaxResponse(AjaxResponse.AJAX_CODE_ILLEGAL_PARAM);
		}
		CommonSendMeg.writeMsg(JackJson.fromObjectToJson(ajaxResponse));
	}

	/**
	 * Delete a menu by its id.
	 */
	public void deleteMenuById() {
		final AjaxResponse ajaxResponse;
		// Check the incoming parameters one by one.
		if (validateId()) {
			final int id = Integer.valueOf(getFromRequestParameter(MenuDao.KEY_ID));

			MenuBean menu = mMenuDao.getMenuBeanById(id);
			if (menu != null) {
				mMenuDao.deleteMenuBeanById(menu);
			}
			ajaxResponse = new AjaxResponse(AjaxResponse.AJAX_CODE_SUCCESS);
		} else {
			ajaxResponse = new AjaxResponse(AjaxResponse.AJAX_CODE_ILLEGAL_PARAM);
		}
		CommonSendMeg.writeMsg(JackJson.fromObjectToJson(ajaxResponse));
	}

	/**
	 * Validate the menu name in the request parameter.
	 * 
	 * @return True if valid, otherwise false.
	 */
	private boolean validateMenuName() {
		final String menuName = getFromRequestParameter(MenuDao.KEY_MENU_NAME);
		return menuName != null && menuName.trim().length() > 0;
	}

	/**
	 * Validate the menu type in the request parameter.
	 * 
	 * @return True if valid, otherwise false.
	 */
	private boolean validateMenuType() {
		final String strMenuType = getFromRequestParameter(MenuDao.KEY_MENU_TYPE);
		return MenuDao.TYPE_FOLDER.toString().equals(strMenuType) || MenuDao.TYPE_PAGE.toString().equals(strMenuType);
	}

	/**
	 * Validate the menu id in the request parameter.
	 * 
	 * @return True if valid, otherwise false.
	 */
	private boolean validateId() {
		final String strId = getFromRequestParameter(MenuDao.KEY_ID);
		return StringUtil.isNumeric(strId) && strId.length() > 0;
	}

	/**
	 * Validate the menu parent id in the request parameter.
	 * 
	 * @return True if valid, otherwise false.
	 */
	private boolean validateParentId() {
		final String strParentId = getFromRequestParameter(MenuDao.KEY_PARENT_ID);
		return MenuDao.ROOT_DEPT_ID.toString().equals(strParentId) || (StringUtil.isNumeric(strParentId) && strParentId.length() > 0);
	}

	/**
	 * Validate the menu idx in the request parameter.
	 * 
	 * @return True if valid, otherwise false.
	 */
	private boolean validateIdx() {
		final String strIdx = getFromRequestParameter(MenuDao.KEY_IDX);
		return StringUtil.isNumeric(strIdx) && strIdx.length() > 0;
	}

	/**
	 * Validate the page URL and page Enter.
	 * 
	 * @return True if valid, otherwise false.
	 */
	private boolean validatePageURLAndPageEnter() {
		final String pageURL = getFromRequestParameter(MenuDao.KEY_PAGE_URL);
		final String pageEnter = getFromRequestParameter(MenuDao.KEY_PAGE_ENTER);
		return pageURL != null && pageEnter != null && Pattern.matches("(/[0-9_a-zA-Z]+){2}", pageURL) && pageURL.indexOf(String.format("/%s/", pageEnter)) == 0;
	}
}
