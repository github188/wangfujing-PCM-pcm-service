package com.wangfj.product.maindata.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangfj.product.maindata.domain.entity.PcmColorCodeDict;
import com.wangfj.product.maindata.domain.vo.PcmColorCodeDictPadDto;
import com.wangfj.product.maindata.persistence.PcmColorCodeDictMapper;
import com.wangfj.product.maindata.service.intf.IPcmColorCodeDictDataService;

@Service
public class PcmColorCodeDictDataServiceImpl implements IPcmColorCodeDictDataService {

	private static final Logger logger = LoggerFactory
			.getLogger(PcmColorCodeDictDataServiceImpl.class);

	@Autowired
	private PcmColorCodeDictMapper colorCodeDictMapper;

	/**
	 * 移动工作台调用主数据获取色码信息
	 * 
	 * @Methods Name getColorCodeDictForPad
	 * @Create In 2015-8-31 By wangxuan
	 * @param paramMap
	 * @return List<PcmColorCodeDictPadDto>
	 */
	@Override
	public List<PcmColorCodeDictPadDto> getColorCodeDictForPad(Map<String, Object> paramMap) {

		logger.info("start getColorCodeDictForPad(),para:" + paramMap.toString());

		List<PcmColorCodeDict> colorCodeDictList = colorCodeDictMapper
				.selectListByParamForPad(paramMap);

		List<PcmColorCodeDictPadDto> codeDictPadDtoList = new ArrayList<PcmColorCodeDictPadDto>();
		if (colorCodeDictList != null && !colorCodeDictList.isEmpty()) {

			for (PcmColorCodeDict colorCodeDict : colorCodeDictList) {
				PcmColorCodeDictPadDto codeDictPadDto = new PcmColorCodeDictPadDto();
				codeDictPadDto.setBrandCode(colorCodeDict.getBrandCode());
				codeDictPadDto.setCode(colorCodeDict.getColorCode());
				codeDictPadDto.setName(colorCodeDict.getColorName());

				codeDictPadDtoList.add(codeDictPadDto);
			}

		}

		logger.info("end getColorCodeDictForPad()");

		return codeDictPadDtoList;

	}

}
