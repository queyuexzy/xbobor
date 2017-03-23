package cn.system.basic.manage.cache;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import cn.system.basic.manage.bean.UserBean;
import cn.system.basic.manage.dao.UserDao;

/**
 * Provide current system user cache.
 * 
 * @author Luo Yinzhuo
 */
public class UserBeanCache {
	/** The id cache map. */
	private static final Map<Integer, UserBean> ID_USER_MAP = new HashMap<Integer, UserBean>();
	/** The user name cache map. */
	private static final Map<String, UserBean> USER_NAME_USER_MAP = new HashMap<String, UserBean>();

	/**
	 * Get the {@link UserBean} by id.
	 * 
	 * @param userId
	 * The user id.
	 * @return The user bean.
	 */
	public static UserBean getUserBeanById(int userId) {
		final UserBean origin = (UserBean) ID_USER_MAP.get(userId);
		return origin == null ? null : (UserBean) origin.clone();
	}

	/**
	 * Get the {@link UserBean} by user name.
	 * 
	 * @param userName
	 * The user name.
	 * @return The user bean.
	 */
	public static UserBean getUserBeanByUserName(String userName) {
		final UserBean origin = (UserBean) USER_NAME_USER_MAP.get(userName);
		return origin == null ? null : (UserBean) origin.clone();
	}

	/**
	 * Get all the {@link UserBean}.
	 * 
	 * @return user bean list.
	 */
	public synchronized static List<UserBean> getUserBeanList() {
		final List<UserBean> userBeanList = new ArrayList<UserBean>();
		for (UserBean user : ID_USER_MAP.values()) {
			userBeanList.add((UserBean) user.clone());
		}
		return userBeanList;
	}

	/** The data access object to get {@link UserBean}. */
	@Autowired
	private UserDao mUserDao;
	
	/**
	 * Initialize the cache.
	 */
	public void init() {
		ID_USER_MAP.clear();
		USER_NAME_USER_MAP.clear();
		List<UserBean> userBeans = mUserDao.getUserBeanList();
		if (userBeans != null) {
			for (UserBean userBean : userBeans) {
				ID_USER_MAP.put(userBean.getId(), userBean);
				USER_NAME_USER_MAP.put(userBean.getUserName(), userBean);
			}
		}
	}

	private static final Object USER_LOCK = new Object();
	
	/**
	 * Update the cache.
	 */
	public void updateUser() {
		synchronized (USER_LOCK) {
			ID_USER_MAP.clear();
			USER_NAME_USER_MAP.clear();

			List<UserBean> userBeans = mUserDao.getUserBeanList();
			if (userBeans != null) {
				for (UserBean userBean : userBeans) {
					ID_USER_MAP.put(userBean.getId(), userBean);
					USER_NAME_USER_MAP.put(userBean.getUserName(), userBean);
				}
			}
		}
	}
	
	/**
	 * 更新单个用户
	 * 
	 * @author Luo Yinzhuo
	 * @date 2015年7月6日 下午4:24:35
	 */
	public void updateSingleUser(JoinPoint point) {
		final Object[] args = point.getArgs();
		if (args.length > 0) {
			final Object temp = args[0];
			if (temp != null && temp instanceof UserBean) {
				UserBean userBean = (UserBean) temp;
				
				synchronized(USER_LOCK) {
					ID_USER_MAP.put(userBean.getId(), userBean);
					USER_NAME_USER_MAP.put(userBean.getUserName(), userBean);
				}
			}
		}
	}
	
	/**
	 * 设置用户真实姓名
	 * 
	 * @param list Bean的list
	 * @author Luo Yinzhuo
	 * @date 2015年4月9日 上午11:26:35
	 */
	public static void setUserName(List<?> list) {
		if (list == null) {
			return;
		}
		
		for (Object object : list) {
			Class<?> clz = object.getClass();

			// Creator Name.
			try {
				final Method getCreatorId = clz.getMethod("getCreatorId");
				final Method setCreatorName = clz.getMethod("set_creatorName",
						String.class);
				final Integer creatorId = (Integer) getCreatorId.invoke(object);
				if (creatorId != null) {
					final UserBean creator = ID_USER_MAP.get(creatorId);
					if (creator != null) {
						setCreatorName.invoke(object, creator.getRealName());
					}
				}
			} catch (Exception e) {
			}

			// Update User Name.
			try {
				final Method getUpdateUserId = clz.getMethod("getUpdateUserId");
				final Method setUpdateUserName = clz.getMethod(
						"set_updateUserName", String.class);
				final Integer updateUserId = (Integer) getUpdateUserId
						.invoke(object);
				if (updateUserId != null) {
					final UserBean updateUser = ID_USER_MAP.get(updateUserId);
					if (updateUser != null) {
						setUpdateUserName.invoke(object,
								updateUser.getRealName());
					}
				}
			} catch (Exception e) {
			}
		}
	}
}
