package com.wangfj.product.maindata.persistence;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.maindata.domain.entity.PcmPromotionRateProduct;

public interface PcmPromotionRateProductMapper extends BaseMapper<PcmPromotionRateProduct> {
	int deleteByPrimaryKey(Long sid);

	int insertSelective(PcmPromotionRateProduct record);

	PcmPromotionRateProduct selectByPrimaryKey(Long sid);

	int updateByPrimaryKeySelective(PcmPromotionRateProduct record);

	int updateByPrimaryKey(PcmPromotionRateProduct record);

	String selectPromoSaleCode(PcmPromotionRateProduct entity);
}