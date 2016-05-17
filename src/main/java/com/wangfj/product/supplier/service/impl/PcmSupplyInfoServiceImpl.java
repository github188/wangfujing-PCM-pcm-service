package com.wangfj.product.supplier.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangfj.core.constants.ComErrorCodeConstants;
import com.wangfj.core.framework.base.page.Page;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.core.utils.DateUtil;
import com.wangfj.core.utils.StringUtils;
import com.wangfj.product.organization.domain.entity.PcmOrganization;
import com.wangfj.product.organization.persistence.PcmOrganizationMapper;
import com.wangfj.product.supplier.domain.entity.PcmSupplyInfo;
import com.wangfj.product.supplier.domain.vo.GetSupCodeResultDto;
import com.wangfj.product.supplier.domain.vo.PcmPushSupplyInfoDto;
import com.wangfj.product.supplier.domain.vo.PcmSupInfoForSupResultDto;
import com.wangfj.product.supplier.domain.vo.PcmSupplyInfoDto;
import com.wangfj.product.supplier.domain.vo.PcmSupplyInfoPartDto;
import com.wangfj.product.supplier.domain.vo.PcmSupplyInfoQueryDto;
import com.wangfj.product.supplier.domain.vo.PcmSupplyInfoResultDto;
import com.wangfj.product.supplier.persistence.PcmSupplyInfoMapper;
import com.wangfj.product.supplier.service.intf.IPcmSupplyInfoService;
import com.wangfj.util.Constants;

/**
 * 实现供应商信息
 *
 * @Class Name PcmSupplyInfoServiceImpl
 * @Author wuxiong
 * @Create In 2015年7月13日
 */
@Service
public class PcmSupplyInfoServiceImpl implements IPcmSupplyInfoService {

    private static final Logger logger = LoggerFactory.getLogger(PcmSupplyInfoServiceImpl.class);

    @Autowired
    private PcmSupplyInfoMapper supplyInfoMapper;

    @Autowired
    private PcmOrganizationMapper organizationMapper;

    /**
     * 电商上传供应商
     *
     * @param supplyInfo
     * @return Map<String, Object>
     * @Methods Name uploadSupplierFromEBusiness
     * @Create In 2015-12-1 By wangxuan
     */
    @Override
    @Transactional
    public Map<String, Object> uploadSupplierFromEBusiness(PcmSupplyInfo supplyInfo) {

        logger.info("start uploadSupplierFromEBusiness(),param:" + supplyInfo.toString());
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Integer result = Constants.PUBLIC_0;

        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap.put("organizationStatus", Constants.PUBLIC_0);
        paramMap.put("organizationType", Constants.PUBLIC_3);
        paramMap.put("organizationCode", supplyInfo.getShopSid());
        List<PcmOrganization> shopList = organizationMapper.selectListByParam(paramMap);
        if (shopList != null && shopList.size() > 0) {
            paramMap.clear();
            paramMap.put("supplyCode", supplyInfo.getSupplyCode());
            paramMap.put("shopSid", supplyInfo.getShopSid());
            List<PcmSupplyInfo> supplyInfoList = supplyInfoMapper.selectListByParam(paramMap);
            if (supplyInfoList != null) {
                if (supplyInfoList.size() == 0) {
                    result = supplyInfoMapper.insertSelective(supplyInfo);
                    resultMap.put("actionCode", Constants.A);
                }

                if (supplyInfoList.size() == 1) {
                    supplyInfo.setSid(supplyInfoList.get(0).getSid());
                    result = supplyInfoMapper.updateByPrimaryKeySelective(supplyInfo);
                    resultMap.put("actionCode", Constants.U);
                }

                if (result == 1) {
                    resultMap.put("sid", supplyInfo.getSid());
                }
            }
        }
        resultMap.put("result", result);

        logger.info("end uploadSupplierFromEBusiness(),return:" + resultMap.toString());
        return resultMap;
    }

