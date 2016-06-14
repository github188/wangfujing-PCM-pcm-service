package com.wangfj.product.maindata.persistence;

import java.util.List;
import java.util.Map;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.maindata.domain.entity.PcmShoppeProduct;
import com.wangfj.product.maindata.domain.vo.OmsResProInfoDto;
import com.wangfj.product.maindata.domain.vo.OmsShoppeProductDto;
import com.wangfj.product.maindata.domain.vo.OmsShoppeProductResultDto;
import com.wangfj.product.maindata.domain.vo.PcmProByOrgCodeDto;
import com.wangfj.product.maindata.domain.vo.PcmProPhotoDto;
import com.wangfj.product.maindata.domain.vo.PcmProPhotoTwoDto;
import com.wangfj.product.maindata.domain.vo.PcmProRetPhotoDto;
import com.wangfj.product.maindata.domain.vo.PcmProSearchDto;
import com.wangfj.product.maindata.domain.vo.PcmPublishSapErpDto;
import com.wangfj.product.maindata.domain.vo.PcmSapInfoToSapSourcePis;
import com.wangfj.product.maindata.domain.vo.PcmSearchOnlineProDto;
import com.wangfj.product.maindata.domain.vo.ProPadDto;
import com.wangfj.product.maindata.domain.vo.ProductPageDto;
import com.wangfj.product.maindata.domain.vo.ProductPicInfoResDto;
import com.wangfj.product.maindata.domain.vo.PushToPcmEfutureERPFromPcmDto;
import com.wangfj.product.maindata.domain.vo.ShoProInfoFormPcmToPisDto;
import com.wangfj.product.maindata.domain.vo.ShoppeProductDto;

public interface PcmShoppeProductMapper extends BaseMapper<PcmShoppeProduct> {
	List<Long> getSidListBySapProCode(Map<String, Object> paramMap);

	/**
	 * 通过SKU或Spu查询专柜商品Sid
	 *
	 * @param paramMap
	 * @return List<String>
	 * @Methods Name getProSidBySkuOrSpu
	 * @Create In 2016年4月8日 By yedong
	 */
	List<String> getSidListInfo22();

	List<String> getProSidBySkuOrSpu(Map<String, Object> paramMap);

	Integer searchSpCountByStore(Map<String, Object> paramMap);

	List<Map<String, Object>> picOldToNew(Map<String, Object> paramMap);

	OmsResProInfoDto omsGetResProInfo(Map<String, Object> paramMap);

	List<PcmProByOrgCodeDto> searchSpByStore(Map<String, Object> paramMap);

	List<Map<String, Object>> getStoreCodeByProCode(Map<String, Object> paramMap);

	List<PcmPublishSapErpDto> pcmPublishSapErpMap(Map<String, Object> paramMap);

	List<PcmSapInfoToSapSourcePis> pcmPublishSapErpSourcePis(Map<String, Object> paraMap);

	List<Map<String, Object>> getGyCateToSapInfo(Map<String, Object> paraMap);

	List<Map<String, Object>> getTjCateToSapInfo(Map<String, Object> paraMap);

	ProPadDto proInfoToPad(Map<String, Object> paramMap);

	List<ProductPageDto> getProInfoToFj(Map<String, Object> paramMap);

	List<Map<String, Object>> judgeShoppeProductRepeat(Map<String, Object> paramMap);

	List<ProductPicInfoResDto> getProductInfoBySpuAndColor(Map<String, Object> paramMap);

	List<PcmSearchOnlineProDto> searchOnlinePageProInfo(Map<String, Object> paramMap);

	List<PcmProSearchDto> getProInfoPulishSearch(Map<String, Object> paramMap);

	int searchOnlinePageProCount(Map<String, Object> paramMap);

	int deleteByPrimaryKey(Long sid);

	int insertSelective(PcmShoppeProduct record);

	PcmShoppeProduct selectByPrimaryKey(Long sid);

	PcmShoppeProduct selectRateCodeByProSid(String shoppeProSid);

	int updateByPrimaryKeySelective(PcmShoppeProduct record);

	int updateByCodeSelective(PcmShoppeProduct record);

	int updateByPrimaryKey(PcmShoppeProduct record);

	int updateBySku(PcmShoppeProduct record);

	/**
	 * @param storeCode
	 * @return List<PcmProPhotoDto>
	 * @Methods Name itemsListSelect
	 * @Create In 2015年10月13日 By yedong
	 */
	List<PcmProPhotoDto> itemsListSelect(PcmProRetPhotoDto dto);

	/**
	 * @param dto
	 * @return List<PcmProPhotoDto>
	 * @Methods Name itemsListSelectTwo
	 * @Create In 2016-1-13 By wangc
	 */
	List<PcmProPhotoTwoDto> itemsListSelectTwo(PcmProRetPhotoDto dto);

