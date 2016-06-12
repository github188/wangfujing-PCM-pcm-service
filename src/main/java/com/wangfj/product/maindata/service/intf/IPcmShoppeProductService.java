package com.wangfj.product.maindata.service.intf;

import java.util.List;
import java.util.Map;

import com.wangfj.core.framework.base.page.Page;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.product.maindata.domain.entity.PcmShoppeProduct;
import com.wangfj.product.maindata.domain.entity.ShoProInfoFormPcmToPis;
import com.wangfj.product.maindata.domain.vo.ErpProductDto;
import com.wangfj.product.maindata.domain.vo.OmsResProInfoDto;
import com.wangfj.product.maindata.domain.vo.OmsShoppeProductDto;
import com.wangfj.product.maindata.domain.vo.OmsShoppeProductReturnDto;
import com.wangfj.product.maindata.domain.vo.PcmProByOrgCodeDto;
import com.wangfj.product.maindata.domain.vo.PcmProSearchDto;
import com.wangfj.product.maindata.domain.vo.PcmPublishSapErpDto;
import com.wangfj.product.maindata.domain.vo.PcmSapInfoToSapSourcePis;
import com.wangfj.product.maindata.domain.vo.ProPadDto;
import com.wangfj.product.maindata.domain.vo.ProPageDto;
import com.wangfj.product.maindata.domain.vo.ProductPageDto;
import com.wangfj.product.maindata.domain.vo.PublishShoppeProductFromPcmDto;
import com.wangfj.product.maindata.domain.vo.PushShoppeProFromPcmDto;
import com.wangfj.product.maindata.domain.vo.PushToPcmEfutureERPFromPcmDto;
import com.wangfj.product.maindata.domain.vo.SapProListDto;
import com.wangfj.product.maindata.domain.vo.ShoProInfoFormPcmToPisDto;
import com.wangfj.product.maindata.domain.vo.ShoppeProductDto;
import com.wangfj.product.maindata.domain.vo.UpdateProductInfoDto;
import com.wangfj.util.mq.PublishDTO;

/**
 * @Class Name IPcmShoppeProductService
 * @Author zhangxy
 * @Create In 2015年7月10日
 */
public interface IPcmShoppeProductService {
	public List<Long> getSidListBySapProCode(List<SapProListDto> dtoList);

	/**
	 * 导入终端上传电商商品下发电商
	 *
	 * @param paraMap
	 * @return List<PcmPublishSapErpDto>
	 * @Methods Name pcmPublishSapErpSourcePis
	 * @Create In 2016年5月24日 By wangc
	 */
	public List<PcmSapInfoToSapSourcePis> pcmPublishSapErpSourcePis(Map<String, Object> paraMap);

	public Integer searchSpCountByStore(Map<String, Object> paramMap);

	public List<PcmProByOrgCodeDto> searchSpByStore(Map<String, Object> paramMap);

	public ProPadDto proInfoToPad(Map<String, Object> paramMap);

	/**
	 * 删除缓存
	 *
	 * @param code
	 * @Methods Name cacheDelete
	 * @Create In 2015年10月8日 By zhangxy
	 */
	public void cacheDelete(String code);

	/**
	 * 搜索下发商品信息
	 *
	 * @param paramMap
	 * @return List<PcmProSearchDto>
	 * @Methods Name getProInfoPulishSearch
	 * @Create In 2016年3月8日 By yedong
	 */
	public List<PcmProSearchDto> getProInfoPulishSearch(Map<String, Object> paramMap);

	/**
	 * 查询专柜商品信息By专柜商品
	 *
	 * @param proDto
	 * @return List<PcmShoppeProduct>
	 * @Methods Name selectShoppeProductInfo
	 * @Create In 2016年2月17日 By yedong
	 */
	public List<PcmShoppeProduct> selectShoppeProductInfo(PcmShoppeProduct proDto);

	/**
	 * 按条件查询专柜商品基本信息及统计分类
	 *
	 * @param pageDto
	 * @return List<ProductPageDto>
	 * @throws Exception
	 * @Methods Name findShoppeProductAndCategoryByPara
	 * @Create In 2016年04月14日 By yedong
	 */
	List<ProductPageDto> findShoppeProductAndCategoryByPara(ProductPageDto pageDto);

	/**
	 * 按条件查询单个专柜商品信息(含条码)-优化
	 *
	 * @param pageDto
	 * @return ProductPageDto
	 * @throws Exception
	 * @Methods Name selectShoppeProductByPara
	 * @Create In 2015年7月15日 By yedong
	 */
	public ProductPageDto selectShoppeProductByPara(ProductPageDto pageDto) throws BleException;

	/**
	 * 查询专柜商品信息
	 *
	 * @param shoppeProSid
	 * @return Map<String,Object>
	 * @Methods Name selectStockInfo
	 * @Create In 2015年8月3日 By yedong
	 */
	public Map<String, Object> selectStockInfo(String shoppeProSid);

	public Map<String, Object> selectStockInfo(String shoppeProSid, String sapProductCode);

	/**
	 * 下发DTO数据处理
	 *
	 * @param list
	 * @return List<PublishShoppeProductFromPcmDto>
	 * @Methods Name pushDtoProcess
	 * @Create In 2015年8月19日 By zhangxy
	 */
	public List<PublishShoppeProductFromPcmDto> pushDtoProcess(List<ProductPageDto> list,
			Integer type);

	public List<PushShoppeProFromPcmDto> pushErpDtoProcess(List<ErpProductDto> list, Integer type);

