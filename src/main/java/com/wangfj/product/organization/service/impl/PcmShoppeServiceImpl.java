package com.wangfj.product.organization.service.impl;

import com.wangfj.core.constants.ComErrorCodeConstants;
import com.wangfj.core.constants.ComErrorCodeConstants.ErrorCode;
import com.wangfj.core.framework.base.page.Page;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.core.utils.DateUtil;
import com.wangfj.core.utils.StringUtils;
import com.wangfj.product.brand.persistence.PcmShoppeBrandRelationMapper;
import com.wangfj.product.organization.domain.entity.PcmChannelSaleConfig;
import com.wangfj.product.organization.domain.entity.PcmFloor;
import com.wangfj.product.organization.domain.entity.PcmOrganization;
import com.wangfj.product.organization.domain.entity.PcmShoppe;
import com.wangfj.product.organization.domain.vo.*;
import com.wangfj.product.organization.persistence.PcmChannelSaleConfigMapper;
import com.wangfj.product.organization.persistence.PcmFloorMapper;
import com.wangfj.product.organization.persistence.PcmOrganizationMapper;
import com.wangfj.product.organization.persistence.PcmShoppeMapper;
import com.wangfj.product.organization.service.intf.IPcmShoppeService;
import com.wangfj.product.supplier.domain.entity.PcmSupplyInfo;
import com.wangfj.product.supplier.domain.entity.PcmSupplyShoppeRelation;
import com.wangfj.product.supplier.persistence.PcmSupplyInfoMapper;
import com.wangfj.product.supplier.persistence.PcmSupplyShoppeRelationMapper;
import com.wangfj.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 专柜管理 Service
 *
 * @Class Name PcmShoppeServiceImpl
 * @Author yedong
 * @Create In 2015年7月14日
 */
@Service
public class PcmShoppeServiceImpl implements IPcmShoppeService {

    @Autowired
    private PcmShoppeMapper pcmShoppeMapper;

    @Autowired
    private PcmOrganizationMapper pcmOrganizationMapper;

    @Autowired
    private PcmChannelSaleConfigMapper channelSaleConfigMapper;

    @Autowired
    private PcmFloorMapper pcmFloorMapper;

    @Autowired
    private PcmSupplyInfoMapper supplyInfoMapper;

    @Autowired
    private PcmSupplyShoppeRelationMapper supplyShoppeRelationMapper;

    @Autowired
    private PcmShoppeBrandRelationMapper shoppeBrandRelationMapper;

    private static final Logger logger = LoggerFactory.getLogger(PcmShoppeServiceImpl.class);

    /**
     * 查询下发给电商的数据
     */
    @Override
    public List<Map<String, Object>> pushShoppeToEBusiness(Map<String, Object> para) {

        logger.info("start pushShoppeToEBusiness(),param :" + para.toString());

        String sid = para.get("sid") + "";
        String actionCode = para.get("actionCode") + "";
        String actionPerson = para.get("actionPerson") + "";

        Map<String, Object> paramMap = new HashMap<String, Object>();

        if (StringUtils.isNotEmpty(sid)) {
            paramMap.put("sid", Long.parseLong(sid.trim()));
        }

        List<Map<String, Object>> list = pcmShoppeMapper.pushShoppeToEBusiness(paramMap);

        if (list != null && list.size() > 0) {
            for (Map<String, Object> map : list) {
                map.put("ACTIONCODE", actionCode);
                map.put("ACTIONPERSON", actionPerson);
                map.put("ACTIONDATE", DateUtil.formatToStr(new Date(), "yyyyMMdd.HHmmssZ"));

                // 字段转义
                String businessType = map.get("BUSINESSTYPE") + "";
                if (StringUtils.isNotEmpty(businessType)) {
                    if (businessType.equals(Constants.PUBLIC_0 + "")) {
                        map.put("BUSINESSTYPE", Constants.PCMSHOPPE_SHOPPE_BUSINESSTYPE_0);
                    }
                    if (businessType.equals(Constants.PUBLIC_1 + "")) {
                        map.put("BUSINESSTYPE", Constants.PCMSHOPPE_SHOPPE_BUSINESSTYPE_1);
                    }
                    if (businessType.equals(Constants.PUBLIC_2 + "")) {
                        map.put("BUSINESSTYPE", Constants.PCMSHOPPE_SHOPPE_BUSINESSTYPE_2);
                    }
                }
            }
        }

        logger.info("end pushShoppeToEBusiness(),return :" + list.toString());
        return list;
    }

