package com.wangfj.product.brand.service.impl;

import com.wangfj.core.constants.ComErrorCodeConstants;
import com.wangfj.core.constants.ComErrorCodeConstants.ErrorCode;
import com.wangfj.core.framework.base.page.Page;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.core.utils.DateUtil;
import com.wangfj.core.utils.StringUtils;
import com.wangfj.product.brand.domain.entity.PcmBrand;
import com.wangfj.product.brand.domain.entity.PcmShopBrandRelation;
import com.wangfj.product.brand.domain.vo.*;
import com.wangfj.product.brand.persistence.PcmBrandMapper;
import com.wangfj.product.brand.persistence.PcmShopBrandRelationMapper;
import com.wangfj.product.brand.service.intf.IPcmBrandCommonService;
import com.wangfj.product.brand.service.intf.IPcmBrandService;
import com.wangfj.product.maindata.persistence.PcmShoppeProductMapper;
import com.wangfj.product.organization.domain.entity.PcmOrganization;
import com.wangfj.product.organization.persistence.PcmOrganizationMapper;
import com.wangfj.util.Constants;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * 品牌的管理
 *
 * @Class Name PcmBrandServiceImpl
 * @Author wuxiong
 * @Create In 2015年7月13日
 */
@Service
public class PcmBrandServiceImpl implements IPcmBrandService {

    private static final Logger logger = LoggerFactory.getLogger(PcmBrandServiceImpl.class);

    @Autowired
    private IPcmBrandCommonService brandCommonService;

    @Autowired
    private PcmBrandMapper brandMapper;

    @Autowired
    private PcmOrganizationMapper organizationMapper;

    @Autowired
    private PcmShopBrandRelationMapper shopBrandRelationMapper;

    @Autowired
    private PcmShoppeProductMapper shoppeProductMapper;

    /**
     * 添加品牌
     *
     * @param brand
     * @return Integer
     * @Methods Name addBrand
     * @Create In 2015-8-12 By wangx
     */
    @Override
    @Transactional
    public Integer addBrand(PcmBrand brand) {

        logger.info("start addBrand(),para:" + brand.toString());

        // 返回标记
        Integer flag = Constants.PUBLIC_1;

        Integer brandType = brand.getBrandType();
        // 判断添加的品牌类型（集团品牌、门店品牌）
        if (brandType != null) {

            // 添加集团品牌
            if (brandType == Constants.PCMBRAND_TYPE_BRANDGROUP) {
                // 判重
                Map<String, Object> paraBrandGroup = new HashMap<String, Object>();
                paraBrandGroup.put("brandName", brand.getBrandName());
                paraBrandGroup.put("brandNameSecond", brand.getBrandNameSecond());
                paraBrandGroup.put("brandNameEn", brand.getBrandNameEn());
                // 名称判重
                if (!brandCommonService.isBrandGroupExistence(paraBrandGroup)) {
                    Integer addResult = brandMapper.insertSelective(brand);
                    if (addResult.equals(Constants.PUBLIC_0)) {
                        flag = Constants.PUBLIC_0;
                    }

                    // 自动生成品牌编码
                    GenerateBrandCodeDto codeDto = new GenerateBrandCodeDto();
                    codeDto.setBrandType(Constants.PCMBRAND_TYPE_BRANDGROUP);
                    String brandCode = brandCommonService.generateBrandCode(codeDto);
                    brand.setBrandSid(brandCode);
                    // 编码判重
                    paraBrandGroup.clear();
                    paraBrandGroup.put("brandSid", brandCode);
                    if (!brandCommonService.isBrandGroupExistence(paraBrandGroup)) {
                        Integer updateResult = brandMapper.updateByPrimaryKeySelective(brand);
                        if (updateResult.equals(Constants.PUBLIC_0)) {
                            flag = Constants.PUBLIC_0;
                        }
                    }
                }
            }

            // 添加门店品牌
            if (brandType == Constants.PCMBRAND_TYPE_BRAND) {
                // 判重
                Map<String, Object> paraBrand = new HashMap<String, Object>();
                // 名称判重
                paraBrand.put("shopType", brand.getShopType());
                paraBrand.put("brandName", brand.getBrandName());
                if (!brandCommonService.isBrandExistence(paraBrand)) {

                    Integer addResult = brandMapper.insertSelective(brand);

                    if (addResult.equals(Constants.PUBLIC_0)) {
                        flag = Constants.PUBLIC_0;
                    }

                    // 自动生成品牌编码
                    GenerateBrandCodeDto codeDto = new GenerateBrandCodeDto();
                    codeDto.setBrandType(Constants.PCMBRAND_TYPE_BRAND);
                    codeDto.setShopType(brand.getShopType());
                    String brandCode = brandCommonService.generateBrandCode(codeDto);
                    brand.setBrandSid(brandCode);

                    // 编码判重
                    paraBrand.clear();
                    paraBrand.put("shopType", brand.getShopType());
                    paraBrand.put("brandSid", brandCode);
                    if (!brandCommonService.isBrandExistence(paraBrand)) {
                        Integer updateResult = brandMapper.updateByPrimaryKeySelective(brand);

                        if (updateResult.equals(Constants.PUBLIC_0)) {
                            flag = Constants.PUBLIC_0;
                        }
                        // 判断是否是电商品牌，电商品牌需要插入门店与门店品牌的关系（电商是特殊的门店）
                        if (brand.getShopType() == Constants.PCMBRAND_SHOP_TYPE_EBUSINESS) {
                            paraBrand.clear();
                            paraBrand.put("organizationCode",
                                    Constants.PCMORGANIZATION_E_STORE_CODE);
                            paraBrand.put("organizationStatus", 0);
                            paraBrand.put("organizationType", 3);
                            List<PcmOrganization> list = organizationMapper
                                    .selectListByParam(paraBrand);
                            if (list != null && list.size() == 1) {
                                PcmShopBrandRelation shopBrand = new PcmShopBrandRelation();
                                shopBrand.setBrandSid(brand.getSid() + "");
                                shopBrand.setShopSid(list.get(0).getSid() + "");
                                shopBrand.setStatus(Constants.PUBLIC_0);
                                shopBrandRelationMapper.insertSelective(shopBrand);
                            }
                        }

                    } else {
                        logger.error("门店品牌编码已经存在。");
                        throw new BleException(
                                ComErrorCodeConstants.ErrorCode.BRAND_BRANDSID_EXIST.getErrorCode(),
                                ComErrorCodeConstants.ErrorCode.BRAND_BRANDSID_EXIST.getMemo());
                    }

                } else {
                    logger.error("门店品牌名称已经存在。");
                    throw new BleException(
                            ComErrorCodeConstants.ErrorCode.BRAND_BRANDNAEM_EXIST.getErrorCode(),
                            ComErrorCodeConstants.ErrorCode.BRAND_BRANDNAEM_EXIST.getMemo());
                }
            }

        }

        logger.info("end addBrand()");
        return flag;
    }

