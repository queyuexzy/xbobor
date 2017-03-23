package cn.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

/**
 * http 发送信息工具类
 * 
 * @author zhl
 * 
 */
public class CommonSendMeg {
	/**
	 * 发送一组url，主要用于关键词管理
	 * 
	 * @param url
	 * 需要访问的服务地址列表
	 * @param parameters
	 * 需要发送的参数
	 * @throws IOException
	 */
	public static void sendUrlList(String url, String parameters)
			throws IOException {
		if (url.indexOf(",") > 0) {
			String[] urls = url.split(",");
			if (urls != null) {
				for (int i = 0; i < urls.length; i++) {
					String urlTemp = urls[i];
					if (urlTemp.trim().length() > 0) {
						sendURL(urlTemp + parameters);
					}
				}
			}
		} else {
			sendURL(url + parameters);
		}
	}

	/**
	 * 发送信息
	 * 
	 * @param urlPath
	 * url+参数
	 * @throws IOException
	 */
	public static void sendURL(String urlPath) throws IOException {
		InputStream ins = null;
		HttpURLConnection urlConnection = null;
		try {
			URL url = new URL(urlPath.toString());
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.connect();
			urlConnection.setConnectTimeout(30000);
			ins = urlConnection.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (ins != null) {
				try {
					ins.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
			urlConnection.disconnect();
		}
	}

	/**
	 * 发送消息
	 * 
	 * @param urlPath
	 * @param parameters
	 * @return
	 */
	public static String sendMeg(String urlPath, String parameters) {
		InputStream urlStream = null;
		HttpURLConnection conn = null;
		String CurrentLine = "";
		StringBuffer returnJSON = new StringBuffer();
		try {
			URL url = new URL(urlPath);
			conn = (HttpURLConnection) url.openConnection();

			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setUseCaches(false);
			conn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			conn.setRequestProperty("Content-Length",
					String.valueOf(parameters.length()));
			conn.setDoInput(true);
			conn.connect();
			OutputStreamWriter out = new OutputStreamWriter(
					conn.getOutputStream(), "UTF-8");
			out.write(parameters);
			out.flush();
			out.close();

			urlStream = conn.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					urlStream, "UTF-8"));
			while ((CurrentLine = reader.readLine()) != null) {
				returnJSON.append(CurrentLine);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (urlStream != null) {
				try {
					urlStream.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
			conn.disconnect();
		}
		return returnJSON.toString();
	}

	public static void getHtml(HttpServletResponse response, String urlPath,
			String parameters) {
		InputStream urlStream = null;
		HttpURLConnection conn = null;
		OutputStream outs = null;
		System.out.println("url==" + urlPath + parameters);
		// StringBuffer returnJSON = new StringBuffer();
		try {
			URL url = new URL(urlPath);
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setUseCaches(false);
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Content-Length", String.valueOf(parameters.length()));
			conn.setDoInput(true);
			conn.connect();
			OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			out.write(parameters.toString());
			out.flush();
			out.close();
			urlStream = conn.getInputStream();
			System.out.println("----调用---");
			response.setContentType(conn.getContentType());
			response.setHeader("Cache-Control", conn.getHeaderField("Cache-Control"));
			response.setHeader("Pragma", conn.getHeaderField("Pragma"));
			response.setHeader("Expires", conn.getHeaderField("Expires"));
			// System.out.println("----调用111---");
			outs = response.getOutputStream();
			// System.out.println("----调用2222--");
			byte[] buffer = new byte[5000];
			int bytesRead = 0;
			while ((bytesRead = urlStream.read(buffer, 0, 5000)) != -1) {
				outs.write(buffer, 0, bytesRead);
				// String a=new String(buffer);
				// returnJSON.append(a);
			}
			// System.out.println(returnJSON);
			outs.flush();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (urlStream != null) {
				try {
					urlStream.close();

				} catch (IOException e) {

					e.printStackTrace();
				}
			}
			if (outs != null) {
				try {
					outs.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
			conn.disconnect();
		}
		return;
	}

	/**
	 * 客户端写值 html格式
	 * 
	 * @param msg
	 */
	public static void writeMsg(String msg) {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setDateHeader("Expires", 0);

		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			pw.write(msg);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
	}
	
	/**
	 * 客户端写文件格式
	 * 
	 * @param msg
	 */
	public static void writeFile(String msg, String fileName) {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Disposition", "filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream");
		response.setDateHeader("Expires", 0);
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			pw.write(msg);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
	}

	/**
	 * Write byte array message.
	 * 
	 * @author Luo Yinzhuo
	 * @param msg
	 * The byte array.
	 * @throws IOException
	 * If any I/O error occurs.
	 * @date 2014年8月22日 下午3:47:24
	 */
	public static void writeMsg(byte[] msg) throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setDateHeader("Expires", 0);
		try {
			response.getOutputStream().write(msg);
			response.getOutputStream().flush();
		} finally {
			response.getOutputStream().close();
		}
	}

	/**
	 * 给客户端写xml
	 * 
	 * @param msg
	 */
	public static void writeXml(String msg) {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/xml");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			pw.write(msg);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
	}
}
