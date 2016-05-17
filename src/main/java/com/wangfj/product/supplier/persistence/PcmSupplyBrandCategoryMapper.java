package com.wangfj.product.supplier.persistence;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.supplier.domain.entity.PcmSupplyBrandCategory;


public interface PcmSupplyBrandCategoryMapper extends BaseMapper<PcmSupplyBrandCategory> {
    int deleteByPrimaryKey(Long sid);

    Integer insert(PcmSupplyBrandCategory record);

    int insertSelective(PcmSupplyBrandCategory record);

    PcmSupplyBrandCategory selectByPrimaryKey(Long sid);

    int updateByPrimaryKeySelective(PcmSupplyBrandCategory record);

    int updateByPrimaryKey(PcmSupplyBrandCategory record);
}