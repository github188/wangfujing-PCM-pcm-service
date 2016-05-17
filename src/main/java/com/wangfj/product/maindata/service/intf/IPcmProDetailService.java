package com.wangfj.product.maindata.service.intf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wangfj.core.framework.base.page.Page;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.product.maindata.domain.entity.PcmProDetail;
import com.wangfj.product.maindata.domain.vo.CmsProductDto;
import com.wangfj.product.maindata.domain.vo.CmsProductResultDto;
import com.wangfj.product.maindata.domain.vo.PackDescDto;
import com.wangfj.product.maindata.domain.vo.PcmPhotoShoppeDto;
import com.wangfj.product.maindata.domain.vo.PcmProDetailDto;
import com.wangfj.product.maindata.domain.vo.PcmProPhotoDto;
import com.wangfj.product.maindata.domain.vo.PcmProPhotoSubDto;
import com.wangfj.product.maindata.domain.vo.PcmProPhotoTwoDto;
import com.wangfj.product.maindata.domain.vo.PcmProRetPhotoDto;
import com.wangfj.product.maindata.domain.vo.PcmProYeInfoDto;
import com.wangfj.product.maindata.domain.vo.ProSkuSpuPublishDto;
import com.wangfj.product.maindata.domain.vo.ProStanDto;
import com.wangfj.product.maindata.domain.vo.ProductEditDto;
import com.wangfj.product.maindata.domain.vo.ProductOnSellDto;
import com.wangfj.product.maindata.domain.vo.ProductPhotoDto;
import com.wangfj.product.maindata.domain.vo.ProductPicInfoResDto;
import com.wangfj.product.maindata.domain.vo.PushPromotionDto;
import com.wangfj.product.maindata.domain.vo.SkuPageCacheDto;
import com.wangfj.product.maindata.domain.vo.SkuPageDto;
import com.wangfj.product.maindata.domain.vo.TagsPriceStockDto;
import com.wangfj.util.mq.PublishDTO;

public interface IPcmProDetailService {

	/**
	 * sku信息基础查询
	 * 
	 * @Methods Name selectListByParam
	 * @Create In 2016年3月10日 By yedong
	 * @param entity
	 * @return List<PcmProDetail>
	 */
	public List<PcmProDetail> selectListByParam(PcmProDetail entity);

	/**
	 * 单品页接口
	 * 
	 * @Methods Name getProYeInfoBySpuCode
	 * @Create In 2015年12月21日 By yedong
	 * @param paramMap
	 * @return PcmProYeInfoDto
	 */
	PcmProYeInfoDto getProYeInfoBySpuCode(String key, Map<String, Object> paramMap);

	/**
	 * 由主数据获取待拍照商品信息上线计划
	 * 
	 * @Methods Name itemsListByPhotoPlan
	 * @Create In 2015年9月30日 By yedong
	 * @param dto
	 * @return ProductPhotoDto
	 * @throws Exception
	 */
	public Page<PcmProPhotoDto> itemsListByPhotoPlan(PcmProRetPhotoDto dto) throws Exception;

	/**
	 * 多条件分页查询SKU信息
	 * 
	 * @Methods Name selectSkuPage
	 * @Create In 2015年8月17日 By zhangxy
	 * @param dto
	 * @return Page<PcmProDetail>
	 */
	Page<SkuPageDto> selectSkuPage(SkuPageDto dto);

	/**
	 * 多条件分页查询SKU信息
	 * 
	 * @Methods Name selectSkuPage
	 * @Create In 2015年8月17日 By zhangxy
	 * @param dto
	 * @return Page<PcmProDetail>
	 */
	SkuPageCacheDto selectSkuPageCache(String proPage, SkuPageDto dto);

	public List<ProductPhotoDto> selectProductPhotoByPara(ProductPhotoDto dto);

	/**
	 * 由主数据获取尺码信息
	 * 
	 * @Methods Name selectStan
	 * @Create In 2015年10月8日 By yedong
	 * @param dto
	 * @return List<ProStanDto>
	 */
	public List<ProStanDto> selectStan(List<ProStanDto> dtoList);

	/**
	 * 根据(色系,产品编码)or skuList 查询SKU
	 */
	public List<ProductOnSellDto> selectSkuBySpuColor(String spuCode, String color, List sids);

	public List<PcmProPhotoDto> selectProPhotoByPara(PcmProRetPhotoDto dto) throws Exception;

	public List<PcmProPhotoTwoDto> selectProPhotoByParaTwo(PcmProRetPhotoDto dto) throws Exception;

