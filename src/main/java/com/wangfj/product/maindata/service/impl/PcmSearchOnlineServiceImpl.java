package com.wangfj.product.maindata.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangfj.product.category.domain.entity.PcmCategory;
import com.wangfj.product.category.domain.vo.SelectCategoryParamDto;
import com.wangfj.product.category.persistence.PcmCategoryMapper;
import com.wangfj.product.maindata.domain.entity.PcmProduct;
import com.wangfj.product.maindata.domain.vo.PcmSearchOnlineProDto;
import com.wangfj.product.maindata.domain.vo.SearchOnlineSkuInfoDto;
import com.wangfj.product.maindata.domain.vo.SearchSpuDto;
import com.wangfj.product.maindata.persistence.PcmPictureUrlMapper;
import com.wangfj.product.maindata.persistence.PcmProDetailMapper;
import com.wangfj.product.maindata.persistence.PcmProductMapper;
import com.wangfj.product.maindata.persistence.PcmShoppeProductMapper;
import com.wangfj.product.maindata.persistence.PcmTagMapper;
import com.wangfj.product.maindata.service.intf.IPcmSearchOnlineService;
import com.wangfj.product.organization.persistence.PcmChannelSaleConfigMapper;

@Service
public class PcmSearchOnlineServiceImpl implements IPcmSearchOnlineService {

	@Autowired
	private PcmShoppeProductMapper proMapper;
	@Autowired
	private PcmProDetailMapper skuMapper;
	@Autowired
	private PcmProductMapper spuMapper;
	@Autowired
	private PcmCategoryMapper cateMapper;
	@Autowired
	private PcmPictureUrlMapper puMapper;
	@Autowired
	private PcmChannelSaleConfigMapper channelSaleMapper;
	@Autowired
	private PcmTagMapper tagMapper;

	/**
	 * 搜索商品信息
	 * 
	 * @Methods Name searchOnlinePageProInfo
	 * @Create In 2015年11月10日 By yedong
	 * @param paramMap
	 * @return List<Map<String,Object>>
	 */
	@Override
	public List<PcmSearchOnlineProDto> searchOnlinePageProInfo(Map<String, Object> paramMap) {
		List<PcmSearchOnlineProDto> finalList = proMapper.searchOnlinePageProInfo(paramMap);
		return finalList;
	}

	@Override
	public Integer getProductCountByPara(Map<String, Object> paramMap) {
		Integer count = proMapper.searchOnlinePageProCount(paramMap);
		return count;
	}

	/**
	 * 搜索SKU信息
	 * 
	 * @Methods Name searchOnlineSkuInfoByCode
	 * @Create In 2015年11月10日 By yedong
	 * @param paramMap
	 * @return List<Map<String,Object>>
	 */
	@Override
	public List<SearchOnlineSkuInfoDto> searchOnlineSkuInfoByCode(Map<String, Object> paramMap) {
		List<SearchOnlineSkuInfoDto> list = skuMapper.searchOnlineSkuInfoByCode(paramMap);
		for (SearchOnlineSkuInfoDto skuDto : list) {
			Map<String, Object> tagMap = new HashMap<String, Object>();
			tagMap.put("skuCode", skuDto.getSkuId());
			List<String> tagList = tagMapper.getTagNameInfoByProCode(tagMap);
			skuDto.setActiveKeywords(tagList);
		}
		return list;
	}

	/**
	 * SKU编码列出专柜商品编码
	 * 
	 * @Methods Name searchItemCodeBySkuCode
	 * @Create In 2015年11月11日 By yedong
	 * @param paramMap
	 * @return List<Map<String,Object>>
	 */
	@Override
	public List<Map<String, Object>> searchItemCodeBySkuCode(Map<String, Object> paramMap) {
		List<Map<String, Object>> list = skuMapper.searchItemCodeBySkuCode(paramMap);
		return list;
	}

	/**
	 * SPU编码列出SKU编码
	 * 
	 * @Methods Name searchSkuCodeBySpuCode
	 * @Create In 2015年11月11日 By yedong
	 * @param paramMap
	 * @return List<Map<String,Object>>
	 */
	@Override
	public List<Map<String, Object>> searchSkuCodeBySpuCode(Map<String, Object> paramMap) {
		List<Map<String, Object>> list = skuMapper.searchSkuCodeBySpuCode(paramMap);
		return list;
	}

	/**
	 * 按品牌编码列出SPU编码
	 * 
	 * @Methods Name searchSpuCodeByBrandCode
	 * @Create In 2015年11月12日 By yedong
	 * @param paramMap
	 * @return List<Map<String,Object>>
	 */
	@Override
	public List<Map<String, Object>> searchSpuCodeByBrandCode(Map<String, Object> paramMap) {
		List<PcmProduct> list = spuMapper.selectListByParam(paramMap);
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		for (PcmProduct entity : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("spuId", entity.getProductSid());
			mapList.add(map);
		}
		return mapList;
	}

