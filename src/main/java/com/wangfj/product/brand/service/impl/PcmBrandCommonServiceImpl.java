package com.wangfj.product.brand.service.impl;

import com.wangfj.core.constants.ComErrorCodeConstants;
import com.wangfj.core.constants.ComErrorCodeConstants.ErrorCode;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.core.utils.StringUtils;
import com.wangfj.product.brand.domain.entity.PcmBrand;
import com.wangfj.product.brand.domain.vo.GenerateBrandCodeDto;
import com.wangfj.product.brand.domain.vo.PcmShopBrandResultDto;
import com.wangfj.product.brand.persistence.PcmBrandMapper;
import com.wangfj.product.brand.persistence.PcmShopBrandRelationMapper;
import com.wangfj.product.brand.service.intf.IPcmBrandCommonService;
import com.wangfj.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PcmBrandCommonServiceImpl implements IPcmBrandCommonService {

    private static final Logger logger = LoggerFactory.getLogger(PcmBrandCommonServiceImpl.class);

    @Autowired
    private PcmBrandMapper brandMapper;

    @Autowired
    private PcmShopBrandRelationMapper shopBrandRelationMapper;

    /**
     * 集团品牌判重
     *
     * @param para
     * @return Boolean
     * @Methods Name isBrandGroupExistence
     * @Create In 2015-8-12 By wangx
     */
    @Override
    public Boolean isBrandGroupExistence(Map<String, Object> para) {

        logger.info("start isBrandGroupExistence(),para:" + para.toString());

        Boolean flag = false;

        // 判重，集团品牌名称和集团品牌编码都不能重复
        String brandName = para.get("brandName") + "";
        String brandSid = para.get("brandSid") + "";
        if (StringUtils.isNotEmpty(brandName)) {
            // 集团品牌名称
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("brandName", brandName);
            paramMap.put("status", Constants.PUBLIC_0);
            paramMap.put("brandType", Constants.PUBLIC_0);
            List<PcmBrand> brandList = brandMapper.selectListByParam(paramMap);
            if (!brandList.isEmpty()) {
                flag = true;
                throw new BleException(ErrorCode.BRANDGROUP_BRANDNAEM_EXIST.getErrorCode(),
                        ErrorCode.BRANDGROUP_BRANDNAEM_EXIST.getMemo());
            }
        }

        if (StringUtils.isNotEmpty(brandSid)) {
            // 集团品牌编码
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("brandSid", brandSid);
            paramMap.put("status", Constants.PUBLIC_0);
            paramMap.put("brandType", Constants.PUBLIC_0);
            List<PcmBrand> brandList = brandMapper.selectListByParam(paramMap);
            if (!brandList.isEmpty()) {
                flag = true;
                throw new BleException(ErrorCode.BRANDGROUP_BRANDSID_EXIST.getErrorCode(),
                        ErrorCode.BRANDGROUP_BRANDSID_EXIST.getMemo());
            }
        }

        // 判重，集团品牌中文名称和英文名称都不能重复
        String brandNameSecond = para.get("brandNameSecond") + "";
        String brandNameEn = para.get("brandNameEn") + "";
        if (StringUtils.isNotEmpty(brandNameSecond)) {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("brandNameSecond", brandNameSecond);
            paramMap.put("status", Constants.PUBLIC_0);
            paramMap.put("brandType", Constants.PUBLIC_0);
            List<PcmBrand> brandList = brandMapper.selectListByParam(paramMap);
            if (!brandList.isEmpty()) {
                flag = true;
                throw new BleException(ErrorCode.BRANDGROUP_BRANDNAMESECOND_EXIST.getErrorCode(),
                        ErrorCode.BRANDGROUP_BRANDNAMESECOND_EXIST.getMemo());
            }
        }
        if (StringUtils.isNotEmpty(brandNameEn)) {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("brandNameEn", brandNameEn);
            paramMap.put("status", Constants.PUBLIC_0);
            paramMap.put("brandType", Constants.PUBLIC_0);
            List<PcmBrand> brandList = brandMapper.selectListByParam(paramMap);
            if (!brandList.isEmpty()) {
                flag = true;
                throw new BleException(ErrorCode.BRANDGROUP_BRANDNAMEEN_EXIST.getErrorCode(),
                        ErrorCode.BRANDGROUP_BRANDNAMEEN_EXIST.getMemo());
            }
        }

        logger.info("end isBrandGroupExistence(),return:" + flag);
        return flag;

    }

    /**
     * 门店品牌判重
     *
     * @param para
     * @return Boolean
     * @Methods Name isBrandExistence
     * @Create In 2015-8-24 By wangxuan
     */
    @Override
    public Boolean isBrandExistence(Map<String, Object> para) {

        logger.info("start isBrandExistence(),para:" + para.toString());

        Boolean flag = false;

        // 判断门店类型，必须传入门店类型
        String shopType = para.get("shopType") + "";
        if (!StringUtils.isNotEmpty(shopType)) {
            throw new BleException(
                    ComErrorCodeConstants.ErrorCode.BRAND_SHOPTYPE_IS_NULL.getErrorCode(),
                    ComErrorCodeConstants.ErrorCode.BRAND_SHOPTYPE_IS_NULL.getMemo());
        }

        // 判断，传入的门店品牌名称和门店品牌编码不能为空
        String brandName = para.get("brandName") + "";
        String brandSid = para.get("brandSid") + "";

        // 判重，同一门店类型下的门店品牌名称和门店品牌编码都不能重复

        // 门店品牌名称
        if (StringUtils.isNotEmpty(brandName)) {
            Map<String, Object> paraBrandName = new HashMap<String, Object>();
            paraBrandName.put("brandName", brandName);
            paraBrandName.put("shopType", Integer.parseInt(shopType));
            paraBrandName.put("status", Constants.PUBLIC_0);
            paraBrandName.put("brandType", Constants.PUBLIC_1);
            List<PcmBrand> brandNameList = brandMapper.selectListByParam(paraBrandName);
            if (!brandNameList.isEmpty()) {
                flag = true;
                throw new BleException(ErrorCode.BRAND_BRANDNAEM_EXIST.getErrorCode(),
                        ErrorCode.BRAND_BRANDNAEM_EXIST.getMemo());
            }
        }

        // 门店品牌编码
        if (StringUtils.isNotEmpty(brandSid)) {
            Map<String, Object> paraBrandSid = new HashMap<String, Object>();
            paraBrandSid.put("brandSid", brandSid);
            paraBrandSid.put("shopType", Integer.parseInt(shopType));
            paraBrandSid.put("status", Constants.PUBLIC_0);
            paraBrandSid.put("brandType", Constants.PUBLIC_1);
            List<PcmBrand> brandSidList = brandMapper.selectListByParam(paraBrandSid);
            if (!brandSidList.isEmpty()) {
                flag = true;
                throw new BleException(ErrorCode.BRAND_BRANDSID_EXIST.getErrorCode(),
                        ErrorCode.BRAND_BRANDSID_EXIST.getMemo());
            }
        }

        logger.info("end isBrandExistence(),reutrn:" + flag);
        return flag;

    }

    /**
     * 门店品牌判重(门店与门店品牌信息上传)
     *
     * @param para
     * @return List<PcmShopBrandResultDto>
     * @Methods Name isShopBrandExistence
     * @Create In 2015-11-18 By wangxuan
     */
    @Override
    public List<PcmShopBrandResultDto> isShopBrandExistence(Map<String, Object> para) {

        logger.info("start isShopBrandExistence(),para:" + para.toString());

        // 判断，传入的门店品牌编码和门店编码不能为空
        String shopCode = para.get("shopCode") + "";
        String brandSid = para.get("brandSid") + "";

        List<PcmShopBrandResultDto> list = new ArrayList<PcmShopBrandResultDto>();
        if (StringUtils.isNotEmpty(shopCode)) {
            if (StringUtils.isNotEmpty(brandSid)) {
                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("shopCode", shopCode);
                paramMap.put("brandSid", brandSid);
                list = shopBrandRelationMapper.getListShopBrandFromRelation(paramMap);
            }
        }

        logger.info("end isShopBrandExistence(),return:" + list);
        return list;

    }

    /**
     * 生成品牌编码(废弃)
     *
     * @param sid
     * @return String
     * @Methods Name generateBrandCode
     * @Create In 2015-9-15 By wangxuan
     */
    @Deprecated
    @Override
    public String generateBrandCode(String sid) {

        logger.info("start generateBrandCode(),para:" + sid.toString());

        StringBuffer brandCode = new StringBuffer();

        brandCode.append(Constants.PCMBRANDGROUP_BRANDSID_START);

        sid = sid.trim();
        Integer i = Constants.PCMBRAND_BRANDSID_DIGIT - 1 - sid.length();
        while (i > 0) {
            brandCode.append("0");
            i--;
        }

        brandCode.append(sid);

        logger.info("end generateBrandCode()");
        return brandCode.toString();
    }

    /**
     * 生成品牌编码
     *
     * @param dto
     * @return String
     * @Methods Name generateBrandCode
     * @Create In 2015-12-14 By wangxuan
     */
    @Override
    public String generateBrandCode(GenerateBrandCodeDto dto) {

        logger.info("start generateBrandCode(),param:" + dto.toString());

        String brandCode = "";
        StringBuffer sb = new StringBuffer();

        Integer brandType = dto.getBrandType();
        if (brandType != null) {

            GenerateBrandCodeDto queryDto = new GenerateBrandCodeDto();
            // 集团品牌
            if (brandType == Constants.PCMBRAND_TYPE_BRANDGROUP) {
                queryDto.setBrandType(Constants.PCMBRAND_TYPE_BRANDGROUP);
                queryDto.setShopType(null);
                queryDto.setCodeStart(Constants.PCMBRANDGROUP_BRANDSID_START);
                // 品牌编码开头
                sb.append(Constants.PCMBRANDGROUP_BRANDSID_START);
            }

            // 门店品牌
            if (brandType == Constants.PCMBRAND_TYPE_BRAND) {
                queryDto.setBrandType(Constants.PCMBRAND_TYPE_BRAND);
                Integer shopType = dto.getShopType();
                if (shopType != null) {
                    queryDto.setShopType(shopType);
                    // 品牌编码开头
                    if (shopType == Constants.PCMBRAND_SHOP_TYPE_BEIJING) {
                        queryDto.setCodeStart(Constants.PCMBRAND_BRANDSID_BEIJING_START);
                        sb.append(Constants.PCMBRAND_BRANDSID_BEIJING_START);
                    }
                    if (shopType == Constants.PCMBRAND_SHOP_TYPE_OTHER) {
                        queryDto.setCodeStart(Constants.PCMBRAND_BRANDSID_OTHER_START);
                        sb.append(Constants.PCMBRAND_BRANDSID_OTHER_START);
                    }
                    if (shopType == Constants.PCMBRAND_SHOP_TYPE_EBUSINESS) {
                        queryDto.setCodeStart(Constants.PCMBRAND_BRANDSID_EBUSINESS_START);
                        sb.append(Constants.PCMBRAND_BRANDSID_EBUSINESS_START);
                    }
                }
            }

            Map<String, Object> map = brandMapper.selectMaxSidByParam(queryDto);
            if (map == null) {
                Integer i = Constants.PCMBRAND_BRANDSID_DIGIT - 1;
                for (int j = 1; j <= i; j++) {
                    if (j == i) {
                        sb.append("1");
                    } else {
                        sb.append("0");
                    }
                }
                brandCode = sb.toString();
            }

            if (map != null) {
                String tempStr = map.get("maxSid") + "";
                String tempStr1 = sb.append(tempStr.substring(1).trim()).toString();
                brandCode = (Integer.parseInt(tempStr1) + 1) + "";
            }

        }
        logger.info("end generateBrandCode(),return:" + brandCode);
        return brandCode;
    }
}
