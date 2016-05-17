package com.wangfj.product.maindata.service.intf;

import java.util.List;

import com.wangfj.product.maindata.domain.entity.PcmSeasonDict;

public interface IPcmSeasonDictService {
	public List<PcmSeasonDict> selectListByParam();
}
