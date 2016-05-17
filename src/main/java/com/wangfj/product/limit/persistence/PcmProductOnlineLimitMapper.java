package com.wangfj.product.limit.persistence;

import java.util.List;
import java.util.Map;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.limit.domain.entity.PcmProductOnlineLimit;
import com.wangfj.product.limit.domain.vo.PcmProductOnlineLimitQueryDto;
import com.wangfj.product.limit.domain.vo.PcmProductOnlineLimitResultDto;

public interface PcmProductOnlineLimitMapper extends BaseMapper<PcmProductOnlineLimit> {
	int deleteByPrimaryKey(Long sid);

	Integer insert(PcmProductOnlineLimit record);

	int insertSelective(PcmProductOnlineLimit record);

	PcmProductOnlineLimit selectByPrimaryKey(Long sid);

	int updateByPrimaryKeySelective(PcmProductOnlineLimit record);

	int updateByPrimaryKey(PcmProductOnlineLimit record);

	List<PcmProductOnlineLimit> selectListByParam(Map<String, Object> paramMap);

	List<PcmProductOnlineLimit> selectExistenceByParam(Map<String, Object> paramMap);

	/**
	 * 查询有效的阀值
	 * 
	 * @Methods Name selectProductLimitInfoList
	 * @Create In 2015-11-30 By wangxuan
	 * @param dto
	 * @return List<PcmProductOnlineLimitResultDto>
	 */
	List<PcmProductOnlineLimitResultDto> selectProductLimitInfoList(
			PcmProductOnlineLimitQueryDto dto);

	Integer selectProductLimitInfoCount(PcmProductOnlineLimitQueryDto dto);

	/**
	 * 可查询所有的阀值
	 * 
	 * @Methods Name selectAllProductLimitInfoList
	 * @Create In 2015-11-30 By wangxuan
	 * @param dto
	 * @return List<PcmProductOnlineLimitResultDto>
	 */
	List<PcmProductOnlineLimitResultDto> selectAllProductLimitInfoList(
			PcmProductOnlineLimitQueryDto dto);

	Integer selectAllProductLimitInfoCount(PcmProductOnlineLimitQueryDto dto);

}