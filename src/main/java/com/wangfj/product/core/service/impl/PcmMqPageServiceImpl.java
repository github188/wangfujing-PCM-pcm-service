package com.wangfj.product.core.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangfj.core.framework.base.page.Page;
import com.wangfj.core.utils.DateUtil;
import com.wangfj.core.utils.StringUtils;
import com.wangfj.product.core.domain.dto.PcmMqPageDto;
import com.wangfj.product.core.domain.entity.PcmMq;
import com.wangfj.product.core.persistence.PcmMqMapper;
import com.wangfj.product.core.service.intf.IPcmMqPageService;

/**
 * MQ
 * 
 * @Class Name IPcmMqPageServiceImpl
 * @Author chenhu
 * @Create In 2015-9-10
 */
@Service
public class PcmMqPageServiceImpl implements IPcmMqPageService {

	private static final Logger logger = LoggerFactory.getLogger(PcmMqPageServiceImpl.class);

	@Autowired
	private PcmMqMapper mqMapper;

	/**
	 * 分页查询MQ
	 * 
	 * @Methods Name findPageMQ
	 * @Create In 2015-9-10 By chenhu
	 * @param paramMap
	 * @return Page<PcmMqPageDto>
	 */
	@Override
	public Page<PcmMqPageDto> findPageMQ(Map<String, Object> paramMap) {

		logger.info("start findPageMQ(),para:" + paramMap.toString());

		Page<PcmMqPageDto> page = new Page<PcmMqPageDto>();

		String currentPage = paramMap.get("currentPage") + "";
		if (StringUtils.isNotEmpty(currentPage)) {
			page.setCurrentPage(Integer.parseInt(currentPage));
		}

		String pageSize = paramMap.get("pageSize") + "";
		if (StringUtils.isNotEmpty(pageSize)) {
			page.setPageSize(Integer.parseInt(pageSize));
		}

		Integer count = mqMapper.getPageCountByParam(paramMap);
		page.setCount(count);

		paramMap.put("start", page.getStart());
		paramMap.put("limit", page.getLimit());

		List<PcmMq> mqList = mqMapper.selectPageByParam(paramMap);

		List<PcmMqPageDto> mqPageDtoList = new ArrayList<PcmMqPageDto>();
		for (int i = 0; i < mqList.size(); i++) {

			PcmMqPageDto mqPageDto = new PcmMqPageDto();
			BeanUtils.copyProperties(mqList.get(i), mqPageDto);

			Date createdate = mqList.get(i).getCreatedate();
			mqPageDto.setCreatedates(createdate);
			mqPageDto.setCreatedateStr(DateUtil.formatToStr(createdate, "yyyy-MM-dd"));

			mqPageDtoList.add(mqPageDto);

		}

		page.setList(mqPageDtoList);
		logger.info("end findPageMQ()");

		return page;
	}

}
