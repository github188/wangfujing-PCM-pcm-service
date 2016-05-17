/**
 * @Probject Name: pcm-service
 * @Path: com.wangfj.product.price.service.intfIPcmPrice.java
 * @Create By duanzhaole
 * @Create In 2015年7月10日 上午10:39:36
 * TODO
 */
package com.wangfj.product.price.service.intf;

import com.wangfj.core.framework.base.page.Page;
import com.wangfj.product.price.domain.entity.PcmChangePriceRecord;
import com.wangfj.product.price.domain.entity.PcmPrice;
import com.wangfj.product.price.domain.vo.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @Class Name IPcmPrice
 * @Author kongqf
 * @Create In 2015年7月10日
 */
public interface IPcmPriceService {

	/**
	 * 商品价格初始化
	 * 
	 * @Methods Name initProductPriceInfo
	 * @Create In 2015年7月27日 By kongqf
	 * @param pp
	 * @return int
	 */
	public int initProductPriceInfo(PcmPrice pcmPrice);

	/**
	 * 商品价格查询
	 * 
	 * @Methods Name queryPriceInfoByPara
	 * @Create In 2015年8月25日 By kongqf
	 * @param key
	 * @param queryPriceDto
	 * @return SelectPriceDto
	 */
	public SelectPriceDto queryPriceInfoByPara(String key, QueryPriceDto queryPriceDto);

	public List<SelectPriceDto> queryPriceInfoListByPara(QueryPriceListDto queryPriceListDto);

	/**
	 * 保存或修改商品价格信息
	 * 
	 * @Methods Name saveOrUpdatePrice
	 * @Create In 2015年8月19日 By kongqf
	 * @param pcmpricedto
	 * @param fromSystem
	 * @return boolean
	 */
	public boolean saveOrUpdatePrice(PcmPriceERPDto pcmpricedto, String fromSystem,
			BigDecimal upperLimit, BigDecimal lowerLimit);

	/**
	 * 保存变价请求信息
	 * 
	 * @Methods Name saveChangePriceRecord
	 * @Create In 2015年8月14日 By kongqf
	 * @param dto
	 * @return boolean
	 */
	public boolean saveChangePriceRecord(PcmPriceERPDto dto, String fromSystem);

	/**
	 * 保存批量变价请求信息
	 * 
	 * @Methods Name saveChangePriceRecord
	 * @Create In 2015年8月14日 By kongqf
	 * @param dto
	 * @return boolean
	 */
	public boolean saveBatchChangePriceRecord(PcmPricePISDto pcmPricePISDto);

	/**
	 * 查询无效价格信息
	 * 
	 * @Methods Name queryExpirePriceInfoList
	 * @Create In 2015年8月10日 By kongqf
	 * @return List<PcmPrice>
	 */
	public List<PcmPrice> queryExpirePriceInfoList();

	/**
	 * 保存无效价格信息到价格历史表
	 * 
	 * @Methods Name saveExpirePriceToPriceHis
	 * @Create In 2015年8月10日 By kongqf
	 * @param pcmPrice
	 * @return boolean
	 */
	public boolean saveExpirePriceToPriceHis(PcmPrice pcmPrice);

	/**
	 * 根据条件查询所有专柜商品编码 管理分类、供应商编码、专柜编码
	 * 
	 * @Methods Name queryBatchChangePriceInfo
	 * @Create In 2015年8月19日 By kongqf
	 * @param pcmPricePISDto
	 * @return Page<QueryShoppeProSidDto>
	 */
	public Page<QueryShoppeProSidDto> queryBatchChangePriceInfo(PcmPricePISDto pcmPricePISDto);

	/**
	 * 根据条件和pagesize查询所有专柜商品编码总页数
	 * 
	 * @Methods Name queryBatchChangePriceInfoPages
	 * @Create In 2015年8月21日 By kongqf
	 * @param pcmPricePISDto
	 * @return int
	 */
	public int queryBatchChangePriceInfoPages(PcmPricePISDto pcmPricePISDto);

	/**
	 * 根据变价号和pageSize查询下发数据总页数
	 * 
	 * @Methods Name queryPushBatchChangePriceInfoPages
	 * @Create In 2015年8月21日 By kongqf
	 * @param selectPcmPriceToERPPDto
	 * @param pageSize
	 * @return int
	 */
	public int queryPushBatchChangePriceInfoPages(SelectPcmPriceToERPPDto selectPriceToERPPDto);

	/**
	 * 批量变价
	 * 
	 * @Methods Name saveOrUpdatePrice
	 * @Create In 2015年7月31日 By kongqf
	 * @param pcmpricedto
	 * @return boolean
	 */
	public boolean saveOrUpdateBatchPrice(PcmPricePISDto pcmPricePISDto, BigDecimal upperLimit,
			BigDecimal lowerLimit);

	/**
	 * 根据变价号查询下发数据
	 * 
	 * @Methods Name queryPushBatchChangePriceInfo
	 * @Create In 2015年8月21日 By kongqf
	 * @param selectPriceToERPPDto
	 * @return Page<PcmPriceToERPPDto>
	 */
	public Page<PcmPriceToERPPDto> queryPushBatchChangePriceInfo(
			SelectPcmPriceToERPPDto selectPriceToERPPDto);

