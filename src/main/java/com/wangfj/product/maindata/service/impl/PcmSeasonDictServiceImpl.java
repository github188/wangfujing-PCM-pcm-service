package com.wangfj.product.maindata.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangfj.product.maindata.domain.entity.PcmSeasonDict;
import com.wangfj.product.maindata.persistence.PcmSeasonDictMapper;
import com.wangfj.product.maindata.service.intf.IPcmSeasonDictService;

@Service
public class PcmSeasonDictServiceImpl implements IPcmSeasonDictService {
	@Autowired
	PcmSeasonDictMapper seasonDictMapper;
	
	public List<PcmSeasonDict> selectListByParam(){
		PcmSeasonDict entity = new PcmSeasonDict();
		List<PcmSeasonDict> seasonList = seasonDictMapper.selectListByParam(entity);
		return seasonList;
	}
}
