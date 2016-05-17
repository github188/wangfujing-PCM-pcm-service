package com.wangfj.product.maindata.persistence;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.maindata.domain.entity.PcmHourPromotionRule;

public interface PcmHourPromotionRuleMapper extends BaseMapper<PcmHourPromotionRule> {
	int deleteByPrimaryKey(Long sid);

	int insertSelective(PcmHourPromotionRule record);

	PcmHourPromotionRule selectByPrimaryKey(Long sid);

	int updateByPrimaryKeySelective(PcmHourPromotionRule record);

	int updateByPrimaryKey(PcmHourPromotionRule record);
}