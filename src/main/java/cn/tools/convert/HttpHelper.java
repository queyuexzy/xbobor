package cn.tools.convert;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

/**
 * Http服务公共类
 * @author hubaoting
 * @date 2015-6-24
 * @Description
 */
public abstract class HttpHelper {

	public static final String KEY_ACCEPT = "Accept";
	public static final String KEY_ACCEPT_LANGUAGE = "Accept-Language";
	public static final String KEY_CACHE_CONTROL = "Cache-Control";
	public static final String KEY_CONNECTION = "Connection";
	public static final String KEY_PARGMA = "Pragma";
	public static final String KEY_USER_AGENT = "User-Agent";
	public static final String KEY_ACCEPT_ENCODING = "Accept-Encoding";
	public static final String VALUE_ACCEPT = "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8";
	public static final String VALUE_ACCEPT_LANGUAGE = "en-US,en;q=0.8,zh-CN;q=0.6,zh;q=0.4";
	public static final String VALUE_CACHE_CONTROL = "no-cache";
	public static final String VALUE_CONNECTION = "keep-alive";
	public static final String VALUE_PARGMA = "no-cache";
	public static final String VALUE_USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2540.0 Safari/537.36";
	public static final String VALUE_ACCEPT_ENCODING = "gzip, deflate, sdch";
	
	/**
	 * 创建HttpGet
	 * @param url
	 * @return HttpGet
	 */
	public static HttpGet createHttpGet(String url) {
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader(KEY_ACCEPT, VALUE_ACCEPT);
		httpGet.setHeader(KEY_ACCEPT_LANGUAGE, VALUE_ACCEPT_LANGUAGE);
		httpGet.setHeader(KEY_CACHE_CONTROL, VALUE_CACHE_CONTROL);
		httpGet.setHeader(KEY_CONNECTION, VALUE_CONNECTION);
		httpGet.setHeader(KEY_PARGMA, VALUE_PARGMA);
		httpGet.setHeader(KEY_USER_AGENT, VALUE_USER_AGENT);
		httpGet.setHeader(KEY_ACCEPT_ENCODING, VALUE_ACCEPT_ENCODING);
		return httpGet;
	}
	
	/**
	 * 创建HttpPost
	 * @param url
	 * @return HttpPost
	 */
	public static HttpPost createHttpPost(String url) {
		HttpPost post = new HttpPost(url);
		post.setHeader(KEY_ACCEPT, VALUE_ACCEPT);
		post.setHeader(KEY_ACCEPT_LANGUAGE, VALUE_ACCEPT_LANGUAGE);
		post.setHeader(KEY_CACHE_CONTROL, VALUE_CACHE_CONTROL);
		post.setHeader(KEY_CONNECTION, VALUE_CONNECTION);
		post.setHeader(KEY_PARGMA, VALUE_PARGMA);
		post.setHeader(KEY_USER_AGENT, VALUE_USER_AGENT);
		post.setHeader(KEY_ACCEPT_ENCODING, VALUE_ACCEPT_ENCODING);
		return post;
	}
	
	/**
	 * 获取区间数的随机数
	 * @param max 最大数
	 * @param min 最小数
	 * @return 随机区间数
	 */
	private static int getRandom(int max, int min){
		int num = min;
		try {
			num = (int) Math.round(Math.random()*(max-min)+min);
		} catch (Exception e) {}
		return num;
	}
	
	public static void main(String[] args) {
		System.out.println();
	}
}
