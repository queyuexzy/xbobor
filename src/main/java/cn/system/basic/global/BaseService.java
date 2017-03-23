package cn.system.basic.global;

import java.util.List;
import cn.system.basic.global.bean.BaseBean;
import cn.system.basic.global.bean.SortBean;
import cn.system.basic.global.bean.TypeSelectBean;

public interface BaseService {
	
	/**
	 * 更新
	 * @author zhl
	 * @date 20112011-8-25下午08:44:49
	 *
	 * @param oprBean
	 * @return
	 * @throws Exception
	 */
	public int updateByIdBean(BaseBean oprBean)throws Exception;
	
	/**
	 * 更新 所有属性根据主键
	 * @author zhl
	 * @date 20112011-8-25下午08:44:49
	 *
	 * @param oprBean
	 * @return
	 * @throws Exception
	 */
	public int updateAllBeanByPrimary(BaseBean oprBean, String primaryName)throws Exception;

	/**
	 * 更新 所有属性
	 * @author zhl
	 * @date 20112011-8-25下午08:44:49
	 *
	 * @param oprBean
	 * @return
	 * @throws Exception
	 */
	public int updateAllByIdBean(BaseBean oprBean)throws Exception;
	
	/**
	 * 更新 根据主键
	 * @author zhl
	 * @date 20112011-8-25下午08:44:49
	 *
	 * @param oprBean
	 * @return
	 * @throws Exception
	 */
	public int updateBeanByPrimary(BaseBean oprBean, String primaryName)throws Exception;

	/**
	 * 新增
	 * @author zhl
	 * @date   20112011-8-25下午09:06:57
	 *
	 * @param oprBean
	 * @return
	 * @throws Exception
	 */
	public long insertBean(BaseBean oprBean)throws Exception;
	
	/**
	 * 删除bean  有id值
	 * @author zhl
	 * @date 20112011-8-25下午08:47:24
	 *
	 * @param oprBean
	 * @return
	 * @throws Exception
	 */
	public int deleteByIdBean(BaseBean oprBean)throws Exception;
	
	/**
	 * 根据指定主键删除
	 * @author zhl
	 * @date   20112011-8-25下午08:49:08
	 *
	 * @param oprBean
	 * @param primary
	 * @return
	 * @throws Exception
	 */
	public int deleteBeanByPrimary(BaseBean oprBean, String primaryName)throws Exception;

	/**
	 * 批量删除根据id
	 * @author zhl
	 * @date 20112011-8-25下午08:51:07
	 *
	 * @param oprBean
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public int batchDeleteById(BaseBean oprBean, String[] ids)throws Exception;

	/**
	 * 根据指定主键批量删除
	 * @author zhl
	 * @date 20112011-8-25下午08:52:38
	 *
	 * @param oprBean
	 * @param keys
	 * @param primary
	 * @return
	 * @throws Exception
	 */
	public int batchDeleteByPrimary(BaseBean oprBean, String[] primaryValues, String primaryName)throws Exception;

	 
	/**
	 * 唯一检查
	 * @author zhl
	 * @date 20112011-8-26下午01:58:53
	 *
	 * @param oprBean
	 * @param fieldName   要检查的字段
	 * @param primary     对应表的主键
	 * @return
	 * @throws Exception
	 */
	public boolean onlyCheck(BaseBean oprBean, String fieldName, String primary)throws Exception;
	
	 /**
	  * 取排序的下来选项
	  * @author zhl
	  * @date 2011-8-31下午02:39:20
	  *
	  * @param sortBean
	  * @return
	  * @throws Exception
	  */
	 public List<TypeSelectBean> getSortTypeSelectList(SortBean sortBean)throws Exception;

	 /**
	  * 更改排序
	  * @author zhl
	  * @date 2011-8-31下午02:41:55
	  *
	  * @param sortBean
	  * @return
	  * @throws Exception
	  */
	 public boolean updateSort(SortBean sortBean)throws Exception;
}
