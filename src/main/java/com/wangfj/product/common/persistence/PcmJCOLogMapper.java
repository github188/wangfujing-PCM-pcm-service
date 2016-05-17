package com.wangfj.product.common.persistence;

import com.wangfj.product.common.domain.entity.PcmJCOLog;

public interface PcmJCOLogMapper {
    int deleteByPrimaryKey(Long sid);

    int insert(PcmJCOLog record);

    int insertSelective(PcmJCOLog record);

    PcmJCOLog selectByPrimaryKey(Long sid);

    int updateByPrimaryKeySelective(PcmJCOLog record);

    int updateByPrimaryKey(PcmJCOLog record);
}