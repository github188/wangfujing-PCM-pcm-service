package com.wangfj.product.maindata.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.maindata.domain.entity.PcmProDetail;
import com.wangfj.product.maindata.domain.vo.CmsProductDto;
import com.wangfj.product.maindata.domain.vo.CmsProductResultDto;
import com.wangfj.product.maindata.domain.vo.PcmPhotoShoppeDto;
import com.wangfj.product.maindata.domain.vo.PcmProPhotoSubDto;
import com.wangfj.product.maindata.domain.vo.PcmProYeInfoDto;
import com.wangfj.product.maindata.domain.vo.PcmSearchSkuDto;
import com.wangfj.product.maindata.domain.vo.ProStanDto;
import com.wangfj.product.maindata.domain.vo.ProductEditDto;
import com.wangfj.product.maindata.domain.vo.ProductOnSellDto;
import com.wangfj.product.maindata.domain.vo.ProductPhotoDto;
import com.wangfj.product.maindata.domain.vo.PushPromotionDto;
import com.wangfj.product.maindata.domain.vo.SearchOnlineSkuInfoDto;
import com.wangfj.product.maindata.domain.vo.SkuPageDto;
import com.wangfj.product.maindata.domain.vo.TagsPriceStockDto;
import com.wangfj.util.mq.PublishDTO;

public interface PcmProDetailMapper extends BaseMapper<PcmProDetail> {

	List<Map<String, Object>> judgeproDetailRepeat(Map<String, Object> paramMap);

	List<PcmProYeInfoDto> getProYeInfoBySpuCode(Map<String, Object> paramMap);

	int deleteByPrimaryKey(Long sid);

	int insertSelective(PcmProDetail record);

	List<PublishDTO> selectSkuSidBySpuCodeColor(Map<String, Object> paramMap);

	PcmProDetail selectByPrimaryKey(Long sid);

	int updateByPrimaryKeySelective(PcmProDetail record);

	List<PcmProDetail> selectListByParam(Map<String, Object> paramMap);

	List<PcmProDetail> validSku(Map<String, Object> paramMap);

	List<SearchOnlineSkuInfoDto> searchOnlineSkuInfoByCode(Map<String, Object> paramMap);

	List<Map<String, Object>> selectPhotoBySpuColor(Map<String, Object> paramMap);

	List<Map<String, Object>> getSpuColorBySkuSids(Map<String, Object> paramMap);

	/**
	 * 修改图片状态
	 * 
	 * @Methods Name updatePhotoStatusByProSidColorSid
	 * @Create In 2015年10月14日 By kongqf
	 * @param record
	 * @return int
	 */
	int updatePhotoStatusByProSidColorSid(PcmProDetail record);

	int updateByPrimaryKey(PcmProDetail record);

	/**
	 * 验证SKU是否存在
	 * <p>
	 * 参数:<b>productSid(SPU编码)</b>和<b>colorCode</b>和<b>sizeCode</b>
	 * 
	 * @Methods Name selectPDByProductSidAndColorAndSizeIsExists
	 * @Create In 2015年7月14日 By wangsy
	 * @param map
	 * @return PcmProDetail
	 */
	PcmProDetail selectPDByProductSidAndColorAndSizeIsExists(Map map);

	List<Map<String, Object>> searchSku(PcmSearchSkuDto dto);

	Integer getProductCountByPara(SkuPageDto dto);

	List<SkuPageDto> selectProductPageByPara(SkuPageDto dto);

	List<SkuPageDto> selectProductPageByPara2(SkuPageDto dto);

	List<String> getskuSidList(SkuPageDto dto);

	List<ProductPhotoDto> selectProductPhotoByPara(ProductPhotoDto dto);

	List<PcmProDetail> selectProductEditByPara(ProductEditDto dto);

	List<Map<String, Object>> selectStanByPara(ProStanDto dto);

	List<PcmProPhotoSubDto> selectShoppeByPara(PcmPhotoShoppeDto dto);

	List<PushPromotionDto> getPushPromotionDtoBySid(Map<String, Object> paramMap);

	/**
	 * cms商品查询接口
	 * 
	 * @Methods Name cmsProductInfo
	 * @Create In 2015年10月29日 By yedong
	 * @param dto
	 * @return List<CmsProductResultDto>
	 */
	List<CmsProductResultDto> cmsProductInfo(CmsProductDto dto);

	List<CmsProductResultDto> cmsProductInfoBySid(List<String> list);

	List<Map<String, Object>> searchItemCodeBySkuCode(Map<String, Object> paramMap);

	List<Map<String, Object>> searchSkuCodeBySpuCode(Map<String, Object> paramMap);

	List<PcmProDetail> selectSkuItemByCode(Map<String, Object> paramMap);

	/**
	 * 根据SPU 色系 或 SKUSIDLIST查询 上架商品DTO
	 * 
	 * @Methods Name selectColorProBySpuColor
	 * @Create In 2015年11月30日 By zhangxy
	 * @param paramMap
	 * @return List<ProductOnSellDto>
	 */
	List<ProductOnSellDto> selectColorProBySpuColor(Map<String, Object> paramMap);

	Integer selectLimitValueByBrandCategory(ProductOnSellDto para);

	Integer updateSelectiveByList(Map<String, Object> map);

	/**
	 * 单品页商品漏出
	 * 
	 * @Methods Name selectTagsPriceStockBySku
	 * @Create In 2015年12月1日 By zhangxy
	 * @param map
	 * @return Map<String,Object>
	 */
	TagsPriceStockDto selectTagsPriceStockBySku(Map<String, Object> map);

	/**
	 * sku修改库存状态
	 * 
	 * @Methods Name updateStockStatusBySkuSid
	 * @Create In 2015年11月26日 By kongqf
	 * @param paramMap
	 * @return int
	 */
	int updateStockStatusBySkuSid(Map<String, Object> paramMap);

	/**
	 * 单品页查询库存价格 BY SKUCODE
	 * 
	 * @Methods Name selectStockAndPriceBySkuCode
	 * @Create In 2015-12-21 By wangc
	 * @param skuCode
	 * @return HashMap<String,Object>
	 */
	List<HashMap<String, Object>> selectStockAndPriceBySkuCode(Map<String, Object> para);
}