package com.wangfj.product.category.persistence;

import java.util.List;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.category.domain.entity.PcmCategoryValuesDict;
import com.wangfj.product.category.domain.vo.PcmCategoryValuesDictDto;

/**
 * 分类属性值字典表mapper
 * @Class Name PcmCategoryValuesDictMapper
 * @Author duanzhaole
 * @Create In 2015年7月3日
 */
public interface PcmCategoryValuesDictMapper extends BaseMapper<PcmCategoryValuesDict> {
    int deleteByPrimaryKey(Long sid);

	int insertBatch(List<PcmCategoryValuesDict> list);

    int insertSelective(PcmCategoryValuesDict record);

    PcmCategoryValuesDict selectByPrimaryKey(Long sid);

    int updateByPrimaryKeySelective(PcmCategoryValuesDict record);

    int updateByPrimaryKey(PcmCategoryValuesDict record);

	int updateStatus(PcmCategoryValuesDict record);

	int updateBatch(List<PcmCategoryValuesDict> list);

	List<PcmCategoryValuesDict> selectList(PcmCategoryValuesDict record);

	List<PcmCategoryValuesDictDto> selectPage(PcmCategoryValuesDict record);

	int selectPageTotal(PcmCategoryValuesDict record);
	
	List<PcmCategoryValuesDict> selectListByCategorySid(PcmCategoryValuesDictDto valuedto);
}
