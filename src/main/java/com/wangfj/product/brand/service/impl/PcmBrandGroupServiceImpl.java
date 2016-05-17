package com.wangfj.product.brand.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangfj.core.constants.ComErrorCodeConstants;
import com.wangfj.core.framework.base.page.Page;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.product.brand.domain.entity.PcmBrandGroup;
import com.wangfj.product.brand.domain.vo.PcmBrandGroupDto;
import com.wangfj.product.brand.domain.vo.SelectPcmBrandGroupPageDto;
import com.wangfj.product.brand.persistence.PcmBrandGroupMapper;
import com.wangfj.product.brand.service.intf.IPcmBrandGroupService;
import com.wangfj.util.Constants;

/**
 * 集团品牌管理
 * 
 * @Class Name PcmBrandGroupServiceImpl
 * @Author wangx
 * @Create In 2015年7月30日
 */
@Service
public class PcmBrandGroupServiceImpl implements IPcmBrandGroupService {

	private static final Logger logger = LoggerFactory.getLogger(PcmBrandGroupServiceImpl.class);

	@Autowired
	private PcmBrandGroupMapper pcmBrandGroupMapper;

	/*
	 * 修改集团品牌(non-Javadoc)
	 * 
	 * @see
	 * com.wangfj.product.brand.service.intf.IPcmBrandGroupService#updateBrandGroup
	 * (com.wangfj.product.brand.domain.vo.PcmBrandGroupDto)
	 */
	@Transactional
	@Override
	public Integer updateBrandGroup(PcmBrandGroupDto pcmBrandGroupDto)
			throws IllegalAccessException, InvocationTargetException {

		logger.info("start updateBrandGroup(),para:" + pcmBrandGroupDto.toString());

		int updateResult = Constants.PUBLIC_0;
		// 根据Sid修改集团品牌，即集团品牌的Sid不能为空
		Long sid = pcmBrandGroupDto.getSid();
		if (sid != null) {
			PcmBrandGroup pcmBrandGroupResult = pcmBrandGroupMapper.selectByPrimaryKey(sid);
			if (pcmBrandGroupResult != null) {

				PcmBrandGroup pcmBrandGroup = new PcmBrandGroup();
				BeanUtils.copyProperties(pcmBrandGroup, pcmBrandGroupDto);

				// 不让修改集团品牌编码
				if (pcmBrandGroupDto.getBrandSid() != null
						&& !pcmBrandGroupDto.getBrandSid()
								.equals(pcmBrandGroupResult.getBrandSid())) {
					pcmBrandGroup.setBrandSid(pcmBrandGroupResult.getBrandSid());
					logger.error("不许修改集团品牌编码");
					throw new BleException(
							ComErrorCodeConstants.ErrorCode.BRANDGROUP_BRANDSID_MODIFY
									.getErrorCode(),
							ComErrorCodeConstants.ErrorCode.BRANDGROUP_BRANDSID_MODIFY.getMemo());
				}

				// 不让修改集团品牌名称
				if (pcmBrandGroupDto.getBrandName() != null
						&& !pcmBrandGroupDto.getBrandName().equals(
								pcmBrandGroupResult.getBrandName())) {
					pcmBrandGroup.setBrandName(pcmBrandGroupResult.getBrandName());
					logger.error("不许修改集团品牌名称");
					throw new BleException(
							ComErrorCodeConstants.ErrorCode.BRANDGROUP_BRANDNAEM_MODIFY
									.getErrorCode(),
							ComErrorCodeConstants.ErrorCode.BRANDGROUP_BRANDNAEM_MODIFY.getMemo());
				}

				pcmBrandGroup.setSid(pcmBrandGroupResult.getSid());
				updateResult = pcmBrandGroupMapper.updateByPrimaryKeySelective(pcmBrandGroup);
			} else {
				logger.error("修改的集团品牌不存在");
				throw new BleException(
						ComErrorCodeConstants.ErrorCode.BRANDGROUP_NOT_EXIST.getErrorCode(),
						ComErrorCodeConstants.ErrorCode.BRANDGROUP_NOT_EXIST.getMemo());
			}

		}
		logger.info("end updateBrandGroup()");

		return updateResult;
	}

	/*
	 * 查询集团品牌(non-Javadoc)
	 * 
	 * @see com.wangfj.product.brand.service.intf.IPcmBrandGroupService#
	 * findBrandGroupByPara(com.wangfj.product.brand.domain.vo.PcmBrandGroupDto)
	 */
	@Override
	public List<PcmBrandGroupDto> findBrandGroupByPara(PcmBrandGroupDto brandGroupDto)
			throws IllegalAccessException, InvocationTargetException {

		logger.info("findBrandGroupByPara(),para:" + brandGroupDto.toString());
		List<PcmBrandGroup> brandGroupList = pcmBrandGroupMapper
				.selectListByParaSelective(brandGroupDto);

		if (brandGroupList != null && brandGroupList.size() > 0) {
			List<PcmBrandGroupDto> brandGroupDtoList = new ArrayList<PcmBrandGroupDto>();
			for (int i = 0; i < brandGroupList.size(); i++) {

				PcmBrandGroupDto brandGroupDtoReturn = new PcmBrandGroupDto();
				BeanUtils.copyProperties(brandGroupDtoReturn, brandGroupList.get(i));
				brandGroupDtoList.add(brandGroupDtoReturn);

			}
			logger.info("end findBrandGroupByPara()");
			return brandGroupDtoList;
		} else {
			logger.error("查询集团品牌没有符合条件的数据");
			throw new BleException(ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getErrorCode(),
					ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getMemo());
		}

	}

