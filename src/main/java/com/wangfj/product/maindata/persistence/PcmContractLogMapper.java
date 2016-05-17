package com.wangfj.product.maindata.persistence;

import java.util.List;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.maindata.domain.entity.PcmContractLog;
import com.wangfj.product.maindata.domain.vo.ContractLogFromPcmToSupDto;
import com.wangfj.product.maindata.domain.vo.GetContractLogForSupDto;
import com.wangfj.product.maindata.domain.vo.GetContractLogResultForSupDto;
import com.wangfj.product.maindata.domain.vo.PcmContractLogDto;
import com.wangfj.product.maindata.domain.vo.PcmContractLogPartDto;
import com.wangfj.product.maindata.domain.vo.PcmContractLogQueryDto;

public interface PcmContractLogMapper extends BaseMapper<PcmContractLog> {
	int deleteByPrimaryKey(Long sid);

	Integer insert(PcmContractLog record);

	int insertSelective(PcmContractLog record);

	PcmContractLog selectByPrimaryKey(Long sid);

	int updateByPrimaryKeySelective(PcmContractLog record);

	int updateByPrimaryKey(PcmContractLog record);

	int updateByParam(PcmContractLog record);

	/**
	 * 查询要约的部分信息
	 *
	 * @param dto
	 * @return List<PcmContractLogPartDto>
	 * @Methods Name findContractLogList
	 * @Create In 2015-12-8 By wangxuan
	 */
	List<PcmContractLogPartDto> findContractLogList(PcmContractLogQueryDto dto);

	/**
	 * 分页查询要约信息
	 *
	 * @param dto
	 * @return
	 */
	List<PcmContractLogDto> findPageContractLog(PcmContractLogQueryDto dto);

	/**
	 * 分页查询要约数量
	 * 
	 * @param dto
	 * @return
	 */
	Integer findPageCountContractLog(PcmContractLogQueryDto dto);
	/**
	 * 供应商获取要约信息
	 * @Methods Name GetContractLogFromPcmToSup
	 * @Create In 2016-3-30 By wangc
	 * @param paraDto
	 * @return List<GetContractLogResultForSupDto>
	 */
	List<GetContractLogResultForSupDto> getContractLogFromPcmToSup(GetContractLogForSupDto paraDto);
	/**
	 * 供应商获取要约信息数量
	 * @Methods Name GetContractLogCountFromPcmToSup
	 * @Create In 2016-3-30 By wangc
	 * @param paraDto
	 * @return Integer
	 */
	Integer getContractLogCountFromPcmToSup(GetContractLogForSupDto paraDto);
	/**
	 * 供应商根据时间段获取要约信息数量
	 * @Methods Name getContractLogFromPcmToSupByTime
	 * @Create In 2016-4-6 By wangc
	 * @param paraDto
	 * @return Integer
	 */
	Integer getContractLogCountFromPcmToSupByTime(ContractLogFromPcmToSupDto paraDto);

}