package cn.system.basic.manage.dao;

import java.util.List;
import cn.system.basic.manage.bean.RoleBean;
import cn.tools.flexigrid.bean.FlexiGrid;

/**
 * Interface definition to provide methods to manage {@link RoleBean}.
 * 
 * @author Luo Yinzhuo
 */
public interface RoleDao {
	/** The key to map id column. */
	public final static String KEY_ID = "id";
	/** The key to map roleName column. */
	public final static String KEY_ROLE_NAME = "roleName";
	/** The key to map createTime column to order select result. */
	public final static String ORDER_CREATE_TIME = "createTime";
	/** The key to map editTime column to order select result. */
	public final static String ORDER_EDIT_TIME = "editTime";
	/** The super manager role id. */
	public final static int ID_SUPER_MANAGER = 1;

	/**
	 * Get the role list for specified user id.
	 * 
	 * @param userId
	 * The user id.
	 * @return The role list.
	 */
	public List<RoleBean> getRoleBeanListForUserId(int userId);

	/**
	 * Get role bean list.
	 * 
	 * @param flexiGrid
	 * The input.
	 * @return The result return.
	 */
	public FlexiGrid getRoleBeanList(FlexiGrid flexiGrid);

	/**
	 * Get all role bean list.
	 * 
	 * @author Luo Yinzhuo
	 * @return The list.
	 * @date 2014年8月22日 上午11:04:53
	 */
	public List<RoleBean> getRoleBeanList();

	/**
	 * Get role by its id.
	 * 
	 * @param id
	 * The role id.
	 * @return The role bean or null if not exists.
	 */
	public RoleBean getRoleBeanById(int id);

	/**
	 * Get role by its name.
	 * 
	 * @param roleName
	 * The role name.
	 * @return The role bean or null if not exists.
	 */
	public RoleBean getRoleBeanByRoleName(String roleName);

	/**
	 * Add a role.
	 * 
	 * @param role
	 * The role.
	 * @return True success, otherwise false.
	 */
	public boolean addRoleBean(RoleBean role);

	/**
	 * Update a role.
	 * 
	 * @param role
	 * The role.
	 */
	public void updateRoleBeanById(RoleBean role);

	/**
	 * Delete a role.
	 * 
	 * @param id
	 * The id.
	 */
	public void deleteRoleBeanById(int id);
}
