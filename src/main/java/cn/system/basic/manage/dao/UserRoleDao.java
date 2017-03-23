package cn.system.basic.manage.dao;

import java.util.List;

/**
 * Interface definition to manage table user_role_info.
 * 
 * @author Luo Yinzhuo
 */
public interface UserRoleDao {
	/** The key to map user id column. */
	public final static String KEY_USER_ID = "userId";
	/** The key to map role id column. */
	public final static String KEY_ROLE_ID = "roleId";
	/** The key to map creator column.  */
	public final static String KEY_CREATOR = "creator";
	/** The key to map create time column. */
	public final static String KEY_CREATE_TIME = "createTime";

	/**
	 * Update user role bean list.
	 * 
	 * @param userId
	 * The user id.
	 * @param roleIds
	 * The role id list.
	 * @param creator
	 * The creator id.
	 */
	public void updateUserRoleBeanList(int userId, List<Integer> roleIds,
			int creator);
}
