/*  
 * @(#) MailSender.java Create on 2012-7-31 下午06:05:14
 *
 * Copyright 2012 by xl.
 */
package cn.tools;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import sun.misc.BASE64Encoder;

/**
 * 
 * @author zxd
 * @date 2012-7-31
 */
public class WordTestUteil {

	private String getImageStr() {
		String imgFile = "d:/1.png";
		InputStream in = null;
		byte[] data = null;
		try {
			in = new FileInputStream(imgFile);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		BASE64Encoder encoder = new BASE64Encoder();
			return encoder.encode(data);
		}
}
