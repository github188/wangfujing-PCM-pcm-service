package com.wangfj.product.brand.persistence;

import java.util.List;
import java.util.Map;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.brand.domain.entity.PcmBrandCategory;
import com.wangfj.product.brand.domain.vo.PcmBrandCateDto;

public interface PcmBrandCategoryMapper extends BaseMapper<PcmBrandCategory> {
	int deleteByPrimaryKey(Long sid);

	Integer insert(PcmBrandCategory record);

	int insertSelective(PcmBrandCategory record);

	PcmBrandCategory selectByPrimaryKey(Long sid);

	int updateByPrimaryKeySelective(PcmBrandCategory record);

	int updateByPrimaryKey(PcmBrandCategory record);

	Map<String, Object> getCateAndBrandSid(PcmBrandCateDto dto);

	List<Map<String, Object>> getBrandCateInfo(PcmBrandCateDto dto);
}