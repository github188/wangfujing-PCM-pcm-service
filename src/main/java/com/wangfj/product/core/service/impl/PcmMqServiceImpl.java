package com.wangfj.product.core.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangfj.core.constants.ComErrorCodeConstants.ErrorCode;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.product.core.domain.entity.PcmMq;
import com.wangfj.product.core.persistence.PcmMqMapper;
import com.wangfj.product.core.service.intf.IPcmMqService;
import com.wangfj.util.Constants;

@Service
/**
 * mq记录表
 * @Class Name PcmMqServiceImpl
 * @Author liuhp
 * @Create In 2015-8-26
 */
public class PcmMqServiceImpl implements IPcmMqService {

	private static final Logger logger = LoggerFactory.getLogger(PcmMqServiceImpl.class);

	@Autowired
	PcmMqMapper mapper;

	/**
	 * 插入MQ记录表
	 * 
	 * @Methods Name insertSelective
	 * @Create In 2015-8-26 By liuhp
	 * @param record
	 */
	@Override
	public void insertSelective(PcmMq record) {
		int i = mapper.insertSelective(record);
		if (i == Constants.PUBLIC_0) {
			throw new BleException(ErrorCode.ADD_MQ_ERROR.getErrorCode(),
					ErrorCode.ADD_MQ_ERROR.getMemo());
		}
	}

	/**
	 * 修改MQ记录表
	 * 
	 * @Methods Name updateByPrimaryKeySelective
	 * @Create In 2015-8-26 By liuhp
	 * @param record
	 */
	@Override
	public void updateByPrimaryKeySelective(PcmMq record) {
		int i = mapper.updateByPrimaryKeySelective(record);
		if (i == Constants.PUBLIC_0) {
			throw new BleException(ErrorCode.UPDATE_MQ_ERROR.getErrorCode(),
					ErrorCode.UPDATE_MQ_ERROR.getMemo());
		}
	}

	/**
	 * 查询未发送成功的MQ
	 * 
	 * @Methods Name pcmMqInfoSendAgainJob
	 * @Create In 2016年2月24日 By yedong
	 * @return List<PcmMq>
	 */
	public List<PcmMq> pcmMqInfoSendAgainJob() {
		List<PcmMq> pcmMqInfoSendAgainJob = mapper.pcmMqInfoSendAgainJob();
		return pcmMqInfoSendAgainJob;
	}

}
