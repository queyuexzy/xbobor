package cn.tools.http;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.map.ObjectMapper;
import cn.system.basic.common.SystemCommon;

public final class HTTPUtils {
	
	private HTTPUtils() {

	}
	private static final String DEFAULT_ENCODING = "UTF-8";

	/**
	 * 以byte形式返回对应的文件数据
	 * 
	 * @param url url
	 * @return
	 * @throws IOException IO异常
	 * @author chengWenFeng
	 * @date 2014-2-15
	 */
	public static byte[] getRspAsByteByGetMethod(String url) throws IOException {
/*		String proxyIp=ConfigFactory.getString("cntv.url.proxy", "10.10.160.249");
		int proxyPort=ConfigFactory.getInt("cntv.url.prot",8081);*/
		//HttpHost proxy = new HttpHost(proxyIp, proxyPort); 
		//CloseableHttpClient httpClient = HttpClientBuilder.create().setProxy(proxy).build();
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet get = new HttpGet(url);
		try {
			HttpResponse response = httpClient.execute(get);
			return IOUtils.toByteArray(response.getEntity().getContent());
		} finally {
			httpClient.close();
		}
	}

	public static String getResponseAsStringByGetMethod(String reqUrl) throws IOException, URISyntaxException{
		String respContent=null;
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		URL url = new URL(reqUrl.toString());
		URI uri = new URI(url.getProtocol(), url.getHost(), url.getPath(), url.getQuery(), null);
		HttpGet get = new HttpGet(uri);
		try{
			HttpResponse response = httpClient.execute(get);
			respContent = new String(IOUtils.toByteArray(response.getEntity().getContent()), "utf-8");
		}finally{
			httpClient.close();
		}
		return respContent;
	}
	/**
	 * GET方式请求--以字符串的形式返回
	 * 
	 * @param url 目标url
	 * @param encoder 编码方式
	 * @return
	 * @throws IOException IO异常
	 */
	public static String getResponseAsStringByGetMethod(String url, String encoder) throws IOException {
		byte[] responseBody = getRspAsByteByGetMethod(url);
		// 处理内容
		if (StringUtils.isEmpty(encoder)) {
			encoder = "utf-8";
		}
		return new String(responseBody, encoder);
	}
	
