package com.wangfj.product.price.persistence;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.price.domain.entity.PcmCurrentPriceLog;

/**
 * 商品现价日志mapper
 * @Class Name PcmCurrentPriceLogMapper
 * @Author duanzhaole
 * @Create In 2015年7月3日
 */
public interface PcmCurrentPriceLogMapper extends BaseMapper<PcmCurrentPriceLog> {
    int deleteByPrimaryKey(Long sid);


    int insertSelective(PcmCurrentPriceLog record);

    PcmCurrentPriceLog selectByPrimaryKey(Long sid);

    int updateByPrimaryKeySelective(PcmCurrentPriceLog record);

    int updateByPrimaryKey(PcmCurrentPriceLog record);
}