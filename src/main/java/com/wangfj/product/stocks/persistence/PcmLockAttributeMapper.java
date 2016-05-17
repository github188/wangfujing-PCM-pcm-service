package com.wangfj.product.stocks.persistence;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.stocks.domain.entity.PcmLockAttribute;

import java.util.List;
import java.util.Map;

public interface PcmLockAttributeMapper extends BaseMapper<PcmLockAttribute> {
    int deleteByPrimaryKey(Integer sid);

    int insertSelective(PcmLockAttribute record);

    PcmLockAttribute selectByPrimaryKey(Integer sid);

    int updateByPrimaryKeySelective(PcmLockAttribute record);

    List<PcmLockAttribute> findListByParam(Map<String, Object> paramMap);


}