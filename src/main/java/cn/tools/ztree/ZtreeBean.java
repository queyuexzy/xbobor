/*  
 * @(#) ZtreeBean.java Create on 2011-12-15 下午4:03:32
 *
 * Copyright 2011 by xl.   
 */
package cn.tools.ztree;

import java.util.List;

public class ZtreeBean {
	private String id;
	private String pId;
	private String name;
	private String icon;
	private boolean isParent;
	private List<ZtreeBean> nodes;//结点
	private boolean open;
	private boolean nocheck;//是否被选中
	private boolean checked;
	private String clickPath;

	public boolean isNocheck() {
		return nocheck;
	}

	public void setNocheck(boolean nocheck) {
		this.nocheck = nocheck;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(boolean isParent) {
		this.isParent = isParent;
	}

	public List<ZtreeBean> getNodes() {
		return nodes;
	}

	public void setNodes(List<ZtreeBean> nodes) {
		this.nodes = nodes;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getClickPath() {
		return clickPath;
	}

	public void setClickPath(String clickPath) {
		this.clickPath = clickPath;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
}