    /**
     * 查询下发给门店和促销的数据
     */
    @Override
    public List<Map<String, Object>> pushShoppeData(Map<String, Object> para) {

        logger.info("start pushShoppeData(),param :" + para.toString());

        String sid = para.get("sid") + "";
        String actionCode = para.get("actionCode") + "";
        String actionPerson = para.get("actionPerson") + "";

        Map<String, Object> paramMap = new HashMap<String, Object>();

        if (StringUtils.isNotEmpty(sid)) {
            paramMap.put("sid", Long.parseLong(sid.trim()));
        }

        List<Map<String, Object>> list = pcmShoppeMapper.pushShoppeData(paramMap);

        if (list != null && list.size() > 0) {
            for (Map<String, Object> map : list) {
                map.put("actionCode", actionCode);
                map.put("actionPerson", actionPerson);
                map.put("actionDate", DateUtil.formatToStr(new Date(), "yyyyMMdd.HHmmssZ"));

                // 字段转义
                String isNegInventory = map.get("isNegInventory") + "";
                if (Constants.PUBLIC_0 == Integer.parseInt(isNegInventory.trim())) {
                    map.put("isNegInventory", Constants.Y);
                }
                if (Constants.PUBLIC_1 == Integer.parseInt(isNegInventory.trim())) {
                    map.put("isNegInventory", Constants.N);
                }

                String businessType = map.get("businessType") + "";
                if (StringUtils.isNotEmpty(businessType)) {
                    if (businessType.equals(Constants.PUBLIC_0 + "")) {
                        map.put("businessType", Constants.PCMSHOPPE_SHOPPE_BUSINESSTYPE_0);
                    }
                    if (businessType.equals(Constants.PUBLIC_1 + "")) {
                        map.put("businessType", Constants.PCMSHOPPE_SHOPPE_BUSINESSTYPE_1);
                    }
                    if (businessType.equals(Constants.PUBLIC_2 + "")) {
                        map.put("businessType", Constants.PCMSHOPPE_SHOPPE_BUSINESSTYPE_2);
                    }
                }

                String counterInventoryType = map.get("counterInventoryType") + "";
                if (StringUtils.isNotEmpty(counterInventoryType)) {
                    if (counterInventoryType.equals(Constants.PUBLIC_1 + "")) {
                        map.put("counterInventoryType", "01");
                    }
                    if (counterInventoryType.equals(Constants.PUBLIC_2 + "")) {
                        map.put("counterInventoryType", "02");
                    }
                }

                // 字段类型转换
                map.put("counterStatus", map.get("counterStatus") + "");

            }
        }

        logger.info("end pushShoppeData(),return :" + list.toString());
        return list;
    }

    /**
     * MDERP专柜主数据条件查询
     *
     * @return finalList
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @Methods Name findShoppeByParamFromPcm
     * @Create In 2015年7月9日 By yedong
     */
    public List<Map<String, Object>> findShoppeByParamFromPcm(Map<String, Object> map) {
        logger.info("start findShoppeByParamFromPcm(),param :" + map.toString());
        List<Map<String, Object>> pushCounter = pcmShoppeMapper.findShoppeByParamFromPcm(map);
        for (int i = Constants.PUBLIC_0; i < pushCounter.size(); i++) {
            if (pushCounter.get(i).get("negIiveStock") == Constants.PUBLIC_0) {
                pushCounter.get(i).put("negIiveStock", Constants.Y);
            } else if (pushCounter.get(i).get("negIiveStock") == Constants.PUBLIC_1) {
                pushCounter.get(i).put("negIiveStock", Constants.N);
            }
        }
        logger.info("end findShoppeByParamFromPcm(),return :" + pushCounter.toString());
        return pushCounter;
    }

    public Integer getCountByParam(Map<String, Object> paramMap) {
        Integer count = pcmShoppeMapper.getCountByParam(paramMap);
        return count;
    }

    /*
     * (non-Javadoc)通过sid查询专柜信息
     *
     * @see com.wangfj.product.organization.service.intf.IPcmShoppeService#
     * selectShoppeSid(java.lang.Long)
     */
    @Override
    public PcmGetShoppeDto selectShoppeSid(Long sid) {
        logger.info("start selectShoppeSid()");
        PcmGetShoppeDto pcmGetShoppeDto = pcmShoppeMapper.getPcmGetShoppeDtoByShoppeSid(sid);
        logger.info("end selectShoppeSid(),return :" + pcmGetShoppeDto.toString());
        return pcmGetShoppeDto;
    }

    /**
     * 分页查询
     *
     * @param selectShoppeDto
     * @return Page<PcmShoppeResultDto>
     * @Methods Name findPageShoppe
     * @Create In 2015-8-24 By niuzhifan
     */
    @Override
    public Page<PcmShoppeResultDto> findPageShoppe(SelectPcmShoppeDto selectShoppeDto) {

        logger.info("start findPageShoppe(),para:" + selectShoppeDto.toString());

        Page<PcmShoppeResultDto> page = new Page<PcmShoppeResultDto>();
        if (selectShoppeDto.getCurrentPage() != null) {
            page.setCurrentPage(selectShoppeDto.getCurrentPage());
        }
        if (selectShoppeDto.getPageSize() != null) {
            page.setPageSize(selectShoppeDto.getPageSize());
        }

        // 查询总记录数
        Integer count = pcmShoppeMapper.findPageCountByPara(selectShoppeDto);
        page.setCount(count);

        // 分页查询
        selectShoppeDto.setStart(page.getStart());
        selectShoppeDto.setLimit(page.getLimit());
        List<PcmShoppeResultDto> shoppeDtoList = pcmShoppeMapper.findPageByPara(selectShoppeDto);

        page.setList(shoppeDtoList);

        logger.info("end findPageShoppe(),return:" + shoppeDtoList.toString());
        return page;

    }

