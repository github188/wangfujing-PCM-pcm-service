package com.wangfj.product.supplier.service.intf;

import java.util.List;

import com.wangfj.product.supplier.domain.vo.PcmShoppeSupplierQueryDto;
import com.wangfj.product.supplier.domain.vo.PcmShoppeSupplierResultDto;

public interface IPcmSupplyShoppeService {

	/**
	 * 根据(门店&&(专柜||供应商))查询专柜及供应商的信息
	 * 
	 * @Methods Name findShoppeSupplierInfoByParam
	 * @Create In 2015-12-16 By wangxuan
	 * @param dto
	 * @return List<PcmShoppeSupplierResultDto>
	 */
	List<PcmShoppeSupplierResultDto> findShoppeSupplierInfoByParam(PcmShoppeSupplierQueryDto dto);

}
