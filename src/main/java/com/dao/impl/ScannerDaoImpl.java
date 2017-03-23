package com.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.bean.CategoryBean;
import com.bean.ScannerBean;
import com.dao.CategoryDao;
import com.dao.ScannerDao;
import com.moduleInfo.ModuleDaoXboborManage;

@Repository
public class ScannerDaoImpl  extends ModuleDaoXboborManage<ScannerBean> implements ScannerDao {

	/*
	 * (non-Javadoc)
	 * @see com.dao.ScannerDao#getAllScanner()
	 * @author zhangying
	 * @date 2016年6月29日 下午5:23:01
	 * @return
	 */
	@Override
	public List<ScannerBean> getAllScanner(){
		String sql = "select * from xbb_scanner_info";
		return mJdbcTemplate.query(sql, new BeanPropertyRowMapper<ScannerBean>(ScannerBean.class));
	}
	
}
