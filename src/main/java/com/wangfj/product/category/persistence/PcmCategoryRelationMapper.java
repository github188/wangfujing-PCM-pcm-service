package com.wangfj.product.category.persistence;

import java.util.List;

import com.wangfj.core.framework.base.persistence.BaseMapper;
import com.wangfj.product.category.domain.entity.PcmCategoryRelation;


/**
 * 管理分类与工业分类关联表mapper
 * @Class Name PcmCategoryRelationMapper
 * @Author duanzhaole
 * @Create In 2015年7月3日
 */
public interface PcmCategoryRelationMapper extends BaseMapper<PcmCategoryRelation> {
    int deleteByPrimaryKey(Long sid);


    int insertSelective(PcmCategoryRelation record);

    PcmCategoryRelation selectByPrimaryKey(Long sid);

    int updateByPrimaryKeySelective(PcmCategoryRelation record);

    int updateByPrimaryKey(PcmCategoryRelation record);

	List<PcmCategoryRelation> selectList(PcmCategoryRelation record);

}