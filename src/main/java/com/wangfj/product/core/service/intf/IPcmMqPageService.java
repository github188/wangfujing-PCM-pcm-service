package com.wangfj.product.core.service.intf;

import java.util.Map;

import com.wangfj.core.framework.base.page.Page;
import com.wangfj.product.core.domain.dto.PcmMqPageDto;

public interface IPcmMqPageService {

	/**
	 * 分页查询MQ
	 * 
	 * @Methods Name findPageMQ
	 * @Create In 2015-9-10 By chenhu
	 * @param paramMap
	 * @return Page<PcmMqPageDto>
	 */
	Page<PcmMqPageDto> findPageMQ(Map<String, Object> paramMap);

}