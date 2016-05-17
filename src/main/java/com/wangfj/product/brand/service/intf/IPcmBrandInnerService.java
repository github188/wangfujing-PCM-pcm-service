package com.wangfj.product.brand.service.intf;

import java.util.List;
import java.util.Map;

import com.wangfj.product.brand.domain.vo.BrandDataDto;

public interface IPcmBrandInnerService {

	List<BrandDataDto> getBrandData(Map<String, Object> paramMap);

	List<Map<String, Object>> getBrandInfoByName(Map<String, Object> para);

	List<Map<String, Object>> getBrandListByIds(List<String> paraList);

	List<Map<String, Object>> getBrandGroupByBrandParam(Map<String, Object> para);

}
