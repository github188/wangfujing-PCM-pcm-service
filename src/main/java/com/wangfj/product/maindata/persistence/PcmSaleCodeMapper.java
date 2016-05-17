package com.wangfj.product.maindata.persistence;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.maindata.domain.entity.PcmSaleCode;

public interface PcmSaleCodeMapper extends BaseMapper<PcmSaleCode> {
	int deleteByPrimaryKey(Long sid);

	int insertSelective(PcmSaleCode record);

	PcmSaleCode selectByPrimaryKey(Long sid);

	int updateByPrimaryKeySelective(PcmSaleCode record);

	int updateByPrimaryKey(PcmSaleCode record);
}