package com.wangfj.product.brand.service.intf;

import com.wangfj.core.framework.base.page.Page;
import com.wangfj.product.brand.domain.entity.PcmBrand;
import com.wangfj.product.brand.domain.vo.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * 品牌的管理
 *
 * @Class Name IPcmBrandService
 * @Author wangx
 * @Create In 2015年7月30日
 */
public interface IPcmBrandService {

    /**
     * 修改门店品牌
     *
     * @param brand
     * @return Integer
     * @Methods Name updatePcmBrand
     * @Create In 2015年7月30日 By wangx
     */
    Integer updatePcmBrand(PcmBrand brand);

    /**
     * 删除门店品牌
     *
     * @param paraMap
     * @return Integer
     * @Methods Name deleteBrand
     * @Create In 2015-8-5 By wangx
     */
    Integer deleteBrand(Map<String, Object> paraMap);

    /**
     * 添加品牌
     *
     * @param brand
     * @return Integer
     * @Methods Name addBrand
     * @Create In 2015-8-12 By wangx
     */
    Integer addBrand(PcmBrand brand);

    /**
     * 修改集团品牌
     *
     * @param brand
     * @return Integer
     * @Methods Name updateBrandGroup
     * @Create In 2015-8-13 By wangx
     */
    Integer updateBrandGroup(PcmBrand brand);

    /**
     * 修改门店品牌与集团品牌的关系
     *
     * @param brandDto
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException Integer
     * @Methods Name updateRelation
     * @Create In 2015-8-13 By wangx
     */
    Integer updateRelation(PcmBrandDto brandDto) throws IllegalAccessException,
            InvocationTargetException;

    /**
     * 分页查询品牌(模糊)
     *
     * @param pageDto
     * @return Page<PcmBrandDto>
     * @Methods Name findPageBrand
     * @Create In 2015-8-13 By wangx
     */
    Page<PcmBrandDto> findPageBrand(SelectPcmBrandPageDto pageDto);

    /**
     * 删除集团品牌
     *
     * @param paraMap
     * @return Integer
     * @Methods Name deleteBrandGroup
     * @Create In 2015-8-5 By wangx
     */
    Integer deleteBrandGroup(Map<String, Object> paraMap);

    /**
     * 根据某门店品牌查询其对应的集团品牌
     *
     * @param para
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException PcmBrandDto
     * @Methods Name findBrandGroupByParentSid
     * @Create In 2015-8-13 By wangx
     */
    PcmBrandDto findBrandGroupByParentSid(Map<String, Object> para) throws IllegalAccessException,
            InvocationTargetException;

    /**
     * 查询品牌（非模糊，非分页）
     *
     * @param paramMap
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException List<PcmBrandDto>
     * @Methods Name findListBrand
     * @Create In 2015-8-16 By wangx
     */
    List<PcmBrandDto> findListBrand(Map<String, Object> paramMap) throws IllegalAccessException,
            InvocationTargetException;

    /**
     * 删除门店品牌与集团品牌的关系
     *
     * @param paramMap
     * @return Integer
     * @Methods Name deleteRelation
     * @Create In 2015-8-18 By wangx
     */
    Integer deleteRelation(Map<String, Object> paramMap);

    /**
     * 查询品牌（模糊，非分页）
     *
     * @param selectDto
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException List<PcmBrandDto>
     * @Methods Name findListBrand
     * @Create In 2015-8-18 By wangx
     */
    List<PcmBrandDto> findListBrand(SelectPcmBrandPageDto selectDto) throws IllegalAccessException,
            InvocationTargetException;

    /**
     * 查询品牌部分信息（模糊）
     *
     * @param selectDto
     * @return Page<PcmBrandPartDto>
     * @Methods Name findListBrandPartInfo
     * @Create In 2016-02-22 By wangxuan
     */
    Page<PcmBrandPartDto> findListBrandPartInfo(SelectPcmBrandPageDto selectDto);


    /**
     * 根据sid查询
     *
     * @param para
     * @return PcmBrand
     * @Methods Name findBrandBySid
     * @Create In 2015-8-26 By wangxuan
     */
    PcmBrand findBrandBySid(Map<String, Object> para);

    /**
     * 分页查找(移动工作台调用主数据获取品牌信息)
     *
     * @param para
     * @return List<PadBrandResultDto>
     * @Methods Name getPageBrandForPad
     * @Create In 2015-8-26 By wangxuan
     */
    List<PadBrandResultDto> getPageBrandForPad(Map<String, Object> para);

    /**
     * 门店品牌数据下发分页查找
     *
     * @param para
     * @return List<PushBrandDto>
     * @Methods Name pushPageBrandData
     * @Create In 2015-8-27 By wangxuan
     */
    List<PushBrandDto> pushPageBrandData(Map<String, Object> para);

    /**
     * 分页查询门店品牌及其集团品牌
     *
     * @param paramMap
     * @return Page<PcmBrandDto>
     * @Methods Name findPageBrandAndBrandGroup
     * @Create In 2015-9-8 By wangxuan
     */
    Page<PcmBrandDto> findPageBrandAndBrandGroup(Map<String, Object> paramMap);

    /**
     * 批量添加门店品牌与集团品牌的关系
     *
     * @param brandDtoList
     * @return Integer
     * @Methods Name addRelationList
     * @Create In 2015-9-14 By wangxuan
     */
    Integer addRelationList(List<PcmBrandDto> brandDtoList);

    /**
     * 根据门店的sid和集团品牌的sid查询门店品牌
     *
     * @param paramMap
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException List<PcmBrandDto>
     * @Methods Name getListBrandByShopSidAndParentSid
     * @Create In 2015-9-21 By wangxuan
     */
    List<PcmBrandDto> getListBrandByShopSidAndParentSid(Map<String, Object> paramMap)
            throws IllegalAccessException, InvocationTargetException;

    /**
     * 根据sid查询下发给搜索的数据（修改品牌时）
     *
     * @param para
     * @return List<PushSearchBrandDto>
     * @Methods Name pushSearchBrandBySid
     * @Create In 2015-10-9 By wangxuan
     */
    List<PushSearchBrandDto> pushSearchBrandBySid(Map<String, Object> para);

    /**
     * 搜索线上下发品牌信息
     *
     * @param paramMap
     * @return List<Map<String,Object>>
     * @Methods Name pushSearchOnlineBrandBySid
     * @Create In 2015-11-10 By wangxuan
     */
    List<Map<String, Object>> pushSearchOnlineBrandBySid(Map<String, Object> paramMap);

    /**
     * 集团品牌下发给搜索
     *
     * @param paramMap
     * @return List<Map<String,Object>>
     * @Methods Name pushBrandGroupToSearch
     * @Create In 2016-03-21 By wangxuan
     */
    List<Map<String, Object>> pushBrandGroupToSearch(Map<String, Object> paramMap);

    Page<PcmBrandDto> getPageBrandFromShopBrandRelation(PcmShopBrandDto dto);

    /**
     * 根据skuSid和门店sid查询门店品牌
     *
     * @param paramMap
     * @return List<PcmBrand>
     * @Methods Name getListBrandByShopSidAndSkuSid
     * @Create In 2015-11-19 By wangxuan
     */
    List<PcmBrand> getListBrandByShopSidAndSkuSid(Map<String, Object> paramMap);

}
