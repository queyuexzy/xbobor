package cn.system.basic.global.tools;

import java.util.Map;
import java.util.Set;
import org.springframework.util.Assert;

public class SqlHelper {

	/**
	 * 把map转换成inser sql语句
	 * 
	 * @author zhl
	 * @date 20112011-8-25下午11:18:25
	 * 
	 * @param oprMap
	 * @param tableName
	 * @param primaryName
	 * 不要的主键
	 * @return
	 */
	public static String getInserSql(Map<String, Object> oprMap, String tableName, String primaryName) {
		Assert.notNull(oprMap);
		Assert.notNull(tableName);
		Assert.notNull(primaryName);
		StringBuffer result = new StringBuffer();
		result.append("INSERT INTO " + tableName + " (");

		StringBuffer keys = new StringBuffer();
		StringBuffer values = new StringBuffer();

		if (oprMap.size() > 0) {
			for (String key : oprMap.keySet()) {
				if (!key.equals(primaryName) && !key.startsWith("_") && !key.equals("tableName") && !key.equals("class") && !key.equals("serialVersionUID")) {
					keys.append("`" + key + "`,");
					values.append(":" + key + ",");
				}
			}
		}
		result.append(keys.toString().replaceAll(",$", ""));
		result.append(")");
		result.append(" VALUES (");
		result.append(values.toString().replaceAll(",$", "") + ")");
		return result.toString();
	}

	/**
	 * 取update sql
	 * 
	 * @author zhl
	 * @date 20112011-8-26上午09:02:41
	 * 
	 * @param keys
	 * @param tableName
	 * @param primaryName
	 * @return
	 */
	public static String getUpdateSql(Set<String> keys, String tableName, String primaryName) {
		StringBuffer result = new StringBuffer();
		Assert.notNull(keys);
		Assert.notNull(tableName);
		Assert.notNull(primaryName);

		result.append("UPDATE " + tableName + " SET ");
		StringBuffer upBuffer = new StringBuffer();

		if (keys.size() > 0) {
			for (String key : keys) {
				if (!key.equals(primaryName) && !key.startsWith("_") && !key.equals("tableName") && !key.equals("class") && !key.equals("serialVersionUID")) {
					upBuffer.append("`"+key + "`=:" + key + ",");
				}
			}
		}

		result.append(upBuffer.toString().replaceAll(",$", ""));
		result.append(" WHERE " + primaryName + "=:" + primaryName + "");
		return result.toString();
	}
}
