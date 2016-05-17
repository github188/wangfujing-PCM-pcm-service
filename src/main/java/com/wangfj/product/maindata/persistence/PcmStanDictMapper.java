package com.wangfj.product.maindata.persistence;

import java.util.List;
import java.util.Map;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.maindata.domain.entity.PcmStanDict;

public interface PcmStanDictMapper extends BaseMapper<PcmStanDict> {
	int deleteByPrimaryKey(Long sid);

	int insertSelective(PcmStanDict record);

	PcmStanDict selectByPrimaryKey(Long sid);

	int updateByPrimaryKeySelective(PcmStanDict record);

	int updateByPrimaryKey(PcmStanDict record);

	/**
	 * 移动工作台调用主数据获取尺码信息
	 * 
	 * @Methods Name selectListByParamForPad
	 * @Create In 2015-9-9 By wangxuan
	 * @param paramMap
	 * @return List<PcmStanDict>
	 */
	List<PcmStanDict> selectListByParamForPad(Map<String, Object> paramMap);

}