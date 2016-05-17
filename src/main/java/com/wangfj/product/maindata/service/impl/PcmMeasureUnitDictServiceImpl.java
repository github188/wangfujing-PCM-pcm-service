package com.wangfj.product.maindata.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangfj.core.framework.base.page.Page;
import com.wangfj.product.maindata.domain.entity.PcmMeasureUnitDict;
import com.wangfj.product.maindata.domain.entity.PcmShoppeProduct;
import com.wangfj.product.maindata.domain.vo.PcmMeasureUnitDto;
import com.wangfj.product.maindata.persistence.PcmMeasureUnitDictMapper;
import com.wangfj.product.maindata.persistence.PcmShoppeProductMapper;
import com.wangfj.product.maindata.service.intf.IPcmMeasureUnitDictService;
import com.wangfj.util.Constants;

/**
 * 计量单位service
 * 
 * @Class Name IPcmMeasureUnitDictService
 * @Author zhangxy
 * @Create In 2015年7月29日
 */
@Service
public class PcmMeasureUnitDictServiceImpl implements IPcmMeasureUnitDictService {
	private static final Logger logger = LoggerFactory
			.getLogger(PcmMeasureUnitDictServiceImpl.class);
	@Autowired
	PcmMeasureUnitDictMapper measureMapper;
	@Autowired
	PcmShoppeProductMapper proMapper;

	/**
	 * 新增一计量单位
	 * 
	 * @Methods Name saveMeasureUnit
	 * @Create In 2015年7月29日 By zhangxy
	 * @param PcmMeasureUnitDto
	 * @return int 0失败 1成功 2已存在
	 * @throws Exception
	 */
	@Override
	public int saveMeasureUnit(PcmMeasureUnitDict entity) {
		logger.info("start saveMeasureUnit() param:" + entity.toString());
		int res = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("unitName", entity.getUnitName());
		map.put("status", 0);
		// 根据单位名判重
		List<PcmMeasureUnitDict> li = measureMapper.selectListByParam(map);
		if (li != null && li.size() > 0) {
			// 已存在
			res = 2;
		} else {
			res = measureMapper.insertSelective(entity);
		}
		logger.info("end saveMeasureUnit() param:" + res);
		return res;
	}

	/**
	 * 修改一条计量单位
	 * 
	 * @Methods Name updateMeasureUnit
	 * @Create In 2015年7月29日 By zhangxy
	 * @param PcmMeasureUnitDto
	 * @return int 0失败 1成功 2已存在
	 * @throws Exception
	 */
	@Override
	public int updateMeasureUnit(PcmMeasureUnitDict entity) {
		logger.info("start updateMeasureUnit() param:" + entity.toString());
		int res = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("unitName", entity.getUnitName());
		map.put("status", 0);
		// 根据单位名判重
		List<PcmMeasureUnitDict> li = measureMapper.selectListByParam(map);
		if (li != null && li.size() > 0) {
			// 已存在
			res = 2;
		} else {
			res = measureMapper.updateByPrimaryKeySelective(entity);
		}
		logger.info("end updateMeasureUnit() param:" + res);
		return res;
	}

	/**
	 * 删除一条计量单位
	 * 
	 * @Methods Name deleteMeasureUnit
	 * @Create In 2015年7月29日 By zhangxy
	 * @param PcmMeasureUnitDto
	 * @return int 0失败 1成功 2已存在关联
	 * @throws Exception
	 */
	@Override
	public int deleteMeasureUnit(PcmMeasureUnitDict entity) {
		logger.info("start deleteMeasureUnit() param:" + entity.toString());
		int res = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("measureUnitDictSid", entity.getSid());
		map.put("start", Constants.PUBLIC_0);
		map.put("limit", Constants.PUBLIC_1);
		// 判断是否存在关联
		List<PcmShoppeProduct> li = proMapper.selectPageListByParam(map);
		if (li != null && li.size() > 0) {
			// 已存在关联
			res = 2;
		} else {
			PcmMeasureUnitDict record = new PcmMeasureUnitDict();
			record.setSid(entity.getSid());
			record.setStatus(1);
			res = measureMapper.updateByPrimaryKeySelective(record);
		}
		logger.info("end deleteMeasureUnit() param:" + res);
		return res;
	}

	/**
	 * 查询计量单位
	 * 
	 * @Methods Name selectMeasureUnitDict
	 * @Create In 2015年8月3日 By zhangxy
	 * @param dto
	 * @return List<PcmBusinessTypeDict>
	 * @throws Exception
	 */
	public Page<PcmMeasureUnitDict> selectMeasureUnit(PcmMeasureUnitDto dto) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sid", dto.getSid());
		map.put("unitDesc", dto.getUnitDesc());
		map.put("unitName", dto.getUnitName());
		map.put("status", 0);
		Integer count = measureMapper.getCountByParam(map);
		Page<PcmMeasureUnitDict> page = new Page<PcmMeasureUnitDict>();
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
		List<PcmMeasureUnitDict> list = measureMapper.selectPageListByParam(map);
		page.setList(list);
		return page;
	}
}
