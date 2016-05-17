package com.wangfj.product.maindata.persistence;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.maindata.domain.entity.PcmErpTypeDict;

public interface PcmErpTypeDictMapper extends BaseMapper<PcmErpTypeDict> {
	int deleteByPrimaryKey(Integer sid);

	int insertSelective(PcmErpTypeDict record);

	PcmErpTypeDict selectByPrimaryKey(Integer sid);

	int updateByPrimaryKeySelective(PcmErpTypeDict record);

	int updateByPrimaryKey(PcmErpTypeDict record);
}