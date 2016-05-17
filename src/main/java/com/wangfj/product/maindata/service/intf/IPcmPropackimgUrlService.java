package com.wangfj.product.maindata.service.intf;

import java.util.List;

import com.wangfj.product.maindata.domain.entity.pcmPropackimgUrl;

public interface IPcmPropackimgUrlService {

	boolean savePropackimgUrl(pcmPropackimgUrl pack);

	List<pcmPropackimgUrl> getAllListBySpuAndColor(pcmPropackimgUrl pack);

}
