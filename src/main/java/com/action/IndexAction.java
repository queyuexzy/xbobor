package com.action;

import java.util.*;

import cn.system.basic.global.baseAbstract.BaseAction;
import com.bean.RedisProductBean;
import org.springframework.beans.factory.annotation.Autowired;

import cn.tools.CommonSendMeg;
import cn.tools.jackjson.JackJson;

import com.bean.ActivityBean;
import com.bean.CategoryBean;
import com.bean.ScannerBean;
import com.dao.ActivityDao;
import com.dao.CategoryDao;
import com.dao.ScannerDao;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class IndexAction extends BaseAction {
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private ScannerDao scannerDao;
	@Autowired
	private ActivityDao activityDao;

	@Autowired
	private JedisPool jedisPool;
	
	/**
	 *
	 * @author zhangying
	 * @date 2016年6月29日 下午5:30:39 void
	 */
	public void squareIndex(){
		Map<String, Object> map = new HashMap<String, Object>();
		List<CategoryBean> categoryList = categoryDao.getAllCategory();
		List<ScannerBean> scannerList = scannerDao.getAllScanner();
		List<ActivityBean> activityList = activityDao.getAllActivity();
		map.put("category", categoryList);
		map.put("scanner", scannerList);
		map.put("activity", activityList);
		CommonSendMeg.writeMsg(JackJson.fromObjectToJson(map));
	}
	
	/**
	 * 返回首页
	 * @author zhangying
	 * @date 2016年6月30日 下午5:35:39
	 * @return String
	 */
	public String toSquare(){
		return "toSquare";
	}
	
	/**
	 * 返回我的页面
	 * @author zhangying
	 * @date 2016年6月30日 下午6:02:37
	 * @return String
	 */
	public String toMine(){
		return "toMine";
	}

	public String toCategory(){
		String name = this.getFromRequestParameter("name");
		String shopId = "1";
		String queryPatten = "";
		if(name.equals("quanbu")){
			queryPatten = "*";
		}
		if("lingshi".equals(name)){
			queryPatten = "1";
		}

		if("shuiguo".equals(name)){
			queryPatten = "2";
		}

		if("liangyou".equals(name)){
			queryPatten = "3";
		}

		if("shucai".equals(name)){
			queryPatten = "4";
		}

		if("shenghuo".equals(name)){
			queryPatten = "5";
		}

		if("fushi".equals(name)){
			queryPatten = "6";
		}

		if("yinpin".equals(name)){
			queryPatten = "7";
		}

		Jedis jedis = null;
		List<RedisProductBean> result = new ArrayList<RedisProductBean>();
		try {
			jedis = jedisPool.getResource();
			Set<String> keys  = jedis.keys("1_" + queryPatten + "_*");
			for(String key : keys){
				String value = jedis.get(key);
				RedisProductBean bean = new RedisProductBean();
				String[] values = value.split("#");
				String[] keyDetail = key.split("_");
				bean.setShopId(keyDetail[0]);
				bean.setCategoryId(keyDetail[1]);
				bean.setProductId(keyDetail[2]);
				bean.setTitle(values[0]);
				bean.setPlace(values[1]);
				bean.setPrice(values[2]);
				bean.setImage(values[3]);
				bean.setDetail(values[4]);
				bean.setOriginalPrice(values[5]);
				bean.setRepertory(values[6]);
				bean.setSale(values[7]);
				bean.setDay(values[8]);
				bean.setKg(values[9]);
				bean.setPackageWay(values[10]);
				bean.setTastes(values[11]);
				bean.setEats(values[12]);
				bean.setImages(Arrays.asList(values[13].split(",")));
				result.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		if(result.size() > 0){
			putToContext("result", result);
		}
		if("quanbu".equals(name)){
			return "quanbu";
		}

		if("lingshi".equals(name)){
			return "lingshi";
		}

		if("shuiguo".equals(name)){
			return "shuiguo";
		}

		if("liangyou".equals(name)){
			return "liangyou";
		}

		if("shucai".equals(name)){
			return "shucai";
		}

		if("shenghuo".equals(name)){
			return "shenghuo";
		}

		if("fushi".equals(name)){
			return "fushi";
		}

		if("yinpin".equals(name)){
			return "yinpin";
		}

		return "quanbu";
	}

	/**
	 * 立即报名
	 */
	public void apply(){
		String product = this.getFromRequestParameter("shopId_categoryId_productId");
		String tel = this.getFromRequestParameter("tel");

		String value = getDataFromRedis("tel_" + tel);
		value = value + "#" + product;

		setDataToRedis("tel_" + tel, value);
	}


	private String getDataFromRedis(String key){
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			String value = jedis.get(key);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return "";
	}

	private boolean setDataToRedis(String key, String value){
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.set(key, value);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return false;
	}

	public String toProduct(){
		return "quanbu";
	}
}