    /**
     * 门店上传供应商
     *
     * @param supplyInfo
     * @return Map<String, Object>
     * @Methods Name uploadSupplierFromEFutureERP
     * @Create In 2016-2-18 By wangxuan
     */
    @Override
    @Transactional
    public Map<String, Object> uploadSupplierFromEFutureERP(PcmSupplyInfo supplyInfo) {

        logger.info("start uploadSupplierFromEBusiness(),param:" + supplyInfo.toString());
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Integer result = Constants.PUBLIC_0;

        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap.put("organizationStatus", Constants.PUBLIC_0);
        paramMap.put("organizationType", Constants.PUBLIC_3);
        paramMap.put("organizationCode", supplyInfo.getShopSid());
        List<PcmOrganization> shopList = organizationMapper.selectListByParam(paramMap);
        if (shopList != null && shopList.size() > 0) {
            paramMap.clear();
            paramMap.put("supplyCode", supplyInfo.getSupplyCode());
            paramMap.put("shopSid", supplyInfo.getShopSid());
            List<PcmSupplyInfo> supplyInfoList = supplyInfoMapper.selectListByParam(paramMap);
            if (supplyInfoList != null) {
                if (supplyInfoList.size() == 0) {
                    result = supplyInfoMapper.insertSelective(supplyInfo);
                    resultMap.put("actionCode", Constants.A);
                }

                if (supplyInfoList.size() == 1) {
                    supplyInfo.setSid(supplyInfoList.get(0).getSid());
                    result = supplyInfoMapper.updateByPrimaryKeySelective(supplyInfo);
                    resultMap.put("actionCode", Constants.U);
                }

                if (result == 1) {
                    resultMap.put("sid", supplyInfo.getSid());
                }
            }
        }
        resultMap.put("result", result);

        logger.info("end uploadSupplierFromEBusiness(),return:" + resultMap.toString());
        return resultMap;
    }

    /**
     * 添加单个供应商信息
     *
     * @param supplyInfo
     * @return Map<String,Object>
     * @Methods Name addSupplyInfo
     * @Create In 2015-8-8 By wangx
     */
    @Override
    @Transactional
    public Integer addSupplyInfo(PcmSupplyInfo supplyInfo) {

        logger.info("start addSupplyInfo(),para:" + supplyInfo.toString());

        Integer flag = Constants.PUBLIC_0;

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("organizationStatus", Constants.PUBLIC_0);
        paramMap.put("organizationType", Constants.PUBLIC_3);
        paramMap.put("organizationCode", supplyInfo.getShopSid());
        List<PcmOrganization> shopList = organizationMapper.selectListByParam(paramMap);
        if (shopList != null && shopList.size() > 0) {
            paramMap.clear();
            paramMap.put("supplyCode", supplyInfo.getSupplyCode());
            paramMap.put("shopSid", supplyInfo.getShopSid());
            List<PcmSupplyInfo> supplyInfoList = supplyInfoMapper.selectListByParam(paramMap);
            if (supplyInfoList != null && supplyInfoList.size() == 0) {
                flag = supplyInfoMapper.insertSelective(supplyInfo);
            }
        }

        logger.info("end addSupplyInfo(),return:" + flag);
        return flag;
    }

    /**
     * 根据编码修改信息 供应商上传
     *
     * @param supplyInfo
     * @return Integer
     * @Methods Name updateSupplyInfoBySupplyCode
     * @Create In 2015-8-26 By wangxuan
     */
    @Override
    @Transactional
    public Integer updateSupplyInfoBySupplyCode(PcmSupplyInfo supplyInfo) {

        logger.info("start updateSupplyInfoBySupplyCode(),para:" + supplyInfo.toString());

        Integer flag = Constants.PUBLIC_0;

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("organizationStatus", Constants.PUBLIC_0);
        paramMap.put("organizationType", Constants.PUBLIC_3);
        paramMap.put("organizationCode", supplyInfo.getShopSid());
        List<PcmOrganization> shopList = organizationMapper.selectListByParam(paramMap);
        if (shopList != null && shopList.size() > 0) {
            paramMap.clear();
            paramMap.put("supplyCode", supplyInfo.getSupplyCode());
            paramMap.put("shopSid", supplyInfo.getShopSid());
            List<PcmSupplyInfo> supplyInfoList = supplyInfoMapper.selectListByParam(paramMap);

            if (supplyInfoList != null && supplyInfoList.size() == 1) {
                supplyInfo.setSid(supplyInfoList.get(0).getSid());
                flag = supplyInfoMapper.updateByPrimaryKeySelective(supplyInfo);
            }
        }

        logger.info("end updateSupplyInfoBySupplyCode(),return:" + flag);
        return flag;
    }

