package com.wangfj.product.category.persistence;

import java.util.List;
import java.util.Map;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.category.domain.entity.PcmCategoryPropValues;
import com.wangfj.product.category.domain.vo.PcmPropsDictsDto;
import com.wangfj.product.category.domain.vo.ValuesVO;

/**
 * 分类属性关联mapper
 * 
 * @Class Name PcmCategoryPropValuesMapper
 * @Author duanzhaole
 * @Create In 2015年7月3日
 */
public interface PcmCategoryPropValuesMapper extends BaseMapper<PcmCategoryPropValues> {
	int deleteByPrimaryKey(Long sid);

	int deleteList(PcmCategoryPropValues record);

	int insertSelective(PcmCategoryPropValues record);

	int insertBatch(List<PcmCategoryPropValues> list);

	PcmCategoryPropValues selectByPrimaryKey(Long sid);

	int updateByPrimaryKeySelective(PcmCategoryPropValues record);

	int updateByCategorySid(Map<String, Object> mappram);

	int updateByPrimaryKey(PcmCategoryPropValues record);

	int updateBatch(List<PcmCategoryPropValues> list);

	List<PcmCategoryPropValues> selectList(PcmCategoryPropValues record);

	List<PcmCategoryPropValues> selectPage(PcmCategoryPropValues record);

	int selectPageTotal(PcmCategoryPropValues record);

	int selectPropsVOTotal(PcmCategoryPropValues record);

	List<PcmCategoryPropValues> selectCateVO(PcmCategoryPropValues record);

	List<PcmCategoryPropValues> selectPropsVOList(PcmCategoryPropValues record);

	List<PcmCategoryPropValues> selectPropsVO(PcmCategoryPropValues record);

	/**
	 * 根据品类Id与属性Id获取属性值
	 * 
	 * @Methods Name getAllCategoryPropsVOs
	 * @Create In 2015-3-5 By chengsj
	 * @param record
	 * @return List<ValuesVO>
	 */
	List<ValuesVO> getAllCategoryValuesVOs(Map map);

	PcmCategoryPropValues selectByPropSid(PcmPropsDictsDto propsSid);

	List<String> getSidListParams(Map<String, Object> paramMap);

	int deleteSidListParams(Map<String, Object> paramMap);

	int insertCategoryPropValBatch(Map<String, Object> paramMap);

	int deleteDragInheritance(Map<String, Object> paramMap);

}