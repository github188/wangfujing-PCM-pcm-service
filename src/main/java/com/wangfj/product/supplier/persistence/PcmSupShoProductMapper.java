/**
 * @Probject Name: pcm-service-outer
 * @Path: com.wangfj.product.supplier.persistencePcmSupShoProductMapper.java
 * @Create By wangc
 * @Create In 2016-3-7 下午6:42:40
 */
package com.wangfj.product.supplier.persistence;

import java.util.List;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.supplier.domain.entity.GetShoProductSup;
import com.wangfj.product.supplier.domain.vo.PcmShoProductSupDataDto;
import com.wangfj.product.supplier.domain.vo.PcmShoppeProductForSupDto;

/**
 * @Class Name PcmSupShoProductMapper
 * @Author wangc
 * @Create In 2016-3-7
 */
public interface PcmSupShoProductMapper extends BaseMapper<GetShoProductSup>{

	/**
	 * 供应商获取商品信息
	 * @Methods Name getShoProduct
	 * @Create In 2016-3-7 By wangc
	 * @param proDto
	 * @return List<PcmShoProductSupDataDto>
	 */
	 List<PcmShoProductSupDataDto> getShoProductByParams(GetShoProductSup proDto);
	/**
	 * 供应商获取商品信息数量
	 * @Methods Name getShoProductCountByParams
	 * @Create In 2016-3-7 By wangc
	 * @param proDto
	 * @return Integer
	 */
	 Integer getShoProductCount(GetShoProductSup proDto);
	 /**
	  * 供应商获取专柜商品详情
	  * @Methods Name ShoppeProductForSupDto
	  * @Create In 2016-3-9 By wangc
	  * @param proDto
	  * @return List<PcmShoppeProductForSupDto>
	  */
	 List<PcmShoppeProductForSupDto> getShoppeProductForSupDto(GetShoProductSup proDto);
}
