package com.wangfj.product.maindata.persistence;

import com.wangfj.product.maindata.domain.entity.PcmProInput;

public interface PcmProInputMapper {
	int deleteByPrimaryKey(Long sid);

	int insert(PcmProInput record);

	int insertSelective(PcmProInput record);

	PcmProInput selectByPrimaryKey(Long sid);

	int updateByPrimaryKeySelective(PcmProInput record);

	int updateByPrimaryKey(PcmProInput record);

	int updateByProSidSelective(PcmProInput record);
}