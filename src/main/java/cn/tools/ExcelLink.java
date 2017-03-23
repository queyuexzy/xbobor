/*  
 * @(#) Excel074Link.java Create on 2012-10-26 下午05:29:32   
 *   
 * Copyright 2012 by xl.   
 */


package cn.tools;

import org.apache.poi.hssf.usermodel.HSSFHyperlink;

/**
 * Excel链接   2003
 * @author liweidong
 * @date   2012-10-26
 */
public class ExcelLink extends HSSFHyperlink {

	public ExcelLink(int type) {
		super(type);
	}
	
}
