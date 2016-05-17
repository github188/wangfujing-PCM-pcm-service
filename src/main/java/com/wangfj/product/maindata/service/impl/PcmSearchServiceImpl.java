package com.wangfj.product.maindata.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangfj.core.constants.ComErrorCodeConstants.ErrorCode;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.product.maindata.domain.vo.PcmProByOrgCodeDto;
import com.wangfj.product.maindata.domain.vo.PcmSearchShoppeProDto;
import com.wangfj.product.maindata.domain.vo.PcmSearchSkuDto;
import com.wangfj.product.maindata.domain.vo.PcmSearchSpuDto;
import com.wangfj.product.maindata.persistence.PcmProDetailMapper;
import com.wangfj.product.maindata.persistence.PcmProductMapper;
import com.wangfj.product.maindata.persistence.PcmShoppeProductMapper;
import com.wangfj.product.maindata.service.intf.IPcmSearchService;

/**
 * 对接搜索接口
 * 
 * @Class Name PcmSearchServiceImpl
 * @Author yedong
 * @Create In 2015年8月28日
 */
@Service
public class PcmSearchServiceImpl implements IPcmSearchService {
	private static final Logger logger = LoggerFactory.getLogger(PcmSearchServiceImpl.class);

	@Autowired
	private PcmProDetailMapper skuMapper;

	@Autowired
	private PcmProductMapper spuMapper;

	@Autowired
	private PcmShoppeProductMapper proMapper;

	/**
	 * 对接搜索-查询SKU
	 * 
	 * @Methods Name searchSku
	 * @Create In 2015年8月28日 By yedong
	 * @param skuId
	 * @return Map<String,Object>
	 */
	public Map<String, Object> searchSku(String skuCode) {
		logger.info("start searchSku ,param:" + skuCode);
		PcmSearchSkuDto dto = new PcmSearchSkuDto();
		dto.setSkuCode(skuCode);
		List<Map<String, Object>> searchSku = skuMapper.searchSku(dto);
		if (searchSku != null && searchSku.size() > 0) {
			logger.info("end searchSku ,return:" + searchSku.get(0));
			return searchSku.get(0);
		} else {
			logger.info("end searchSku ,return: 查询为空");
			throw new BleException(ErrorCode.SKU_INFO_NO_EXIST.getErrorCode(),
					ErrorCode.SKU_INFO_NO_EXIST.getMemo());
		}
	}

	/**
	 * 对接搜索-查询SPU
	 * 
	 * @Methods Name searchSpu
	 * @Create In 2015年8月28日 By yedong
	 * @param spuId
	 * @return Map<String,Object>
	 */
	public Map<String, Object> searchSpu(String spuCode) {
		logger.info("start searchSku ,param:" + spuCode);
		PcmSearchSpuDto dto = new PcmSearchSpuDto();
		dto.setSpuCode(spuCode);
		List<Map<String, Object>> searchSpu = spuMapper.searchSpu(dto);
		if (searchSpu != null && searchSpu.size() > 0) {
			List<String> aliases = new ArrayList<String>();
			aliases.add((String) searchSpu.get(0).get("aliases"));
			searchSpu.get(0).put("aliases", aliases);
			logger.info("end searchSku ,return:" + searchSpu.get(0));
			return searchSpu.get(0);
		} else {
			logger.info("end searchSku ,return: 查询为空");
			throw new BleException(ErrorCode.SPU_INFO_NO_EXIST.getErrorCode(),
					ErrorCode.SPU_INFO_NO_EXIST.getMemo());
		}
	}

	/**
	 * 赋值
	 * 
	 * @Methods Name searchShoppePro
	 * @Create In 2015年8月28日 By yedong
	 * @param proList
	 * @return List<PcmSearchShoppeProDto>
	 */
	public List<PcmSearchShoppeProDto> searchShoppePro(List<String> proList) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sidList", proList);
		paramMap.put("num", proList.size());
		List<PcmProByOrgCodeDto> sqList = proMapper.searchSpByStore(paramMap);
		List<PcmSearchShoppeProDto> dtoList = new ArrayList<PcmSearchShoppeProDto>();
		for (PcmProByOrgCodeDto pro : sqList) {
			PcmSearchShoppeProDto dto = new PcmSearchShoppeProDto();
			dto.setManageCategoryType(pro.getManageCategoryType());
			dto.setOriginPrice(pro.getOriginPrice());
			dto.setProductCategory(pro.getProductCategory());
			dto.setUnit(pro.getUnit());
			dto.setArticle_num(pro.getArticle_num());
			dto.setBarcode(pro.getBarcode());
			dto.setDiscountLimit(pro.getDiscountLimit());
			dto.setErpProductCode(pro.getErpProductCode());
			dto.setErpSkuType(pro.getErpSkuType());
			dto.setItemCode(pro.getItemCode());
			dto.setManageType(pro.getManageType());
			dto.setOnMarketDate(pro.getOnMarketDate());
			dto.setRateCode(pro.getRateCode());
			if (pro.getSaleStatus().equals("Y")) {
				dto.setSaleStatus("0");
			} else if (pro.getSaleStatus().equals("N")) {
				dto.setSaleStatus("1");
			} else {
				dto.setSaleStatus(pro.getSaleStatus());
			}
			dto.setSeason(pro.getSeason());
			dto.setStoreBrandCode(pro.getStoreBrandCode());
			dto.setShoppeCode(pro.getShoppeCode());
			dto.setShoppeItemName(pro.getShoppeItemName());
			dto.setShoppeItemType(pro.getShoppeItemType());
			dto.setSkuCode(pro.getSkuCode());
			dto.setSupplierCode(pro.getSupplierCode());
			dto.setSupplierName(pro.getSupplierName());
			dto.setStoreCode(pro.getStoreCode());
			dtoList.add(dto);
		}
		return dtoList;
	}
}
