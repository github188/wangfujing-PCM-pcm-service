package com.wangfj.product.organization.service.intf;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wangfj.core.framework.base.page.Page;
import com.wangfj.product.organization.domain.vo.PcmFloorDto;
import com.wangfj.product.organization.domain.vo.PcmFloorResultDto;
import com.wangfj.product.organization.domain.vo.SelectPcmFloorDto;

public interface IPcmFloorService {

	public List<PcmFloorDto> findFloorByParamFromPcm(Map<String, Object> paramMap)
			throws IllegalAccessException, InvocationTargetException;

	Integer getCountByParam(Map<String, Object> map);

	Page<HashMap<String, Object>> findFloorFromPCM(PcmFloorDto floordto, Page<PcmFloorDto> pagedto);

	PcmFloorDto selectByFloorSid(Long sid);

	Integer deleteFloor(PcmFloorDto dto);

	/**
	 * 查询此门店下所有楼层列表
	 * 
	 * @Methods Name getFloorsByShopSid
	 * @Create In 2015-8-24 By chengsj
	 * @param shopSid
	 * @return List<Map<String,Object>>
	 */
	List<Map<String, Object>> getFloorsByShopSid(Long shopSid);

	/**
	 * 分页查询
	 * 
	 * @Methods Name findPageFloor
	 * @Create In 2015-8-25 By niuzhifan
	 * @param selectFloorDto
	 * @return Page<PcmFloorResultDto>
	 */
	Page<PcmFloorResultDto> findPageFloor(SelectPcmFloorDto selectFloorDto);

	/**
	 * 查询楼层List
	 * 
	 * @param dto
	 * @return
	 */
	List<PcmFloorResultDto> selectFloorListByParam(SelectPcmFloorDto dto);

	/**
	 * 移动工作台调用主数据获取楼层信息列表
	 * 
	 * @create in 2015-8-31 nzf
	 * @param dto
	 * @return
	 */
	List<Map<String, Object>> selectFloorByShopCode(PcmFloorDto dto);

	/**
	 * 移动工作台调用主数据获取门店对应楼层下所有专柜信
	 * 
	 * @create in 2015-8-31 nzf
	 * @param dto
	 * @return
	 */
	List<Map<String, Object>> selectFloorsByShopAndFloorCode(PcmFloorDto dto);

	/**
	 * 主数据ERP发送楼层字典到其他系统(增量下发)
	 */
	List<Map<String, Object>> selectFloorsANDOrg(Map<String, Object> dtoMap);

	List<Map<String, Object>> pushFloorData(Map<String, Object> para);

	/**
	 * 楼层判重
	 * 
	 * @Methods Name isExistence
	 * @Create In 2015-11-26 By wangxuan
	 * @param para
	 * @return boolean
	 */
	boolean isExistence(Map<String, Object> para);

	Map<String, Object> addFloor(PcmFloorDto dto);

	Map<String, Object> modifyFloor(PcmFloorDto dto);

}
