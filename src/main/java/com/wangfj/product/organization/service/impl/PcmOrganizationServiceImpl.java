package com.wangfj.product.organization.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wangfj.product.organization.domain.entity.PcmStoreInfo;
import com.wangfj.product.organization.persistence.PcmStoreInfoMapper;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangfj.core.constants.ComErrorCodeConstants;
import com.wangfj.core.constants.ComErrorCodeConstants.ErrorCode;
import com.wangfj.core.framework.base.page.Page;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.core.utils.DateUtil;
import com.wangfj.core.utils.StringUtils;
import com.wangfj.product.organization.domain.entity.PcmOrganization;
import com.wangfj.product.organization.domain.vo.PcmOrgDto;
import com.wangfj.product.organization.domain.vo.PcmOrgPartInfoDto;
import com.wangfj.product.organization.domain.vo.PcmOrganizationDto;
import com.wangfj.product.organization.domain.vo.PcmOrganizationPAD;
import com.wangfj.product.organization.domain.vo.PcmOrganizationResultDto;
import com.wangfj.product.organization.domain.vo.PublishOrganizationDto;
import com.wangfj.product.organization.domain.vo.SelectPcmOrganizationDto;
import com.wangfj.product.organization.persistence.PcmOrganizationMapper;
import com.wangfj.product.organization.service.intf.IPcmOrganizationService;
import com.wangfj.util.Constants;

/**
 * @Class Name PcmOrganizationServiceImpl
 * @Author yedong
 * @Create In 2015年7月9日
 */
@Service
public class PcmOrganizationServiceImpl implements IPcmOrganizationService {

    @Autowired
    private PcmOrganizationMapper pcmOrganizationMapper;

    @Autowired
    private PcmStoreInfoMapper storeInfoMapper;

    private static final Logger logger = LoggerFactory.getLogger(PcmOrganizationServiceImpl.class);

    /**
     * @return List<PublishOrganizationDto>
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @Methods Name publishOrganizationFromPCM
     * @Create In 2015年7月13日 By yedong
     */
    @Override
    public List<PublishOrganizationDto> findOrganizationByParamFromPcm(Map<String, Object> map)
            throws IllegalAccessException, InvocationTargetException {
        logger.info("start publishOrganizationFromPCM()");
        List<HashMap<String, Object>> publishOrganization = pcmOrganizationMapper
                .findOrganizationByParamFromPcm(map);

        List<PublishOrganizationDto> list = new ArrayList<PublishOrganizationDto>();
        for (int i = Constants.PUBLIC_0; i < publishOrganization.size(); i++) {
            PublishOrganizationDto dto = new PublishOrganizationDto();
            BeanUtils.copyProperties(dto, publishOrganization.get(i));
            dto.setStoreCode((String) publishOrganization.get(i).get("code"));
            list.add(dto);
        }

        for (PublishOrganizationDto publishOrganizationDto : list) {
            /* 门店类型 */
            if (publishOrganizationDto.getStoreType() == Integer
                    .toString(Constants.PCMORGANIZATION_STORE_TYPE_DS)) {
                publishOrganizationDto.setStoreType(Constants.PCMORGANIZATION_STORE_TYPE_DS_STR);
            } else if (publishOrganizationDto.getStoreType() == Integer
                    .toString(Constants.PCMORGANIZATION_STORE_TYPE_BJ)) {
                publishOrganizationDto.setStoreType(Constants.PCMORGANIZATION_STORE_TYPE_BJ_STR);
            } else if (publishOrganizationDto.getStoreType() == Integer
                    .toString(Constants.PCMORGANIZATION_STORE_TYPE_MD)) {
                publishOrganizationDto.setStoreType(Constants.PCMORGANIZATION_STORE_TYPE_MD_STR);
            }
            // else if (publishOrganizationDto.getStoreType() == Integer
            // .toString(Constants.PCMORGANIZATION_STORE_TYPE_JH)) {
            // publishOrganizationDto.setStoreType(Constants.PCMORGANIZATION_STORE_TYPE_JH_STR);
            // } else if (publishOrganizationDto.getStoreType() == Integer
            // .toString(Constants.PCMORGANIZATION_STORE_TYPE_WL)) {
            // publishOrganizationDto.setStoreType(Constants.PCMORGANIZATION_STORE_TYPE_WL_STR);
            // }
            /* 级别类型 */
            if (publishOrganizationDto.getType() == Integer.toString(Constants.PUBLIC_0)) {
                publishOrganizationDto.setType(Constants.PCMORGANIZATION_TYPE_GROUP);
            } else if (publishOrganizationDto.getType() == Integer.toString(Constants.PUBLIC_1)) {
                publishOrganizationDto.setType(Constants.PCMORGANIZATION_TYPE_REGION);
            } else if (publishOrganizationDto.getType() == Integer.toString(Constants.PUBLIC_2)) {
                publishOrganizationDto.setType(Constants.PCMORGANIZATION_TYPE_CITY);
            } else if (publishOrganizationDto.getType() == Integer.toString(Constants.PUBLIC_3)) {
                publishOrganizationDto.setType(Constants.PCMORGANIZATION_TYPE_STORE);
            }

        }
        logger.info("end publishOrganizationFromPCM(),return :" + list.toString());
        return list;
    }

