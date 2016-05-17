package com.wangfj.product.price.persistence;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.price.domain.entity.PcmCurrentPrice;

/**
 * 商品现价mapper
 * @Class Name PcmCurrentPriceMapper
 * @Author duanzhaole
 * @Create In 2015年7月3日
 */
public interface PcmCurrentPriceMapper extends BaseMapper<PcmCurrentPrice> {
    int deleteByPrimaryKey(Long sid);


    int insertSelective(PcmCurrentPrice record);

    PcmCurrentPrice selectByPrimaryKey(Long sid);

    int updateByPrimaryKeySelective(PcmCurrentPrice record);

    int updateByPrimaryKey(PcmCurrentPrice record);
}