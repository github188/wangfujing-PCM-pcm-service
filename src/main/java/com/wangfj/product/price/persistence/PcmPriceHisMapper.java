package com.wangfj.product.price.persistence;

import com.wangfj.product.price.domain.entity.PcmPrice;
import com.wangfj.product.price.domain.entity.PcmPriceHis;

public interface PcmPriceHisMapper {
	int deleteByPrimaryKey(Long sid);

	int insert(PcmPriceHis record);

	/**
	 * 新增价格历史信息
	 * 
	 * @Methods Name insertSelective
	 * @Create In 2015年8月18日 By kongqf
	 * @param record
	 * @return int
	 */
	int insertSelective(PcmPrice record);

	PcmPriceHis selectByPrimaryKey(Long sid);

	int updateByPrimaryKeySelective(PcmPriceHis record);

	int updateByPrimaryKey(PcmPriceHis record);

}
