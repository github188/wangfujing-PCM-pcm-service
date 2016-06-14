package com.wangfj.product.price.persistence;

import com.wangfj.product.price.domain.entity.PcmChangePriceRecord;
import com.wangfj.product.price.domain.entity.PcmChangePriceRecordHis;

public interface PcmChangePriceRecordHisMapper {
    int deleteByPrimaryKey(Long sid);

    int insert(PcmChangePriceRecordHis record);

    int insertSelective(PcmChangePriceRecord record);

    PcmChangePriceRecordHis selectByPrimaryKey(Long sid);

    int updateByPrimaryKeySelective(PcmChangePriceRecordHis record);

    int updateByPrimaryKey(PcmChangePriceRecordHis record);
}