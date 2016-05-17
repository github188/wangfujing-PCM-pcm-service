package com.wangfj.product.core.persistence;

import java.util.List;
import java.util.Map;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.core.domain.dto.PcmDictDto;
import com.wangfj.product.core.domain.dto.PcmGetDictDto;
import com.wangfj.product.core.domain.dto.PcmSelectDictDto;
import com.wangfj.product.core.domain.entity.PcmDict;

public interface PcmDictMapper extends BaseMapper<PcmDict> {

	int deleteByPrimaryKey(Long sid);

	Integer insert(PcmDict record);

	int insertSelective(PcmDict record);

	PcmDict selectByPrimaryKey(Long sid);

	int updateByPrimaryKeySelective(PcmDict record);

	int updateByPrimaryKey(PcmDict record);

	int getCountByParamForCheck(PcmDict dto);

	/* 非分页查询 */
	List<PcmDict> selectPcmDict(PcmDictDto dto);

	/* 分页查询 */
	List<PcmSelectDictDto> selectPcmDictInfo(PcmGetDictDto dto);

	/* 分页查询查总数量 */
	Integer getCountDictInfo(PcmGetDictDto dto);

	List<PcmDict> selectListByParam(Map<String, Object> paramMap);

}