    /**
     * 查询专柜List
     *
     * @param dto
     * @return List<PcmShoppeResultDto>
     * @Methods Name findListShoppe
     * @Create In 2015-10-16 By wangxuan
     */
    @Override
    public List<PcmShoppeResultDto> findShoppeList(SelectPcmShoppeDto dto) {

        logger.info("start findShoppeList(),param:" + dto.toString());
        List<PcmShoppeResultDto> resultDtoList = pcmShoppeMapper.selectShoppeListByParam(dto);
        logger.info("end findShoppeList(),return:" + resultDtoList.toString());

        return resultDtoList;

    }

    /**
     * 查询专柜
     *
     * @param selectShoppeDto
     * @return List<PcmShoppeResultDto>
     * @Methods Name findListShoppeData
     * @Create In 2015-8-25 By niuzhifan
     */
    @Override
    public List<PcmShoppeResultDto> findListShoppeData(SelectPcmShoppeDto selectShoppeDto) {

        logger.info("start findListShoppeData(),para:" + selectShoppeDto.toString());

        // 封装查询参数,不分页
        selectShoppeDto.setStart(null);
        selectShoppeDto.setLimit(null);

        // 封装门店sid
        String shopCode = selectShoppeDto.getShopCode();
        if (StringUtils.isNotEmpty(shopCode)) {
            Map<String, Object> paramOrg = new HashMap<String, Object>();
            paramOrg.put("organizationCode", shopCode);
            paramOrg.put("organizationType", Constants.PCMORGANIZATION_TYPE_STORE_INT);
            List<PcmOrganization> orgList = pcmOrganizationMapper.selectListByParam(paramOrg);

            if (orgList != null && orgList.size() == 1) {
                selectShoppeDto.setShopSid(orgList.get(0).getSid());
            }
        }

        List<PcmShoppeResultDto> shoppeDtoList = pcmShoppeMapper.selectPageByPara(selectShoppeDto);

        // 封装返回参数
        if (!shoppeDtoList.isEmpty()) {

            for (int i = 0; i < shoppeDtoList.size(); i++) {
                PcmShoppeResultDto shoppeDto = shoppeDtoList.get(i);

                String createTimeStr = DateUtil.formatToStr(shoppeDtoList.get(i).getCreateTimes(),
                        "yyyy-MM-dd");
                shoppeDto.setCreateTimeStr(createTimeStr);

                String updateTimeStr = DateUtil.formatToStr(shoppeDtoList.get(i).getUpdateTimes(),
                        "yyyy-MM-dd");
                shoppeDto.setUpdateTimeStr(updateTimeStr);
            }
        }

        logger.info("end findListShoppeData(),return:" + shoppeDtoList.toString());
        return shoppeDtoList;

    }

    /**
     * 专柜导入终端--由主数据获取专柜信息
     *
     * @param paramMap
     * @return List<PcmShoppeDataDto>
     * @Methods Name getShoppeData
     * @Create In 2015-8-25 By niuzhifan
     */
    @Override
    public List<PcmShoppeDataDto> getShoppeData(Map<String, Object> paramMap) {

        logger.info("start getShoppeData(),param:" + paramMap.toString());
        List<PcmShoppeDataDto> shoppeDataList = pcmShoppeMapper.getShoppeData(paramMap);
        logger.info("end getShoppeData(),return:" + shoppeDataList.toString());
        return shoppeDataList;
    }

