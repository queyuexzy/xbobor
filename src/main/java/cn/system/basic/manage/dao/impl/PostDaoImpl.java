package cn.system.basic.manage.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import cn.system.basic.manage.bean.PostBean;
import cn.system.basic.manage.dao.PostDao;
import cn.system.basic.manage.moduleInfo.ModuleDaoA;
import cn.tools.flexigrid.bean.Addparams;
import cn.tools.flexigrid.bean.FlexiGrid;

/**
 * Data access object to manage table post_info.
 * 
 * @author Luo Yinzhuo
 */
@Repository
public class PostDaoImpl extends ModuleDaoA<PostBean> implements PostDao {
	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.system.basic.manage.dao.PostDao#getPostBeanList()
	 */
	@Override
	public List<PostBean> getPostBeanList() {
		final String sql = "select * from post_info";
		return systemJdbcTemplate.query(sql,
				new BeanPropertyRowMapper<PostBean>(PostBean.class));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.system.basic.manage.dao.PostDao#getPostBeanList(cn.tools.flexigrid
	 * .bean.FlexiGrid)
	 */
	@Override
	public FlexiGrid getPostBeanList(FlexiGrid flexiGrid) {
		StringBuffer sqlWhere = new StringBuffer();
		Map<String, Object> paramMap = new HashMap<String, Object>();

		// First, extract the flexiGrid condition.
		List<Addparams> params = flexiGrid.getAddparams();
		if (params != null) {
			for (Addparams param : params) {
				if (KEY_POST_NAME.equals(param.getName())
						&& param.getValue() != null) {
					sqlWhere.append(" and a.postName like :postName");
					paramMap.put(KEY_POST_NAME,
							String.format("%%%s%%", param.getValue()));
				}
			}
		}

		// Second, check the total count under the condition.
		StringBuffer sqlCount = new StringBuffer(
				"select count(*) from post_info a where 1=1 ");
		sqlCount.append(sqlWhere.toString());
		final int total = systemNameJdbcTemplate.queryForObject(
				sqlCount.toString(), paramMap, Integer.class);
		flexiGrid.adjust(total);

		// Third, get the list under the condition.
		if (total > 0) {
			StringBuffer sqlSelect = new StringBuffer(
					"select * from post_info a where 1 = 1");
			sqlSelect.append(sqlWhere.toString());

			final String sortName = flexiGrid.getSortname();
			if (ORDER_CREATE_TIME.equals(sortName)
					|| ORDER_UPDATE_TIME.equals(sortName)) {
				sqlSelect.append(String
						.format(" order by %s %s", sortName, ORDER_DESC
								.equals(flexiGrid.getSortorder()) ? ORDER_DESC
								: ORDER_ASC));
			}
			sqlSelect.append(String.format(" limit %d, %d",
					(flexiGrid.getPage() - 1) * flexiGrid.getPagesize(),
					flexiGrid.getPagesize()));
			flexiGrid.setRows(systemNameJdbcTemplate.query(
					sqlSelect.toString(), paramMap,
					new BeanPropertyRowMapper<PostBean>(PostBean.class)));
		}
		return flexiGrid;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.system.basic.manage.dao.PostDao#getPostBeanById(int)
	 */
	@Override
	public PostBean getPostBeanById(int id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(KEY_ID, id);

		final String sql = "select * from post_info where id = :id";
		return systemNameJdbcTemplate.queryForObject(sql, paramMap,
				new BeanPropertyRowMapper<PostBean>(PostBean.class));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.system.basic.manage.dao.PostDao#getPostBeanByPostName(java.lang.String
	 * )
	 */
	@Override
	public PostBean getPostBeanByPostName(String postName) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(KEY_POST_NAME, postName);

		final String sql = "select * from post_info where postName = :postName";
		return systemNameJdbcTemplate.queryForObject(sql, paramMap,
				new BeanPropertyRowMapper<PostBean>(PostBean.class));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.system.basic.manage.dao.PostDao#addPostBean(cn.system.basic.manage
	 * .bean.PostBean)
	 */
	@Override
	public boolean addPostBean(PostBean post) {
		return insertBean(post, "post_info",
				"postName, creatorId, createTime, updateUserId") > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.system.basic.manage.dao.PostDao#updatePostBeanById(cn.system.basic
	 * .manage.bean.PostBean)
	 */
	@Override
	public void updatePostBeanById(PostBean post) {
		updateBean(post, "post_info", "postName, updateUseId", "id");
	}

	/*
	 * (non-Javadoc)
	 * @see cn.system.basic.manage.dao.PostDao#deletePostBeanById(int)
	 */
	@Override
	public void deletePostBeanById(int id) {
		final Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(KEY_ID, id);
		final String sql = "delete from post_info where id = :id";
		systemNameJdbcTemplate.update(sql, paramMap);

		final String sqlUser = "update user_info set postId = null where postId = :id";
		systemNameJdbcTemplate.update(sqlUser, paramMap);
	}
}
