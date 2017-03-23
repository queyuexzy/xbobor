package cn.system.basic.manage.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import cn.system.basic.manage.bean.UserMenuBean;
import cn.system.basic.manage.dao.UserMenuDao;
import cn.system.basic.manage.moduleInfo.ModuleDaoA;
import cn.tools.FastDateFormatHelper;

/**
 * Data access object to manage table user_menu_info.
 * 
 * @author Luo Yinzhuo
 */
@Repository
public class UserMenuDaoImpl extends ModuleDaoA<UserMenuBean> implements UserMenuDao {
	
	@Resource(name = "system")
	private String system;
	
	@Override
	public void updateUserMenuBeanList(int userId, List<Integer> menuIds,
			int creator) {
		final StringBuffer strMenuIds = new StringBuffer();
		for (Integer menuId : menuIds) {
			if (strMenuIds.length() > 0) {
				strMenuIds.append(",");
			}
			strMenuIds.append(menuId);
		}
		final Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(KEY_USER_ID, userId);
		paramMap.put("system", system);
		final StringBuffer sqlDelete = new StringBuffer(
				"delete from user_menu_info where userId = :userId and menuId in (select id from menu_info where system = :system)");
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
					.format("insert into user_menu_info (userId, menuId, creator, createTime) select :userId, id, :creator, :createTime from menu_info where id in (%s) and id not in (select menuId from user_menu_info where userId = :userId)",
							strMenuIds.toString());
			systemNameJdbcTemplate.update(sqlInsert, paramMap);
		}
	}

}