    /**
     * 分页查找
     *
     * @param selectOrganizationDto
     * @return Page<PcmOrganizationResultDto>
     * @Methods Name findPageOrganization
     * @Create In 2015-8-18 By wangx
     */
    @Override
    public Page<PcmOrganizationResultDto> findPageOrganization(
            SelectPcmOrganizationDto selectOrganizationDto) {

        logger.info("start findPageOrganization()");

        Page<PcmOrganizationResultDto> page = new Page<PcmOrganizationResultDto>();

        Integer currentPage = selectOrganizationDto.getCurrentPage();
        Integer pageSize = selectOrganizationDto.getPageSize();
        if (currentPage != null) {
            page.setCurrentPage(currentPage);
        } else {
            page.setCurrentPage(Constants.PUBLIC_1);
        }

        if (pageSize != null) {
            page.setPageSize(pageSize);
        } else {
            page.setPageSize(Constants.PUBLIC_10);
        }

        // 查询总数量
        Integer count = pcmOrganizationMapper.getPageCountByPara(selectOrganizationDto);
        page.setCount(count);

        // 分页查询
        selectOrganizationDto.setStart(page.getStart());
        selectOrganizationDto.setLimit(page.getLimit());
        List<PcmOrganizationResultDto> orgList = pcmOrganizationMapper
                .selectPageByPara(selectOrganizationDto);

        if (orgList != null && !orgList.isEmpty()) {

            for (int i = 0; i < orgList.size(); i++) {

                PcmOrganizationResultDto resultDto = orgList.get(i);
                // 不同参数的封装
                Date updateTimes = resultDto.getUpdateTimes();
                Date createTimes = resultDto.getCreateTimes();

                resultDto.setUpdateTimeStr(DateUtil.formatToStr(updateTimes, "yyyy-MM-dd"));
                resultDto.setCreateTimeStr(DateUtil.formatToStr(createTimes, "yyyy-MM-dd"));
            }
        }

        page.setList(orgList);

        return page;

    }

    /**
     * 查询所有的组织机构信息
     *
     * @param selectOrganizationDto
     * @return List<PcmOrganizationResultDto>
     * @Methods Name findListOrganization
     * @Create In 2015-8-20 By wangxuan
     */
    @Override
    public List<PcmOrganizationResultDto> findListOrganization(
            SelectPcmOrganizationDto selectOrganizationDto) {

        logger.info("start findListOrganization()");

        selectOrganizationDto.setStart(null);
        selectOrganizationDto.setLimit(null);
        List<PcmOrganizationResultDto> orgList = pcmOrganizationMapper
                .selectPageByPara(selectOrganizationDto);

        if (orgList != null && !orgList.isEmpty()) {

            for (int i = 0; i < orgList.size(); i++) {

                PcmOrganizationResultDto resultDto = orgList.get(i);
                Date updateTimes = resultDto.getUpdateTimes();
                Date createTimes = resultDto.getCreateTimes();

                resultDto.setUpdateTimeStr(DateUtil.formatToStr(updateTimes, "yyyy-MM-dd"));
                resultDto.setCreateTimeStr(DateUtil.formatToStr(createTimes, "yyyy-MM-dd"));
            }

        }

        return orgList;

    }