	/**
	 * 获取变价单信息
	 * 
	 * @Methods Name getSelectPcmPriceToERPDto
	 * @Create In 2015年8月21日 By kongqf
	 * @param pcmPricePISDtos
	 * @return List<SelectPcmPriceToERPPDto>
	 */
	public List<SelectPcmPriceToERPPDto> getSelectPcmPriceToERPDto(
			List<PcmPricePISDto> pcmPricePISDtos);

	/**
	 * 商品价格信息查询
	 * 
	 * @Methods Name queryProductPriceInfo
	 * @Create In 2015年9月7日 By kongqf
	 * @param queryProductPriceInfoDto
	 * @return Page<SelectProductPriceInfoDto>
	 */
	public Page<SelectProductPriceInfoDto> queryProductPriceInfo(
			QueryProductPriceInfoDto queryProductPriceInfoDto);

    /**
     * 商品价格信息查询(优化)
     *
     * @param queryProductPriceInfoDto
     * @return Page<QueryProductPriceInfoDto>
     * @Methods Name queryProductPriceInfoOptimization
     * @Create In 2015年9月7日 By kongqf
     */
    public Page<SelectProductPriceInfoDto> queryProductPriceInfoOptimization(
            QueryProductPriceInfoDto queryProductPriceInfoDto);

	/**
	 * 根据一组专柜商品编码查询价格
	 *
	 * @param queryProductPriceInfoDto
	 * @return List<SelectProductPriceInfoDto>
	 * @Methods Name findPriceInfoByParaForShoppeProduct
	 * @Create In 2016年4月12日 By wangxuan
	 */
	List<SelectProductPriceInfoDto> findPriceInfoByParaForShoppeProduct(QueryProductPriceInfoDto dto);

	/**
	 * 根据专柜商品查询价格信息
	 * 
	 * @Methods Name queryShoppeProPriceInfoByShoppeProSid
	 * @Create In 2015年9月11日 By kongqf
	 * @param queryShoppeProSidDto
	 * @return List<SelectShoppeProPriceInfoDto>
	 */
	public List<SelectShoppeProPriceInfoDto> queryShoppeProPriceInfoByShoppeProSid(
			QueryPriceDto queryPriceDto);

	/**
	 * 查询当前价格信息页数
	 * 
	 * @Methods Name queryCurrPriceInfoPages
	 * @Create In 2015年9月17日 By kongqf
	 * @param pcmPricePISDto
	 * @return Integer
	 */
	public Integer queryCurrPriceInfoPages(PcmPricePISDto pcmPricePISDto);

	/**
	 * 查询当前价格信息（分页）
	 * 
	 * @Methods Name queryCurrPriceInfo
	 * @Create In 2015年9月17日 By kongqf
	 * @param pcmPricePISDto
	 * @return Page<SelectPriceDto>
	 */
	public Page<SelectPriceDto> queryCurrPriceInfo(PcmPricePISDto pcmPricePISDto);

	/**
	 * 查询当前生效变价单数量
	 * 
	 * @Methods Name queryCurChangePriceRecordPages
	 * @Create In 2015年9月23日 By kongqf
	 * @param dto
	 * @return int
	 */
	public int queryCurChangePriceRecordPages(SelectChangePriceRecordDto dto);

	/**
	 * 查询当前生效变价单
	 * 
	 * @Methods Name queryCurChangePriceRecordInfo
	 * @Create In 2015年9月23日 By kongqf
	 * @param dto
	 * @return Page<PcmChangePriceRecord>
	 */
	public Page<PcmChangePriceRecord> queryCurChangePriceRecordInfo(SelectChangePriceRecordDto dto);

	/**
	 * 修改大码价格
	 * 
	 * @Methods Name UpdateERPProPrice
	 * @Create In 2015年9月23日 By kongqf
	 * @param priceRecords
	 * @return List<PcmChangePriceRecord>
	 */
	public List<PcmChangePriceRecord> UpdateERPProPrice(List<PcmChangePriceRecord> priceRecords);

    /**
     * 专柜商品价格信息查询导出Excel 查总数
     *
     * @param queryProductStockInfoDto
     * @return List<SelectProductStockInfoDto>
     * @Methods Name queryProductPriceInfoExcelCount
     * @Create In 2016年04月05日 By wangxuan
     */
    public Page<SelectProductPriceInfoDto> queryProductPriceInfoExcelCount(QueryProductPriceInfoDto queryProductPriceInfoDto);

    /**
	 * 专柜商品价格信息查询导出Excel
	 * 
	 * @Methods Name queryProductStockInfoExcel
	 * @Create In 2015年10月8日 By kongqf
	 * @param queryProductStockInfoDto
	 * @return List<SelectProductStockInfoDto>
	 */
	public List<SelectProductPriceInfoDto> queryProductPriceInfoExcel(
			QueryProductPriceInfoDto queryProductPriceInfoDto);

	/**
	 * 根据开始时间结束时间，获取商品价格信息
	 * 
	 * @Methods Name selectPriceByTimeFrame
	 * @Create In 2016-1-6 By wangc
	 * @param map
	 * @return Map<String,Object>
	 */
	public Map<String, Object> selectPriceByTimeFrame(Map<String, Object> map);

	/**
	 * 根据专柜商品编码查询商品sid
	 * 
	 * @Methods Name selectShopProSidByShopProCode
	 * @Create In 2016年3月8日 By kongqf
	 * @param shopProSids
	 * @return List<Map<String,Object>>
	 */
	public List<Map<String, Object>> selectShopProSidByShopProCode(List<String> shopProSids);

}
