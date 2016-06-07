package com.wangfj.product.maindata.persistence;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.maindata.domain.entity.PcmProInput;

public interface PcmProInputMapper extends BaseMapper<PcmProInput> {
	int deleteByPrimaryKey(Long sid);

	Integer insert(PcmProInput record);

	int insertSelective(PcmProInput record);

	PcmProInput selectByPrimaryKey(Long sid);

	int updateByPrimaryKeySelective(PcmProInput record);

	int updateByPrimaryKey(PcmProInput record);

	int updateByProSidSelective(PcmProInput record);
}