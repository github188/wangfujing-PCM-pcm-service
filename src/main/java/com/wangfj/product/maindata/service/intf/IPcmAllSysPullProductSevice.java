/**
 * @Probject Name: pcm-service
 * @Path: com.wangfj.product.maindata.service.intfIPcmAllSysPullProductSevice.java
 * @Create By wangc
 * @Create In 2016年6月14日 下午4:37:25
 * TODO
 */
package com.wangfj.product.maindata.service.intf;

import com.wangfj.core.framework.exception.BleException;
import com.wangfj.product.maindata.domain.entity.PcmShoppeProduct;
import com.wangfj.product.maindata.domain.entity.PcmShoppeProductExtend;
import com.wangfj.product.maindata.domain.vo.PcmAllSysPullDataDto;

/**
 * @Class Name IPcmAllSysPullProductSevice
 * @Author wangc
 * @Create In 2016年6月14日
 */
public interface IPcmAllSysPullProductSevice {

	/**
	 * 多系统商品上传接口
	 * @Methods Name allSysSaveProduct
	 * @Create In 2016年6月14日 By wangc
	 * @param dataDto
	 * @return
	 * @throws BleException PcmShoppeProduct
	 */
	public PcmShoppeProduct allSysSaveProduct (PcmAllSysPullDataDto dataDto,PcmShoppeProductExtend extendDto) throws BleException;
}
