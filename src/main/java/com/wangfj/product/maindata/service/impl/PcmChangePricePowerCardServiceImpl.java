package com.wangfj.product.maindata.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangfj.product.maindata.service.intf.IPcmChangePricePowerCardService;
import com.wangfj.product.price.domain.entity.PcmChangePricePowerCard;
import com.wangfj.product.price.persistence.PcmChangePricePowerCardMapper;

@Service
public class PcmChangePricePowerCardServiceImpl implements IPcmChangePricePowerCardService {

	@Autowired
	private PcmChangePricePowerCardMapper changePriceCardMapper;

	public void addChangePricePowerCard(PcmChangePricePowerCard entity) {
		changePriceCardMapper.insertSelective(entity);
	}
}
