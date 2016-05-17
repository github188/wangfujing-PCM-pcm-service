package com.wangfj.product.organization.service.intf;

import java.util.List;
import java.util.Map;

public interface IPcmOrgService {

	/**
	 * 搜索线上查询所有门店
	 * 
	 * @Methods Name searchOnlineShopList
	 * @Create In 2015-11-10 By wangxuan
	 * @return List<Map<String,Object>>
	 */
	List<Map<String, Object>> searchOnlineShopList();

}
