package cn.system.basic.manage.bean;

import java.util.Collection;
import cn.system.basic.manage.dao.RoleDao;

/**
 * Represent a piece of result in table role_info..
 */
public class RoleBean {
	/**
	 * Check if the roles contains super manager.
	 * 
	 * @param roles
	 * The roles to be checked.
	 * @return True if the roles contains super manager, otherwise false.
	 */
	public static boolean isSuperManager(Collection<Integer> roles) {
		return roles != null && roles.contains(RoleDao.ID_SUPER_MANAGER);
	}

	/** The role id. */
	private int id;
	/** The role name. */
	private String roleName;
	/** The delete flag. */
	private boolean deleteFlag = false;
	/** The creator id. */
	private int creatorId;
	/** The create time. */
	private String createTime;
	/** The update user id. */
	private int updateUserId;
	/** The update time. */
	private String updateTime;

	/** The flag to identify the user has the role or not. */
	private boolean _hasRole = false;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public boolean isDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public int getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(int creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public int getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(int updateUserId) {
		this.updateUserId = updateUserId;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public boolean is_hasRole() {
		return _hasRole;
	}

	public void set_hasRole(boolean _hasRole) {
		this._hasRole = _hasRole;
	}
}
