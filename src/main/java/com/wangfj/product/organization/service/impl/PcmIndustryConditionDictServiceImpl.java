package com.wangfj.product.organization.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangfj.product.organization.persistence.PcmIndustryConditionDictMapper;
import com.wangfj.product.organization.service.intf.IPcmIndustryConditionDictService;

@Service
public class PcmIndustryConditionDictServiceImpl implements IPcmIndustryConditionDictService {
	@Autowired
	public PcmIndustryConditionDictMapper pcmIndustryConditionDictMapper;
}