    /*
     * (non-Javadoc) 分页条件查询专柜信息
     *
     * @see com.wangfj.product.organization.service.intf.IPcmShoppeService#
     * selectPageShoppe
     * (com.wangfj.product.organization.domain.vo.PcmGetShoppeDto,
     * com.wangfj.core.framework.base.page.Page)
     */
    @Override
    public Page<HashMap<String, Object>> selectPageShoppe(PcmGetShoppeDto dto,
                                                          Page<PcmGetShoppeDto> pageorg) {
        logger.info("start selectPageShoppe()");
        // Page<PcmShoppeResultDto> page = new Page<PcmShoppeResultDto>();
        // if (selectShoppeDto.getCurrentPage() != null) {
        // page.setCurrentPage(selectShoppeDto.getCurrentPage());
        // }
        // if (selectShoppeDto.getPageSize() != null) {
        // page.setPageSize(selectShoppeDto.getPageSize());
        // }
        //
        // // 查询总记录数
        // Integer count = pcmShoppeMapper.getPageCountByPara(selectShoppeDto);
        // page.setCount(count);
        //
        // // 分页查询
        // selectShoppeDto.setStart(page.getStart());
        // selectShoppeDto.setLimit(page.getLimit());
        // List<PcmShoppe> shoppeList =
        // pcmShoppeMapper.selectPageByPara(selectShoppeDto);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("shoppeCode", dto.getShoppeCode());
        map.put("shoppeName", dto.getShoppeName());
        map.put("shoppeType", dto.getShoppeType());
        map.put("shoppeStatus", dto.getShoppeStatus());
        map.put("floorCode", dto.getFloorCode());
        map.put("floorName", dto.getFloorName());
        map.put("industrySid", dto.getIndustrySid());
        map.put("goodManageType", dto.getGoodManageType());
        map.put("shippingPoint", dto.getShippingPoint());
        map.put("refCounter", dto.getRefCounter());
        map.put("negIiveStock", dto.getNegIiveStock());
        map.put("businessTypeSid", dto.getBusinessTypeSid());
        map.put("orgCode", dto.getOrgCode());
        map.put("orgName", dto.getOrgName());
        map.put("supplyCode", dto.getSupplyCode());
        map.put("shoppeGroup", dto.getShoppeGroup());
        map.put("actionCode", dto.getActionCode());
        map.put("actionDate", dto.getActionDate());
        map.put("actionPerson", dto.getActionPerson());
        map.put("optUser", dto.getOptUser());
        map.put("floorSid", dto.getFloorSid());
        map.put("createName", dto.getCreateName());
        map.put("createTime", dto.getCreateTime());
        map.put("shopSid", dto.getShopSid());
        map.put("updateTime", dto.getUpdateTime());
        map.put("isShippingPoint", dto.getIsShippingPoint());
        // 查询数据总数
        Integer count = pcmShoppeMapper.getCountShoppes(map);
        if (count != null) {
            pageorg.setCount(count);
        }
        int count1 = count / pageorg.getPageSize();// 整数
        int count2 = count % pageorg.getPageSize();// 余数
        int start = (pageorg.getCurrentPage() - 1) * pageorg.getPageSize();

        if (count2 == Constants.PUBLIC_0) {// 正好是整页
            if (count1 < pageorg.getCurrentPage()) {
                throw new BleException(
                        ComErrorCodeConstants.ErrorCode.SHOPPE_PAGE_ERROR.getErrorCode(),
                        ComErrorCodeConstants.ErrorCode.SHOPPE_PAGE_ERROR.getMemo());
            } else {
                map.put("start", start);
                map.put("limit", pageorg.getPageSize());
            }
        } else {
            if ((count1 + 1) < pageorg.getCurrentPage()) {
                throw new BleException(
                        ComErrorCodeConstants.ErrorCode.SHOPPE_PAGE_ERROR.getErrorCode(),
                        ComErrorCodeConstants.ErrorCode.SHOPPE_PAGE_ERROR.getMemo());
            } else if ((count1 + 1) == pageorg.getCurrentPage()) {
                map.put("start", start);
                map.put("limit", count2);
            } else {
                map.put("start", start);
                map.put("limit", pageorg.getPageSize());
            }
        }
        List<HashMap<String, Object>> listshopps = pcmShoppeMapper.findShoppeByParam(map);
        Page<HashMap<String, Object>> page = new Page<HashMap<String, Object>>();
        if (!listshopps.isEmpty()) {
            page.setList(listshopps);
            page.setCurrentPage(pageorg.getCurrentPage());
            page.setPageSize(pageorg.getPageSize());
            page.setCount(count);
        } else {
            logger.error("查询失败");
            throw new BleException(ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getErrorCode(),
                    ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getMemo());
        }
        logger.info("end selectPageShoppe()" + dto.toString());
        return page;
    }

    /**
     * 非电商专柜的编码生成
     */
    @Override
    public String generateShoppeCode(Map<String, Object> paramMap) {

        logger.info("start generateShoppeCode(),param:" + paramMap.toString());

        StringBuffer code = new StringBuffer();

        String shopCode = paramMap.get("shopCode") + "";
        if (StringUtils.isNotEmpty(shopCode)) {
            code.append(shopCode);
        }

        /**
         * 自动生成专柜编码 规则：门店编码+【该门店编码查找该门店下专柜的最大sid然后取后5位值再加1】
         */
        String maxSid = pcmShoppeMapper.getMaxShoppeSidByShop(paramMap);// 根据门店编码查找该门店下专柜的最大sid
        // 生成sid的后5位编号
        if (StringUtils.isNotEmpty(maxSid)) {

            Integer lengthSid = maxSid.length();

            if (lengthSid >= 5) {
                maxSid = maxSid.substring(lengthSid - 5);
            } else {
                for (int y = 0; y < 5 - maxSid.length(); y++) {
                    maxSid = "0" + maxSid;
                }
            }
            int n = Integer.parseInt(maxSid);
            n = n + 1;
            maxSid = String.valueOf(n);
            while (maxSid.length() < 5) {// 转化为数字时，位数不够5位时
                maxSid = "0" + maxSid;
            }
            maxSid = maxSid.substring(maxSid.length() - 5);

        } else {
            // 门店还没有专柜呢
            maxSid = "00001";
        }

        code.append(maxSid);

        logger.info("end generateShoppeCode(),return:" + code.toString());

        return code.toString();
    }

    /**
     * 电商专柜编码生成
     */
    @Deprecated
    @Override
    public String generateEShoppeCode(Map<String, Object> paramMap) {

        logger.info("start generateEShoppeCode(),param:" + paramMap.toString());

        StringBuilder sb = new StringBuilder();
        Map<String, Object> tempMap = pcmShoppeMapper.getMaxEShoppeCode(paramMap);
        String shopCode = paramMap.get("shopCode") + "";
        if (tempMap == null) {
            String shoppeCodeStart = paramMap.get("shoppeCodeStart") + "";
            sb.append(shopCode + shoppeCodeStart + "0001");
        } else {
            String maxEShoppeCode = tempMap.get("maxEShoppeCode") + "";
            String tempStr = maxEShoppeCode.substring(maxEShoppeCode.length()
                    - Constants.PCMSHOPPE_SHOPPE_SHOPPECODE_DIGIT);
            sb.append(shopCode);
            sb.append(Integer.parseInt(tempStr) + 1);
        }

        logger.info("end getMaxEShoppeCode(),return:" + sb.toString());
        return sb.toString();
    }

