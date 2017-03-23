package cn.system.basic.manage.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import cn.system.basic.manage.bean.DeptBean;
import cn.system.basic.manage.dao.DeptDao;
import cn.system.basic.manage.moduleInfo.ModuleDaoA;

/**
 * Data access object to manage table dept_info.
 * 
 * @author Luo Yinzhuo
 */
@Repository
public class DeptDaoImpl extends ModuleDaoA<DeptBean> implements DeptDao {
	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.system.basic.manage.dao.DeptDao#getDeptBeanListByParentId(int)
	 */
	@Override
	public List<DeptBean> getDeptBeanListByParentId(int pId) {
		final Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(KEY_PARENT_ID, pId);
		final String sql = "select *,  (select count(*) from dept_info c where c.pId = a.id) _childCount from dept_info a where a.pId = :pId";
		return systemNameJdbcTemplate.query(sql, paramMap,
				new BeanPropertyRowMapper<DeptBean>(DeptBean.class));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.system.basic.manage.dao.DeptDao#getDeptBeanById(int)
	 */
	@Override
	public DeptBean getDeptBeanById(int id) {
		final Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(KEY_ID, id);
		final String sql = "select * from dept_info a where a.id = :id";
		return systemNameJdbcTemplate.queryForObject(sql, paramMap,
				new BeanPropertyRowMapper<DeptBean>(DeptBean.class));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.system.basic.manage.dao.DeptDao#addDeptBean(cn.system.basic.manage
	 * .bean.DeptBean)
	 */
	@Override
	public synchronized void addDeptBean(DeptBean dept) {
		final Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(KEY_PARENT_ID, dept.getPId());
		this.insertBean(dept, "dept_info",
				"pId, deptName, creatorId, createTime, updateUserId");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.system.basic.manage.dao.DeptDao#updateDeptBeanById(cn.system.basic
	 * .manage.bean.DeptBean)
	 */
	@Override
	public void updateDeptBeanById(DeptBean dept) {
		this.updateBean(dept, "dept_info", "deptName, updateUserId", "id");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.system.basic.manage.dao.DeptDao#deleteDeptBeanById(cn.system.basic
	 * .manage.bean.DeptBean)
	 */
	@Override
	public void deleteDeptBeanById(DeptBean dept) {
		final Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(KEY_ID, dept.getId());
		final String sql = "delete from dept_info where id = :id";
		systemNameJdbcTemplate.update(sql, paramMap);
		final String sqlUser = "update user_info set deptId = null where id = :id";
		systemNameJdbcTemplate.update(sqlUser, paramMap);
	}
}
