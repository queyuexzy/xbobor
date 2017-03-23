/*  
 * @(#) ZTreeQueryBean.java Create on 2011-12-15 下午5:26:59
 *
 * Copyright 2011 by xl.
 */

package cn.tools.ztree;

/**
 * @author zhanghongliang
 * @date 2011-12-15
 */
public class ZTreeQueryBean {
	// 父节点的名称
	private String pIdName;
	// 父节点值
	private Object pIdValue;
	// 其他的条件属性
	private String otherCondition;
	// 与pid对应的id名称
	private String idName;
	// 表名称
	private String tableName;
	// 显示的名称
	private String name;

	// 排序信息，为:id asc,name desc
	private String sortValue;

	public String getpIdName() {
		return pIdName;
	}

	public void setpIdName(String pIdName) {
		this.pIdName = pIdName;
	}

	public Object getpIdValue() {
		return pIdValue;
	}

	public void setpIdValue(Object pIdValue) {
		this.pIdValue = pIdValue;
	}

	public String getOtherCondition() {
		return otherCondition;
	}

	public void setOtherCondition(String otherCondition) {
		this.otherCondition = otherCondition;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getIdName() {
		return idName;
	}

	public void setIdName(String idName) {
		this.idName = idName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSortValue() {
		return sortValue;
	}

	public void setSortValue(String sortValue) {
		this.sortValue = sortValue;
	}

}
