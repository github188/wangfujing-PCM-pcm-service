package com.wangfj.product.maindata.persistence;

import java.util.List;
import java.util.Map;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.maindata.domain.entity.PcmDictVersion;
import com.wangfj.product.maindata.domain.vo.PcmDictVersionDto;

public interface PcmDictVersionMapper extends BaseMapper<PcmDictVersion> {
    int deleteByPrimaryKey(Long sid);

    Integer insert(PcmDictVersion record);

    int insertSelective(PcmDictVersion record);

    PcmDictVersion selectByPrimaryKey(Long sid);

    int updateByPrimaryKeySelective(PcmDictVersion record);

    int updateByPrimaryKey(PcmDictVersion record);
    
    List<Map<String,Object>> selectVersionByType(PcmDictVersionDto record);
}