package cn.system.basic.manage.action;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.system.basic.global.baseAbstract.BaseAction;
import cn.system.basic.manage.bean.PostBean;
import cn.system.basic.manage.dao.PostDao;
import cn.tools.CommonSendMeg;
import cn.tools.FastDateFormatHelper;
import cn.tools.ajax.AjaxResponse;
import cn.tools.flexigrid.bean.FlexiGrid;
import cn.tools.jackjson.JackJson;

/**
 * The system position action.
 * 
 * @author Luo Yinzhuo
 */
public class PostAction extends BaseAction {
	private static final long serialVersionUID = 5663262257479435917L;

	/** The data access object to manage {@link PostBean}. */
	@Autowired
	private PostDao mPostDao;

	/**
	 * Direct to the index page.
	 * 
	 * @return The index page tag.
	 */
	public String toIndex() {
		return TOINDEX;
	}

	/**
	 * Used to search post for flexigrid use.
	 */
	public void searchPostForFlexiGrid() {
		final String flexiGridQuery = getFromRequestParameter(FlexiGrid.KEY_QUERY_JSON);
		final String result;
		if (flexiGridQuery != null && flexiGridQuery.length() > 0) {
			FlexiGrid flexiGrid = JackJson.fromJsonToObject(flexiGridQuery,
					FlexiGrid.class);
			mPostDao.getPostBeanList(flexiGrid);
			result = JackJson.fromObjectToJson(flexiGrid);
		} else {
			result = JackJson
					.fromObjectToJson(AjaxResponse.AJAX_CODE_ILLEGAL_PARAM);
		}
		CommonSendMeg.writeMsg(result);
	}

	/**
	 * Add a position.
	 */
	public void addPost() {
		final AjaxResponse ajaxResponse;
		// Check the incoming parameters one by one.
		if (validatePostName()) {
			final String postName = getFromRequestParameter(PostDao.KEY_POST_NAME);
			if (mPostDao.getPostBeanByPostName(postName) != null) {
				ajaxResponse = new AjaxResponse(
						AjaxResponse.AJAX_CODE_RESULT_REPEAT);
			} else {
				// Construct a bean.
				PostBean post = new PostBean();
				post.setPostName(postName);
				post.setCreatorId(getCurrentUserId());
				post.setCreateTime(FastDateFormatHelper.DEFAULT
						.format(new Date()));
				post.setUpdateUserId(getCurrentUserId());

				mPostDao.addPostBean(post);
				ajaxResponse = new AjaxResponse(AjaxResponse.AJAX_CODE_SUCCESS);
			}
		} else {
			ajaxResponse = new AjaxResponse(
					AjaxResponse.AJAX_CODE_ILLEGAL_PARAM);
		}
		CommonSendMeg.writeMsg(JackJson.fromObjectToJson(ajaxResponse));
	}

	/**
	 * Used to get a position specified by id.
	 */
	public void getPostById() {
		final AjaxResponse ajaxResponse;
		// Check the incoming parameters one by one.
		if (validateId()) {
			PostBean post = mPostDao.getPostBeanById(Integer
					.valueOf(getFromRequestParameter(PostDao.KEY_ID)));
			if (post == null) {
				ajaxResponse = new AjaxResponse(
						AjaxResponse.AJAX_CODE_RESULT_NOT_FOUND);
			} else {
				ajaxResponse = new AjaxResponse(AjaxResponse.AJAX_CODE_SUCCESS);
				ajaxResponse.setData(post);
			}
		} else {
			ajaxResponse = new AjaxResponse(
					AjaxResponse.AJAX_CODE_ILLEGAL_PARAM);
		}
		CommonSendMeg.writeMsg(JackJson.fromObjectToJson(ajaxResponse));
	}
	
	/**
	 * Update a position.
	 */
	public void updatePostById() {
		final AjaxResponse ajaxResponse;
		// Check the incoming parameters one by one.
		if (validateId()) {
			PostBean post = mPostDao.getPostBeanById(Integer
					.valueOf(getFromRequestParameter(PostDao.KEY_ID)));

			if (post == null) {
				ajaxResponse = new AjaxResponse(
						AjaxResponse.AJAX_CODE_RESULT_NOT_FOUND);
			} else {
				final String postName = getFromRequestParameter(PostDao.KEY_POST_NAME);
				if (mPostDao.getPostBeanByPostName(postName) != null) {
					ajaxResponse = new AjaxResponse(
							AjaxResponse.AJAX_CODE_RESULT_REPEAT);
				} else {
					post.setPostName(postName);
					post.setUpdateUserId(getCurrentUserId());

					mPostDao.updatePostBeanById(post);
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
	 * Used to delete a position specified by id.
	 */
	public void deletePostById() {
		final AjaxResponse ajaxResponse;
		// Check the incoming parameters one by one.
		if (validateId()) {
			mPostDao.deletePostBeanById(Integer
					.valueOf(getFromRequestParameter(PostDao.KEY_ID)));
			ajaxResponse = new AjaxResponse(AjaxResponse.AJAX_CODE_SUCCESS);
		} else {
			ajaxResponse = new AjaxResponse(
					AjaxResponse.AJAX_CODE_ILLEGAL_PARAM);
		}
		CommonSendMeg.writeMsg(JackJson.fromObjectToJson(ajaxResponse));
	}
	
	/**
	 * Validate the post id in the request parameter.
	 * 
	 * @return True if valid, otherwise false.
	 */
	private boolean validateId() {
		final String strId = getFromRequestParameter(PostDao.KEY_ID);
		return StringUtils.isNumeric(strId) && strId.length() > 0;
	}

	/**
	 * Validate the post name in the request parameter.
	 * 
	 * @return True if valid, otherwise false.
	 */
	private boolean validatePostName() {
		final String postName = getFromRequestParameter(PostDao.KEY_POST_NAME);
		return postName != null && postName.length() > 0;
	}
}