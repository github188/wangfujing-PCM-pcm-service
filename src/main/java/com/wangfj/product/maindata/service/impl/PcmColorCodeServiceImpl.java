package com.wangfj.product.maindata.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangfj.core.framework.base.page.Page;
import com.wangfj.product.maindata.domain.entity.PcmColorCodeDict;
import com.wangfj.product.maindata.persistence.PcmColorCodeDictMapper;
import com.wangfj.product.maindata.service.intf.IPcmColorCodeService;
import com.wangfj.util.Constants;

@Service
/**
 * 色码字典表service
 * @Class Name PcmColorCodeServiceImpl
 * @Author zhangxy
 * @Create In 2015年7月17日
 */
public class PcmColorCodeServiceImpl implements IPcmColorCodeService {
	private static final Logger logger = LoggerFactory.getLogger(PcmColorCodeServiceImpl.class);

	@Autowired
	PcmColorCodeDictMapper mapper;

	/**
	 * 色码下发
	 * 
	 * @Methods Name selectColorCode
	 * @Create In 2015年7月17日 By zhangxueyi
	 * @param paramMap
	 * 
	 */
	@Override
	public Page<PcmColorCodeDict> selectColorCode(Map<String, Object> paramMap) throws Exception {
		logger.info("start selectColorCode() 分发色码字典信息");
		Page<PcmColorCodeDict> page = new Page<PcmColorCodeDict>();
		if (paramMap.get("currentPage") != null) {
			page.setCurrentPage((Integer) paramMap.get("currentPage"));
		}
		if (paramMap.get("pageSize") != null) {
			page.setPageSize((Integer) paramMap.get("pageSize"));
		}
		Integer count = mapper.getCountByParam(paramMap);
		page.setCount(count);
		if (paramMap.get("currentPage") != null && paramMap.get("pageSize") != null) {
			paramMap.put("start", page.getStart());
			paramMap.put("limit", page.getLimit());
		} else if (paramMap.get("start") == null) {
			paramMap.put("start", 0);
		}
		List<PcmColorCodeDict> list = mapper.selectPageListByParam(paramMap);
		if (!list.isEmpty()) {
			page.setList(list);
		} else {
			logger.info("end selectColorCode() 分发色码字典信息失败，查询结果为空");
		}
		logger.info("end selectColorCode() 分发色码字典信息  list:" + list.toString());
		return page;
	}

	/**
	 * 新增色码字典表
	 */
	@Override
	public int insertColorCodeDict(Map<String, Object> map) {
		PcmColorCodeDict pccd = new PcmColorCodeDict();
		pccd.setBrandCode((String) map.get("brandCode"));
		pccd.setColorCode((String) map.get("colorCode"));
		pccd.setColorName((String) map.get("colorName"));
		map.remove("colorName");
		map.remove("brandCode");
		List<PcmColorCodeDict> lpccd = mapper.selectListByParam(map);
		if (lpccd == null || lpccd.size() < Constants.PUBLIC_1) {
			int i = mapper.insertSelective(pccd);
			return i;
		} else {
			return Constants.PUBLIC_2;
		}
	}

}
