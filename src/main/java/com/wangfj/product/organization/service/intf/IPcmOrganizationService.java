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
     * @param pricedto
     * @param pageprice
     * @return Page<HashMap<String,Object>>
     * @Methods Name selectPageOrganization
     * @Create In 2015年7月28日 By wuxiong
     */
    public Page<HashMap<String, Object>> selectPageOrganization(PublishOrganizationDto dto,
                                                                Page<PublishOrganizationDto> pageorg);

    public List<PcmOrganization> selectListByParamOrg(PcmOrganizationDto dto);

    public Integer getCountByParamOrg(PcmOrganizationDto dto);

    public int deleletOrganization(PublishOrganizationDto dto);

    /**
     * 分页查找
     *
     * @param selectOrganizationDto
     * @return Page<PcmOrganizationResultDto>
     * @Methods Name findPageOrganization
     * @Create In 2015-8-18 By wangx
     */
    Page<PcmOrganizationResultDto> findPageOrganization(
            SelectPcmOrganizationDto selectOrganizationDto);

    /**
     * 查询所有的组织机构信息
     *
     * @param selectOrganizationDto
     * @return List<PcmOrganizationResultDto>
     * @Methods Name findListOrganization
     * @Create In 2015-8-20 By wangxuan
     */
    List<PcmOrganizationResultDto> findListOrganization(
            SelectPcmOrganizationDto selectOrganizationDto);

    /**
     * 根据门店sid查询所属大区下的所有门店名称
     *
     * @param selectShoppeDto
     * @return
     * @Methods Name findPcmGetShoppeDtoByShopSid
     * @Create In 2015-8-25 By niuzhifan
     */
    List<Map<String, Object>> findPcmGetShoppeDtoByShopSid(PublishOrganizationDto dto);

    List<Map<String, Object>> findListShop();

    /**
     * 搜索线上与线下查询门店合并service
     *
     * @param para
     * @return
     */
    List<Map<String, Object>> findShopListCommon(Map<String, Object> para);

    /**
     * 移动工作台调用主数据获取组织机构信息
     *
     * @return List<Map<String,Object>>
     * @create In 2015-8-31 nzf
     */
    List<PcmOrganizationPAD> selectPcmOrganizationByTypeAndCode(PublishOrganizationDto dto);

    List<HashMap<String, Object>> pushOrgData(Map<String, Object> para);

    boolean isExistence(Map<String, Object> para);

    Map<String, Object> saveOrUpdateOrg(PcmOrgDto dto);

    List<PcmOrgPartInfoDto> findListOrgPartInfo(SelectPcmOrganizationDto dto);

    /**
     * 校验门店信息
     *
     * @param dto
     */
    void checkStoreInfo(PcmOrgDto dto);
}
