package com.wangfj.product.maindata.persistence;

import com.wangfj.product.maindata.domain.entity.PcmShoppeProductExtend;

public interface PcmShoppeProductExtendMapper {
    int deleteByPrimaryKey(Long sid);

    int insert(PcmShoppeProductExtend record);

    int insertSelective(PcmShoppeProductExtend record);

    PcmShoppeProductExtend selectByPrimaryKey(Long sid);

    int updateByPrimaryKeySelective(PcmShoppeProductExtend record);

    int updateByPrimaryKey(PcmShoppeProductExtend record);
    
    Long getSidByShopProSid(Long sid);
}