package com.wangfj.product.category.service.intf;

import java.util.List;

import com.wangfj.product.category.domain.entity.PcmProductCategory;

public interface IProductCategoryService {

	int delete(Long sid);

	int insert(PcmProductCategory record);
    
	int save(PcmProductCategory record);

	PcmProductCategory get(Long sid);
    
//    boolean saveorupdate(Long productSid);
    
	int saveorupdate(PcmProductCategory record);

	int update(PcmProductCategory record);
    
	List<PcmProductCategory> selectList(PcmProductCategory record);
    
	int deleteByRecord(PcmProductCategory record);
	
}
