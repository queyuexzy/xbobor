package cn.system.basic.manage.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import cn.system.basic.manage.bean.UserBean;
import cn.system.basic.manage.dao.UserDao;
import cn.system.basic.manage.moduleInfo.ModuleDaoA;
import cn.tools.flexigrid.bean.Addparams;
import cn.tools.flexigrid.bean.FlexiGrid;

/**
 * Data access object to manage table user_info.
 * 
 * @author Luo Yinzhuo
 */
@Repository
public class UserDaoImpl extends ModuleDaoA<UserBean> implements UserDao {

	@Resource(name = "system")
	private String system;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.system.basic.manage.dao.UserDao#getUserBeanList(cn.tools.flexigrid
	 * .bean.FlexiGrid)
	 */
	@Override
	public FlexiGrid getUserBeanList(FlexiGrid flexiGrid) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//20151019 去掉区分项目查询用户 begin
		//paramMap.put(KEY_SYSTEM, system);
		//StringBuffer sqlWhere = new StringBuffer(" and (a.system is null or a.system = :system)");
		//20151019 去掉区分项目查询用户 end
		StringBuffer sqlWhere = new StringBuffer();

		// First, extract the flexiGrid condition.
		List<Addparams> params = flexiGrid.getAddparams();
		if (params != null) {
			Map<String, Object> tempMap = new HashMap<String, Object>();
			for (Addparams param : params) {
				if (param != null && param.getName() != null
						&& param.getValue() != null) {
					tempMap.put(param.getName(), param.getValue());
				}
			}
			boolean existRealName = false;
			if (tempMap.containsKey(UserDao.KEY_REAL_NAME)) {
				final Object temp = tempMap.get(UserDao.KEY_REAL_NAME);
				if (temp instanceof String) {
					sqlWhere.append(" and a.realName like :realName");
					paramMap.put(UserDao.KEY_REAL_NAME,
							String.format("%%%s%%", (String) temp));
					existRealName = true;
				}
			}
			if (!existRealName && tempMap.containsKey(UserDao.KEY_USER_NAME)) {
				final Object temp = tempMap.get(UserDao.KEY_USER_NAME);
				if (temp instanceof String) {
					sqlWhere.append(" and a.userName like :userName");
					paramMap.put(UserDao.KEY_USER_NAME,
							String.format("%%%s%%", (String) temp));
				}
			}
			if (tempMap.containsKey(UserDao.KEY_DEPT_ID)) {
				final Object temp = tempMap.get(UserDao.KEY_DEPT_ID);
				if (temp instanceof Integer) {
					sqlWhere.append(" and a.deptId = :deptId");
					paramMap.put(UserDao.KEY_DEPT_ID, temp);
				}
			}
			if (tempMap.containsKey(UserDao.KEY_ROLE_ID)) {
				final Object temp = tempMap.get(UserDao.KEY_ROLE_ID);
				if (temp instanceof Integer) {
					sqlWhere.append(" and a.id  in (select userId from user_role_info where roleId = :roleId)");
					paramMap.put(UserDao.KEY_ROLE_ID, temp);
				}
			}
			if (tempMap.containsKey(UserDao.KEY_MENU_ID)) {
				final Object temp = tempMap.get(UserDao.KEY_MENU_ID);
				if (temp instanceof Integer) {
					sqlWhere.append(" and a.id in (select userId from user_menu_info where menuId = :menuId UNION select userId from user_role_info where roleId in (select roleId from role_menu_info where menuId = :menuId))");
					paramMap.put(UserDao.KEY_MENU_ID, temp);
				}
			}
		}
		// Second, check the total count under the condition.
		StringBuffer sqlCount = new StringBuffer(
				"select count(*) from user_info a where 1=1 ");
		sqlCount.append(sqlWhere.toString());

		final int total = systemNameJdbcTemplate.queryForObject(
				sqlCount.toString(), paramMap, Integer.class);
		flexiGrid.adjust(total);
		// Third, get the list under the condition.
		if (total > 0) {
			final String sqlDeptName = "(select deptName from dept_info where id = a.deptId) _deptName";
			final String sqlPostName = "(select postName from post_info where id = a.postId) _postName";
			StringBuffer sqlSelect = new StringBuffer(String.format(
					"select *, %s, %s from user_info a where 1 = 1",
					sqlDeptName, sqlPostName));
			sqlSelect.append(sqlWhere.toString());
			final String sortName = flexiGrid.getSortname();
			if (ORDER_CREATE_TIME.equals(sortName)
					|| ORDER_EDIT_TIME.equals(sortName)) {
				sqlSelect.append(String
						.format(" order by %s %s", sortName, ORDER_DESC
								.equals(flexiGrid.getSortorder()) ? ORDER_DESC
								: ORDER_ASC));
			}
			sqlSelect.append(String.format(" limit %d, %d",
					(flexiGrid.getPage() - 1) * flexiGrid.getPagesize(),
					flexiGrid.getPagesize()));
			flexiGrid.setRows(systemNameJdbcTemplate.query(
					sqlSelect.toString(), paramMap,
					new BeanPropertyRowMapper<UserBean>(UserBean.class)));
		}
		return flexiGrid;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.system.basic.manage.dao.UserDao#addUserBean(cn.system.basic.manage
	 * .bean.UserBean)
	 */
	@Override
	public boolean addUserBean(UserBean bean) {
		final Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(KEY_USER_NAME, bean.getUserName());
		final String sqlCheck = "select count(*) from user_info where userName = :userName";
		final int count = systemNameJdbcTemplate.queryForObject(sqlCheck,
				paramMap, Integer.class);
		if (count > 0) {
			return false;
		}

		bean.setSystem(system);
		return insertBean(
				bean,
				"user_info",
				"userName, password, postId, realName, createTime, deptId, sex, birthday, telephone, mobile, email, address, zipCode, education, separationFlag, journalType, system") > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.system.basic.manage.dao.UserDao#getUserBeanById(int)
	 */
	@Override
	public UserBean getUserBeanById(int id) {
		final Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(UserDao.KEY_ID, id);
		
		//20151019 去掉用户区分项目  begin
		//paramMap.put(KEY_SYSTEM, system);
		//final String sql = "select *, (select deptName from dept_info where id = a.deptId) _deptName  from user_info a where (a.system is null or a.system = :system) and a.id = :id";
		//20151019 去掉用户区分项目  end
		
		final String sql = "select *, (select deptName from dept_info where id = a.deptId) _deptName  from user_info a where a.id = :id";
		return systemNameJdbcTemplate.queryForObject(sql, paramMap,
				new BeanPropertyRowMapper<UserBean>(UserBean.class));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.system.basic.manage.dao.UserDao#updateUserBeanById(cn.system.basic
	 * .manage.bean.UserBean)
	 */
	@Override
	public boolean updateUserBeanById(UserBean bean) {
		return updateBean(
				bean,
				"user_info",
				"password, postId, realName, editTime, deptId, sex, birthday, telephone, mobile, email, address, zipCode, education, separationFlag, journalType, lastLoginTime, lastLoginIP",
				"id") > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.system.basic.manage.dao.UserDao#deleteUserBeanById(int)
	 */
	@Override
	public void deleteUserBeanById(int id) {
		final Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(KEY_ID, id);
		final String sql = "delete from user_info where id = :id";
		systemNameJdbcTemplate.update(sql, paramMap);

		final String sqlMenu = "delete from user_menu_info where userId = :id";
		systemNameJdbcTemplate.update(sqlMenu, paramMap);

		final String sqlRole = "delete from user_role_info where userId = :id";
		systemNameJdbcTemplate.update(sqlRole, paramMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.system.basic.manage.dao.UserDao#getUserBeanList()
	 */
	@Override
	public List<UserBean> getUserBeanList() {
		final String sqlDeptName = "(select deptName from dept_info where id = a.deptId) _deptName";
		final String sqlPostName = "(select postName from post_info where id = a.postId) _postName";
		final String sqlRoleSet = "(select group_concat(roleId) from user_role_info where userId = a.id) _roleSet";
		final String sqlPrivilegeSet = "(select group_concat(pageEnter) from menu_info where id  in (select menuId from user_menu_info where userId = a.id union select menuId from role_menu_info where roleId in (select roleId from user_role_info where userId = a.id))) _privilegeSet";
		final String sql = String.format(
				"select *, %s, %s, %s, %s from user_info a order by id",
				sqlDeptName, sqlPostName, sqlRoleSet, sqlPrivilegeSet);
		return systemNameJdbcTemplate.query(sql.toString(),
				new BeanPropertyRowMapper<UserBean>(UserBean.class));
	}
}
