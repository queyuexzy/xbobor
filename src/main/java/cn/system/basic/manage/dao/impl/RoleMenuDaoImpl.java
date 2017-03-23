package cn.system.basic.manage.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import cn.system.basic.manage.bean.UserMenuBean;
import cn.system.basic.manage.dao.RoleMenuDao;
import cn.system.basic.manage.moduleInfo.ModuleDaoA;
import cn.tools.FastDateFormatHelper;

/**
 * Data access object to manage table role_menu_info.
 * 
 * @author Luo Yinzhuo
 */
@Repository
public class RoleMenuDaoImpl extends ModuleDaoA<UserMenuBean> implements
		RoleMenuDao {

	/*
	 * (non-Javadoc)
	 * @see cn.system.basic.manage.dao.RoleMenuDao#updateRoleMenuBeanList(int, java.util.List, int)
	 */
	@Override
	public void updateRoleMenuBeanList(int roleId, List<Integer> menuIds,
			int creator) {
		final StringBuffer strMenuIds = new StringBuffer();

		for (Integer menuId : menuIds) {
			if (strMenuIds.length() > 0) {
				strMenuIds.append(",");
			}
			strMenuIds.append(menuId);
		}
		final Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(KEY_ROLE_ID, roleId);
		final StringBuffer sqlDelete = new StringBuffer(
				"delete from role_menu_info where roleId = :roleId");
		if (strMenuIds.length() > 0) {
			sqlDelete.append(String.format(" and menuId not in (%s)",
					strMenuIds.toString()));
		}
		systemNameJdbcTemplate.update(sqlDelete.toString(), paramMap);
		if (strMenuIds.length() > 0) {
			paramMap.put(KEY_CREATOR, creator);
			paramMap.put(KEY_CREATE_TIME,
					FastDateFormatHelper.DEFAULT.format(new Date()));
			final String sqlInsert = String
					.format("insert into role_menu_info (roleId, menuId, creator, createTime) select :roleId, id, :creator, :createTime from menu_info where id in (%s) and id not in (select menuId from role_menu_info where roleId = :roleId)",
							strMenuIds.toString());
			systemNameJdbcTemplate.update(sqlInsert, paramMap);
		}
	}

}
