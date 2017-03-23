package com.dao.impl;

import com.alibaba.fastjson.JSON;
import com.bean.UserInfoBean;
import com.dao.UserInfoDao;
import com.moduleInfo.ModuleDaoUserInfoManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Repository
public class UserInfoDaoImpl  extends ModuleDaoUserInfoManage<UserInfoBean> implements UserInfoDao{

	@Autowired
	private JedisPool jedisPool;

	@Override
	public Boolean addUserInfo(String userName, String tel, String place){
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			String value = jedis.get(tel);
			if(null == value || "".equals(value)){
				jedis.set(tel, userName + ":" + place);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return false;
	}


	public Boolean addToMysql(String appName, String tel, String place){
		UserInfoBean bean = new UserInfoBean();
		bean.setAppName(appName);
		bean.setTel(tel);
		bean.setPlace(place);
		try{
			return this.insertBean(bean, "user_info", "id, tel, appName, place") > 0;
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return false;
	}

//	/**
//	 *
//	 * closeJedis(释放redis资源)
//	 *
//	 * @Title: closeJedis
//	 * @param @param jedis
//	 * @return void
//	 * @throws
//	 */
//	public void closeJedis(Jedis jedis) {
//		try {
//			if (jedis != null) {
//				jedisPool.returnBrokenResource(jedis);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
