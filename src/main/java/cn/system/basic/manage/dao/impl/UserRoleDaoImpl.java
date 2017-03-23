package cn.system.basic.manage.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import cn.system.basic.manage.bean.UserRoleBean;
import cn.system.basic.manage.dao.UserRoleDao;
import cn.system.basic.manage.moduleInfo.ModuleDaoA;
import cn.tools.FastDateFormatHelper;

/**
 * Data access object to manage table user_role_info.
 * 
 * @author Luo Yinzhuo
 */
@Repository
public class UserRoleDaoImpl extends ModuleDaoA<UserRoleBean> implements UserRoleDao {

	@Override
	public void updateUserRoleBeanList(int userId, List<Integer> roleIds,
			int creator) {
		final StringBuffer strRoleIds = new StringBuffer();
		for (Integer roleId : roleIds) {
			if (strRoleIds.length() > 0) {
				strRoleIds.append(",");
			}
			strRoleIds.append(roleId);
		}
		final Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(KEY_USER_ID, userId);
		final StringBuffer sqlDelete = new StringBuffer(
				"delete from user_role_info where userId = :userId");
		if (strRoleIds.length() > 0) {
			sqlDelete.append(String.format(" and roleId not in (%s)",
					strRoleIds.toString()));
		}
		systemNameJdbcTemplate.update(sqlDelete.toString(), paramMap);
		if (strRoleIds.length() > 0) {
			paramMap.put(KEY_CREATOR, creator);
			paramMap.put(KEY_CREATE_TIME,
					FastDateFormatHelper.DEFAULT.format(new Date()));
			final String sqlInsert = String
					.format("insert into user_role_info (userId, roleId, creator, createTime) select :userId, id, :creator, :createTime from role_info where id in (%s) and id not in (select roleId from user_role_info where userId = :userId)",
							strRoleIds.toString());
			systemNameJdbcTemplate.update(sqlInsert, paramMap);
		}
	}

}
