package cn.system.basic.manage.dao;

import java.util.List;

/**
 * Interface definition to manage table user_menu_info.
 * 
 * @author Luo Yinzhuo
 */
public interface UserMenuDao {
	/** The key to map user id column. */
	public final static String KEY_USER_ID = "userId";
	/** The key to map menu id column. */
	public final static String KEY_MENU_ID = "menuId";
	/** The key to map creator column.  */
	public final static String KEY_CREATOR = "creator";
	/** The key to map create time column. */
	public final static String KEY_CREATE_TIME = "createTime";

	/**
	 * Update user menu bean list.
	 * 
	 * @param userId
	 * The user id.
	 * @param menuIds
	 * The menu id list.
	 * @param creator
	 * The creator id.
	 */
	public void updateUserMenuBeanList(int userId, List<Integer> menuIds, int creator);
}
