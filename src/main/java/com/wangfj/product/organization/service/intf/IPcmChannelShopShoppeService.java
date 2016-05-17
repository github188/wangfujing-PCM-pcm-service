package com.wangfj.product.organization.service.intf;

import java.util.List;

import com.wangfj.product.organization.domain.vo.PcmInfoQueryDto;
import com.wangfj.product.organization.domain.vo.PcmInfoResultDto;

public interface IPcmChannelShopShoppeService {

	/**
	 * 渠道,门店，专柜查询， 包括渠道列表，每个渠道下对应的门店，每个门店下对应的专柜
	 * 
	 * @Methods Name getInfoByParam
	 * @Create In 2015-12-28 By wangxuan
	 * @param dto
	 * @return List<PcmInfoResultDto>
	 */
	List<PcmInfoResultDto> getInfoByParam(PcmInfoQueryDto dto);

}
