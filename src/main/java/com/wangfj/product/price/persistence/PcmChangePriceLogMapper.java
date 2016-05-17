package com.wangfj.product.price.persistence;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.price.domain.entity.PcmChangePriceLog;

/**
 * 变价日志mapper
 * @Class Name PcmChangePriceLogMapper
 * @Author duanzhaole
 * @Create In 2015年7月3日
 */
public interface PcmChangePriceLogMapper extends BaseMapper<PcmChangePriceLog> {
    int deleteByPrimaryKey(Long sid);


    int insertSelective(PcmChangePriceLog record);

    PcmChangePriceLog selectByPrimaryKey(Long sid);

    int updateByPrimaryKeySelective(PcmChangePriceLog record);

    int updateByPrimaryKey(PcmChangePriceLog record);
}