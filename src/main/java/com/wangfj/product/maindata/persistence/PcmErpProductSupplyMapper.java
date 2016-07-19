package com.wangfj.product.maindata.persistence;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.maindata.domain.entity.PcmErpProductSupply;

import java.util.List;
import java.util.Map;

public interface PcmErpProductSupplyMapper extends BaseMapper<PcmErpProductSupply> {
    int deleteByPrimaryKey(Long sid);

    Integer insert(PcmErpProductSupply record);

    int insertSelective(PcmErpProductSupply record);

    PcmErpProductSupply selectByPrimaryKey(Long sid);

    int updateByPrimaryKeySelective(PcmErpProductSupply record);

    int updateByPrimaryKey(PcmErpProductSupply record);

    /**
     * 一品多商下发查询
     *
     * @param paramMap
     * @return
     */
    List<PcmErpProductSupply> pushListByParam(Map<String, Object> paramMap);
}