    /**
     * 修改集团品牌
     *
     * @param brand
     * @return Integer
     * @Methods Name updateBrandGroup
     * @Create In 2015-8-13 By wangx
     */
    @Transactional
    @Override
    public Integer updateBrandGroup(PcmBrand brand) {

        logger.info("start updateBrandGroup(),para:" + brand.toString());

        Integer flag = Constants.PUBLIC_0;
        // 根据Sid修改集团品牌，即集团品牌的Sid不能为空
        Long sid = brand.getSid();
        if (sid != null) {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("sid", brand.getSid());
            paramMap.put("brandType", Constants.PUBLIC_0);
            paramMap.put("status", Constants.PUBLIC_0);
            List<PcmBrand> brandList = brandMapper.selectListByParam(paramMap);
            if (brandList != null && !brandList.isEmpty()) {

                // 不让修改集团品牌名称
                String brandName = brand.getBrandName();
                if (StringUtils.isNotEmpty(brandName)) {
                    if (!brandName.equals(brandList.get(0).getBrandName())) {
                        logger.error("不许修改集团品牌名称");
                        throw new BleException(
                                ErrorCode.BRANDGROUP_BRANDNAEM_MODIFY.getErrorCode(),
                                ErrorCode.BRANDGROUP_BRANDNAEM_MODIFY.getMemo());
                    }
                }

                paramMap.clear();
                String brandNameSecond = brand.getBrandNameSecond();
                if (StringUtils.isNotEmpty(brandNameSecond)) {
                    if (!brandNameSecond.equals(brandList.get(0).getBrandNameSecond())) {
                        paramMap.put("brandNameSecond", brandNameSecond);
                    }
                }
                String brandNameEn = brand.getBrandNameEn();
                if (StringUtils.isNotEmpty(brandNameEn)) {
                    if (!brandNameEn.equals(brandList.get(0).getBrandNameEn())) {
                        paramMap.put("brandNameEn", brandNameEn);
                    }
                }
                // 名称判重
                if (!brandCommonService.isBrandGroupExistence(paramMap)) {
                    brand.setOptUpdateTime(new Date());
                    brand.setSid(brandList.get(0).getSid());
                    flag = brandMapper.updateByPrimaryKeySelective(brand);
                }
            } else {
                logger.error("修改的集团品牌不存在");
                throw new BleException(
                        ComErrorCodeConstants.ErrorCode.BRANDGROUP_NOT_EXIST.getErrorCode(),
                        ComErrorCodeConstants.ErrorCode.BRANDGROUP_NOT_EXIST.getMemo());
            }

        }
        logger.info("end updateBrandGroup()");

        return flag;
    }

    /**
     * 修改门店品牌
     *
     * @param brand
     * @return Integer
     * @Methods Name updatePcmBrand
     * @Create In 2015年7月30日 By wangx
     */
    @Transactional
    @Override
    public Integer updatePcmBrand(PcmBrand brand) {

        logger.info("start updatePcmBrand(),para:" + brand.toString());

        Integer flag = Constants.PUBLIC_0;

        if (brand.getSid() == null) {
            logger.error("修改门店品牌时没有传入Sid");
            throw new BleException(ErrorCode.BRAND_SID_IS_NULL.getErrorCode(),
                    ErrorCode.BRAND_SID_IS_NULL.getMemo());
        }

        if (brand.getShopType() == null) {
            throw new BleException(ErrorCode.BRAND_SHOPTYPE_IS_NULL.getErrorCode(),
                    ErrorCode.BRAND_SHOPTYPE_IS_NULL.getMemo());
        }

        // 判断修改的门店品牌是否存在
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sid", brand.getSid());
        paramMap.put("brandType", Constants.PUBLIC_1);
        paramMap.put("status", Constants.PUBLIC_0);
        List<PcmBrand> brandList = brandMapper.selectListByParam(paramMap);

        if (brandList != null && brandList.size() == 1) {

            // 不让修改门店品牌编码
            String brandSid = brandList.get(0).getBrandSid();
            if (brand.getBrandSid() != null && !brand.getBrandSid().equals(brandSid)) {
                logger.error("不许修改门店品牌编码");
                throw new BleException(
                        ComErrorCodeConstants.ErrorCode.BRAND_BRANDSID_MODIFY.getErrorCode(),
                        ComErrorCodeConstants.ErrorCode.BRAND_BRANDSID_MODIFY.getMemo());
            }

            // 不让修改门店品牌名称
            String brandName = brandList.get(0).getBrandName();
            if (brand.getBrandName() != null && !brand.getBrandName().equals(brandName)) {
                logger.error("不许修改门店品牌名称");
                throw new BleException(
                        ComErrorCodeConstants.ErrorCode.BRAND_BRANDNAEM_MODIFY.getErrorCode(),
                        ComErrorCodeConstants.ErrorCode.BRAND_BRANDNAEM_MODIFY.getMemo());
            }

            brand.setOptUpdateTime(new Date());

            brand.setSid(brandList.get(0).getSid());
            flag = brandMapper.updateByPrimaryKeySelective(brand);

        } else {
            logger.error("修改的门店品牌不存在");
            throw new BleException(ComErrorCodeConstants.ErrorCode.BRAND_NOT_EXIST.getErrorCode(),
                    ComErrorCodeConstants.ErrorCode.BRAND_NOT_EXIST.getMemo());
        }

        logger.info("end updatePcmBrand()");
        return flag;

    }

