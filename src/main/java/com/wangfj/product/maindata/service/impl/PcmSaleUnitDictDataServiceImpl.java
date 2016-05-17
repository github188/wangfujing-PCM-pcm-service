package com.wangfj.product.maindata.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangfj.product.maindata.domain.entity.PcmSaleUnitDict;
import com.wangfj.product.maindata.domain.vo.PcmSaleUinitDictPadDto;
import com.wangfj.product.maindata.persistence.PcmSaleUnitDictMapper;
import com.wangfj.product.maindata.service.intf.IPcmSaleUnitDictDataService;

@Service
public class PcmSaleUnitDictDataServiceImpl implements IPcmSaleUnitDictDataService {

	private static final Logger logger = LoggerFactory
			.getLogger(PcmSaleUnitDictDataServiceImpl.class);

	@Autowired
	private PcmSaleUnitDictMapper saleUnitDictMapper;

	/**
	 * 移动工作台调用主数据获取单位信息
	 * 
	 * @Methods Name getSaleUinitDictForPad
	 * @Create In 2015-8-31 By wangxuan
	 * @param paramMap
	 * @return List<PcmSaleUinitDictPadDto>
	 */
	@Override
	public List<PcmSaleUinitDictPadDto> getSaleUinitDictForPad(Map<String, Object> paramMap) {

		logger.info("start getSaleUinitDictForPad(),para:" + paramMap.toString());

		List<PcmSaleUnitDict> saleUnitDictList = saleUnitDictMapper
				.selectListByParamForPad(paramMap);

		List<PcmSaleUinitDictPadDto> dictPadDtoList = new ArrayList<PcmSaleUinitDictPadDto>();
		if (saleUnitDictList != null && !saleUnitDictList.isEmpty()) {

			for (PcmSaleUnitDict saleUinitDict : saleUnitDictList) {

				PcmSaleUinitDictPadDto dictPadDto = new PcmSaleUinitDictPadDto();
				dictPadDto.setCode(saleUinitDict.getUnitCode());
				dictPadDto.setName(saleUinitDict.getUnitName());

				dictPadDtoList.add(dictPadDto);

			}

		}

		logger.info("end getSaleUinitDictForPad()");

		return dictPadDtoList;
	}

}
