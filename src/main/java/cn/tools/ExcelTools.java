package cn.tools;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidOperationException;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * 
 * @author zhl
 * 
 */
public class ExcelTools<T> {
	/**
	 * 
	 * @param file
	 * excel文件
	 * @param cols
	 * 读到第几列
	 * @param startRowNum
	 * 开始行数
	 * @param mapper
	 * 列数和字段名称的对应
	 * @param classType
	 * 返回bean的类型
	 * @return List<T>
	 * @throws Exception
	 */
	public List<T> readExcel(File file, int cols, int startRowNum, Map<Integer, String> mapper, Class<T> classType) throws Exception {
		if (file != null) {
			String fileName = file.getName();
			if (fileName.lastIndexOf(".xlsx") > 0) {
				try {
					return this.read2007Excel(file, cols, startRowNum, mapper, classType);
				} catch (InvalidOperationException e) {
					return this.read2003Excel(file, cols, startRowNum, mapper, classType);
				} catch (Exception e) {
					throw e;
				}

			} else {
				try {
					return this.read2003Excel(file, cols, startRowNum, mapper, classType);
				} catch (OfficeXmlFileException e) {
					return this.read2007Excel(file, cols, startRowNum, mapper, classType);
				} catch (InvalidOperationException e) {
					return this.read2007Excel(file, cols, startRowNum, mapper, classType);
				} catch (Exception e) {
					throw e;
				}
			}
		}
		return null;
	}

	public List<T> read2003Excel(File file, int cols, int startRowNum, Map<Integer, String> mapper, Class<T> classType) throws Exception {
		List<T> list = null;
		FileInputStream fis = null;
		// POIFSFileSystem fs = null;
		try {
			fis = new FileInputStream(file); // 根据excel文件路径创建文件流
			// fs = new POIFSFileSystem(fis); // 利用poi读取excel文件流
			HSSFWorkbook wb = new HSSFWorkbook(fis); // 读取excel工作簿
			int sheetNum = wb.getNumberOfSheets();
			list = new ArrayList<T>();
			for (int k = 0; k < sheetNum; k++) {
				HSSFSheet sheet = wb.getSheetAt(k); // 读取excel的sheet，0表示读取第一个、1表示第二个.....

				List<T> rowList = getExeclList(cols, startRowNum, sheet, mapper, classType);
				if (rowList != null && rowList.size() > 0) {
					list.addAll(rowList);
				}

			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (fis != null) {
				fis.close();
			}

		}

		return list;
	}

	public List<T> read2007Excel(File file, int cols, int startRowNum, Map<Integer, String> mapper, Class<T> classType) throws Exception {
		List<T> list = null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file); // 根据excel文件路径创建文件流
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			int sheetNum = wb.getNumberOfSheets();
			list = new ArrayList<T>();
			for (int k = 0; k < sheetNum; k++) {
				XSSFSheet sheet = wb.getSheetAt(k); // 读取excel的sheet，0表示读取第一个、1表示第二个.....
				List<T> rowList = getExeclList(cols, startRowNum, sheet, mapper, classType);
				if (rowList != null && rowList.size() > 0) {
					list.addAll(rowList);
				}

			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (fis != null) {
				fis.close();
			}
		}

		return list;
	}

	/**
	 * 获取excel信息
	 * 
	 * @param cols
	 * 读到第几列
	 * @param startRowNum
	 * 开始行数
	 * @param sheet
	 * @param mapper
	 * @param classType
	 * @return
	 * @throws Exception
	 */
	private List<T> getExeclList(int cols, int startRowNum, Sheet sheet, Map<Integer, String> mapper, Class<T> classType) throws Exception {
		List<T> rowList = new ArrayList<T>();
		int num = sheet.getPhysicalNumberOfRows();
		String sheetName = sheet.getSheetName();
		for (int i = startRowNum; i < num; i++) {
			Row row = sheet.getRow(i);
			if (row != null) {
				rowList.add(this.parseObject(row, mapper, classType, sheetName));
			}
		}
		return rowList;
	}

	private String formatNum(double d) {
		DecimalFormat formatter = new java.text.DecimalFormat("########.### ");
		return formatter.format(d);
	}

