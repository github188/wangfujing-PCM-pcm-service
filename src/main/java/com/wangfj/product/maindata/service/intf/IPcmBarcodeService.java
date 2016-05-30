package com.wangfj.product.maindata.service.intf;

import com.wangfj.core.framework.exception.BleException;
import com.wangfj.product.maindata.domain.entity.PcmBarcode;
import com.wangfj.product.maindata.domain.vo.SupplierBarCodeFromEfutureDto;

import java.util.List;
import java.util.Map;

/**
 * 条码表service
 *
 * @Class Name IPcmBarcodeService
 * @Author zhangxy
 * @Create In 2015年7月21日
 */
public interface IPcmBarcodeService {

    /**
     * 下发条码查询
     *
     * @param paramMap
     * @return
     */
    List<PcmBarcode> pushBarcodeToSAPERP(Map<String, Object> paramMap);

    /**
     * 条码信息-门店ERP上传--MQ
     *
     * @param pushSupplierlist void
     * @throws Exception
     * @Methods Name getSupplierBarCodeFromEfuture
     * @Create In 2015年7月21日 By zhangxy
     */
    void importSupplierBarCodeFromEfuture(SupplierBarCodeFromEfutureDto pushSupplierDto)
            throws BleException;

    /**
     * 条码信息-门店ERP上传--MQ
     *
     * @param pushSupplierlist void
     * @throws Exception
     * @Methods Name getSupplierBarCodeFromEfuture
     * @Create In 2015年7月21日 By zhangxy
     */
    public void getSupplierBarCodeFromEfuture(SupplierBarCodeFromEfutureDto pushSupplierlist)
            throws BleException;
}
