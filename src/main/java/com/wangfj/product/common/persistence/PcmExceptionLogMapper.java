package com.wangfj.product.common.persistence;

import java.util.List;

import com.wangfj.product.common.domain.entity.PcmExceptionLog;
import com.wangfj.product.common.domain.vo.PcmExceptionLogDto;
import com.wangfj.product.common.domain.vo.PcmExceptionLogDtos;

public interface PcmExceptionLogMapper {
    int deleteByPrimaryKey(Long sid);

	int insert(PcmExceptionLog record);

	int insertSelective(PcmExceptionLogDto record);

	PcmExceptionLog selectByPrimaryKey(Long sid);

	int updateByPrimaryKeySelective(PcmExceptionLog record);

	int updateByPrimaryKeyWithBLOBs(PcmExceptionLog record);

	int updateByPrimaryKey(PcmExceptionLog record);
	//分页查询异常信息
	List<PcmExceptionLog> selectPage (PcmExceptionLogDtos pcmExceptionLogDtos);
	//查询数量
	Long getCountByParam(PcmExceptionLogDtos pcmExceptionLogDtos);
}