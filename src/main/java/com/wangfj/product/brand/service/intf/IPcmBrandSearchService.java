package com.wangfj.product.brand.service.intf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 搜索
 * 
 * @Class Name IPcmBrandSearchService
 * @Author wangxuan
 * @Create In 2015-8-27
 */
public interface IPcmBrandSearchService {

	/**
	 * 搜索查询门店品牌信息
	 * 
	 * @Methods Name searchBrandByParam
	 * @Create In 2015-8-27 By wangxuan
	 * @param paramMap
	 * @return List<HashMap<String,Object>>
	 */
	List<HashMap<String, Object>> searchBrandByParam(Map<String, Object> paramMap);

	/**
	 * 搜索线上根据编码查询品牌
	 * 
	 * @Methods Name searchOnlineBrandByCode
	 * @Create In 2015-11-10 By wangxuan
	 * @param paramMap
	 * @return Map<String,Object>
	 */
	Map<String, Object> searchOnlineBrandByCode(Map<String, Object> paramMap);

	/**
	 * 搜索线上查询所有品牌
	 * 
	 * @Methods Name searchOnlineAllBrand
	 * @Create In 2015-12-16 By wangxuan
	 * @return List<Map<String,Object>>
	 */
	List<Map<String, Object>> searchOnlineAllBrand(Map<String, Object> paramMap);

}
