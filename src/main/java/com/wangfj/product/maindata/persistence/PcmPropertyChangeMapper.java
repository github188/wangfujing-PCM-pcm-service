package com.wangfj.product.maindata.persistence;

import java.util.List;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.maindata.domain.entity.PcmPropertyChange;

public interface PcmPropertyChangeMapper extends BaseMapper<PcmPropertyChange> {
	int deleteByPrimaryKey(Long sid);

	int insertSelective(PcmPropertyChange record);

	PcmPropertyChange selectByPrimaryKey(Long sid);

	int updateByPrimaryKeySelective(PcmPropertyChange record);

	int updateByPrimaryKeyWithBLOBs(PcmPropertyChange record);

	int updateByPrimaryKey(PcmPropertyChange record);

	List<PcmPropertyChange> selectList();
}