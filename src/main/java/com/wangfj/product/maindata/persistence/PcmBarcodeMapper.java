package com.wangfj.product.maindata.persistence;

import java.util.List;
import java.util.Map;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.maindata.domain.entity.PcmBarcode;
import com.wangfj.product.maindata.domain.vo.BarcodeListDto;
import com.wangfj.product.maindata.domain.vo.PcmBarodeListDto;
import com.wangfj.product.maindata.domain.vo.ProductPageDto;

public interface PcmBarcodeMapper extends BaseMapper<PcmBarcode> {
    int deleteByPrimaryKey(Long sid);

    int insertSelective(PcmBarcode record);

    PcmBarcode selectByPrimaryKey(Long sid);

    int updateByPrimaryKeySelective(PcmBarcode record);

    int updateByPrimaryKey(PcmBarcode record);

    List<String> selectBarcodeByErp(Map<String, Object> map);

    List<BarcodeListDto> selectBarcodeByList(List<ProductPageDto> list);

    List<PcmBarodeListDto> getBarcodeByList(List<ProductPageDto> list);

    /**
     * 下发条码查询
     *
     * @return
     */
    List<PcmBarcode> pushBarcode(Map<String, Object> paramMap);

    /**
     * 根据sid和门店号删除
     *
     * @param paramMap
     * @return
     */
    int deleteByPrimaryKeyAndStoreCode(Map<String, Object> paramMap);

}