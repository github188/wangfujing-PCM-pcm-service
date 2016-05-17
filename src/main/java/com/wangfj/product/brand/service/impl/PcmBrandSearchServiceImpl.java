package com.wangfj.product.brand.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangfj.core.utils.StringUtils;
import com.wangfj.product.brand.persistence.PcmBrandMapper;
import com.wangfj.product.brand.service.intf.IPcmBrandSearchService;
import com.wangfj.util.Constants;

/**
 * 搜索
 * 
 * @Class Name PcmBrandSearchServiceImpl
 * @Author wangxuan
 * @Create In 2015-8-27
 */
@Service
public class PcmBrandSearchServiceImpl implements IPcmBrandSearchService {

	private static final Logger logger = LoggerFactory.getLogger(PcmBrandSearchServiceImpl.class);

	@Autowired
	private PcmBrandMapper brandMapper;

	/**
	 * 搜索查询门店品牌信息
	 * 
	 * @Methods Name searchBrandByParam
	 * @Create In 2015-8-27 By wangxuan
	 * @param paramMap
	 * @return List<HashMap<String,Object>>
	 */
	@Override
	public List<HashMap<String, Object>> searchBrandByParam(Map<String, Object> paramMap) {

		logger.info("start searchBrandByParam(),para:" + paramMap.toString());

		// 门店编码
		String storeCode = paramMap.get("storeCode") + "";
		// 门店品牌编码
		String storeBrandCode = paramMap.get("storeBrandCode") + "";

		Map<String, Object> para = new HashMap<String, Object>();

		// 默认有效
		para.put("status", Constants.PUBLIC_0);

		if (StringUtils.isNotEmpty(storeCode)) {
			para.put("storeCode", storeCode);
		}

		if (StringUtils.isNotEmpty(storeBrandCode)) {
			para.put("storeBrandCode", storeBrandCode);
		}

		List<HashMap<String, Object>> hashMapList = brandMapper.searchBrandByParam(para);

		logger.info("end searchBrandByParam()");
		return hashMapList;

	}

	/**
	 * 搜索线上根据编码查询品牌
	 * 
	 * @Methods Name searchOnlineBrandByCode
	 * @Create In 2015-11-10 By wangxuan
	 * @param paramMap
	 * @return Map<String,Object>
	 */
	@Override
	public Map<String, Object> searchOnlineBrandByCode(Map<String, Object> paramMap) {

		logger.info("start searchOnlineBrandByCode(),param:" + paramMap.toString());

		Map<String, Object> resultMap = new HashMap<String, Object>();

		List<Map<String, Object>> brandList = brandMapper.searchOnlineBrandByCode(paramMap);
		if (brandList != null && !brandList.isEmpty()) {
			Map<String, Object> brandMap = brandList.get(0);
			resultMap.put("brandId", brandMap.get("brandId"));
			resultMap.put("brandName", brandMap.get("brandName"));
			resultMap.put("brandDesc", brandMap.get("brandDesc"));
			resultMap.put("brandLogo", brandMap.get("brandLogo"));
			resultMap.put("brandPicture", brandMap.get("brandPicture"));

			List<Object> list = new ArrayList<Object>();
			list.add(brandMap.get("brandNameSecond"));
			list.add(brandMap.get("spell"));
			list.add(brandMap.get("brandNameEn"));

			resultMap.put("brandAliases", list);

		}

		logger.info("end searchOnlineBrandByCode(),return:" + resultMap.toString());
		return resultMap;
	}

	/**
	 * ONLINE-PCM-09-列出所有网站品牌
	 */
	@Override
	public List<Map<String, Object>> searchOnlineAllBrand(Map<String, Object> paramMap) {

		logger.info("start searchOnlineAllBrand(),param:" + paramMap.toString());

		List<Map<String, Object>> list = brandMapper.searchOnlineAllBrand(paramMap);

		logger.info("end searchOnlineAllBrand(),return:" + list.toString());
		return list;
	}
}
