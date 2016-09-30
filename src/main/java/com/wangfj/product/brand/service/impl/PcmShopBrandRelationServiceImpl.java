package com.wangfj.product.brand.service.impl;

import com.wangfj.core.constants.ComErrorCodeConstants.ErrorCode;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.core.utils.StringUtils;
import com.wangfj.product.brand.domain.entity.PcmBrand;
import com.wangfj.product.brand.domain.entity.PcmShopBrandRelation;
import com.wangfj.product.brand.domain.vo.PcmBrandUploadDto;
import com.wangfj.product.brand.domain.vo.PcmShopBrandResultDto;
import com.wangfj.product.brand.domain.vo.PcmShopBrandUploadDto;
import com.wangfj.product.brand.persistence.PcmBrandMapper;
import com.wangfj.product.brand.persistence.PcmShopBrandRelationMapper;
import com.wangfj.product.brand.service.intf.IPcmBrandCommonService;
import com.wangfj.product.brand.service.intf.IPcmShopBrandRelationService;
import com.wangfj.product.organization.domain.entity.PcmOrganization;
import com.wangfj.product.organization.persistence.PcmOrganizationMapper;
import com.wangfj.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 门店与品牌关系
 *
 * @Class Name PcmShopBrandRelationServiceImpl
 * @Author wangxuan
 * @Create In 2015-8-19
 */
@Service
public class PcmShopBrandRelationServiceImpl implements IPcmShopBrandRelationService {

    private static final Logger logger = LoggerFactory
            .getLogger(PcmShopBrandRelationServiceImpl.class);

    @Autowired
    private PcmShopBrandRelationMapper shopBrandRelationMapper;

    @Autowired
    private PcmBrandMapper brandMapper;

    @Autowired
    private PcmOrganizationMapper organizationMapper;

    @Autowired
    private IPcmBrandCommonService brandCommonService;

    /**
     * 门店与品牌关系的判重
     *
     * @param shopBrandRelation
     * @return Boolean
     * @Methods Name isExistence
     * @Create In 2015-8-19 By wangxuan
     */
    @Override
    public Boolean isExistence(PcmShopBrandRelation shopBrandRelation) {

        logger.info("start isExistence(),para:" + shopBrandRelation.toString());

        Boolean flag = false;

        String shopSid = shopBrandRelation.getShopSid();
        String brandSid = shopBrandRelation.getBrandSid();

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("status", Constants.PUBLIC_0);

        if (StringUtils.isNotEmpty(shopSid)) {
            paramMap.put("shopSid", shopSid);
        }

        if (StringUtils.isNotEmpty(brandSid)) {
            paramMap.put("brandSid", brandSid);
        }

        List<PcmShopBrandRelation> shopBrandRelationList = shopBrandRelationMapper
                .selectListByParam(paramMap);
        if (shopBrandRelationList != null && !shopBrandRelationList.isEmpty()) {
            flag = true;
            throw new BleException(ErrorCode.BRAND_SHOP_RELATION_EXISTENCE.getErrorCode(),
                    ErrorCode.BRAND_SHOP_RELATION_EXISTENCE.getMemo());
        }

        logger.info("end isExistence(),return:" + flag);
        return flag;

    }

