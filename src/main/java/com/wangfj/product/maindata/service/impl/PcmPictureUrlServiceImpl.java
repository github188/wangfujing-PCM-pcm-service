package com.wangfj.product.maindata.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangfj.product.maindata.domain.entity.PcmPictureUrl;
import com.wangfj.product.maindata.persistence.PcmPictureUrlMapper;
import com.wangfj.product.maindata.service.intf.IPcmPictureUrlService;

@Service
public class PcmPictureUrlServiceImpl implements IPcmPictureUrlService {
	@Autowired
	private PcmPictureUrlMapper urlMapper;

	@Override
	public String getPrimaryUrlBySpuColor(PcmPictureUrl url) {
		List<PcmPictureUrl> selectListByParam = urlMapper.selectListByParam(url);
		if (selectListByParam != null && selectListByParam.size() > 0) {
			return selectListByParam.get(0).getPictureUrl();
		} else {
			return null;
		}
	}

}