    /**
     * 供应商信息删除(状态的变更)
     *
     * @param paramMap
     * @return Integer
     * @Methods Name updateSupplyInfoStatus
     * @Create In 2015-8-10 By wangx
     */
    @Override
    @Transactional
    public Integer updateSupplyInfoStatus(Map<String, Object> paramMap) {

        logger.info("start deleteSupplyInfo(),para:" + paramMap.toString());

        Integer flag = Constants.PUBLIC_0;

        String sid = (String) paramMap.get("sid");
        String status = (String) paramMap.get("status");
        if (!StringUtils.isNotEmpty(sid)) {
            throw new BleException(
                    ComErrorCodeConstants.ErrorCode.SUPPLYINFO_SID_IS_NULL.getErrorCode(),
                    ComErrorCodeConstants.ErrorCode.SUPPLYINFO_SID_IS_NULL.getMemo());
        }

        if (!StringUtils.isNotEmpty(status)) {
            throw new BleException(
                    ComErrorCodeConstants.ErrorCode.SUPPLYINFO_STATUS_IS_NULL.getErrorCode(),
                    ComErrorCodeConstants.ErrorCode.SUPPLYINFO_STATUS_IS_NULL.getMemo());
        }

        PcmSupplyInfo supplyInfo = new PcmSupplyInfo();
        supplyInfo.setSid(Long.parseLong(sid));
        // 设置状态变更
        supplyInfo.setStatus(status);
        flag = supplyInfoMapper.updateByPrimaryKeySelective(supplyInfo);

        logger.info("end deleteSupplyInfo()");

        return flag;

    }

    /**
     * 修改供应商信息
     *
     * @param updateSupplyInfo void
     * @Methods Name updateSupplyInfo
     * @Create In 2015年7月28日 By wangxiang
     */
    @Override
    @Transactional
    public Integer updateSupplyInfo(PcmSupplyInfo updateSupplyInfo) {

        logger.info("start updateSupplyInfo(),para:" + updateSupplyInfo.toString());

        Integer flag = Constants.PUBLIC_0;

        if (updateSupplyInfo.getSid() != null) {
            flag = supplyInfoMapper.updateByPrimaryKeySelective(updateSupplyInfo);
        }

        logger.info("end updateSupplyInfo()");
        return flag;
    }

