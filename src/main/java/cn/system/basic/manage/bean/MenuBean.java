package cn.system.basic.manage.bean;

import cn.system.basic.manage.dao.MenuDao;

/**
 * Represent a piece of result in table menu_info.
 * 
 * @author Luo Yinzhuo
 */
public class MenuBean {
	/** The menu id. */
	private int id = 0;
	/** The parent menu id. */
	private int pId = 0;
	/** The menu name. */
	private String menuName = "";
	/** The menu type {@link MenuDao#TYPE_FOLDER} or {@link MenuDao#TYPE_PAGE}. */
	private int menuType = MenuDao.TYPE_PAGE;
	/** The page URL. */
	private String pageURL = "";
	/** The page Enter. */
	private String pageEnter = "";
	/** The creator ID. */
	private int creatorId;
	/** The create time. */
	private String createTime;
	/** The update user id. */
	private int updateUserId;
	/** The update time. */
	private String updateTime;
	/** The menu index. */
	private int idx = 1;
	/** The show IP. */
	private String showIP;
	/** The system. */
	private String system;

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
		return menuType == MenuDao.TYPE_FOLDER;
	}

	/** The old menu index. Only for update use. */
	private Integer _oldIdx = null;

	public Integer get_oldIdx() {
		return _oldIdx;
	}

	public void set_oldIdx(Integer _oldIdx) {
		this._oldIdx = _oldIdx;
	}

	/** The max idx. */
	private int _maxIdx = 1;

	/**
	 * The flag to identify the user has the menu or not. Only for manage
	 * privilege use.
	 */
	private boolean _hasMenu = false;

	public boolean is_hasMenu() {
		return _hasMenu;
	}

	public void set_hasMenu(boolean _hasMenu) {
		this._hasMenu = _hasMenu;
	}

	public String getShowIP() {
		return showIP;
	}

	public void setShowIP(String showIP) {
		this.showIP = showIP;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getPageURL() {
		return pageURL;
	}

	public void setPageURL(String pageURL) {
		this.pageURL = pageURL;
	}

	public String getPageEnter() {
		return pageEnter;
	}

	public void setPageEnter(String pageEnter) {
		this.pageEnter = pageEnter;
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

	public int getMenuType() {
		return menuType;
	}

	public void setMenuType(int menuType) {
		this.menuType = menuType;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
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

	public void setUpdateUserId(int udpateUserId) {
		this.updateUserId = udpateUserId;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public int get_maxIdx() {
		return _maxIdx;
	}

	public void set_maxIdx(int _maxIdx) {
		this._maxIdx = _maxIdx;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}
}