    /**
     * 电商专柜编码生成
     */
    @Override
    public String generateEBusinessShoppeCode(Map<String, Object> paramMap) {

        logger.info("start generateEBusinessShoppeCode(),param:" + paramMap.toString());

        StringBuilder sb = new StringBuilder();
        Map<String, Object> tempMap = pcmShoppeMapper.getMaxEBusinessShoppeCode(paramMap);
        String shopCode = paramMap.get("shopCode") + "";
        if (tempMap == null) {
            sb.append(shopCode + "00002");
        } else {
            String maxEShoppeCode = tempMap.get("maxEShoppeCode") + "";
            String tempStr = maxEShoppeCode.substring(maxEShoppeCode.length()
                    - Constants.PCMSHOPPE_SHOPPE_SHOPPECODE_DIGIT);
            sb.append(shopCode);
            String serailNumber = Integer.parseInt(tempStr) + 1 + "";
            if (serailNumber.length() < Constants.PCMSHOPPE_SHOPPE_SHOPPECODE_DIGIT) {
                for (int i = 0; i < Constants.PCMSHOPPE_SHOPPE_SHOPPECODE_DIGIT
                        - serailNumber.length(); i++) {
                    sb.append("0");
                }
            }
            sb.append(serailNumber);
        }

        logger.info("end generateEBusinessShoppeCode(),return:" + sb.toString());
        return sb.toString();
    }

    /**
     * 添加专柜
     */
    @Override
    @Transactional
    public Map<String, Object> addShoppe(PcmShoppeAUDto dto) {

        logger.info("start addShoppe(),param:" + dto.toString());

        Map<String, Object> resultMap = new HashMap<String, Object>();

        Integer result = Constants.PUBLIC_0;

        // 判断门店是否存在
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.clear();
        paramMap.put("organizationStatus", Constants.PUBLIC_0);
        paramMap.put("organizationType", Constants.PUBLIC_3);
        paramMap.put("sid", dto.getShopSid());
        paramMap.put("groupSid", dto.getGroupSid());

        List<PcmOrganization> shopList = pcmOrganizationMapper.selectListByParam(paramMap);

        if (shopList != null && shopList.size() == 1) {

            // 判重专柜名称
            paramMap.clear();
            paramMap.put("groupSid", dto.getGroupSid());
            paramMap.put("shopSid", dto.getShopSid());
            paramMap.put("shoppeName", dto.getShoppeName());
            List<PcmShoppe> shoppeList = pcmShoppeMapper.selectListByParam(paramMap);
            if (shoppeList != null && shoppeList.size() > 0) {
                throw new BleException(
                        ComErrorCodeConstants.ErrorCode.SHOPPE_NAME_EXISTENCE.getErrorCode(),
                        ComErrorCodeConstants.ErrorCode.SHOPPE_NAME_EXISTENCE.getMemo());
            }

            //校验供应商是否存在
            String supplySid = dto.getSupplySid();
            List<PcmSupplyInfo> supplyList = null;
            if (StringUtils.isNotEmpty(supplySid)) {
                paramMap.clear();
                paramMap.put("sid", supplySid);
                supplyList = supplyInfoMapper.selectListByParam(paramMap);
                if (supplyList == null || supplyList.size() != 1) {
                    throw new BleException(
                            ComErrorCodeConstants.ErrorCode.SUPPLYINFO_NOT_EXISTENCE.getErrorCode(),
                            ComErrorCodeConstants.ErrorCode.SUPPLYINFO_NOT_EXISTENCE.getMemo());
                }
            } else {
                throw new BleException(
                        ComErrorCodeConstants.ErrorCode.SUPPLYINFO_NOT_EXISTENCE.getErrorCode(),
                        ComErrorCodeConstants.ErrorCode.SUPPLYINFO_NOT_EXISTENCE.getMemo());
            }

            PcmShoppe shoppe = new PcmShoppe();
            BeanUtils.copyProperties(dto, shoppe);
            String shoppeCode = "";

            // 电商专柜创建逻辑：根据拆单标识
            Integer industryConditionSid = dto.getIndustryConditionSid();
            // 业态为电商，电商的专柜
            if (industryConditionSid == 2) {
                if (supplyList != null && supplyList.size() == 1) {
                    PcmSupplyInfo supplyInfo = supplyList.get(0);
                    Integer apartOrder = supplyInfo.getApartOrder();
                    if (apartOrder == 1) {
                        paramMap.clear();
                        paramMap.put("shopCode", shopList.get(0).getOrganizationCode());
                        shoppeCode = generateEBusinessShoppeCode(paramMap);
                    } else {
                        throw new BleException(ErrorCode.SHOPPE_EXIST.getErrorCode(),
                                ErrorCode.SHOPPE_EXIST.getMemo());
                    }
                }
            } else {
                // 非电商的专柜，生成编码
                paramMap.clear();
                paramMap.put("groupSid", dto.getGroupSid());
                paramMap.put("sid", dto.getShopSid());
                paramMap.put("shopCode", shopList.get(0).getOrganizationCode());
                shoppeCode = generateShoppeCode(paramMap);
            }

            shoppe.setShoppeCode(shoppeCode);
            shoppe.setCreateTime(new Date());
            result = pcmShoppeMapper.insertSelective(shoppe);

            if (result == Constants.PUBLIC_1) {

                // 向专柜渠道表插入数据
                List<PcmChannelSaleConfigDto> channelSaleConfigParaList = dto
                        .getChannelSaleConfigParaList();
                if (channelSaleConfigParaList != null && channelSaleConfigParaList.size() > 0) {
                    for (PcmChannelSaleConfigDto channelSaleConfigDto : channelSaleConfigParaList) {
                        paramMap.clear();
                        paramMap.put("shoppeProSid", shoppe.getSid());
                        paramMap.put("channelSid", channelSaleConfigDto.getChannelSid());
                        paramMap.put("saleStauts", Constants.PUBLIC_0);
                        List<PcmChannelSaleConfig> saleList = channelSaleConfigMapper
                                .selectListByParam(paramMap);
                        if (saleList != null && saleList.isEmpty()) {
                            PcmChannelSaleConfig channelSaleConfig = new PcmChannelSaleConfig();
                            BeanUtils.copyProperties(channelSaleConfigDto, channelSaleConfig);
                            channelSaleConfig.setShoppeProSid(shoppe.getSid());
                            channelSaleConfig.setSaleStauts(Constants.PUBLIC_0);
                            channelSaleConfigMapper.insertSelective(channelSaleConfig);
                        }
                    }
                }

                // 向专柜供应商表插入数据
                if (StringUtils.isNotEmpty(supplySid)) {
                    // 新建的专柜，专柜供应商不会重复，不需判重
                    PcmSupplyShoppeRelation supplyShoppeRelation = new PcmSupplyShoppeRelation();
                    supplyShoppeRelation.setShoppeSid(shoppe.getSid() + "");
                    supplyShoppeRelation.setSupplySid(supplySid);
                    supplyShoppeRelation.setStatus(0);
                    supplyShoppeRelationMapper.insertSelective(supplyShoppeRelation);
                }

                resultMap.put("sid", shoppe.getSid());
                resultMap.put("success", Constants.PUBLIC_1);
            }
        }

        logger.info("end addShoppe(),return:" + resultMap.toString());

        return resultMap;

    }