    /**
     * 查询所有组织机构的部分信息
     *
     * @param dto
     * @return List<PcmOrgPartInfoDto>
     * @Methods Name findListOrgPartInfo
     * @Create In 2015-8-20 By wangxuan
     */
    @Override
    public List<PcmOrgPartInfoDto> findListOrgPartInfo(SelectPcmOrganizationDto dto) {

        logger.info("start findListOrganization()");

        dto.setStart(null);
        dto.setLimit(null);
        List<PcmOrgPartInfoDto> orgList = pcmOrganizationMapper.selectPagePartInfoByPara(dto);

        return orgList;

    }

    @Override
    public List<PcmOrganization> selectListByParamOrg(PcmOrganizationDto dto) {
        logger.info("start selectPageOrganization()");
        List<HashMap<String, Object>> list = pcmOrganizationMapper.selectListByParamOrg(dto);
        List<PcmOrganization> dtoList = new ArrayList<PcmOrganization>();
        for (int i = 0; i < list.size(); i++) {
            PcmOrganization dto2 = new PcmOrganization();
            org.springframework.beans.BeanUtils.copyProperties(list.get(i), dto2);
            dtoList.add(dto2);
        }
        // for (PcmOrganization pcmOrganization : dtoList) {
        // if (pcmOrganization.getUpdateTime() != null) {
        // String updateTime =
        // DateUtil.getStrFromDate(pcmOrganization.getUpdateTime());
        // pcmOrganization.setUpdateTime(DateUtil.formatToDate(updateTime,
        // "yyyy-MM-dd HH:mm:ss"));
        // }
        // if (pcmOrganization.getCreateTime() != null) {
        // String createTime =
        // DateUtil.getStrFromDate(pcmOrganization.getCreateTime());
        // pcmOrganization.setCreateTime(DateUtil.formatToDate(createTime,
        // "yyyy-MM-dd HH:mm:ss"));
        // }
        // }
        if (!list.isEmpty()) {
            return dtoList;
        } else {
            logger.error("查询失败");
            throw new BleException(ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getErrorCode(),
                    ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getMemo());
        }

    }

    @Override
    public Integer getCountByParamOrg(PcmOrganizationDto dto) {
        Integer count = pcmOrganizationMapper.getCountByParamOrg(dto);
        return count;
    }

    @Override
    public Integer getCountByParam(Map<String, Object> map) {
        Integer count = pcmOrganizationMapper.getCountByParam(map);
        return count;
    }

    /**
     * 下发查询
     *
     * @param para
     * @return
     */
    @Override
    public List<HashMap<String, Object>> pushOrgData(Map<String, Object> para) {

        logger.info("start pushOrgData(),para:" + para.toString());

        String actionCode = para.get("actionCode") + "";
        String sid = para.get("sid") + "";
        String organizationType = para.get("organizationType") + "";

        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtils.isNotEmpty(sid)) {
            paramMap.put("sid", Long.parseLong(sid.trim()));
        }
        if (StringUtils.isNotEmpty(organizationType)) {
            paramMap.put("organizationType", organizationType);
        }
        if (StringUtils.isNotEmpty(actionCode)) {
            if (actionCode.toUpperCase().equals(Constants.A)
                    || actionCode.toUpperCase().equals(Constants.U)) {
                paramMap.put("organizationStatus", Constants.PUBLIC_0);
            }
        }

