/*  
 * @(#) FlexiGridCondition.java Create on 2012-2-6 上午9:41:44   
 *
 * Copyright 2012 by xl.
 */

package cn.tools.flexigrid.bean;

import java.util.List;

/**
 * 
 * @author zhanghongliang
 * @date 2012-2-6
 */
public class FlexiGridCondition {
	/* 条件 */
	private String condtion;
	
	private List<Object> params;

	public String getCondtion() {
		return condtion;
	}

	public void setCondtion(String condtion) {
		this.condtion = condtion;
	}

	public List<Object> getParams() {
		return params;
	}

	public void setParams(List<Object> params) {
		this.params = params;
	}
}
