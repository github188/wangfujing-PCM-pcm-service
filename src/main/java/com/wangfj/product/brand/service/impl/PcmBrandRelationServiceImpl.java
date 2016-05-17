//package com.wangfj.product.brand.service.impl;
//
//import java.lang.reflect.InvocationTargetException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.commons.beanutils.BeanUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.wangfj.core.constants.ComErrorCodeConstants;
//import com.wangfj.core.framework.exception.BleException;
//import com.wangfj.product.brand.domain.entity.PcmBrand;
//import com.wangfj.product.brand.domain.entity.PcmBrandGroup;
//import com.wangfj.product.brand.domain.entity.PcmBrandRelation;
//import com.wangfj.product.brand.domain.vo.PcmBrandRelationDto;
//import com.wangfj.product.brand.persistence.PcmBrandGroupMapper;
//import com.wangfj.product.brand.persistence.PcmBrandMapper;
//import com.wangfj.product.brand.persistence.PcmBrandRelationMapper;
//import com.wangfj.product.brand.service.intf.IPcmBrandRelationService;
//import com.wangfj.util.Constants;
//
///**
// * 门店品牌与集团品牌的维护
// * 
// * @Class Name PcmBrandRelationServiceImpl
// * @Author wangx
// * @Create In 2015年7月30日
// */
//@Service
//public class PcmBrandRelationServiceImpl implements IPcmBrandRelationService {
//
//	private static final Logger logger = LoggerFactory.getLogger(PcmBrandRelationServiceImpl.class);
//
//	@Autowired
//	private PcmBrandRelationMapper pcmBrandRelationMapper;
//	@Autowired
//	private PcmBrandMapper pcmBrandMapper;
//	@Autowired
//	private PcmBrandGroupMapper pcmBrandGroupMapper;
//
//	/**
//	 * 添加门店品牌与集团品牌的关系
//	 * 
//	 * @Methods Name addRelation
//	 * @Create In 2015年7月30日 By wangx
//	 * @param pcmBrandRelationDto
//	 * @return Integer
//	 * @throws InvocationTargetException
//	 * @throws IllegalAccessException
//	 */
//	@Transactional
//	@Override
//	public Integer addRelation(PcmBrandRelationDto pcmBrandRelationDto)
//			throws IllegalAccessException, InvocationTargetException {
//
//		logger.info("start addRelation(),para:" + pcmBrandRelationDto.toString());
//
//		// 标记位，标记操作是否成功
//		Integer flag = Constants.PUBLIC_0;
//
//		// 查询门店品牌
//		Map<String, Object> paraBrand = new HashMap<String, Object>();
//		paraBrand.put("sid", pcmBrandRelationDto.getBrandSid());
//		paraBrand.put("status", Constants.PUBLIC_0);
//		List<PcmBrand> pcmBrandList = pcmBrandMapper.selectListByParam(paraBrand);
//		if (pcmBrandList != null && !pcmBrandList.isEmpty()) {
//			// 查询集团品牌
//			Map<String, Object> paraBrandGroup = new HashMap<String, Object>();
//			paraBrandGroup.put("sid", pcmBrandRelationDto.getBrandRootSid());
//			paraBrandGroup.put("status", Constants.PUBLIC_0);
//			List<PcmBrandGroup> brandGroupList = pcmBrandGroupMapper
//					.selectListByParam(paraBrandGroup);
//			if (brandGroupList != null && !brandGroupList.isEmpty()) {
//
//				// 查询集团品牌与门店品牌关联关系表（pcm_brand_relation）,判断数据是否存在
//				Map<String, Object> para = new HashMap<String, Object>();
//				para.put("brandSid", pcmBrandRelationDto.getBrandSid());
//				para.put("status", Constants.PUBLIC_0);
//				List<PcmBrandRelation> pcmBrandRelationList = pcmBrandRelationMapper
//						.selectListByParam(para);
//
//				// 集团品牌与门店品牌关联关系表操作结果
//				int relationResult = Constants.PUBLIC_0;
//				PcmBrandRelation pcmBrandRelation = new PcmBrandRelation();
//				BeanUtils.copyProperties(pcmBrandRelation, pcmBrandRelationDto);
//
//				if (pcmBrandRelationList.isEmpty()) {
//					// 集团品牌与门店品牌关联关系表插入数据（pcm_brand_relation）
//					relationResult = pcmBrandRelationMapper.insertSelective(pcmBrandRelation);
//				}
//
//				// 修改门店品牌表的外键修改结果
//				int brandUpdateResult = Constants.PUBLIC_0;
//				if (relationResult > 0) {
//					// 修改门店品牌表的集团品牌关系
//					Map<String, Object> paraMap = new HashMap<String, Object>();
//					paraMap.put("sid", pcmBrandRelationDto.getBrandSid());
//					paraMap.put("groupBrandSid", pcmBrandRelationDto.getBrandRootSid());
//					brandUpdateResult = pcmBrandMapper.updateBrandGroupByBrandSid(paraMap);
//				}
//
//				if (relationResult > 0 && brandUpdateResult > 0) {
//					flag = Constants.PUBLIC_1;
//				}
//
//			} else {
//				logger.error("维护门店品牌与集团品牌的关系时需要维护的集团品牌不存在");
//				throw new BleException(
//						ComErrorCodeConstants.ErrorCode.BRANDGROUP_NOT_EXIST.getErrorCode(),
//						ComErrorCodeConstants.ErrorCode.BRANDGROUP_NOT_EXIST.getMemo());
//			}
//
//		} else {
//			logger.error("维护门店品牌与集团品牌的关系时需要维护的门店品牌不存在");
//			throw new BleException(ComErrorCodeConstants.ErrorCode.BRAND_NOT_EXIST.getErrorCode(),
//					ComErrorCodeConstants.ErrorCode.BRAND_NOT_EXIST.getMemo());
//		}
//		logger.info("end addRelation()");
//		return flag;
//	}
//
//	/**
//	 * 修改门店品牌与集团品牌的关系
//	 * 
//	 * @Methods Name updateRelation
//	 * @Create In 2015-8-12 By wangx
//	 * @param pcmBrandRelationDto
//	 * @return Integer
//	 * @throws InvocationTargetException
//	 * @throws IllegalAccessException
//	 */
//	@Override
//	@Transactional
//	public Integer updateRelation(PcmBrandRelationDto pcmBrandRelationDto)
//			throws IllegalAccessException, InvocationTargetException {
//
//		logger.info("start addRelation(),para:" + pcmBrandRelationDto.toString());
//
//		// 标记位，标记操作是否成功
//		Integer flag = Constants.PUBLIC_0;
//
//		// 查询门店品牌
//		Map<String, Object> paraBrand = new HashMap<String, Object>();
//		paraBrand.put("sid", pcmBrandRelationDto.getBrandSid());
//		paraBrand.put("status", Constants.PUBLIC_0);
//		List<PcmBrand> pcmBrandList = pcmBrandMapper.selectListByParam(paraBrand);
//		if (pcmBrandList != null && !pcmBrandList.isEmpty()) {
//			// 查询集团品牌
//			Map<String, Object> paraBrandGroup = new HashMap<String, Object>();
//			paraBrandGroup.put("sid", pcmBrandRelationDto.getBrandRootSid());
//			paraBrandGroup.put("status", Constants.PUBLIC_0);
//			List<PcmBrandGroup> brandGroupList = pcmBrandGroupMapper
//					.selectListByParam(paraBrandGroup);
//			if (brandGroupList != null && !brandGroupList.isEmpty()) {
//
//				// 查询集团品牌与门店品牌关联关系表（pcm_brand_relation）,判断数据是否存在
//				Map<String, Object> para = new HashMap<String, Object>();
//				para.put("brandSid", pcmBrandRelationDto.getBrandSid());
//				para.put("status", Constants.PUBLIC_0);
//				List<PcmBrandRelation> pcmBrandRelationList = pcmBrandRelationMapper
//						.selectListByParam(para);
//
//				// 集团品牌与门店品牌关联关系表操作结果
//				int relationResult = Constants.PUBLIC_0;
//				PcmBrandRelation pcmBrandRelation = new PcmBrandRelation();
//				BeanUtils.copyProperties(pcmBrandRelation, pcmBrandRelationDto);
//
//				if (pcmBrandRelationList != null && !pcmBrandRelationList.isEmpty()) {
//					// 修改集团品牌与门店品牌关联关系表（pcm_brand_relation）
//					pcmBrandRelation.setSid(pcmBrandRelationList.get(0).getSid());
//					relationResult = pcmBrandRelationMapper
//							.updateByPrimaryKeySelective(pcmBrandRelation);
//				}
//
//				// 修改门店品牌表的外键修改结果
//				int brandUpdateResult = Constants.PUBLIC_0;
//				if (relationResult > 0) {
//					// 修改门店品牌表的集团品牌关系
//					Map<String, Object> paraMap = new HashMap<String, Object>();
//					paraMap.put("sid", pcmBrandRelationDto.getBrandSid());
//					paraMap.put("groupBrandSid", pcmBrandRelationDto.getBrandRootSid());
//					brandUpdateResult = pcmBrandMapper.updateBrandGroupByBrandSid(paraMap);
//				}
//
//				if (relationResult > 0 && brandUpdateResult > 0) {
//					flag = Constants.PUBLIC_1;
//				}
//
//			} else {
//				logger.error("维护门店品牌与集团品牌的关系时需要维护的集团品牌不存在");
//				throw new BleException(
//						ComErrorCodeConstants.ErrorCode.BRANDGROUP_NOT_EXIST.getErrorCode(),
//						ComErrorCodeConstants.ErrorCode.BRANDGROUP_NOT_EXIST.getMemo());
//			}
//
//		} else {
//			logger.error("维护门店品牌与集团品牌的关系时需要维护的门店品牌不存在");
//			throw new BleException(ComErrorCodeConstants.ErrorCode.BRAND_NOT_EXIST.getErrorCode(),
//					ComErrorCodeConstants.ErrorCode.BRAND_NOT_EXIST.getMemo());
//		}
//		logger.info("end addRelation()");
//		return flag;
//	}
//
//	/**
//	 * 集团品牌门店品牌关联关系的删除
//	 * 
//	 * @Methods Name deleteRelation
//	 * @Create In 2015-8-8 By wangx
//	 * @param pcmBrandRelation
//	 * @return Integer
//	 */
//	@Override
//	@Transactional
//	public Integer deleteRelation(PcmBrandRelationDto pcmBrandRelationDto) {
//
//		logger.info("start deleteRelation(),para:" + pcmBrandRelationDto.toString());
//
//		// 标记位，标记操作是否成功
//		Integer flag = Constants.PUBLIC_0;
//
//		// 查询门店品牌
//		Map<String, Object> paraBrand = new HashMap<String, Object>();
//		paraBrand.put("sid", pcmBrandRelationDto.getBrandSid());
//		paraBrand.put("status", Constants.PUBLIC_0);
//		List<PcmBrand> pcmBrandList = pcmBrandMapper.selectListByParam(paraBrand);
//		if (pcmBrandList != null && !pcmBrandList.isEmpty()) {
//			// 查询集团品牌
//			Map<String, Object> paraBrandGroup = new HashMap<String, Object>();
//			paraBrandGroup.put("sid", pcmBrandRelationDto.getBrandRootSid());
//			paraBrandGroup.put("status", Constants.PUBLIC_0);
//			List<PcmBrandGroup> brandGroupList = pcmBrandGroupMapper
//					.selectListByParam(paraBrandGroup);
//			if (brandGroupList != null && !brandGroupList.isEmpty()) {
//
//				// 查询集团品牌与门店品牌关联关系表（pcm_brand_relation）,判断数据是否存在
//				Map<String, Object> para = new HashMap<String, Object>();
//				para.put("brandSid", pcmBrandRelationDto.getBrandSid());
//				para.put("brandRootSid", pcmBrandRelationDto.getBrandRootSid());
//				para.put("status", Constants.PUBLIC_0);
//				List<PcmBrandRelation> pcmBrandRelationList = pcmBrandRelationMapper
//						.selectListByParam(para);
//
//				// 集团品牌与门店品牌关联关系删除结果
//				int relationResult = Constants.PUBLIC_0;
//
//				if (!pcmBrandRelationList.isEmpty()) {
//					PcmBrandRelation pcmBrandRelation = new PcmBrandRelation();
//					pcmBrandRelation.setStatus(Constants.PUBLIC_1);
//					pcmBrandRelation.setSid(pcmBrandRelationList.get(0).getSid());
//					// 集团品牌与门店品牌关联关系表将状态设置为无效（pcm_brand_relation）
//					relationResult = pcmBrandRelationMapper
//							.updateByPrimaryKeySelective(pcmBrandRelation);
//				} else {
//					logger.error("删除门店品牌与集团品牌的关系时需要删除的关系不存在");
//					throw new BleException(
//							ComErrorCodeConstants.ErrorCode.BRANDRELATION_NOT_EXIST.getErrorCode(),
//							ComErrorCodeConstants.ErrorCode.BRANDRELATION_NOT_EXIST.getMemo());
//				}
//
//				// // 修改门店品牌表的外键修改结果
//				// int brandUpdateResult = Constants.PUBLIC_0;
//				// if (relationResult > 0) {
//				// // 修改门店品牌表的集团品牌关系
//				// Map<String, Object> paraMap = new HashMap<String, Object>();
//				// paraMap.put("sid", pcmBrandRelationDto.getBrandSid());
//				// paraMap.put("groupBrandSid", null);
//				// brandUpdateResult =
//				// pcmBrandMapper.updateBrandGroupByBrandSid(paraMap);
//				// }
//
//				if (relationResult > 0) {
//					flag = Constants.PUBLIC_1;
//				}
//
//				logger.info("end deleteRelation()");
//				return flag;
//			} else {
//				logger.error("删除门店品牌与集团品牌的关系时需要删除的集团品牌不存在");
//				throw new BleException(
//						ComErrorCodeConstants.ErrorCode.BRANDGROUP_NOT_EXIST.getErrorCode(),
//						ComErrorCodeConstants.ErrorCode.BRANDGROUP_NOT_EXIST.getMemo());
//			}
//
//		} else {
//			logger.error("删除门店品牌与集团品牌的关系时需要删除的门店品牌不存在");
//			throw new BleException(ComErrorCodeConstants.ErrorCode.BRAND_NOT_EXIST.getErrorCode(),
//					ComErrorCodeConstants.ErrorCode.BRAND_NOT_EXIST.getMemo());
//		}
//
//	}
//
//	/**
//	 * 条件查询门店品牌与集团品牌关联关系表
//	 * 
//	 * @Methods Name findListByPara
//	 * @Create In 2015-8-10 By wangx
//	 * @param brandRelationDto
//	 * @return List<PcmBrandRelationDto>
//	 * @throws InvocationTargetException
//	 * @throws IllegalAccessException
//	 */
//	@Override
//	public List<PcmBrandRelationDto> findListByPara(Map<String, Object> para)
//			throws IllegalAccessException, InvocationTargetException {
//
//		logger.info("start findListByPara(),para:" + para.toString());
//
//		if (para.get("status") == null) {
//			para.put("status", Constants.PUBLIC_0);
//		}
//
//		List<PcmBrandRelation> brandRelationList = pcmBrandRelationMapper.selectListByParam(para);
//
//		List<PcmBrandRelationDto> brandRelationDtoList = new ArrayList<PcmBrandRelationDto>();
//		if (!brandRelationList.isEmpty()) {
//			for (int i = 0; i < brandRelationList.size(); i++) {
//				PcmBrandRelationDto brandRelationDto = new PcmBrandRelationDto();
//				BeanUtils.copyProperties(brandRelationDto, brandRelationList.get(i));
//				brandRelationDto.setOptDates(brandRelationList.get(i).getOptDate());
//				brandRelationDtoList.add(brandRelationDto);
//			}
//
//		}
//
//		logger.info("end findListByPara()");
//
//		return brandRelationDtoList;
//	}
//
//}
