package com.wangfj.product.maindata.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangfj.core.framework.base.page.Page;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.product.maindata.domain.entity.PcmTag;
import com.wangfj.product.maindata.domain.vo.AUTagDto;
import com.wangfj.product.maindata.domain.vo.PcmTagDto;
import com.wangfj.product.maindata.persistence.PcmTagMapper;
import com.wangfj.product.maindata.service.intf.IPcmTagService;
import com.wangfj.util.Constants;

/**
 * 标签service
 * 
 * @Class Name PcmTagServiceImpl
 * @Author zhangxy
 * @Create In 2015年7月17日
 */
@Service
public class PcmTagServiceImpl implements IPcmTagService {
	private static final Logger logger = LoggerFactory.getLogger(PcmTagServiceImpl.class);

	@Autowired
	PcmTagMapper tagMapper;

	/**
	 * 标签下发(查询)
	 * 
	 * @Methods Name selectTag
	 * @Create In 2015年7月17日 By zhangxueyi
	 * @param paramMap
	 * 
	 */
	@Override
	public Page<PcmTagDto> selectTag(Map<String, Object> paramMap) throws Exception {
		logger.info("start selectTag() 分发标签信息");
		Page<PcmTagDto> page = new Page<PcmTagDto>();
		if (paramMap.get("currentPage") != null) {
			page.setCurrentPage((Integer) paramMap.get("currentPage"));
		}
		if (paramMap.get("pageSize") != null) {
			page.setPageSize((Integer) paramMap.get("pageSize"));
		}
		Integer count = tagMapper.getCountByParam(paramMap);
		page.setCount(count);
		if (paramMap.get("currentPage") != null && paramMap.get("pageSize") != null) {
			paramMap.put("start", page.getStart());
			paramMap.put("limit", page.getLimit());
		} else if (paramMap.get("start") == null) {
			paramMap.put("start", 0);
		}
		List<PcmTag> list = tagMapper.selectPageListByParam(paramMap);
		List<PcmTagDto> listDto = new ArrayList<PcmTagDto>();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (!(list == null)) {
			for (PcmTag tag : list) {
				PcmTagDto dto = new PcmTagDto();
				dto.setSid(tag.getSid());
				dto.setTagName(tag.getTagName());
				dto.setTagType(tag.getTagType());
				dto.setTagCode(tag.getTagCode());
				dto.setOptUserSid(tag.getOptUserSid());
				dto.setStatus(tag.getStatus());
				dto.setOperaterName(tag.getOperaterName());
				dto.setCreateTime(tag.getCreateTime());
				dto.setUpdateTime(tag.getUpdateTime());
				Date beginDate = tag.getBeginDate();
				if (beginDate != null) {
					dto.setBeginDate(format.format(beginDate));
				}
				Date endDate = tag.getEndDate();
				if (endDate != null) {
					dto.setEndDate(format.format(endDate));
				}
				listDto.add(dto);
			}
			page.setList(listDto);
		} else {
			logger.info("end selectTag() 分发标签信息失败，查询结果为空");
			throw new BleException();
		}
		logger.info("end selectTag() 分发标签信息  list:" + list.toString());
		return page;
	}

