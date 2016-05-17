package com.wangfj.product.maindata.service.intf;

import java.util.List;

import com.wangfj.product.maindata.domain.entity.PcmProductTypeDict;

/**
 * 商品类型字典表
 * 
 * @Class Name IPcmProductTypeDictService
 * @Author zhangxy
 * @Create In 2015年9月11日
 */
public interface IPcmProductTypeDictService {
	/**
	 * 查询商品类型
	 * 
	 * @Methods Name selectProductType
	 * @Create In 2015年9月11日 By zhangxy
	 * @param dto
	 * @return List<PcmProductTypeDict>
	 */
	public List<PcmProductTypeDict> selectProductType(PcmProductTypeDict dto);
}
