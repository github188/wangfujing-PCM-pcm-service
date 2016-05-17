package com.wangfj.product.stocks.persistence;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.stocks.domain.entity.PcmStockTypeDict;

/**
 * 
 * @Class Name PcmStockTypeDictMapper
 * @Author yedong
 * @Create In 2015年7月3日
 */
public interface PcmStockTypeDictMapper extends BaseMapper<PcmStockTypeDict> {
	/**
	 * 删除
	 * 
	 * @Methods Name deleteByPrimaryKey
	 * @Create In 2015年7月3日 By yedong
	 * @param sid
	 * @return int
	 */
	int deleteByPrimaryKey(Integer sid);

	Integer insert(PcmStockTypeDict record);

	/**
	 * 添加
	 * 
	 * @Methods Name insertSelective
	 * @Create In 2015年7月3日 By yedong
	 * @param record
	 * @return int
	 */
	int insertSelective(PcmStockTypeDict record);

	/**
	 * 修改
	 * 
	 * @Methods Name updateByPrimaryKeySelective
	 * @Create In 2015年7月3日 By yedong
	 * @param record
	 * @return int
	 */
	int updateByPrimaryKeySelective(PcmStockTypeDict record);

	int updateByPrimaryKey(PcmStockTypeDict record);
}