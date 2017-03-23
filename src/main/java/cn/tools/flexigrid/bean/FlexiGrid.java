package cn.tools.flexigrid.bean;

import java.lang.reflect.Field;
import java.util.List;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import cn.system.basic.manage.cache.UserBeanCache;
import cn.tools.StringHelper;

/**
 * flexigrid的一些参数
 * 
 * @author zhanghongliang
 * @date 2012-2-6 上午9:28:41
 */
public class FlexiGrid {

	/** The key to get FlexiGrid's request form data. */
	public static final String KEY_QUERY_JSON = "query_json";

	/** 当前第几页 */
	private Integer page;
	/** 每页大小 */
	private Integer pagesize;
	/** 排序的列名 */
	private String sortname;
	/** 升序或者降序; desc;asc */
	private String sortorder;
	/** 附件的查询条件 */
	private List<Addparams> addparams;
	/** 总数多少 */
	private Long total;
	/** 查询出来的数据 */
	private List<?> rows;
	/** Additional data */
	private Object data;

	@Override
	public String toString() {
		// MULTI_LINE_STYLE
		return ReflectionToStringBuilder.toString(this,
				ToStringStyle.SHORT_PREFIX_STYLE, true, true);
	}

	/**
	 * @return 返回给页面需要用的
	 */
	public Integer getPage() {
		return page;
	}

	/**
	 * @param page
	 * 返回给页面需要用的
	 */
	public void setPage(Integer page) {
		this.page = page != null && page > 1 ? page : 1;
	}

	/**
	 * @return 每页大小
	 */
	public Integer getPagesize() {
		return pagesize;
	}

	/**
	 * @param pagesize
	 * 每页大小
	 */
	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}

	/**
	 * @return 排序的列名
	 */
	public String getSortname() {
		return sortname;
	}

	/**
	 * @param sortname
	 * 排序的列名
	 */
	public void setSortname(String sortname) {
		this.sortname = sortname;
	}

	/**
	 * @return 升序或者降序; desc;asc
	 */
	public String getSortorder() {
		return sortorder;
	}

	/**
	 * @param sortorder
	 * 升序或者降序; desc;asc
	 */
	public void setSortorder(String sortorder) {
		this.sortorder = sortorder;
	}

	/**
	 * @return 附件的查询条件
	 */
	public List<Addparams> getAddparams() {
		return addparams;
	}

	/**
	 * @param addparams
	 * 附件的查询条件
	 */
	public void setAddparams(List<Addparams> addparams) {
		this.addparams = addparams;
	}

	/**
	 * @return 总数多少
	 */
	public Long getTotal() {
		return total;
	}

	/**
	 * @param total
	 * 总数多少
	 */
	public void setTotal(Long total) {
		this.total = total;
	}

	/**
	 * @return 查询出来的数据
	 */
	public List<?> getRows() {
		return rows;
	}

	/**
	 * @param rows
	 * 查询出来的数据
	 */
	public void setRows(List<?> rows) {
		UserBeanCache.setUserName(rows);
		for(Object obj : rows){
			Class<?> clz = obj.getClass();
			Field[] fields = clz.getDeclaredFields();
			if(fields == null || fields.length <= 0){
				continue;
			}
			for(Field field : clz.getDeclaredFields()){
				field.setAccessible(true);
				if(!field.getType().getSimpleName().equals("String")){
					continue;
				}
				try {
					Object value = field.get(obj);
					if(value == null){
						continue;
					}
					String stringValue = value.toString();
					String filterValue = StringHelper.stringFilter(stringValue);
					if(stringValue.equals(filterValue)){
						continue;
					}
					field.set(obj, filterValue);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		this.rows = rows;
	}

	/**
	 * Adjust the page now according to the total.
	 * 
	 * @param total
	 * The total result count.
	 */
	public void adjust(long total) {
		if (pagesize == null || pagesize <= 0) {
			pagesize = 15;
		}

		if (page == null || page < 1) {
			page = 1;
		} else if ((page - 1) * pagesize >= total) {
			page = (int) ((total - 1) / pagesize + 1);
		}
		setTotal(total);
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
