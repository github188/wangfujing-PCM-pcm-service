package com.wangfj.product.maindata.service.intf;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.wangfj.core.framework.base.page.Page;
import com.wangfj.product.maindata.domain.vo.AUTagDto;
import com.wangfj.product.maindata.domain.vo.PcmTagDto;

/**
 * 标签表service
 * 
 * @Class Name IPcmTagService
 * @Author zhangxy
 * @Create In 2015年7月21日
 */
public interface IPcmTagService {
	/**
	 * 标签数据分发
	 * 
	 * @Methods Name selectTag
	 * @Create In 2015年7月21日 By zhangxy
	 * @param paramMap
	 * @return Page<PcmTag>
	 * @throws Exception
	 */
	public Page<PcmTagDto> selectTag(Map<String, Object> paramMap) throws Exception;
	
	/**
	 * 添加和修改活动标签
	 * @param dto
	 * @return
	 * @throws ParseException 
	 */
	public String saveOrUpdateTag(AUTagDto dto) throws ParseException;
	
	
	/**
	 * 添加和修改活动标签-批量添加
	 * @param dto
	 * @return
	 * @throws ParseException 
	 */
	public Map<String,Object> saveOrUpdateTags(List<AUTagDto> dto) throws ParseException;
	/**
	 * 根据条件查询tag
	 * @return
	 */
	public List<PcmTagDto> selectTagByParam(Map<String, Object> paramMap);
	/**
	 * 获取时间段内开始的活动信息
	 * @Methods Name selectActiveByTimeFrame
	 * @Create In 2016-1-11 By wangc
	 * @param paraMap
	 * @return List<Map<String,Object>>
	 */
	public Map<String,Object> selectActiveByStartTimeFrame(Map<String,Object> paraMap);
	/**
	 * 获取时间段内结束的活动信息
	 * @Methods Name selectActiveByEndTimeFrame
	 * @Create In 2016-1-11 By wangc
	 * @param paraMap
	 * @return Map<String,Object>
	 */
	public Map<String,Object> selectActiveByEndTimeFrame(Map<String,Object> paraMap);
	/**
	 * 获取指定时间点有效的活动
	 * @Methods Name selectActiveByTimePoint
	 * @Create In 2016-1-14 By wangc
	 * @param paraMap
	 * @return Map<String,Object>
	 */
	public Map<String,Object> selectActiveByTimePoint(Map<String,Object> paraMap);
}
