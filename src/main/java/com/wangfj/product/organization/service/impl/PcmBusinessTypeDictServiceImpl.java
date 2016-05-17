package com.wangfj.product.organization.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangfj.core.framework.base.page.Page;
import com.wangfj.product.organization.domain.entity.PcmBusinessTypeDict;
import com.wangfj.product.organization.domain.entity.PcmShoppe;
import com.wangfj.product.organization.domain.vo.PcmBusinessTypeDictDto;
import com.wangfj.product.organization.persistence.PcmBusinessTypeDictMapper;
import com.wangfj.product.organization.persistence.PcmShoppeMapper;
import com.wangfj.product.organization.service.intf.IPcmBusinessTypeDictService;
import com.wangfj.util.Constants;

/**
 * 经营方式字典service
 * 
 * @Class Name IPcmBusinessTypeDictService
 * @Author zhangxy
 * @Create In 2015年7月29日
 */
@Service
public class PcmBusinessTypeDictServiceImpl implements IPcmBusinessTypeDictService {
	private static final Logger logger = LoggerFactory
			.getLogger(PcmBusinessTypeDictServiceImpl.class);
	@Autowired
	PcmBusinessTypeDictMapper packMapper;
	@Autowired
	PcmShoppeMapper shoppeMapper;

	/**
	 * 新增一条经营方式
	 * 
	 * @Methods Name saveBusinessType
	 * @Create In 2015年7月29日 By zhangxy
	 * @param PcmBusinessTypeDict
	 * @return int 0失败 1成功 2已存在
	 * @throws Exception
	 */
	@Override
	public int saveBusinessTypeDict(PcmBusinessTypeDict entity) {
		logger.info("start saveBusinessTypeDict() param:" + entity.toString());
		int res = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("businessName", entity.getBusinessName());
		map.put("businessCode", entity.getBusinessCode());
		// 根据经营方式 与 编码 判重
		List<PcmBusinessTypeDict> li = packMapper.selectValidByParam(map);
		if (li != null && li.size() > 0) {
			// 已存在
			res = 2;
		} else {
			res = packMapper.insertSelective(entity);
		}
		logger.info("end saveBusinessTypeDict() param:" + res);
		return res;
	}

	/**
	 * 修改一条经营方式
	 * 
	 * @Methods Name updateBusinessType
	 * @Create In 2015年7月29日 By zhangxy
	 * @param PcmBusinessTypeDict
	 * @return int 0失败 1成功 2已存在
	 * @throws Exception
	 */
	@Override
	public int updateBusinessTypeDict(PcmBusinessTypeDict entity) {
		logger.info("start updateBusinessType() param:" + entity.toString());
		int res = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("businessName", entity.getBusinessName());
		map.put("businessCode", entity.getBusinessCode());

		// 根据经营方式 与 编码 判重
		List<PcmBusinessTypeDict> li = packMapper.selectValidByParam(map);
		if (li != null && li.size() > 0) {
			// 已存在
			res = 2;
		} else {
			if (entity.getStatus() != null) {
				entity.setStatus(null);
			}
			res = packMapper.updateByPrimaryKeySelective(entity);
		}
		logger.info("end updateBusinessType() param:" + res);
		return res;
	}

	/**
	 * 删除一条经营方式
	 * 
	 * @Methods Name deleteBusinessType
	 * @Create In 2015年7月29日 By zhangxy
	 * @param PcmBusinessTypeDict
	 * @return int 0失败 1成功 2已存在关联
	 * @throws Exception
	 */
	@Override
	public int deleteBusinessTypeDict(PcmBusinessTypeDict entity) {
		logger.info("start deleteBusinessType() param:" + entity.toString());
		int res = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("businessTypeSid", entity.getSid());
		map.put("start", Constants.PUBLIC_0);
		map.put("limit", Constants.PUBLIC_1);
		// 判断是否存在关联
		List<PcmShoppe> li = shoppeMapper.selectPageListByParam(map);
		if (li != null && li.size() > 0) {
			// 已存在关联
			res = 2;
		} else {
			PcmBusinessTypeDict record = new PcmBusinessTypeDict();
			record.setSid(entity.getSid());
			record.setStatus(1);
			res = packMapper.updateByPrimaryKeySelective(record);
		}
		logger.info("end deleteBusinessType() param:" + res);
		return res;
	}

	/**
	 * 查询经营方式
	 * 
	 * @Methods Name selectBusinessTypeDict
	 * @Create In 2015年8月3日 By zhangxy
	 * @param dto
	 * @return List<PcmBusinessTypeDict>
	 * @throws Exception
	 */
	@Override
	public Page<PcmBusinessTypeDict> selectBusinessTypeDict(PcmBusinessTypeDictDto dto) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sid", dto.getSid());
		map.put("businessCode", dto.getBusinessCode());
		map.put("businessName", dto.getBusinessName());
		map.put("optUserSid", dto.getOptUserSid());
		map.put("status", 0);
		Integer count = packMapper.getCountByParam(map);
		Page<PcmBusinessTypeDict> page = new Page<PcmBusinessTypeDict>();
		if (dto.getCurrentPage() != null && dto.getCurrentPage() != 0) {
			page.setCurrentPage(dto.getCurrentPage());
		}
		if (dto.getPageSize() != null && dto.getPageSize() != 0) {
			page.setPageSize(dto.getPageSize());
		} else if (dto.getLimit() != null) {
			page.setPageSize(dto.getLimit());
		} else {
			page.setPageSize(20);
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
		}
		List<PcmBusinessTypeDict> list = packMapper.selectPageListByParam(map);
		page.setList(list);
		return page;
	}
}
