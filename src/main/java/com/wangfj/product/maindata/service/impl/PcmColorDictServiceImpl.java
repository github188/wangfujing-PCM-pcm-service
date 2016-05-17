package com.wangfj.product.maindata.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangfj.core.framework.base.page.Page;
import com.wangfj.product.maindata.domain.entity.PcmColorDict;
import com.wangfj.product.maindata.domain.entity.PcmProDetail;
import com.wangfj.product.maindata.domain.vo.ColorDictDto;
import com.wangfj.product.maindata.persistence.PcmColorDictMapper;
import com.wangfj.product.maindata.persistence.PcmProDetailMapper;
import com.wangfj.product.maindata.service.intf.IPcmColorDictService;
import com.wangfj.util.Constants;

/**
 * 色系字典service
 * 
 * @Class Name IPcmColorDictService
 * @Author zhangxy
 * @Create In 2015年7月29日
 */
@Service
public class PcmColorDictServiceImpl implements IPcmColorDictService {
	private static final Logger logger = LoggerFactory.getLogger(PcmColorDictServiceImpl.class);
	@Autowired
	PcmColorDictMapper colorMapper;
	@Autowired
	PcmProDetailMapper proMapper;

	/**
	 * 新增一条色系
	 * 
	 * @Methods Name saveColor
	 * @Create In 2015年7月29日 By zhangxy
	 * @param PcmColorDict
	 * @return int 0失败 1成功 2已存在
	 * @throws Exception
	 */
	@Override
	public int saveColorDict(PcmColorDict entity) {
		logger.info("start saveColorDict() param:" + entity.toString());
		int res = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("colorName", entity.getColorName());
		map.put("status", 0);
		// 根据色系名判重
		List<PcmColorDict> li = colorMapper.selectListByParam(map);
		if (li != null && li.size() > 0) {
			// 已存在
			res = 2;
		} else {
			res = colorMapper.insertSelective(entity);
		}
		logger.info("end saveColorDict() param:" + res);
		return res;
	}

	/**
	 * 修改一条色系
	 * 
	 * @Methods Name updateColor
	 * @Create In 2015年7月29日 By zhangxy
	 * @param PcmColorDict
	 * @return int 0失败 1成功 2已存在
	 * @throws Exception
	 */
	@Override
	public int updateColorDict(PcmColorDict entity) {
		logger.info("start updateColor() param:" + entity.toString());
		int res = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("colorName", entity.getColorName());
		map.put("status", 0);
		// 根据色系名判重
		List<PcmColorDict> li = colorMapper.selectListByParam(map);
		if (li != null && li.size() > 0) {
			// 已存在
			res = 2;
		} else {
			if (entity.getStatus() != null) {
				entity.setStatus(null);
			}
			res = colorMapper.updateByPrimaryKeySelective(entity);
		}
		logger.info("end updateColor() param:" + res);
		return res;
	}

	/**
	 * 删除一条色系
	 * 
	 * @Methods Name deleteColor
	 * @Create In 2015年7月29日 By zhangxy
	 * @param PcmColorDict
	 * @return int 0失败 1成功 2已存在关联
	 * @throws Exception
	 */
	@Override
	public int deleteColorDict(PcmColorDict entity) {
		logger.info("start deleteColor() param:" + entity.toString());
		int res = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("proColorSid", entity.getSid());
		map.put("start", Constants.PUBLIC_0);
		map.put("limit", Constants.PUBLIC_1);
		// 判断是否存在关联
		List<PcmProDetail> li = proMapper.selectPageListByParam(map);
		if (li != null && li.size() > 0) {
			// 已存在关联
			res = 2;
		} else {
			PcmColorDict record = new PcmColorDict();
			record.setSid(entity.getSid());
			record.setStatus(1);
			res = colorMapper.updateByPrimaryKeySelective(record);
		}
		logger.info("end deleteColor() param:" + res);
		return res;
	}

	/**
	 * 查询色系
	 * 
	 * @Methods Name selectColorDict
	 * @Create In 2015年8月3日 By zhangxy
	 * @param dto
	 * @return List<PcmBusinessTypeDict>
	 * @throws Exception
	 */
	public Page<PcmColorDict> selectColorDict(ColorDictDto dto) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sid", dto.getSid());
		map.put("colorAlias", dto.getColorAlias());
		map.put("colorName", dto.getColorName());
		map.put("status", 0);
		Integer count = colorMapper.getCountByParam(map);
		Page<PcmColorDict> page = new Page<PcmColorDict>();
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
		} else {
			map.put("start", 0);
		}
		map.put("limit", page.getLimit());
		List<PcmColorDict> list = colorMapper.selectPageListByParam(map);
		page.setList(list);
		return page;
	}
}
