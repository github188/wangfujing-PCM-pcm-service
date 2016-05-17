package com.wangfj.product.maindata.persistence;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.maindata.domain.entity.PcmErpProductChange;

import java.util.List;
import java.util.Map;

public interface PcmErpProductChangeMapper extends BaseMapper<PcmErpProductChange> {
	int deleteByPrimaryKey(Long sid);

	Integer insert(PcmErpProductChange record);

	int insertSelective(PcmErpProductChange record);

	PcmErpProductChange selectByPrimaryKey(Long sid);

	int updateByPrimaryKeySelective(PcmErpProductChange record);

	int updateByPrimaryKeyWithBLOBs(PcmErpProductChange record);

	int updateByPrimaryKey(PcmErpProductChange record);

	/**
	 * 查询当天的单据
	 * 
	 * @param paramMap
	 * @return
	 */
	List<PcmErpProductChange> selectListByCurrentDate(Map<String, Object> paramMap);
}