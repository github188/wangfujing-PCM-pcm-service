package com.wangfj.product.limit.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangfj.core.constants.ComErrorCodeConstants.ErrorCode;
import com.wangfj.core.framework.base.page.Page;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.product.limit.domain.entity.PcmProductOnlineLimit;
import com.wangfj.product.limit.domain.vo.PcmProductOnlineLimitQueryDto;
import com.wangfj.product.limit.domain.vo.PcmProductOnlineLimitResultDto;
import com.wangfj.product.limit.persistence.PcmProductOnlineLimitMapper;
import com.wangfj.product.limit.service.intf.IPcmProductOnlineLimitService;
import com.wangfj.util.Constants;

@Service
public class PcmProductOnlineLimitServiceImpl implements IPcmProductOnlineLimitService {

	private Logger logger = LoggerFactory.getLogger(PcmProductOnlineLimitServiceImpl.class);

	@Autowired
	private PcmProductOnlineLimitMapper productOnlineLimitMapper;

	@Override
	@Transactional
	public Integer addProductLimitList(List<PcmProductOnlineLimit> limitList) {

		logger.info("start addProductLimitList(),param:" + limitList.toString());
		Integer result = Constants.PUBLIC_0;

		if (limitList != null && limitList.size() > 0) {

			for (PcmProductOnlineLimit temp : limitList) {

				Map<String, Object> paramMap = new HashMap<String, Object>();
				Long brandSid = temp.getBrandSid();
				if (brandSid != null) {
					paramMap.put("brandSid", brandSid);
					paramMap.put("categorySid", temp.getCategorySid());
					paramMap.put("status", Constants.PUBLIC_0);
					List<PcmProductOnlineLimit> existList = productOnlineLimitMapper
							.selectExistenceByParam(paramMap);

					if (existList != null && existList.size() > 0) {
						throw new BleException(
								ErrorCode.PRODUCTONLINELIMIT_EXISTENCE.getErrorCode(),
								ErrorCode.PRODUCTONLINELIMIT_EXISTENCE.getMemo());
					}

					temp.setStatus(Constants.PUBLIC_0);
					result = productOnlineLimitMapper.insertSelective(temp);

				}
			}

		}

		logger.info("end addProductLimitList(),return:" + result);
		return result;
	}

	@Override
	@Transactional
	public Integer modifyProductLimit(PcmProductOnlineLimit limit) {

		logger.info("start modifyProductLimitList(),param:" + limit.toString());
		Integer result = Constants.PUBLIC_0;

		Map<String, Object> paramMap = new HashMap<String, Object>();
		Long sid = limit.getSid();
		if (sid != null) {
			paramMap.put("sid", sid);
			// paramMap.put("status", Constants.PUBLIC_0);
			List<PcmProductOnlineLimit> list = productOnlineLimitMapper.selectListByParam(paramMap);

			if (list != null && list.size() == 1) {
				// 不能修改的值
				limit.setBrandSid(list.get(0).getBrandSid());
				limit.setCategorySid(list.get(0).getCategorySid());
				result = productOnlineLimitMapper.updateByPrimaryKeySelective(limit);
			}

		}

		logger.info("end modifyProductLimitList(),return:" + result);
		return result;
	}

	@Override
	public Page<PcmProductOnlineLimitResultDto> findPageProductLimitInfo(
			PcmProductOnlineLimitQueryDto dto) {

		logger.info("start findPageProductLimitInfo(),param:" + dto.toString());

		Page<PcmProductOnlineLimitResultDto> page = new Page<PcmProductOnlineLimitResultDto>();

		page.setCurrentPage(dto.getCurrentPage());
		page.setPageSize(dto.getPageSize());
		Integer count = productOnlineLimitMapper.selectAllProductLimitInfoCount(dto);
		page.setCount(count);

		dto.setStart(page.getStart());
		dto.setLimit(page.getLimit());

		List<PcmProductOnlineLimitResultDto> list = productOnlineLimitMapper
				.selectAllProductLimitInfoList(dto);
		page.setList(list);

		logger.info("end findPageProductLimitInfo(),return:" + page.toString());
		return page;
	}
}
