package com.wangfj.product.brand.service.intf;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import com.wangfj.product.brand.domain.vo.PcmBrandRelationDto;

/**
 * 门店品牌与集团品牌的维护
 * 
 * @Class Name IPcmBrandRelationService
 * @Author wangx
 * @Create In 2015年7月30日
 */
public interface IPcmBrandRelationService {

	/**
	 * 添加门店品牌与集团品牌的关系
	 * 
	 * @Methods Name addRelation
	 * @Create In 2015年7月30日 By wangx
	 * @param pcmBrandRelationDto
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 *             Integer
	 */
	Integer addRelation(PcmBrandRelationDto pcmBrandRelationDto) throws IllegalAccessException,
			InvocationTargetException;

	/**
	 * 集团品牌门店品牌关联关系的删除
	 * 
	 * @Methods Name deleteRelation
	 * @Create In 2015-8-8 By wangx
	 * @param pcmBrandRelation
	 * @return Integer
	 */
	Integer deleteRelation(PcmBrandRelationDto pcmBrandRelationDto);

	/**
	 * 条件查询门店品牌与集团品牌关联关系表
	 * 
	 * @Methods Name findListByPara
	 * @Create In 2015-8-10 By wangx
	 * @param brandRelationDto
	 * @return List<PcmBrandRelationDto>
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	List<PcmBrandRelationDto> findListByPara(Map<String, Object> para)
			throws IllegalAccessException, InvocationTargetException;

	/**
	 * 修改门店品牌与集团品牌的关系
	 * 
	 * @Methods Name updateRelation
	 * @Create In 2015-8-12 By wangx
	 * @param pcmBrandRelationDto
	 * @return Integer
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	Integer updateRelation(PcmBrandRelationDto pcmBrandRelationDto) throws IllegalAccessException, InvocationTargetException;

}
