package com.wangfj.product.stocks.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangfj.product.stocks.domain.entity.PcmShoppeProRelation;
import com.wangfj.product.stocks.persistence.PcmShoppeProRelationMapper;
import com.wangfj.product.stocks.service.intf.IPcmShoppeProRelationService;

@Service
public class PcmShoppeProRelationServiceImpl implements IPcmShoppeProRelationService {
	@Autowired
	private PcmShoppeProRelationMapper pcmShoppeProRelationMapper;

	public List<PcmShoppeProRelation> getSubPro(Long parentCode) {
		PcmShoppeProRelation record = new PcmShoppeProRelation();
		record.setParentCode(parentCode);
		List<PcmShoppeProRelation> list = pcmShoppeProRelationMapper.getSubPro(record);
		return list;
	}

}
