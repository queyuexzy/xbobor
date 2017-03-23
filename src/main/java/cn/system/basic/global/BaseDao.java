package cn.system.basic.global;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import cn.system.basic.global.bean.BaseBean;
import cn.system.basic.global.bean.SortBean;
import cn.system.basic.global.bean.TypeSelectBean;

public interface BaseDao<T> {
	/**
	 * 更新oprMap中有值属性
	 * @author zhl
	 * @date   20112011-8-25下午08:44:49
	 *
	 * @param oprBean
	 * @return
	 * @throws Exception
	 */
	public int updateBeanByPrimary(Map<String, Object> oprMap, String tableName, String primaryName)throws Exception;
	
	/**
	 * 新增
	 * @author zhl
	 * @date   20112011-8-25下午08:44:49
	 *
	 * @param oprBean
	 * @return
	 * @throws Exception
	 */
	public long insertBean(Map<String, Object> oprMap, String tableName, String primaryName)throws Exception;
	
	/**
	 * 删除一个bean
	 * @author zhl
	 * @date   20112011-8-25下午08:56:58
	 *
	 * @param tableName
	 * @param primary
	 * @param keyVaule
	 * @return
	 * @throws Exception
	 */
	public int deleteBean(String tableName, String primaryName, Object primaryValue)throws Exception;
	
	/**
	 * 删除一个bean
	 * @author zhl
	 * @date   20112011-8-25下午08:56:58
	 *
	 * @param tableName
	 * @param primary
	 * @param keyVaule
	 * @return
	 * @throws Exception
	 */
	public int batchdeleteBean(String tableName, String primaryName, Collection<String> primaryValues)throws Exception;
	
	/**
	 * 取一个bean根据主键
	 * @author zhl
	 * @date   20112011-8-25下午09:01:43
	 *
	 * @param tableName
	 * @param primaryName
	 * @param primaryValue
	 * @return
	 * @throws Exception
	 */
	public BaseBean findByPrimary(String tableName, String primaryName, Object primaryValue)throws Exception;
	
	/**
	 * 唯一检查
	 * @author zhl
	 * @date   20112011-8-26下午02:03:39
	 *
	 * @param tableName
	 * @param fieldName
	 * @param primaryName
	 * @param conditon
	 * @return
	 * @throws Exception
	 */
	public int onlyCheck(String tableName, String fieldName, String primaryName, Map<String, Object> conditon)throws Exception;
	
	/**
	 * 更改排序
	 * @author zhl
	 * @date   2011-8-31下午02:32:40
	 *
	 * @param sortBean
	 * @return
	 * @throws Exception
	 */
	 public int updateSortBean(SortBean sortBean)throws Exception;
	 
	 public int updateSortList(SortBean sortBean)throws Exception;
	 
	 /**
	  * 取排序的下来选项
	  * @author zhl
	  * @date   2011-8-31下午02:39:20
	  *
	  * @param sortBean
	  * @return
	  * @throws Exception
	  */
	 public List<TypeSelectBean> getSortTypeSelectList(SortBean sortBean)throws Exception;
	 
	 /**
	  * 重置排序
	  * @author zhl
	  * @date   2011-9-27下午3:22:55
	  *
	  * @param sortBean
	  * @return
	  * @throws Exception
	  */
	 public int resetSort(SortBean sortBean) throws Exception;
}
