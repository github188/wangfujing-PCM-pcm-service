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
	 * @Methods Name getPageCountByPara
	 * @Create In 2015-8-3 By wangx
	 * @param pageDto
	 * @return Long
	 */
	Long getPageCountByPara(SelectPcmBrandPageDto pageDto);

	/**
	 * 分页查询
	 * 
	 * @Methods Name selectPageByPara
	 * @Create In 2015-8-3 By wangx
	 * @param pageDto
	 * @return List<PcmBrandDto>
	 */
	List<PcmBrandDto> selectPageByPara(SelectPcmBrandPageDto pageDto);

	/**
	 * 查询门店品牌及其集团品牌总数
	 * 
	 * @Methods Name getPageCountBrandAndBrandGroup
	 * @Create In 2015-9-8 By wangxuan
	 * @param paramMap
	 * @return Long
	 */
	Long getPageCountBrandAndBrandGroup(Map<String, Object> paramMap);

	/**
	 * 分页查询门店品牌及其集团品牌
	 * 
	 * @Methods Name selectPageBrandAndBrandGroup
	 * @Create In 2015-9-8 By wangxuan
	 * @param paramMap
	 * @return List<PcmBrandDto>
	 */
	List<PcmBrandDto> selectPageBrandAndBrandGroup(Map<String, Object> paramMap);

	/**
	 * 查询（非分页，模糊）
	 * 
	 * @Methods Name selectListByParaSelective
	 * @Create In 2015-8-18 By wangx
	 * @param pageDto
	 * @return List<PcmBrand>
	 */
	List<PcmBrand> selectListByParaSelective(SelectPcmBrandPageDto pageDto);

	/**
	 * 查询部分信息（模糊）
	 *
	 * @Methods Name selectListByParaSelective
	 * @Create In 2016-02-22 By wangxuan
	 * @param pageDto
	 * @return List<PcmBrandPartDto>
	 */
	List<PcmBrandPartDto> selectListPartInfoByParam(SelectPcmBrandPageDto pageDto);

    /**
     * 查询部分信息（模糊）
     *
     * @Methods Name selectListByParaSelective
     * @Create In 2016-03-11 By wangxuan
     * @param pageDto
     * @return Integer
     */
    Integer selectCountPartInfoByParam(SelectPcmBrandPageDto pageDto);

	/**
	 * 删除门店品牌与集团品牌的关系
	 * 
	 * @Methods Name deleteRelation
	 * @Create In 2015-8-18 By wangx
	 * @param paramMap
	 * @return Integer
	 */
	Integer deleteRelation(Map<String, Object> paramMap);

	/**
	 * 分页查找(移动工作台调用主数据获取品牌信息)
	 * 
	 * @Methods Name getPageBrandForPad
	 * @Create In 2015-8-26 By wangxuan
	 * @param para
	 * @return List<PadBrandResultDto>
	 */
	List<PadBrandResultDto> getPageBrandForPad(Map<String, Object> para);

	/**
	 * 下发门店品牌分页查找
	 * 
	 * @Methods Name pushPageBrandData
	 * @Create In 2015-8-27 By wangxuan
	 * @param para
	 * @return List<PushBrandDto>
	 */
	List<PushBrandDto> pushPageBrandData(Map<String, Object> para);

	/**
	 * 搜索查询门店品牌信息
	 * 
	 * @Methods Name searchBrandByParam
	 * @Create In 2015-8-27 By wangxuan
	 * @param para
	 * @return List<HashMap<String,Object>>
	 */
	List<HashMap<String, Object>> searchBrandByParam(Map<String, Object> para);

	List<PcmBrand> selectBrandByShopSku(Map<String, Object> paramMap);

	/**
	 * 批量修改门店品牌与集团品牌的关系
	 * 
	 * @Methods Name updateRelationList
	 * @Create In 2015-9-15 By wangxuan
	 * @param relationMap
	 * @return Integer
	 */
	Integer updateRelationList(Map<String, Object> relationMap);

	/**
	 * 根据门店与门店品牌查询门店品牌
	 * 
	 * @Methods Name getListBrandFromShopBrandRelation
	 * @Create In 2015-9-21 By wangxuan
	 * @param paramMap
	 * @return List<PcmBrand>
	 */
	List<PcmBrand> getListBrandFromShopBrandRelation(Map<String, Object> paramMap);

	/**
	 * 分页查询门店下的门店品牌
	 * 
	 * @Methods Name getPageBrandFromShopBrandRelation
	 * @Create In 2015-11-17 By wangxuan
	 * @param dto
	 * @return List<PcmBrandDto>
	 */
	List<PcmBrandDto> getPageBrandFromShopBrandRelation(PcmShopBrandDto dto);

	Long getPageBrandCountFromShopBrandRelation(PcmShopBrandDto dto);

	/**
	 * 根据sid查询下发给搜索的数据（修改品牌时）
	 * 
	 * @Methods Name pushSearchBrandBySid
	 * @Create In 2015-10-9 By wangxuan
	 * @param paraMap
	 * @return List<PushSearchBrandDto>
	 */
	List<PushSearchBrandDto> pushSearchBrandBySid(Map<String, Object> paraMap);

	/**
	 * 商品导入终端 --由主数据获取品牌信息--查询集团品牌
	 * 
	 * @Methods Name getBrandGroupData
	 * @Create In 2015-10-20 By wangxuan
	 * @param paraMap
	 * @return List<BrandDataDto>
	 */
	List<BrandDataDto> getBrandGroupData(Map<String, Object> paraMap);

	/**
	 * 商品导入终端 --由主数据获取品牌信息--查询门店品牌
	 * 
	 * @Methods Name getBrandData
	 * @Create In 2015-10-20 By wangxuan
	 * @param paraMap
	 * @return List<BrandDataDto>
	 */
	List<BrandDataDto> getBrandData(Map<String, Object> paraMap);

	/**
	 * CMS
	 * 
	 * @Methods Name getBrandInfoByName
	 * @Create In 2015-10-30 By wangxuan
	 * @param paraMap
	 * @return List<Map<String,Object>>
	 */
	List<Map<String, Object>> getBrandInfoByName(Map<String, Object> paraMap);

	/**
	 * CMS
	 * 
	 * @Methods Name getBrandListByIds
	 * @Create In 2015-10-30 By wangxuan
	 * @param list
	 * @return List<Map<String,Object>>
	 */
	List<Map<String, Object>> getBrandListByIds(List<String> list);

	/**
	 * 搜索线上根据编码查询品牌
	 * 
	 * @Methods Name searchOnlineBrandByCode
	 * @Create In 2015-11-10 By wangxuan
	 * @param paramMap
	 * @return List<Map<String,Object>>
	 */
	List<Map<String, Object>> searchOnlineBrandByCode(Map<String, Object> paramMap);

	/**
	 * 搜索线上列出所有网站品牌
	 * 
	 * @Methods Name searchOnlineAllBrand
	 * @Create In 2015-12-16 By wangxuan
	 * @param paramMap
	 * @return List<Map<String,Object>>
	 */
	List<Map<String, Object>> searchOnlineAllBrand(Map<String, Object> paramMap);

	/**
	 * 搜索线上下发品牌信息
	 * 
	 * @Methods Name pushSearchOnlineBrandBySid
	 * @Create In 2015-11-10 By wangxuan
	 * @param paramMap
	 * @return List<Map<String,Object>>
	 */
	List<Map<String, Object>> pushSearchOnlineBrandBySid(Map<String, Object> paramMap);

	/**
	 * 根据门店品牌参数查询集团品牌信息
	 * 
	 * @Methods Name getBrandGroupByBrandParam
	 * @Create In 2015-12-9 By wangxuan
	 * @param para
	 * @return List<Map<String,Object>>
	 */
	List<Map<String, Object>> getBrandGroupByBrandParam(Map<String, Object> para);

	/**
	 * 根据条件查询最大的编码值
	 * 
	 * @Methods Name selectMaxSidByParam
	 * @Create In 2015-12-14 By wangxuan
	 * @param dto
	 * @return Map<String, Object>
	 */
	Map<String, Object> selectMaxSidByParam(GenerateBrandCodeDto dto);

}