    /**
     * 门店与门店品牌关系上传
     *
     * @param dto
     * @return Map<String, Object>
     * @Methods Name uploadShopBrandRelation
     * @Create In 2015-9-18 By wangxuan
     */
    @Override
    @Transactional
    public Map<String, Object> uploadShopBrandRelation(PcmShopBrandUploadDto dto) {

        logger.info("start uploadShopBrandRelation(),para:" + dto.toString());

        Map<String, Object> resultMap = new HashMap<String, Object>();
        Integer flag = Constants.PUBLIC_0;

        String storeCode = dto.getStoreCode();
        String brandCode = dto.getBrandCode();
        // String brandName = dto.getBrandName();

        Map<String, Object> relationPara = new HashMap<String, Object>();
        if (StringUtils.isNotEmpty(storeCode)) {
            relationPara.put("shopCode", storeCode);
        }

        if (StringUtils.isNotEmpty(brandCode)) {
            relationPara.put("brandSid", brandCode);
        }

        List<PcmShopBrandRelation> relationUploadList = shopBrandRelationMapper
                .getShopBrandRelationUpload(relationPara);

        if (relationUploadList != null && relationUploadList.size() == 1) {

            String actionCode = dto.getACT_CODE();
            if (actionCode.toUpperCase().equals(Constants.A)) {
                PcmShopBrandRelation shopBrandRelation = new PcmShopBrandRelation();
                shopBrandRelation.setBrandSid(relationUploadList.get(0).getBrandSid());
                shopBrandRelation.setShopSid(relationUploadList.get(0).getShopSid());
                // 判重
                if (!isExistence(shopBrandRelation)) {

                    // // 如果上传了名称，以上传为准，如果没有上传名称，就以查询出来的数据为准。
                    // if (StringUtils.isNotEmpty(brandName)) {
                    // shopBrandRelation.setBrandName(brandName);
                    // } else {
                    // shopBrandRelation.setBrandName(relationUploadList.get(0).getBrandName());
                    // }
                    // 有效标记
                    shopBrandRelation.setStatus(Constants.PUBLIC_0);
                    flag = shopBrandRelationMapper.insertSelective(shopBrandRelation);
                }
            }

            if (actionCode.toUpperCase().equals(Constants.D)) {

                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("brandSid", relationUploadList.get(0).getBrandSid());
                paramMap.put("shopSid", relationUploadList.get(0).getShopSid());
                paramMap.put("status", Constants.PUBLIC_0);
                List<PcmShopBrandRelation> list = shopBrandRelationMapper
                        .selectListByParam(paramMap);
                // 判断是否存在
                if (list != null && list.size() == 1) {
                    list.get(0).setStatus(Constants.PUBLIC_1);
                    flag = shopBrandRelationMapper.updateByPrimaryKeySelective(list.get(0));
                }

            }

            resultMap.put("sid", relationUploadList.get(0).getBrandSid());

        } else {
            throw new BleException(ErrorCode.BRAND_SHOP_RELATION_EXISTENCE.getErrorCode(),
                    "存在品牌和门店的门店类型一样的重复数据！");
        }

        resultMap.put("result", flag);
        logger.info("end uploadShopBrandRelation(),return:" + resultMap.toString());
        return resultMap;
    }

    /**
     * 门店与门店品牌信息上传
     *
     * @param dto
     * @return Map<String, Object>
     * @Methods Name uploadShopBrandInfo
     * @Create In 2015-11-18 By wangxuan
     */
    @Override
    @Transactional
    public Map<String, Object> uploadShopBrandInfo(PcmShopBrandUploadDto dto) {

        logger.info("start uploadShopBrandInfo(),para:" + dto.toString());

        Map<String, Object> resultMap = new HashMap<String, Object>();
        Integer result = Constants.PUBLIC_0;

        String actionCode = dto.getACT_CODE();
        if (StringUtils.isNotEmpty(actionCode)) {

            // 门店品牌判重
            String storeCode = dto.getStoreCode();
            String brandCode = dto.getBrandCode();

            Map<String, Object> para = new HashMap<String, Object>();
            para.put("shopCode", storeCode);
            para.put("brandSid", brandCode);
            List<PcmShopBrandResultDto> shopBrandList = brandCommonService
                    .isShopBrandExistence(para);

            PcmBrand brand = transformDto(dto);

            if (actionCode.trim().equalsIgnoreCase(Constants.A)) {
                if (shopBrandList != null && shopBrandList.isEmpty()) {

                    result = brandMapper.insertSelective(brand);

                    if (result == 1) {
                        para.clear();
                        para.put("organizationCode", storeCode);
                        para.put("organizationType", Constants.PUBLIC_3);
                        para.put("organizationStatus", Constants.PUBLIC_0);
                        List<PcmOrganization> shopList = organizationMapper.selectListByParam(para);
                        if (shopList != null && shopList.size() > 0) {
                            PcmShopBrandRelation relation = new PcmShopBrandRelation();
                            relation.setBrandSid(brand.getSid() + "");
                            relation.setShopSid(shopList.get(0).getSid() + "");
                            relation.setBrandName(dto.getBrandName());
                            relation.setStatus(Constants.PUBLIC_0);
                            result = shopBrandRelationMapper.insertSelective(relation);
                            if (result == 1) {
                                resultMap.put("sid", brand.getSid());
                            }
                        }
                    }

                }
            }

            if (actionCode.trim().equalsIgnoreCase(Constants.U)) {
                if (shopBrandList != null && shopBrandList.size() == 1) {
                    brand.setSid(shopBrandList.get(0).getSid());
                    result = brandMapper.updateByPrimaryKeySelective(brand);
                    if (result == 1) {
                        resultMap.put("sid", brand.getSid());
                    }
                }
            }

            if (actionCode.trim().equalsIgnoreCase(Constants.D)) {
                if (shopBrandList != null && shopBrandList.size() == 1) {
                    para.put("brandSid", shopBrandList.get(0).getSid());
                    para.put("shopSid", shopBrandList.get(0).getShopSid());
                    para.put("status", Constants.PUBLIC_0);
                    List<PcmShopBrandRelation> list = shopBrandRelationMapper
                            .selectListByParam(para);
                    // 判断是否存在
                    if (list != null && list.size() == 1) {
                        list.get(0).setStatus(Constants.PUBLIC_1);
                        result = shopBrandRelationMapper.updateByPrimaryKeySelective(list.get(0));
                    }
                }
            }

        }

        resultMap.put("result", result);

        logger.info("end uploadShopBrandInfo(),return:" + resultMap);
        return resultMap;
    }

