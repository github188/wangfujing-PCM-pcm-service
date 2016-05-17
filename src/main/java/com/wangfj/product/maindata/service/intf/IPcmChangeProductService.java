package com.wangfj.product.maindata.service.intf;

import com.wangfj.product.maindata.domain.vo.ChangeProductDto;
import com.wangfj.product.maindata.domain.vo.ProSkuSpuPublishDto;

/**
 * 商品修改属性
 * 
 * @Class Name IPcmChangeProductService
 * @Author liuhp
 * @Create In 2015-8-3
 */
public interface IPcmChangeProductService {

	/**
	 * 根据商品sid禁售专柜商品
	 * 
	 * @Methods Name frozeProduct
	 * @Create In 2015-8-3 By liuhp
	 * @param shoppeProduct
	 * @return
	 */
	public void prohibiteShoppeProduct(ChangeProductDto changeProductDto);

	/**
	 * 冻结专柜商品
	 * 
	 * @Methods Name freezeShoppeProduct
	 * @Create In 2015-8-4 By liuhp
	 * @param shoppeProductDto
	 * @return
	 */
	public void freezeShoppeProduct(ChangeProductDto changeProductDto);

	/**
	 * 专柜商品换品牌（集团品牌）
	 * 
	 * @Methods Name changeGroupBrand
	 * @Create In 2015-8-8 By liuhp
	 * @param changeProductDto
	 *            void
	 * @return
	 */
	public ProSkuSpuPublishDto changeGroupBrand(ChangeProductDto changeProductDto);

}
