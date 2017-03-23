package cn.tools;

import java.awt.Font;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.jfree.chart.StandardChartTheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExcelHelper {

	private Logger logger = LoggerFactory.getLogger(ExcelHelper.class);
	/**
	 * 设置统计图样式
	 * @author xieyan
	 * @date   2012-10-19下午03:09:49
	 * @return
	 */
	private StandardChartTheme getChartTheme(){
		//创建主题样式  
		StandardChartTheme standardChartTheme=new StandardChartTheme("CN");  
		standardChartTheme.setLargeFont(new Font("黑体",Font.TYPE1_FONT,12));
		//设置标题字体  
		standardChartTheme.setExtraLargeFont(new Font("黑体",Font.TYPE1_FONT,12));  
		//设置图例的字体  
		standardChartTheme.setRegularFont(new Font("黑体",Font.TYPE1_FONT,12));  
		//设置轴向的字体  
		standardChartTheme.setLargeFont(new Font("黑体",Font.TYPE1_FONT,12));  
		return standardChartTheme;
	}
	
	/**
	 * 合并单元格（连续的横行）
	 * @Title: mergeCells
	 * @data:2012-11-2下午12:29:08
	 * @author:liweidong
	 *
	 * @param sheet		当前sheet
	 * @param startStr	开始列
	 * @param endStr	结束列
	 * @param rowIndex	行数
	 */
	public static void mergeCells(Sheet sheet, String startStr, String endStr, int rowIndex){
		sheet.addMergedRegion(CellRangeAddress.valueOf("$"+startStr+"$"+rowIndex+":$"+endStr+"$"+rowIndex));
	}
	
	/**
	 * 设置行高
	 * @Title: setHeight
	 * @data:2012-11-7上午11:39:23
	 * @author:liweidong
	 *
	 * @param row
	 * @param height
	 */
	public static void setHeight(Row row, float height){
		row.setHeightInPoints(height);
	}
	
	
	/**
	 * 获取excel自适应行高
	 * @Title: getExcelCellAutoHeight
	 * @data:2013-11-14下午12:29:50
	 * @author:lilei
	 *
	 * @param str
	 * @param fontCountInline
	 * @return
	 */
	public static float getExcelCellAutoHeight(String str, float fontCountInline) {
		float defaultRowHeight = 12.00f;//每一行的高度指定
		float defaultCount = 0.00f;
		for (int i = 0; i < str.length(); i++) {
			float ff = getregex(str.substring(i, i + 1));
			defaultCount = defaultCount + ff;
		}
		return ((int) (defaultCount / fontCountInline) + 1) * defaultRowHeight;//计算
	}
	
	public static float getregex(String charStr) {
		if(charStr==" "){
			return 0.5f;
		}
		// 判断是否为字母或字符
		if (Pattern.compile("^[A-Za-z0-9]+$").matcher(charStr).matches()) {
			return 0.5f;
		}
		// 判断是否为全角
	
		if (Pattern.compile("[\u4e00-\u9fa5]+$").matcher(charStr).matches()) {
			return 1.00f;
		}
		//全角符号 及中文
		if (Pattern.compile("[^x00-xff]").matcher(charStr).matches()) {
			return 1.00f;
		}
		return 0.5f;
	}
	
	/**
	 * 从cell中取值
	 * @Title: getValue
	 * @data:2012-8-3上午11:51:57
	 * @author:liweidong
	 *
	 * @param cell
	 * @return
	 */
	public static String getValue(Cell cell) {
		String value = "";
		switch (cell.getCellType()) {
			case Cell.CELL_TYPE_STRING:
				value = cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					Date date = cell.getDateCellValue();
					if (date != null) {
						value = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
					} else {
						value = "";
					}
				}else{
					value = new DecimalFormat("0").format(cell.getNumericCellValue());
				}
				break;
			case Cell.CELL_TYPE_FORMULA:
				break;
			case Cell.CELL_TYPE_BLANK:
				break;
			case Cell.CELL_TYPE_ERROR:
				value = "";
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				value = (cell.getBooleanCellValue() == true ? "Y": "N");
				break;
			default:
				value = "";
		}
		return value;
	}
}

