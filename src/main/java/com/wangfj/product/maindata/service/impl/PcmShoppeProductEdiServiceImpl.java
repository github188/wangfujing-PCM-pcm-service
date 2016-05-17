package com.wangfj.product.maindata.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangfj.product.maindata.domain.entity.PcmShoppeProductEdiRelation;
import com.wangfj.product.maindata.persistence.PcmShoppeProductEdiRelationMapper;
import com.wangfj.product.maindata.service.intf.IPcmShoppeProductEdiService;

@Service
public class PcmShoppeProductEdiServiceImpl implements IPcmShoppeProductEdiService {

	@Autowired
	private PcmShoppeProductEdiRelationMapper ediMapper;

	/**
	 * Edi-Oms查询商品支付减信息
	 * 
	 * @Methods Name getProIsPayReduceInfo
	 * @Create In 2016年3月7日 By yedong
	 * @param paramMap
	 * @return PcmShoppeProductEdiRelation
	 */
	public List<PcmShoppeProductEdiRelation> getProIsPayReduceInfo(Map<String, Object> paramMap) {
		return ediMapper.getProIsPayReduceInfo(paramMap);
	}
}
