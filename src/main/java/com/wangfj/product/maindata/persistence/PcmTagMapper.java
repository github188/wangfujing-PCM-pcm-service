package com.wangfj.product.maindata.persistence;

import java.util.List;
import java.util.Map;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.maindata.domain.entity.PcmTag;

public interface PcmTagMapper extends BaseMapper<PcmTag> {
	/**
	 * 根据专柜商品编码查询标签信息
	 * 
	 * @Methods Name getTagNameInfoByProCode
	 * @Create In 2015年12月18日 By yedong
	 * @param paramMap
	 * @return List<String>
	 */
	List<String> getTagNameInfoByProCode(Map<String, Object> paramMap);

	/**
	 * 根据标签sid删除
	 * 
	 * @param sid
	 * @return
	 */
	int deleteByPrimaryKey(Long sid);

	/**
	 * 添加
	 * 
	 * @param record
	 * @return
	 */
	int insertSelective(PcmTag record);

	/**
	 * 根据标签sid查询
	 * 
	 * @param sid
	 * @return
	 */
	PcmTag selectByPrimaryKey(Long sid);

	/**
	 * 根据标签sid修改部分
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(PcmTag record);

	/**
	 * 根据标签sid修改
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKey(PcmTag record);

	/**
	 * 分页查询（条件）
	 * 
	 * @param record
	 * @return
	 */
	List<PcmTag> selectPageListByParam(Map<String, Object> paramMap);
	
	List<PcmTag> selectListByParam(Map<String, Object> paramMap);
	
	List<PcmTag> selectListByParam1(Map<String, Object> paramMap);
	/**
	 * 获取时间段内开始的活动
	 * @Methods Name selectActiveByTimeFrame
	 * @Create In 2016-1-11 By wangc
	 * @param paraMap
	 * @return List<Map<String,Object>>
	 */
	List<Map<String,Object>> selectActiveByStartTimeFrame(Map<String,Object> paraMap);

	/**
	 * 获取时间段内开始的活动数量
	 * @Methods Name getCountByTimeFrame
	 * @Create In 2016-1-11 By wangc
	 * @param paraMap
	 * @return int
	 */
	Integer getTagCountByStartTimeFrame(Map<String,Object> paraMap);
	/**
	 * 获取时间段内结束的活动
	 * @Methods Name selectActiveByEndTimeFrame
	 * @Create In 2016-1-11 By wangc
	 * @param paraMap
	 * @return List<Map<String,Object>>
	 */
	List<Map<String,Object>> selectActiveByEndTimeFrame(Map<String,Object> paraMap);
	/**
	 * 获取时间段内结束的活动数量
	 * @Methods Name getTagCountByEndTimeFrame
	 * @Create In 2016-1-11 By wangc
	 * @param paraMap
	 * @return Integer
	 */
	Integer getTagCountByEndTimeFrame(Map<String,Object> paraMap);
	/**
	 * 获取指定时间点有效的活动
	 * @Methods Name selectActiveByTimePoint
	 * @Create In 2016-1-14 By wangc
	 * @param paraMap
	 * @return List<Map<String,Object>>
	 */
	List<Map<String,Object>> selectActiveByTimePoint(Map<String,Object> paraMap);
	/**
	 * 获取指定时间点有效的活动数量
	 * @Methods Name getActiveCountByTimePoint
	 * @Create In 2016-1-14 By wangc
	 * @param paraMap
	 * @return Integer
	 */
	Integer getActiveCountByTimePoint(Map<String,Object> paraMap);
}