package com.wangfj.product.maindata.persistence;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.maindata.domain.entity.PcmPackUnitDict;

public interface PcmPackUnitDictMapper extends BaseMapper<PcmPackUnitDict> {
	int deleteByPrimaryKey(Long sid);

	int insertSelective(PcmPackUnitDict record);

	PcmPackUnitDict selectByPrimaryKey(Long sid);

	int updateByPrimaryKeySelective(PcmPackUnitDict record);

	int updateByPrimaryKey(PcmPackUnitDict record);
}