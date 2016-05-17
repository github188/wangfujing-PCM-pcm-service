package com.wangfj.product.price.persistence;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.price.domain.entity.PcmChangePricePowerCard;

/**
 * 变价权限mapper
 * @Class Name PcmChangePricePowerCardMapper
 * @Author duanzhaole
 * @Create In 2015年7月3日
 */
public interface PcmChangePricePowerCardMapper extends BaseMapper<PcmChangePricePowerCard>{
    int deleteByPrimaryKey(Long sid);


    int insertSelective(PcmChangePricePowerCard record);

    PcmChangePricePowerCard selectByPrimaryKey(Long sid);

    int updateByPrimaryKeySelective(PcmChangePricePowerCard record);

    int updateByPrimaryKey(PcmChangePricePowerCard record);
}