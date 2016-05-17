package com.wangfj.product.maindata.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangfj.product.maindata.domain.entity.PcmStanDict;
import com.wangfj.product.maindata.domain.vo.PcmStanDictPadDto;
import com.wangfj.product.maindata.persistence.PcmStanDictMapper;
import com.wangfj.product.maindata.service.intf.IPcmStanDictDataService;

/**
 * 
 * @Class Name PcmStanDictDataServiceImpl
 * @Author wangxuan
 * @Create In 2015-9-9
 */
@Service
public class PcmStanDictDataServiceImpl implements IPcmStanDictDataService {

	private static final Logger logger = LoggerFactory.getLogger(PcmStanDictDataServiceImpl.class);

	@Autowired
	private PcmStanDictMapper stanDictMapper;

	/**
	 * 移动工作台调用主数据获取尺码信息
	 * 
	 * @Methods Name getStanDictForPad
	 * @Create In 2015-9-9 By wangxuan
	 * @param paramMap
	 * @return List<PcmStanDictPadDto>
	 */
	@Override
	public List<PcmStanDictPadDto> getStanDictForPad(Map<String, Object> paramMap) {

		logger.info("start getStanDictForPad(),para:" + paramMap.toString());

		List<PcmStanDict> stanDictList = stanDictMapper.selectListByParamForPad(paramMap);

		List<PcmStanDictPadDto> stanDictPadDtoList = new ArrayList<PcmStanDictPadDto>();
		if (stanDictList != null && !stanDictList.isEmpty()) {

			for (PcmStanDict stanDict : stanDictList) {
				PcmStanDictPadDto stanPadDto = new PcmStanDictPadDto();
				stanPadDto.setBrandCode(stanDict.getBrandSid());
				stanPadDto.setCode(stanDict.getProStanSid());
				stanPadDto.setName(stanDict.getProStanName());

				stanDictPadDtoList.add(stanPadDto);
			}

		}

		logger.info("end getStanDictForPad()");

		return stanDictPadDtoList;

	}

}
