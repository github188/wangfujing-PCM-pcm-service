package com.wangfj.product.supplier.persistence;

import java.util.List;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.supplier.domain.entity.PcmSupplyShoppeRelation;
import com.wangfj.product.supplier.domain.vo.PcmShoppeSupplierQueryDto;
import com.wangfj.product.supplier.domain.vo.PcmShoppeSupplierResultDto;

public interface PcmSupplyShoppeRelationMapper extends BaseMapper<PcmSupplyShoppeRelation> {
	int deleteByPrimaryKey(Long sid);

	Integer insert(PcmSupplyShoppeRelation record);

	int insertSelective(PcmSupplyShoppeRelation record);

	PcmSupplyShoppeRelation selectByPrimaryKey(Long sid);

	int updateByPrimaryKeySelective(PcmSupplyShoppeRelation record);

	int updateByPrimaryKey(PcmSupplyShoppeRelation record);

	List<PcmSupplyShoppeRelation> selectListByParam(PcmSupplyShoppeRelation record);

	/**
	 * 根据(门店&&(专柜||供应商))查询专柜及供应商的信息
	 * 
	 * @Methods Name findShoppeSupplierInfoByParam
	 * @Create In 2015-12-16 By wangxuan
	 * @param dto
	 * @return List<PcmShoppeSupplierResultDto>
	 */
	List<PcmShoppeSupplierResultDto> findShoppeSupplierInfoByParam(PcmShoppeSupplierQueryDto dto);

	/**
	 * 根据专柜供应商查询关系数量
	 * 
	 * @Methods Name findShoppeSupplierInfoCountByParam
	 * @Create In 2015-12-28 By wangxuan
	 * @param dto
	 * @return Integer
	 */
	Integer findShoppeSupplierInfoCountByParam(PcmShoppeSupplierQueryDto dto);
}