package cn.tools;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang3.time.FastDateFormat;

/**
 * 时间操作 公共类
 * @ClassName FastDateFormatHelper
 * @Description
 * @Author hubaoting
 * @Date 2015年8月19日 下午3:52:56
 */
public final class FastDateFormatHelper {

	/** The default date format. */
	public static final FastDateFormat DEFAULT = FastDateFormat
			.getInstance("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 比较字符串时间的大小
	 * @Description
	 * @Author hubaoting
	 * @Date 2015年8月19日 下午4:04:16
	 * @param beginTime 开始时间
	 * @param endTime 结束时间[为空或null时直接返回true]
	 * @param format 时间格式
	 * @return boolean 是否在结束时间之前[true表示开始时间小于结束时间，false表示开始时间大于结束时间]
	 */
	public static boolean compareDate(String beginTime, String endTime, String format){
		SimpleDateFormat sdf = null;
		boolean result = false; 
		try {
			if("".equals(beginTime) || null==beginTime){
				return false;
			}
			if("".equals(endTime) || null==endTime){
				return true;
			}
			sdf = new SimpleDateFormat(format);
			Date d1 = sdf.parse(beginTime);
			Date d2 = sdf.parse(endTime);
			String localTime = sdf.format(new Date());
			Date d3 = sdf.parse(localTime);
			if(localTime.equals(endTime) || !d2.before(d3)){
				if(beginTime.equals(endTime) || d1.before(d2)){
					//表示d1在d2之前
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 结束时间与当前时间字符串比较大小
	 * @Description
	 * @Author hubaoting
	 * @Date 2015年8月19日 下午4:04:16
	 * @param endTime 结束时间[为空或null时直接返回true]
	 * @param format 时间格式
	 * @return boolean 是否在结束时间之前[true表示开始时间小于结束时间，false表示开始时间大于结束时间]
	 */
	public static boolean compareDate(String endTime, String format){
		SimpleDateFormat sdf = null;
		boolean result = false; 
		try {
			if("".equals(endTime) || null==endTime){
				return true;
			}
			sdf = new SimpleDateFormat(format);
			Date d2 = sdf.parse(endTime);
			String localTime = sdf.format(new Date());
			Date d3 = sdf.parse(localTime);
			if(localTime.equals(endTime) || !d2.before(d3)){
				//表示d2在d3之前
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static void main(String[] args) {
		/*if(compareDate("2015-08-31 15:54:32", "yyyy-MM-dd")){
			System.out.println("前");
		}else{
			System.out.println("后");
		}*/
		
		String str = "ab, c, d, d, s";
		String[] ab = str.split(",");
		System.out.println(ab.length);
		for (int i = 0; i < ab.length; i++) {
			if(i>2){
				break;
			}
			System.out.println(ab[i]);
		}
	}

}
