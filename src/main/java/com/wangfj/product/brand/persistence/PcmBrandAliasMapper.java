package com.wangfj.product.brand.persistence;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.brand.domain.entity.PcmBrandAlias;


public interface PcmBrandAliasMapper extends BaseMapper<PcmBrandAlias> {
    int deleteByPrimaryKey(Long sid);

    Integer insert(PcmBrandAlias record);

    int insertSelective(PcmBrandAlias record);

    PcmBrandAlias selectByPrimaryKey(Long sid);

    int updateByPrimaryKeySelective(PcmBrandAlias record);

    int updateByPrimaryKey(PcmBrandAlias record);
}