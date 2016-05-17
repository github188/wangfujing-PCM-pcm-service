package com.wangfj.product.stocks.persistence;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.stocks.domain.entity.PcmLockTypeDict;

/**
 * 
 * @Class Name PcmLockTypeDictMapper
 * @Author yedong
 * @Create In 2015年7月3日
 */
public interface PcmLockTypeDictMapper extends BaseMapper<PcmLockTypeDict> {
	/**
	 * 删除
	 * 
	 * @Methods Name deleteByPrimaryKey
	 * @Create In 2015年7月3日 By yedong
	 * @param sid
	 * @return int
	 */
	int deleteByPrimaryKey(Integer sid);

	Integer insert(PcmLockTypeDict record);

	/**
	 * 添加
	 * 
	 * @Methods Name insertSelective
	 * @Create In 2015年7月3日 By yedong
	 * @param record
	 * @return int
	 */
	int insertSelective(PcmLockTypeDict record);

	/**
	 * 修改
	 * 
	 * @Methods Name updateByPrimaryKeySelective
	 * @Create In 2015年7月3日 By yedong
	 * @param record
	 * @return int
	 */
	int updateByPrimaryKeySelective(PcmLockTypeDict record);

	int updateByPrimaryKey(PcmLockTypeDict record);
}