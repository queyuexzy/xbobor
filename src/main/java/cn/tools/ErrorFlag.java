/*  
 * @(#) ErrorFlag.java Create on 2012-1-4 下午4:40:54   
 *   
 * Copyright 2012 by xl.   
 */

package cn.tools;

/**
 * 
 * @author zhanghongliang
 * @date 2012-1-4
 */
public class ErrorFlag {
	/*
	 * 操作失败,10个已经填满
	 */
	
	public final static int OPR_FAIL = 0;
	
	/*
	 * 后台异常
	 */
	public final static int MANAGE_ERROR = -1;
	
	/*
	 * session 失效
	 */
	public final static int SESSION_FAIL = -2;

	/*
	 * 传入参数为空
	 */
	public final static int PARAMETER_NULL = -3;

	/*
	 * 根据id查询时 -4 对象为null
	 */
	public final static int FINDBYID_NULL = -4;
	
	public final static int EXIST = -5;
	

}