    /**
     * 供应商信息查询（分页,非模糊）
     *
     * @param paramMap
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException Page<PcmSupplyInfoDto>
     * @Methods Name findPageSullyInfo
     * @Create In 2015-8-14 By wangx
     */
    @Override
    public Page<PcmSupplyInfoDto> findPageSullyInfo(Map<String, Object> paramMap)
            throws IllegalAccessException, InvocationTargetException {

        logger.info("start findPageSullyInfo(),para:" + paramMap.toString());

        Page<PcmSupplyInfoDto> page = new Page<PcmSupplyInfoDto>();

        String currentPage = paramMap.get("currentPage") + "";
        String pageSize = paramMap.get("pageSize") + "";
        if (StringUtils.isNotEmpty(currentPage)) {
            page.setCurrentPage(Integer.parseInt(currentPage));
        }
        if (StringUtils.isNotEmpty(pageSize)) {
            page.setPageSize(Integer.parseInt(pageSize));
        }

        // 查询总记录数
        Integer count = supplyInfoMapper.getCountByParam(paramMap);
        page.setCount(count);

        // 分页查询
        paramMap.put("start", page.getStart());
        paramMap.put("limit", page.getLimit());
        List<PcmSupplyInfo> supplyInfoList = supplyInfoMapper.selectPageListByParam(paramMap);

        if (supplyInfoList != null && !supplyInfoList.isEmpty()) {

            List<PcmSupplyInfoDto> supplyInfoDtoList = new ArrayList<PcmSupplyInfoDto>();
            for (int i = 0; i < supplyInfoList.size(); i++) {
                PcmSupplyInfoDto supplyInfoDto = new PcmSupplyInfoDto();
                BeanUtils.copyProperties(supplyInfoDto, supplyInfoList.get(i));
                supplyInfoDto.setLastOptDates(supplyInfoList.get(i).getLastOptDate());
                String lastOptDateStr = DateUtil.formatToStr(supplyInfoList.get(i).getLastOptDate(),
                        "yyyy-MM-dd");
                supplyInfoDto.setLastOptDateStr(lastOptDateStr);
                supplyInfoDto.setTaxRates(supplyInfoList.get(i).getTaxRate());
                supplyInfoDto.setTaxRateStr(supplyInfoList.get(i).getTaxRate() + "");

                supplyInfoDtoList.add(supplyInfoDto);
            }

            page.setList(supplyInfoDtoList);

        }

        logger.info("end findPageSullyInfo()");
        return page;

    }

    /**
     * 查询供应商（条件可以加门店的sid）
     *
     * @param paramMap
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException List<PcmSupplyInfoDto>
     * @Methods Name findListSullyInfoFuzzy
     * @Create In 2015-8-21 By wangxuan
     */
    @Override
    public List<PcmSupplyInfoDto> findListSullyInfoFuzzy(Map<String, Object> paramMap)
            throws IllegalAccessException, InvocationTargetException {

        logger.info("start findListSullyInfoFuzzy(),para:" + paramMap.toString());

        String shopSid_ = paramMap.get("shopSid_") + "";
        if (StringUtils.isNotEmpty(shopSid_)) {
            Map<String, Object> paramOrg = new HashMap<String, Object>();
            paramOrg.put("sid", Long.parseLong(shopSid_));
            paramOrg.put("organizationType", Constants.PCMORGANIZATION_TYPE_STORE_INT);
            List<PcmOrganization> organizationList = organizationMapper.selectListByParam(paramOrg);

            if (organizationList != null && organizationList.size() == 1) {
                String organizationCode = organizationList.get(0).getOrganizationCode();
                if (StringUtils.isNotEmpty(organizationCode)) {
                    paramMap.put("shopSid", organizationCode);
                }
            }
        }

        // 非分页
        paramMap.put("start", null);
        paramMap.put("limit", null);
        List<PcmSupplyInfo> supplyInfoList = supplyInfoMapper.selectPageByPara(paramMap);

        List<PcmSupplyInfoDto> supplyInfoDtoList = new ArrayList<PcmSupplyInfoDto>();
        if (supplyInfoList != null && !supplyInfoList.isEmpty()) {

            for (int i = 0; i < supplyInfoList.size(); i++) {
                PcmSupplyInfoDto supplyInfoDto = new PcmSupplyInfoDto();
                BeanUtils.copyProperties(supplyInfoDto, supplyInfoList.get(i));
                supplyInfoDto.setLastOptDates(supplyInfoList.get(i).getLastOptDate());
                String lastOptDateStr = DateUtil.formatToStr(supplyInfoList.get(i).getLastOptDate(),
                        "yyyy-MM-dd");
                supplyInfoDto.setLastOptDateStr(lastOptDateStr);
                supplyInfoDto.setTaxRates(supplyInfoList.get(i).getTaxRate());
                supplyInfoDto.setTaxRateStr(supplyInfoList.get(i).getTaxRate() + "");

                supplyInfoDtoList.add(supplyInfoDto);
            }

        }

        logger.info("end findListSullyInfoFuzzy()");
        return supplyInfoDtoList;
    }