    /**
     * 修改专柜
     */
    @Override
    @Transactional
    public Integer modifyShoppe(PcmShoppeAUDto dto) {

        logger.info("start modifyShoppe(),param:" + dto.toString());

        Integer result = Constants.PUBLIC_0;

        // 判断修改的专柜是否存在
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sid", dto.getSid());
        paramMap.put("groupSid", dto.getGroupSid());
        paramMap.put("shopSid", dto.getShopSid());
        List<PcmShoppe> list = pcmShoppeMapper.selectListByParam(paramMap);

        if (list != null && list.size() == 1) {

            String shoppeName = dto.getShoppeName();
            if (StringUtils.isNotEmpty(shoppeName)) {
                // 如果修改了名称需要判重
                if (!shoppeName.trim().equals(list.get(0).getShoppeName())) {
                    paramMap.clear();
                    paramMap.put("groupSid", dto.getGroupSid());
                    paramMap.put("shopSid", dto.getShopSid());
                    paramMap.put("shoppeName", dto.getShoppeName());
                    List<PcmShoppe> nameList = pcmShoppeMapper.selectListByParam(paramMap);
                    if (nameList != null && !nameList.isEmpty()) {
                        throw new BleException(
                                ComErrorCodeConstants.ErrorCode.SHOPPE_NAME_EXISTENCE
                                        .getErrorCode(),
                                ComErrorCodeConstants.ErrorCode.SHOPPE_NAME_EXISTENCE.getMemo());
                    }
                }
            }

            PcmShoppe shoppe = new PcmShoppe();
            BeanUtils.copyProperties(dto, shoppe);
            shoppe.setUpdateTime(new Date());
            result = pcmShoppeMapper.updateByPrimaryKeySelective(shoppe);

            // 修改专柜渠道
            paramMap.clear();
            paramMap.put("shoppeProSid", dto.getSid());
            paramMap.put("saleStauts", Constants.PUBLIC_0);
            List<PcmChannelSaleConfig> saleList = channelSaleConfigMapper
                    .selectListByParam(paramMap);

            // 专柜下是否存在渠道
            if (saleList != null && !saleList.isEmpty()) {

                // 如果存在将所有的渠道可售删除
                for (PcmChannelSaleConfig temp : saleList) {
                    // 删除该渠道，将渠道可售设置1
                    PcmChannelSaleConfig channelSaleConfig = new PcmChannelSaleConfig();
                    channelSaleConfig.setSid(temp.getSid());
                    channelSaleConfig.setSaleStauts(Constants.PUBLIC_1);
                    channelSaleConfigMapper.updateByPrimaryKeySelective(channelSaleConfig);
                }

            }
            // 再添加专柜渠道
            List<PcmChannelSaleConfigDto> channelSaleConfigParaList = dto
                    .getChannelSaleConfigParaList();
            for (PcmChannelSaleConfigDto temp : channelSaleConfigParaList) {
                PcmChannelSaleConfig channelSaleConfig = new PcmChannelSaleConfig();
                BeanUtils.copyProperties(temp, channelSaleConfig);
                channelSaleConfig.setSaleStauts(Constants.PUBLIC_0);
                channelSaleConfigMapper.insertSelective(channelSaleConfig);
            }

            // 修改专柜供应商表数据
            String supplySid = dto.getSupplySid();
            if (StringUtils.isNotEmpty(supplySid)) {
                PcmSupplyShoppeRelation relationPara = new PcmSupplyShoppeRelation();
                relationPara.setShoppeSid(dto.getSid() + "");
                List<PcmSupplyShoppeRelation> ssrList = supplyShoppeRelationMapper
                        .selectListByParam(relationPara);
                if (ssrList != null && ssrList.size() == 1) {
                    PcmSupplyShoppeRelation relation = ssrList.get(0);
                    relation.setSupplySid(supplySid);
                    relation.setStatus(Constants.PUBLIC_0);
                    supplyShoppeRelationMapper.updateByPrimaryKeySelective(relation);
                }
            }

        }

        logger.info("end modifyShoppe(),return:" + result.toString());
        return result;

    }

