package com.wangfj.product.category.persistence;

import java.util.List;

import com.wangfj.product.category.domain.entity.PcmCategoryMq;

public interface PcmCategoryMqMapper {
	int deleteByPrimaryKey(Long sid);

	int insert(PcmCategoryMq record);

	int insertSelective(PcmCategoryMq record);

	PcmCategoryMq selectByPrimaryKey(Long sid);

	int updateByPrimaryKeySelective(PcmCategoryMq record);

	int updateByPrimaryKeyWithBLOBs(PcmCategoryMq record);

	int updateSelective(PcmCategoryMq record);

	int updateByPrimaryKey(PcmCategoryMq record);

	int updateFlag(String msgContext);

	List<PcmCategoryMq> selectList(String msgContext);
}