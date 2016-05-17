package com.wangfj.product.maindata.persistence;

import java.util.List;
import java.util.Map;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.maindata.domain.entity.PcmSaleUnitDict;

public interface PcmSaleUnitDictMapper extends BaseMapper<PcmSaleUnitDict> {
	int deleteByPrimaryKey(Long sid);

	int insertSelective(PcmSaleUnitDict record);

	PcmSaleUnitDict selectByPrimaryKey(Long sid);

	int updateByPrimaryKeySelective(PcmSaleUnitDict record);

	int updateByPrimaryKey(PcmSaleUnitDict record);

	/**
	 * 移动工作台调用主数据获取单位信息
	 * 
	 * @Methods Name selectListByParamForPad
	 * @Create In 2015-8-31 By wangxuan
	 * @param paramMap
	 * @return List<PcmSaleUnitDict>
	 */
	List<PcmSaleUnitDict> selectListByParamForPad(Map<String, Object> paramMap);

}