package com.wangfj.product.limit.service.intf;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wangfj.core.framework.base.page.Page;
import com.wangfj.product.limit.domain.entity.PcmProductOnlineLimit;
import com.wangfj.product.limit.domain.vo.PcmProductOnlineLimitQueryDto;
import com.wangfj.product.limit.domain.vo.PcmProductOnlineLimitResultDto;

@Service
public interface IPcmProductOnlineLimitService {

	Integer addProductLimitList(List<PcmProductOnlineLimit> limitList);

	Integer modifyProductLimit(PcmProductOnlineLimit limit);

	Page<PcmProductOnlineLimitResultDto> findPageProductLimitInfo(PcmProductOnlineLimitQueryDto dto);

}
