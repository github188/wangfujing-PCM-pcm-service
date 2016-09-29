package com.wangfj.product.brand.persistence;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.brand.domain.entity.PcmBrand;
import com.wangfj.product.brand.domain.vo.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface PcmBrandMapper extends BaseMapper<PcmBrand> {

    int deleteByPrimaryKey(Long sid);

    Integer insert(PcmBrand record);

    int insertSelective(PcmBrand record);

    PcmBrand selectByPrimaryKey(Long sid);

    int updateByPrimaryKeySelective(PcmBrand record);

    int updateByPrimaryKey(PcmBrand record);

    /**
     * 分页记录总数查询
     *
     * @param pageDto
     * @return Long
     * @Methods Name getPageCountByPara
     * @Create In 2015-8-3 By wangx
     */
    Long getPageCountByPara(SelectPcmBrandPageDto pageDto);

    /**
     * 分页查询
     *
     * @param pageDto
     * @return List<PcmBrandDto>
     * @Methods Name selectPageByPara
     * @Create In 2015-8-3 By wangx
     */
    List<PcmBrandDto> selectPageByPara(SelectPcmBrandPageDto pageDto);

    /**
     * 查询门店品牌及其集团品牌总数
     *
     * @param paramMap
     * @return Long
     * @Methods Name getPageCountBrandAndBrandGroup
     * @Create In 2015-9-8 By wangxuan
     */
    Long getPageCountBrandAndBrandGroup(Map<String, Object> paramMap);

    /**
     * 分页查询门店品牌及其集团品牌
     *
     * @param paramMap
     * @return List<PcmBrandDto>
     * @Methods Name selectPageBrandAndBrandGroup
     * @Create In 2015-9-8 By wangxuan
     */
    List<PcmBrandDto> selectPageBrandAndBrandGroup(Map<String, Object> paramMap);

    /**
     * 查询（非分页，模糊）
     *
     * @param pageDto
     * @return List<PcmBrand>
     * @Methods Name selectListByParaSelective
     * @Create In 2015-8-18 By wangx
     */
    List<PcmBrand> selectListByParaSelective(SelectPcmBrandPageDto pageDto);

    /**
     * 查询部分信息（模糊）
     *
     * @param pageDto
     * @return List<PcmBrandPartDto>
     * @Methods Name selectListByParaSelective
     * @Create In 2016-02-22 By wangxuan
     */
    List<PcmBrandPartDto> selectListPartInfoByParam(SelectPcmBrandPageDto pageDto);

    /**
     * 查询部分信息（模糊）
     *
     * @param pageDto
     * @return Integer
     * @Methods Name selectListByParaSelective
     * @Create In 2016-03-11 By wangxuan
     */
    Integer selectCountPartInfoByParam(SelectPcmBrandPageDto pageDto);

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
     * 分页查找(移动工作台调用主数据获取品牌信息)
     *
     * @param para
     * @return List<PadBrandResultDto>
     * @Methods Name getPageBrandForPad
     * @Create In 2015-8-26 By wangxuan
     */
    List<PadBrandResultDto> getPageBrandForPad(Map<String, Object> para);

    /**
     * 下发门店品牌分页查找
     *
     * @param para
     * @return List<PushBrandDto>
     * @Methods Name pushPageBrandData
     * @Create In 2015-8-27 By wangxuan
     */
    List<PushBrandDto> pushPageBrandData(Map<String, Object> para);

    /**
     * 搜索查询门店品牌信息
     *
     * @param para
     * @return List<HashMap<String,Object>>
     * @Methods Name searchBrandByParam
     * @Create In 2015-8-27 By wangxuan
     */
    List<HashMap<String, Object>> searchBrandByParam(Map<String, Object> para);

    List<PcmBrand> selectBrandByShopSku(Map<String, Object> paramMap);

    /**
     * 批量修改门店品牌与集团品牌的关系
     *
     * @param relationMap
     * @return Integer
     * @Methods Name updateRelationList
     * @Create In 2015-9-15 By wangxuan
     */
    Integer updateRelationList(Map<String, Object> relationMap);

    /**
     * 根据门店与门店品牌查询门店品牌
     *
     * @param paramMap
     * @return List<PcmBrand>
     * @Methods Name getListBrandFromShopBrandRelation
     * @Create In 2015-9-21 By wangxuan
     */
    List<PcmBrand> getListBrandFromShopBrandRelation(Map<String, Object> paramMap);

    /**
     * 分页查询门店下的门店品牌
     *
     * @param dto
     * @return List<PcmBrandDto>
     * @Methods Name getPageBrandFromShopBrandRelation
     * @Create In 2015-11-17 By wangxuan
     */
    List<PcmBrandDto> getPageBrandFromShopBrandRelation(PcmShopBrandDto dto);

    Long getPageBrandCountFromShopBrandRelation(PcmShopBrandDto dto);

    /**
     * 根据sid查询下发给搜索的数据（修改品牌时）
     *
     * @param paraMap
     * @return List<PushSearchBrandDto>
     * @Methods Name pushSearchBrandBySid
     * @Create In 2015-10-9 By wangxuan
     */
    List<PushSearchBrandDto> pushSearchBrandBySid(Map<String, Object> paraMap);

    /**
     * 门店品牌与门店号下发给搜索查询
     *
     * @param paramMap
     * @return
     */
    List<HashMap<String, Object>> pushShopBrandToSearch(Map<String, Object> paramMap);

    /**
     * 商品导入终端 --由主数据获取品牌信息--查询集团品牌
     *
     * @param paraMap
     * @return List<BrandDataDto>
     * @Methods Name getBrandGroupData
     * @Create In 2015-10-20 By wangxuan
     */
    List<BrandDataDto> getBrandGroupData(Map<String, Object> paraMap);

    /**
     * 商品导入终端 --由主数据获取品牌信息--查询门店品牌
     *
     * @param paraMap
     * @return List<BrandDataDto>
     * @Methods Name getBrandData
     * @Create In 2015-10-20 By wangxuan
     */
    List<BrandDataDto> getBrandData(Map<String, Object> paraMap);

    /**
     * CMS
     *
     * @param paraMap
     * @return List<Map<String,Object>>
     * @Methods Name getBrandInfoByName
     * @Create In 2015-10-30 By wangxuan
     */
    List<Map<String, Object>> getBrandInfoByName(Map<String, Object> paraMap);

    /**
     * CMS
     *
     * @param list
     * @return List<Map<String,Object>>
     * @Methods Name getBrandListByIds
     * @Create In 2015-10-30 By wangxuan
     */
    List<Map<String, Object>> getBrandListByIds(List<String> list);

    /**
     * 搜索线上根据编码查询品牌
     *
     * @param paramMap
     * @return List<Map<String,Object>>
     * @Methods Name searchOnlineBrandByCode
     * @Create In 2015-11-10 By wangxuan
     */
    List<Map<String, Object>> searchOnlineBrandByCode(Map<String, Object> paramMap);

    /**
     * 搜索线上列出所有网站品牌
     *
     * @param paramMap
     * @return List<Map<String,Object>>
     * @Methods Name searchOnlineAllBrand
     * @Create In 2015-12-16 By wangxuan
     */
    List<Map<String, Object>> searchOnlineAllBrand(Map<String, Object> paramMap);

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
     * 根据门店品牌参数查询集团品牌信息
     *
     * @param para
     * @return List<Map<String,Object>>
     * @Methods Name getBrandGroupByBrandParam
     * @Create In 2015-12-9 By wangxuan
     */
    List<Map<String, Object>> getBrandGroupByBrandParam(Map<String, Object> para);

    /**
     * 根据条件查询最大的编码值
     *
     * @param dto
     * @return Map<String, Object>
     * @Methods Name selectMaxSidByParam
     * @Create In 2015-12-14 By wangxuan
     */
    Map<String, Object> selectMaxSidByParam(GenerateBrandCodeDto dto);

}