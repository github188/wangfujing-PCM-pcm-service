package com.wangfj.product.brand.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.brand.domain.entity.PcmBrandRelation;
import com.wangfj.product.brand.domain.vo.PcmBrandRelationDto;


public interface PcmBrandRelationMapper extends BaseMapper<PcmBrandRelation> {
    int deleteByPrimaryKey(Long sid);

    Integer insert(PcmBrandRelation record);

    int insertSelective(PcmBrandRelation record);

    PcmBrandRelation selectByPrimaryKey(Long sid);

    int updateByPrimaryKeySelective(PcmBrandRelation record);

    int updateByPrimaryKey(PcmBrandRelation record);
    
   List<Map<String, Object>> selectList(Map<String, Object> map);
   
	List<PcmBrandRelationDto> selectByBrand(Map<String, Object> map);

	List<HashMap<String, Object>> selectByBrands(Map<String, Object> map);

	Integer selectCountByBrand(Map<String, Object> map);
}