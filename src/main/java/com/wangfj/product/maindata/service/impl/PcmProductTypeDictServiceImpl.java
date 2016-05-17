package com.wangfj.product.maindata.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangfj.product.maindata.domain.entity.PcmProductTypeDict;
import com.wangfj.product.maindata.persistence.PcmProductTypeDictMapper;
import com.wangfj.product.maindata.service.intf.IPcmProductTypeDictService;

/**
 * 产品接口：商品属性修改service
 * 
 * @Class Name IPcmProductService
 * @Author chengsj
 * @Create In 2015-8-4
 */
@Service
public class PcmProductTypeDictServiceImpl implements IPcmProductTypeDictService {
	@Autowired
	PcmProductTypeDictMapper pptdMapper;

	/**
	 * 查询商品类型
	 * 
	 * @Methods Name selectProductType
	 * @Create In 2015年9月11日 By zhangxy
	 * @param dto
	 * @return List<PcmProductTypeDict>
	 */
	@Override
	public List<PcmProductTypeDict> selectProductType(PcmProductTypeDict dto) {
		return pptdMapper.selectListByParam(dto);
	}
}
