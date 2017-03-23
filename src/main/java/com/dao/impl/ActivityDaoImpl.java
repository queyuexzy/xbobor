package com.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.bean.ActivityBean;
import com.dao.ActivityDao;
import com.moduleInfo.ModuleDaoXboborManage;

@Repository
public class ActivityDaoImpl  extends ModuleDaoXboborManage<ActivityBean> implements ActivityDao {

	/*
	 * (non-Javadoc)
	 * @see com.dao.ActivityDao#getAllActivity()
	 * @author zhangying
	 * @date 2016年6月29日 下午5:28:34
	 * @return
	 */
	@Override
	public List<ActivityBean> getAllActivity(){
		String sql = "select * from xbb_activity_info";
		return mJdbcTemplate.query(sql, new BeanPropertyRowMapper<ActivityBean>(ActivityBean.class));
	}
	
}
