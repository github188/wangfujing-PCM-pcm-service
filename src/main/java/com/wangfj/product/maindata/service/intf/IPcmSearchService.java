package com.wangfj.product.maindata.service.intf;

import java.util.List;
import java.util.Map;

import com.wangfj.product.maindata.domain.vo.PcmSearchShoppeProDto;

public interface IPcmSearchService {
	/**
	 * 对接搜索-查询SKU
	 * 
	 * @Methods Name searchSku
	 * @Create In 2015年8月28日 By yedong
	 * @param skuId
	 * @return Map<String,Object>
	 */
	public Map<String, Object> searchSku(String skuId);

	/**
	 * 对接搜索-查询SPU
	 * 
	 * @Methods Name searchSpu
	 * @Create In 2015年8月28日 By yedong
	 * @param spuId
	 * @return Map<String,Object>
	 */
	public Map<String, Object> searchSpu(String spuId);

	/**
	 * 赋值
	 * 
	 * @Methods Name searchShoppePro
	 * @Create In 2015年8月28日 By yedong
	 * @param proList
	 * @return List<PcmSearchShoppeProDto>
	 */
	public List<PcmSearchShoppeProDto> searchShoppePro(List<String> proList);
}
