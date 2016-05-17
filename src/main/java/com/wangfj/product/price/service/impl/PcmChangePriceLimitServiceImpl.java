package com.wangfj.product.price.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangfj.core.framework.base.page.Page;
import com.wangfj.product.organization.persistence.PcmOrganizationMapper;
import com.wangfj.product.price.domain.entity.PcmChangePriceLimit;
import com.wangfj.product.price.domain.vo.PcmChangePriceLimitDto;
import com.wangfj.product.price.domain.vo.PublishPriceLimitDto;
import com.wangfj.product.price.domain.vo.SelectPcmPriceLimitDto;
import com.wangfj.product.price.persistence.PcmChangePriceLimitMapper;
import com.wangfj.product.price.service.intf.IPcmChangePriceLimitService;
import com.wangfj.util.Constants;

@Service
public class PcmChangePriceLimitServiceImpl implements IPcmChangePriceLimitService {

	@Autowired
	private PcmChangePriceLimitMapper pcmChangePriceLimitMapper;
	@Autowired
	private PcmOrganizationMapper pcmOrganizationMapper;

	/**
	 * 添加/修改门店阀值
	 * 
	 * @param record
	 * @return
	 */
	@Override
	public String saveOrUpdateChangePriceLimit(PublishPriceLimitDto dto) {
		// TODO Auto-generated method stub

		String message = "";

		PcmChangePriceLimit upperLimit = new PcmChangePriceLimit();
		upperLimit.setLevelType(0);
		upperLimit.setLevelValue(dto.getUpper());
		upperLimit.setStatus(dto.getUpperStatus());
		upperLimit.setShopSid(dto.getShopSid());

		PcmChangePriceLimit lowerLimit = new PcmChangePriceLimit();
		lowerLimit.setLevelType(1);
		lowerLimit.setLevelValue(dto.getLower());
		lowerLimit.setStatus(dto.getLowerStatus());
		lowerLimit.setShopSid(dto.getShopSid() + "");

		if (Constants.A.equals(dto.getActionCode().toUpperCase())) {

			upperLimit.setCreateTime(new Date());
			upperLimit.setCreateName(dto.getCreateName());
			lowerLimit.setCreateTime(new Date());
			lowerLimit.setCreateName(dto.getCreateName());

			boolean isSuccess = pcmChangePriceLimitMapper.insertSelective(upperLimit) > 0
					&& pcmChangePriceLimitMapper.insertSelective(lowerLimit) > 0;
			if (isSuccess) {
				message = "添加成功";
			} else {
				message = "添加失败";
			}
		} else if (Constants.U.equals(dto.getActionCode().toUpperCase())) {
			List<PcmChangePriceLimit> priceLimits = pcmChangePriceLimitMapper
					.selectPriceLimitByShopSid(dto.getShopSid());
			upperLimit.setUpdateTime(new Date());
			upperLimit.setUpdateName(dto.getUpdateName());
			lowerLimit.setUpdateTime(new Date());
			lowerLimit.setUpdateName(dto.getUpdateName());
			for (PcmChangePriceLimit ppl : priceLimits) {
				if (ppl.getLevelType() == 0) {
					upperLimit.setSid(ppl.getSid());
				}
				if (ppl.getLevelType() == 1) {
					lowerLimit.setSid(ppl.getSid());
				}
			}
			boolean isSuccess = pcmChangePriceLimitMapper
					.updateByPrimaryKeySelective(upperLimit) > 0
					&& pcmChangePriceLimitMapper.updateByPrimaryKeySelective(lowerLimit) > 0;
			if (isSuccess) {
				message = "修改成功";
			} else {
				message = "修改失败";
			}
		}

		return message;
	}

	/**
	 * 分页查询
	 * 
	 * @Methods Name selectPageByPara
	 * @Create In 2015-9-29 By zhangdongliang
	 * @param paramMap
	 * @return List<PcmChangePriceLimit>
	 */
	@Override
	public Page<PcmChangePriceLimitDto> selectPageList(Map<String, Object> map) {
		// TODO Auto-generated method stub

		SelectPcmPriceLimitDto dto = new SelectPcmPriceLimitDto();
		dto.setCurrentPage(Integer.parseInt(map.get("currentPage") + ""));
		dto.setPageSize(Integer.parseInt(map.get("pageSize") + ""));
		if (map.get("shopCode") != null) {
			Map<String, Object> orgMap = new HashMap<String, Object>();
			orgMap.put("organizationCode", map.get("shopCode"));
			orgMap.put("organizationType", map.get("3"));
			dto.setShopSid("" + pcmOrganizationMapper.selectListByParam(orgMap).get(0).getSid());
		}

		Page<PcmChangePriceLimitDto> page = new Page<PcmChangePriceLimitDto>();

		Integer currentPage = dto.getCurrentPage();
		Integer pageSize = dto.getPageSize();
		if (currentPage != null) {
			page.setCurrentPage(currentPage);
		}
		if (pageSize != null) {
			page.setPageSize(pageSize);
		}

		Integer count = pcmChangePriceLimitMapper.getPageCount(dto);
		page.setCount(count);

		List<PcmChangePriceLimitDto> limitList = pcmChangePriceLimitMapper.selectPageList(dto);

		page.setList(limitList);

		return page;
	}

	/**
	 * 得到所有已经添加过阀值的门店号
	 * 
	 * @return
	 */
	@Override
	public String selectAllShopSidFromPriceLimit() {
		// TODO Auto-generated method stub
		StringBuffer s = new StringBuffer();
		List<Long> shops = pcmChangePriceLimitMapper.selectAllShopSidFromPriceLimit();
		for (Long shop : shops) {
			s.append(shop + ",");
		}
		return s.substring(0, s.length() - 1);
	}

	/**
	 * 根据门店编号查询门店阀值
	 */
	@Override
	public PcmChangePriceLimitDto selectPriceLimitByShopCode(String shopCode) {
		// TODO Auto-generated method stub
		List<PcmChangePriceLimit> limits = pcmChangePriceLimitMapper
				.selectPriceLimitByShopCode(shopCode);

		PcmChangePriceLimitDto priceDto = new PcmChangePriceLimitDto();
		for (PcmChangePriceLimit li : limits) {
			if (li.getLevelType() == 0) {
				priceDto.setUpper(li.getLevelValue());
				priceDto.setUpperStatus(li.getStatus());
			}
			if (li.getLevelType() == 1) {
				priceDto.setLower(li.getLevelValue());
				priceDto.setLowerStatus(li.getStatus());
			}
		}
		return priceDto;
	}

	/**
	 * 获取上下线阀值
	 * 
	 * @Methods Name getPriceLimit
	 * @Create In 2015年11月18日 By kongqf
	 * @param limitDto
	 * @param flag
	 * @return BigDecimal
	 */
	@Override
	public BigDecimal getPriceLimit(PcmChangePriceLimitDto limitDto, boolean flag) {
		BigDecimal limitValue = new BigDecimal(0);
		if (limitDto != null) {
			if (flag) {
				if (limitDto.getUpperStatus() == 0 && limitDto.getUpper() != null) {
					limitValue = limitDto.getUpper();
				}

			} else {
				if (limitDto.getLowerStatus() == 0 && limitDto.getUpper() != null) {
					limitValue = limitDto.getLower();
				}
			}
		}
		return limitValue;
	}
}
