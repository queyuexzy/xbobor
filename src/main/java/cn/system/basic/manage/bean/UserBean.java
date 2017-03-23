package cn.system.basic.manage.bean;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import cn.system.basic.manage.action.UserChangePwdAction;
import cn.system.basic.manage.dao.UserDao;
import cn.tools.SecurityTools;

/**
 * Represent a piece of result in table user_info.
 */
public class UserBean implements Cloneable {
	/** The primary key. */
	private int id;
	/** The user name used to login, must be unique. */
	private String userName;
	/** The password. */
	private String password = SecurityTools.getMD5(UserDao.PASSWORD_DEFAULT);
	/** The user's real name, used to display. */
	private String realName;
	/** The user's position id. */
	private Integer postId;
	/** The user's department id. */
	private Integer deptId;
	/**
	 * The user's gender. Only could be {@link UserDao#GENDER_MALE} or
	 * {@link UserDao#GENDER_FEMALE}.
	 */
	private int sex = UserDao.GENDER_MALE;
	/** The user's birthday. */
	private String birthday;
	/** The user's telephone. */
	private String telephone;
	/** The user's mobile. */
	private String mobile;
	/** The user's email. */
	private String email;
	/** The user's address. */
	private String address;
	/** The user's zip code. */
	private String zipCode;
	/** The user's education. */
	private String education;
	/** The user's separation status. */
	private int separationFlag = UserDao.SEPARATION_NO;
	/** The user's create time. */
	private String createTime;
	/** The user's edit time. */
	private String editTime;
	/** The user's last login time. */
	private String lastLoginTime;
	/** The user's last login IP. */
	private String lastLoginIP;
	/**
	 * The user's journal type. Only could be
	 * {@link UserDao#JOURNAL_TYPE_WORKER}. {@link UserDao#JOURNAL_TYPE_MANAGER}
	 * and {@link UserDao#JOURNAL_TYPE_LEDDER}.
	 */
	private int journalType = UserDao.JOURNAL_TYPE_WORKER;
	/** The system. */
	private String system;

	// 辅助字段
	private String _postName;
	private String _deptName;
	private String _deptList; // 当前用下所有子部门的id
	private Integer _menuId;
	private String _menuName;

	private HashSet<String> _userMenuSet; // 用户管理的菜单
	private HashSet<String> _userPageFunctionSet; // 用户所有的页面按钮权限

	private HashSet<Integer> _userRoleSet;
	
	public Integer get_menuId() {
		return _menuId;
	}

	public String get_menuName() {
		return _menuName;
	}

	public void set_menuId(Integer _menuId) {
		this._menuId = _menuId;
	}

	public void set_menuName(String _menuName) {
		this._menuName = _menuName;
	}

	public String get_deptList() {
		return _deptList;
	}

