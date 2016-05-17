package com.wangfj.product.maindata.persistence;

import java.util.List;
import java.util.Map;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.maindata.domain.entity.PcmColorCodeDict;

public interface PcmColorCodeDictMapper extends BaseMapper<PcmColorCodeDict> {
	int deleteByPrimaryKey(Long sid);

	int insertSelective(PcmColorCodeDict record);

	PcmColorCodeDict selectByPrimaryKey(Long sid);

	int updateByPrimaryKeySelective(PcmColorCodeDict record);

	int updateByPrimaryKey(PcmColorCodeDict record);

	/**
	 * 移动工作台调用主数据获取色码信息
	 * 
	 * @Methods Name selectListByParamForPad
	 * @Create In 2015-8-28 By wangxuan
	 * @param paramMap
	 * @return List<PcmColorCodeDict>
	 */
	List<PcmColorCodeDict> selectListByParamForPad(Map<String, Object> paramMap);

}