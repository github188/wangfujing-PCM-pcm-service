package com.wangfj.product.maindata.persistence;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.maindata.domain.entity.PcmShoppeProductCategory;

public interface PcmShoppeProductCategoryMapper extends BaseMapper<PcmShoppeProductCategory> {
    int deleteByPrimaryKey(Long sid);

    int insertSelective(PcmShoppeProductCategory record);

    PcmShoppeProductCategory selectByPrimaryKey(Long sid);

    int updateByPrimaryKeySelective(PcmShoppeProductCategory record);

    int updateByPrimaryKey(PcmShoppeProductCategory record);
}