    /**
     * 门店品牌上传
     *
     * @param dto
     * @return Map<String, Object>
     * @Methods Name uploadBrand
     * @Create In 2016-03-22 By wangxuan
     */
    @Override
    @Transactional
    public Map<String, Object> uploadBrand(PcmBrandUploadDto dto) {
        logger.info("start uploadBrand(),param:" + dto.toString());
        Map<String, Object> returnMap = new HashMap<String, Object>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        //校验门店是否存在
        String storeCode = dto.getStoreCode();
        if (StringUtils.isNotEmpty(storeCode)) {
            paramMap.put("organizationCode", storeCode.trim());
        }
        paramMap.put("organizationType", Constants.PCMORGANIZATION_TYPE_STORE_INT);
        paramMap.put("organizationStatus", Constants.PUBLIC_0);
        List<PcmOrganization> organizationList = organizationMapper.selectListByParam(paramMap);
        if (organizationList == null || organizationList.size() != 1) {
            throw new BleException(ErrorCode.SHOP_NULL.getErrorCode(), ErrorCode.SHOP_NULL.getMemo());
        }

        //校验集团品牌是否存在
        String superCode = dto.getSuperCode();
        List<PcmBrand> brandGroupList = null;
        if (StringUtils.isNotEmpty(superCode)) {
            paramMap.clear();
            paramMap.put("brandSid", superCode.trim());
            paramMap.put("brandType", Constants.PUBLIC_0);
            paramMap.put("status", Constants.PUBLIC_0);
            brandGroupList = brandMapper.selectListByParam(paramMap);
            if (brandGroupList == null || brandGroupList.size() != 1) {
                throw new BleException(ErrorCode.BRANDGROUP_NOT_EXIST.getErrorCode(), ErrorCode.BRANDGROUP_NOT_EXIST.getMemo());
            }
        }

        //查询门店品牌
        String code = dto.getCode();
        paramMap.clear();
        if (StringUtils.isNotEmpty(code)) {
            paramMap.put("brandSid", code.trim());
        }
        paramMap.put("brandType", Constants.PUBLIC_1);
        paramMap.put("status", Constants.PUBLIC_0);
        List<PcmBrand> brandList = brandMapper.selectListByParam(paramMap);

        PcmBrand brand = new PcmBrand();
        brand.setBrandSid(code);
        brand.setBrandName(dto.getName());
        brand.setBrandType(Constants.PUBLIC_1);
        brand.setStatus(Constants.PUBLIC_0);
        String storeType = dto.getStoreType();
        if (Constants.PCMBRAND_ERP_STORE_TYPE_BEIJING.equals(storeType.trim())) {
            brand.setShopType(Constants.PCMBRAND_SHOP_TYPE_BEIJING);
        }
        if (Constants.PCMBRAND_ERP_STORE_TYPE_OTHER.equals(storeType.trim())) {
            brand.setShopType(Constants.PCMBRAND_SHOP_TYPE_OTHER);
        }
        if (Constants.PCMBRAND_ERP_STORE_TYPE_EBUSINESS.equals(storeType.trim())) {
            brand.setShopType(Constants.PCMBRAND_SHOP_TYPE_EBUSINESS);
        }
        brand.setBrandNameSecond(dto.getName2());
        if (brandGroupList != null && brandGroupList.size() == 1) {
            brand.setParentSid(brandGroupList.get(0).getSid());
        } else {
            brand.setParentSid(new Long(Constants.PUBLIC_0));
        }
        brand.setIsDisplay(Constants.PUBLIC_0);

        String actionCode = dto.getActionCode();
        if (Constants.A.equals(actionCode)) {
            if (brandList != null && brandList.size() > 0) {//判重
                throw new BleException(ErrorCode.BRAND_EXIST.getErrorCode(), ErrorCode.BRAND_EXIST.getMemo());
            }
            int result = brandMapper.insertSelective(brand);
            returnMap.put("result", result);
            if (result == 1) {
                Long sid = brand.getSid();
                if (sid != null) {//添加门店品牌与门店的关系
                    PcmShopBrandRelation shopBrand = new PcmShopBrandRelation();
                    shopBrand.setShopSid(organizationList.get(0).getSid() + "");
                    shopBrand.setBrandSid(sid + "");
                    shopBrand.setStatus(Constants.PUBLIC_0);
                    shopBrandRelationMapper.insertSelective(shopBrand);
                }
            }
        }

        if (Constants.U.equals(actionCode)) {
            if (brandList == null || brandList.size() != 1) {
                throw new BleException(ErrorCode.BRAND_NOT_EXIST.getErrorCode(), ErrorCode.BRAND_NOT_EXIST.getMemo());
            }
            brand.setSid(brandList.get(0).getSid());
            brandMapper.updateByPrimaryKeySelective(brand);
        }

        logger.info("end uploadBrand(),return:" + returnMap.toString());
        return returnMap;
    }

