package com.wangfj.product.price.persistence;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.price.domain.entity.PcmChangePriceInterval;

/**
 * 变价区间mapper
 * @Class Name PcmChangePriceIntervalMapper
 * @Author duanzhaole
 * @Create In 2015年7月3日
 */
public interface PcmChangePriceIntervalMapper extends BaseMapper<PcmChangePriceInterval> {
    int deleteByPrimaryKey(Long sid);


    int insertSelective(PcmChangePriceInterval record);

    PcmChangePriceInterval selectByPrimaryKey(Long sid);

    int updateByPrimaryKeySelective(PcmChangePriceInterval record);

    int updateByPrimaryKey(PcmChangePriceInterval record);
}