package com.wangfj.product.price.persistence;

import com.wangfj.product.price.domain.entity.PcmPaymentOrgan;

public interface PcmPaymentOrganMapper {
	int insertSelective(PcmPaymentOrgan record);

	int updateByPrimaryKeySelective(PcmPaymentOrgan record);
}