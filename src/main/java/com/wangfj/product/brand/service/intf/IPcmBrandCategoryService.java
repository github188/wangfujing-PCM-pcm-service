package com.wangfj.product.brand.service.intf;

import java.util.List;
import java.util.Map;

import com.wangfj.product.brand.domain.vo.PcmBrandCateDto;

public interface IPcmBrandCategoryService {
	public void addBrandCateInfo(List<PcmBrandCateDto> dtoList);

	List<Map<String, Object>> getBrandCateInfo(PcmBrandCateDto dto);
}
