package com.wangfj.product.stocks.service.intf;

import java.util.List;
import java.util.Map;

import com.wangfj.product.stocks.domain.entity.PcmLockTypeDict;

public interface IPcmLockTypeDictService {
	/**
	 * 添加锁定类型
	 * 
	 * @Methods Name insertSelective
	 * @Create In 2015年7月31日 By yedong
	 * @param record
	 * @return int
	 */
	public int insertSelective(PcmLockTypeDict record);

	/**
	 * 修改
	 * 
	 * @Methods Name updateSelective
	 * @Create In 2015年7月31日 By yedong
	 * @param record
	 * @return int
	 */
	public int updateSelective(PcmLockTypeDict record);

	/**
	 * 查询
	 * 
	 * @Methods Name selectListByParam
	 * @Create In 2015年7月31日 By yedong
	 * @param paramMap
	 * @return List<PcmLockTypeDict>
	 */
	public List<PcmLockTypeDict> selectListByParam(Map<String, Object> paramMap);
}
