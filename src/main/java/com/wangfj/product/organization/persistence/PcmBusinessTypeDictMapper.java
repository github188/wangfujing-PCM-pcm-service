package com.wangfj.product.organization.persistence;

import java.util.List;
import java.util.Map;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.organization.domain.entity.PcmBusinessTypeDict;

/**
 * 
 * @Class Name PcmBusinessTypeDictMapper
 * @Author yedong
 * @Create In 2015年7月3日
 */
public interface PcmBusinessTypeDictMapper extends BaseMapper<PcmBusinessTypeDict> {
	/**
	 * 根据SID删除
	 * 
	 * @Methods Name deleteByPrimaryKey
	 * @Create In 2015年7月3日 By yedong
	 * @param sid
	 * @return int
	 */
	int deleteByPrimaryKey(Integer sid);

	Integer insert(PcmBusinessTypeDict record);

	/**
	 * 添加
	 * 
	 * @Methods Name insertSelective
	 * @Create In 2015年7月3日 By yedong
	 * @param record
	 * @return int
	 */
	int insertSelective(PcmBusinessTypeDict record);

	/**
	 * 修改
	 * 
	 * @Methods Name updateByPrimaryKeySelective
	 * @Create In 2015年7月3日 By yedong
	 * @param record
	 * @return int
	 */
	int updateByPrimaryKeySelective(PcmBusinessTypeDict record);

	List<PcmBusinessTypeDict> selectValidByParam(Map<String, Object> paramMap);

	int updateByPrimaryKey(PcmBusinessTypeDict record);
}