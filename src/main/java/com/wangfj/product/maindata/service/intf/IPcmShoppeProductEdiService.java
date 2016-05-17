package com.wangfj.product.maindata.service.intf;

import java.util.List;
import java.util.Map;

import com.wangfj.product.maindata.domain.entity.PcmShoppeProductEdiRelation;

public interface IPcmShoppeProductEdiService {
	/**
	 * Edi-Oms查询商品支付减信息
	 * 
	 * @Methods Name getProIsPayReduceInfo
	 * @Create In 2016年3月7日 By yedong
	 * @param paramMap
	 * @return PcmShoppeProductEdiRelation
	 */
	public List<PcmShoppeProductEdiRelation> getProIsPayReduceInfo(Map<String, Object> paramMap);
}
