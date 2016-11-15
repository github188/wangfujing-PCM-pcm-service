package com.wangfj.product.organization.service.intf;

import java.util.List;

import com.wangfj.core.framework.base.page.Page;
import com.wangfj.product.organization.domain.entity.PcmStoreInfo;
import com.wangfj.product.organization.domain.vo.PcmStoreInfoQueryDto;
import com.wangfj.product.organization.domain.vo.PcmStoreInfoResultDto;
import com.wangfj.product.organization.domain.vo.PcmStoreInfoToSupplierDto;

/**
 * 门店信息 Created by wangxuan on 2016-08-25 0025.
 */
public interface IPcmStoreInfoService {

	public List<PcmStoreInfo> selectListByParam(PcmStoreInfo storeInfo);

	public boolean getPublish(String storeCode);

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
