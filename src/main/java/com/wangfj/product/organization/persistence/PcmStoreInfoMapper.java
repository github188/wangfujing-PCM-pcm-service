package com.wangfj.product.organization.persistence;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.organization.domain.entity.PcmOrganization;
import com.wangfj.product.organization.domain.entity.PcmStoreInfo;
import com.wangfj.product.organization.domain.vo.PcmStoreInfoQueryDto;
import com.wangfj.product.organization.domain.vo.PcmStoreInfoResultDto;

import java.util.List;
import java.util.Map;

public interface PcmStoreInfoMapper extends BaseMapper<PcmStoreInfo> {
    int deleteByPrimaryKey(Long sid);

    Integer insert(PcmStoreInfo record);

    int insertSelective(PcmStoreInfo record);

    PcmStoreInfo selectByPrimaryKey(Long sid);

    int updateByPrimaryKeySelective(PcmStoreInfo record);

    int updateByPrimaryKey(PcmStoreInfo record);

    List<PcmStoreInfo> selectListByParam(Map<String, Object> paramMap);

    /**
     * 门店信息分页查询数量
     *
     * @param queryDto
     * @return
     */
    Integer getPageCountByPara(PcmStoreInfoQueryDto queryDto);

    /**
     * 门店信息分页查询
     *
     * @param queryDto
     * @return
     */
    List<PcmStoreInfoResultDto> selectPageByPara(PcmStoreInfoQueryDto queryDto);

    /**
     * 查询门店信息List
     *
     * @param queryDto
     * @return
     */
    List<PcmStoreInfoResultDto> findListByPara(PcmStoreInfoQueryDto queryDto);
}