	/**
	 * 下发 大码->专柜商品数据处理 --促销
	 *
	 * @param list
	 * @return List<PublishShoppeProductFromPcmDto>
	 * @Methods Name pushDtoProcess
	 * @Create In 2015年8月19日 By zhangxy
	 */
	public List<PushShoppeProFromPcmDto> pushDtoProcessToCx(List<String> list, Integer type);

	/**
	 * 根据SKU查询专柜商品
	 *
	 * @param dto
	 * @return PcmShoppeProduct
	 * @Methods Name selectProPageBySku
	 * @Create In 2015年9月21日 By zhangxy
	 */
	public Page<ShoppeProductDto> selectProPageBySku(ShoppeProductDto dto);

	/**
	 * 根据SKU查询专柜商品(缓存)
	 *
	 * @param dto
	 * @return PcmShoppeProduct
	 * @Methods Name selectProPageCache
	 * @Create In 2015年9月21日 By zhangxy
	 */
	public ProPageDto selectProPageCache(String proPage, ShoppeProductDto dto);

	/**
	 * 根据专柜商品编码查询扣率码
	 *
	 * @param shoppeProSid
	 * @return PcmShoppeProduct
	 * @Methods Name selectRateCodeByProSid
	 * @Create In 2015年10月13日 By kongqf
	 */
	public PcmShoppeProduct selectRateCodeByProSid(String shoppeProSid);

	/**
	 * 修改商品基本属性
	 *
	 * @param dto
	 * @Methods Name updateProductInfo
	 * @Create In 2015年10月20日 By zhangxy
	 */
	public void updateProductInfo(UpdateProductInfoDto dto) throws BleException;

	/**
	 * 专柜商品启用状态修改
	 *
	 * @param list
	 * @return
	 * @Methods Name updateProductStatusInfo
	 * @Create In 2015年12月8日 By zhangdongliang
	 */
	public List<PublishDTO> updateProductStatusInfo(List<UpdateProductInfoDto> list);

	/**
	 * 按条件查询专柜商品(销售)
	 *
	 * @param pageDto
	 * @return ProductPageDto
	 * @throws Exception
	 * @Methods Name selectProByParaForOms
	 * @Create In 2015年8月24日 By zhangxy
	 */
	List<ProductPageDto> selectProByParaForOms(ProductPageDto pageDto) throws BleException;

	Map<String, Long> selectSidsByPro(Long skuSid, Long proSid);

	List<PublishDTO> selectSidsBySku(Long skuSid);

	/**
	 * 根据Code查询专柜商品及品牌Sid
	 *
	 * @param paramMap
	 * @return Map<String,Object>
	 * @Methods Name getProAndBrandSidByCode
	 * @Create In 2015年11月18日 By yedong
	 */
	public Map<String, Object> getProAndBrandSidByCode(Map<String, Object> paramMap);

	public Page<ProductPageDto> selectSAPProList(Map<String, Object> paramMap);

	/**
	 * 专柜商品导出Excel 查询总数量
	 *
	 * @param pageDto
	 * @return Page<ProductPageDto>
	 * @throws Exception
	 * @Methods Name getShoppeProductToExcelCount
	 * @Create In 2016年04月05日 By wangxuan
	 */
	public Page<ProductPageDto> getShoppeProductToExcelCount(ProductPageDto pageDto)
			throws BleException;

	/**
	 * 专柜商品导出Excel 查询
	 *
	 * @param pageDto
	 * @return Page<ProductPageDto>
	 * @throws Exception
	 * @Methods Name getShoppeProductToExcel
	 * @Create In 2016年04月05日 By wangxuan
	 */
	public Page<ProductPageDto> getShoppeProductToExcel(ProductPageDto pageDto) throws BleException;

	/**
	 * 按条件 分页 查询专柜商品基础信息(优化)
	 *
	 * @param pageDto
	 * @return Page<ProductPageDto>
	 * @throws Exception
	 * @Methods Name selectBaseProPageByParaOptimization
	 * @Create In 2016年03月26日 By zhangxy
	 */
	public Page<ProductPageDto> selectBaseProPageByParaOptimization(ProductPageDto pageDto)
			throws BleException;

	List<PcmPublishSapErpDto> pcmPublishSapErpMap(Map<String, Object> paramMap);

	/**
	 * 下发至门店系统专柜商品信息查询
	 *
	 * @param list
	 * @return List<PushToPcmEfutureERPFromPcmDto>
	 * @Methods Name pushDtoProcess
	 * @Create In 2016-3-28 By wangc
	 */
	public List<PushToPcmEfutureERPFromPcmDto> pushDtoProcess1(List<String> list, Integer type);

	OmsResProInfoDto getProductPageByPara(String productCode, ProductPageDto pageDto)
			throws BleException;

	/**
	 * 根据专柜商品编码LIST查询专柜商品信息
	 *
	 * @param pageDto
	 * @throws BleException
	 *             Page<ProductPageDto>
	 * @Methods Name selectBaseProPageByProCodes1
	 * @Create In 2016-3-29 By wangc
	 */
	public Page<ShoProInfoFormPcmToPisDto> selectBaseProPageByProCodes1(
			ShoProInfoFormPcmToPis pageDto) throws BleException;

	/**
	 * Oms根据专柜商品编码查询指定级别的工业分类
	 *
	 * @param dto
	 * @return
	 */
	OmsShoppeProductReturnDto findIndustryCategoryByParaForOms(OmsShoppeProductDto dto);
}
