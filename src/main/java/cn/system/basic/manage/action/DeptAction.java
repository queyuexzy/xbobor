package cn.system.basic.manage.action;

import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import cn.system.basic.global.baseAbstract.BaseAction;
import cn.system.basic.manage.bean.DeptBean;
import cn.system.basic.manage.dao.DeptDao;
import cn.tools.CommonSendMeg;
import cn.tools.FastDateFormatHelper;
import cn.tools.ajax.AjaxResponse;
import cn.tools.jackjson.JackJson;

/**
 * The system department action.
 * 
 * @author Luo Yinzhuo
 */
public class DeptAction extends BaseAction {
	private static final long serialVersionUID = 4000157111957230714L;

	/**
	 * Direct to the index page.
	 * 
	 * @return The index page tag.
	 */
	public String toIndex() {
		return TOINDEX;
	}

	/** The data access object to manage {@link DeptBean}. */
	@Autowired
	private DeptDao mDeptDao;

	/**
	 * Used to search department for ztree use.
	 */
	public void searchDeptForZtree() {
		final String strPid = getFromRequestParameter(DeptDao.KEY_ID);
		final AjaxResponse ajaxResponse;
		if (strPid == null || DeptDao.ROOT_DEPT_ID.toString().equals(strPid)) {
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
	 * Add a department.
	 */
	public void addDept() {
		final AjaxResponse ajaxResponse;
		// Check the incoming parameters one by one.
		if (validateDeptName() && validateParentId()) {
			final int parentId = Integer.valueOf(getFromRequestParameter(DeptDao.KEY_PARENT_ID));
			if (parentId == DeptDao.ROOT_DEPT_ID
					|| mDeptDao.getDeptBeanById(parentId) != null) {
				// Construct a bean.
				DeptBean dept = new DeptBean();
				dept.setDeptName(getFromRequestParameter(DeptDao.KEY_DEPT_NAME));
				dept.setPId(parentId);
				dept.setCreatorId(getCurrentUserId());
				dept.setCreateTime(FastDateFormatHelper.DEFAULT
						.format(new Date()));
				dept.setUpdateUserId(getCurrentUserId());

				mDeptDao.addDeptBean(dept);
				ajaxResponse = new AjaxResponse(AjaxResponse.AJAX_CODE_SUCCESS);
			} else {
				ajaxResponse = new AjaxResponse(
						AjaxResponse.AJAX_CODE_RESULT_NOT_FOUND);
			}
		} else {
			ajaxResponse = new AjaxResponse(
					AjaxResponse.AJAX_CODE_ILLEGAL_PARAM);
		}
		CommonSendMeg.writeMsg(JackJson.fromObjectToJson(ajaxResponse));
	}

	/**
	 * Update a department by its id.
	 */
	public void updateDeptById() {
		final AjaxResponse ajaxResponse;
		// Check the incoming parameters one by one.
		if (validateId()) {
			final int id = Integer
					.valueOf(getFromRequestParameter(DeptDao.KEY_ID));

			DeptBean dept = mDeptDao.getDeptBeanById(id);
			if (dept == null) {
				ajaxResponse = new AjaxResponse(
						AjaxResponse.AJAX_CODE_RESULT_NOT_FOUND);
			} else {
				if (validateDeptName()) {
					dept.setDeptName(getFromRequestParameter(DeptDao.KEY_DEPT_NAME));
				}
				dept.setUpdateUserId(getCurrentUserId());

				mDeptDao.updateDeptBeanById(dept);
				ajaxResponse = new AjaxResponse(AjaxResponse.AJAX_CODE_SUCCESS);
			}
		} else {
			ajaxResponse = new AjaxResponse(
					AjaxResponse.AJAX_CODE_ILLEGAL_PARAM);
		}
		CommonSendMeg.writeMsg(JackJson.fromObjectToJson(ajaxResponse));
	}

	/**
	 * Delete a department by its id.
	 */
	public void deleteDeptById() {
		final AjaxResponse ajaxResponse;
		// Check the incoming parameters one by one.
		if (validateId()) {
			final int id = Integer
					.valueOf(getFromRequestParameter(DeptDao.KEY_ID));

			DeptBean dept = mDeptDao.getDeptBeanById(id);
			if (dept != null) {
				mDeptDao.deleteDeptBeanById(dept);
			}
			ajaxResponse = new AjaxResponse(AjaxResponse.AJAX_CODE_SUCCESS);
		} else {
			ajaxResponse = new AjaxResponse(
					AjaxResponse.AJAX_CODE_ILLEGAL_PARAM);
		}
		CommonSendMeg.writeMsg(JackJson.fromObjectToJson(ajaxResponse));
	}

	/**
	 * Validate the department id in the request parameter.
	 * 
	 * @return True if valid, otherwise false.
	 */
	private boolean validateId() {
		final String strId = getFromRequestParameter(DeptDao.KEY_ID);
		return StringUtil.isNumeric(strId) && strId.length() > 0;
	}

	/**
	 * Validate the department name in the request parameter.
	 * 
	 * @return True if valid, otherwise false.
	 */
	private boolean validateDeptName() {
		final String deptName = getFromRequestParameter(DeptDao.KEY_DEPT_NAME);
		return deptName != null && deptName.trim().length() > 0;
	}

	/**
	 * Validate the department parent id in the request parameter.
	 * 
	 * @return True if valid, otherwise false.
	 */
	private boolean validateParentId() {
		final String strParentId = getFromRequestParameter(DeptDao.KEY_PARENT_ID);
		return DeptDao.ROOT_DEPT_ID.toString().equals(strParentId)
				|| (StringUtil.isNumeric(strParentId) && strParentId.length() > 0);
	}
}
