package com.wangfj.product.brand.persistence;

import java.util.List;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.brand.domain.entity.PcmShoppeBrandRelation;

public interface PcmShoppeBrandRelationMapper extends BaseMapper<PcmShoppeBrandRelation> {
	int deleteByPrimaryKey(Long sid);

	Integer insert(PcmShoppeBrandRelation record);

	int insertSelective(PcmShoppeBrandRelation record);

	PcmShoppeBrandRelation selectByPrimaryKey(Long sid);

	int updateByPrimaryKeySelective(PcmShoppeBrandRelation record);

	int updateByPrimaryKey(PcmShoppeBrandRelation record);

	List<PcmShoppeBrandRelation> selectListByParam(PcmShoppeBrandRelation record);
}