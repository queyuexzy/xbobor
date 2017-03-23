package cn.system.basic.manage.bean;

/**
 * Represent a piece of result in table post_info.
 * 
 * @author Luo Yinzhuo
 */
public class PostBean{
	/** The primary key. */
	private int id;
	/** The position name. */
    private String postName;
    /** The creator id. */
    private int creatorId;
    /** The create time. */
    private String createTime;
    /** The update user id. */
    private int updateUserId;
    /** The update time. */
    private String updateTime;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
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
}
