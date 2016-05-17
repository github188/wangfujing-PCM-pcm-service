package com.wangfj.product.brand.service.intf;

import java.util.Map;

import com.wangfj.product.brand.domain.entity.PcmShopBrandRelation;
import com.wangfj.product.brand.domain.vo.PcmBrandUploadDto;
import com.wangfj.product.brand.domain.vo.PcmShopBrandUploadDto;

/**
 * 门店与品牌关系
 *
 * @Class Name IPcmShopBrandRelationService
 * @Author wangxuan
 * @Create In 2015-8-19
 */
public interface IPcmShopBrandRelationService {

    /**
     * 门店与品牌关系的判重
     *
     * @param shopBrandRelation
     * @return Boolean
     * @Methods Name isExistence
     * @Create In 2015-8-19 By wangxuan
     */
    Boolean isExistence(PcmShopBrandRelation shopBrandRelation);

    /**
     * 门店与门店品牌关系上传
     *
     * @param dto
     * @return Map<String, Object>
     * @Methods Name uploadShopBrandRelation
     * @Create In 2015-9-18 By wangxuan
     */
    Map<String, Object> uploadShopBrandRelation(PcmShopBrandUploadDto dto);

    /**
     * 门店与门店品牌信息上传
     *
     * @param dto
     * @return Map<String, Object>
     * @Methods Name uploadShopBrandInfo
     * @Create In 2015-11-18 By wangxuan
     */
    Map<String, Object> uploadShopBrandInfo(PcmShopBrandUploadDto dto);

    /**
     * 门店品牌上传
     *
     * @param dto
     * @return Map<String, Object>
     * @Methods Name uploadBrand
     * @Create In 2016-03-22 By wangxuan
     */
    Map<String, Object> uploadBrand(PcmBrandUploadDto dto);
}
