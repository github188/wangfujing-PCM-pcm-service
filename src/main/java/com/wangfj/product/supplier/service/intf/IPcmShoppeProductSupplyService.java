package com.wangfj.product.supplier.service.intf;

import com.wangfj.product.supplier.domain.entity.PcmShoppeProductSupply;
import com.wangfj.product.supplier.domain.vo.PcmShoppeProSupplyUploadDto;

import java.util.Map;

public interface IPcmShoppeProductSupplyService {

	/**
	 * 一品多供应商关系上传
	 * 
	 * @Methods Name uploadShoppeProSupply
	 * @Create In 2015-8-28 By wangxuan
	 * @param dto
	 * @return Map<String, Object>
	 */
	Map<String, Object> uploadShoppeProSupply(PcmShoppeProSupplyUploadDto dto);

	/**
	 * 判重
	 * 
	 * @Methods Name isExist
	 * @Create In 2015-9-17 By wangxuan
	 * @param shoppeProductSupply
	 * @return Boolean
	 */
	Boolean isExist(PcmShoppeProductSupply shoppeProductSupply);

}
