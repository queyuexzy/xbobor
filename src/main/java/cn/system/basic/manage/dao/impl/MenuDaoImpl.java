package cn.system.basic.manage.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import cn.system.basic.manage.bean.MenuBean;
import cn.system.basic.manage.dao.MenuDao;
import cn.system.basic.manage.dao.RoleMenuDao;
import cn.system.basic.manage.dao.UserMenuDao;
import cn.system.basic.manage.moduleInfo.ModuleDaoA;

/**
 * Data access object to manage table menu_info.
 * 
 * @author Luo Yinzhuo
 */
@Repository
public class MenuDaoImpl extends ModuleDaoA<MenuBean> implements MenuDao {

	@Resource(name = "system")
	private String system;

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.system.basic.manage.dao.MenuDao#getMenuBeanListByParentId(int)
	 */
	@Override
	public List<MenuBean> getMenuBeanListByParentId(int parentId) {
		final Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(MenuDao.KEY_PARENT_ID, parentId);
		paramMap.put(KEY_SYSTEM, system);

		final String sql = String.format("select *, (select count(*) from menu_info b where b.pId = a.pId) _maxIdx, (select count(*) from menu_info c where c.pId = a.id) _childCount from menu_info a where (system is null or system = :system) and a.pId = :pId order by idx", parentId);
		return systemNameJdbcTemplate.query(sql, paramMap, new BeanPropertyRowMapper<MenuBean>(MenuBean.class));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.system.basic.manage.dao.MenuDao#getMenuBeanListForRoleId(int)
	 */
	@Override
	public List<MenuBean> getMenuBeanListForRoleId(int roleId) {
		final Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(RoleMenuDao.KEY_ROLE_ID, roleId);
		final String sql = "select *, (select count(*) > 0 from role_menu_info b where b.roleId = :roleId and b.menuId = a.id) _hasMenu from menu_info a order by idx asc";
		return systemNameJdbcTemplate.query(sql, paramMap, new BeanPropertyRowMapper<MenuBean>(MenuBean.class));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.system.basic.manage.dao.MenuDao#getMenuBeanListForUserId(int)
	 */
	@Override
	public List<MenuBean> getMenuBeanListForUserId(int userId) {
		final Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(UserMenuDao.KEY_USER_ID, userId);
		paramMap.put(KEY_SYSTEM, system);
		final String sql = "select *, (select count(*) > 0 from user_menu_info b where b.userId = :userId and b.menuId = a.id) _hasMenu from menu_info a where (a.system is null or a.system = :system) order by idx asc";
		return systemNameJdbcTemplate.query(sql, paramMap, new BeanPropertyRowMapper<MenuBean>(MenuBean.class));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.system.basic.manage.dao.MenuDao#getMenuBeanListForNormalUserId(int)
	 */
	@Override
	public List<MenuBean> getMenuBeanListForNormalUserId(int userId) {
		final Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		paramMap.put(KEY_SYSTEM, system);
		final String sql = "select * from menu_info a where (system is null or system = :system) and id in (select menuId from user_menu_info where userId = :userId union select menuId from role_menu_info where roleId in (select roleId from user_role_info where userId = :userId))";
		return systemNameJdbcTemplate.query(sql, paramMap, new BeanPropertyRowMapper<MenuBean>(MenuBean.class));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.system.basic.manage.dao.MenuDao#getMenuBeanListForSystemManager()
	 */
	@Override
	public List<MenuBean> getMenuBeanListForSystemManager() {
		final String sql = "select * from menu_info where (system is null or system = :system) and (id = :id or pId = :pId) order by idx";
		final Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(KEY_SYSTEM, system);
		paramMap.put(KEY_ID, SYSTEM_MENU_ID);
		paramMap.put(KEY_PARENT_ID, SYSTEM_MENU_ID);
		return systemNameJdbcTemplate.query(sql, paramMap, new BeanPropertyRowMapper<MenuBean>(MenuBean.class));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.system.basic.manage.dao.MenuDao#getMenuBeanListForSuperManager()
	 */
	@Override
	public List<MenuBean> getMenuBeanListForSuperManager() {
		final String sql = "select * from menu_info where (system is null or system = :system) and id != :id and pId != :pId order by idx";
		final Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(KEY_SYSTEM, system);
		paramMap.put(KEY_ID, SYSTEM_MENU_ID);
		paramMap.put(KEY_PARENT_ID, SYSTEM_MENU_ID);
		return systemNameJdbcTemplate.query(sql, paramMap, new BeanPropertyRowMapper<MenuBean>(MenuBean.class));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.system.basic.manage.dao.MenuDao#getMenuBeanById(int)
	 */
	@Override
	public MenuBean getMenuBeanById(int id) {
		final Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(KEY_ID, id);
		final String sql = "select *, (select count(*) from menu_info b where b.pId = a.pId) _maxIdx from menu_info a where a.id = :id";
		return systemNameJdbcTemplate.queryForObject(sql, paramMap, new BeanPropertyRowMapper<MenuBean>(MenuBean.class));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.system.basic.manage.dao.MenuDao#addMenuBean(cn.system.basic.manage
	 * .bean.MenuBean)
	 */
	@Override
	public synchronized void addMenuBean(MenuBean menu) {
		final Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(KEY_PARENT_ID, menu.getPId());

		final String sqlMaxIdx = "select count(*) from menu_info where pId = :pId";
		final int maxIdx = systemNameJdbcTemplate.queryForObject(sqlMaxIdx, paramMap, Integer.class);
		menu.setIdx(maxIdx + 1);

		menu.setSystem(system);
		this.insertBean(menu, "menu_info", "pId, menuName, menuType, pageURL, pageEnter, idx, showIP, creatorId, createTime, updateUserId, system");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.system.basic.manage.dao.MenuDao#updateMenuBeanById(cn.system.basic
	 * .manage.bean.MenuBean)
	 */
	@Override
	public synchronized void updateMenuBeanById(MenuBean menu) {
		if (menu.get_oldIdx() != null) {
			if (menu.getIdx() < menu.get_oldIdx()) {
				final String sql = String.format("update menu_info set idx = idx + 1 where pId = %d and idx >= %d and idx < %d", menu.getPId(), menu.getIdx(), menu.get_oldIdx());
				systemJdbcTemplate.update(sql);
			} else if (menu.getIdx() > menu.get_oldIdx()) {
				final String sql = String.format("update menu_info set idx = idx - 1 where pId = %d and idx <= %d and idx > %d", menu.getPId(), menu.getIdx(), menu.get_oldIdx());
				systemJdbcTemplate.update(sql);
			}
		}
		this.updateBean(menu, "menu_info", "menuName, pageURL, pageEnter, updateUserId, idx, showIP", "id");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.system.basic.manage.dao.MenuDao#deleteMenuBeanById(int)
	 */
	@Override
	public synchronized void deleteMenuBeanById(MenuBean menu) {
		final Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(KEY_ID, menu.getId());
		final String sql = "delete from menu_info where id = :id";
		systemNameJdbcTemplate.update(sql, paramMap);
		final String sqlIdx = String.format("update menu_info set idx = idx - 1 where pId = %d and idx > %d", menu.getPId(), menu.getIdx());
		systemJdbcTemplate.update(sqlIdx);
		final String sqlMenu = "delete from user_menu_info where menuId = :id";
		systemNameJdbcTemplate.update(sqlMenu, paramMap);
		final String sqlRole = "delete from role_menu_info where menuId = :id";
		systemNameJdbcTemplate.update(sqlRole, paramMap);
	}
}