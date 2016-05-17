package com.wangfj.product.maindata.persistence;

import com.wangfj.product.maindata.domain.entity.PcmBarcodeHis;

public interface PcmBarcodeHisMapper {
    int deleteByPrimaryKey(Long sid);

    int insert(PcmBarcodeHis record);

    int insertSelective(PcmBarcodeHis record);

    PcmBarcodeHis selectByPrimaryKey(Long sid);

    int updateByPrimaryKeySelective(PcmBarcodeHis record);

    int updateByPrimaryKey(PcmBarcodeHis record);
}