	/**
	 * 添加/修改门店阀值
	 * 
	 * @param record
	 * @return
	 * @throws ParseException
	 */
	@Override
	public String saveOrUpdateTag(AUTagDto dto) throws ParseException {

		String message = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		if (Constants.A.equals(dto.getActionCode().toUpperCase())) {
			// 判重
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("tagName", dto.getTagName());
			param.put("tagType", dto.getTagType());
			List<PcmTag> li = tagMapper.selectListByParam1(param);
			if (li.size() != 0) {
				return "该标签已存在";
			}
			PcmTag tag = new PcmTag();
			tag.setTagName(dto.getTagName());
			tag.setTagType(dto.getTagType());
			tag.setTagCode(dto.getTagCode());
			tag.setStatus(dto.getStatus());
			tag.setCreateTime(new Date());
			tag.setUpdateTime(new Date());
			tag.setOptUserSid(null);
			tag.setBeginDate(format.parse(dto.getBeginDate()));
			tag.setEndDate(format.parse(dto.getEndDate()));
			tag.setOperaterName(dto.getOperaterName());

			int isSuccess = tagMapper.insertSelective(tag);
			if (isSuccess > 0) {
				message = "添加成功";
			} else {
				message = "添加失败";
			}
		} else if (Constants.U.equals(dto.getActionCode().toUpperCase())) {
			PcmTag tag = new PcmTag();
			tag.setSid(dto.getSid());
			tag.setTagName(dto.getTagName());
			tag.setTagCode(dto.getTagCode());
			tag.setTagType(dto.getTagType());
			tag.setStatus(dto.getStatus());
			tag.setUpdateTime(new Date());
			tag.setOptUserSid(null);
			tag.setBeginDate(format.parse(dto.getBeginDate()));
			tag.setEndDate(format.parse(dto.getEndDate()));
			tag.setOperaterName(dto.getOperaterName());

			int isSuccess = tagMapper.updateByPrimaryKeySelective(tag);
			if (isSuccess > 0) {
				message = "修改成功";
			} else {
				message = "修改失败";
			}
		}

		return message;
	}

	
	/**
	 * 添加/修改标签-批量添加
	 * 
	 * @param record
	 * @return
	 * @throws ParseException
	 */
	@Override
	@Transactional
	public Map<String,Object> saveOrUpdateTags(List<AUTagDto> dtos) throws BleException, ParseException {
		String message = "";
		for(AUTagDto dto : dtos){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (Constants.A.equals(dto.getActionCode().toUpperCase())) {
				// 判重
				//List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
				//List<String> tagList = new ArrayList<String>();
					/*Map<String, Object> param = new HashMap<String, Object>();
					param.put("tagName", dto.getTagName());
					param.put("tagType", dto.getTagType());
					//param.put("beginDate", dto.getBeginDate());//开始时间
					//param.put("endDate", dto.getBeginDate());//结束时间
					List<PcmTag> li = tagMapper.selectListByParam1(param);
					if (li.size() != 0) {
						Map<String,Object> re = new HashMap<String,Object>();
						re.put("success", Constants.FAILURE);
						re.put("tagName", param.get("tagName"));
						re.put("tagType", param.get("tagType"));
						re.put("beginDate", param.get("beginDate"));
						re.put("endDate", param.get("endDate"));
						re.put("msg", "该标签已存在");
						return re;
					}*/
					PcmTag tag = new PcmTag();
					tag.setTagName(dto.getTagName());
					tag.setTagType(dto.getTagType());
					tag.setTagCode(dto.getTagCode());
					tag.setStatus(dto.getStatus());
					tag.setCreateTime(new Date());
					tag.setUpdateTime(new Date());
					tag.setOptUserSid(null);
					tag.setBeginDate(format.parse(dto.getBeginDate()));
					tag.setEndDate(format.parse(dto.getEndDate()));
					tag.setOperaterName(dto.getOperaterName());
					int isSuccess = tagMapper.insertSelective(tag);
					if (isSuccess > 0) {
						message = "添加成功";
					} else {
						/*Map<String,Object> re = new HashMap<String,Object>();
						re.put("success", Constants.FAILURE);
						re.put("tagName", tag.getTagName());
						re.put("tagType", tag.getTagType());
						re.put("beginDate", tag.getBeginDate());
						re.put("endDate", tag.getEndDate());
						re.put("msg", "添加失败");*/
						throw new BleException("",tag.getTagName()+"添加失败");
						
					}
			} else if (Constants.U.equals(dto.getActionCode().toUpperCase())) {
				PcmTag tag = new PcmTag();
				tag.setSid(dto.getSid());
				tag.setTagName(dto.getTagName());
				tag.setTagCode(dto.getTagCode());
				tag.setTagType(dto.getTagType());
				tag.setStatus(dto.getStatus());
				tag.setUpdateTime(new Date());
				tag.setOptUserSid(null);
				tag.setBeginDate(format.parse(dto.getBeginDate()));
				tag.setEndDate(format.parse(dto.getEndDate()));
				tag.setOperaterName(dto.getOperaterName());
				int isSuccess = tagMapper.updateByPrimaryKeySelective(tag);
				if (isSuccess > 0) {
					message = "修改成功";
				} else {
					/*Map<String,Object> re = new HashMap<String,Object>();
					re.put("tagName", tag.getTagName());
					re.put("tagType", tag.getTagType());
					re.put("beginDate", tag.getBeginDate());
					re.put("endDate", tag.getEndDate());
					re.put("msg", "修改失败");*/
					throw new BleException("",tag.getTagName()+"修改失败");
				}
			}
		}
		Map<String,Object> re = new HashMap<String,Object>();
		re.put("msg", message);
		re.put("success", Constants.SUCCESS);
		return re;
	}
	/**
	 * 根据条件查询tag
	 * 
	 * @return
	 */
	@Override
	public List<PcmTagDto> selectTagByParam(Map<String, Object> paramMap) {
		List<PcmTag> list = tagMapper.selectListByParam1(paramMap);
		List<PcmTagDto> listDto = new ArrayList<PcmTagDto>();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (!(list == null)) {
			for (PcmTag tag : list) {
				PcmTagDto dto = new PcmTagDto();
				dto.setSid(tag.getSid());
				dto.setTagName(tag.getTagName());
				dto.setTagType(tag.getTagType());
				dto.setTagCode(tag.getTagCode());
				dto.setOptUserSid(tag.getOptUserSid());
				dto.setStatus(tag.getStatus());
				dto.setOperaterName(tag.getOperaterName());
				dto.setCreateTime(tag.getCreateTime());
				dto.setUpdateTime(tag.getUpdateTime());
				Date beginDate = tag.getBeginDate();
				if (beginDate != null) {
					dto.setBeginDate(format.format(beginDate));
				}
				Date endDate = tag.getEndDate();
				if (endDate != null) {
					dto.setEndDate(format.format(endDate));
				}
				Date nowDate = new Date();
				if (nowDate.before(tag.getEndDate())) {
					listDto.add(dto);
				}
			}
		}
		return listDto;
	}
	/**
	 * 根据开始时间段获得活动信息
	 */
	@Override
	public Map<String, Object> selectActiveByStartTimeFrame(Map<String, Object> paraMap) {
		logger.info("start selectActiveByStartTimeFrame(),param:" + paraMap.toString());
		List<Map<String, Object>> resultList = tagMapper.selectActiveByStartTimeFrame(paraMap);
		logger.info("start selectActiveByStartTimeFrame(),result:" + resultList.toString());
		Integer count = tagMapper.getTagCountByStartTimeFrame(paraMap);
		logger.info("start getTagCountByStartTimeFrame(),result:" + count.toString());
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("list", resultList);
		result.put("total", count);
		return result;
	}
	/**
	 * 根据结束时间段获得活动信息
	 */
	@Override
	public Map<String, Object> selectActiveByEndTimeFrame(Map<String, Object> paraMap) {
		logger.info("start selectActiveByEndTimeFrame(),param:" + paraMap.toString());
		List<Map<String, Object>> resultList = tagMapper.selectActiveByEndTimeFrame(paraMap);
		logger.info("start selectActiveByEndTimeFrame(),result:" + resultList.toString());
		Integer count = tagMapper.getTagCountByEndTimeFrame(paraMap);
		logger.info("start getTagCountByEndTimeFrame(),result:" + count.toString());
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("list", resultList);
		result.put("total", count);
		return result;
	}

	/**
	 * 获取指定时间点有效的活动
	 */
	@Override
	public Map<String, Object> selectActiveByTimePoint(Map<String, Object> paraMap) {
		logger.info("start selectActiveByTimePoint(),param:" + paraMap.toString());
		List<Map<String, Object>> resultList = tagMapper.selectActiveByTimePoint(paraMap);
		logger.info("start selectActiveByTimePoint(),result:" + resultList.toString());
		Integer count = tagMapper.getActiveCountByTimePoint(paraMap);
		logger.info("start getActiveCountByTimePoint(),result:" + count.toString());
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("list", resultList);
		result.put("total", count);
		return result;
	}
	
	
}