        List<HashMap<String, Object>> list = pcmOrganizationMapper.pushOrgData(paramMap);
        if (list != null && !list.isEmpty()) {
            for (HashMap<String, Object> hashMap : list) {
                hashMap.put("actionCode", actionCode);
                hashMap.put("actionDate", DateUtil.formatToStr(new Date(), "yyyyMMdd.HHmmssZ"));

                String type = hashMap.get("type") + "";
                if (StringUtils.isNotEmpty(type)) {
                    if (type.trim().equals((Constants.PCMORGANIZATION_TYPE_GROUP_INT + "").trim())) {
                        hashMap.put("type", Constants.PCMORGANIZATION_TYPE_GROUP);
                    }
                    if (type.trim().equals((Constants.PCMORGANIZATION_TYPE_REGION_INT + "").trim())) {
                        hashMap.put("type", Constants.PCMORGANIZATION_TYPE_REGION);
                    }
                    if (type.trim().equals((Constants.PCMORGANIZATION_TYPE_CITY_INT + "").trim())) {
                        hashMap.put("type", Constants.PCMORGANIZATION_TYPE_CITY);
                    }
                    if (type.trim().equals((Constants.PCMORGANIZATION_TYPE_STORE_INT + "").trim())) {
                        hashMap.put("type", Constants.PCMORGANIZATION_TYPE_STORE);
                    }
                }

                String storeType = hashMap.get("storeType") + "";
                if (StringUtils.isNotEmpty(storeType)) {
                    if (storeType.trim().equals(
                            (Constants.PCMORGANIZATION_STORE_TYPE_BJ + "").trim())) {
                        hashMap.put("storeType", Constants.PUBLIC_2);
                    }
                    if (storeType.trim().equals(
                            (Constants.PCMORGANIZATION_STORE_TYPE_MD + "").trim())) {
                        hashMap.put("storeType", Constants.PUBLIC_3);
                    }
                    if (storeType.trim().equals(
                            (Constants.PCMORGANIZATION_STORE_TYPE_DS + "").trim())) {
                        hashMap.put("storeType", Constants.PUBLIC_1);
                    }

                }

            }
        }
        logger.info("end pushOrgData(),return:" + list.toString());
        return list;
    }

    /*
     * (non-Javadoc) 根据条件查询信息
     *
     * @see
     * com.wangfj.product.organization.service.intf.IPcmOrganizationService#
     * selectPageOrganization
     * (com.wangfj.product.organization.domain.vo.PublishOrganizationDto,
     * com.wangfj.core.framework.base.page.Page)
     */
    @Override
    public Page<HashMap<String, Object>> selectPageOrganization(PublishOrganizationDto dto,
                                                                Page<PublishOrganizationDto> pageorg) {
        logger.info("start selectPageOrganization()");
        Map<String, Object> map = new HashMap<String, Object>();
        /* 封装条件查询 */
        map.put("code", dto.getCode());
        map.put("name", dto.getName());
        /* 机构类型 */
        if (Constants.PCMORGANIZATION_TYPE_GROUP.equals(dto.getType())) {
            map.put("type", Constants.PUBLIC_0);
        } else if (Constants.PCMORGANIZATION_TYPE_REGION.equals(dto.getType())) {
            map.put("type", Constants.PUBLIC_1);
        } else if (Constants.PCMORGANIZATION_TYPE_CITY.equals(dto.getType())) {
            map.put("type", Constants.PUBLIC_2);
        } else if (Constants.PCMORGANIZATION_TYPE_STORE.equals(dto.getType())) {
            map.put("type", Constants.PUBLIC_3);
        }
        map.put("superCode", dto.getSuperCode());
        /* 门店类型 */
        if (Constants.PCMORGANIZATION_STORE_TYPE_DS_STR.equals(dto.getStoreType())) {
            map.put("storeType", Constants.PCMORGANIZATION_STORE_TYPE_DS);
        } else if (Constants.PCMORGANIZATION_STORE_TYPE_BJ_STR.equals(dto.getStoreType())) {
            map.put("storeType", Constants.PCMORGANIZATION_STORE_TYPE_BJ);
        } else if (Constants.PCMORGANIZATION_STORE_TYPE_MD_STR.equals(dto.getStoreType())) {
            map.put("storeType", Constants.PCMORGANIZATION_STORE_TYPE_MD);
        }
        // else if
        // (Constants.PCMORGANIZATION_STORE_TYPE_JH_STR.equals(dto.getStoreType()))
        // {
        // map.put("storeType", Constants.PCMORGANIZATION_STORE_TYPE_JH);
        // } else if
        // (Constants.PCMORGANIZATION_STORE_TYPE_WL_STR.equals(dto.getStoreType()))
        // {
        // map.put("storeType", Constants.PCMORGANIZATION_STORE_TYPE_WL);
        // }
        map.put("shippingPoint", dto.getShippingPoint());
        map.put("storeCode", dto.getStoreCode());
        map.put("areaCode", dto.getAreaCode());
        Integer count = pcmOrganizationMapper.getCountByParam(map);
        pageorg.setCount(count);
        map.put("start", pageorg.getStart());
        map.put("limit", pageorg.getLimit());
        List<HashMap<String, Object>> list = pcmOrganizationMapper
                .findOrganizationByParamFromPcm(map);
        Page<HashMap<String, Object>> page = new Page<HashMap<String, Object>>();
        if (!list.isEmpty()) {
            page.setList(list);
            page.setCurrentPage(pageorg.getCurrentPage());
            page.setPageSize(pageorg.getPageSize());
            page.setCount(count);
            return page;
        } else {
            logger.error("查询失败");
            throw new BleException(ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getErrorCode(),
                    ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getMemo());
        }

    }

    /**
     * 判重
     */
    @Override
    public boolean isExistence(Map<String, Object> para) {

        logger.info("start isExistence(),param:" + para.toString());

        boolean flag = false;

        Map<String, Object> paramMap = null;
        String groupSid = para.get("groupSid") + "";

        String organizationCode = para.get("organizationCode") + "";
        if (StringUtils.isNotEmpty(organizationCode)) {
            paramMap = new HashMap<String, Object>();
            paramMap.clear();
            paramMap.put("groupSid", Long.parseLong(groupSid.trim()));
            paramMap.put("organizationCode", organizationCode.trim());

            List<PcmOrganization> codeList = pcmOrganizationMapper.selectListByParam(paramMap);
            if (codeList != null && codeList.size() > 0) {
                flag = true;
                paramMap.clear();
                paramMap.put("sid", codeList.get(0).getParentSid());
                List<PcmOrganization> list = pcmOrganizationMapper.selectListByParam(paramMap);
                String name = "";
                String message = "";
                if (list != null && list.size() > 0) {
                    name = list.get(0).getOrganizationName();
                }
                if (StringUtils.isNotEmpty(name)) {
                    message = "该编码在" + name + "下已存在";
                } else {
                    message = "该编码已存在";
                }
                throw new BleException(ErrorCode.ORGANIZATION_CODE_EXISTENCE.getErrorCode(),
                        message);
            }
        }

        String organizationName = para.get("organizationName") + "";
        if (StringUtils.isNotEmpty(groupSid)) {

            if (StringUtils.isNotEmpty(organizationName)) {
                paramMap = new HashMap<String, Object>();
                paramMap.clear();
                paramMap.put("groupSid", Long.parseLong(groupSid.trim()));
                paramMap.put("organizationName", organizationName.trim());

                List<PcmOrganization> nameList = pcmOrganizationMapper.selectListByParam(paramMap);
                if (nameList != null && nameList.size() > 0) {
                    flag = true;
                    paramMap.clear();
                    paramMap.put("sid", nameList.get(0).getParentSid());
                    List<PcmOrganization> list = pcmOrganizationMapper.selectListByParam(paramMap);
                    String name = "";
                    String message = "";
                    if (list != null && list.size() > 0) {
                        name = list.get(0).getOrganizationName();
                    }
                    if (StringUtils.isNotEmpty(name)) {
                        message = "该名称在" + name + "下已存在";
                    } else {
                        message = "该名称已存在";
                    }
                    throw new BleException(ErrorCode.ORGANIZATION_NAME_EXISTENCE.getErrorCode(),
                            message);
                }
            }
        }

        logger.info("end isExistence(),return:" + flag);

        return flag;

    }

    /**
     * 添加、修改组织机构
     */
    @Override
    @Transactional
    public Map<String, Object> saveOrUpdateOrg(PcmOrgDto dto) {

        logger.info("start saveOrUpdateOrg(),param:" + dto.toString());

        Map<String, Object> resultMap = new HashMap<String, Object>();
        Integer result = Constants.PUBLIC_0;

        String actionCode = dto.getActionCode();
        Integer organizationType = dto.getOrganizationType();

        if (StringUtils.isNotEmpty(actionCode)) {

            Map<String, Object> paramMap = null;
            PcmOrganization organization = null;
            PcmStoreInfo storeInfo = null;
            if (Constants.A.equals(actionCode.trim().toUpperCase())) {
                paramMap = new HashMap<String, Object>();
                paramMap.clear();

                paramMap.put("organizationCode", dto.getOrganizationCode());
                paramMap.put("organizationName", dto.getOrganizationName());
                paramMap.put("groupSid", dto.getGroupSid());
                if (!isExistence(paramMap)) {
                    organization = new PcmOrganization();
                    org.springframework.beans.BeanUtils.copyProperties(dto, organization);
                    organization.setOrganizationStatus(Constants.PUBLIC_0);
                    organization.setCreateTime(new Date());
                    result = pcmOrganizationMapper.insertSelective(organization);//组织机构插入

                    if (Constants.PCMORGANIZATION_TYPE_STORE_INT == organizationType) {//判断是否为门店
                        storeInfo = new PcmStoreInfo();
                        org.springframework.beans.BeanUtils.copyProperties(dto, storeInfo);
                        storeInfo.setStoreCode(dto.getOrganizationCode());
                        storeInfoMapper.insertSelective(storeInfo);//门店信息插入
                    }
                    if (result == 1) {
                        // 返回下发的sid
                        resultMap.put("sid", organization.getSid());
                    }
                }

            }

            if (Constants.U.equals(actionCode.trim().toUpperCase())) {

                Long sid = dto.getSid();
                if (sid != null) {
                    paramMap = new HashMap<String, Object>();
                    paramMap.clear();

                    paramMap.put("sid", sid);
                    List<PcmOrganization> list = pcmOrganizationMapper.selectListByParam(paramMap);

                    if (list != null && list.size() == 1) {

                        organization = new PcmOrganization();
                        org.springframework.beans.BeanUtils.copyProperties(dto, organization);
                        organization.setUpdateTime(new Date());

                        String parentSid = list.get(0).getParentSid();
                        String organizationName = list.get(0).getOrganizationName();
                        paramMap.clear();
                        paramMap.put("groupSid", dto.getGroupSid());
                        if (parentSid.equals(dto.getParentSid())) {
                            if (!organizationName.equals(dto.getOrganizationName())) {
                                paramMap.put("organizationName", dto.getOrganizationName());
                            }
                        } else {
                            paramMap.put("organizationName", dto.getOrganizationName());
                            paramMap.put("organizationCode", dto.getOrganizationCode());
                        }

                        if (!isExistence(paramMap)) {
                            result = pcmOrganizationMapper.updateByPrimaryKeySelective(organization);
                        }

                        if (Constants.PCMORGANIZATION_TYPE_STORE_INT == organizationType) {//判断是否为门店
                            storeInfo = new PcmStoreInfo();
                            org.springframework.beans.BeanUtils.copyProperties(dto, storeInfo);
                            storeInfo.setStoreCode(dto.getOrganizationCode());

                            paramMap.clear();
                            paramMap.put("groupSid", dto.getGroupSid());
                            paramMap.put("storeCode", dto.getOrganizationCode());
                            List<PcmStoreInfo> storeInfoList = storeInfoMapper.selectListByParam(paramMap);
                            if (storeInfoList != null && storeInfoList.size() == 1) {
                                storeInfo.setSid(storeInfoList.get(0).getSid());
                                storeInfoMapper.updateByPrimaryKeySelective(storeInfo);//门店信息修改
                            }
                            if (storeInfoList == null || storeInfoList.size() == 0) {
                                storeInfoMapper.insertSelective(storeInfo);//门店信息插入
                            }
                        }

                        if (result == 1) {
                            // 返回下发的sid
                            resultMap.put("sid", organization.getSid());
                        }
                    }
                }
            }
        }

        resultMap.put("success", result);
        logger.info("end saveOrUpdateOrg(),return:" + resultMap.toString());

        return resultMap;

    }

    ;

    /**
     * 业务删除
     */
    @Override
    public int deleletOrganization(PublishOrganizationDto dto) {
        PcmOrganizationDto pcm = new PcmOrganizationDto();
        Integer count = 0;
        Integer count1 = 0;
        Integer deleteCount = 0;
        // 判重
        if (dto.getOrganizationName() == null && dto.getOrganizationCode() == null) {
            logger.error("数据不存在");
            throw new BleException(ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getErrorCode(),
                    ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getMemo());
        } else {
            if (dto.getOrganizationName() != null) {
                pcm.setOrganizationName(dto.getOrganizationName());
                count = pcmOrganizationMapper.getCountByParamOrg(pcm);
                pcm.setOrganizationName(null);
            }
            if (dto.getOrganizationCode() != null) {
                pcm.setOrganizationCode(dto.getOrganizationCode());
                count1 = pcmOrganizationMapper.getCountByParamOrg(pcm);
            }
            if (count == Constants.PUBLIC_0 && count1 == Constants.PUBLIC_0) {// 库里不存在该数据
                logger.error("数据不存在");
                throw new BleException(
                        ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getErrorCode(),
                        ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getMemo());
            } else {
                if (pcm.getOrganizationStatus() == 1) {
                    logger.error("数据已属于禁用状态，无法操作");
                    throw new BleException(
                            ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getErrorCode(),
                            ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getMemo());
                }
                pcm.setSid(dto.getSid());
                pcm.setOrganizationStatus(1);
                deleteCount = pcmOrganizationMapper.updateByPcmOrganizationDto(pcm);
                logger.info("删除成功");
            }
        }
        return deleteCount;
    }

    /**
     * 查到的门店信息
     */
    @Override
    public List<Map<String, Object>> findPcmGetShoppeDtoByShopSid(PublishOrganizationDto dto) {
        logger.info("开始查询");
        PcmOrganizationDto pcm = new PcmOrganizationDto();
        pcm.setSid(dto.getSid());
        pcm.setOrganizationCode(dto.getOrganizationCode());
        pcm.setOrganizationName(dto.getOrganizationName());
        PcmOrganization pcmOrg = pcmOrganizationMapper.get(dto.getSid());
        if (pcmOrg == null) {
            throw new BleException(ComErrorCodeConstants.ErrorCode.SHOP_NULL.getErrorCode(),
                    ComErrorCodeConstants.ErrorCode.SHOP_NULL.getMemo());
        } else {
            List<Map<String, Object>> listPcmOrganization = pcmOrganizationMapper
                    .selectByShopSid(pcm);
            if (listPcmOrganization.size() == 0) {
                throw new BleException(
                        ComErrorCodeConstants.ErrorCode.ORGANIZATON_RELATED_IS_ERROR.getErrorCode(),
                        ComErrorCodeConstants.ErrorCode.ORGANIZATON_RELATED_IS_ERROR.getMemo());
            }
            return listPcmOrganization;
        }

    }

    /**
     * 查询所有的门店（无条件）(线下搜索)
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> findListShop() {

        List<Map<String, Object>> list = pcmOrganizationMapper.selectShopPageByPara();
        return list;
    }

    /**
     * 搜索线上与线下查询门店合并service
     *
     * @param para
     * @return
     */
    @Override
    public List<Map<String, Object>> findShopListCommon(Map<String, Object> para) {
        logger.debug("start findShopListCommon(),param:" + para.toString());
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("organizationType", 3);
        paramMap.put("organizationStatus", 0);
        List<PcmOrganization> organizationList = pcmOrganizationMapper.selectListByParam(paramMap);

        List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
        if (organizationList != null && organizationList.size() > 0) {
            String flag = para.get("flag") + "";
            if ("searchOnline".equals(flag)) {//线上搜索返回参数转换
                for (PcmOrganization tempOrg : organizationList) {
                    Map<String, Object> returnMap = new HashMap<String, Object>();
                    returnMap.put("shopId", tempOrg.getOrganizationCode());
                    returnMap.put("shopName", tempOrg.getOrganizationName());
                    returnList.add(returnMap);
                }
            }

            if ("searchOffline".equals(flag)) {//线下搜索返回参数转换
                for (PcmOrganization tempOrg : organizationList) {
                    Map<String, Object> returnMap = new HashMap<String, Object>();
                    returnMap.put("storeCode", tempOrg.getOrganizationCode());
                    returnList.add(returnMap);
                }
            }
        }
        logger.debug("end findShopListCommon(),return:" + returnList.toString());
        return returnList;
    }

    /**
     * 移动工作台调用主数据获取组织机构信息 organizationType(不为空)
     */
    @Override
    public List<PcmOrganizationPAD> selectPcmOrganizationByTypeAndCode(PublishOrganizationDto dto) {

        logger.info("start selectPcmOrganizationByTypeAndCode(),param:" + dto.toString());

        List<PcmOrganizationPAD> resultList = null;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("organizationType", dto.getType());
        List<PcmOrganization> list = pcmOrganizationMapper.selectListByParam(map);
        if (list.size() != Constants.PUBLIC_0) {
            map.put("organizationType", null);
            map.put("type", dto.getType());
            map.put("code", dto.getCode());
            resultList = pcmOrganizationMapper.findOrganizationByParamFromPAD(map);
            if (resultList.size() > 0) {
                logger.info("查询完毕");
            } else {
                throw new BleException(
                        ComErrorCodeConstants.ErrorCode.ORGANIZATION_IS_EXIST.getErrorCode(),
                        ComErrorCodeConstants.ErrorCode.ORGANIZATION_IS_EXIST.getMemo());
            }
        } else {
            throw new BleException(
                    ComErrorCodeConstants.ErrorCode.ORGANIZATION_IS_EXIST.getErrorCode(),
                    ComErrorCodeConstants.ErrorCode.ORGANIZATION_IS_EXIST.getMemo());

        }

        logger.info("end selectPcmOrganizationByTypeAndCode(),return:" + resultList.toString());
        return resultList;
    }

}
