package com.wangfj.product.maindata.persistence;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.maindata.domain.entity.PcmProductTypeDict;

public interface PcmProductTypeDictMapper extends BaseMapper<PcmProductTypeDict> {
	int deleteByPrimaryKey(Integer sid);

	int insertSelective(PcmProductTypeDict record);

	PcmProductTypeDict selectByPrimaryKey(Integer sid);

	int updateByPrimaryKeySelective(PcmProductTypeDict record);

	int updateByPrimaryKey(PcmProductTypeDict record);
}