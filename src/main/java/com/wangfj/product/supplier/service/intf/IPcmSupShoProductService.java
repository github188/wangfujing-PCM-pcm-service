/**
 * @Probject Name: pcm-service-outer
 * @Path: com.wangfj.product.supplier.service.intfIPcmSupShoProductService.java
 * @Create By wangc
 * @Create In 2016-3-7 下午6:45:26
 */
package com.wangfj.product.supplier.service.intf;

import java.util.List;

import com.wangfj.core.framework.base.page.Page;
import com.wangfj.product.supplier.domain.entity.GetShoProductSup;
import com.wangfj.product.supplier.domain.vo.PcmShoProductSupDataDto;
import com.wangfj.product.supplier.domain.vo.PcmShoppeProductForSupDto;

/**
 * 供应商商品service
 * @Class Name IPcmSupShoProductService
 * @Author wangc
 * @Create In 2016-3-7
 */
public interface IPcmSupShoProductService {

	/**
	 * 供应商获取商品信息
	 * @Methods Name getShoProductByParams
	 * @Create In 2016-3-7 By wangc
	 * @param proDto
	 * @return List<PcmShoProductSupDataDto>
	 */
	public Page<PcmShoProductSupDataDto> getShoProductByParams(GetShoProductSup proDto);
	/**
	 * 供应商获取专柜商品详情
	 * @Methods Name ShoppeProductForSupDto
	 * @Create In 2016-3-9 By wangc
	 * @param proDto
	 * @return List<PcmShoppeProductForSupDto>
	 */
	public List<PcmShoppeProductForSupDto> getShoppeProductForSupDto(GetShoProductSup proDto);
}
