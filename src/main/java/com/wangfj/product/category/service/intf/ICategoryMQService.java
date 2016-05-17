package com.wangfj.product.category.service.intf;

import java.util.List;

import com.wangfj.product.category.domain.entity.PcmCategoryMq;

public interface ICategoryMQService {

	int save(PcmCategoryMq record);

	int updateSelective(PcmCategoryMq record);

	List<PcmCategoryMq> selectList(String msgContext);
    
    int updateFlag(String msgContext);
}
