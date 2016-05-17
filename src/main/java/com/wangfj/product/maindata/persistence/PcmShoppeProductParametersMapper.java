package com.wangfj.product.maindata.persistence;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.maindata.domain.entity.PcmShoppeProductParameters;

public interface PcmShoppeProductParametersMapper extends BaseMapper<PcmShoppeProductParameters> {
    int deleteByPrimaryKey(Long sid);

    int insertSelective(PcmShoppeProductParameters record);

    PcmShoppeProductParameters selectByPrimaryKey(Long sid);

    int updateByPrimaryKeySelective(PcmShoppeProductParameters record);

    int updateByPrimaryKey(PcmShoppeProductParameters record);
}