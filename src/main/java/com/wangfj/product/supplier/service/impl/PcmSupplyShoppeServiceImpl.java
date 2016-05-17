package com.wangfj.product.supplier.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangfj.product.supplier.domain.vo.PcmShoppeSupplierQueryDto;
import com.wangfj.product.supplier.domain.vo.PcmShoppeSupplierResultDto;
import com.wangfj.product.supplier.persistence.PcmSupplyShoppeRelationMapper;
import com.wangfj.product.supplier.service.intf.IPcmSupplyShoppeService;

@Service
public class PcmSupplyShoppeServiceImpl implements IPcmSupplyShoppeService {

	private static final Logger logger = LoggerFactory.getLogger(PcmSupplyShoppeServiceImpl.class);

	@Autowired
	private PcmSupplyShoppeRelationMapper supplyShoppeRelationMapper;

	/**
	 * 根据(门店&&(专柜||供应商))查询专柜及供应商的信息
	 * 
	 * @Methods Name findShoppeSupplierInfoByParam
	 * @Create In 2015-12-16 By wangxuan
	 * @param dto
	 * @return List<PcmShoppeSupplierResultDto>
	 */
	@Override
	public List<PcmShoppeSupplierResultDto> findShoppeSupplierInfoByParam(
			PcmShoppeSupplierQueryDto dto) {

		logger.info("start findShoppeSupplierInfoByParam(),param:" + dto.toString());
		List<PcmShoppeSupplierResultDto> list = supplyShoppeRelationMapper
				.findShoppeSupplierInfoByParam(dto);

		logger.info("end findShoppeSupplierInfoByParam(),return:" + list.toString());
		return list;
	}
}
