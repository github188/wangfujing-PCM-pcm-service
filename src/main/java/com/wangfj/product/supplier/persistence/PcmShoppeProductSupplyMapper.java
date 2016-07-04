package com.wangfj.product.supplier.persistence;

import java.util.List;
import java.util.Map;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.supplier.domain.entity.PcmShoppeProductSupply;
import com.wangfj.product.supplier.domain.vo.PcmShoppeProSupplyUploadDto;

public interface PcmShoppeProductSupplyMapper extends BaseMapper<PcmShoppeProductSupply> {
    int deleteByPrimaryKey(Long sid);

    int insertSelective(PcmShoppeProductSupply record);

    PcmShoppeProductSupply selectByPrimaryKey(Long sid);

    int updateByPrimaryKeySelective(PcmShoppeProductSupply record);

    int updateByPrimaryKey(PcmShoppeProductSupply record);

    /**
     * 多条件查询
     *
     * @param paramMap
     * @return List<PcmShoppeProductSupply>
     * @Methods Name getListByParam
     * @Create In 2015-9-17 By wangxuan
     */
    List<PcmShoppeProductSupply> getListByParam(Map<String, Object> paramMap);

    /**
     * 专柜商品一品多商下发查询
     *
     * @param paramMap
     * @return
     */
    List<PcmShoppeProSupplyUploadDto> pushShoppeProSupply(Map<String, Object> paramMap);
    /**
     * 判断专柜商品是否有该供应商
     * @Methods Name getPrrsInfo
     * @Create In 2016年6月29日 By wangc
     * @param paramMap
     * @return List<PcmShoppeProductSupply>
     */
    List<PcmShoppeProductSupply> getPrrsInfo(Map<String,Object> paramMap);
}