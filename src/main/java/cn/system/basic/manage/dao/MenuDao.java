package cn.system.basic.manage.dao;

import java.util.List;
import cn.system.basic.manage.bean.MenuBean;

/**
 * Interface definition to provide methods to manage {@link MenuBean}.
 * 
 * @author Luo Yinzhuo
 */
public interface MenuDao {
	/** The root department id. */
	public final static Integer ROOT_DEPT_ID = -1;
	/** The system manage id. */
	public final static int SYSTEM_MENU_ID = 1;

	/** The key to map column id. */
	public final static String KEY_ID = "id";
	/** The key to map column parent id. */
	public final static String KEY_PARENT_ID = "pId";
	/** The key to map column menu name. */
	public final static String KEY_MENU_NAME = "menuName";
	/** The key to map column menu type. */
	public final static String KEY_MENU_TYPE = "menuType";
	/** The key to map column page URL. */
	public final static String KEY_PAGE_URL = "pageURL";
	/** The key to map column page enter. */
	public final static String KEY_PAGE_ENTER = "pageEnter";
	/** The key to map column idx. */
	public final static String KEY_IDX = "idx";
	/** The key to map column showIP. */
	public final static String KEY_SHOW_IP = "showIP";
	/** The key to map column system. */
	public final static String KEY_SYSTEM = "system";
	/** The type to identify the menu is a folder. */
	public final static Integer TYPE_FOLDER = 1;
	/** The type to identify the menu is a page. */
	public final static Integer TYPE_PAGE = 0;

	/**
	 * Get the specified parent id's {@link MenuBean}.
	 * 
	 * @param parentId
	 * The parent id.
	 * @return The {@link MenuBean}.
	 */
	public List<MenuBean> getMenuBeanListByParentId(int parentId);

	/**
	 * Get the menu list for specified role id.
	 * 
	 * @param roleId
	 * The role id.
	 * @return The menu list.
	 */
	public List<MenuBean> getMenuBeanListForRoleId(int roleId);

	/**
	 * Get the menu list for specified user id.
	 * 
	 * @param userId
	 * The user id.
	 * @return The menu list.
	 */
	public List<MenuBean> getMenuBeanListForUserId(int userId);

	/**
	 * Get the menu list for specified normal user id.
	 * 
	 * @param userId
	 * The user id.
	 * @return The menu list.
	 * @author Luo Yinzhuo
	 * @date 2014年8月29日 下午4:43:26
	 */
	public List<MenuBean> getMenuBeanListForNormalUserId(int userId);

	/**
	 * Get the menu list for system manager.
	 * 
	 * @return The menu list.
	 * @author Luo Yinzhuo
	 * @date 2014年8月29日 下午4:46:51
	 */
	public List<MenuBean> getMenuBeanListForSystemManager();

	/**
	 * Get the menu list for super manager.
	 * 
	 * @return The menu list.
	 * @author Luo Yinzhuo
	 * @date 2014年8月29日 下午4:47:49
	 */
	public List<MenuBean> getMenuBeanListForSuperManager();

	/**
	 * Get a menu by its id.
	 * 
	 * @param id
	 * The id.
	 * @return The menu bean or null if not exists.
	 */
	public MenuBean getMenuBeanById(int id);

	/**
	 * Add a menu.
	 * 
	 * @param menu
	 * The menu.
	 */
	public void addMenuBean(MenuBean menu);

	/**
	 * Update a menu by its id.
	 * 
	 * @param menu
	 * The menu.
	 */
	public void updateMenuBeanById(MenuBean menu);

	/**
	 * Delete a menu by its id.
	 * 
	 * @param menu
	 * The menu.
	 */
	public void deleteMenuBeanById(MenuBean menu);
}
