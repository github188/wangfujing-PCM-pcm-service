package com.wangfj.product.maindata.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangfj.product.demo.service.impl.UserServiceImpl;
import com.wangfj.product.maindata.domain.entity.PcmSaleUnitDict;
import com.wangfj.product.maindata.persistence.PcmSaleUnitDictMapper;
import com.wangfj.product.maindata.service.intf.IPcmSaleUnitDictService;

/**
 * 
 * @Class Name PcmSaleUnitDictServiceImpl
 * @Author wangxiang
 * @Create In 2015年7月20日
 */
@Service
public class PcmSaleUnitDictServiceImpl implements IPcmSaleUnitDictService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	PcmSaleUnitDictMapper saleUnitDictMapper;

	/**
	 * 单位下发
	 * 
	 * @Methods Name pushUnitFromPcm
	 * @Create In 2015年7月20 wangxiang
	 * @return List<Map<String, Object>>
	 */
	@Override
	public List<Map<String, Object>> pushUnitFromPcm() {
		logger.info("start pushUnitFromPcm");
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<PcmSaleUnitDict> list = saleUnitDictMapper.selectListByParam(paramMap);
		Iterator<PcmSaleUnitDict> it = list.iterator();
		while (it.hasNext()) {
			PcmSaleUnitDict temp = it.next();
			Map<String, Object> tempMap = new HashMap<String, Object>();
			tempMap.put("code", temp.getUnitCode());
			tempMap.put("name", temp.getUnitName());
			resultList.add(tempMap);
		}
		logger.info("end up pushUnitFromPcm");
		return resultList;
	}
}
