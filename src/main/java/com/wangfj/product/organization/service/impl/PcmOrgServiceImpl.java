package com.wangfj.product.organization.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangfj.product.organization.persistence.PcmOrganizationMapper;
import com.wangfj.product.organization.service.intf.IPcmOrgService;

@Service
public class PcmOrgServiceImpl implements IPcmOrgService {

	private static final Logger logger = LoggerFactory.getLogger(PcmOrgServiceImpl.class);

	@Autowired
	private PcmOrganizationMapper organizationMapper;

	/**
	 * 搜索线上查询所有门店
	 * 
	 * @Methods Name searchOnlineShopList
	 * @Create In 2015-11-10 By wangxuan
	 * @return List<Map<String,Object>>
	 */
	@Override
	public List<Map<String, Object>> searchOnlineShopList() {
		logger.info("start searchOnlineShopList()");
		List<Map<String, Object>> shopList = organizationMapper.searchOnlineShopList();

		logger.info("end searchOnlineShopList(),return:" + shopList.toString());
		return shopList;

	}

}
