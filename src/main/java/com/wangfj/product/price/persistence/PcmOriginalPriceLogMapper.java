package com.wangfj.product.price.persistence;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.price.domain.entity.PcmOriginalPriceLog;

/**
 * 商品原价日志mapper
 * @Class Name PcmOriginalPriceLogMapper
 * @Author duanzhaole
 * @Create In 2015年7月3日
 */
public interface PcmOriginalPriceLogMapper extends BaseMapper<PcmOriginalPriceLog> {
    int deleteByPrimaryKey(Long sid);


    int insertSelective(PcmOriginalPriceLog record);

    PcmOriginalPriceLog selectByPrimaryKey(Long sid);

    int updateByPrimaryKeySelective(PcmOriginalPriceLog record);

    int updateByPrimaryKey(PcmOriginalPriceLog record);
}