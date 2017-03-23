package cn.system.basic.manage.dao;

import java.util.List;
import cn.system.basic.manage.bean.DeptBean;

/**
 * Interface definition to manage department related info.
 * 
 * @author Luo Yinzhuo
 */
public interface DeptDao {
	/** The root department id. */
	public final static Integer ROOT_DEPT_ID = -1;

	/** The key to map column id. */
	public final static String KEY_ID = "id";
	/** The key to map column parent id. */
	public final static String KEY_PARENT_ID = "pId";
	/** The key to map column department name. */
	public final static String KEY_DEPT_NAME = "deptName";

	/**
	 * Get specified parent id's {@link DeptBean}.
	 * 
	 * @param pId
	 * The parent id.
	 * @return The {@link DeptBean}.
	 */
	public List<DeptBean> getDeptBeanListByParentId(int pId);

	/**
	 * Get a department bean by its id.
	 * 
	 * @param id
	 * The id.
	 * @return The department bean or null if not exists.
	 */
	public DeptBean getDeptBeanById(int id);

	/**
	 * Add a department.
	 * 
	 * @param dept
	 * The department.
	 */
	public void addDeptBean(DeptBean dept);

	/**
	 * Update a department by its id.
	 * 
	 * @param dept
	 * The department.
	 */
	public void updateDeptBeanById(DeptBean dept);

	/**
	 * Delete a department by its id.
	 * 
	 * @param dept
	 * The department.
	 */
	public void deleteDeptBeanById(DeptBean dept);
}
