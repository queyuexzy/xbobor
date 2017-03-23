package cn.system.basic.global.bean;

import java.io.Serializable;

/**
 * 
 * @author zhl
 * @date 20112011-8-25下午08:35:10
 * 
 */
public abstract class BaseBean implements Serializable {
	private static final long serialVersionUID = -1183417179997821201L;
	
	private int _pageNow;// 当前页数
	private int _pageSize;// 每页显示记录
	private int _pageTotal;// 总页数
	private long _sumCount;// 总数
	private String _sortName; //排序字段
	private String _sortOrder;//排序方式
	
	public int get_pageNow() {
		return _pageNow;
	}

	public void set_pageNow(int now) {
		_pageNow = now;
	}

	public int get_pageSize() {
		return _pageSize;
	}

	public void set_pageSize(int size) {
		_pageSize = size;
	}

	public int get_pageTotal() {
		if (_pageSize != 0) {
			_pageTotal = (int) ((_sumCount + _pageSize - 1) / _pageSize);
		}
		return _pageTotal;
	}

	public void set_pageTotal(int total) {
		_pageTotal = total;
	}

	public void set_sumCount(long _sumCount) {
		this._sumCount = _sumCount;
	}

	public long get_sumCount() {
		return _sumCount;
	}

	public String get_sortOrder() {
		return _sortOrder;
	}

	public void set_sortOrder(String _sortOrder) {
		this._sortOrder = _sortOrder;
	}

	public String get_sortName() {
		return _sortName;
	}

	public void set_sortName(String _sortName) {
		this._sortName = _sortName;
	}
}
