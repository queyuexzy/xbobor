/*
 * @(#) FlexigridFilter.java Create on 2012-2-6 上午9:45:08
 *
 * Copyright 2012 by xl.
 */
package cn.tools.flexigrid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import cn.tools.flexigrid.bean.Addparams;
import cn.tools.flexigrid.bean.Constants;
import cn.tools.flexigrid.bean.FlexiGrid;
import cn.tools.flexigrid.bean.FlexiGridCondition;
import cn.tools.jackjson.JackJson;

/**
 * 
 * @author zhanghongliang
 * @date 2012-2-6
 */
public class FlexigridFilter {
	public static FlexiGridCondition dealFlexigridBean(FlexiGrid flexGrid) {
		if (flexGrid != null) {
			FlexiGridCondition result = new FlexiGridCondition();
			List<Addparams> items = flexGrid.getAddparams();
			if (items != null && items.size() > 0) {
				StringBuffer conditionSql = new StringBuffer();
				List<Object> params = new ArrayList<Object>();
				for (Addparams addparams : items) {
					String name = addparams.getName();
					Object value = addparams.getValue();
					String logic = addparams.getLogic();
					// 把条件添加到result中
					if (StringUtils.isNotBlank(name) && value != null) {
						conditionSql.append(" AND " + name.trim());
						if (StringUtils.isBlank(logic)) {
							conditionSql.append("=?");
							params.add(value);
						} else if (Constants.EQUAL.equals(logic)) {
							conditionSql.append(Constants.EQUAL_S + " ?");
							params.add(value);
						} else if (Constants.NOT_EQUAL.equals(logic)) {
							conditionSql.append(Constants.NOT_EQUAL_S + " ?");
							params.add(value);
						} else if (Constants.LESS_EQUAL.equals(logic)) {
							conditionSql.append(Constants.LESS_EQUAL_S + " ?");
							params.add(value);
						} else if (Constants.GREAT_EQUAL.equals(logic)) {
							conditionSql.append(Constants.GREAT_EQUAL_S + " ?");
							params.add(value);
						} else if (Constants.LESS.equals(logic)) {
							conditionSql.append(Constants.LESS_S + " ?");
							params.add(value);
						} else if (Constants.GREAT.equals(logic)) {
							conditionSql.append(Constants.GREAT_S + " ?");
							params.add(value);
						} else if (Constants.LIKE.equals(logic)) {
							conditionSql.append(Constants.LIKE_S + " ?");
							params.add(Constants.PERCENT_SIGN + value.toString().trim() + Constants.PERCENT_SIGN);
						}
					}
				}
				result.setCondtion(conditionSql.toString());
				result.setParams(params);
				return result;
			}
		}
		return null;
	}

	public static Map<String, Object> parseQuery(String query) {
		// TODO Auto-generated method stub
		if (StringUtils.isNotBlank(query)) {
			FlexiGrid flexiGrid = JackJson.fromJsonToObject(query, FlexiGrid.class);
			if (flexiGrid != null) {
				Map<String, Object> params = new HashMap<String, Object>();
				List<Addparams> addparams = flexiGrid.getAddparams();
				if (addparams != null && addparams.size() > 0) {
					for (Addparams tmpAdd : addparams) {
						if (StringUtils.isNotBlank(tmpAdd.getName()) && tmpAdd.getValue() != null && StringUtils.isNotBlank(tmpAdd.getValue().toString())) {
							params.put(tmpAdd.getName().trim(), tmpAdd.getValue());
						}
					}
				}

				params.put("pageNow", flexiGrid.getPage());
				params.put("pageSize", flexiGrid.getPagesize());

				if (StringUtils.isNotBlank(flexiGrid.getSortname()) && StringUtils.isNotBlank(flexiGrid.getSortorder())) {
					params.put("_order_by", flexiGrid.getSortname() + " " + flexiGrid.getSortorder());
				}

				return params;
			}
		}
		return new HashMap<String, Object>();
	}

	public static Map<String, Object> parseFlexigrid(FlexiGrid flexiGrid) {
		Map<String, Object> params = new HashMap<String, Object>();
		List<Addparams> addparams = flexiGrid.getAddparams();
		if (addparams != null && addparams.size() > 0) {
			for (Addparams tmpAdd : addparams) {
				if (StringUtils.isNotBlank(tmpAdd.getName()) && tmpAdd.getValue() != null && StringUtils.isNotBlank(tmpAdd.getValue().toString())) {
					params.put(tmpAdd.getName().trim(), tmpAdd.getValue());
				}
			}
		}

		params.put("pageNow", flexiGrid.getPage());
		params.put("pageSize", flexiGrid.getPagesize());

		if (StringUtils.isNotBlank(flexiGrid.getSortname()) && StringUtils.isNotBlank(flexiGrid.getSortorder())) {
			params.put("_order_by", flexiGrid.getSortname() + " " + flexiGrid.getSortorder());
		}

		return params;
	}
}
