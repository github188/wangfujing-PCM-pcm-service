package com.wangfj.product.supplier.service.intf;

import com.wangfj.core.framework.exception.BleException;
import com.wangfj.product.supplier.domain.entity.PcmSupplyGroup;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created by wangxuan on 2016-10-12 0012.
 * 集团供应商
 */
public interface IPcmSupplyGroupService {

    /**
     * 集团供应商从供应商平台上传到PCM
     *
     * @param supplyInfo
     * @param dtoMap
     * @return
     * @throws BleException
     */
    @Transactional
    Map<String, Object> uploadPcmSupplyGroupFromSupplierERP(PcmSupplyGroup supplyInfo, Map<String, Object> dtoMap) throws BleException;
}
