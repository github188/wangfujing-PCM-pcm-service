package com.wangfj.product.maindata.service.intf;

import java.util.List;
import java.util.Map;

import com.wangfj.product.maindata.domain.vo.PcmSaleUinitDictPadDto;

public interface IPcmSaleUnitDictDataService {

	/**
	 * 移动工作台调用主数据获取单位信息
	 * 
	 * @Methods Name getSaleUinitDictForPad
	 * @Create In 2015-8-31 By wangxuan
	 * @param paramMap
	 * @return List<PcmSaleUinitDictPadDto>
	 */
	List<PcmSaleUinitDictPadDto> getSaleUinitDictForPad(Map<String, Object> paramMap);

}
