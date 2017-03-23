package cn.tools.flexigrid.bean;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * flexigrid的附加查询条件
 * 
 * @author zhanghongliang
 * @date 2012-2-6 上午9:28:54
 */
public class Addparams {

	/** 查询的名称 */
	private String name;
	/** 查询的值 */
	private Object value;

	/** 操作符 是= ，like，>,<.... */
	private String logic;

	@Override
	public String toString() {
		// MULTI_LINE_STYLE
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE, true, true);
	}

	/**
	 * @return 查询的名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 * 查询的名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return 查询的值
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * @param value
	 * 查询的值
	 */
	public void setValue(Object value) {
		this.value = value;
	}

	public String getLogic() {
		return logic;
	}

	public void setLogic(String logic) {
		this.logic = logic;
	}
}
