package com.wangfj.product.maindata.service.intf;

import java.util.List;
import java.util.Map;

import com.wangfj.core.framework.exception.BleException;
import com.wangfj.product.maindata.domain.entity.PcmProDetail;
import com.wangfj.product.maindata.domain.entity.PcmProduct;
import com.wangfj.product.maindata.domain.entity.PcmShoppeProduct;
import com.wangfj.product.maindata.domain.entity.PcmShoppeProductExtend;
import com.wangfj.product.maindata.domain.vo.CreateShoppePro;
import com.wangfj.product.maindata.domain.vo.CreateSkuDto;
import com.wangfj.product.maindata.domain.vo.CreateSpuDto;
import com.wangfj.product.maindata.domain.vo.PullDataDto;

/**
 * 创建spu、sku、专柜商品的service
 * 
 * @Class Name IPcmCreateProductService
 * @Author liuhp
 * @Create In 2015-8-17
 */
public interface IPcmCreateProductService {
	public void getLogSupShoppeByProCode(Map<String, Object> paraMap,PullDataDto dataDto);
	/**
	 * 创建spu
	 * 
	 * @Methods Name insertSPU
	 * @Create In 2015-8-19 By liuhp
	 * @param createSpuDto
	 * @return PcmProduct
	 */
	public PcmProduct insertSPU(CreateSpuDto createSpuDto);

	/**
	 * 创建sku
	 * 
	 * @Methods Name insertSKU
	 * @Create In 2015-8-19 By liuhp
	 * @param createSkuDto
	 * @return PcmProDetail
	 */
	public PcmProDetail insertSKU(CreateSkuDto createSkuDto);

	/**
	 * 创建专柜商品
	 * 
	 * @Methods Name insertShoppePro
	 * @Create In 2015-8-19 By liuhp
	 * @param createShoppePro
	 * @return PcmShoppeProduct
	 */
	public PcmShoppeProduct insertShoppePro(CreateShoppePro createShoppePro);

	/**
	 * 插入临时表信息
	 * 
	 * @Methods Name insertProductInput
	 * @Create In 2015年8月24日 By zhangxy
	 * @param dataDto
	 * @return int
	 * @throws Exception
	 */
	public void insertProductInput(Long spSid, String offerNum, String entryNumber, String ppNum,
			PcmShoppeProductExtend dsPro) throws BleException;

	/**
	 * 插入标签
	 * 
	 * @Methods Name insertProductTags
	 * @Create In 2015年11月25日 By zhangxy
	 * @param tags
	 */
	public void insertProductTags(Long sid, List<String> tags);

	/**
	 * 中台商品编码字段不为空时 修改专柜商品信息
	 * @Methods Name updateSProductBySProductCode
	 * @Create In 2015-12-22 By wangc
	 * @param dataDto
	 * @param extendDto
	 * @return PcmShoppeProduct
	 */
	public PcmShoppeProduct updateSProductBySProductCode(PullDataDto dataDto,
			PcmShoppeProductExtend extendDto,String shoppeProSid);
	/**
	 * 中台商品编码字段不为空时 修改专柜商品信息
	 * 去除修改专柜,只允许修改D00100001下的供应商
	 * @Methods Name updateSProductBySProductCode2
	 * @Create In 2016年6月29日 By wangc
	 * @param dataDto
	 * @param extendDto
	 * @param shoppeProSid
	 * @return PcmShoppeProduct
	 */
	public PcmShoppeProduct updateSProductBySProductCode2(PullDataDto dataDto,
			PcmShoppeProductExtend extendDto,String shoppeProSid);
}
