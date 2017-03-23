package com.moduleInfo;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import cn.system.basic.global.baseAbstract.BaseDaoA;

/**
 * Used to inject {@link JdbcTemplate}.
 * @author Luo Yinzhuo
 * @param <T> The bean class.
 */
public class ModuleDaoXboborManage<T> extends BaseDaoA<T> {

	@Resource(name = "dao_nongguo_manage")
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