    /**
     * 专柜状态变更
     *
     * @create 2015-9-12 nzf 根据专柜sid变更
     */
    @Override
    @Transactional
    public int updateShoppeStatus(PcmGetShoppeDto dto) {
        logger.info("开始修改专柜状态");
        int count = 0;

        PcmShoppe pcmShoppe = new PcmShoppe();
        pcmShoppe.setSid(dto.getSid());

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("organizationType", 3);
        map.put("sid", dto.getShopSid());// 门店sid
        List<PcmOrganization> list = pcmOrganizationMapper.selectListByParam(map);
        if (list.size() > 0) {
            /**
             * 根据sid找到对应的装柜
             */
            PcmShoppe shoppe = pcmShoppeMapper.get(dto.getSid());
            if (shoppe == null) {
                throw new BleException(
                        ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getErrorCode(),
                        ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getMemo());
            } else {
                /* 当前状态 */
                Integer status = shoppe.getShoppeStatus();
                /* 欲更正状态 */
                Integer updateStatus = dto.getShoppeStatus();

                // 正常
                if (status == 1) {
                    /**
                     * 停用之前先修改渠道可售状态，更改为不可售再停用处理
                     */
                    if (updateStatus == 2) {
                        Map<String, Object> statusMap = new HashMap<String, Object>();
                        statusMap.put("sid", dto.getSid());
                        statusMap.put("saleStatus", 1);
                        int a = pcmShoppeMapper.getStatusByShoppe(statusMap);
                        if (a == 1) {
                            logger.info("正常更改为停用了");
                            pcmShoppe.setShoppeStatus(2);
                        } else {
                            logger.error("更改失败");
                            throw new BleException(
                                    ComErrorCodeConstants.ErrorCode.FLOOR_SHOP_NOT_RELATIVE
                                            .getErrorCode(),
                                    ComErrorCodeConstants.ErrorCode.FLOOR_SHOP_NOT_RELATIVE
                                            .getMemo());
                        }

                    } else if (updateStatus == 3) {
                        /**
                         * 撤柜之前先判断该专柜下的商品库存是否为零
                         */

                    } else {
                        pcmShoppe.setShoppeStatus(1);
                    }

                    // 停用
                } else if (status == 2) {
                    if (updateStatus == 1) {
                        pcmShoppe.setShoppeStatus(1);
                    } else if (updateStatus == 3) {
                        /**
                         * 撤柜之前先判断该专柜下的商品库存是否为零
                         */
                    } else {
                        pcmShoppe.setShoppeStatus(dto.getShoppeStatus());
                    }
                    // 撤柜
                } else if (status == 3) {
                    // 撤柜状态下不能恢复正常
                    if (updateStatus == 1) {
                        throw new BleException(
                                ComErrorCodeConstants.ErrorCode.SHOPPE_STATUS_UPDATE_ERROR
                                        .getErrorCode(),
                                ComErrorCodeConstants.ErrorCode.SHOPPE_STATUS_UPDATE_ERROR
                                        .getMemo());
                    }
                    pcmShoppe.setShoppeStatus(updateStatus);
                    // 没有其他状态了，故错误情况
                } else {
                    throw new BleException(
                            ComErrorCodeConstants.ErrorCode.SHOPPE_STATUS_IS_ERROR.getErrorCode(),
                            ComErrorCodeConstants.ErrorCode.SHOPPE_STATUS_IS_ERROR.getMemo());
                }
                count = pcmShoppeMapper.updateByPrimaryKeySelective(pcmShoppe);
                if (count == 1) {
                    logger.info("装柜状态变更完毕");
                }
            }
        } else {
            throw new BleException(
                    ComErrorCodeConstants.ErrorCode.SHOPPE_SHOP_NOT_EXIST.getErrorCode(),
                    ComErrorCodeConstants.ErrorCode.SHOPPE_SHOP_NOT_EXIST.getMemo());
        }
        return count;

    }