    /**
     * 添加/修改门店品牌与集团品牌的关系
     *
     * @param brandDto
     * @return Integer
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @Methods Name updateRelation
     * @Create In 2015-8-12 By wangx
     */
    @Override
    @Transactional
    public Integer updateRelation(PcmBrandDto brandDto) throws IllegalAccessException,
            InvocationTargetException {

        logger.info("start updateRelation(),para:" + brandDto.toString());

        // 标记位，标记操作是否成功
        Integer flag = Constants.PUBLIC_0;

        // 查询门店品牌
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.clear();
        paramMap.put("sid", brandDto.getSid());
        paramMap.put("status", Constants.PUBLIC_0);
        paramMap.put("brandType", Constants.PUBLIC_1);
        List<PcmBrand> brandList = brandMapper.selectListByParam(paramMap);
        if (brandList != null && brandList.size() == 1) {
            // 判断该门店品牌下是否存在专柜商品
            paramMap.clear();
            paramMap.put("brandSid", brandList.get(0).getSid());
            Integer count = shoppeProductMapper.getCountShoppeProductByBrandSid(paramMap);
            if (count > 0) {
                // 存在，不让修改关系
                logger.error("该门店品牌下存在专柜商品，不让修改关系");
                throw new BleException(
                        ErrorCode.BRANDRELATION_BRAND_HAS_SHOPPEPRODUCT.getErrorCode(),
                        ErrorCode.BRANDRELATION_BRAND_HAS_SHOPPEPRODUCT.getMemo());
            }

            // 查询集团品牌
            paramMap.clear();
            paramMap.put("sid", brandDto.getParentSid());
            paramMap.put("status", Constants.PUBLIC_0);
            paramMap.put("brandType", Constants.PUBLIC_0);
            List<PcmBrand> brandGroupList = brandMapper.selectListByParam(paramMap);
            if (brandGroupList != null && brandGroupList.size() == 1) {

                brandList.get(0).setParentSid(brandDto.getParentSid());
                // 修改门店品牌表的集团品牌关系
                flag = brandMapper.updateByPrimaryKeySelective(brandList.get(0));

            } else {
                logger.error("维护门店品牌与集团品牌的关系时需要维护的集团品牌不存在");
                throw new BleException(ErrorCode.BRANDGROUP_NOT_EXIST.getErrorCode(),
                        ErrorCode.BRANDGROUP_NOT_EXIST.getMemo());
            }

        } else {
            logger.error("维护门店品牌与集团品牌的关系时需要维护的门店品牌不存在");
            throw new BleException(ErrorCode.BRAND_NOT_EXIST.getErrorCode(),
                    ErrorCode.BRAND_NOT_EXIST.getMemo());
        }
        logger.info("end updateRelation()");
        return flag;
    }

    /**
     * 批量添加门店品牌与集团品牌的关系
     *
     * @param brandDtoList
     * @return Integer
     * @Methods Name addRelationList
     * @Create In 2015-9-14 By wangxuan
     */
    @Override
    @Transactional
    public Integer addRelationList(List<PcmBrandDto> brandDtoList) {

        logger.info("start addRelationList(),para:" + brandDtoList.toString());

        Integer flag = Constants.PUBLIC_0;

        Long parentSid = brandDtoList.get(0).getParentSid();

        // // 查询出已经关联集团品牌下的所有门店品牌
        // Map<String, Object> paramBrand = new HashMap<String, Object>();
        // paramBrand.put("parentSid", parentSid);
        // List<PcmBrand> brandList = brandMapper.selectListByParam(paramBrand);
        //
        // // 判断集团品牌下是否存在门店品牌
        // if (brandList != null && !brandList.isEmpty()) {
        // // 存在门店品牌
        // List<Long> sidDelList = new ArrayList<Long>();
        // for (PcmBrand brand : brandList) {
        // sidDelList.add(brand.getSid());
        // }
        // // 批量将集团品牌下的门店品牌解除关系
        // Map<String, Object> delRelationMap = new HashMap<String, Object>();
        // delRelationMap.put("parentSid", new Long(Constants.PUBLIC_0));
        // delRelationMap.put("sidList", sidDelList);
        // brandMapper.updateRelationList(delRelationMap);
        // }

        // 批量添加门店品牌与集团品牌的关系
        List<Long> sidAddList = new ArrayList<Long>();

        for (PcmBrandDto brandDto : brandDtoList) {
            sidAddList.add(brandDto.getSid());
        }

        Map<String, Object> addRelationMap = new HashMap<String, Object>();
        addRelationMap.put("parentSid", parentSid);
        addRelationMap.put("sidList", sidAddList);

        Integer addResult = brandMapper.updateRelationList(addRelationMap);

        if (addResult.equals(sidAddList.size())) {
            // 标记符，批量添加成功
            flag = Constants.PUBLIC_1;
        }

        logger.info("end addRelationList()");
        return flag;
    }

