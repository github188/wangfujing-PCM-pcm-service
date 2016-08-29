package com.wangfj.product.organization.service.impl;

import com.wangfj.core.framework.base.page.Page;
import com.wangfj.product.organization.domain.entity.PcmStoreInfo;
import com.wangfj.product.organization.domain.vo.PcmStoreInfoQueryDto;
import com.wangfj.product.organization.domain.vo.PcmStoreInfoResultDto;
import com.wangfj.product.organization.domain.vo.PcmStoreInfoToSupplierDto;
import com.wangfj.product.organization.persistence.PcmStoreInfoMapper;
import com.wangfj.product.organization.service.intf.IPcmStoreInfoService;
import com.wangfj.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 门店信息
 * Created by wangxuan on 2016-08-25 0025.
 */
@Service
public class PcmStoreInfoServiceImpl implements IPcmStoreInfoService {

    @Autowired
    private PcmStoreInfoMapper storeInfoMapper;

    private static final Logger logger = LoggerFactory.getLogger(PcmStoreInfoServiceImpl.class);

    /**
     * 门店信息分页查询
     *
     * @param queryDto
     * @return
     */
    @Override
    public Page<PcmStoreInfoResultDto> findPageStoreInfo(PcmStoreInfoQueryDto queryDto) {
        logger.debug("start findPageStoreInfo(),param:" + queryDto.toString());
        Page<PcmStoreInfoResultDto> page = new Page<PcmStoreInfoResultDto>();

        Integer currentPage = queryDto.getCurrentPage();
        Integer pageSize = queryDto.getPageSize();
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
        Integer count = storeInfoMapper.getPageCountByPara(queryDto);
        page.setCount(count);

        // 分页查询
        queryDto.setStart(page.getStart());
        queryDto.setLimit(page.getLimit());
        List<PcmStoreInfoResultDto> storeInfoResultDtoList = storeInfoMapper.selectPageByPara(queryDto);

        page.setList(storeInfoResultDtoList);

        logger.info("end findPageStoreInfo(),return:" + storeInfoResultDtoList.toString());
        return page;
    }

    /**
     * 供应商平台根据门店编码查询门店信息
     *
     * @param queryDto
     * @return
     */
    @Override
    public List<PcmStoreInfoToSupplierDto> findStoreInfoByParaToSupplier(PcmStoreInfoQueryDto queryDto) {
        logger.info("start findStoreInfoByParaToSupplier(),param:" + queryDto.toString());
        List<PcmStoreInfoToSupplierDto> resultList = new ArrayList<PcmStoreInfoToSupplierDto>();

        List<PcmStoreInfoResultDto> resultDtoList = storeInfoMapper.findListByPara(queryDto);
        if (resultDtoList != null && resultDtoList.size() > 0) {
            for (PcmStoreInfoResultDto resultDto : resultDtoList) {
                PcmStoreInfoToSupplierDto toSupplierDto = new PcmStoreInfoToSupplierDto();
                toSupplierDto.setAddress(resultDto.getRegisteredAddress());
                toSupplierDto.setBank(resultDto.getBank());
                toSupplierDto.setBankAccount(resultDto.getBankAccount());
                toSupplierDto.setFax(resultDto.getFaxNumber());
                toSupplierDto.setLegal(resultDto.getLegalRepresentative());
                toSupplierDto.setPostCode(resultDto.getPostCode());
                toSupplierDto.setProxy(resultDto.getAgent());
                toSupplierDto.setShopName(resultDto.getOrganizationName());
                toSupplierDto.setTaxNo(resultDto.getTaxRegistrationNumber());
                toSupplierDto.setTel(resultDto.getTelephoneNumber());
            }
        }
        logger.info("end findStoreInfoByParaToSupplier(),return:" + resultList.toString());
        return resultList;
    }


}
