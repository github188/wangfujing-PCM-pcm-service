package com.wangfj.product.maindata.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangfj.core.framework.base.page.Page;
import com.wangfj.product.maindata.domain.entity.PcmPackUnitDict;
import com.wangfj.product.maindata.domain.entity.PcmShoppeProduct;
import com.wangfj.product.maindata.domain.vo.PcmPackUnitDto;
import com.wangfj.product.maindata.persistence.PcmPackUnitDictMapper;
import com.wangfj.product.maindata.persistence.PcmShoppeProductMapper;
import com.wangfj.product.maindata.service.intf.IPcmPackUnitDictService;
import com.wangfj.util.Constants;

/**
 * 包装单位service
 * 
 * @Class Name IPcmPackUnitDictService
 * @Author zhangxy
 * @Create In 2015年7月29日
 */
@Service
public class PcmPackUnitDictServiceImpl implements IPcmPackUnitDictService {
	private static final Logger logger = LoggerFactory.getLogger(PcmPackUnitDictServiceImpl.class);
	@Autowired
	PcmPackUnitDictMapper packMapper;
	@Autowired
	PcmShoppeProductMapper proMapper;

	/**
	 * 新增一条包装单位
	 * 
	 * @Methods Name savePackUnit
	 * @Create In 2015年7月29日 By zhangxy
	 * @param PcmPackUnitDto
	 * @return int 0失败 1成功 2已存在
	 * @throws Exception
	 */
	@Override
	public int savePackUnit(PcmPackUnitDict entity) {
		logger.info("start savePackUnit() param:" + entity.toString());
		int res = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("unitName", entity.getUnitName());
		map.put("status", 0);
		// 根据单位名判重
		List<PcmPackUnitDict> li = packMapper.selectListByParam(map);
		if (li != null && li.size() > 0) {
			// 已存在
			res = 2;
		} else {
			res = packMapper.insertSelective(entity);
		}
		logger.info("end savePackUnit() param:" + res);
		return res;
	}

	/**
	 * 修改一条包装单位
	 * 
	 * @Methods Name updatePackUnit
	 * @Create In 2015年7月29日 By zhangxy
	 * @param PcmPackUnitDto
	 * @return int 0失败 1成功 2已存在
	 * @throws Exception
	 */
	@Override
	public int updatePackUnit(PcmPackUnitDict entity) {
		logger.info("start updatePackUnit() param:" + entity.toString());
		int res = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("unitName", entity.getUnitName());
		map.put("status", 0);
		// 根据单位名判重
		List<PcmPackUnitDict> li = packMapper.selectListByParam(map);
		if (li != null && li.size() > 0) {
			// 已存在
			res = 2;
		} else {
			res = packMapper.updateByPrimaryKeySelective(entity);
		}
		logger.info("end updatePackUnit() param:" + res);
		return res;
	}

	/**
	 * 删除一条包装单位
	 * 
	 * @Methods Name deletePackUnit
	 * @Create In 2015年7月29日 By zhangxy
	 * @param PcmPackUnitDto
	 * @return int 0失败 1成功 2已存在关联
	 * @throws Exception
	 */
	@Override
	public int deletePackUnit(PcmPackUnitDict entity) {
		logger.info("start deletePackUnit() param:" + entity.toString());
		int res = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("packUnitDictSid", entity.getSid());
		map.put("start", Constants.PUBLIC_0);
		map.put("limit", Constants.PUBLIC_1);
		// 判断是否存在关联
		List<PcmShoppeProduct> li = proMapper.selectPageListByParam(map);
		if (li != null && li.size() > 0) {
			// 已存在关联
			res = 2;
		} else {
			PcmPackUnitDict record = new PcmPackUnitDict();
			record.setSid(entity.getSid());
			record.setStatus(1);
			res = packMapper.updateByPrimaryKeySelective(record);
		}
		logger.info("end deletePackUnit() param:" + res);
		return res;
	}

	/**
	 * 查询包装单位
	 * 
	 * @Methods Name selectPackUnitDict
	 * @Create In 2015年8月3日 By zhangxy
	 * @param dto
	 * @return List<PcmPackUnitDict>
	 * @throws Exception
	 */
	public Page<PcmPackUnitDict> selectPackUnit(PcmPackUnitDto dto) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sid", dto.getSid());
		map.put("unitDesc", dto.getUnitDesc());
		map.put("unitName", dto.getUnitName());
		map.put("status", 0);
		Integer count = packMapper.getCountByParam(map);
		Page<PcmPackUnitDict> page = new Page<PcmPackUnitDict>();
		if (dto.getCurrentPage() != null && dto.getCurrentPage() != 0) {
			page.setCurrentPage(dto.getCurrentPage());
		}
		if (dto.getPageSize() != null && dto.getPageSize() != 0) {
			page.setPageSize(dto.getPageSize());
		} else {
			page.setPageSize(200);
		}
		page.setCount(count);
		if (dto.getStart() != null && dto.getLimit() != null) {
			map.put("start", dto.getStart());
			map.put("limit", dto.getLimit());
			page.setStart(dto.getStart());
			page.setLimit(dto.getLimit());
		} else if (dto.getPageSize() != null && dto.getPageSize() != 0) {
			map.put("start", page.getStart());
			map.put("limit", page.getLimit());
		} else {
			map.put("start", 0);
			map.put("limit", page.getLimit());
		}
		List<PcmPackUnitDict> list = packMapper.selectPageListByParam(map);
		page.setList(list);
		return page;
	}
}
