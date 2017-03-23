package cn.system.basic.manage.dao;

import java.util.List;
import cn.system.basic.manage.bean.PostBean;
import cn.tools.flexigrid.bean.FlexiGrid;

/**
 * Interface definition to provide methods to manage {@link PostBean}.
 * 
 * @author Luo Yinzhuo
 */
public interface PostDao {
	/** The key to map id column. */
	public final static String KEY_ID = "id";
	/** The key to map post name column. */
	public final static String KEY_POST_NAME = "postName";

	/** The key to map create time column to order select result. */
	public final static String ORDER_CREATE_TIME = "createTime";
	/** The key to map update time column to order select result. */
	public final static String ORDER_UPDATE_TIME = "updateTime";

	/**
	 * Get all post info in table post_info.
	 * 
	 * @return The {@link PostBean}.
	 */
	public List<PostBean> getPostBeanList();

	/**
	 * Get post bean list.
	 * 
	 * @param flexiGrid
	 * The input.
	 * @return The result return.
	 */
	public FlexiGrid getPostBeanList(FlexiGrid flexiGrid);

	/**
	 * Get position by its id.
	 * 
	 * @param id
	 * The position id.
	 * @return The position bean or null if not exists.
	 */
	public PostBean getPostBeanById(int id);

	/**
	 * Get position by its name.
	 * 
	 * @param postName
	 * The position name.
	 * @return The position bean or null if not exists.
	 */
	public PostBean getPostBeanByPostName(String postName);

	/**
	 * Add a position.
	 * 
	 * @param post
	 * The position.
	 * @return True success, otherwise false.
	 */
	public boolean addPostBean(PostBean post);

	/**
	 * Update a position.
	 * 
	 * @param post
	 * The position.
	 */
	public void updatePostBeanById(PostBean post);
	
	/**
	 * Delete a position.
	 * 
	 * @param id
	 * The id.
	 */
	public void deletePostBeanById(int id);
}