    /**
     * 删除门店品牌与集团品牌的关系
     *
     * @param paramMap
     * @return Integer
     * @Methods Name deleteRelation
     * @Create In 2015-8-18 By wangx
     */
    @Override
    @Transactional
    public Integer deleteRelation(Map<String, Object> paramMap) {

        logger.info("start deleteRelation(),para:" + paramMap.toString());

        // 标记位，标记操作是否成功
        Integer flag = Constants.PUBLIC_0;

        String sid = paramMap.get("sid") + "";
        if (!StringUtils.isNotEmpty(sid)) {
            throw new BleException(
                    ComErrorCodeConstants.ErrorCode.BRAND_SID_IS_NULL.getErrorCode(),
                    ComErrorCodeConstants.ErrorCode.BRAND_SID_IS_NULL.getMemo());
        }

        // 查询门店品牌
        Map<String, Object> para = new HashMap<String, Object>();
        para.clear();
        para.put("sid", sid);
        para.put("status", Constants.PUBLIC_0);
        para.put("brandType", Constants.PUBLIC_1);
        List<PcmBrand> brandList = brandMapper.selectListByParam(para);
        if (brandList != null && !brandList.isEmpty()) {

            // 判断该门店品牌下是否存在专柜商品
            para.clear();
            para.put("brandSid", brandList.get(0).getSid());
            Integer count = shoppeProductMapper.getCountShoppeProductByBrandSid(para);
            if (count > 0) {
                // 存在，不让修改关系
                logger.error("该门店品牌下存在专柜商品，不让修改关系");
                throw new BleException(
                        ErrorCode.BRANDRELATION_BRAND_HAS_SHOPPEPRODUCT.getErrorCode(),
                        ErrorCode.BRANDRELATION_BRAND_HAS_SHOPPEPRODUCT.getMemo());
            }

            para.clear();
            para.put("sid", sid);
            para.put("parentSid", new Long(Constants.PUBLIC_0));
            // 删除门店品牌与集团品牌的关系
            flag = brandMapper.deleteRelation(para);

        } else {
            logger.error("删除门店品牌与集团品牌的关系时需要维护的门店品牌不存在");
            throw new BleException(ErrorCode.BRAND_NOT_EXIST.getErrorCode(),
                    ErrorCode.BRAND_NOT_EXIST.getMemo());
        }
        logger.info("end deleteRelation()");
        return flag;
    }

    /**
     * 分页查询品牌(模糊)
     *
     * @param pageDto
     * @return Page<PcmBrandGroupDto>
     * @Methods Name findPageBrand
     * @Create In 2015-8-13 By wangx
     */
    @Override
    public Page<PcmBrandDto> findPageBrand(SelectPcmBrandPageDto pageDto) {

        logger.info("start findPageBrand(),para:" + pageDto.toString());

        Page<PcmBrandDto> page = new Page<PcmBrandDto>();
        page.setCurrentPage(pageDto.getCurrentPage());
        page.setPageSize(pageDto.getPageSize());

        // 默认查询有效
        if (pageDto.getStatus() == null) {
            pageDto.setStatus(Constants.PUBLIC_0);
        }

        // 查询总记录数
        Long count = brandMapper.getPageCountByPara(pageDto);
        page.setCount(count);

        // 分页查询
        pageDto.setStart(page.getStart());
        pageDto.setLimit(page.getLimit());
        List<PcmBrandDto> brandList = brandMapper.selectPageByPara(pageDto);

        if (!brandList.isEmpty()) {

            for (int i = 0; i < brandList.size(); i++) {

                PcmBrandDto brandDto = brandList.get(i);
                String optUpdateTimeStr = DateUtil.formatToStr(brandDto.getOptUpdateTimes(),
                        "yyyy-MM-dd");
                brandDto.setOptUpdateTimeStr(optUpdateTimeStr);
            }

            page.setList(brandList);
        }

        logger.info("end findPageBrand()");
        return page;

    }

    /**
     * 分页查询门店品牌及其集团品牌
     *
     * @param paramMap
     * @return Page<PcmBrandDto>
     * @Methods Name findPageBrandAndBrandGroup
     * @Create In 2015-9-8 By wangxuan
     */
    @Override
    public Page<PcmBrandDto> findPageBrandAndBrandGroup(Map<String, Object> paramMap) {

        logger.info("start findPageBrandAndBrandGroup(),para:" + paramMap.toString());

        Page<PcmBrandDto> page = new Page<PcmBrandDto>();
        page.setCurrentPage(Integer.parseInt(paramMap.get("currentPage") + ""));
        page.setPageSize(Integer.parseInt(paramMap.get("pageSize") + ""));

        // 默认查询有效
        if (!StringUtils.isNotEmpty(paramMap.get("status") + "")) {
            paramMap.put("status", Constants.PUBLIC_0);
        }
        if (!StringUtils.isNotEmpty(paramMap.get("brandGroupStatus") + "")) {
            paramMap.put("brandGroupStatus", Constants.PUBLIC_0);
        }

        // 查询总记录数
        Long count = brandMapper.getPageCountBrandAndBrandGroup(paramMap);
        page.setCount(count);

        // 分页查询
        paramMap.put("start", page.getStart());
        paramMap.put("limit", page.getLimit());
        List<PcmBrandDto> brandDtoList = brandMapper.selectPageBrandAndBrandGroup(paramMap);

        if (!brandDtoList.isEmpty()) {

            for (int i = 0; i < brandDtoList.size(); i++) {
                String optUpdateTimeStr = DateUtil.formatToStr(brandDtoList.get(i)
                        .getOptUpdateTimes(), "yyyy-MM-dd");
                brandDtoList.get(i).setOptUpdateTimeStr(optUpdateTimeStr);
            }

            page.setList(brandDtoList);
        }

        logger.info("end findPageBrandAndBrandGroup()");
        return page;

    }

