package com.wangfj.product.price.persistence;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.price.domain.entity.PcmOriginalPrice;

/**
 * 商品原价mapper
 * @Class Name PcmOriginalPriceMapper
 * @Author duanzhaole
 * @Create In 2015年7月3日
 */
public interface PcmOriginalPriceMapper extends BaseMapper<PcmOriginalPrice> {
    int deleteByPrimaryKey(Long productDetailSid);

    int insertSelective(PcmOriginalPrice record);

    PcmOriginalPrice selectByPrimaryKey(Long productDetailSid);

    int updateByPrimaryKeySelective(PcmOriginalPrice record);

    int updateByPrimaryKey(PcmOriginalPrice record);
}