	/*
	 * 分页查询集团品牌(non-Javadoc)
	 * 
	 * @see com.wangfj.product.brand.service.intf.IPcmBrandGroupService#
	 * findBrandGroupForPage
	 * (com.wangfj.product.brand.domain.vo.PcmBrandGroupDto)
	 */
	@Override
	public Page<PcmBrandGroupDto> findBrandGroupForPage(SelectPcmBrandGroupPageDto pageDto)
			throws IllegalAccessException, InvocationTargetException {

		logger.info("start findBrandGroupForPage(),para:" + pageDto.toString());

		Page<PcmBrandGroupDto> page = new Page<PcmBrandGroupDto>();
		page.setCurrentPage(pageDto.getCurrentPage());
		page.setPageSize(pageDto.getPageSize());

		// 查询总记录数
		Long count = pcmBrandGroupMapper.getCountByParaForPage(pageDto);
		page.setCount(count);

		// 分页查询
		pageDto.setStart(page.getStart());
		pageDto.setLimit(page.getLimit());
		List<PcmBrandGroup> pcmBrandGroupList = pcmBrandGroupMapper
				.selectListByParaForPage(pageDto);

		List<PcmBrandGroupDto> brandGroupDtoList = new ArrayList<PcmBrandGroupDto>();
		if (!pcmBrandGroupList.isEmpty()) {

			for (int i = 0; i < pcmBrandGroupList.size(); i++) {
				PcmBrandGroupDto pcmBrandGroupDto = new PcmBrandGroupDto();
				BeanUtils.copyProperties(pcmBrandGroupDto, pcmBrandGroupList.get(i));
				brandGroupDtoList.add(pcmBrandGroupDto);
			}

			page.setList(brandGroupDtoList);

			logger.info("end findBrandGroupForPage()");
			return page;
		} else {
			logger.error("查询集团品牌没有符合条件的数据");
			throw new BleException(ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getErrorCode(),
					ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getMemo());
		}

	}

	/**
	 * 集团品牌下发
	 * 
	 * @Methods Name findPageBrandGroup
	 * @Create In 2015-8-4 By wangx
	 * @param paraMap
	 * @return Page<PcmBrandGroup>
	 */
	@Override
	public Page<PcmBrandGroup> findPageBrandGroup(Map<String, Object> paraMap) {

		logger.info("start findPageBrandGroup(),para:" + paraMap.toString());

		Page<PcmBrandGroup> page = new Page<PcmBrandGroup>();

		if (paraMap.get("currentPage") != null) {
			page.setCurrentPage((Integer) paraMap.get("currentPage"));
		}
		if (paraMap.get("pageSize") != null) {
			page.setPageSize((Integer) paraMap.get("pageSize"));
		}

		// 分页记录总数查询
		Integer count = pcmBrandGroupMapper.getCountByParam(paraMap);
		page.setCount(count);

		// 分页查询
		paraMap.put("start", page.getStart());
		paraMap.put("limit", page.getLimit());
		List<PcmBrandGroup> brandGroupList = pcmBrandGroupMapper.selectPageListByParam(paraMap);
		if (!brandGroupList.isEmpty()) {
			page.setList(brandGroupList);
			logger.info("end findPageBrandGroup()");
			return page;
		} else {
			logger.error("查询集团品牌没有符合条件的数据");
			throw new BleException(ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getErrorCode(),
					ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getMemo());
		}

	}

	/**
	 * 删除集团品牌
	 * 
	 * @Methods Name deleteBrandGroup
	 * @Create In 2015-8-5 By wangx
	 * @param paraMap
	 * @return Integer
	 */
	@Override
	@Transactional
	public Integer deleteBrandGroup(Map<String, Object> paraMap) {

		logger.info("start deleteBrandGroup(),para:" + paraMap.toString());

		Integer flag = Constants.PUBLIC_0;
		Long sid = Long.parseLong(paraMap.get("sid") + "");
		if (sid != null) {

			PcmBrandGroup brandGroup = pcmBrandGroupMapper.selectByPrimaryKey(sid);
			if (brandGroup != null) {
				// 让集团品牌的有效字段失效
				brandGroup.setStatus(1);
				flag = pcmBrandGroupMapper.updateByPrimaryKeySelective(brandGroup);
			}

		}

		logger.info("end deleteBrandGroup()");
		return flag;

	}

	
	
}