	/**
	 * POST方式请求--以字符串的形式返回
	 * @param url 目标url
	 * @param para 参数
	 * @return
	 * @throws IOException IO异常
	 */
	public static String getResponseAsStringByPostMethod(String url, Map<String, String> para) throws IOException {
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost httppost = new HttpPost(url);
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		for (String key : para.keySet()) {
			formparams.add(new BasicNameValuePair(key, para.get(key)));
		}
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, DEFAULT_ENCODING);
		httppost.setEntity(entity);
		try {
			HttpResponse response = httpClient.execute(httppost);
			StringBuilder sb = new StringBuilder();
			BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), DEFAULT_ENCODING));
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			return sb.toString();
		} finally {
			httpClient.close();
		}
	}
	
	public static void main(String[] args) {
		BufferedImage jsion = getTfskeyInfo("http://n5.map.pg0.cn/T1jrDvBmVg1RCvBVdK", null, 30*1000);
		if(null!=jsion){
			System.out.println(jsion.getWidth());
			System.out.println(jsion.getHeight());
		}else{
			System.out.println("没有获取到");
		}
		System.out.println("-----------------------");
		Map<String, Object> map = getTfskeySizeInfo("http://n5.map.pg0.cn/T1jrDvBmVg1RCvBVdK/size", null, 30*1000);
		Integer s  = (Integer) map.get("width");
		System.out.println(s);
		System.out.println(map.get("height"));
		System.out.println(map.get("type"));
		
	}
	
	/**
	 * 获取TFSkey的宽高信息
	 * @author hubaoting
	 * @date 2016年1月29日 下午1:49:43
	 * @param oringinalURL
	 * @param environment
	 * @return BufferedImage
	 */
	public static BufferedImage getTfskeyInfo(String oringinalURL, String environment, int timeOut){
		BufferedImage sourceImg = null;
		HttpGet httpGet = null;
		HttpHost PROXY = null;
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse response = null;
		InputStream in = null;
		try {
			httpGet = createHttpGet(oringinalURL);
			PROXY = new HttpHost(SystemCommon.proxyIp, SystemCommon.proxyPort, "http");
			if ("online".equals(environment) || "dev".equals(environment)) {
				httpGet.setConfig(RequestConfig.custom().setProxy(PROXY).setConnectTimeout(timeOut).build());
			}
			httpclient = HttpClients.createDefault();
			response = httpclient.execute(httpGet);
			in = response.getEntity().getContent();
			sourceImg = ImageIO.read(in);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeAll(httpclient, response, in);
		}
		return sourceImg;
	}

	/**
	 * 获取TFSkey的宽高信息
	 * @author hubaoting
	 * @date 2016年1月29日 下午1:49:43
	 * @param oringinalURL
	 * @param environment
	 * @return Map&lt;String, Object&gt;
	 */
	public static Map<String, Object> getTfskeySizeInfo(String oringinalURL, String environment, int timeOut){
		HttpGet httpGet = null;
		HttpHost PROXY = null;
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse response = null;
		InputStream in = null;
		byte[] bt = null;
		try {
			httpGet = createHttpGet(oringinalURL+"/size");
			PROXY = new HttpHost(SystemCommon.proxyIp, SystemCommon.proxyPort, "http");
			if ("online".equals(environment) || "dev".equals(environment)) {
				httpGet.setConfig(RequestConfig.custom().setProxy(PROXY).setConnectTimeout(timeOut).build());
			}
			httpclient = HttpClients.createDefault();
			response = httpclient.execute(httpGet);
			in = response.getEntity().getContent();
			bt = IOUtils.toByteArray(in);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeAll(httpclient, response, in);
		}
		if(null==bt || 0==bt.length){ return null; }
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Map<String, Object>> map = null;
		try {
			map = objectMapper.readValue(bt, Map.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return map.get("img");
	}

	/**
	 * 获取图片宽和高信息
	 * @author hubaoting
	 * @date 2016年1月29日 下午3:12:47
	 * @param requestUrl
	 * @param environment
	 * @param isUserAgent
	 * @return Map&lt;String, Object&gt;
	 */
	public static Map<String, Object> getTfskeySizeInfo(String requestUrl, String environment, boolean isUserAgent) {
		StringBuffer buffer = null;
		HttpURLConnection httpUrlConn = null;
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		try {
			// 建立连接
			URL url = new URL(requestUrl+"/size");
			if ("online".equals(environment) || "dev".equals(environment)) {
				Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(SystemCommon.proxyIp, SystemCommon.proxyPort));
				httpUrlConn = (HttpURLConnection)url.openConnection(proxy);
			}else{
				httpUrlConn = (HttpURLConnection)url.openConnection();
			}
			httpUrlConn.setDoInput(true);
			httpUrlConn.setRequestMethod("GET");
			if(isUserAgent){
				httpUrlConn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			}
			// 获取输入流
			inputStream = httpUrlConn.getInputStream();
			inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			bufferedReader = new BufferedReader(inputStreamReader);

			// 读取返回结果
			buffer = new StringBuffer();
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeAll(httpUrlConn, inputStream, inputStreamReader, bufferedReader);
		}
		if(null==buffer ||"".equals(buffer.toString().trim())){ return null; }
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Map<String, Object>> map = null;
		try {
			map = objectMapper.readValue(buffer.toString().getBytes(), Map.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return map.get("img");
	}

	/**
	 * 获取图片宽和高信息
	 * @author hubaoting
	 * @date 2016年1月29日 下午2:53:59
	 * @param requestUrl
	 * @param environment
	 * @param isUserAgent
	 * @return String
	 */
	public static String getTfskeyInfo(String requestUrl, String environment, boolean isUserAgent) {
		StringBuffer buffer = null;
		HttpURLConnection httpUrlConn = null;
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		String str = null;
		try {
			// 建立连接
			URL url = new URL(requestUrl);
			if ("online".equals(environment) || "dev".equals(environment)) {
				Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(SystemCommon.proxyIp, SystemCommon.proxyPort));
				httpUrlConn = (HttpURLConnection)url.openConnection(proxy);
			}else{
				httpUrlConn = (HttpURLConnection)url.openConnection();
			}
			httpUrlConn.setDoInput(true);
			httpUrlConn.setRequestMethod("GET");
			if(isUserAgent){
				httpUrlConn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			}
			// 获取输入流
			inputStream = httpUrlConn.getInputStream();
			inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			bufferedReader = new BufferedReader(inputStreamReader);

			// 读取返回结果
			buffer = new StringBuffer();
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeAll(httpUrlConn, inputStream, inputStreamReader, bufferedReader);
		}
		return buffer.toString();
	}
	
	public static HttpGet createHttpGet(String url) {
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		httpGet.setHeader("Accept-Language",
				"en-US,en;q=0.8,zh-CN;q=0.6,zh;q=0.4");
		httpGet.setHeader("Cache-Control", "no-cache");
		httpGet.setHeader("Connection", "keep-alive");
		httpGet.setHeader("Pragma", "no-cache");
		httpGet.setHeader(
				"User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2540.0 Safari/537.36");
		httpGet.setHeader("Accept-Encoding", "gzip, deflate, sdch");
		return httpGet;
	}
	
	/**
	 * 关闭流
	 * @author liulin
	 * @date 2016年2月24日 上午10:35:23
	 * @param in void
	 */
	public static void close(InputStream in){
		if(null!=in){
			try {
				in.close(); in = null;
			} catch (Exception e2) 
			{e2.printStackTrace();}
		}
	}
	/**
	 * 关闭对象
	 * @author hubaoting
	 * @date 2016年1月29日 下午2:42:42
	 * @param httpclient
	 * @param is
	 * @param baos void
	 */
	public static void closeAll(CloseableHttpClient httpclient, InputStream is, ByteArrayOutputStream baos){
		if(null!=baos){
			try { baos.close(); } catch (IOException e) {e.printStackTrace(); }
			baos = null;
		}
		if(null!=is){
			try { is.close(); } catch (IOException e) {e.printStackTrace(); }
			is = null;
		}
		if(null!=httpclient){
			try { httpclient.close(); } catch (IOException e) {e.printStackTrace(); }
			httpclient = null;
		}
	}
	
	/**
	 * 关闭对象
	 * @author hubaoting
	 * @date 2016年1月29日 下午2:42:42
	 * @param httpclient
	 * @param is
	 * @param baos void
	 */
	public static void closeAll(HttpClient httpclient, InputStream is, ByteArrayOutputStream baos){
		if(null!=baos){
			try { baos.close(); } catch (IOException e) {e.printStackTrace(); }
			baos = null;
		}
		if(null!=is){
			try { is.close(); } catch (IOException e) {e.printStackTrace(); }
			is = null;
		}
		if(null!=httpclient){
			try { httpclient.getConnectionManager().shutdown(); } catch (Exception e) { e.printStackTrace(); }
		}
	}

	/**
	 * 关闭对象
	 * @author hubaoting
	 * @date 2016年1月29日 下午3:17:23
	 * @param httpclient
	 * @param response
	 * @param in
	 */
	public static void closeAll(CloseableHttpClient httpclient, CloseableHttpResponse response, InputStream in){
		if(null!=in){
			try { in.close(); } catch (Exception e) { e.printStackTrace(); }
			in = null;
		}
		if(null!=response){
			try { response.close(); } catch (Exception e) { e.printStackTrace(); }
			response = null;
		}
		if(httpclient!=null){
			try { httpclient.close(); } catch (IOException e) { e.printStackTrace(); }
		}
	}

	/**
	 * 关闭对象
	 * @author hubaoting
	 * @date 2016年1月29日 下午4:49:59
	 * @param httpclient
	 * @param response
	 * @param in
	 * @param baos
	 */
	public static void closeAll(CloseableHttpClient httpclient, CloseableHttpResponse response, InputStream in, ByteArrayOutputStream baos){
		if(null!=baos){
			try { baos.close(); } catch (IOException e) {e.printStackTrace(); }
			baos = null;
		}
		if(null!=in){
			try { in.close(); } catch (Exception e) { e.printStackTrace(); }
			in = null;
		}
		if(null!=response){
			try { response.close(); } catch (Exception e) { e.printStackTrace(); }
			response = null;
		}
		if(httpclient!=null){
			try { httpclient.close(); } catch (IOException e) { e.printStackTrace(); }
		}
	}

	/**
	 * 关闭对象
	 * @author hubaoting
	 * @date 2016年1月29日 下午2:42:42
	 * @param httpclient
	 * @param is
	 * @param baos
	 */
	public static void closeAll(HttpURLConnection huc , InputStream is, InputStreamReader isr, BufferedReader br){
		if(null!=br){
			try { br.close(); } catch (Exception e) {e.printStackTrace(); }
			br = null;
		}
		if(null!=isr){
			try { isr.close(); } catch (Exception e) {e.printStackTrace(); }
			isr = null;
		}
		if(null!=is){
			try { is.close(); } catch (Exception e) {e.printStackTrace(); }
			is = null;
		}
		if(null!=huc){
			try { huc.disconnect(); } catch (Exception e) {e.printStackTrace(); }
			huc = null;
		}
	}
	
}
