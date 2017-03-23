package cn.tools;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Provide some security related methods, include MD5 creator.
 * 
 * @author Luo Yinzhuo
 */
public class SecurityTools {
	/**
	 * Get MD5 encode string.
	 * 
	 * @param key
	 * The input key.
	 * @return The MD5 encode string.
	 */
	public static String getMD5(String key) {
		if (key == null) {
			key = "";
		}

		try {
			return createMD5(key);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "";
		}
	}

	/** The hex digits. */
	private static char HEX_DIGITS[] = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	/**
	 * Get MD5 encode string.
	 * 
	 * @param s
	 * The original string.
	 * @return The MD5 encode string.
	 * @throws NoSuchAlgorithmException
	 * If there's no support.
	 */
	private static final String createMD5(String s)
			throws NoSuchAlgorithmException {
		byte[] btInput = s.getBytes();
		MessageDigest mdInst = MessageDigest.getInstance("MD5");
		mdInst.update(btInput);
		byte[] md = mdInst.digest();
		int j = md.length;
		char str[] = new char[j * 2];
		int k = 0;
		for (int i = 0; i < j; i++) {
			byte byte0 = md[i];
			str[k++] = HEX_DIGITS[byte0 >>> 4 & 0xf];
			str[k++] = HEX_DIGITS[byte0 & 0xf];
		}
		return new String(str);
	}
}