    /**
     * 查询品牌（模糊，非分页）
     *
     * @param selectDto
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException List<PcmBrandDto>
     * @Methods Name findListBrand
     * @Create In 2015-8-18 By wangx
     */
    @Override
    public List<PcmBrandDto> findListBrand(SelectPcmBrandPageDto selectDto)
            throws IllegalAccessException, InvocationTargetException {

        logger.info("start findListBrand(),para:" + selectDto.toString());

        // 默认查询有效
        if (selectDto.getStatus() == null) {
            selectDto.setStatus(Constants.PUBLIC_0);
        }
        // 查询
        List<PcmBrand> brandList = brandMapper.selectListByParaSelective(selectDto);

        List<PcmBrandDto> brandDtoList = new ArrayList<PcmBrandDto>();
        if (brandList != null && !brandList.isEmpty()) {
            for (int i = 0; i < brandList.size(); i++) {
                PcmBrandDto brandDto = new PcmBrandDto();
                BeanUtils.copyProperties(brandDto, brandList.get(i));
                brandDto.setOptUpdateTimes(brandList.get(i).getOptUpdateTime());
                String optUpdateTimeStr = DateUtil.formatToStr(brandList.get(i).getOptUpdateTime(),
                        "yyyy-MM-dd");
                brandDto.setOptUpdateTimeStr(optUpdateTimeStr);

                brandDtoList.add(brandDto);
            }
        }
        logger.info("end findListBrand(),return:" + brandDtoList.toString());
        return brandDtoList;
    }

    /**
     * 查询品牌部分信息（模糊）
     *
     * @param selectDto
     * @return List<PcmBrandPartDto>
     * @Methods Name findListBrandPartInfo
     * @Create In 2016-02-22 By wangxuan
     */
    @Override
    public Page<PcmBrandPartDto> findListBrandPartInfo(SelectPcmBrandPageDto selectDto) {

        logger.info("start findListBrandPartInfo(),param:" + selectDto.toString());
        Page<PcmBrandPartDto> page = new Page<PcmBrandPartDto>();

        // 默认查询有效
        if (selectDto.getStatus() == null) {
            selectDto.setStatus(Constants.PUBLIC_0);
        }
        Integer pageSize = selectDto.getPageSize();
        if (pageSize != null) {
            page.setPageSize(pageSize);
        }
        Integer currentPage = selectDto.getCurrentPage();
        if (currentPage != null) {
            page.setCurrentPage(currentPage);
        }
        Integer count = brandMapper.selectCountPartInfoByParam(selectDto);
        page.setCount(count);

        selectDto.setStart(page.getStart());
        selectDto.setLimit(page.getLimit());
        // 查询
        List<PcmBrandPartDto> dtoList = brandMapper.selectListPartInfoByParam(selectDto);
        page.setList(dtoList);

        logger.info("end findListBrandPartInfo(),return:" + dtoList.toString());
        return page;
    }

    /**
     * 移动工作台调用主数据获取品牌信息
     *
     * @param para
     * @return List<PadBrandResultDto>
     * @Methods Name getPageBrandForPad
     * @Create In 2015-8-26 By wangxuan
     */
    @Override
    public List<PadBrandResultDto> getPageBrandForPad(Map<String, Object> para) {

        logger.info("start getPageBrandForPad(),para:" + para.toString());

        String storeType = para.get("storeType") + "";
        String code = para.get("code") + "";

        Map<String, Object> paramMap = new HashMap<String, Object>();

        // 默认有效
        String status = para.get("status") + "";
        if (StringUtils.isNotEmpty(status)) {
            paramMap.put("status", Constants.PUBLIC_0);
        }

        if (StringUtils.isNotEmpty(code)) {
            paramMap.put("brandSid", code);
        }

        List<PadBrandResultDto> brandResultDtoList = new ArrayList<PadBrandResultDto>();
        if (StringUtils.isNotEmpty(storeType)) {

            if (storeType.equals(Constants.PCMBRAND_PAD_STORE_TYPE_ALL)) {
                paramMap.put("brandType", Constants.PUBLIC_0);
                List<PcmBrand> brandGroupList = brandMapper.selectListByParam(paramMap);
                if (brandGroupList != null && !brandGroupList.isEmpty()) {
                    for (PcmBrand pcmBrand : brandGroupList) {
                        PadBrandResultDto brandResultDto = new PadBrandResultDto();
                        brandResultDto.setSuperCode(pcmBrand.getBrandSid());
                        brandResultDto.setCode(pcmBrand.getBrandSid());
                        brandResultDto.setName(pcmBrand.getBrandName());
                        brandResultDto.setName2(pcmBrand.getBrandNameSecond());
                        brandResultDto.setStoreType(storeType);
                        brandResultDtoList.add(brandResultDto);
                    }
                }
            }

            if (storeType.equals(Constants.PCMBRAND_PAD_STORE_TYPE_EBUSINESS)) {
                paramMap.put("brandType", Constants.PUBLIC_1);
                paramMap.put("shopType", Constants.PCMBRAND_SHOP_TYPE_EBUSINESS);
                brandResultDtoList = brandMapper.getPageBrandForPad(paramMap);
                if (brandResultDtoList != null && !brandResultDtoList.isEmpty()) {
                    for (PadBrandResultDto padBrandResultDto : brandResultDtoList) {
                        padBrandResultDto.setStoreType(storeType);
                    }
                }
            }

            if (storeType.equals(Constants.PCMBRAND_PAD_STORE_TYPE_BEIJING)) {
                paramMap.put("brandType", Constants.PUBLIC_1);
                paramMap.put("shopType", Constants.PCMBRAND_SHOP_TYPE_BEIJING);
                brandResultDtoList = brandMapper.getPageBrandForPad(paramMap);
                if (brandResultDtoList != null && !brandResultDtoList.isEmpty()) {
                    for (PadBrandResultDto padBrandResultDto : brandResultDtoList) {
                        padBrandResultDto.setStoreType(storeType);
                    }
                }
            }

            if (storeType.equals(Constants.PCMBRAND_PAD_STORE_TYPE_OTHER)) {
                paramMap.put("brandType", Constants.PUBLIC_1);
                paramMap.put("shopType", Constants.PCMBRAND_SHOP_TYPE_OTHER);
                brandResultDtoList = brandMapper.getPageBrandForPad(paramMap);
                if (brandResultDtoList != null && !brandResultDtoList.isEmpty()) {
                    for (PadBrandResultDto padBrandResultDto : brandResultDtoList) {
                        padBrandResultDto.setStoreType(storeType);
                    }
                }
            }

        }

        logger.info("end getPageBrandForPad()");
        return brandResultDtoList;

    }