    /**
     * 分页查询供应商(模糊)
     *
     * @param paramMap
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException Page<PcmSupplyInfoDto>
     * @Methods Name findPageSullyInfoFuzzy
     * @Create In 2015-8-14 By wangx
     */
    @Override
    public Page<PcmSupplyInfoDto> findPageSullyInfoFuzzy(Map<String, Object> paramMap)
            throws IllegalAccessException, InvocationTargetException {

        logger.info("start findPageSullyInfoFuzzy(),para:" + paramMap.toString());

        Page<PcmSupplyInfoDto> page = new Page<PcmSupplyInfoDto>();

        String currentPage = paramMap.get("currentPage") + "";
        String pageSize = paramMap.get("pageSize") + "";
        if (StringUtils.isNotEmpty(currentPage)) {
            page.setCurrentPage(Integer.parseInt(currentPage));
        }
        if (StringUtils.isNotEmpty(pageSize)) {
            page.setPageSize(Integer.parseInt(pageSize));
        }

        // 查询总记录数
        Integer count = supplyInfoMapper.getPageCountByPara(paramMap);
        page.setCount(count);

        // 分页查询
        paramMap.put("start", page.getStart());
        paramMap.put("limit", page.getLimit());
        List<PcmSupplyInfo> supplyInfoList = supplyInfoMapper.selectPageByPara(paramMap);

        if (supplyInfoList != null && !supplyInfoList.isEmpty()) {

            List<PcmSupplyInfoDto> supplyInfoDtoList = new ArrayList<PcmSupplyInfoDto>();
            for (int i = 0; i < supplyInfoList.size(); i++) {
                PcmSupplyInfoDto supplyInfoDto = new PcmSupplyInfoDto();
                BeanUtils.copyProperties(supplyInfoDto, supplyInfoList.get(i));
                supplyInfoDto.setLastOptDates(supplyInfoList.get(i).getLastOptDate());
                String lastOptDateStr = DateUtil.formatToStr(supplyInfoList.get(i).getLastOptDate(),
                        "yyyy-MM-dd");
                supplyInfoDto.setLastOptDateStr(lastOptDateStr);
                supplyInfoDto.setTaxRates(supplyInfoList.get(i).getTaxRate());
                supplyInfoDto.setTaxRateStr(supplyInfoList.get(i).getTaxRate() + "");

                supplyInfoDtoList.add(supplyInfoDto);
            }

            page.setList(supplyInfoDtoList);

        }

        logger.info("end findPageSullyInfoFuzzy(),return:" + supplyInfoList.toString());
        return page;
    }

    /**
     * 多条件查询(模糊)
     *
     * @param paramMap
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException List<PcmSupplyInfoDto>
     * @Methods Name findListSupplyInfo
     * @Create In 2015-9-8 By wangxuan
     */
    @Override
    public List<PcmSupplyInfoDto> findListSupplyInfo(Map<String, Object> paramMap)
            throws IllegalAccessException, InvocationTargetException {

        logger.info("start findListSullyInfo(),para:" + paramMap.toString());

        // 查询
        // 非分页
        paramMap.put("start", null);
        paramMap.put("limit", null);
        List<PcmSupplyInfo> supplyInfoList = supplyInfoMapper.selectListByParamFuzzy(paramMap);

        List<PcmSupplyInfoDto> supplyInfoDtoList = new ArrayList<PcmSupplyInfoDto>();
        if (supplyInfoList != null && !supplyInfoList.isEmpty()) {

            for (int i = 0; i < supplyInfoList.size(); i++) {
                PcmSupplyInfoDto supplyInfoDto = new PcmSupplyInfoDto();
                BeanUtils.copyProperties(supplyInfoDto, supplyInfoList.get(i));
                supplyInfoDto.setLastOptDates(supplyInfoList.get(i).getLastOptDate());
                String lastOptDateStr = DateUtil.formatToStr(supplyInfoList.get(i).getLastOptDate(),
                        "yyyy-MM-dd");
                supplyInfoDto.setLastOptDateStr(lastOptDateStr);

                supplyInfoDtoList.add(supplyInfoDto);
            }

        }

        logger.info("end findListSullyInfo()");
        return supplyInfoDtoList;

    }

