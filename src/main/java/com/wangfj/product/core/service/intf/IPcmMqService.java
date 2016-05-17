package com.wangfj.product.core.service.intf;

import java.util.List;

import com.wangfj.product.core.domain.entity.PcmMq;

public interface IPcmMqService {

	void insertSelective(PcmMq record);

	void updateByPrimaryKeySelective(PcmMq record);

	/**
	 * 查询未发送成功的MQ
	 * 
	 * @Methods Name pcmMqInfoSendAgainJob
	 * @Create In 2016年2月24日 By yedong
	 * @return List<PcmMq>
	 */
	public List<PcmMq> pcmMqInfoSendAgainJob();
}