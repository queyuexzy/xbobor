package cn.tools;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class NumberHelper {

	/**
	 * 字符串转整形
	 * @param num
	 * @return
	 */
	public static int stringToInt(String num){
		int result = 0;
		try{
			result = Integer.parseInt(num);
		}catch(Exception ex){
			result = 0;
		}
		return result;
	}
	/**
	 * 字符串转整形(出错时设置默认值)
	 * @param num
	 * @return
	 */
	public static int stringToInt(String num,int defult){
		int result = 0;
		try{
			result = Integer.parseInt(num);
		}catch(Exception ex){
			result = defult;
		}
		return result;
	}

	
	/**
	 * 将字符串转换成浮点型，转换失败时返回0
	 * @author xieyan
	 * @date   2012-6-3下午06:06:33
	 * @param src
	 * @return 
	 */
	public static float stringToFloat(String src){
		float result = 0;
		try{
			result = Float.parseFloat(src);			
		}catch(Exception ex){
			
		}
		return result;
	}
	/**
	 * 将float后面两个保留两位
	 * @Title: floadToString
	 * @data:2012-7-18下午05:44:57
	 * @author:zxd
	 *
	 * @param fc
	 * @return
	 */
	public static String floadToString(float fc){
		DecimalFormat   fnum   =   new   DecimalFormat("##0.00");
		String   dd=fnum.format(fc);	  
		return dd;
	}
	/**
	 * 将字符转换成浮点型，如果失败返回传入的参数
	 * @author xieyan
	 * @date   2012-6-3下午06:09:08
	 * @param src
	 * @param defult
	 * @return
	 */
	public static float stringToFloat(String src,float defult){
		float result = 0;
		try{
			result = Float.parseFloat(src);	
			
		}catch(Exception ex){
			result = defult;
		}
		return result;
	}
	/**
	 * 字符转成double
	 * @Title: stringToDouble
	 * @data:2012-7-20上午10:30:15
	 * @author:zxd
	 *
	 * @param src
	 * @return
	 */
	public static double stringToDouble(String src){
		double result = 0;
		try{
			result = Double.parseDouble(src);	
			
		}catch(Exception ex){
		}
		return result;
	}

	/**
	 * 精确四舍五入
	 * @Title: round
	 * @data:2012-7-18下午06:36:18
	 * @author:zxd
	 *
	 * @param v
	 * @param scale 小数点后溜几位
	 * @return
	 */
	public static float round(double v,int scale){
		if(scale<0){
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale,BigDecimal.ROUND_HALF_UP).floatValue();
	}
	/**
	* 传入Integer类型数据，如果数据为null，返回0
	* @param src
	* @return
	*/
	public static int getIntegerToInt(Integer src){
		int result = 0;
		if(src==null){
			result = 0;
		}else{
			result = src;
		}
		return result;
	}
	public static long getLongTolong(Long src){
		long result = 0;
		if(src==null){
			result = 0;
		}else{
			result = src;
		}
		return result;
	}
	public static float getFloatTofloat(Float src){
		float result = 0;
		if(src==null){
			result = 0;
		}else{
			result = src;
		}
		return result;
	}
	public static void main(String[] args) {
//		String ss = "-0.655%";
////		NumberHelper number = new NumberHelper();
////		float aa = number.stringToFloat(ss);
////		System.out.println(aa);
////		String bb = number.floadToString(aa);
////		System.out.println(bb);
////		String cc = NumberHelper.roundContent(ss);
////		System.out.println(cc);
		
	}
}
