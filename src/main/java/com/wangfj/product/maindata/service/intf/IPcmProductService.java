package com.wangfj.product.maindata.service.intf;

import java.util.List;
import java.util.Map;

import com.wangfj.core.framework.base.page.Page;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.product.maindata.domain.entity.PcmProDetail;
import com.wangfj.product.maindata.domain.entity.PcmProduct;
import com.wangfj.product.maindata.domain.vo.PcmProductDto;
import com.wangfj.product.maindata.domain.vo.ProSkuSpuPublishDto;
import com.wangfj.product.maindata.domain.vo.ProductCondDto;
import com.wangfj.product.maindata.domain.vo.PromotionRateDto;
import com.wangfj.product.maindata.domain.vo.PushPromotionDto;
import com.wangfj.product.maindata.domain.vo.SpuPageDto;
import com.wangfj.util.mq.PublishDTO;

/**
 * 产品接口：商品属性修改service
 * 
 * @Class Name IPcmProductService
 * @Author chengsj
 * @Create In 2015-8-4
 */
public interface IPcmProductService {
	/**
	 * 产品更换工业分类
	 * 
	 * @Methods Name updateProductCategory
	 * @Create In 2015-8-4 By chengsj
	 * @param pc
	 * @return int
	 */
	int updateProductCategory(ProductCondDto condDto);

	/**
	 * SKU换款/主属性
	 * 
	 * @Methods Name changeProductSku
	 * @Create In 2015年9月10日 By yedong
	 * @param condDto
	 * @throws Exception
	 *             void
	 */
	public ProSkuSpuPublishDto changeProductSkuBySKU(ProductCondDto condDto) throws BleException;

	/**
	 * SKU换款/主属性
	 * 
	 * @Methods Name changeProductSku
	 * @Create In 2015年9月10日 By yedong
	 * @param condDto
	 * @throws Exception
	 *             void
	 */
	public List<PublishDTO> validChangeProductSkuBySKU(ProductCondDto condDto) throws BleException;

	/**
	 * 专柜商品换款
	 * 
	 * @Methods Name changeProductSku
	 * @Create In 2015年9月10日 By yedong
	 * @param condDto
	 * @return
	 * @throws Exception
	 *             void
	 */
	public ProSkuSpuPublishDto changeProductSku(ProductCondDto condDto) throws BleException;

	/**
	 * 专柜商品换专柜
	 * 
	 * @Methods Name updateProductShoppe
	 * @Create In 2015年8月5日 By zhangxy
	 * @param condDto
	 * @return int
	 * @throws Exception
	 */
	List<PublishDTO> updateProductShoppe(ProductCondDto condDto) throws BleException;

	/**
	 * 专柜商品换扣率码
	 * 
	 * @Methods Name updateProductRateCode
	 * @Create In 2015年8月5日 By zhangxy
	 * @param condDto
	 * @return int
	 * @throws Exception
	 */
	List<PublishDTO> updateProductRateCode(ProductCondDto condDto) throws BleException;

	/**
	 * 专柜商品挂促销扣率码
	 * 
	 * @Methods Name insertPromotionRate
	 * @Create In 2015年8月5日 By zhangxy
	 * @param condDto
	 * @return int
	 */
	int savePromotionRate(ProductCondDto condDto);

	List<Map<String, Object>> selectPage1(PcmProductDto record);

	int getTotalCount(PcmProductDto record);

	/**
	 * 多条件分页查询SPU信息
	 * 
	 * @Methods Name selectSpuPage
	 * @Create In 2015年8月12日 By zhangxy
	 * @param dto
	 * @return Page<SpuPageDto>
	 */
	Page<SpuPageDto> selectSpuPage(SpuPageDto dto);

	/**
	 * 14.1 移动工作台调用主数据获取促销扣率码
	 * 
	 * @Methods Name selectPromotionRteDto
	 * @Create In 2015年8月27日 By zhangxy
	 * @param dto
	 * @return List<PromotionRateDto>
	 */
	List<PromotionRateDto> selectPromotionRteDto(PromotionRateDto dto);

	/**
	 * 专柜商品换管理分类
	 * 
	 * @Methods Name updateManagerCategory2
	 * @Create In 2015年8月20日 By yedong
	 * @param condDto
	 * @return int
	 */
	public List<PublishDTO> updateManagerCategory(ProductCondDto condDto);

	/**
	 * 专柜商品换色码、规码 专柜商品编码，色码，规码，特性
	 * 
	 * @Methods Name updateColorStan
	 * @Create In 2015年8月19日 By yedong
	 * @param condDto
	 * @return boolean
	 * @throws Exception
	 */
	public ProSkuSpuPublishDto updateColorStan(ProductCondDto condDto) throws BleException;

	/**
	 * sku换色码、规码
	 * 
	 * @Methods Name updateSkuColorStan
	 * @Create In 2015年8月21日 By yedong
	 * @param condDto
	 * @return boolean
	 */
	public List<PublishDTO> validUpdateSkuColorStan(ProductCondDto condDto);

	/**
	 * sku换色码、规码
	 * 
	 * @Methods Name updateSkuColorStan
	 * @Create In 2015年8月21日 By yedong
	 * @param condDto
	 * @return boolean
	 */
	public ProSkuSpuPublishDto updateSkuColorStan(ProductCondDto condDto);

	/**
	 * 产品更换统计分类
	 * 
	 * @Methods Name updateStatCategory
	 * @Create In 2015-8-4 By chengsj
	 * @param pc
	 * @return int
	 */
	public List<PublishDTO> updateStatCategory(ProductCondDto condDto);

	/**
	 * 下发SPU至促销
	 */
	public List<PushPromotionDto> getPushPromotionDtoBySid(Map<String, Object> paramMap);

	ProSkuSpuPublishDto updateSapColorStan(ProductCondDto condDto);

	void skuStutasVail(PcmProDetail oldSku, PcmProDetail newSku);

	List<PcmProduct> getListProByParam(Map<String, Object> param);

	String updateProByParam(PcmProduct param);

}
