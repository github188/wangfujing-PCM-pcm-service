package com.wangfj.product.stocks.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangfj.product.stocks.domain.entity.PcmStockTypeDict;
import com.wangfj.product.stocks.persistence.PcmStockTypeDictMapper;
import com.wangfj.product.stocks.service.intf.IPcmStockTypeDictService;

@Service
public class PcmStockTypeDictServiceImpl implements IPcmStockTypeDictService {
	@Autowired
	public PcmStockTypeDictMapper pcmStockTypeDoctMapper;

	/**
	 * 添加锁定类型
	 * 
	 * @Methods Name insertSelective
	 * @Create In 2015年7月31日 By yedong
	 * @param record
	 * @return int
	 */
	public int insertSelective(PcmStockTypeDict record) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("stockName", record.getStockName());
		List<PcmStockTypeDict> list = pcmStockTypeDoctMapper.selectListByParam(paramMap);
		int status = 0;
		if (list.size() == 0) {
			status = pcmStockTypeDoctMapper.insertSelective(record);
		}
		return status;
	}

	/**
	 * 修改
	 * 
	 * @Methods Name updateSelective
	 * @Create In 2015年7月31日 By yedong
	 * @param record
	 * @return int
	 */
	public int updateSelective(PcmStockTypeDict record) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sid", record.getSid());
		paramMap.put("stockName", record.getStockName());
		List<PcmStockTypeDict> list = pcmStockTypeDoctMapper.selectListByParam(paramMap);
		int status = 0;
		if (list.size() == 0) {
			status = pcmStockTypeDoctMapper.updateByPrimaryKeySelective(record);
		}
		return status;
	}

	/**
	 * 查询
	 * 
	 * @Methods Name selectListByParam
	 * @Create In 2015年7月31日 By yedong
	 * @param paramMap
	 * @return List<PcmLockTypeDict>
	 */
	public List<PcmStockTypeDict> selectListByParam(Map<String, Object> paramMap) {
		List<PcmStockTypeDict> list = pcmStockTypeDoctMapper.selectListByParam(paramMap);
		return list;
	}
}