	/**
	 * 按品牌编码列出SPU编码
	 * 
	 * @Methods Name searchCountByBrandCode
	 * @Create In 2015年11月12日 By yedong
	 * @param paramMap
	 * @return List<Map<String,Object>>
	 */
	@Override
	public Integer searchCountByBrandCode(Map<String, Object> paramMap) {
		Integer countByParam = spuMapper.getCountByParam(paramMap);
		return countByParam;
	}

	/**
	 * 对接搜索专柜商品PRO赋值操作
	 * 
	 * @Methods Name resultProcess
	 * @Create In 2015年7月22日 By yedong
	 * @param map
	 * @return PcmSearchOnlineProDto
	 * @throws Exception
	 */
	public PcmSearchOnlineProDto resultProcess(Map<String, Object> map) {
		PcmSearchOnlineProDto dto = new PcmSearchOnlineProDto();
		dto.setItemId(map.get("productCode").toString());
		dto.setSkuId(map.get("skuCode").toString());
		dto.setSupplierId(map.get("supplierCode").toString());
		// dto.setShopId(map.get("storeSid").toString());
		map.get("skuSale").toString();
		// if ("0".equals(map.get("isSale").toString()) &&
		// "1".equals(map.get("skuSale").toString())) {
		// dto.setOnSell("true");
		// } else {
		// dto.setOnSell("false");
		// }
		dto.setStockMode(map.get("stockType").toString());
		dto.setInventory(map.get("saleStock").toString());
		dto.setOriginalPrice(map.get("marketPrice").toString());
		dto.setCurrentPrice(map.get("salesPrice").toString());
		return dto;
	}

	/**
	 * 按叶子级分类编码列出SPU编码
	 * 
	 * @Methods Name selectSpuByCategory
	 * @Create In 2015年11月11日 By zhangxy
	 * @param cateCode
	 * @return List<Map<String,Object>>
	 */
	@Override
	public Map<String, Object> selectSpuByCategory(String cateCode, Integer start, Integer limit) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		PcmCategory cate = new PcmCategory();
		cate.setCategorySid(cateCode);
		cate.setIsLeaf("Y");
		cate.setStatus("Y");
		cate.setIsDisplay(1);
		cate.setCategoryType(3);
		List<PcmCategory> cateList = cateMapper.selectListByParam(cate);
		if (cateList == null || cateList.size() == 0) {
			return null;
		}
		SelectCategoryParamDto cateParam = new SelectCategoryParamDto();
		cateParam.setSid(cateList.get(0).getSid());
		cateParam.setStart(start);
		cateParam.setLimit(limit);
		List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
		int count = spuMapper.selectSpuCountByIsLeaf(cateParam);
		if (count > 0) {
			List<PcmProduct> list = spuMapper.selectSpuByIsLeaf(cateParam);
			if (!list.isEmpty()) {
				for (PcmProduct pp : list) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("spuId", pp.getProductSid());
					resList.add(map);
				}
			}
		}
		resMap.put("list", resList);
		resMap.put("count", count);
		return resMap;
	}

	/**
	 * 获取SPU信息
	 * 
	 * @Methods Name selectSpuByCode
	 * @Create In 2015年11月11日 By zhangxy
	 * @param code
	 * @return SearchSpuDto
	 */
	@Override
	public List<SearchSpuDto> selectSpuByCode(Map<String, Object> paramMap) {
		List<SearchSpuDto> selectSpuByCodeForSearch = spuMapper.selectSpuByCodeForSearch(paramMap);
		return selectSpuByCodeForSearch;
	}

	/**
	 * 获取参加活动的专柜商品信息
	 * @Methods Name selectProByActiveId
	 * @Create In 2016-1-11 By wangc
	 * @param paraMap
	 * @return Map<String,Object>
	 */
	@Override
	public List<PcmSearchOnlineProDto> selectProByActiveId(Map<String, Object> paraMap) {
		List<PcmSearchOnlineProDto> resultList = proMapper.selectProByActiveId(paraMap);
		return resultList;
	}

	/**
	 * 获取参加活动的专柜商品数量
	 * @Methods Name selectProByActiveId
	 * @Create In 2016-1-11 By wangc
	 * @param paraMap
	 * @return Map<String,Object>
	 */
	@Override
	public Integer getProCountByActiveId(Map<String, Object> paraMap) {
		Integer total = proMapper.getProCountByActiveId(paraMap);
		return total;
	}
}