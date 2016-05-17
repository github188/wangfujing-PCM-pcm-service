package com.wangfj.product.maindata.persistence;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.maindata.domain.entity.PcmPromotionRule;

public interface PcmPromotionRuleMapper extends BaseMapper<PcmPromotionRule> {
	int deleteByPrimaryKey(Long sid);

	int insertSelective(PcmPromotionRule record);

	PcmPromotionRule selectByPrimaryKey(Long sid);

	int updateByPrimaryKeySelective(PcmPromotionRule record);

	int updateByPrimaryKey(PcmPromotionRule record);
}