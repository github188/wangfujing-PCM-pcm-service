package com.wangfj.product.organization.service.impl;

import com.wangfj.core.constants.ComErrorCodeConstants;
import com.wangfj.core.framework.base.page.Page;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.core.utils.DateUtil;
import com.wangfj.core.utils.StringUtils;
import com.wangfj.product.organization.domain.entity.PcmRegion;
import com.wangfj.product.organization.domain.vo.PcmRegionQueryDto;
import com.wangfj.product.organization.domain.vo.PcmRegionResultDto;
import com.wangfj.product.organization.persistence.PcmRegionMapper;
import com.wangfj.product.organization.service.intf.IPcmRegionService;
import com.wangfj.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 行政区域Service
 *
 * @Class Name PcmRegionServiceImpl
 * @Author yedong
 * @Create In 2015年7月14日
 */
@Service
public class PcmRegionServiceImpl implements IPcmRegionService {

	@Autowired
	public PcmRegionMapper pcmRegionMapper;

	private static final Logger logger = LoggerFactory.getLogger(PcmRegionServiceImpl.class);

	/**
	 * 判重
	 * 
	 * @param para
	 * @return
	 */
	@Override
	public Boolean isExistence(Map<String, Object> para) {

		logger.info("start isExistence(),param:" + para.toString());

		Boolean flag = false;

		Map<String, Object> paramMap = new HashMap<String, Object>();

		String regionCode = para.get("regionCode") + "";
		if (StringUtils.isNotEmpty(regionCode)) {
			paramMap.clear();
			paramMap.put("regionCode", regionCode.trim());
			List<PcmRegion> list = pcmRegionMapper.selectListByParam(paramMap);
			if (list != null && list.size() > 0) {
				flag = true;
				throw new BleException(
						ComErrorCodeConstants.ErrorCode.REGION_CODE_EXIST.getErrorCode(),
						ComErrorCodeConstants.ErrorCode.REGION_CODE_EXIST.getMemo());
			}
		}

		String parentId = para.get("parentId") + "";
		String regionInnerCode = para.get("regionInnerCode") + "";
		if (StringUtils.isNotEmpty(parentId) && StringUtils.isNotEmpty(regionInnerCode)) {
			paramMap.clear();
			paramMap.put("regionInnerCode", regionInnerCode.trim());
			paramMap.put("parentId", Long.parseLong(parentId.trim()));
			List<PcmRegion> list = pcmRegionMapper.selectListByParam(paramMap);
			if (list != null && list.size() > 0) {
				flag = true;
				throw new BleException(
						ComErrorCodeConstants.ErrorCode.REGION_INNER_CODE_EXIST.getErrorCode(),
						ComErrorCodeConstants.ErrorCode.REGION_INNER_CODE_EXIST.getMemo());
			}
		}

		String regionName = para.get("regionName") + "";
		if (StringUtils.isNotEmpty(regionName)) {
			paramMap.clear();
			paramMap.put("regionName", regionName.trim());
			List<PcmRegion> list = pcmRegionMapper.selectListByParam(paramMap);
			if (list != null && list.size() > 0) {
				flag = true;
				throw new BleException(
						ComErrorCodeConstants.ErrorCode.REGION_NAME_EXIST.getErrorCode(),
						ComErrorCodeConstants.ErrorCode.REGION_NAME_EXIST.getMemo());
			}
		}

		logger.info("end isExistence(),return:" + flag);

		return flag;
	}

	/**
	 * 添加行政区域
	 *
	 * @param region
	 * @return
	 */
	@Transactional
	@Override
	public Integer addRegion(PcmRegion region) {

		logger.info("start addRegion(),param:" + region.toString());
		int result = Constants.PUBLIC_0;

		Map<String, Object> para = new HashMap<String, Object>();
		para.put("regionName", region.getRegionName());
		para.put("regionCode", region.getRegionCode());
		para.put("regionInnerCode", region.getRegionInnerCode());
		para.put("parentId", region.getParentId());
		if (!isExistence(para)) {
			result = pcmRegionMapper.insertSelective(region);
		}

		logger.info("end addRegion(),return:" + result);
		return result;

	}

	/**
	 * 修改行政区域
	 *
	 * @param region
	 * @return
	 */
	@Override
	@Transactional
	public Integer modifyRegion(PcmRegion region) {

		logger.info("start modifyRegion(),param:" + region.toString());

		int result = Constants.PUBLIC_0;

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sid", region.getSid());
		List<PcmRegion> list = pcmRegionMapper.selectListByParam(paramMap);
		if (list != null && list.size() == 1) {

			String regionName = region.getRegionName();
			if (StringUtils.isNotEmpty(regionName)) {

				if (!regionName.equals(list.get(0).getRegionName())) {
					paramMap.clear();
					paramMap.put("regionName", regionName.trim());
					if (!isExistence(paramMap)) {
						result = pcmRegionMapper.updateByPrimaryKeySelective(region);
					}
				} else {
					result = pcmRegionMapper.updateByPrimaryKeySelective(region);
				}
			}
		}

		logger.info("end modifyRegion(),return:" + result);

		return result;
	}

