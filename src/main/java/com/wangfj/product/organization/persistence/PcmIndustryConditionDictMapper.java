package com.wangfj.product.organization.persistence;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.organization.domain.entity.PcmIndustryConditionDict;

/**
 * 
 * @Class Name PcmIndustryConditionDictMapper
 * @Author yedong
 * @Create In 2015年7月3日
 */
public interface PcmIndustryConditionDictMapper extends BaseMapper<PcmIndustryConditionDict> {
	/**
	 * 删除
	 * 
	 * @Methods Name deleteByPrimaryKey
	 * @Create In 2015年7月3日 By yedong
	 * @param sid
	 * @return int
	 */
	int deleteByPrimaryKey(Long sid);

	Integer insert(PcmIndustryConditionDict record);

	/**
	 * 添加
	 * 
	 * @Methods Name insertSelective
	 * @Create In 2015年7月3日 By yedong
	 * @param record
	 * @return int
	 */
	int insertSelective(PcmIndustryConditionDict record);

	/**
	 * 修改
	 * 
	 * @Methods Name updateByPrimaryKeySelective
	 * @Create In 2015年7月3日 By yedong
	 * @param record
	 * @return int
	 */
	int updateByPrimaryKeySelective(PcmIndustryConditionDict record);

	int updateByPrimaryKey(PcmIndustryConditionDict record);

	/**
	 * 根据sid查询
	 * 
	 * @Methods Name selectByPrimaryKey
	 * @Create In 2015-8-24 By wangxuan
	 * @param sid
	 * @return PcmIndustryConditionDict
	 */
	PcmIndustryConditionDict selectByPrimaryKey(Long sid);

}