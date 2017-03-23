package cn.tools;

import java.util.ResourceBundle;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author shizhijie
 * 2010-07-28
 * 读取配置文件的工具类
 */
public class ConfigTools {

	/**
	 * 保存装载的 /config.properties 里的内容
	 */
	static ResourceBundle CONFIG = null;
	static ResourceBundle JDB_KEYWORD_CONFIG = null;
	
	/**
	 *	日志句柄 
	 */
	static Log log = LogFactory.getLog(ConfigTools.class);
	
	
	/**
	 * 初始化
	 */
	static{
		try{
			CONFIG = ResourceBundle.getBundle("config");
			JDB_KEYWORD_CONFIG = ResourceBundle.getBundle("jdb_keyWord");
		}catch(Exception ignored)
		{
			
		}
	}
	
	/**
	 * 获取 classpath 下 /config.properties 里的配置信息
	 * 
	 * @param key	根据关键字获取值
	 * @return 返回对应关键字的值
	 */
	public synchronized static String get(String key)
	{
		try{
			return CONFIG.getString(key);
		}catch(Exception ex)
		{
			log.debug(ex);
		}
		return null;
		
	}
	
	/**
	 * 获取加多宝过滤关键字
	 * @Title: getJDBKeyWord
	 * @data:2012-8-16下午12:16:23
	 * @author:liweidong
	 *
	 * @param key
	 * @return
	 */
	public synchronized static String getJDBKeyWord(){
		try{
			return JDB_KEYWORD_CONFIG.getString("过滤关键字");
		}catch(Exception ex)
		{
			log.debug(ex);
		}
		return null;
		
	}
}
