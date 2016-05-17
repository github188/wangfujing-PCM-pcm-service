package com.wangfj.product.supplier.persistence;

import java.util.List;
import java.util.Map;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.supplier.domain.entity.PcmShoppeProductSupply;

public interface PcmShoppeProductSupplyMapper extends BaseMapper<PcmShoppeProductSupply> {
	int deleteByPrimaryKey(Long sid);

	int insertSelective(PcmShoppeProductSupply record);

	PcmShoppeProductSupply selectByPrimaryKey(Long sid);

	int updateByPrimaryKeySelective(PcmShoppeProductSupply record);

	int updateByPrimaryKey(PcmShoppeProductSupply record);

	/**
	 * 多条件查询
	 * 
	 * @Methods Name getListByParam
	 * @Create In 2015-9-17 By wangxuan
	 * @param paramMap
	 * @return List<PcmShoppeProductSupply>
	 */
	List<PcmShoppeProductSupply> getListByParam(Map<String, Object> paramMap);

}