    /**
     * 下发门店品牌分页查找
     *
     * @param para
     * @return List<PushBrandDto>
     * @Methods Name pushPageBrandData
     * @Create In 2015-8-26 By wangxuan
     */
    @Override
    public List<PushBrandDto> pushPageBrandData(Map<String, Object> para) {

        logger.info("start pushPageBrandData(),para:" + para.toString());

        // 默认查询有效
        String status = para.get("status") + "";
        if (StringUtils.isNotEmpty(status)) {
            para.put("status", Constants.PUBLIC_0);
        }

        // 查询
        List<PushBrandDto> brandDtoList = brandMapper.pushPageBrandData(para);

        // 封装返回参数storeType
        for (PushBrandDto findBrandDto : brandDtoList) {

            Integer brandType = findBrandDto.getBrandType();
            if (brandType != null) {

                if (brandType.equals(Constants.PCMBRAND_TYPE_BRANDGROUP)) {
                    findBrandDto.setStoreType(Constants.PCMBRAND_ERP_STORE_TYPE_ALL);
                }

                if (brandType.equals(Constants.PCMBRAND_TYPE_BRAND)) {

                    Integer shopType = findBrandDto.getShopType();
                    if (shopType != null) {
                        if (shopType.equals(Constants.PCMBRAND_SHOP_TYPE_BEIJING)) {
                            findBrandDto.setStoreType(Constants.PCMBRAND_ERP_STORE_TYPE_BEIJING);

                        }
                        if (shopType.equals(Constants.PCMBRAND_SHOP_TYPE_EBUSINESS)) {
                            findBrandDto.setStoreType(Constants.PCMBRAND_ERP_STORE_TYPE_EBUSINESS);

                        }
                        if (shopType.equals(Constants.PCMBRAND_SHOP_TYPE_OTHER)) {
                            findBrandDto.setStoreType(Constants.PCMBRAND_ERP_STORE_TYPE_OTHER);

                        }
                    }

                }

            }

        }

        logger.info("end pushPageBrandData(),return:" + brandDtoList.toString());
        return brandDtoList;

    }

    /**
     * 根据sid查询下发给搜索的数据（修改品牌时）
     *
     * @param para
     * @return List<PushSearchBrandDto>
     * @Methods Name pushSearchBrandBySid
     * @Create In 2015-10-9 By wangxuan
     */
    @Override
    public List<PushSearchBrandDto> pushSearchBrandBySid(Map<String, Object> para) {

        logger.info("start pushSearchBrandBySid(),para:" + para.toString());

        String sid = para.get("sid") + "";
        Map<String, Object> paraMap = new HashMap<String, Object>();
        if (StringUtils.isNotEmpty(sid)) {
            paraMap.put("sid", Long.parseLong(sid));
        }

        List<PushSearchBrandDto> dtoList = brandMapper.pushSearchBrandBySid(paraMap);

        logger.info("end pushSearchBrandBySid(),return:" + dtoList.toString());

        return dtoList;
    }

    /**
     * 搜索线上下发品牌信息
     *
     * @param paramMap
     * @return List<Map<String,Object>>
     * @Methods Name pushSearchOnlineBrandBySid
     * @Create In 2015-11-10 By wangxuan
     */
    @Override
    public List<Map<String, Object>> pushSearchOnlineBrandBySid(Map<String, Object> paramMap) {

        logger.info("start pushSearchOnlineBrandBySid(),param:" + paramMap.toString());

        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();

        List<Map<String, Object>> brandList = brandMapper.pushSearchOnlineBrandBySid(paramMap);
        if (brandList != null && !brandList.isEmpty()) {

            for (Map<String, Object> brandMap : brandList) {

                Map<String, Object> resultMap = new HashMap<String, Object>();
                resultMap.put("brandId", brandMap.get("brandId"));
                resultMap.put("brandName", brandMap.get("brandName"));
                resultMap.put("brandDesc", brandMap.get("brandDesc"));
                resultMap.put("brandLogo", brandMap.get("brandLogo"));
                resultMap.put("brandPicture", brandMap.get("brandPicture"));

                List<Object> list = new ArrayList<Object>();
                list.add(brandMap.get("brandNameSecond"));
                list.add(brandMap.get("spell"));
                list.add(brandMap.get("brandNameEn"));
                resultMap.put("brandAliases", list);

                resultList.add(resultMap);
            }

        }

        logger.info("end pushSearchOnlineBrandBySid(),return:" + resultList.toString());
        return resultList;
    }

    /**
     * 集团品牌下发给搜索
     *
     * @param paramMap
     * @return List<Map<String,Object>>
     * @Methods Name pushBrandGroupToSearch
     * @Create In 2016-03-21 By wangxuan
     */
    @Override
    public List<Map<String, Object>> pushBrandGroupToSearch(Map<String, Object> paramMap) {

        logger.info("start pushBrandGroupToSearch(),param:" + paramMap.toString());

        paramMap.put("status", Constants.PUBLIC_0);
        List<PcmBrand> brandList = brandMapper.selectListByParam(paramMap);

        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        if (brandList != null && !brandList.isEmpty()) {
            for (PcmBrand tempBrand : brandList) {
                Map<String, Object> resultMap = new HashMap<String, Object>();
                resultMap.put("sid", tempBrand.getSid() + "");
                resultMap.put("brandGroupCode", tempBrand.getBrandSid());
                resultMap.put("brandGroupName", tempBrand.getBrandName());
                resultList.add(resultMap);
            }
        }
        logger.info("end pushBrandGroupToSearch(),return:" + resultList.toString());
        return resultList;
    }

