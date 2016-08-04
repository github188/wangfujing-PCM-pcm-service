package com.wangfj.product.common.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.wangfj.core.constants.ComErrorCodeConstants.ErrorCode;
import com.wangfj.core.framework.base.controller.BaseController;
import com.wangfj.core.framework.base.page.Page;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.core.utils.DateUtil;
import com.wangfj.core.utils.StringUtils;
import com.wangfj.product.common.domain.entity.PcmExceptionLog;
import com.wangfj.product.common.domain.entity.PcmJCOLog;
import com.wangfj.product.common.domain.vo.PcmExceptionLogDto;
import com.wangfj.product.common.domain.vo.PcmExceptionLogDtos;
import com.wangfj.product.common.persistence.PcmExceptionLogMapper;
import com.wangfj.product.common.persistence.PcmJCOLogMapper;
import com.wangfj.product.common.service.intf.IPcmExceptionLogService;

/**
 * 异常信息管理
 * 
 * @Class Name PcmExceptionLogService
 * @Author kongqf
 * @Create In 2015年8月5日
 */
@Service
public class PcmExceptionLogService implements IPcmExceptionLogService {
	private static final Logger logger = LoggerFactory.getLogger(PcmExceptionLogService.class);

	@Autowired
	private PcmExceptionLogMapper pcmExceptionLogMapper;
	@Autowired
	private PcmJCOLogMapper pcmJCOLogMapper;
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;

	/**
	 * 保存异常信息
	 * 
	 * @Methods Name saveExceptionLogInfo
	 * @Create In 2015年8月5日 By kongqf
	 * @param pcmExceptionLogdto
	 * @return int
	 */
	@Override
	public int saveExceptionLogInfo(PcmExceptionLogDto pcmExceptionLogdto) {
		try {
			String errorCode = null;
			if (StringUtils.isNotEmpty(pcmExceptionLogdto.getErrorCode())) {
				errorCode = pcmExceptionLogdto.getErrorCode();
			} else {
				if (StringUtils.isNotEmpty(pcmExceptionLogdto.getExceptionType())) {
					errorCode = pcmExceptionLogdto.getExceptionType();
				} else {
					errorCode = ErrorCode.APPLICATION_OPER_ERROR.getErrorCode();
				}
			}
			BaseController.sendException(
					new BleException(errorCode, errorCode + pcmExceptionLogdto.getErrorMessage()));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		int result = 0;
		try {
			result = pcmExceptionLogMapper.insertSelective(pcmExceptionLogdto);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}

	/**
	 * 接口类型枚举
	 * 
	 * @Class Name InterfaceType
	 * @Author kongqf
	 * @Create In 2015年8月5日
	 */
	public enum InterfaceType {
		ChanagePriceInterface;

	}

	@Override
	public Page<PcmExceptionLogDtos> selectPage(PcmExceptionLogDtos pcmExceptionLogDtos) {

		Page<PcmExceptionLogDtos> page = new Page<PcmExceptionLogDtos>();
		page.setCurrentPage(pcmExceptionLogDtos.getCurrentPage());
		page.setPageSize(pcmExceptionLogDtos.getPageSize());

		Long count = pcmExceptionLogMapper.getCountByParam(pcmExceptionLogDtos);
		page.setCount(count);

		pcmExceptionLogDtos.setLimit(page.getLimit());
		pcmExceptionLogDtos.setStart(page.getStart());
		List<PcmExceptionLog> dto = new ArrayList<PcmExceptionLog>();
		dto = pcmExceptionLogMapper.selectPage(pcmExceptionLogDtos);
		List<PcmExceptionLogDtos> list = new ArrayList<PcmExceptionLogDtos>();
		for (int i = 0; i < dto.size(); i++) {

			PcmExceptionLog pcmExceptionLog = dto.get(i);
			PcmExceptionLogDtos exceptionLogDtos = new PcmExceptionLogDtos();
			BeanUtils.copyProperties(pcmExceptionLog, exceptionLogDtos);
			exceptionLogDtos.setCreateTimes(pcmExceptionLog.getCreateTime());
			exceptionLogDtos.setUpdateTimes(pcmExceptionLog.getUpdateTime());
			exceptionLogDtos.setCreateTimeStr(
					DateUtil.formatToStr(exceptionLogDtos.getCreateTimes(), "yyyy-MM-dd"));
			exceptionLogDtos.setUpdateTimeStr(
					DateUtil.formatToStr(exceptionLogDtos.getUpdateTimes(), "yyyy-MM-dd"));
			list.add(exceptionLogDtos);
		}
		page.setList(list);
		return page;
	}

	/**
	 * 保存JCO发送记录
	 * 
	 * @Methods Name saveJcoLog
	 * @Create In 2016年3月31日 By kongqf
	 * @param jcoLog
	 *            void
	 */
	@Override
	public void saveJcoLog(PcmJCOLog jcoLog) {
		final PcmJCOLog logRecord = jcoLog;
		taskExecutor.execute(new Runnable() {
			@Override
			public void run() {
				try {
					pcmJCOLogMapper.insertSelective(logRecord);
				} catch (Exception e) {
					logger.error("saveJcoLog:" + e.getMessage());
				}
			}
		});
	}

	@Override
	public int addExceptionLogInfo(PcmExceptionLogDto pcmExceptionLogdto) {
		int result = 0;
		try {
			result = pcmExceptionLogMapper.insertSelective(pcmExceptionLogdto);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}
}
