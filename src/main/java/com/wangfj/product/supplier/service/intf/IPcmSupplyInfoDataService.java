package com.wangfj.product.supplier.service.intf;

import java.util.List;
import java.util.Map;

import com.wangfj.product.supplier.domain.vo.PcmSupplyInfoDataDto;
import com.wangfj.product.supplier.domain.vo.PcmSupplyInfoPadDto;

public interface IPcmSupplyInfoDataService {

	/**
	 * 由主数据获取供应商信息
	 * 
	 * @Methods Name getSupplyInfoData
	 * @Create In 2015-8-31 By wangxuan
	 * @param para
	 * @return List<PcmSupplyInfoDataDto>
	 */
	List<PcmSupplyInfoDataDto> getSupplyInfoData(Map<String, Object> para);

	/**
	 * 移动工作台调用主数据获取供应商信息
	 * 
	 * @Methods Name getSupplyInfoForPad
	 * @Create In 2015-8-31 By wangxuan
	 * @param para
	 * @return List<PcmSupplyInfoPadDto>
	 */
	List<PcmSupplyInfoPadDto> getSupplyInfoForPad(Map<String, Object> para);

}
