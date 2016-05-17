package com.wangfj.product.maindata.service.intf;

import java.util.List;
import java.util.Map;

import com.wangfj.product.maindata.domain.vo.PcmStanDictPadDto;

public interface IPcmStanDictDataService {

	/**
	 * 移动工作台调用主数据获取尺码信息
	 * 
	 * @Methods Name getStanDictForPad
	 * @Create In 2015-9-9 By wangxuan
	 * @param paramMap
	 * @return List<PcmStanDictPadDto>
	 */
	List<PcmStanDictPadDto> getStanDictForPad(Map<String, Object> paramMap);

}
