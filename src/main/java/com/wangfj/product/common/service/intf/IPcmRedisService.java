package com.wangfj.product.common.service.intf;

import com.wangfj.core.framework.base.page.Page;
import com.wangfj.product.common.domain.entity.PcmRedis;
import com.wangfj.product.common.domain.vo.PcmRedisQueryDto;
import com.wangfj.product.common.domain.vo.PcmRedisUDto;

public interface IPcmRedisService {

	/**
	 * 保存redis异常记录
	 * 
	 * @Methods Name savePcmRedis
	 * @Create In 2016年3月3日 By kongqf
	 * @param entity
	 * @return int
	 */
	public void savePcmRedis(PcmRedis entity);

	/**
	 * 保存redis异常记录
	 * 
	 * @Methods Name insertPcmRedis
	 * @Create In 2016年3月23日 By kongqf
	 * @param entity
	 * @return int
	 */
	public int insertPcmRedis(PcmRedis entity);

	/**
	 * 修改处理状态
	 * 
	 * @param dto
	 * @return
	 */
	int updatePcmRedisStatus(PcmRedisUDto dto);

	/**
	 * 分页查询
	 *
	 * @param dto
	 * @return
	 */
	Page<PcmRedis> findPageRedis(PcmRedisQueryDto dto);

	/**
	 * 分页查询未处理
	 *
	 * @param dto
	 * @return
	 */
	Page<PcmRedis> findPageRedisUndone(PcmRedisQueryDto dto);
}
