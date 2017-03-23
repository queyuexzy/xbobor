package cn.tools;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;

/**
 * 工具类
 * 
 * @author 祁海峰
 * 
 */
public class Util {

	private static final Util INSTANCE = new Util();

	public static Util getInstance() {
		return INSTANCE;
	}

	private Util() {
	}

	/**
	 * 判断FTP方式更新附件的时候，是否输入了正确的文件名。
	 * 
	 * @param filename
	 * String
	 * @return boolean
	 */
	public boolean attachFileNameIsNotRight(String filename) {
		Pattern p = Pattern.compile("[^\\w\\d.,_-]");
		Matcher m = p.matcher(filename);
		return m.find();
	}

	/**
	 * 返回某个字母在26个字母中的位置
	 * 
	 * @param c
	 * @return int
	 */
	public int charInAZ(char c) {
		int i = c;
		return i - 96;
	}

	/**
	 * 把中文转为unicode编码字符串 ChinaToUnicode 2005-6-24 10:35:48
	 * 
	 * @param cnGBK
	 * 中文字符串
	 * @return 字符串
	 */
	public String ChinaToUnicode(String cnGBK) {
		StringBuffer buff = new StringBuffer();
		for (int x = 0; x < cnGBK.length(); x++) {
			try {
				byte[] b = cnGBK.substring(x, x + 1).getBytes();
				if (b.length == 2) {
					b = cnGBK.substring(x, x + 1).getBytes("UniCode");
					buff.append("\\u");
					for (int i = b.length - 1; i >= b.length - 2; i--) {
						if (b[i] < 16 && b[i] >= 0) {
							buff.append("0");
						}
						buff.append(Integer.toHexString(b[i] & 0xff));
					}
				} else {
					buff.append(cnGBK.substring(x, x + 1));
				}
			} catch (UnsupportedEncodingException ex) {
			}
		}
		return buff.toString();
	}

	/**
	 * 图片尺寸
	 * 
	 * @param filePath
	 * String
	 * @return String
	 */
	public String getGraphicSize(String fileName) {
		// fileName = filepath + filename
		File _file = new File(fileName); // 读入文件
		Image src = null;
		int wideth = 0;
		int height = 0;
		try {
			src = javax.imageio.ImageIO.read(_file); // 构造Image对象
			wideth = src.getWidth(null); // 得到源图宽
			height = src.getHeight(null); // 得到源图长
		} catch (IOException ex) {
		}

		return wideth + ", " + height;
	}

	/**
	 * 不允许访问网站的IP
	 * 
	 * @param ip
	 * String
	 * @param cfg
	 * config
	 * @return boolean
	 */
	public boolean isNotAllowIP(String ip, String notAllowIPs) {
		String ips = notAllowIPs;
		if (ips.equalsIgnoreCase("null")) {
			return false;
		}

		Pattern p = Pattern.compile("[,]");
		String[] notAllowIP = p.split(ips);

		ip = ip.toLowerCase();
		for (int i = 0; i < notAllowIP.length; i++) {
			if (notAllowIP[i].indexOf(ip) != -1) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 编码转换
	 * 
	 * @param str
	 * String
	 * @return String
	 */
	public String iso88591ToUtf8(String str) {
		String rTxt = "";
		try {
			if (str != null && !str.equals("")) {
				try {
					rTxt = new String(str.getBytes("ISO-8859-1"), "UTF-8");
				} catch (Exception e) {
				}
			} else {
				rTxt = "";
			}
			return rTxt;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rTxt;
	}

	/**
	 * 数组中是否含有某个字符串
	 * 
	 * @param arr
	 * String[]
	 * @param str
	 * String
	 * @return boolean
	 */
	public boolean isStringInArray(String[] arr, String str) {
		boolean isIn = false;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].equals(str)) {
				isIn = true;
				break;
			}
		}
		return isIn;
	}

	/**
	 * 处理NULL字符串
	 * 
	 * @param Str
	 * String
	 * @return String
	 */
	public String nullStr(String str) {

		if (str == null || "null".equals(str)) {
			str = "";
		}

		return str;
	}

	/**
	 * 处理NULL字符串
	 * 
	 * @param Str
	 * String
	 * @param newStr
	 * String
	 * @return String
	 */
	public String nullStr(String str, String newStr) {

		if (str == null || "null".equals(str)) {
			str = newStr;
		}

		return str;
	}

	/**
	 * 处理NULL字符串
	 * 
	 * @param Str
	 * String
	 * @param newStr
	 * String
	 * @return String
	 */
	public String NVL(String str, String newStr) {

		if (str == null || "".equals(str.trim())) {
			str = newStr;
		}

		return str.trim();
	}

	/**
	 * 处理NULL字符串
	 * 
	 * @param Str
	 * String
	 * @param newStr
	 * String
	 * @return String
	 */
	public String NVL(String str) {

		if (str == null || "".equals(str.trim())) {
			str = "";
		}

		return str.trim();
	}

	/**
	 * 将某个整型对象转换为整型数字
	 * 
	 * @param obj
	 * Object
	 * @param val
	 * int
	 * @return int
	 */
	public int parseInt(String str, int val) {
		int intNumber = val;
		try {
			intNumber = Integer.parseInt(str);
		} catch (Exception ex) {
			intNumber = val;
		}
		return intNumber;
	}

