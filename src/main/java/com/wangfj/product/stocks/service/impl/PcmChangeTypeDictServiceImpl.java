package com.wangfj.product.stocks.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangfj.product.stocks.domain.entity.PcmChangeTypeDict;
import com.wangfj.product.stocks.persistence.PcmChangeTypeDictMapper;
import com.wangfj.product.stocks.service.intf.IPcmChangeTypeDictService;

@Service
public class PcmChangeTypeDictServiceImpl implements IPcmChangeTypeDictService {
	@Autowired
	public PcmChangeTypeDictMapper pcmChangeTypeMapper;

	/**
	 * 添加锁定类型
	 * 
	 * @Methods Name insertSelective
	 * @Create In 2015年7月31日 By yedong
	 * @param record
	 * @return int
	 */
	public int insertSelective(PcmChangeTypeDict record) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("changeName", record.getChangeName());
		List<PcmChangeTypeDict> list = pcmChangeTypeMapper.selectListByParam(paramMap);
		int status = 0;
		if (list.size() == 0 || list.get(0) == null) {
			status = pcmChangeTypeMapper.insertSelective(record);
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
	public int updateSelective(PcmChangeTypeDict record) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sid", record.getSid());
		paramMap.put("changeName", record.getChangeName());
		List<PcmChangeTypeDict> list = pcmChangeTypeMapper.selectListByParam(paramMap);
		int status = 0;
		if (list.size() == 0) {
			status = pcmChangeTypeMapper.updateByPrimaryKeySelective(record);
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
	public List<PcmChangeTypeDict> selectListByParam(Map<String, Object> paramMap) {
		List<PcmChangeTypeDict> list = pcmChangeTypeMapper.selectListByParam(paramMap);
		return list;
	}
}
