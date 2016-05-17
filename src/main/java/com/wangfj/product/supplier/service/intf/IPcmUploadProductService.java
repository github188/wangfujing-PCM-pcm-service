/**
 * @Probject Name: pcm-service
 * @Path: com.wangfj.product.supplier.service.intfIPcmUploadProductService.java
 * @Create By wangc
 * @Create In 2016-2-26 下午2:33:52
 * TODO
 */
package com.wangfj.product.supplier.service.intf;

import org.springframework.stereotype.Service;

import com.wangfj.core.framework.exception.BleException;
import com.wangfj.product.maindata.domain.entity.PcmShoppeProduct;
import com.wangfj.product.maindata.domain.vo.PullDataDto;


/**
 *供应商商品上传接口
 * @Class Name IPcmUploadProductService
 * @Author  wangc
 * @Create In 2016-2-26
 */
@Service
public interface IPcmUploadProductService {

	/**
	 * 供应商商品上传
	 * @Methods Name uploadProductFromSup
	 * @Create In 2016-2-26 By wangc
	 * @param PUPDto
	 * @return PcmShoppeProduct
	 */
	public PcmShoppeProduct uploadProductFromSup(PullDataDto dataDto) throws BleException;
}
