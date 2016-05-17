package com.wangfj.product.maindata.persistence;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.maindata.domain.entity.PcmColorDict;

public interface PcmColorDictMapper extends BaseMapper<PcmColorDict> {
	int deleteByPrimaryKey(Long sid);

	int insertSelective(PcmColorDict record);

	PcmColorDict selectByPrimaryKey(Long sid);

	int updateByPrimaryKeySelective(PcmColorDict record);

	int updateByPrimaryKey(PcmColorDict record);
}