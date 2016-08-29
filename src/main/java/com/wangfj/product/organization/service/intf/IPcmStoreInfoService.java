package com.wangfj.product.organization.service.intf;

import com.wangfj.core.framework.base.page.Page;
import com.wangfj.product.organization.domain.vo.PcmStoreInfoQueryDto;
import com.wangfj.product.organization.domain.vo.PcmStoreInfoResultDto;
import com.wangfj.product.organization.domain.vo.PcmStoreInfoToSupplierDto;

import java.util.List;

/**
 * 门店信息
 * Created by wangxuan on 2016-08-25 0025.
 */
public interface IPcmStoreInfoService {

    /**
     * 门店信息分页查询
     *
     * @param queryDto
     * @return
     */
    Page<PcmStoreInfoResultDto> findPageStoreInfo(PcmStoreInfoQueryDto queryDto);

    /**
     * 查询门店信息List
     *
     * @param queryDto
     * @return
     */
    List<PcmStoreInfoResultDto> findListStoreInfo(PcmStoreInfoQueryDto queryDto);

    /**
     * 供应商平台根据门店编码查询门店信息
     *
     * @param queryDto
     * @return
     */
    List<PcmStoreInfoToSupplierDto> findStoreInfoByParaToSupplier(PcmStoreInfoQueryDto queryDto);
}
