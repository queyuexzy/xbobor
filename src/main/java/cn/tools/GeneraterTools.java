package cn.tools;

import java.util.Calendar;

public class GeneraterTools {
	private static int m_serial = 0;
	private static Calendar m_latest = Calendar.getInstance();

	private static String strPad(String str, int len) {
		int l = str.length();
		String s = str;
		for (int i = 0; i < len - l; i++) {
			s = "0".concat(s);
		}
		return s;
	}

	public static synchronized String generateId() {
		Calendar rightNow;
		while (true) {
			rightNow = Calendar.getInstance();
			if (!rightNow.equals(m_latest)) {
				m_serial = 0;
				m_latest = rightNow;
				break;
			}
			if (m_serial < 255) {
				m_serial += 1;
				break;
			}
			try {
				Thread.sleep(1L);
			} catch (InterruptedException e) {
			}
		}
		long year = rightNow.get(Calendar.YEAR);
		long month = rightNow.get(Calendar.MONTH) + 1;
		long day = rightNow.get(Calendar.DATE);
		long hour = rightNow.get(Calendar.HOUR_OF_DAY);
		long minute = rightNow.get(Calendar.MINUTE);
		long second = rightNow.get(Calendar.SECOND);
		long millisecond = rightNow.get(Calendar.MILLISECOND);
		year <<= 36;
		month <<= 32;
		day <<= 27;
		hour <<= 22;
		minute <<= 16;
		second <<= 10;
		long t48 = year | month | day | hour | minute | second | millisecond;
		String ser = Integer.toHexString(m_serial);
		ser = strPad(ser, 2);
		String sid = Long.toHexString(t48).concat(ser);
		sid = strPad(sid, 14);
		return sid;
	}
	
	public static long generateLongId() {
		final String sid = generateId();
		return Long.parseLong(sid, 16);
	}
}
