package com.wangfj.product.brand.service.intf;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import com.wangfj.core.framework.base.page.Page;
import com.wangfj.product.brand.domain.entity.PcmBrandGroup;
import com.wangfj.product.brand.domain.vo.PcmBrandGroupDto;
import com.wangfj.product.brand.domain.vo.SelectPcmBrandGroupPageDto;

/**
 * 集团品牌管理
 * 
 * @Class Name IPcmBrandGroupService
 * @Author wangx
 * @Create In 2015年7月30日
 */
public interface IPcmBrandGroupService {

	/**
	 * 修改集团品牌
	 * 
	 * @Methods Name updateBrandGroup
	 * @Create In 2015年7月30日 By wangx
	 * @param pcmBrandGroupDto
	 * @return Integer
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	Integer updateBrandGroup(PcmBrandGroupDto pcmBrandGroupDto) throws IllegalAccessException,
			InvocationTargetException;

	/**
	 * 查询集团品牌
	 * 
	 * @Methods Name findBrandGroupByPara
	 * @Create In 2015-7-31 By wangx
	 * @param brandGroupDto
	 * @return List<PcmBrandGroupDto>
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	List<PcmBrandGroupDto> findBrandGroupByPara(PcmBrandGroupDto brandGroupDto)
			throws IllegalAccessException, InvocationTargetException;

	/**
	 * 分页查询集团品牌
	 * 
	 * @Methods Name findBrandGroupForPage
	 * @Create In 2015-8-3 By wangx
	 * @param pageDto
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 *             Page<PcmBrandGroupDto>
	 */
	Page<PcmBrandGroupDto> findBrandGroupForPage(SelectPcmBrandGroupPageDto pageDto)
			throws IllegalAccessException, InvocationTargetException;

	/**
	 * 集团品牌下发
	 * 
	 * @Methods Name findPageBrandGroup
	 * @Create In 2015-8-4 By wangx
	 * @param paraMap
	 * @return Page<PcmBrandGroup>
	 */
	Page<PcmBrandGroup> findPageBrandGroup(Map<String, Object> paraMap);

	/**
	 * 删除集团品牌
	 * 
	 * @Methods Name deleteBrandGroup
	 * @Create In 2015-8-5 By wangx
	 * @param paraMap
	 * @return Integer
	 */
	Integer deleteBrandGroup(Map<String, Object> paraMap);
	
	

}