	@Override
	public Page<PcmRegionResultDto> findPageRegion(PcmRegionQueryDto dto) {

		logger.info("start findPageRegion(),param:" + dto.toString());
		Page<PcmRegionResultDto> page = new Page<PcmRegionResultDto>();

		Integer count = pcmRegionMapper.getPageCountByParam(dto);

		Integer currentPage = dto.getCurrentPage();
		if (StringUtils.isNotEmpty(currentPage + "")) {
			page.setCurrentPage(currentPage);
		}
		Integer pageSize = dto.getPageSize();
		if (StringUtils.isNotEmpty(pageSize + "")) {
			page.setPageSize(pageSize);
		}

		page.setCount(count);

		dto.setStart(page.getStart());
		dto.setLimit(page.getLimit());

		List<PcmRegionResultDto> list = pcmRegionMapper.findPageRegionByParam(dto);
		page.setList(list);

		logger.info("end findPageRegion(),return:" + page.toString());
		return page;
	}

	@Override
	public List<PcmRegionResultDto> findListRegion(PcmRegionQueryDto dto) {

		logger.info("start findListRegion(),param:" + dto.toString());

		dto.setStart(null);
		dto.setLimit(null);

		List<PcmRegionResultDto> list = pcmRegionMapper.findPageRegionByParam(dto);

		logger.info("end findListRegion(),return:" + list.toString());
		return list;
	}

	/**
	 * 下发查询
	 */
	@Override
	public List<Map<String, Object>> pushRegionData(Map<String, Object> para) {

		logger.info("start pushRegionData(),param:" + para.toString());

		String actionCode = para.get("actionCode") + "";
		String sid = para.get("sid") + "";
		String regionLevel = para.get("regionLevel") + "";
		String start = para.get("start") + "";
		String limit = para.get("limit") + "";

		Map<String, Object> paramMap = new HashMap<String, Object>();
		if (StringUtils.isNotEmpty(sid)) {
			paramMap.put("sid", Long.parseLong(sid.trim()));
		}
		if (StringUtils.isNotEmpty(regionLevel)) {
			paramMap.put("regionLevel", regionLevel.trim());
		}
		if (StringUtils.isNotEmpty(start)) {
			paramMap.put("start", Integer.parseInt(start.trim()));
		}
		if (StringUtils.isNotEmpty(limit)) {
			paramMap.put("limit", Integer.parseInt(limit.trim()));
		}

		List<Map<String, Object>> list = pcmRegionMapper.pushRegionData(paramMap);

		if (list != null && !list.isEmpty()) {
			for (Map<String, Object> map : list) {
				// 添加返回参数
				map.put("actionCode", actionCode);
				map.put("actionDate", DateUtil.formatToStr(new Date(), "yyyyMMdd.HHmmssZ"));

				// 字段转义
				String areaLib = map.get("areaLib") + "";
				if (StringUtils.isNotEmpty(areaLib)) {
					if (areaLib.trim().equals(Constants.PUBLIC_0 + "")) {
						map.put("areaLib", Constants.PCMREGION_TYPE_PROVINCE);
					}
					if (areaLib.trim().equals(Constants.PUBLIC_1 + "")) {
						map.put("areaLib", Constants.PCMREGION_TYPE_CITY);
					}
					if (areaLib.trim().equals(Constants.PUBLIC_3 + "")) {
						map.put("areaLib", Constants.PCMREGION_TYPE_DISTRICT);
					}
					if (areaLib.trim().equals(Constants.PUBLIC_4 + "")) {
						map.put("areaLib", Constants.PCMREGION_TYPE_DISTRICT);
					}
				}
			}
		}

		logger.info("end pushRegionData(),return:" + list.toString());

		return list;
	}

	/**
	 * 根据编码查询所有的下级
	 *
	 * @param dto
	 * @return List<PcmRegionResultDto>
	 * @Methods Name getRegionChildrenListByCode
	 * @Create In 2015-12-14 By wangxuan
	 */
	@Override
	public List<PcmRegionResultDto> getRegionChildrenListByCode(PcmRegionQueryDto dto) {

		logger.info("start getRegionChildrenListByCode(),param:" + dto.toString());

		dto.setStart(null);
		dto.setLimit(null);

		List<PcmRegionResultDto> list = pcmRegionMapper.getRegionChildrenListByCode(dto);

		logger.info("end getRegionChildrenListByCode(),return:" + list.toString());
		return list;
	}

}
