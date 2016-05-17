package com.wangfj.product.stocks.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangfj.product.stocks.domain.entity.PcmLockTypeDict;
import com.wangfj.product.stocks.persistence.PcmLockTypeDictMapper;
import com.wangfj.product.stocks.service.intf.IPcmLockTypeDictService;

@Service
public class PcmLockTypeDictServiceImpl implements IPcmLockTypeDictService {
	@Autowired
	private PcmLockTypeDictMapper pcmLockTypeDictMapper;

	/**
	 * 添加锁定类型
	 * 
	 * @Methods Name insertSelective
	 * @Create In 2015年7月31日 By yedong
	 * @param record
	 * @return int
	 */
	public int insertSelective(PcmLockTypeDict record) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("lockTypeName", record.getLockTypeName());
		List<PcmLockTypeDict> list = pcmLockTypeDictMapper.selectListByParam(paramMap);
		int status = 0;
		if (list.size() == 0) {
			status = pcmLockTypeDictMapper.insertSelective(record);
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
	public int updateSelective(PcmLockTypeDict record) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sid", record.getSid());
		paramMap.put("lockTypeName", record.getLockTypeName());
		List<PcmLockTypeDict> list = pcmLockTypeDictMapper.selectListByParam(paramMap);
		int status = 0;
		if (list.size() == 0) {
			status = pcmLockTypeDictMapper.updateByPrimaryKeySelective(record);
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
	public List<PcmLockTypeDict> selectListByParam(Map<String, Object> paramMap) {
		List<PcmLockTypeDict> list = pcmLockTypeDictMapper.selectListByParam(paramMap);
		return list;
	}
}
