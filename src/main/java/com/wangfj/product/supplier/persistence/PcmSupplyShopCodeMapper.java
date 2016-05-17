package com.wangfj.product.supplier.persistence;

import java.util.List;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.maindata.domain.vo.PromotionRateDto;
import com.wangfj.product.supplier.domain.entity.PcmSupplyShopCode;

public interface PcmSupplyShopCodeMapper extends BaseMapper<PcmSupplyShopCode> {
	int deleteByPrimaryKey(Long sid);

	Integer insert(PcmSupplyShopCode record);

	int insertSelective(PcmSupplyShopCode record);

	PcmSupplyShopCode selectByPrimaryKey(Long sid);

	int updateByPrimaryKeySelective(PcmSupplyShopCode record);

	int updateByPrimaryKey(PcmSupplyShopCode record);

	/**
	 * 校验促销扣率码是否正确
	 * 
	 * @Methods Name validProRateCode
	 * @Create In 2015年8月27日 By zhangxy
	 * @param PcmSupplyShopCode
	 * @return List<PcmSupplyShopCode>
	 */
	List<PcmSupplyShopCode> validProRateCode(PcmSupplyShopCode record);

	/**
	 * 查询促销扣率码
	 * 
	 * @Methods Name selectProRateCode
	 * @Create In 2015年8月27日 By zhangxy
	 * @param PromotionRateDto
	 * @return List<PcmSupplyShopCode>
	 */
	List<PromotionRateDto> selectProRateCode(PromotionRateDto dto);

}