    /**
     * 供应商信息查询（非分页,非模糊）
     *
     * @param paramMap
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException List<PcmSupplyInfoDto>
     * @Methods Name findListSullyInfo
     * @Create In 2015-8-16 By wangx
     */
    @Override
    public List<PcmSupplyInfoDto> findListSullyInfo(Map<String, Object> paramMap)
            throws IllegalAccessException, InvocationTargetException {

        logger.info("start findListSullyInfo(),para:" + paramMap.toString());

        // 查询
        // 非分页
        paramMap.put("start", null);
        paramMap.put("limit", null);
        List<PcmSupplyInfo> supplyInfoList = supplyInfoMapper.selectListByParam(paramMap);

        List<PcmSupplyInfoDto> supplyInfoDtoList = new ArrayList<PcmSupplyInfoDto>();
        if (supplyInfoList != null && !supplyInfoList.isEmpty()) {

            for (int i = 0; i < supplyInfoList.size(); i++) {
                PcmSupplyInfoDto supplyInfoDto = new PcmSupplyInfoDto();
                BeanUtils.copyProperties(supplyInfoDto, supplyInfoList.get(i));
                supplyInfoDto.setLastOptDates(supplyInfoList.get(i).getLastOptDate());
                String lastOptDateStr = DateUtil.formatToStr(supplyInfoList.get(i).getLastOptDate(),
                        "yyyy-MM-dd");
                supplyInfoDto.setLastOptDateStr(lastOptDateStr);

                supplyInfoDtoList.add(supplyInfoDto);
            }

        } else {
            logger.error("查询供应商没有符合条件的数据");
            throw new BleException(ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getErrorCode(),
                    ComErrorCodeConstants.ErrorCode.DATA_EMPTY_ERROR.getMemo());
        }

        logger.info("end findListSullyInfo()");
        return supplyInfoDtoList;

    }

    /**
     * 供应商信息下发（全量）
     *
     * @param paramMap
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException List<PcmPushSupplyInfoDto>
     * @Methods Name pushPageSullyInfo
     * @Create In 2015-8-31 By wangxuan
     */
    @Override
    public List<PcmPushSupplyInfoDto> pushPageSullyInfo(Map<String, Object> paramMap)
            throws IllegalAccessException, InvocationTargetException {

        logger.info("start pushPageSullyInfo(),para:" + paramMap.toString());

        String start = paramMap.get("start") + "";
        String limit = paramMap.get("limit") + "";

        if (!StringUtils.isNotEmpty(start)) {
            paramMap.put("start", 0);
        }
        if (!StringUtils.isNotEmpty(limit)) {
            paramMap.put("limit", 10);
        }

        List<PcmSupplyInfo> supplyInfoList = supplyInfoMapper.pushPageSupplyInfo(paramMap);

        List<PcmPushSupplyInfoDto> supplyInfoDtoList = new ArrayList<PcmPushSupplyInfoDto>();
        if (supplyInfoList != null && !supplyInfoList.isEmpty()) {

            for (int i = 0; i < supplyInfoList.size(); i++) {

                PcmPushSupplyInfoDto supplyInfoDto = new PcmPushSupplyInfoDto();

                BeanUtils.copyProperties(supplyInfoDto, supplyInfoList.get(i));
                supplyInfoDto.setLastOptDates(supplyInfoList.get(i).getLastOptDate());

                supplyInfoDtoList.add(supplyInfoDto);
            }

        }

        logger.info("end pushPageSullyInfo()");
        return supplyInfoDtoList;

    }

