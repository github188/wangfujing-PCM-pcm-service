package com.wangfj.product.supplier.service.intf;

import com.wangfj.product.supplier.domain.entity.PcmShoppeProductSupply;
import com.wangfj.product.supplier.domain.vo.PcmShoppeProSupplyUploadDto;

import java.util.List;
import java.util.Map;

public interface IPcmShoppeProductSupplyService {

    /**
     * 一品多供应商关系上传
     *
     * @param dto
     * @return Map<String, Object>
     * @Methods Name uploadShoppeProSupply
     * @Create In 2015-8-28 By wangxuan
     */
    Map<String, Object> uploadShoppeProSupply(PcmShoppeProSupplyUploadDto dto);

    /**
     * 判重
     *
     * @param shoppeProductSupply
     * @return Boolean
     * @Methods Name isExist
     * @Create In 2015-9-17 By wangxuan
     */
    Boolean isExist(PcmShoppeProductSupply shoppeProductSupply);

    /**
     * 大码一品多商下发查询
     *
     * @param para
     * @return
     */
    List<PcmShoppeProSupplyUploadDto> pushErpProductSupply(Map<String, Object> para);

    /**
     * 专柜商品一品多商下发查询
     *
     * @param paramMap
     * @return
     */
    List<PcmShoppeProSupplyUploadDto> pushShoppeProSupply(Map<String, Object> paramMap);
}
