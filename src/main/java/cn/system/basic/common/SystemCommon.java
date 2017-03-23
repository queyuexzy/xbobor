package cn.system.basic.common;

/**
 * 当前模块中用到得表与数据
 * 
 * @author zhl
 * @date 20112011-8-24下午02:01:37
 * 
 */
public class SystemCommon {
	/**
	 * 数据库名称
	 */
	public final static String DATA_NAME = "chinaso_manage";
	/**
	 * 用户表
	 */
	public final static String TABLE_USER_INFO = DATA_NAME + ".user_info";

	/**
	 * 用户与菜单关联表
	 */
	public final static String TABLE_USER_MENU_INFO = DATA_NAME + ".user_menu_info";
	
	/**
	 * 菜单表
	 */
	public final static String TABLE_MENU_INFO = DATA_NAME + ".menu_info";

	/**
	 * 部门表
	 */
	public final static String TABLE_DEPT_INFO = DATA_NAME + ".dept_info";

	/**
	 * 职位表
	 */
	public final static String TABLE_POST_INFO = DATA_NAME + ".post_info";
	/**
	 * 用户角色表
	 */
	public final static String TABLE_USER_ROLE_INFO = DATA_NAME + ".user_role_info";
	/**
	 * tfs保存图片地址
	 */
	public final static String TFS_IP="10.10.224.98";
	/**
	 * tfs保存图片地址端口
	 */
	public final static int TFS_PORT=9000;
	/**
	 * 裁图服务地址
	 */
	public final static String cutImageService="http://10.10.160.45:10080/image-convert-http/crop";
	/**
	 * 图片缩放服务地址
	 */
	public final static String zoomPicAuto="http://10.10.160.45:10080/image-convert-http/zoomAuto";
	/**
	 * 根据宽度缩放
	 */
	public final static String zoomPicByWidth="http://10.10.160.45:10080/image-convert-http/zoomByWidth";
	/**
	 * 根据高度缩放
	 */
	public final static String zoomPicByHeight="http://10.10.160.45:10080/image-convert-http/zoomByHeight";
	/**
	 * 裁剪并等比例缩放
	 */
	public final static String cutZoomImageService="http://10.10.160.45:11080/image-news-http/cropHome";
	/**
	 * 新闻图片推送的时候调用裁剪服务，判读第几张图片符合裁剪标准
	 * 从第一张开始判断
	 */
	public static String indexURL="http://10.10.160.45:11080/image-news-http/indexHomeImage";
	/**
	 * 单图判断是否符合裁剪标准
	 */
	public static String indexURLSingle="http://10.10.160.45:11080/image-news-http/validateHomeImage";
	/**
	 * 新闻图片推送裁剪服务
	 */
	public static String indexURLCut="http://10.10.160.45:11080/image-news-http/handleHomeImage";
	/**
	 * 访问外网代理ip
	 */
	public final static String proxyIp="10.10.160.249";
	/**
	 * 访问外网代理端口
	 */
	public final static int proxyPort=8081;
}
