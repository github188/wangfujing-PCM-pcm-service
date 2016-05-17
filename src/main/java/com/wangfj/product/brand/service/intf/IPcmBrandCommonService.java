package com.wangfj.product.brand.service.intf;

import java.util.List;
import java.util.Map;

import com.wangfj.product.brand.domain.vo.GenerateBrandCodeDto;
import com.wangfj.product.brand.domain.vo.PcmShopBrandResultDto;

public interface IPcmBrandCommonService {

	/**
	 * 集团品牌判重
	 * 
	 * @Methods Name isBrandGroupExistence
	 * @Create In 2015-9-14 By wangxuan
	 * @param para
	 * @return Boolean
	 */
	Boolean isBrandGroupExistence(Map<String, Object> para);

	/**
	 * 门店品牌判重
	 * 
	 * @Methods Name isBrandExistence
	 * @Create In 2015-9-14 By wangxuan
	 * @param para
	 * @return Boolean
	 */
	Boolean isBrandExistence(Map<String, Object> para);

	/**
	 * 生成品牌编码(废弃)
	 * 
	 * @Methods Name generateBrandCode
	 * @Create In 2015-9-15 By wangxuan
	 * @param sid
	 * @return String
	 */
	@Deprecated
	String generateBrandCode(String sid);

	/**
	 * 门店品牌判重(门店与门店品牌信息上传)
	 * 
	 * @Methods Name isShopBrandExistence
	 * @Create In 2015-11-18 By wangxuan
	 * @param para
	 * @return List<PcmShopBrandResultDto>
	 */
	List<PcmShopBrandResultDto> isShopBrandExistence(Map<String, Object> para);

	/**
	 * 生成品牌编码
	 * 
	 * @Methods Name generateBrandCode
	 * @Create In 2015-12-14 By wangxuan
	 * @param dto
	 * @return String
	 */
	String generateBrandCode(GenerateBrandCodeDto dto);

}
