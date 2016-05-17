package com.wangfj.product.category.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangfj.product.category.domain.entity.PcmCategoryMq;
import com.wangfj.product.category.persistence.PcmCategoryMqMapper;
import com.wangfj.product.category.service.intf.ICategoryMQService;

@Service
public class CategoryMQServiceImpl implements ICategoryMQService {

	@Autowired
	private PcmCategoryMqMapper ssdCategoryMQMapper;

	public int save(PcmCategoryMq record) {
		return this.ssdCategoryMQMapper.insertSelective(record);
	}

	public int updateSelective(PcmCategoryMq record) {
		return this.ssdCategoryMQMapper.updateSelective(record);
	}

	public List<PcmCategoryMq> selectList(String msgContext) {
		return this.ssdCategoryMQMapper.selectList(msgContext);
	}

	public int updateFlag(String msgContext) {
		return this.ssdCategoryMQMapper.updateFlag(msgContext);
	}

}
