package com.wangfj.product.stocks.persistence;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.stocks.domain.entity.PcmChangeTypeDict;

/**
 * 
 * @Class Name PcmChangeTypeDictMapper
 * @Author yedong
 * @Create In 2015年7月3日
 */
public interface PcmChangeTypeDictMapper extends BaseMapper<PcmChangeTypeDict> {
	/**
	 * 删除
	 * 
	 * @Methods Name deleteByPrimaryKey
	 * @Create In 2015年7月3日 By yedong
	 * @param sid
	 * @return int
	 */
	int deleteByPrimaryKey(Integer sid);

	Integer insert(PcmChangeTypeDict record);

	/**
	 * 添加
	 * 
	 * @Methods Name insertSelective
	 * @Create In 2015年7月3日 By yedong
	 * @param record
	 * @return int
	 */
	int insertSelective(PcmChangeTypeDict record);

	/**
	 * 根据SID修改
	 * 
	 * @Methods Name updateByPrimaryKeySelective
	 * @Create In 2015年7月3日 By yedong
	 * @param record
	 * @return int
	 */
	int updateByPrimaryKeySelective(PcmChangeTypeDict record);

	int updateByPrimaryKey(PcmChangeTypeDict record);

}