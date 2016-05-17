package com.wangfj.product.category.persistence;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.category.domain.entity.PcmProductClass;

public interface PcmProductClassMapper extends BaseMapper<PcmProductClass>{
    int deleteByPrimaryKey(Integer sid);

    int insertSelective(PcmProductClass record);

    PcmProductClass selectByPrimaryKey(Integer sid);

    int updateByPrimaryKeySelective(PcmProductClass record);

    int updateByPrimaryKey(PcmProductClass record);
}