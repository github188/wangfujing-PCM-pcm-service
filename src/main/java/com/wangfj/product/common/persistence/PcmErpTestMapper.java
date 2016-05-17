package com.wangfj.product.common.persistence;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.common.domain.entity.PcmErpTest;

public interface PcmErpTestMapper extends BaseMapper<PcmErpTest> {
	int deleteByPrimaryKey(Long sid);

	Integer insert(PcmErpTest record);

	int insertSelective(PcmErpTest record);

	PcmErpTest selectByPrimaryKey(Long sid);

	int updateByPrimaryKeySelective(PcmErpTest record);

	int updateByPrimaryKey(PcmErpTest record);
}