	/**
	 * @param storeCode
	 * @return List<PcmProPhotoDto>
	 * @Methods Name itemsListByPhotoPlan
	 * @Create In 2015年11月30日 By yedong
	 */
	List<PcmProPhotoDto> itemsListByPhotoPlan(PcmProRetPhotoDto dto);

	/**
	 * @param dto
	 * @return Integer
	 * @Methods Name itemsCountByPhotoPlan
	 * @Create In 2015年11月30日 By yedong
	 */
	Integer itemsCountByPhotoPlan(PcmProRetPhotoDto dto);

	String getPhotoStatusBySpuColor(Map<String, Object> paramMap);

	/**
	 * 修改SKU
	 *
	 * @param paramMap
	 * @return int
	 * @Methods Name updateSkuByParam
	 * @Create In 2015年9月17日 By yedong
	 */
	int updateSkuByParam(Map<String, Object> paramMap);

	/**
	 * 专柜商品下发MQ
	 *
	 * @return List<Map<String,Object>>
	 * @Methods Name selectShoppePro
	 * @Create In 2015年7月15日 By zhangxy
	 */
	List<Map<String, Object>> selectShoppePro(Map<String, Object> para);

	/**
	 * getCount_专柜商品下发MQ
	 *
	 * @return List<Map<String,Object>>
	 * @Methods Name getCountShoppePro
	 * @Create In 2015年7月15日 By zhangxy
	 */
	Integer getCountShoppePro();

	/**
	 * 按条件分页查询专柜商品信息
	 *
	 * @param para
	 * @return List<Map<String,Object>>
	 * @Methods Name selectProductPageByPara
	 * @Create In 2015年7月15日 By zhangxy
	 */
	List<Map<String, Object>> selectProductPageByPara(Map<String, Object> para);

	/**
	 * 按条件分页查询专柜商品信息(销售用)
	 *
	 * @param para
	 * @return List<Map<String,Object>>
	 * @Methods Name selectProductPageByParaForOms
	 * @Create In 2015年7月15日 By zhangxy
	 */
	List<Map<String, Object>> selectProductPageByParaForOms(Map<String, Object> para);

	List<Map<String, Object>> selectProductPageByPara1(List<Long> array);

	/**
	 * 按条件查询专柜商品信息计数
	 *
	 * @param para
	 * @return Integer
	 * @Methods Name getProductCountByPara
	 * @Create In 2015年7月15日 By zhangxy
	 */
	Integer getProductCountByPara(Map<String, Object> para);

	/**
	 * 按条件查询专柜商品信息计数2
	 *
	 * @param para
	 * @return Integer
	 * @Methods Name getProductCountByPara2
	 * @Create In 2016-1-14 By wangc
	 */
	Integer getProductCountByPara2(Map<String, Object> para);

	/**
	 * 验证专柜商品是否存在
	 * <p/>
	 * 参数: <b>productDetailSid</b>(SKU编码)和 <b>supplierCode</b>(供应商编码)和
	 * <b>counterCode</b> (专柜编码)
	 *
	 * @param map
	 * @return PcmShoppeProduct
	 * @Methods Name selectSpPByPDSidAndSCAndCCIsExists
	 * @Create In 2015年7月14日 By wangsy
	 */
	PcmShoppeProduct selectSpPByPDSidAndSCAndCCIsExists(Map map);

	/**
	 * 查询专柜商品信息(名称、是否可售、是否可包装、是否管库存、是否负库存销售)
	 *
	 * @param paramMap
	 * @return List<Map<String,Object>>
	 * @Methods Name selectStockInFo
	 * @Create In 2015年8月3日 By yedong
	 */
	List<Map<String, Object>> selectStockInFo(Map<String, Object> paramMap);

	/**
	 * 一品多供应商关系上传 查询专柜商品信息
	 *
	 * @param paramMap
	 * @return List<PcmShoppeProduct>
	 * @Methods Name selectShoppeProductByShoppeProCode
	 * @Create In 2015-8-28 By wangxuan
	 */
	List<PcmShoppeProduct> selectShoppeProductByShoppeProCode(Map<String, Object> paramMap);

	List<ShoppeProductDto> selectProPageBySku(ShoppeProductDto dto);

	Integer getCountProPageBySku(ShoppeProductDto dto);

	Map<String, Object> getStroeCodeByShoppePro(PcmShoppeProduct entity);

	Map<String, Long> selectSidsByPro(Map<String, Object> map);

	Map<String, Object> getProAndBrandSidByCode(Map<String, Object> map);

	/**
	 * 根据门店品牌sid查询改门店品牌下的专柜商品的数量
	 *
	 * @param paramMap
	 * @return Integer
	 * @Methods Name getCountShoppeProductByBrandSid
	 * @Create In 2015-12-29 By wangxuan
	 */
	Integer getCountShoppeProductByBrandSid(Map<String, Object> paramMap);

