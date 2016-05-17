package com.wangfj.product.maindata.service.intf;

import java.util.List;
import java.util.Map;

import com.wangfj.product.maindata.domain.vo.PcmColorCodeDictPadDto;

public interface IPcmColorCodeDictDataService {

	/**
	 * 移动工作台调用主数据获取色码信息
	 * 
	 * @Methods Name getColorCodeDictForPad
	 * @Create In 2015-8-31 By wangxuan
	 * @param paramMap
	 * @return List<PcmColorCodeDictPadDto>
	 */
	List<PcmColorCodeDictPadDto> getColorCodeDictForPad(Map<String, Object> paramMap);

}
