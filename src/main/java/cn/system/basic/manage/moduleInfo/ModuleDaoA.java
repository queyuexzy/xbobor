package cn.system.basic.manage.moduleInfo;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import cn.system.basic.global.baseAbstract.BaseDaoA;

/**
 * 模块dao 用来住人jdbcTemplate
 * @author zhl
 * @date   20112011-8-24下午02:46:46
 *
 */
public abstract class ModuleDaoA<T> extends BaseDaoA<T>{
	@Resource(name="systemJdbcTemplate") protected JdbcTemplate systemJdbcTemplate;
	protected NamedParameterJdbcTemplate systemNameJdbcTemplate;

	@PostConstruct
	public void initJdbcTemplae() {
		systemNameJdbcTemplate = new NamedParameterJdbcTemplate(this.systemJdbcTemplate);
	}
	
	@Override
	protected NamedParameterJdbcTemplate getNamedJdbc(){
		return systemNameJdbcTemplate;
	}
}
