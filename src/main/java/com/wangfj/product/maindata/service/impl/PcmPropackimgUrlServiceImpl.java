package com.wangfj.product.maindata.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangfj.product.maindata.domain.entity.pcmPropackimgUrl;
import com.wangfj.product.maindata.persistence.pcmPropackimgUrlMapper;
import com.wangfj.product.maindata.service.intf.IPcmPropackimgUrlService;

@Service
public class PcmPropackimgUrlServiceImpl implements IPcmPropackimgUrlService {

	@Autowired
	private pcmPropackimgUrlMapper proPackimgMapper;
	
	@Override
	public boolean savePropackimgUrl(pcmPropackimgUrl pack){
		boolean meg = false;
		if(pack != null){
			int a = proPackimgMapper.insertSelective(pack);
			if(a > 0){
				meg = true;
			}
		}
		return meg;
	}
	
	@Override
	public List<pcmPropackimgUrl> getAllListBySpuAndColor(pcmPropackimgUrl pack){
		List<pcmPropackimgUrl> urlList = null;
		urlList = proPackimgMapper.selectListBySpuAndColor(pack);
		return urlList;
	}

}
