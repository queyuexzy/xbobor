package cn.system.basic.manage.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import cn.system.basic.manage.bean.RoleBean;
import cn.system.basic.manage.dao.RoleDao;
import cn.system.basic.manage.dao.UserRoleDao;
import cn.system.basic.manage.moduleInfo.ModuleDaoA;
import cn.tools.flexigrid.bean.Addparams;
import cn.tools.flexigrid.bean.FlexiGrid;

/**
 * Data access object to manage table role_info.
 * 
 * @author Luo Yinzhuo
 */
@Repository
public class RoleDaoImpl extends ModuleDaoA<RoleBean> implements RoleDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.system.basic.manage.dao.RoleDao#getRoleBeanListForUserId(int)
	 */
	@Override
	public List<RoleBean> getRoleBeanListForUserId(int userId) {
		final Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(UserRoleDao.KEY_USER_ID, userId);
		final String sql = "select *, (select count(*) > 0 from user_role_info b where b.userId = :userId and b.roleId = a.id) _hasRole from role_info a";
		return systemNameJdbcTemplate.query(sql, paramMap,
				new BeanPropertyRowMapper<RoleBean>(RoleBean.class));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.system.basic.manage.dao.RoleDao#getRoleBeanById(int)
	 */
	@Override
	public RoleBean getRoleBeanById(int id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(KEY_ID, id);

		final String sql = "select * from role_info where id = :id";
		return systemNameJdbcTemplate.queryForObject(sql, paramMap,
				new BeanPropertyRowMapper<RoleBean>(RoleBean.class));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.system.basic.manage.dao.RoleDao#getRoleBeanList(cn.tools.flexigrid
	 * .bean.FlexiGrid)
	 */
	@Override
	public FlexiGrid getRoleBeanList(FlexiGrid flexiGrid) {
		StringBuffer sqlWhere = new StringBuffer(String.format(
				" and a.id != %d", ID_SUPER_MANAGER));
		Map<String, Object> paramMap = new HashMap<String, Object>();

		// First, extract the flexiGrid condition.
		List<Addparams> params = flexiGrid.getAddparams();
		if (params != null) {
			for (Addparams param : params) {
				if (KEY_ROLE_NAME.equals(param.getName())
						&& param.getValue() != null
						&& param.getValue() instanceof String) {
					paramMap.put(KEY_ROLE_NAME,
							String.format("%%%s%%", param.getValue()));
					sqlWhere.append(" and a.roleName like :roleName");
				}
			}
		}

		// Second, check the total count under the condition.
		StringBuffer sqlCount = new StringBuffer(
				"select count(*) from role_info a where 1=1 ");
		sqlCount.append(sqlWhere.toString());

		final int total = systemNameJdbcTemplate.queryForObject(
				sqlCount.toString(), paramMap, Integer.class);
		flexiGrid.adjust(total);

		// Third, get the list under the condition.
		if (total > 0) {
			StringBuffer sqlSelect = new StringBuffer(
					"select * from role_info a where 1 = 1");
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
					new BeanPropertyRowMapper<RoleBean>(RoleBean.class)));
		}
		return flexiGrid;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.system.basic.manage.dao.RoleDao#getRoleBeanList()
	 */
	@Override
	public List<RoleBean> getRoleBeanList() {
		final String sql = "select * from role_info";
		return systemNameJdbcTemplate.query(sql, new BeanPropertyRowMapper<RoleBean>(RoleBean.class));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.system.basic.manage.dao.RoleDao#getRoleBeanByRoleName(java.lang.String
	 * )
	 */
	@Override
	public RoleBean getRoleBeanByRoleName(String roleName) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(KEY_ROLE_NAME, roleName);

		final String sql = "select * from role_info where roleName = :roleName";
		try {
			return systemNameJdbcTemplate.queryForObject(sql, paramMap,
					new BeanPropertyRowMapper<RoleBean>(RoleBean.class));
		} catch (Exception e) {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.system.basic.manage.dao.RoleDao#addRoleBean(cn.system.basic.manage
	 * .bean.RoleBean)
	 */
	@Override
	public boolean addRoleBean(RoleBean role) {
		return insertBean(role, "role_info", "roleName, creatorId, createTime, updateUserId") > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.system.basic.manage.dao.RoleDao#updateRoleBeanById(cn.system.basic
	 * .manage.bean.RoleBean)
	 */
	@Override
	public void updateRoleBeanById(RoleBean role) {
		updateBean(role, "role_info", "roleName, updateUseId", "id");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.system.basic.manage.dao.RoleDao#deleteRoleBeanById(int)
	 */
	@Override
	public void deleteRoleBeanById(int id) {
		final Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(KEY_ID, id);
		final String sql = "delete from role_info where id = :id";
		systemNameJdbcTemplate.update(sql, paramMap);
		final String sqlRoleMenu = "delete from role_menu_info where roleId = :id";
		systemNameJdbcTemplate.update(sqlRoleMenu, paramMap);
		final String sqlUserRole = "delete from user_role_info where roleId = :id";
		systemNameJdbcTemplate.update(sqlUserRole, paramMap);
	}
}
