package com.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.bean.CategoryBean;
import com.dao.CategoryDao;
import com.moduleInfo.ModuleDaoXboborManage;

@Repository
public class CategoryDaoImpl  extends ModuleDaoXboborManage<CategoryBean> implements CategoryDao {

	/*
	 * (non-Javadoc)
	 * @see com.dao.CategoryDao#getAllCategory()
	 * @author zhangying
	 * @date 2016年6月29日 下午5:16:30
	 * @return
	 */
	@Override
	public List<CategoryBean> getAllCategory(){
		String sql = "select * from xbb_category_info";
		return mJdbcTemplate.query(sql, new BeanPropertyRowMapper<CategoryBean>(CategoryBean.class));
	}
	
}
