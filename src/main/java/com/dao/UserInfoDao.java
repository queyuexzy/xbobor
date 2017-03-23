package com.dao;

import com.bean.ActivityBean;

import java.util.List;
import java.util.Map;

public interface UserInfoDao {

	/**
	 * 1：深州
	 * @param userName
	 * @param tel
	 * @param place
	 * @return
	 */
	Boolean addUserInfo(String userName, String tel, String place);


	Boolean addToMysql(String appName, String tel, String place);
}