    /**
     * 门店品牌与门店号下发给搜索查询
     *
     * @param paramMap
     * @return
     */
    @Override
    public List<Map<String, Object>> pushShopBrandToSearch(Map<String, Object> paramMap) {
        logger.info("start com.wangfj.product.brand.service.impl.PcmBrandServiceImpl#pushShopBrandToSearch(),para:" + paramMap.toString());

        List<HashMap<String, Object>> hashMapList = brandMapper.pushShopBrandToSearch(paramMap);
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        if (hashMapList != null && hashMapList.size() > 0) {
            for (Map<String, Object> tempMap : hashMapList) {
                Map<String, Object> resultMap = new HashMap<String, Object>();
                resultMap.put("sid", tempMap.get("brandSid"));
                resultMap.put("brandGroupCode", tempMap.get("storeBrandCode"));
                resultMap.put("brandGroupName", tempMap.get("storeBrandName"));
                resultMap.put("storeCode", tempMap.get("storeCode"));
                resultList.add(resultMap);
            }
        }

        logger.info("end com.wangfj.product.brand.service.impl.PcmBrandServiceImpl#pushShopBrandToSearch(),return:" + resultList.toString());
        return resultList;
    }

    /**
     * 根据sid查询
     *
     * @param para
     * @return PcmBrand
     * @Methods Name findBrandBySid
     * @Create In 2015-8-26 By wangxuan
     */
    @Override

    public PcmBrand findBrandBySid(Map<String, Object> para) {

        logger.info("start findBrandBySid(),para:" + para.toString());

        // 默认查询有效
        String status = para.get("status") + "";
        if (StringUtils.isNotEmpty(status)) {
            para.put("status", Constants.PUBLIC_0);
        }

        // 查询
        Long sid = Long.parseLong(para.get("sid") + "");
        PcmBrand brand = brandMapper.selectByPrimaryKey(sid);

        logger.info("end findBrandBySid()");
        return brand;

    }

    /**
     * 多条件查询品牌（非模糊,非分页）
     *
     * @param paramMap
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException List<PcmBrandDto>
     * @Methods Name findListBrand
     * @Create In 2015-8-16 By wangx
     */
    @Override
    public List<PcmBrandDto> findListBrand(Map<String, Object> paramMap)
            throws IllegalAccessException, InvocationTargetException {

        logger.info("start findListBrand(),para:" + paramMap.toString());

        paramMap.put("status", Constants.PUBLIC_0);
        // 查询
        List<PcmBrand> brandList = brandMapper.selectListByParam(paramMap);

        List<PcmBrandDto> brandDtoList = new ArrayList<PcmBrandDto>();
        if (brandList != null && !brandList.isEmpty()) {
            for (int i = 0; i < brandList.size(); i++) {
                PcmBrandDto brandDto = new PcmBrandDto();
                BeanUtils.copyProperties(brandDto, brandList.get(i));
                brandDto.setOptUpdateTimes(brandList.get(i).getOptUpdateTime());
                String optUpdateTimeStr = DateUtil.formatToStr(brandList.get(i).getOptUpdateTime(),
                        "yyyy-MM-dd");
                brandDto.setOptUpdateTimeStr(optUpdateTimeStr);

                brandDtoList.add(brandDto);
            }
        }
        logger.info("end findListBrand(),return:" + brandDtoList.toString());
        return brandDtoList;
    }

    /**
     * 根据门店的sid和集团品牌的sid查询门店品牌
     *
     * @param paramMap
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException List<PcmBrandDto>
     * @Methods Name getListBrandByShopSidAndParentSid
     * @Create In 2015-9-21 By wangxuan
     */
    @Override
    public List<PcmBrandDto> getListBrandByShopSidAndParentSid(Map<String, Object> paramMap)
            throws IllegalAccessException, InvocationTargetException {

        logger.info("start getListBrandByShopSidAndParentSid(),para:" + paramMap.toString());

        paramMap.put("status", Constants.PUBLIC_0);
        paramMap.put("brandType", Constants.PUBLIC_1);
        // 查询
        List<PcmBrand> brandList = brandMapper.getListBrandFromShopBrandRelation(paramMap);

        List<PcmBrandDto> brandDtoList = new ArrayList<PcmBrandDto>();
        if (brandList != null && !brandList.isEmpty()) {

            for (int i = 0; i < brandList.size(); i++) {
                PcmBrandDto brandDto = new PcmBrandDto();
                BeanUtils.copyProperties(brandDto, brandList.get(i));
                brandDtoList.add(brandDto);
            }

        }

        logger.info("end getListBrandByShopSidAndParentSid(),return:" + brandDtoList.toString());
        return brandDtoList;

    }

    /**
     * 根据门店查询门店品牌
     */
    @Override
    public Page<PcmBrandDto> getPageBrandFromShopBrandRelation(PcmShopBrandDto dto) {

        logger.info("start getPageBrandFromShopBrandRelation(),para:" + dto.toString());

        Page<PcmBrandDto> page = new Page<PcmBrandDto>();

        Long count = brandMapper.getPageBrandCountFromShopBrandRelation(dto);

        Integer currentPage = dto.getCurrentPage();
        Integer pageSize = dto.getPageSize();
        if (currentPage != null) {
            page.setCurrentPage(currentPage);
        }
        if (pageSize != null) {
            page.setPageSize(dto.getPageSize());
        }
        page.setCount(count);

        // 查询
        dto.setStart(page.getStart());
        dto.setLimit(page.getLimit());
        List<PcmBrandDto> brandDtoList = brandMapper.getPageBrandFromShopBrandRelation(dto);
        page.setList(brandDtoList);

        logger.info("end getPageBrandFromShopBrandRelation(),return:" + brandDtoList.toString());
        return page;

    }

