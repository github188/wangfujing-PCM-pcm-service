package com.wangfj.product.supplier.persistence;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.supplier.domain.entity.PcmSupplyGroup;

public interface PcmSupplyGroupMapper extends BaseMapper<PcmSupplyGroup> {
    int deleteByPrimaryKey(Long sid);

    Integer insert(PcmSupplyGroup record);

    int insertSelective(PcmSupplyGroup record);

    PcmSupplyGroup selectByPrimaryKey(Long sid);

    int updateByPrimaryKeySelective(PcmSupplyGroup record);

    int updateByPrimaryKey(PcmSupplyGroup record);
}