package com.wangfj.product.brand.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangfj.core.utils.StringUtils;
import com.wangfj.product.brand.domain.vo.BrandDataDto;
import com.wangfj.product.brand.persistence.PcmBrandMapper;
import com.wangfj.product.brand.service.intf.IPcmBrandInnerService;
import com.wangfj.util.Constants;

@Service
public class PcmBrandInnerServiceImpl implements IPcmBrandInnerService {

	private static final Logger logger = LoggerFactory.getLogger(PcmBrandInnerServiceImpl.class);

	@Autowired
	private PcmBrandMapper brandMapper;

	/**
	 * 导入终端查询品牌
	 * 
	 * @param paramMap
	 * @return
	 */
	@Override
	public List<BrandDataDto> getBrandData(Map<String, Object> paramMap) {

		logger.info("start getBrandData(),param:" + paramMap.toString());

		String brandType = paramMap.get("brandType") + "";
		String storeType = paramMap.get("storeType") + "";

		List<BrandDataDto> dtoList = null;
		if (StringUtils.isNotEmpty(brandType)) {
			if (brandType.trim().equals(Constants.PCMBRAND_TYPE_BRANDGROUP + "")) {
				dtoList = brandMapper.getBrandGroupData(paramMap);
			}
			if (brandType.trim().equals(Constants.PCMBRAND_TYPE_BRAND + "")) {
				dtoList = brandMapper.getBrandData(paramMap);
			}
			if (dtoList != null && !dtoList.isEmpty()) {
				for (BrandDataDto dto : dtoList) {
					dto.setStoreType(storeType);
				}
			}
		}

		logger.info("end getBrandData(),return:" + dtoList.toString());
		return dtoList;
	}

	/**
	 * CMS
	 * 
	 * @Methods Name getBrandInfoByName
	 * @Create In 2015-10-30 By wangxuan
	 * @param para
	 * @return List<Map<String,Object>>
	 */
	@Override
	public List<Map<String, Object>> getBrandInfoByName(Map<String, Object> para) {

		logger.info("start getBrandInfoByName(),param:" + para.toString());

		para.put("brandPicUrl", Constants.PCMBRAND_BRANDPICURL);
		List<Map<String, Object>> list = brandMapper.getBrandInfoByName(para);

		logger.info("end getBrandInfoByName(),return:" + list.toString());
		return list;

	}

	/**
	 * CMS
	 */
	@Override
	public List<Map<String, Object>> getBrandListByIds(List<String> paraList) {

		logger.info("start getBrandListByIds(),param:" + paraList.toString());

		List<Map<String, Object>> list = brandMapper.getBrandListByIds(paraList);

		logger.info("end getBrandListByIds(),return:" + list.toString());
		return list;

	}

	/**
	 * 根据门店品牌参数查询集团品牌信息
	 */
	@Override
	public List<Map<String, Object>> getBrandGroupByBrandParam(Map<String, Object> para) {

		logger.info("start getBrandGroupByBrandParam(),param:" + para.toString());

		List<Map<String, Object>> list = brandMapper.getBrandGroupByBrandParam(para);

		logger.info("end getBrandGroupByBrandParam(),return:" + list.toString());
		return list;
	}

}