	private String getValue(Cell cell) {
		int cellType = cell.getCellType();
		String result = "";
		switch (cellType) {
		case Cell.CELL_TYPE_NUMERIC:
			result = DateUtil.isCellDateFormatted(cell) ? DateHelper.getSpecifiedDate(cell.getDateCellValue().getTime(), DateHelper.FMT_DATE_DATETIME) : formatNum(cell.getNumericCellValue());
			break;
		case Cell.CELL_TYPE_STRING:
			result = cell.getStringCellValue();
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			result = String.valueOf(cell.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_BLANK:
			result = null;
			break;
		case Cell.CELL_TYPE_FORMULA:
			result = cell.getCellFormula();
			break;
		}
		return result;
	}

	private boolean hasValue(Cell cell) {
		if (cell != null) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) throws Exception {
		// ExcelTools<ArticleBean> excelTools = new ExcelTools<ArticleBean>();
		// Map<Integer, String> mapper = new HashMap<Integer, String>();
		// mapper.put(0, "title");
		// mapper.put(1, "clientcode");
		// List<ArticleBean> list = excelTools.read2007Excel(file, 0, 1, mapper, ArticleBean.class);
		//
		// for (ArticleBean b : list) {
		// // TestExcelBean b = (TestExcelBean)a;
		// System.out.println(b.getTitle() + "," + b.getClientcode());
		// }
		@SuppressWarnings("unused")
		File file = new File("C:/Users/zhanghongliang/Desktop/7dc3faa0a0de01.xls");// 中央政府机构.xlsx ,政务人物表.xls
	//	ExcelTools<EvaluatonInfoBean> excelTools = new ExcelTools<EvaluatonInfoBean>();
		Map<Integer, String> mapper = new HashMap<Integer, String>();
		int idx = 0;
		mapper.put(idx++, "_clientName");
		mapper.put(idx++, "_brandName");
		mapper.put(idx++, "productNum");
		mapper.put(idx++, "productName");
		mapper.put(idx++, "productType");
		mapper.put(idx++, "productClassification");
		mapper.put(idx++, "productTag");
		mapper.put(idx++, "evaluationMerit");
		mapper.put(idx++, "evaluationInsufficient");
		mapper.put(idx++, "baseUse");
		mapper.put(idx++, "evaluationTitle");
		mapper.put(idx++, "evaluationGood");
		mapper.put(idx++, "productUrl");
		mapper.put(idx++, "evaluationImg");
		mapper.put(idx++, "evaluationType");
		mapper.put(idx++, "nickname");
		mapper.put(idx++, "evaluationTime");
		mapper.put(idx++, "impTime");
		mapper.put(idx++, "evaluationCount");
		mapper.put(idx++, "_natureName");
//		for (EvaluatonInfoBean evaluatonInfoBean : list) {
//			System.out.println(evaluatonInfoBean);
//		}
	}

	public T parseObject(Row row, Map<Integer, String> mapper, Class<T> classType, String sheetName) throws Exception {
		T objResult = classType.newInstance();
		Set<Integer> keys = mapper.keySet();
		if (keys != null) {
			for (Integer key : keys) {
				String fieldName = mapper.get(key);
				Field field = classType.getDeclaredField(fieldName);
				String setMethodName = "set" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
				Method setMethod = objResult.getClass().getMethod(setMethodName, field.getType());
				if (key >= 0) {
					Cell cell = row.getCell(key);
					if (hasValue(cell)) {
						String valueStr = getValue(cell);
						if (valueStr != null && valueStr.trim().length() > 0) {
							Object value = getTypeIntValue(field.getType(), valueStr);
							setMethod.invoke(objResult, new Object[] { value });
						}
					}

				} else if (key == -1) {
					if (sheetName != null && sheetName.trim().length() > 0) {
						Object value = getTypeIntValue(field.getType(), sheetName);
						setMethod.invoke(objResult, new Object[] { value });
					}
				}

			}
		}
		return objResult;
	}

	@SuppressWarnings("rawtypes")
	private Object getTypeIntValue(Class typeClass, String value) {
		String typeName = typeClass.getSimpleName();
		Object result = null;
		if (value != null) {
			value = value.trim();
		}
		if (typeName.equals("String")) {
			result = value;
		} else if (typeName.equals("int")) {
			result = Integer.parseInt(value);
		} else if (typeName.equals("long")) {
			result = Long.parseLong(value);
		} else if (typeName.equals("float")) {
			result = Float.parseFloat(value);
		} else if (typeName.equals("double")) {
			result = Double.parseDouble(value);
		} else if (typeName.equals("char")) {
			result = value.charAt(0);
		} else if (typeName.equals("boolean")) {
			result = Boolean.parseBoolean(value);
		} else if (typeName.equals("Integer")) {
			result = Integer.parseInt(value);
		} else if (typeName.equals("Boolean")) {
			result = Boolean.parseBoolean(value);
		} else if (typeName.equals("Float")) {
			result = Float.parseFloat(value);
		} else if (typeName.equals("Double")) {
			result = Double.parseDouble(value);
		} else if (typeName.equals("Long")) {
			result = Long.parseLong(value);
		} else if (typeName.equals("Character")) {
			result = value.charAt(0);
		} else if (typeName.equals("Timestamp")) {
			long time = DateHelper.formatStringToLong(value, DateHelper.FMT_DATE_DATETIME);
			result = new Timestamp(time);
		} else if (typeName.equals("Date")) {
			long time = DateHelper.formatStringToLong(value, DateHelper.FMT_DATE_DATETIME);
			result = new Date(time);
		}

		return result;
	}
	/**
	 * 日报表格样式
	 * @Title: createStyles
	 * @data:2012-6-5上午11:37:36
	 * @author:zxd
	 *
	 * @param wb
	 * @return
	 */
	public  static Map<String, CellStyle> createStyles(Workbook wb){
		Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
		CellStyle style;
		Font titleFont = wb.createFont();
		//标题的样式
		titleFont.setFontHeightInPoints((short)10);
		titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.BORDER_DASHED);
		style.setFont(titleFont);
		styles.put("title", style);
		//表头的样式
		Font monthFont = wb.createFont();
		monthFont.setFontHeightInPoints((short)10);
		monthFont.setColor(IndexedColors.BLACK.getIndex());
		monthFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中
		style.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setRightBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setTopBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		style.setFont(monthFont);
		style.setWrapText(true);//设置自动换行
		styles.put("header", style);
		
		//表格内容的样式
		style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setRightBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setTopBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		style.setWrapText(true);
		styles.put("cell", style); 
		return styles;
	}

}
