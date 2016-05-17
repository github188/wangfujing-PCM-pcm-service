package com.wangfj.product.maindata.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangfj.core.framework.base.page.Page;
import com.wangfj.product.brand.domain.entity.PcmBrand;
import com.wangfj.product.brand.persistence.PcmBrandMapper;
import com.wangfj.product.demo.service.impl.UserServiceImpl;
import com.wangfj.product.maindata.domain.entity.PcmStanDict;
import com.wangfj.product.maindata.persistence.PcmStanDictMapper;
import com.wangfj.product.maindata.service.intf.IPcmStanDictService;
import com.wangfj.util.Constants;

/**
 * 尺寸信息下发（查询）
 * 
 * @class Name PcmStanDictServiceImpl
 * @author wangxiang
 * @Create in 2015年7月17
 *
 */
@Service
public class PcmStanDictServiceImpl implements IPcmStanDictService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	PcmStanDictMapper stanDictMapper;
	// 门店品牌
	@Autowired
	private PcmBrandMapper brandMapper;

	@Override
	public Page<PcmStanDict> pushSizeFromPCM(Map<String, Object> map) {
		logger.info("start pushSizeFromPCM");
		Page<PcmStanDict> page = new Page<PcmStanDict>();
		if (map.get("currentPage") != null) {
			page.setCurrentPage((Integer) map.get("currentPage"));
		}
		if (map.get("pageSize") != null) {
			page.setPageSize((Integer) map.get("pageSize"));
		}
		Integer count = stanDictMapper.getCountByParam(map);
		page.setCount(count);
		if (map.get("currentPage") != null && map.get("pageSize") != null) {
			map.put("start", page.getStart());
			map.put("limit", page.getLimit());
		} else if (map.get("start") == null) {
			map.put("start", 0);
		}
		List<PcmStanDict> list = stanDictMapper.selectPageListByParam(map);
		if (!list.isEmpty()) {
			page.setList(list);
		} else {
			logger.info("end pushSizeFromPCM() 分发尺码字典信息失败，查询结果为空");
		}
		logger.info("end pushSizeFromPCM() 分发尺码字典信息  list:");
		return page;
	}

	/**
	 * 新增尺码/规格字典数据
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public int insertPcmStanDict(Map map) {
		Map m = new HashMap();
		// 验重[门店品牌编码+规格名称]
		// m.put("brandSid", map.get("brandSid"));// 门店品牌编码
		m.put("proStanName", map.get("proStanName"));// 规格名称
		List<PcmStanDict> lpd = stanDictMapper.selectListByParam(m);
		if (lpd == null || lpd.size() < Constants.PUBLIC_1) {
			PcmStanDict psd = new PcmStanDict();
			// 获取门店品牌名称
			List<PcmBrand> lpb = brandMapper.selectListByParam(map);
			psd.setProStanName(String.valueOf(map.get("proStanName")));
			psd.setProStanSid(String.valueOf(map.get("proStanSid")));
			psd.setBrandName(lpb.get(Constants.PUBLIC_0).getBrandName());// 品牌名称
			psd.setBrandRootSid(String.valueOf(map.get("brandRootSid")));// 集团品牌编码
			psd.setBrandSid(String.valueOf(map.get("brandSid")));// 门店品牌编码
			psd.setCategorySid((Long) map.get("categorySid"));// 工业分类编码
			int i = stanDictMapper.insertSelective(psd);
			return i;
		} else {
			return Constants.PUBLIC_2;
		}
	}
}
