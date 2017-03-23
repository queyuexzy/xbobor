/*  
 * @(#) ProgressBean.java Create on 2013-8-6 下午02:20:45   
 *   
 * Copyright 2013 by xl.   
 */


package cn.system.basic.global.bean;

/**
 * 
 * @author xieyan
 * @date   2013-8-6
 */
public class ProgressBean {

	private int startFlag = 0;//0-未执行状态  1-正在执行  2-执行完成  -1-抛出异常
	private String progressInfo = "";
	private int percent = 0;
	public int getStartFlag() {
		return startFlag;
	}
	public void setStartFlag(int startFlag) {
		this.startFlag = startFlag;
	}
	public String getProgressInfo() {
		return progressInfo;
	}
	public void setProgressInfo(String progressInfo) {
		this.progressInfo = progressInfo;
	}
	public int getPercent() {
		return percent;
	}
	public void setPercent(int percent) {
		this.percent = percent;
	}
	@Override
	public String toString() {
		return "[startFlag:"+startFlag+",progressInfo:"+progressInfo+",percent:"+percent+"]";
	}
}
