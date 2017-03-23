package cn.system.basic.global.bean;

import java.util.List;

public class SortBean {
	private int newSort;
	private int oldSort;
	private String sortName;
	private String sortTable;
	
	private List<SortFieldBean> sortFieldBeanList = null;
	private long pkId;
	
	public int getNewSort() {
		return newSort;
	}
	public void setNewSort(int newSort) {
		this.newSort = newSort;
	}
	public int getOldSort() {
		return oldSort;
	}
	public void setOldSort(int oldSort) {
		this.oldSort = oldSort;
	}
	public String getSortName() {
		return sortName;
	}
	public void setSortName(String sortName) {
		this.sortName = sortName;
	}
	public String getSortTable() {
		return sortTable;
	}
	public void setSortTable(String sortTable) {
		this.sortTable = sortTable;
	}
	public long getPkId() {
		return pkId;
	}
	public void setPkId(long pkId) {
		this.pkId = pkId;
	}
	public List<SortFieldBean> getSortFieldBeanList() {
		return sortFieldBeanList;
	}
	public void setSortFieldBeanList(List<SortFieldBean> sortFieldBeanList) {
		this.sortFieldBeanList = sortFieldBeanList;
	}
}
