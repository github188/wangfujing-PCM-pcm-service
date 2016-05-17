package com.wangfj.product.organization.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangfj.product.organization.domain.entity.PcmChannel;
import com.wangfj.product.organization.domain.vo.PcmInfoQueryDto;
import com.wangfj.product.organization.domain.vo.PcmInfoResultDto;
import com.wangfj.product.organization.persistence.PcmChannelMapper;
import com.wangfj.product.organization.persistence.PcmOrganizationMapper;
import com.wangfj.product.organization.persistence.PcmShoppeMapper;
import com.wangfj.product.organization.service.intf.IPcmChannelShopShoppeService;
import com.wangfj.util.Constants;

@Service
public class PcmChannelShopShoppeServiceImpl implements IPcmChannelShopShoppeService {

	private static final Logger logger = LoggerFactory
			.getLogger(PcmChannelShopShoppeServiceImpl.class);

	@Autowired
	private PcmChannelMapper channelMapper;

	@Autowired
	private PcmOrganizationMapper organizationMapper;

	@Autowired
	private PcmShoppeMapper shoppeMapper;

	/**
	 * 渠道,门店，专柜查询， 包括渠道列表，每个渠道下对应的门店，每个门店下对应的专柜
	 */
	@Override
	public List<PcmInfoResultDto> getInfoByParam(PcmInfoQueryDto dto) {

		logger.info("start getInfoByParam(),param:" + dto.toString());

		List<PcmInfoResultDto> resultList = new ArrayList<PcmInfoResultDto>();

		String queryFlag = dto.getQueryFlag();
		if ((Constants.PUBLIC_0 + "").equalsIgnoreCase(queryFlag)) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("status", Constants.PUBLIC_0);
			List<PcmChannel> list = channelMapper.selectListByParam(paramMap);
			if (list != null && list.size() > 0) {
				for (PcmChannel channel : list) {
					PcmInfoResultDto tempDto = new PcmInfoResultDto();
					BeanUtils.copyProperties(channel, tempDto);
					resultList.add(tempDto);
				}
			}
		}

		if ((Constants.PUBLIC_1 + "").equalsIgnoreCase(queryFlag)) {
			resultList = organizationMapper.findShopByChannelParamToCMS(dto);
		}

		if ((Constants.PUBLIC_2 + "").equalsIgnoreCase(queryFlag)) {
			resultList = shoppeMapper.findShoppeByShopParamToCMS(dto);
		}

		logger.info("end getInfoByParam(),return:" + resultList.toString());

		return resultList;
	}
}
