package com.wangfj.product.category.service.intf;

import com.wangfj.product.category.domain.entity.PcmCategoryRelation;

/**
 * 工业分类与管理分类关联service
 * 
 * @Class Name ICategoryRelationService
 * @Author duanzhaole
 * @Create In 2015年7月30日
 */
public interface ICategoryRelationService {

	int delete(Long sid);

	int save(PcmCategoryRelation record);

	PcmCategoryRelation get(Long sid);

	int update(PcmCategoryRelation record);
    
    String getCateIds(String erpCategorySid);
}
