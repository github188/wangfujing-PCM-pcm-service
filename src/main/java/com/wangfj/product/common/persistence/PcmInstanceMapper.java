package com.wangfj.product.common.persistence;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.common.domain.entity.PcmInstance;

public interface PcmInstanceMapper extends BaseMapper<PcmInstance> {
    int deleteByPrimaryKey(Long sid);

    Integer insert(PcmInstance record);

    int insertSelective(PcmInstance record);

    PcmInstance selectByPrimaryKey(Long sid);

    int updateByPrimaryKeySelective(PcmInstance record);

    int updateByPrimaryKey(PcmInstance record);
}