	public void set_deptList(String list) {
		_deptList = list;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public Integer getSeparationFlag() {
		return separationFlag;
	}

	public void setSeparationFlag(int separationFlag) {
		this.separationFlag = separationFlag;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getEditTime() {
		return editTime;
	}

	public void setEditTime(String editTime) {
		this.editTime = editTime;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginIP() {
		return lastLoginIP;
	}

	public void setLastLoginIP(String lastLoginIP) {
		this.lastLoginIP = lastLoginIP;
	}

	public String get_postName() {
		return _postName;
	}

	public void set_postName(String name) {
		_postName = name;
	}

	public String get_deptName() {
		return _deptName;
	}

	public void set_deptName(String name) {
		_deptName = name;
	}

	public Integer getJournalType() {
		return journalType;
	}

	public void setJournalType(int journalType) {
		this.journalType = journalType;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public void set_userPageFunctionSet(HashSet<String> _userPageFunctionSet) {
		this._userPageFunctionSet = _userPageFunctionSet;
	}

	public HashSet<String> get_userPageFunctionSet() {
		return _userPageFunctionSet;
	}

	public void set_userMenuSet(HashSet<String> _userMenuSet) {
		this._userMenuSet = _userMenuSet;
	}

	public HashSet<String> get_userMenuSet() {
		return _userMenuSet;
	}

	public HashSet<Integer> get_userRoleSet() {
		return _userRoleSet;
	}

	public void set_userRoleSet(Collection<Integer> _userRoleSet) {
		this._userRoleSet = new HashSet<Integer>();
		this._userRoleSet.addAll(_userRoleSet);
	}

	/** The role set. */
	private final Set<Integer> _roleSet = new HashSet<Integer>();

	/**
	 * Set the role set.
	 * 
	 * @param roleSetConcat
	 *            The role set concatenate.
	 */
	public void set_roleSet(String roleSetConcat) {
		if (roleSetConcat != null) {
			final String[] roles = roleSetConcat.split(",");
			for (int i = 0; i < roles.length; i++) {
				if (StringUtils.isNumeric(roles[i])) {
					_roleSet.add(Integer.valueOf(roles[i]));
				}
			}
		}
	}

	/** The privilege set. */
	private final Set<String> _privilegeSet = new HashSet<String>();

	/**
	 * Set the privilege set.
	 * 
	 * @param privilegeSetConcat
	 * The privilege set concatenate.
	 */
	public void set_privilegeSet(String privilegeSetConcat) {
		if (privilegeSetConcat != null) {
			final String[] privileges = privilegeSetConcat.split(",");
			for (int i = 0; i < privileges.length; i++) {
				if (privileges[i].trim().length() > 0) {
					_privilegeSet.add(privileges[i]);
				}
			}
		}
	}

	public boolean isAdmin() {
		return this.id == 1 || RoleBean.isSuperManager(this._userRoleSet);
	}

	/**
	 * Check if it is system manager.
	 * 
	 * @return True if is, otherwise false.
	 * @author Luo Yinzhuo
	 * @date 2014年8月29日 下午4:56:52
	 */
	public boolean isSystemManager() {
		return id == UserDao.SYSTEM_MANAGE_ID;
	}

	/**
	 * Check if it is a super manager.
	 * 
	 * @return True if is, otherwise false.
	 * @author Luo Yinzhuo
	 * @date 2014年8月29日 下午4:59:05
	 */
	public boolean isSuperManager() {
		return RoleBean.isSuperManager(this._roleSet);
	}

	/**
	 * Check if the user has privilege to access the URL.
	 * 
	 * @param url
	 * The request URL.
	 * @return True if has privilege, otherwise false.
	 * @author Luo Yinzhuo
	 * @date 2014年9月4日 下午3:00:10
	 */
	public boolean hasPrivilege(String url) {
		final String pageEnter = url.substring(1, url.lastIndexOf("/"));
		return UserChangePwdAction.PAGE_ENTER.equals(pageEnter)
				|| _privilegeSet.contains(pageEnter);
	}

	@Override
	public Object clone() {
		UserBean clone = new UserBean();
		clone.id = this.id;
		clone.userName = this.userName;
		clone.realName = this.realName;
		clone.password = this.password;
		clone.postId = this.postId;
		clone.deptId = this.deptId;
		clone.sex = this.sex;
		clone.birthday = this.birthday;
		clone.telephone = this.telephone;
		clone.mobile = this.mobile;
		clone.email = this.email;
		clone.address = this.address;
		clone.zipCode = this.zipCode;
		clone.education = this.education;
		clone.separationFlag = this.separationFlag;
		clone.createTime = this.createTime;
		clone.editTime = this.editTime;
		clone.lastLoginTime = this.lastLoginTime;
		clone.lastLoginIP = this.lastLoginIP;
		clone.journalType = this.journalType;
		clone._postName = this._postName;
		clone._deptName = this._deptName;
		clone._deptList = this._deptList;
		clone._menuId = this._menuId;
		clone._menuName = this._menuName;
		clone._userMenuSet = this._userMenuSet;
		clone._userPageFunctionSet = this._userPageFunctionSet;
		clone._userRoleSet = this._userRoleSet;
		clone._roleSet.addAll(this._roleSet);
		clone._privilegeSet.addAll(this._privilegeSet);
		return clone;
	}
	
	// 记录是否是第一次登录
	private Boolean _firstLogin = false;

	public Boolean get_firstLogin() {
		return _firstLogin;
	}

	public void set_firstLogin(Boolean _firstLogin) {
		this._firstLogin = _firstLogin;
	}
}
