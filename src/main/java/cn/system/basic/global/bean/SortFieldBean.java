package cn.system.basic.global.bean;

public class SortFieldBean {
	public final static int NumType = 0;
	public final static int StringType = 1;

	public final static String GT = ">=";
	public final static String G = ">=";
	public final static String LT = "<=";
	public final static String L = "<";
	public final static String EQ = "=";
	public final static int sortMaxNum = 999;

	private String fieldName;
	private String fieldVale;
	private int filedType = -1;// NumType || StringType
	private String filedOpt; // GT || G || LT || L ||EQ

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldVale() {
		return fieldVale;
	}

	public void setFieldVale(String fieldVale) {
		this.fieldVale = fieldVale;
	}

	public static int getNumType() {
		return NumType;
	}

	public static int getStringType() {
		return StringType;
	}

	public String getFiledOpt() {
		return filedOpt;
	}

	public void setFiledOpt(String filedOpt) {
		this.filedOpt = filedOpt;
	}

	public String getFieldSQL() {
		StringBuilder sb = new StringBuilder();
		if (fieldName != null && fieldName.trim().length() > 0
				&& filedType >= 0 && fieldVale != null
				&& fieldVale.trim().length() > 0 && filedOpt != null
				&& filedOpt.trim().length() > 0) {
			sb.append(" and " + fieldName + filedOpt);
			if (filedType == StringType) {
				sb.append("'" + fieldVale + "'");
			} else {
				sb.append(fieldVale);
			}
		}
		return sb.toString();
	}

	public int getFiledType() {
		return filedType;
	}

	public void setFiledType(int filedType) {
		this.filedType = filedType;
	}
}
