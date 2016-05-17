package com.wangfj.product.organization.service.intf;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wangfj.core.framework.base.page.Page;
import com.wangfj.product.organization.domain.entity.PcmOrganization;
import com.wangfj.product.organization.domain.vo.PcmOrgDto;
import com.wangfj.product.organization.domain.vo.PcmOrgPartInfoDto;
import com.wangfj.product.organization.domain.vo.PcmOrganizationDto;
import com.wangfj.product.organization.domain.vo.PcmOrganizationPAD;
import com.wangfj.product.organization.domain.vo.PcmOrganizationResultDto;
import com.wangfj.product.organization.domain.vo.PublishOrganizationDto;
import com.wangfj.product.organization.domain.vo.SelectPcmOrganizationDto;

/**
 * 
 * @Class Name IPcmOrganizationService
 * @Author yedong
 * @Create In 2015年7月10日
 */
public interface IPcmOrganizationService {
	List<PublishOrganizationDto> findOrganizationByParamFromPcm(Map<String, Object> map)
			throws IllegalAccessException, InvocationTargetException;

	Integer getCountByParam(Map<String, Object> map);

	/**
	 * 分页查询组织机构
	 * 
	 * @Methods Name selectPageOrganization
	 * @Create In 2015年7月28日 By wuxiong
	 * @param pricedto
	 * @param pageprice
	 * @return Page<HashMap<String,Object>>
	 */
	public Page<HashMap<String, Object>> selectPageOrganization(PublishOrganizationDto dto,
			Page<PublishOrganizationDto> pageorg);

	public List<PcmOrganization> selectListByParamOrg(PcmOrganizationDto dto);

	public Integer getCountByParamOrg(PcmOrganizationDto dto);

	public int deleletOrganization(PublishOrganizationDto dto);

	/**
	 * 分页查找
	 * 
	 * @Methods Name findPageOrganization
	 * @Create In 2015-8-18 By wangx
	 * @param selectOrganizationDto
	 * @return Page<PcmOrganizationResultDto>
	 */
	Page<PcmOrganizationResultDto> findPageOrganization(
			SelectPcmOrganizationDto selectOrganizationDto);

	/**
	 * 查询所有的组织机构信息
	 * 
	 * @Methods Name findListOrganization
	 * @Create In 2015-8-20 By wangxuan
	 * @param selectOrganizationDto
	 * @return List<PcmOrganizationResultDto>
	 */
	List<PcmOrganizationResultDto> findListOrganization(
			SelectPcmOrganizationDto selectOrganizationDto);

	/**
	 * 根据门店sid查询所属大区下的所有门店名称
	 * 
	 * @Methods Name findPcmGetShoppeDtoByShopSid
	 * @Create In 2015-8-25 By niuzhifan
	 * @param selectShoppeDto
	 * @return
	 */
	List<Map<String, Object>> findPcmGetShoppeDtoByShopSid(PublishOrganizationDto dto);

	List<Map<String, Object>> findListShop();

	/**
	 * 移动工作台调用主数据获取组织机构信息
	 * 
	 * @create In 2015-8-31 nzf
	 * @return List<Map<String,Object>>
	 */
	List<PcmOrganizationPAD> selectPcmOrganizationByTypeAndCode(PublishOrganizationDto dto);

	List<HashMap<String, Object>> pushOrgData(Map<String, Object> para);

	boolean isExistence(Map<String, Object> para);

	Map<String, Object> saveOrUpdateOrg(PcmOrgDto dto);

	List<PcmOrgPartInfoDto> findListOrgPartInfo(SelectPcmOrganizationDto dto);
}
