package com.wangfj.product.maindata.persistence;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.maindata.domain.entity.PcmSeasonDict;

public interface PcmSeasonDictMapper extends BaseMapper<PcmSeasonDict> {
    int deleteByPrimaryKey(Integer sid);

    int insertSelective(PcmSeasonDict record);

    PcmSeasonDict selectByPrimaryKey(Integer sid);

    int updateByPrimaryKeySelective(PcmSeasonDict record);

    int updateByPrimaryKey(PcmSeasonDict record);
}