	/**
	 * 将某个整型对象转换为Integer，如果错误，则抛出NewdirectException
	 * 
	 * @param str
	 * String
	 * @return Integer
	 * @throws NewdirectException
	 */
	public Integer parseInteger(String str) {
		Integer intNumber;
		try {
			intNumber = new Integer(str);
		} catch (Exception ex) {
			intNumber = new Integer(0);

			ex.printStackTrace();
		}
		return intNumber;
	}

	/**
	 * 将某个整型对象转换为Integer
	 * 
	 * @param obj
	 * Object
	 * @param val
	 * int
	 * @return Integer
	 */
	public Integer parseInteger(String str, int val) {
		Integer intNumber;
		try {
			intNumber = new Integer(str);
		} catch (Exception ex) {
			intNumber = new Integer(val);
		}
		return intNumber;
	}

	/**
	 * 将某个整型对象转换为long数字
	 * 
	 * @param obj
	 * Object
	 * @param val
	 * long
	 * @return long
	 */
	public long parseLong(String str, long val) {
		long longNumber = val;
		try {
			longNumber = Long.parseLong(str);
		} catch (Exception ex) {
			longNumber = val;
		}
		return longNumber;
	}

	/**
	 * 编码转化
	 * 
	 * @param str
	 * String
	 * @return String
	 */
	public String toUtf8(String str) {
		return str;
		/*
		 * try { String rTxt = new String(""); if (str != null &&
		 * !str.equals("")) { try { rTxt = new
		 * String(str.getBytes("ISO-8859-1"), "UTF-8"); } catch (Exception e) {}
		 * } else { rTxt = ""; } return rTxt; } catch (Exception e) {
		 * e.printStackTrace(); } return "null"; //
		 */
	}

	/**
	 * 验证密码
	 * 
	 * @param email
	 * String
	 * @return boolean
	 */
	public boolean validateEmail(String email) {
		// The email address cannot involve unapt character
		Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$");
		Matcher m = p.matcher(email);

		if (m.find()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 密码不能含有特殊字符
	 * 
	 * @param password
	 * String
	 * @return boolean
	 */
	public boolean validatePassword(String password) {
		Pattern p = Pattern.compile("^\\w+$");
		Matcher m = p.matcher(password);

		if (m.find()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 验证用户名的前后是否含有空格
	 * 
	 * @param username
	 * String
	 * @return boolean
	 */
	public boolean validateUserName(String username) {
		// The userName address cannot involve unapt character
		Pattern p = Pattern.compile("^ | $|  ");
		Matcher m = p.matcher(username);

		if (m.find()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 将阿拉伯数字转换为大写，如11转换为十一
	 * 
	 * @param str
	 * String
	 * @return String
	 */
	public String convertInt(String str) {
		String[] cstr = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };

		String[] wstr = { "", "", "十", "佰", "仟", "萬", "拾", "佰", "仟", "億", "拾", "佰", "仟" };
		int len = str.length();
		int i;

		String tmpstr, rstr;
		rstr = "";
		for (i = 1; i <= len; i++) {
			tmpstr = str.substring(len - i, len + 1 - i);
			rstr = cstr[Integer.parseInt(tmpstr)] + wstr[i] + rstr;
		}
		rstr = rstr.replace("一十", "十");
		rstr = rstr.replace("十零", "十");
		rstr = rstr.replace("零十", "零");
		rstr = rstr.replace("零佰", "零");
		rstr = rstr.replace("零仟", "零");
		rstr = rstr.replace("零万", "萬");
		for (i = 1; i <= 6; i++) {
			rstr = rstr.replace("零零", "零");
		}
		rstr = rstr.replace("零万", "零");
		rstr = rstr.replace("零亿", "億");
		rstr = rstr.replace("零零", "零");
		return rstr;
	}

	/**
	 * 特殊字符转义
	 * */
	public String escapeStr(String str) {
		if (str == null || str.trim().length() == 0) {
			return "";
		}
		str = str.replaceAll("\"", "&quot;");
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		return str;
	}

	/**
	 * 判断对象是否为空
	 * 
	 * @author zhl
	 * @date 2011-11-2上午10:14:33
	 * 
	 * @param bean
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public boolean checkEmpty(Object bean) {
		if (bean == null) {
			return true;
		}
		if (bean instanceof String) {
			return "".equals(NVL(bean.toString()));
		}
		if (bean instanceof Collection) {
			return ((Collection) bean).size() == 0;
		}

		if (bean instanceof Map) {
			return ((Map) bean).size() == 0;
		}
		return false;
	}

	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip != null && ip.length() > 0) {
			String[] ips = ip.split(",");
			for (int i = 0; i < ips.length; i++) {
				if (ips[i].trim().length() > 0 && !"unknown".equalsIgnoreCase(ips[i].trim())) {
					ip = ips[i].trim();
					break;
				}
			}
		}
		return ip;
	}
	
	/**
	 * 得到按指定格式的系统当前时间
	 * @titile getSysDate
	 * @date 2014-5-27 上午10:14:17 
	 * @author zcf
	 *
	 * @param dateFormat
	 * @return
	 */
	public static String getSysDate(String dateFormat) {
		if (dateFormat == null || "".equals(dateFormat)) {
 dateFormat = "yyyy-MM-dd HH:mm:ss";
		}
		Calendar date = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		String dateStr = sdf.format(date.getTime());
		return dateStr;
	}
	
	/**
	 * 处理NULL字符串
	 * @titile nullStr
	 * @date 2014-5-30 上午9:24:08 
	 *
	 * @param str
	 * @return
	 */
	public static String checkNull(String str) {

		if (str == null || "null".equals(str)) {
			str = "";
		}

		return str;
	}
}