    /**
     * 供应商信息下发（增量）
     *
     * @param paramMap
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException List<PcmPushSupplyInfoDto>
     * @Methods Name pushSupplyInfo
     * @Create In 2015-9-9 By wangxuan
     */
    @Override
    public List<PcmPushSupplyInfoDto> pushSupplyInfo(Map<String, Object> paramMap)
            throws IllegalAccessException, InvocationTargetException {

        logger.info("start pushSupplyInfo(),para:" + paramMap.toString());

        List<PcmSupplyInfo> supplyInfoList = supplyInfoMapper.pushSupplyInfo(paramMap);

        List<PcmPushSupplyInfoDto> supplyInfoDtoList = new ArrayList<PcmPushSupplyInfoDto>();
        if (supplyInfoList != null && !supplyInfoList.isEmpty()) {
            PcmPushSupplyInfoDto supplyInfoDto = null;
            for (PcmSupplyInfo dto : supplyInfoList) {
                supplyInfoDto = new PcmPushSupplyInfoDto();
                BeanUtils.copyProperties(supplyInfoDto, dto);
                supplyInfoDto.setLastOptDates(dto.getLastOptDate());
                supplyInfoDtoList.add(supplyInfoDto);
            }

        }

        logger.info("end pushSupplyInfo(),return:" + supplyInfoDtoList.toString());
        return supplyInfoDtoList;

    }

    /**
     * 查询供应商
     *
     * @param dto
     * @return List<PcmSupplyInfoPartDto>
     * @Methods Name findListSupplier
     * @Create In 2015-12-8 By wangxuan
     */
    @Override
    public List<PcmSupplyInfoPartDto> findListSupplier(PcmSupplyInfoQueryDto dto) {

        logger.info("start findListSupplier(),param:" + dto.toString());

        List<PcmSupplyInfoPartDto> resultList = new ArrayList<PcmSupplyInfoPartDto>();

        // 查询
        List<PcmSupplyInfoPartDto> dtoList = supplyInfoMapper.findListSupplier(dto);
        if (dtoList != null && !dtoList.isEmpty()) {
            for (PcmSupplyInfoPartDto tempDto : dtoList) {
                resultList.add(tempDto);
            }
        }

        logger.info("end findListSupplier(),return:" + resultList.toString());
        return resultList;
    }

    /**
     * 查询专柜的供应商信息
     *
     * @param dto
     * @return List<PcmSupplyInfoResultDto>
     * @Methods Name findListSupplierByShoppeParam
     * @Create In 2015-12-11 By wangxuan
     */
    @Override
    public List<PcmSupplyInfoResultDto> findListSupplierByShoppeParam(PcmSupplyInfoQueryDto dto) {

        logger.info("start findListSupplierByShoppeParam(),param:" + dto.toString());

        List<PcmSupplyInfoResultDto> list = supplyInfoMapper.findListSupplierByShoppeParam(dto);

        logger.info("end findListSupplierByShoppeParam(),return:" + list.toString());
        return list;

    }

	/**
	 * 根据门店编码及管理分类编码列表获取供应商信息
	 */
	@Override
	public List<GetSupCodeResultDto> getSupInfoFromPcmByShopCodesAndManagerCodes(
			Map<String,Object> para) {
		List<GetSupCodeResultDto> resultList = new ArrayList<GetSupCodeResultDto>();
		resultList = supplyInfoMapper.getSupCodeFromPcmByShopCode(para);
		return resultList;
	}

	/**
	 * 根据营业执照号及税号获取供应商信息
	 * @Methods Name getSupInfoByLicenseNoAndTaxNo
	 * @Create In 2016-4-13 By wangc
	 * @param para
	 * @return List<PcmSupInfoForSupResultDto>
	 */
	@Override
	public List<PcmSupInfoForSupResultDto> getSupInfoByLicenseNoAndTaxNo(Map<String, String> para) {
		List<PcmSupInfoForSupResultDto> resultList = supplyInfoMapper.getSupInfoFromPcmByLicenseNoAndTaxNo(para);
		return resultList;
	}

    
}
