/**
 * @Probject Name: pcm-service
 * @Path: com.wangfj.product.supplier.service.intfIPcmBarcodeService.java
 * @Create By wangc
 * @Create In 2016-3-2 下午2:07:04
 */
package com.wangfj.product.supplier.service.intf;

import com.wangfj.core.framework.exception.BleException;
import com.wangfj.product.supplier.domain.vo.SupplierBarCodeFromSupDto;

/**
 * @Class Name IPcmBarcodeService
 * @Author  wangc
 * @Create In 2016-3-2
 */
public interface IPcmBarcodeSupService {

	public void getSupplierBarCodeFromSup(SupplierBarCodeFromSupDto pushSupplierlist)
			throws BleException;
}
