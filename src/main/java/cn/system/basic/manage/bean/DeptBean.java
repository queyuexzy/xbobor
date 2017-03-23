package cn.system.basic.manage.bean;

/**
 * Represent a row result in table dept_info.
 * 
 * @author Luo Yinzhuo
 */
public class DeptBean {
	/** The department id. */
	private int id;
	/** The parent id. */
	private int pId;
	/** The department name. */
	private String deptName;
	/** The create user id. */
	private int creatorId;
	/** The create time. */
	private String createTime;
	/** The update user id. */
	private int updateUserId;
	
	/** The child count. */
	private int _childCount = 0;

	/**
	 * Only for ztree use.
	 * 
	 * @return The child count.
	 */
	public int get_childCount() {
		return _childCount;
	}

	public void set_childCount(int _childCount) {
		this._childCount = _childCount;
	}
	
	/**
	 * Only for zTree use.
	 * 
	 * @return True is parent, otherwise false.
	 */
	public boolean isIsParent() {
		return this._childCount > 0;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPId() {
		return pId;
	}

	public void setPId(int pId) {
		this.pId = pId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
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
}
