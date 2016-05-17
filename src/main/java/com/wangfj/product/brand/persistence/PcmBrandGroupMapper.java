package com.wangfj.product.brand.persistence;

import java.util.List;
import java.util.Map;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.brand.domain.entity.PcmBrandGroup;
import com.wangfj.product.brand.domain.vo.PcmBrandGroupDto;
import com.wangfj.product.brand.domain.vo.SelectPcmBrandGroupPageDto;

/**
 * 
 * @Class Name PcmBrandGroupMapper
 * @Author wangx
 * @Create In 2015年7月31日
 */
public interface PcmBrandGroupMapper extends BaseMapper<PcmBrandGroup> {

	int deleteByPrimaryKey(Long sid);

	Integer insert(PcmBrandGroup record);

	int insertSelective(PcmBrandGroup record);

	PcmBrandGroup selectByPrimaryKey(Long sid);

	int updateByPrimaryKeySelective(PcmBrandGroup record);

	int updateByPrimaryKey(PcmBrandGroup record);

	/**
	 * 根据集团品牌Sid查询集团品牌
	 * 
	 * @Methods Name selectByBrandSid
	 * @Create In 2015年7月30日 By wangx
	 * @param brandSid
	 * @return PcmBrandGroup
	 */
	PcmBrandGroup selectByBrandSid(String brandSid);

	/**
	 * 根据集团名称和是否有效查询集团品牌
	 * 
	 * @Methods Name selectListByBrandNameAndStatus
	 * @Create In 2015-8-8 By wangx
	 * @param paraMap
	 * @return List<PcmBrandGroup>
	 */
	List<PcmBrandGroup> selectListByBrandNameAndStatus(Map<String, Object> paraMap);

	/**
	 * 根据集团编码和是否有效查询集团品牌
	 * 
	 * @Methods Name selectListByBrandSidAndStatus
	 * @Create In 2015-8-8 By wangx
	 * @param paraMap
	 * @return List<PcmBrandGroup>
	 */
	List<PcmBrandGroup> selectListByBrandSidAndStatus(Map<String, Object> paraMap);

	/**
	 * 根据集团品牌Sid或者集团名称查询集团品牌
	 * 
	 * @Methods Name selectListByBrandSidOrBrandName
	 * @Create In 2015年7月31日 By wangx
	 * @param paraMap
	 * @return List<PcmBrandGroup>
	 */
	List<PcmBrandGroup> selectListByBrandSidOrBrandName(Map<String, Object> paraMap);

	/**
	 * 查询集团品牌
	 * 
	 * @Methods Name selectListByParaSelective
	 * @Create In 2015-7-31 By wangx
	 * @param pcmBrandGroupDto
	 * @return List<PcmBrandGroup>
	 */
	List<PcmBrandGroup> selectListByParaSelective(PcmBrandGroupDto pcmBrandGroupDto);

	/**
	 * 分页记录总数查询
	 * 
	 * @Methods Name getCountByParaForPage
	 * @Create In 2015-8-3 By wangx
	 * @param pageDto
	 * @return Integer
	 */
	Long getCountByParaForPage(SelectPcmBrandGroupPageDto pageDto);

	/**
	 * 分页查询
	 * 
	 * @Methods Name selectListByParaForPage
	 * @Create In 2015-8-3 By wangx
	 * @param pageDto
	 * @return List<PcmBrandGroup>
	 */
	List<PcmBrandGroup> selectListByParaForPage(SelectPcmBrandGroupPageDto pageDto);

}