    /**
     * 移动工作台调用主数据获取专柜信息
     */
    @Override
    public List<PcmShoppeErpDto> getShoppeInfo(PcmGetShoppeDto dto) {
        List<PcmShoppeErpDto> resultDto = null;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("organizationType", 3);
        map.put("organizationCode", dto.getOrgCode());// 门店编码
        List<PcmOrganization> list = pcmOrganizationMapper.selectListByParam(map);
        if (list != null && list.size() == 1) {
            PcmShoppe shoppe = new PcmShoppe();
            Long shopSid = list.get(0).getSid();
            shoppe.setShopSid(shopSid);
            if (StringUtils.isNotEmpty(dto.getShoppeCode())) {
                shoppe.setShoppeCode(dto.getShoppeCode());
            }
            if (StringUtils.isNotEmpty(dto.getShoppeName())) {
                shoppe.setShoppeName(dto.getShoppeName());
            }
            resultDto = pcmShoppeMapper.getShoppeInfo(shoppe);
            if (resultDto.size() == Constants.PUBLIC_0) {
                throw new BleException(
                        ComErrorCodeConstants.ErrorCode.SHOPPE_IS_NOT.getErrorCode(),
                        ComErrorCodeConstants.ErrorCode.SHOPPE_IS_NOT.getMemo());
            } else {
                for (int i = 0; i < resultDto.size(); i++) {

                    // 封装楼层名称和编码
                    Map<String, Object> map1 = new HashMap<String, Object>();
                    map1.put("shoppeCode", resultDto.get(i).getCode());// 专柜编码
                    List<PcmShoppe> fl = pcmShoppeMapper.selectListByParam(map1);
                    Long floorSid = fl.get(0).getFloorSid();// 获取楼层sid
                    if (floorSid != null) {
                        PcmFloor floor = pcmFloorMapper.selectByPrimaryKey(floorSid);
                        if (floor != null) {
                            resultDto.get(i).setFloorName(floor.getFloorName());
                            resultDto.get(i).setFloorCode(floor.getFloorCode());
                        } else {
                            logger.error("查找不到该楼层信息");
                            resultDto.get(i).setFloorName(null);
                            resultDto.get(i).setFloorCode(null);
                        }
                    } else {
                        logger.error("楼层sid为空");
                        resultDto.get(i).setFloorName(null);
                        resultDto.get(i).setFloorCode(null);
                    }

                    Map<String, Object> map2 = new HashMap<String, Object>();
                    map2.put("shopSid", dto.getOrgCode());// 专柜编码
                    // 供应商编码
                    List<PcmSupplyInfo> pcmInfo = supplyInfoMapper.selectListByParam(map2);
                    if (pcmInfo.size() == Constants.PUBLIC_0) {
                        logger.error("根据门店编码没有找到关联的供应商");
                        resultDto.get(i).setSupplierCode(null);
                        resultDto.get(i).setSupplierErpCode(null);

                    } else {
                        resultDto.get(i).setSupplierCode(pcmInfo.get(0).getSupplyCode());
                        resultDto.get(i).setSupplierErpCode(null);
                    }
                }
                logger.info("查询成功");
            }
        } else {
            throw new BleException(
                    ComErrorCodeConstants.ErrorCode.SHOPPE_SHOP_NOT_EXIST.getErrorCode(),
                    ComErrorCodeConstants.ErrorCode.SHOPPE_SHOP_NOT_EXIST.getMemo());
        }
        return resultDto;

    }

    /**
     * PAD查询专柜信息
     *
     * @param dto
     * @return
     */
    @Override
    public List<PcmPadShoppeResultDto> findShopAndShoppeByParam(PcmPadShoppeQueryDto dto) {

        logger.info("start findShopAndShoppeByParam(),param:" + dto.toString());
        List<PcmPadShoppeResultDto> list = pcmShoppeMapper.findShopAndShoppeByParam(dto);
        logger.info("end findShopAndShoppeByParam(),return:" + list.toString());
        return list;
    }

    /**
     * 根据门店和供应商查询专柜（页面添加专柜商品）
     */
    @Override
    public List<PcmShoppeResultDto> findListShoppeForAddShoppeProduct(SelectPcmShoppeDto dto) {

        logger.info("start findListShoppeForAddShoppeProduct(),param:" + dto.toString());
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sid", dto.getSupplySid());
        List<PcmSupplyInfo> supplyInfoList = supplyInfoMapper.selectListByParam(paramMap);
        //电商拆弹标识：否，时查询电商自库专柜
        if (supplyInfoList.size() == 1) {
            PcmSupplyInfo supplyInfo = supplyInfoList.get(0);
            String shopSid = supplyInfo.getShopSid();
            Integer apartOrder = supplyInfo.getApartOrder();
            if ("D001".equals(shopSid) && apartOrder == 0) {//电商自库专柜
                paramMap.clear();
                paramMap.put("supplyCode", "1");
                List<PcmSupplyInfo> wfjSupplyList = supplyInfoMapper.selectListByParam(paramMap);
                Long supplySid = wfjSupplyList.get(0).getSid();
                dto.setSupplySid(supplySid + "");
            }
        }
        List<PcmShoppeResultDto> list = pcmShoppeMapper.findListShoppeForAddShoppeProduct(dto);
        logger.info("end findListShoppeForAddShoppeProduct(),return:" + list.toString());
        return list;
    }
}