	/**
	 * 回传商品编辑到主数据
	 * 
	 * @Methods Name updateProductEdit
	 * @Create In 2015年10月8日 By yedong
	 * @param dto
	 * @throws Exception
	 */
	public void updateProductEdit(ProductEditDto dto) throws BleException;

	/**
	 * 回传商品编辑到主数据2
	 * 
	 * @Methods Name updateProductEditTwo
	 * @Create In 2016-1-14 By wangc
	 * @param dto
	 * @throws BleException
	 *             void
	 */
	public void updateProductEditTwo(ProductEditDto dto) throws BleException;

	/**
	 * 由拍照系统上传精包装信息
	 * 
	 * @Methods Name updatePackDesc
	 * @Create In 2015年10月16日 By zhangxy
	 * @param dto
	 * @throws Exception
	 */
	void updatePackDesc(PackDescDto dto) throws BleException;

	/**
	 * 根据产品ID，色系编码和尺码查询专柜信息
	 * 
	 * @Methods Name shoppePhotoByParam
	 * @Create In 2015年10月24日 By yedong
	 * @param dto
	 * @return List<PcmProPhotoSubDto>
	 */
	public List<PcmProPhotoSubDto> shoppePhotoByParam(PcmPhotoShoppeDto dto);

	/**
	 * cms查询商品信息
	 * 
	 * @Methods Name cmsSelectProductInfo
	 * @Create In 2015年10月29日 By yedong
	 * @param dto
	 * @return List<CmsProductResultDto>
	 */
	public List<CmsProductResultDto> cmsSelectProductInfo(CmsProductDto dto);

	public List<CmsProductResultDto> cmsSelectProductInfoBySid(List<String> list);

	/**
	 * SKU启用状态修改
	 * 
	 * @Methods Name updateProDetailDisable
	 * @Create In 2015年11月3日 By yedong
	 * @param entity
	 * @return boolean
	 */
	public List<PublishDTO> updateProDetailDisable(List<PcmProDetail> list);

	/**
	 * SKU上下架状态修改
	 * 
	 * @Methods Name updateProDetailDisable
	 * @Create In 2015年11月3日 By zhangxy
	 * @param sid
	 * @return boolean
	 */
	public ProSkuSpuPublishDto updateProDetailSell(ProductOnSellDto dto) throws BleException;

	/**
	 * 根据SKU编码查询所有同SPU下的所有SKU
	 * 
	 * @Methods Name selectSkuItemByCode
	 * @Create In 2015年11月16日 By yedong
	 * @param paramMap
	 * @return List<PcmProDetail>
	 */
	public List<PcmProDetail> selectSkuItemByCode(Map<String, Object> paramMap);

	/**
	 * SKU换色码规码/添加SKU
	 * 
	 * @Methods Name insertOrUpdateSku
	 * @Create In 2015年11月16日 By yedong
	 * @param skuList
	 *            void
	 */
	public ProSkuSpuPublishDto insertOrUpdateSku(List<PcmProDetailDto> skuList);

	/**
	 * sku添加
	 * 
	 * @Methods Name insertSkuInfo
	 * @Create In 2015年11月17日 By yedong
	 * @param skuList
	 * @return Map<String,Object>
	 */
	public Map<String, Object> insertSkuInfo(List<PcmProDetailDto> skuList);

	/**
	 * 根据SKUSID修改SKU信息
	 * 
	 * @Methods Name updateSkuInfoBySid
	 * @Create In 2015年11月25日 By yedong
	 * @param dto
	 *            void
	 */
	public void updateSkuInfoBySid(PcmProDetailDto dto);

	/**
	 * 单品页商品漏出
	 * 
	 * @Methods Name selectTagsPriceStockBySku
	 * @Create In 2015年12月1日 By zhangxy
	 * @param map
	 * @return TagsPriceStockDto
	 */
	public TagsPriceStockDto selectTagsPriceStockBySku(Map<String, Object> map);

	/**
	 * 单品页查询价格库存 BY SKUCODE
	 * 
	 * @Methods Name selectStockAndPriceByProDetail
	 * @Create In 2015-12-21 By wangc
	 * @param map
	 * @return Map<String,Object>
	 */
	public HashMap<String, Object> selectStockAndPriceByProDetail(Map<String, Object> map);

	Map<String, Object> getProYeBySkuOrSpu(Map<String, Object> paramMap);

	List<ProductPicInfoResDto> getProductInfoBySpuAndColor(Map<String, Object> paramMap);

	public List<PcmProDetail> selectSkuListByParam(Map<String, Object> paramMap);

	List<PushPromotionDto> getPushPromotionDtoBySid(Map<String, Object> paramMap);
}
