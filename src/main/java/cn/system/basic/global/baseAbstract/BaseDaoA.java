package cn.system.basic.global.baseAbstract;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import cn.system.basic.global.BaseDao;
import cn.system.basic.global.bean.BaseBean;
import cn.system.basic.global.bean.SortBean;
import cn.system.basic.global.bean.SortFieldBean;
import cn.system.basic.global.bean.TypeSelectBean;
import cn.system.basic.global.tools.SqlHelper;

/**
 * 基础dao
 * 
 * @author zhl
 * @date 20112011-8-26下午01:48:57
 * 
 * @param <T>
 */
public abstract class BaseDaoA<T> implements BaseDao<T> {
	/** The order desc key word. */
	protected static final String ORDER_DESC = "desc";
	/** The order asc key word. */
	protected static final String ORDER_ASC = "asc";

	protected Class<T> clazz;

	@SuppressWarnings("unchecked")
	public BaseDaoA() {
		clazz = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/**
	 * 更新bean中有值属性
	 * 
	 * @author zhl
	 * @date 20112011-8-25下午08:44:49
	 * 
	 * @param oprBean
	 * @return
	 * @throws Exception
	 */
	@Override
	public int updateBeanByPrimary(Map<String, Object> oprMap,
			String tableName, String primaryName) throws Exception {
		String updateSql = SqlHelper.getUpdateSql(oprMap.keySet(), tableName,
				primaryName);
		return this.getNamedJdbc().update(updateSql, oprMap);
	}

	/**
	 * 新增
	 * 
	 * @author zhl
	 * @date 20112011-8-25下午08:44:49
	 * 
	 * @param oprBean
	 * @return
	 * @throws Exception
	 */
	@Override
	public long insertBean(Map<String, Object> oprMap, String tableName,
			String primaryName) throws Exception {
		String insertSql = SqlHelper
				.getInserSql(oprMap, tableName, primaryName);
		/*
		 * int oprCount = this.getNamedJdbc().update(insertSql, oprMap); long
		 * lastInsertId = this.getlastInsertId();
		 */
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.getNamedJdbc().update(insertSql,
				(new MapSqlParameterSource(oprMap)), keyHolder);
		long lastInsertId = keyHolder.getKey().longValue();
		return lastInsertId;
	}

	/**
	 * 删除一个bean
	 * 
	 * @author zhl
	 * @date 20112011-8-25下午08:56:58
	 * 
	 * @param tableName
	 * @param primary
	 * @param keyVaule
	 * @return
	 * @throws Exception
	 */
	@Override
	public int deleteBean(String tableName, String primaryName,
			Object primaryValue) throws Exception {
		String sql = "DELETE FROM " + tableName + " WHERE " + primaryName
				+ "=?";
		return this.getNamedJdbc().getJdbcOperations()
				.update(sql, new Object[] { primaryValue });
	}

	/**
	 * 删除一个bean
	 * 
	 * @author zhl
	 * @date 20112011-8-25下午08:56:58
	 * 
	 * @param tableName
	 * @param primary
	 * @param keyVaule
	 * @return
	 * @throws Exception
	 */
	@Override
	public int batchdeleteBean(String tableName, String primaryName,
			final Collection<String> primaryValues) throws Exception {
		int result = 0;
		final String sql = "DELETE FROM " + tableName + " WHERE " + primaryName
				+ "=?";

		result = (Integer) this.getNamedJdbc().getJdbcOperations()
				.execute(sql, new PreparedStatementCallback() {
					@Override
					public Object doInPreparedStatement(PreparedStatement ps)
							throws SQLException, DataAccessException {
						Iterator<String> ids = primaryValues.iterator();
						int count = 0;
						while (ids.hasNext()) {
							ps.setObject(1, ids.next());
							ps.addBatch();
							count++;
							if (count % 100000 == 0) {
								ps.executeBatch();
							}
						}
						ps.executeBatch();
						return count;
					}

				});
		return result;
	}

	/**
	 * 取一个bean根据主键
	 * 
	 * @author zhl
	 * @date 20112011-8-25下午09:01:43
	 * 
	 * @param tableName
	 * @param primaryName
	 * @param primaryValue
	 * @return
	 * @throws Exception
	 */
	@Override
	public BaseBean findByPrimary(String tableName, String primaryName,
			Object primaryValue) throws Exception {
		BaseBean result = null;
		String sql = "SELECT * FROM " + tableName + " WHERE " + primaryName
				+ "=?";
		try {
			result = (BaseBean) this
					.getNamedJdbc()
					.getJdbcOperations()
					.queryForObject(sql, new Object[] { primaryValue },
							new BeanPropertyRowMapper(clazz));
		} catch (EmptyResultDataAccessException e) {
		}
		return result;
	}

	/**
	 * 唯一检查
	 * 
	 * @author zhl
	 * @date 20112011-8-26下午02:03:39
	 * 
	 * @param tableName
	 * @param fieldName
	 * @param primaryName
	 * @param conditon
	 * @return
	 * @throws Exception
	 */
	@Override
	public int onlyCheck(String tableName, String fieldName,
			String primaryName, Map<String, Object> conditon) throws Exception {
		String sql = "SELECT COUNT(*) FROM " + tableName + " WHERE "
				+ fieldName + "=:" + fieldName + " AND " + primaryName + "!=:"
				+ primaryName;
		return this.getNamedJdbc().queryForInt(sql, conditon);
	}

	/**
	 * 更改排序
	 * 
	 * @author zhl
	 * @date 2011-8-31下午02:32:40
	 * 
	 * @param sortBean
	 * @return
	 * @throws Exception
	 */
	@Override
	public int updateSortBean(SortBean sortBean) throws Exception {
		StringBuffer sbSQL = new StringBuffer();
		sbSQL.append(" UPDATE ");
		String sortName = sortBean.getSortName();
		String sortTable = sortBean.getSortTable();
		int newSort = sortBean.getNewSort();
		long pkId = sortBean.getPkId();
		sbSQL.append(sortTable);
		sbSQL.append(" set " + sortName + "=" + newSort);
		sbSQL.append(" where id= " + pkId);
		return this.getNamedJdbc().getJdbcOperations().update(sbSQL.toString());
	}

	@Override
	public int updateSortList(SortBean sortBean) throws Exception {
		int newSort = sortBean.getNewSort();
		int oldSort = sortBean.getOldSort();
		int maxSort = SortFieldBean.sortMaxNum;
		String sortTable = sortBean.getSortTable();
		String sortName = sortBean.getSortName();
		StringBuffer sbSQL = new StringBuffer();
		if (newSort < oldSort) {
			sbSQL.append(" UPDATE ");
			sbSQL.append(sortTable);
			sbSQL.append(" set " + sortName + "=" + sortName + "+1");
			sbSQL.append(" WHERE 1=1");
			sbSQL.append(" and (" + sortName + " between ");
			sbSQL.append(newSort);
			sbSQL.append(" and ");
			sbSQL.append(oldSort - 1);
			sbSQL.append(")");
			sbSQL.append("and " + sortName + "<" + maxSort);
		} else if (newSort > oldSort) {
			sbSQL.append(" UPDATE ");
			sbSQL.append(sortTable);
			sbSQL.append(" set " + sortName + "=" + sortName + "-1");
			sbSQL.append(" WHERE 1=1");
			sbSQL.append(" and (" + sortName + " between ");
			sbSQL.append(oldSort + 1);
			sbSQL.append(" and ");
			sbSQL.append(newSort);
			sbSQL.append(")");
			sbSQL.append("and " + sortName + "<" + maxSort);
		} else if (newSort == oldSort) {
			return 0;
		}
		List<SortFieldBean> sortFieldBeanList = sortBean.getSortFieldBeanList();
		if (sortFieldBeanList != null) {
			for (SortFieldBean sortFieldBean : sortFieldBeanList) {
				if (sortFieldBean != null) {
					sbSQL.append(sortFieldBean.getFieldSQL());
				}
			}
		}
		return this.getNamedJdbc().getJdbcOperations().update(sbSQL.toString());
	}

	/**
	 * 取排序的下来选项
	 * 
	 * @author zhl
	 * @date 2011-8-31下午02:39:20
	 * 
	 * @param sortBean
	 * @return
	 * @throws Exception
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<TypeSelectBean> getSortTypeSelectList(SortBean sortBean)
			throws Exception {

		List<TypeSelectBean> typeBeanList = new ArrayList<TypeSelectBean>();
		StringBuffer sbSQL = new StringBuffer();
		int maxSort = SortFieldBean.sortMaxNum;
		String sortTable = sortBean.getSortTable();
		String sortName = sortBean.getSortName();
		sbSQL.append("select " + sortName + " typeId ," + sortName
				+ " typeName from " + sortTable + " where " + sortName + "<"
				+ maxSort + " and " + sortName + ">0");
		List<SortFieldBean> sortFieldBeanList = sortBean.getSortFieldBeanList();
		if (sortFieldBeanList != null) {
			for (SortFieldBean sortFieldBean : sortFieldBeanList) {
				if (sortFieldBean != null) {
					sbSQL.append(sortFieldBean.getFieldSQL());
				}
			}
		}
		sbSQL.append(" order by " + sortName);
		typeBeanList = this
				.getNamedJdbc()
				.getJdbcOperations()
				.query(sbSQL.toString(),
						new BeanPropertyRowMapper(TypeSelectBean.class));
		return typeBeanList;

	}

	/**
	 * 重置排序
	 * 
	 * @author zhl
	 * @date 2011-9-27下午3:22:55
	 * 
	 * @param sortBean
	 * @return
	 * @throws Exception
	 */
	@Override
	@SuppressWarnings("unchecked")
	public int resetSort(SortBean sortBean) throws Exception {
		StringBuffer querySql = new StringBuffer();
		String sortTable = sortBean.getSortTable();
		String sortName = sortBean.getSortName();
		List<SortFieldBean> sortFieldBeanList = sortBean.getSortFieldBeanList();

		querySql.append("SELECT id FROM " + sortTable + " WHERE 1=1 ");
		if (sortFieldBeanList != null && sortFieldBeanList.size() > 0) {
			for (SortFieldBean sortFieldBean : sortFieldBeanList) {
				querySql.append(sortFieldBean.getFieldSQL());
			}
		}
		querySql.append(" ORDER BY id asc");
		List<Integer> ids = null;
		try {
			ids = this.getNamedJdbc().getJdbcOperations()
					.query(querySql.toString(), new RowMapper() {
						@Override
						public Object mapRow(ResultSet arg0, int arg1)
								throws SQLException {
							return arg0.getInt(1);
						}
					});
		} catch (EmptyResultDataAccessException e) {
		}

		if (ids != null && ids.size() > 0) {
			int length = ids.size();
			String updateSql = "UPDATE " + sortTable + " SET " + sortName
					+ "=? WHERE id=?";
			for (int i = 0; i < length; i++) {
				this.getNamedJdbc()
						.getJdbcOperations()
						.update(updateSql.toString(),
								new Object[] { i + 1, ids.get(i) });
			}
		}
		return 1;
	}

	/**
	 * 取新增的bean id
	 * 
	 * @author zhl
	 * @date 20112011-8-25下午10:50:38
	 * 
	 * @return
	 */
	public long getlastInsertId() {
		String sql = "select LAST_INSERT_ID()";
		return this.getNamedJdbc().getJdbcOperations().queryForInt(sql);
	}

	/**
	 * 取不通模块的jdbc
	 * 
	 * @author zhl
	 * @date 20112011-8-25下午10:42:05
	 * 
	 * @return
	 */
	abstract protected NamedParameterJdbcTemplate getNamedJdbc();

	/**
	 * Insert a bean into the table and only the specified columns value would
	 * be inserted.
	 * 
	 * @param bean
	 * The bean.
	 * @param tableName
	 * The table name.
	 * @param columns
	 * The specified columns, separated by ",".
	 * @return The effected rows.
	 */
	protected long insertBean(T bean, String tableName, String columns) {
		if (bean == null || tableName == null || tableName.trim().length() == 0
				|| columns == null || columns.trim().length() == 0) {
			return 0;
		}

		final Field[] fields = bean.getClass().getDeclaredFields();
		final Map<String, Field> fieldMap = new HashMap<String, Field>();
		for (int i = 0; i < fields.length; i++) {
			fieldMap.put(fields[i].getName(), fields[i]);
		}

		final String[] columnArray = columns.trim().split(",");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		for (int i = 0; i < columnArray.length; i++) {
			final String column = columnArray[i].trim();
			addBeanObjectToParamMapByFieldName(bean, fieldMap, column, paramMap);
		}

		if (paramMap.isEmpty()) {
			return 0;
		} else {
			StringBuffer sqlColumns = new StringBuffer();
			StringBuffer sqlValues = new StringBuffer();

			for (String column : paramMap.keySet()) {
				if (sqlColumns.length() > 0) {
					sqlColumns.append(",");
				}
				sqlColumns.append(column);

				if (sqlValues.length() > 0) {
					sqlValues.append(",");
				}
				sqlValues.append(":").append(column);
			}

			final String sqlInsert = String.format(
					"insert into %s (%s) values (%s)", tableName,
					sqlColumns.toString(), sqlValues.toString());
			KeyHolder keyHolder = new GeneratedKeyHolder();
			if (getNamedJdbc().update(sqlInsert,
					(new MapSqlParameterSource(paramMap)), keyHolder) > 0) {
				if(null!=keyHolder.getKey()){
					return keyHolder.getKey().longValue();
				}
				return 1;
			} else {
				return 0L;
			}
		}
	}

	/**
	 * Insert a bean into the table and only the specified columns value would
	 * be inserted.
	 * 
	 * @param bean
	 * The bean.
	 * @param tableName
	 * The table name.
	 * @param columns
	 * The specified columns, separated by ",".
	 * @param where
	 * The where condition.
	 * @return The effected rows.
	 */
	protected long insertBean(T bean, String tableName, String columns,
			String sqlWhere) {
		if (bean == null || tableName == null || tableName.trim().length() == 0
				|| columns == null || columns.trim().length() == 0) {
			return 0;
		}

		final Field[] fields = bean.getClass().getDeclaredFields();
		final Map<String, Field> fieldMap = new HashMap<String, Field>();
		for (int i = 0; i < fields.length; i++) {
			fieldMap.put(fields[i].getName(), fields[i]);
		}

		final String[] columnArray = columns.trim().split(",");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		for (int i = 0; i < columnArray.length; i++) {
			final String column = columnArray[i].trim();
			addBeanObjectToParamMapByFieldName(bean, fieldMap, column, paramMap);
		}

		if (paramMap.isEmpty()) {
			return 0;
		} else {
			StringBuffer sqlColumns = new StringBuffer();
			StringBuffer sqlValues = new StringBuffer();

			for (String column : paramMap.keySet()) {
				if (sqlColumns.length() > 0) {
					sqlColumns.append(",");
				}
				sqlColumns.append(column);

				if (sqlValues.length() > 0) {
					sqlValues.append(",");
				}
				sqlValues.append(":").append(column);
			}

			final String sqlInsert = String.format(
					"insert into %s (%s) select %s from dual %s", tableName,
					sqlColumns.toString(), sqlValues.toString(), sqlWhere);
			final KeyHolder keyHolder = new GeneratedKeyHolder();
			if (getNamedJdbc().update(sqlInsert,
					(new MapSqlParameterSource(paramMap)), keyHolder) > 0) {
				if(null!=keyHolder.getKey()){
					return keyHolder.getKey().longValue();
				}
				return 1;
			} else {
				return 0L;
			}
		}
	}

	/** The update condition not match. */
	public static final int ERROR_UPDATE_CONDITION_NOT_MATCH = -1;
	/** The operation against result unique restrict. */
	public static final int ERROR_AGAINST_RESULT_UNIQUE_RESTRICT = -2;
	/** The operation missing parent node. */
	public static final int ERROR_MISSING_PARENT_NODE = -3;
	/** The operation has child node. */
	public static final int ERROR_HAS_CHILD_NODE = -4;

	/**
	 * Update a bean in the specified table under the specified conditions and
	 * only the specified columns to be update.
	 * 
	 * @param bean
	 * The bean.
	 * @param tableName
	 * The table name.
	 * @param updateColumns
	 * The columns need to be update.
	 * @param conditionColumns
	 * The condition columns.
	 * @return The effected rows.
	 */
	protected int updateBean(T bean, String tableName, String updateColumns,
			String conditionColumns) {
		if (bean == null || tableName == null || tableName.trim().length() == 0
				|| updateColumns == null || updateColumns.trim().length() == 0) {
			return 0;
		}

		final Field[] fields = bean.getClass().getDeclaredFields();
		final Map<String, Field> fieldMap = new HashMap<String, Field>();
		for (int i = 0; i < fields.length; i++) {
			fieldMap.put(fields[i].getName(), fields[i]);
		}

		final Map<String, Object> paramMap = new HashMap<String, Object>();
		// Check whether all conditions exist in the bean.
		final StringBuffer sqlWhere = new StringBuffer();
		if (conditionColumns != null && conditionColumns.trim().length() > 0) {
			final String[] conditionColumnArray = conditionColumns.split(",");
			for (int i = 0; i < conditionColumnArray.length; i++) {
				final String column = conditionColumnArray[i].trim();
				if (addBeanObjectToParamMapByFieldName(bean, fieldMap, column,
						paramMap)) {
					sqlWhere.append(" and ").append(column).append("=:")
							.append(column);
				} else {
					return ERROR_UPDATE_CONDITION_NOT_MATCH;
				}
			}
		}

		// Dump need update column object into the paramMap
		final StringBuffer sqlSet = new StringBuffer();
		final String[] updateColumnArray = updateColumns.split(",");
		for (int i = 0; i < updateColumnArray.length; i++) {
			final String column = updateColumnArray[i].trim();
			if (addBeanObjectToParamMapByFieldName(bean, fieldMap, column,
					paramMap)) {
				if (sqlSet.length() > 0) {
					sqlSet.append(",");
				}
				sqlSet.append(String.format("`%s`", column)).append("=:")
						.append(column);
			}
		}

		if (sqlSet.length() == 0) {
			return 0;
		} else {
			final String sqlUpdate = String.format(
					"update %s set %s where 1 = 1 %s", tableName,
					sqlSet.toString(), sqlWhere.toString());
			return getNamedJdbc().update(sqlUpdate, paramMap);
		}
	}
	
	/**
	 * Update a bean in the specified table under the specified conditions and
	 * only the specified columns to be update.
	 * 
	 * @param bean
	 * The bean.
	 * @param tableName
	 * The table name.
	 * @param updateColumns
	 * The columns need to be update.
	 * @param conditionColumns
	 * The condition columns.
	 * @return The effected rows.
	 */
	protected int updateBean(T bean, String tableName, String updateColumns,
			String conditionColumns, String sqlWhere) {
		if (bean == null || tableName == null || tableName.trim().length() == 0
				|| updateColumns == null || updateColumns.trim().length() == 0) {
			return 0;
		}

		final Field[] fields = bean.getClass().getDeclaredFields();
		final Map<String, Field> fieldMap = new HashMap<String, Field>();
		for (int i = 0; i < fields.length; i++) {
			fieldMap.put(fields[i].getName(), fields[i]);
		}

		final Map<String, Object> paramMap = new HashMap<String, Object>();
		// Check whether all conditions exist in the bean.
		final StringBuffer sqlCondition = new StringBuffer(sqlWhere);
		if (conditionColumns != null && conditionColumns.trim().length() > 0) {
			final String[] conditionColumnArray = conditionColumns.split(",");
			for (int i = 0; i < conditionColumnArray.length; i++) {
				final String column = conditionColumnArray[i].trim();
				if (addBeanObjectToParamMapByFieldName(bean, fieldMap, column,
						paramMap)) {
					sqlCondition.append(" and ").append(column).append("=:")
							.append(column);
				} else {
					return ERROR_UPDATE_CONDITION_NOT_MATCH;
				}
			}
		}

		// Dump need update column object into the paramMap
		final StringBuffer sqlSet = new StringBuffer();
		final String[] updateColumnArray = updateColumns.split(",");
		for (int i = 0; i < updateColumnArray.length; i++) {
			final String column = updateColumnArray[i].trim();
			if (addBeanObjectToParamMapByFieldName(bean, fieldMap, column,
					paramMap)) {
				if (sqlSet.length() > 0) {
					sqlSet.append(",");
				}
				sqlSet.append(String.format("`%s`", column)).append("=:")
						.append(column);
			}
		}

		if (sqlSet.length() == 0) {
			return 0;
		} else {
			final String sqlUpdate = String.format(
					"update %s set %s %s", tableName,
					sqlSet.toString(), sqlCondition.toString());
			return getNamedJdbc().update(sqlUpdate, paramMap);
		}
	}


	/**
	 * Add bean object to parameter map by field name.
	 * 
	 * @param bean
	 * The bean.
	 * @param fieldMap
	 * The bean's field map.
	 * @param column
	 * The column name.
	 * @param paramMap
	 * The parameter map.
	 * @return True if the field exist and put into the parameter map
	 * successfully, otherwise false.
	 */
	private boolean addBeanObjectToParamMapByFieldName(T bean,
			Map<String, Field> fieldMap, String column,
			Map<String, Object> paramMap) {
		final Field field = fieldMap.get(column);

		if (field != null) {
			try {
				final StringBuffer fieldMethodName = new StringBuffer();
				if (field.getType().equals(boolean.class)
						|| field.getType().equals(Boolean.class)) {
					fieldMethodName.append("is");
				} else {
					fieldMethodName.append("get");
				}
				fieldMethodName.append(Character.toUpperCase(column.charAt(0)))
						.append(column.substring(1));

				Method method = bean.getClass().getMethod(
						fieldMethodName.toString());
				if (method != null) {
					paramMap.put(column, method.invoke(bean));
					return true;
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
