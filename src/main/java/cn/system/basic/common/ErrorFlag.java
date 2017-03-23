package cn.system.basic.common;

public class ErrorFlag {
	/*
	 * 操作失败
	 */

	public final static int OPR_FAIL = 0;
	/*
	 * 后台异常
	 */
	public final static int MANAGE_ERROR = -1;

	/*
	 * 传入参数为空
	 */
	public final static int PARAMETER_NULL = -3;

	/*
	 * 根据id查询时 -4 对象为null
	 */
	public final static int FINDBYID_NULL = -4;

	/* 已经存在 */
	public final static int EXIST = -6;
	/*
	 *保存失败 
	 */
	public final static int SAVE_FAIL = -7;
	/*
	 *删除失败 
	 */
	public final static int DELETE_FAIL = -8;
}
