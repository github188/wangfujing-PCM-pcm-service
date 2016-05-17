package com.wangfj.product.maindata.persistence;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.maindata.domain.entity.PcmMeasureUnitDict;

public interface PcmMeasureUnitDictMapper extends BaseMapper<PcmMeasureUnitDict> {
	int deleteByPrimaryKey(Long sid);

	int insertSelective(PcmMeasureUnitDict record);

	PcmMeasureUnitDict selectByPrimaryKey(Long sid);

	int updateByPrimaryKeySelective(PcmMeasureUnitDict record);

	int updateByPrimaryKey(PcmMeasureUnitDict record);
}