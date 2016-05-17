package com.wangfj.product.common.persistence;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.common.domain.entity.PcmRedis;
import com.wangfj.product.common.domain.vo.PcmRedisQueryDto;

import java.util.List;
import java.util.Map;

public interface PcmRedisMapper extends BaseMapper<PcmRedis> {
	int deleteByPrimaryKey(Long sid);

	Integer insert(PcmRedis record);

	int insertSelective(PcmRedis record);

	PcmRedis selectByPrimaryKey(Long sid);

	int updateByPrimaryKeySelective(PcmRedis record);

	int updateByPrimaryKeyWithBLOBs(PcmRedis record);

	int updateByPrimaryKey(PcmRedis record);

	/**
	 * 分页查询按时间正序排列
	 * 
	 * @param dto
	 * @return
	 */
	List<PcmRedis> findPageRedis(PcmRedisQueryDto dto);

	/**
	 * 分页查询总数
	 * 
	 * @param dto
	 * @return
	 */
	Integer findPageCountRedis(PcmRedisQueryDto dto);
}