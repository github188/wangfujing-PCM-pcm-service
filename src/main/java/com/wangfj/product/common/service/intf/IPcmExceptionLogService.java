package com.wangfj.product.common.service.intf;

import com.wangfj.core.framework.base.page.Page;
import com.wangfj.product.common.domain.entity.PcmJCOLog;
import com.wangfj.product.common.domain.vo.PcmExceptionLogDto;
import com.wangfj.product.common.domain.vo.PcmExceptionLogDtos;

/**
 * 异常信息管理接口
 * 
 * @Class Name IPcmExceptionLogService
 * @Author kongqf
 * @Create In 2015年8月5日
 */
public interface IPcmExceptionLogService {

	/**
	 * 保存异常信息并发送到减库平台
	 * 
	 * @Methods Name saveExceptionLogInfo
	 * @Create In 2015年8月5日 By kongqf
	 * @param pcmExceptionLogdto
	 * @return int
	 */
	public int saveExceptionLogInfo(PcmExceptionLogDto pcmExceptionLogdto);

	/**
	 * 保存异常信息
	 * 
	 * @Methods Name addExceptionLogInfo
	 * @Create In 2016年8月4日 By kongqf
	 * @param pcmExceptionLogdto
	 * @return int
	 */
	public int addExceptionLogInfo(PcmExceptionLogDto pcmExceptionLogdto);

	/**
	 * 分页查询异常信息
	 * 
	 * @Methods Name selectPage
	 * @Create In 2015-9-9 By chenhu
	 * @param pcmExceptionLogDtos
	 * @return List<PcmExceptionLog>
	 */
	Page<PcmExceptionLogDtos> selectPage(PcmExceptionLogDtos pcmExceptionLogDtos);

	/**
	 * 保存JCO发送记录
	 * 
	 * @Methods Name saveJcoLog
	 * @Create In 2016年3月31日 By kongqf
	 * @param jcoLog
	 *            void
	 */
	public void saveJcoLog(PcmJCOLog jcoLog);

}
