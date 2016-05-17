package com.wangfj.product.organization.service.intf;

import java.util.List;
import java.util.Map;

import com.wangfj.product.organization.domain.entity.PcmChannelSaleConfig;
import com.wangfj.product.organization.domain.vo.PcmChannelSaleConfigQueryDto;
import com.wangfj.product.organization.domain.vo.PcmChannelSaleConfigResultDto;

public interface IPcmChannelSaleConfigService {

	List<PcmChannelSaleConfig> findListByParam(Map<String, Object> paramMap);

	List<PcmChannelSaleConfigResultDto> findListByShoppeParam(PcmChannelSaleConfigQueryDto dto);

	List<PcmChannelSaleConfigResultDto> findListByShoppeProParam(PcmChannelSaleConfigQueryDto dto);

}
