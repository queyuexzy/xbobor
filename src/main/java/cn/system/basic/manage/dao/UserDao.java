package cn.system.basic.manage.dao;

import java.util.List;
import cn.system.basic.manage.bean.UserBean;
import cn.tools.flexigrid.bean.FlexiGrid;

/**
 * Interface definition to provide methods to manage {@link UserBean}.
 * 
 * @author Luo Yinzhuo
 */
public interface UserDao {
	/** The key to map id column. */
	public final static String KEY_ID = "id";
	/** The key to map userName column. */
	public final static String KEY_USER_NAME = "userName";
	/** The key to map realName column. */
	public final static String KEY_REAL_NAME = "realName";
	/** The key to map sex column. */
	public final static String KEY_SEX = "sex";
	/** The key to map birthday column. */
	public final static String KEY_BIRTHDAY = "birthday";
	/** The key to map address column. */
	public final static String KEY_ADDRESS = "address";
	/** The key to map journal type. */
	public final static String KEY_JOURNAL_TYPE = "journalType";
	/** The key to map telephone column. */
	public final static String KEY_TELEPHONE = "telephone";
	/** The key to map mobile column. */
	public final static String KEY_MOBILE = "mobile";
	/** The key to map email column. */
	public final static String KEY_EMAIL = "email";
	/** The key to map zip code column. */
	public final static String KEY_ZIP_CODE = "zipCode";
	/** The key to map education column. */
	public final static String KEY_EDUCATION = "education";
	/** The key to map separation flag column. */
	public final static String KEY_SEPARATION_FLAG = "separationFlag";
	/** The key to map deptId column. */
	public final static String KEY_DEPT_ID = "deptId";
	/** The key to map postId column. */
	public final static String KEY_POST_ID = "postId";
	/** The key to map create time column. */
	public final static String KEY_CREATE_TIME = "createTime";
	/** The key to map menuId column in user_role_info. */
	public final static String KEY_ROLE_ID = "roleId";
	/** The key to map menuId column in user_menu_info. */
	public final static String KEY_MENU_ID = "menuId";
	/** The gender value for male. */
	public final static int GENDER_MALE = 1;
	/** The gender value for female. */
	public final static int GENDER_FEMALE = 0;
	/** The flag to represent separation. */
	public final static int SEPARATION_YES = 0;
	/** The flag to represent not separation. */
	public final static int SEPARATION_NO = 1;
	/** The journal type for worker. */
	public final static int JOURNAL_TYPE_WORKER = 3;
	/** The journal type for manager. */
	public final static int JOURNAL_TYPE_MANAGER = 2;
	/** The journal type for leader. */
	public final static int JOURNAL_TYPE_LEADER = 1;
	/** The key to map createTime column to order select result. */
	public final static String ORDER_CREATE_TIME = "createTime";
	/** The key to map editTime column to order select result. */
	public final static String ORDER_EDIT_TIME = "editTime";
	/** The default password. */
	public final static String PASSWORD_DEFAULT = "123456";
	/** The system manage id. */
	public final static int SYSTEM_MANAGE_ID = 1;
	/** The key to map column system. */
	public final static String KEY_SYSTEM = "system";

	/**
	 * Get user bean list.
	 * 
	 * @param flexiGrid
	 * The input.
	 * @return The result return.
	 */
	public FlexiGrid getUserBeanList(FlexiGrid flexiGrid);

	/**
	 * Add a user bean.
	 * 
	 * @param bean
	 * The user bean.
	 * @return True success, otherwise false.
	 */
	public boolean addUserBean(UserBean bean);

	/**
	 * Get a user.
	 * 
	 * @param id
	 * The id.
	 * @return The user or null if not exists.
	 */
	public UserBean getUserBeanById(int id);

	/**
	 * Update a user bean by its id.
	 * 
	 * @param bean
	 * The user bean.
	 * @return True success, otherwise false.
	 */
	public boolean updateUserBeanById(UserBean bean);

	/**
	 * Delete a user.
	 * 
	 * @param id
	 * The id.
	 */
	public void deleteUserBeanById(int id);

	/**
	 * Get current all user bean.
	 * 
	 * @return The user bean list.
	 */
	public List<UserBean> getUserBeanList();
}
