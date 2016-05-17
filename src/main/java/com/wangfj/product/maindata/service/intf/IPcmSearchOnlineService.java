package com.wangfj.product.maindata.service.intf;

import java.util.List;
import java.util.Map;

import com.wangfj.product.maindata.domain.vo.PcmSearchOnlineProDto;
import com.wangfj.product.maindata.domain.vo.SearchOnlineSkuInfoDto;
import com.wangfj.product.maindata.domain.vo.SearchSpuDto;

public interface IPcmSearchOnlineService {
	/**
	 * 搜索商品信息
	 * 
	 * @Methods Name searchOnlinePageProInfo
	 * @Create In 2015年11月10日 By yedong
	 * @param paramMap
	 * @return List<Map<String,Object>>
	 */
	public List<PcmSearchOnlineProDto> searchOnlinePageProInfo(Map<String, Object> paramMap);

	List<SearchOnlineSkuInfoDto> searchOnlineSkuInfoByCode(Map<String, Object> paramMap);

	/**
	 * SPU编码列出SKU编码
	 * 
	 * @Methods Name searchSkuCodeBySpuCode
	 * @Create In 2015年11月11日 By yedong
	 * @param paramMap
	 * @return List<Map<String,Object>>
	 */
	public List<Map<String, Object>> searchSkuCodeBySpuCode(Map<String, Object> paramMap);

	/**
	 * SKU编码列出专柜商品编码
	 * 
	 * @Methods Name searchItemCodeBySkuCode
	 * @Create In 2015年11月11日 By yedong
	 * @param paramMap
	 * @return List<Map<String,Object>>
	 */
	public List<Map<String, Object>> searchItemCodeBySkuCode(Map<String, Object> paramMap);

	/**
	 * 对接搜索专柜商品PRO赋值操作
	 * 
	 * @Methods Name resultProcess
	 * @Create In 2015年7月22日 By yedong
	 * @param map
	 * @return PcmSearchOnlineProDto
	 * @throws Exception
	 */
	public PcmSearchOnlineProDto resultProcess(Map<String, Object> map);

	/**
	 * 按叶子级分类编码列出SPU编码
	 * 
	 * @Methods Name selectSpuByCategory
	 * @Create In 2015年11月11日 By zhangxy
	 * @param cateCode
	 * @return List<Map<String,Object>>
	 */
	Map<String, Object> selectSpuByCategory(String cateCode, Integer start, Integer limit);

	/**
	 * 获取SPU信息
	 * 
	 * @Methods Name selectSpuByCode
	 * @Create In 2015年11月11日 By zhangxy
	 * @param code
	 * @return SearchSpuDto
	 */
	List<SearchSpuDto> selectSpuByCode(Map<String, Object> paramMap);

	// SearchSpuDto selectSpuByCode(String code);

	List<Map<String, Object>> searchSpuCodeByBrandCode(Map<String, Object> paramMap);

	Integer getProductCountByPara(Map<String, Object> paramMap);

	Integer searchCountByBrandCode(Map<String, Object> paramMap);

	/**
	 * 获取参加活动的专柜商品信息
	 * @Methods Name selectProByActiveId
	 * @Create In 2016-1-11 By wangc
	 * @param paraMap
	 * @return Map<String,Object>
	 */
	List<PcmSearchOnlineProDto> selectProByActiveId(Map<String,Object> paraMap);
	/**
	 * 获取参加活动的专柜商品数量
	 * @Methods Name getProCountByActiveId
	 * @Create In 2016-1-11 By wangc
	 * @param paraMap
	 * @return Integer
	 */
	Integer getProCountByActiveId(Map<String,Object> paraMap);
	
}
