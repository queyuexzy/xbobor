package cn.system.basic.manage.dao;

import java.util.List;

/**
 * Interface definition to manage table role_menu_info.
 * 
 * @author Luo Yinzhuo
 */
public interface RoleMenuDao {
	/** The key to map role id column. */
	public final static String KEY_ROLE_ID = "roleId";
	/** The key to map menu id column. */
	public final static String KEY_MENU_ID = "menuId";
	/** The key to map creator column.  */
	public final static String KEY_CREATOR = "creator";
	/** The key to map create time column. */
	public final static String KEY_CREATE_TIME = "createTime";

	/**
	 * Update role menu bean list.
	 * 
	 * @param roleId
	 * The role id.
	 * @param menuIds
	 * The menu id list.
	 * @param creator
	 * The creator id.
	 */
	public void updateRoleMenuBeanList(int roleId, List<Integer> menuIds, int creator);
}