	/**
	 * 获取参加活动的专柜商品信息
	 *
	 * @param paraMap
	 * @return List<PcmSearchOnlineProDto>
	 * @Methods Name selectProByActiveId
	 * @Create In 2016-1-11 By wangc
	 */
	List<PcmSearchOnlineProDto> selectProByActiveId(Map<String, Object> paraMap);

	/**
	 * 获取参加活动的专柜商品数量
	 *
	 * @param paraMap
	 * @return Integer
	 * @Methods Name getProCountByActiveId
	 * @Create In 2016-1-11 By wangc
	 */
	Integer getProCountByActiveId(Map<String, Object> paraMap);

	List<Map<String, Object>> selectSAPProList(Map<String, Object> para);

	/**
	 * 按条件查询专柜商品信息计数
	 *
	 * @param pageDto
	 * @return Integer
	 * @Methods Name findProductCountByPara
	 * @Create In 2016年03月26日 By zhangxy
	 */
	Integer findProductCountByPara(ProductPageDto pageDto);

	/**
	 * 按条件分页查询专柜商品信息
	 *
	 * @param pageDto
	 * @return List<Map<String,Object>>
	 * @Methods Name findProductPageByPara
	 * @Create In 2016年03月26日 By zhangxy
	 */
	List<Map<String, Object>> findProductPageByPara(ProductPageDto pageDto);

	/**
	 * 按条件查询单个专柜商品的基本信息（优化）
	 *
	 * @param pageDto
	 * @return List<ProductPageDto>
	 * @Methods Name findShoppeProductBaseInfoByPara
	 * @Create In 2016年03月28日 By wangxuan
	 */
	List<ProductPageDto> findShoppeProductBaseInfoByPara(ProductPageDto pageDto);

	/**
	 * 按条件查询单个专柜商品的基础数据信息（优化）
	 *
	 * @param pageDto
	 * @return List<ProductPageDto>
	 * @Methods Name findShoppeProductBasicDataByPara
	 * @Create In 2016年03月28日 By wangxuan
	 */
	List<ProductPageDto> findShoppeProductBasicDataByPara(ProductPageDto pageDto);

	/**
	 * 按条件查询单个专柜商品的合同等相关信息（优化）
	 *
	 * @param pageDto
	 * @return List<ProductPageDto>
	 * @Methods Name findShoppeProductContractInfoByPara
	 * @Create In 2016年03月28日 By wangxuan
	 */
	List<ProductPageDto> findShoppeProductContractInfoByPara(ProductPageDto pageDto);

	/**
	 * 按条件查询单个专柜商品的Sku,Spu等相关信息（优化）
	 *
	 * @param pageDto
	 * @return List<ProductPageDto>
	 * @Methods Name findShoppeProductSkuSpuInfoByPara
	 * @Create In 2016年03月29日 By wangxuan
	 */
	List<ProductPageDto> findShoppeProductSkuSpuInfoByPara(ProductPageDto pageDto);

	/**
	 * 根据sid列表查询需要下发门店的专柜商品信息
	 *
	 * @param para
	 * @return List<Map<String,Object>>
	 * @Methods Name selectProPageByParamForPis
	 * @Create In 2016-3-28 By wangc
	 */
	List<PushToPcmEfutureERPFromPcmDto> selectProPageByParamForPis(Map<String, Object> para);

	/**
	 * 由主数据获取专柜商品信息
	 *
	 * @param para
	 * @return List<Map<String,Object>>
	 * @Methods Name getShoProInfoFormPcmToPis
	 * @Create In 2016-3-29 By wangc
	 */
	List<ShoProInfoFormPcmToPisDto> getShoProInfoFormPcmToPis(Map<String, Object> para);

	/**
	 * 有主数据获取专柜商品信息数量
	 *
	 * @param para
	 * @return Integer
	 * @Methods Name getShoProCountFormPcmToPis
	 * @Create In 2016-3-29 By wangc
	 */
	Integer getShoProCountFormPcmToPis1(Map<String, Object> para);

	/**
	 * 按条件查询专柜商品基本信息及统计分类
	 *
	 * @param pageDto
	 * @return List<ProductPageDto>
	 * @Methods Name findShoppeProductAndCategoryByPara
	 * @Create In 2016年04月14日 By wangxuan
	 */
	List<ProductPageDto> findShoppeProductAndCategoryByPara(ProductPageDto pageDto);

	/**
	 * Oms根据专柜商品编码查询工业分类
	 *
	 * @param dto
	 * @return
	 */
	OmsShoppeProductResultDto findIndustryCategoryByParaForOms(OmsShoppeProductDto dto);
}