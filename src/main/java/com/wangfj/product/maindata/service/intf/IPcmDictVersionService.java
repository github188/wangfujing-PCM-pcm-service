package com.wangfj.product.maindata.service.intf;

import com.wangfj.product.maindata.domain.vo.PcmDictVersionDto;

public interface IPcmDictVersionService {
	public Integer selectVersionByType(PcmDictVersionDto record);
	public boolean saveOrUpdateVersion(PcmDictVersionDto record) throws Exception;
}
