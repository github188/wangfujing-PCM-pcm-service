package com.wangfj.product.maindata.persistence;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.maindata.domain.entity.PcmErpProductSupply;

public interface PcmErpProductSupplyMapper extends BaseMapper<PcmErpProductSupply> {
    int deleteByPrimaryKey(Long sid);

    Integer insert(PcmErpProductSupply record);

    int insertSelective(PcmErpProductSupply record);

    PcmErpProductSupply selectByPrimaryKey(Long sid);

    int updateByPrimaryKeySelective(PcmErpProductSupply record);

    int updateByPrimaryKey(PcmErpProductSupply record);
}