    /**
     * 根据某门店品牌查询其对应的集团品牌
     *
     * @param para
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException PcmBrandDto
     * @Methods Name findBrandGroupByParentSid
     * @Create In 2015-8-13 By wangx
     */
    @Override
    public PcmBrandDto findBrandGroupByParentSid(Map<String, Object> para)
            throws IllegalAccessException, InvocationTargetException {

        logger.info("start findBrandByParentSid(),para:" + para.toString());

        PcmBrandDto brandDto = new PcmBrandDto();

        if (para.get("sid") != null) {

            Map<String, Object> paraBrand = new HashMap<String, Object>();
            paraBrand.put("sid", para.get("sid"));
            paraBrand.put("brandType", Constants.PUBLIC_1);
            paraBrand.put("status", Constants.PUBLIC_0);
            List<PcmBrand> brandList = brandMapper.selectListByParam(paraBrand);

            if (brandList != null && !brandList.isEmpty()) {

                // 判断门店品牌是否有集团品牌关联，如果有关联就查询对应的集团品牌
                Long parentSid = brandList.get(0).getParentSid();
                if (parentSid != null) {
                    Map<String, Object> paraBrandGroup = new HashMap<String, Object>();
                    paraBrandGroup.put("sid", brandList.get(0).getParentSid());
                    paraBrandGroup.put("brandType", Constants.PUBLIC_0);
                    paraBrandGroup.put("status", Constants.PUBLIC_0);
                    List<PcmBrand> brandGroupList = brandMapper.selectListByParam(paraBrandGroup);

                    if (brandGroupList != null && brandGroupList.size() == 1) {

                        BeanUtils.copyProperties(brandDto, brandGroupList.get(0));
                        brandDto.setOptUpdateTimes(brandGroupList.get(0).getOptUpdateTime());

                    }
                }

            } else {
                throw new BleException(
                        ComErrorCodeConstants.ErrorCode.BRAND_NOT_EXIST.getErrorCode(),
                        ComErrorCodeConstants.ErrorCode.BRAND_NOT_EXIST.getMemo());
            }

        }

        logger.info("end findBrandByParentSid()");
        return brandDto;
    }

    /**
     * 删除门店品牌
     *
     * @param paraMap
     * @return Integer
     * @Methods Name deleteBrand
     * @Create In 2015-8-5 By wangx
     */
    @Override
    @Transactional
    public Integer deleteBrand(Map<String, Object> paraMap) {

        logger.info("start deleteBrand(),para:" + paraMap.toString());

        Integer flag = Constants.PUBLIC_0;

        Long sid = Long.parseLong(paraMap.get("sid") + "");
        if (sid != null) {

            Map<String, Object> paraBrand = new HashMap<String, Object>();
            paraBrand.put("sid", sid);
            paraBrand.put("brandType", Constants.PUBLIC_1);
            paraBrand.put("status", Constants.PUBLIC_0);
            List<PcmBrand> brandList = brandMapper.selectListByParam(paraBrand);
            if (brandList != null && !brandList.isEmpty()) {
                // 让门店品牌的有效字段失效
                brandList.get(0).setStatus(1);
                flag = brandMapper.updateByPrimaryKeySelective(brandList.get(0));
            }

        }

        logger.info("end deleteBrand()");
        return flag;

    }

    /**
     * 删除集团品牌
     *
     * @param paraMap
     * @return Integer
     * @Methods Name deleteBrandGroup
     * @Create In 2015-8-5 By wangx
     */
    @Override
    @Transactional
    public Integer deleteBrandGroup(Map<String, Object> paraMap) {

        logger.info("start deleteBrandGroup(),para:" + paraMap.toString());

        Integer flag = Constants.PUBLIC_1;
        Long sid = Long.parseLong(paraMap.get("sid") + "");
        if (sid != null) {

            Integer flagBrandGroup = Constants.PUBLIC_0;
            Map<String, Object> paramBrandGroup = new HashMap<String, Object>();
            paramBrandGroup.put("sid", sid);
            paramBrandGroup.put("brandType", Constants.PUBLIC_0);
            paramBrandGroup.put("status", Constants.PUBLIC_0);
            List<PcmBrand> brandGroupList = brandMapper.selectListByParam(paramBrandGroup);
            if (brandGroupList != null && !brandGroupList.isEmpty()) {
                // 让集团品牌的有效字段失效
                brandGroupList.get(0).setStatus(1);
                flagBrandGroup = brandMapper.updateByPrimaryKeySelective(brandGroupList.get(0));
                if (flagBrandGroup == 0) {
                    flag = Constants.PUBLIC_0;
                }

                Integer flagBrand = Constants.PUBLIC_0;
                if (flagBrandGroup > 0) {
                    Map<String, Object> paramBrand = new HashMap<String, Object>();
                    paramBrand.put("parentSid", sid);
                    paramBrand.put("brandType", Constants.PUBLIC_1);
                    paramBrand.put("status", Constants.PUBLIC_0);
                    List<PcmBrand> brandList = brandMapper.selectListByParam(paramBrand);
                    if (brandList != null && !brandList.isEmpty()) {
                        for (PcmBrand brand : brandList) {
                            // 让集团下的门店品牌有效字段失效
                            brand.setStatus(1);
                            flagBrand = brandMapper.updateByPrimaryKeySelective(brand);
                            if (flagBrand == 0) {
                                flag = Constants.PUBLIC_0;
                            }
                        }
                    }
                }

            }

        }

        logger.info("end deleteBrandGroup()");
        return flag;

    }

    /**
     * 根据skuSid和门店sid查询门店品牌
     *
     * @param paramMap
     * @return List<PcmBrand>
     * @Methods Name getListBrandByShopSidAndSkuSid
     * @Create In 2015-11-19 By wangxuan
     */
    @Override
    public List<PcmBrand> getListBrandByShopSidAndSkuSid(Map<String, Object> paramMap) {

        logger.info("start getListBrandByShopSidAndSkuSid,param:" + paramMap.toString());

        List<PcmBrand> list = brandMapper.selectBrandByShopSku(paramMap);

        logger.info("end getListBrandByShopSidAndSkuSid,return:" + list.toString());
        return list;
    }
}
