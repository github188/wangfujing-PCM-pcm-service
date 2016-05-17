package com.wangfj.product.organization.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.organization.domain.entity.PcmOrganization;
import com.wangfj.product.organization.domain.vo.PcmInfoQueryDto;
import com.wangfj.product.organization.domain.vo.PcmInfoResultDto;
import com.wangfj.product.organization.domain.vo.PcmOrgPartInfoDto;
import com.wangfj.product.organization.domain.vo.PcmOrganizationDto;
import com.wangfj.product.organization.domain.vo.PcmOrganizationPAD;
import com.wangfj.product.organization.domain.vo.PcmOrganizationResultDto;
import com.wangfj.product.organization.domain.vo.SelectPcmOrganizationDto;

public interface PcmOrganizationMapper extends BaseMapper<PcmOrganization> {
	/**
	 * 删除
	 * 
	 * @Methods Name deleteByPrimaryKey
	 * @Create In 2015年7月3日 By yedong
	 * @param sid
	 * @return int
	 */
	int deleteByPrimaryKey(Long sid);

	Integer insert(PcmOrganization record);

	/**
	 * 添加
	 * 
	 * @Methods Name insertSelective
	 * @Create In 2015年7月3日 By yedong
	 * @param record
	 * @return int
	 */
	int insertSelective(PcmOrganization record);

	/**
	 * 修改
	 * 
	 * @Methods Name updateByPrimaryKeySelective
	 * @Create In 2015年7月3日 By yedong
	 * @param record
	 * @return int
	 */
	int updateByPrimaryKeySelective(PcmOrganization record);

	int updateByPrimaryKey(PcmOrganization record);

	// 逻辑删除
	int updateByPcmOrganizationDto(PcmOrganizationDto record);

	/**
	 * MDERP基础组织机构主数据分发
	 * 
	 * @Methods Name findOrganizationByParamFromPcm
	 * @Create In 2015年7月14日 By yedong
	 * @return List<PublishOrganizationDto>
	 */
	List<HashMap<String, Object>> findOrganizationByParamFromPcm(Map<String, Object> map);

	List<HashMap<String, Object>> pushOrgData(Map<String, Object> paramMap);

	/**
	 * 
	 * @Methods Name selectListByParamOrg
	 * @Create In 2015年8月7日 By yedong
	 * @param record
	 * @return List<HashMap<String,Object>>
	 */
	List<HashMap<String, Object>> selectListByParamOrg(PcmOrganizationDto dto);

	Integer getCountByParamOrg(PcmOrganizationDto dto);

	/**
	 * 通过机构编码查找组织机构
	 * 
	 * @param organizationName
	 * @return
	 */
	PcmOrganization getPOrganizationByOrganizationCode(String organizationCode);

	/**
	 * 分页查询
	 * 
	 * @Methods Name selectPageByPara
	 * @Create In 2015-8-18 By wangx
	 * @param selectOrganizationDto
	 * @return List<PcmOrganizationResultDto>
	 */
	List<PcmOrganizationResultDto> selectPageByPara(SelectPcmOrganizationDto selectOrganizationDto);

	List<PcmOrgPartInfoDto> selectPagePartInfoByPara(SelectPcmOrganizationDto dto);

	/**
	 * 分页查询总数量
	 * 
	 * @Methods Name getPageCountByPara
	 * @Create In 2015-8-18 By wangx
	 * @param selectOrganizationDto
	 * @return Integer
	 */
	Integer getPageCountByPara(SelectPcmOrganizationDto selectOrganizationDto);

	/**
	 * 根据sid查询组织机构
	 * 
	 * @Methods Name selectByPrimaryKey
	 * @Create In 2015-8-20 By wangxuan
	 * @param sid
	 * @return PcmOrganization
	 */
	PcmOrganization selectByPrimaryKey(Long sid);

	/**
	 * 根据门店sid查询组织机构
	 * 
	 * @param dto
	 * @return
	 */
	List<Map<String, Object>> selectByShopSid(PcmOrganizationDto dto);

	/**
	 * 多条件查询
	 * 
	 * @Methods Name selectListByParam
	 * @Create In 2015-8-25 By wangxuan
	 * @param paramMap
	 * @return List<PcmOrganization>
	 */
	List<PcmOrganization> selectListByParam(Map<String, Object> paramMap);

	List<Map<String, Object>> selectShopPageByPara();

	/**
	 * 移动工作台调用主数据获取组织机构信息
	 * 
	 * @create in 2015-9-1 nzf
	 * @param map
	 * @return
	 */
	List<PcmOrganizationPAD> findOrganizationByParamFromPAD(Map<String, Object> map);

	/**
	 * 搜索线上查询所有门店
	 * 
	 * @Methods Name searchOnlineShopList
	 * @Create In 2015-11-10 By wangxuan
	 * @return List<Map<String,Object>>
	 */
	List<Map<String, Object>> searchOnlineShopList();

	/**
	 * CMS 渠道下对应的门店
	 * 
	 * @Methods Name findShopByChannelParamToCMS
	 * @Create In 2015-12-28 By wangxuan
	 * @param dto
	 * @return List<PcmInfoResultDto>
	 */
	List<PcmInfoResultDto> findShopByChannelParamToCMS(PcmInfoQueryDto dto);

}