    private PcmBrand transformDto(PcmShopBrandUploadDto dto) {

        PcmBrand brand = new PcmBrand();
        brand.setBrandSid(dto.getBrandCode());
        brand.setBrandName(dto.getBrandName());
        brand.setParentSid(new Long(Constants.PUBLIC_0));
        brand.setBrandType(Constants.PUBLIC_1);
        String storeType = dto.getStoreType();
        if (StringUtils.isNotEmpty(storeType)) {
            if (storeType.equals(Constants.PUBLIC_1 + "")) {
                brand.setShopType(Constants.PUBLIC_2);
            }
            if (storeType.equals(Constants.PUBLIC_2 + "")) {
                brand.setShopType(Constants.PUBLIC_0);
            }
            if (storeType.equals(Constants.PUBLIC_3 + "")) {
                brand.setShopType(Constants.PUBLIC_1);
            }
        }
        brand.setBrandNameSecond(dto.getBrandNameSecond());
        brand.setBrandNameEn(dto.getBrandNameEN());
        brand.setSpell(dto.getBrandNameSpell());
        String isDisplay = dto.getIsDisplay();
        if (StringUtils.isNotEmpty(isDisplay)) {
            brand.setIsDisplay(Integer.parseInt(isDisplay));
        }
        brand.setBrandpic1(dto.getBrandpic1());
        brand.setBrandpic2(dto.getBrandpic2());
        brand.setBrandDesc(dto.getBranddesc());
        brand.setStatus(Constants.PUBLIC_0);
        brand.setOptUpdateTime(new Date());

        return brand;
    }
}
