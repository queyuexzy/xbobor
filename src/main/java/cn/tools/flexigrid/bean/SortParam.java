package cn.tools.flexigrid.bean;

/**
 * Represent a pair of sort rule.
 * 
 * @author Luo Yinzhuo
 */
public class SortParam {
	/** The sort name. */
	private String name;
	/** The sort order. */
	private String order;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
}
