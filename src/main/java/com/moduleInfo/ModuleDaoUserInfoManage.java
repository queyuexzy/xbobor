package com.moduleInfo;

import cn.system.basic.global.baseAbstract.BaseDaoA;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Used to inject {@link JdbcTemplate}.
 * @author Luo Yinzhuo
 * @param <T> The bean class.
 */
public class ModuleDaoUserInfoManage<T> extends BaseDaoA<T> {

	@Resource(name = "dao_588993dd8a3ba.bj.cdb.myqcloud.com")
	protected JdbcTemplate mJdbcTemplate;
	protected NamedParameterJdbcTemplate mNamedJdbcTemplate;

	@PostConstruct
	public void initJdbcTemplae() {
		mNamedJdbcTemplate = new NamedParameterJdbcTemplate(mJdbcTemplate);
	}

	@Override
	protected NamedParameterJdbcTemplate getNamedJdbc() {
		return mNamedJdbcTemplate;
	}
}
