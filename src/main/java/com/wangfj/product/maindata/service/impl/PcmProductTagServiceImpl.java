package com.wangfj.product.maindata.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangfj.core.framework.base.page.Page;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.product.brand.persistence.PcmBrandMapper;
import com.wangfj.product.maindata.domain.entity.PcmProductTag;
import com.wangfj.product.maindata.domain.entity.PcmTag;
import com.wangfj.product.maindata.domain.vo.LabelSkuPageQueryDto;
import com.wangfj.product.maindata.domain.vo.PcmShoppeProductQueryDto;
import com.wangfj.product.maindata.domain.vo.PcmSkuQueryDto;
import com.wangfj.product.maindata.domain.vo.ProductPageDto;
import com.wangfj.product.maindata.domain.vo.SkuPageDto;
import com.wangfj.product.maindata.persistence.PcmProDetailMapper;
import com.wangfj.product.maindata.persistence.PcmProductTagMapper;
import com.wangfj.product.maindata.persistence.PcmTagMapper;
import com.wangfj.product.maindata.service.intf.IPcmProductTagService;
import com.wangfj.product.organization.persistence.PcmOrganizationMapper;
import com.wangfj.product.organization.persistence.PcmShoppeMapper;
import com.wangfj.product.supplier.persistence.PcmSupplyInfoMapper;
import com.wangfj.util.Constants;

@Service
public class PcmProductTagServiceImpl implements IPcmProductTagService {

	private static final Logger logger = LoggerFactory.getLogger(PcmShoppeProductServiceImpl.class);

	@Autowired
	PcmProductTagMapper proTagMapper;

	@Autowired
	PcmBrandMapper brandMapper;

	@Autowired
	PcmSupplyInfoMapper supplyMapper;

	@Autowired
	PcmShoppeMapper shoppeMapper;

	@Autowired
	PcmOrganizationMapper orgMapper;

	@Autowired
	private PcmTagMapper tagMapper;

	@Autowired
	private PcmProDetailMapper proDetailMapper;

	/**
	 * 促销标签页面查询商品
	 */
	@Override
	public Page<SkuPageDto> selectSkuPage(LabelSkuPageQueryDto dto) {
		logger.info("start selectSkuPage()----param:" + dto.toString());
		Page<SkuPageDto> page = new Page<SkuPageDto>();
		if (dto.getCurrentPage() != null && dto.getCurrentPage() != 0) {
			page.setCurrentPage(dto.getCurrentPage());
		}
		if (dto.getPageSize() != null && dto.getPageSize() != 0) {
			page.setPageSize(dto.getPageSize());
		} else {
			// 无pageSize参数时最大不超过20条商品信息数据
			page.setPageSize(20);
		}
		// 查询总数量
		Integer count = proTagMapper.getProDetialCountByPara(dto);
		if (count > 0) {
			page.setCount(count);
			if (dto.getStart() == null && dto.getLimit() == null) {
				dto.setStart(page.getStart());
				dto.setLimit(page.getLimit());
			} else {
				page.setStart(dto.getStart());
				page.setLimit(dto.getLimit());
			}
			List<SkuPageDto> list = proTagMapper.selectProDetialPageByPara(dto);
			page.setList(list);
		} else {
			logger.info("查询结果为空----param:" + dto.toString());
			page.setList(null);
		}

		logger.info("end selectSkuPage()----param:" + page.toString());
		return page;
	}

	/**
	 * 按条件 分页 查询专柜商品基础信息
	 * 
	 * @Methods Name selectBaseProPageByPara
	 * @Create In 2015年8月3日 By zhangxy
	 * @param pageDto
	 * @return Page<ProductPageDto>
	 * @throws Exception
	 */
	public Page<ProductPageDto> selectBaseProPageByPara(ProductPageDto pageDto, String isAddTag,
			String tagSid) throws BleException {
		Page<ProductPageDto> page = new Page<ProductPageDto>();
		if (pageDto.getCurrentPage() != null && pageDto.getCurrentPage() != 0) {
			page.setCurrentPage(pageDto.getCurrentPage());
		}
		if (pageDto.getPageSize() != null && pageDto.getPageSize() != 0) {
			page.setPageSize(pageDto.getPageSize());
		} else if (pageDto.getLimit() != null) {
			page.setPageSize(pageDto.getLimit());
		} else {
			// 无pageSize参数时最大不超过20条商品信息数据
			page.setPageSize(20);
		}
		// 查询参数处理
		Map<String, Object> paramMap = paramProcess(pageDto);
		paramMap.put("isAddTag", isAddTag);
		paramMap.put("tagSid", tagSid);
		// 查询总数量
		Integer count = proTagMapper.getProductCountByPara(paramMap);
		if (count != 0) {
			page.setCount(count);
		}
		if (pageDto.getStart() != null && pageDto.getLimit() != null) {
			paramMap.put("start", pageDto.getStart());
			paramMap.put("limit", pageDto.getLimit());
			page.setStart(pageDto.getStart());
			page.setLimit(pageDto.getLimit());
		} else if (pageDto.getPageSize() != null && pageDto.getPageSize() != 0) {
			paramMap.put("start", page.getStart());
			paramMap.put("limit", page.getLimit());
		}
		List<Map<String, Object>> list = proTagMapper.selectProductPageByPara(paramMap);
		if (!list.isEmpty()) {
			List<ProductPageDto> finalList = new ArrayList<ProductPageDto>();
			for (Map<String, Object> map : list) {
				// 结果处理
				ProductPageDto dto = resultProcess(map);
				finalList.add(dto);
			}
			page.setList(finalList);
		} else {
			page.setList(null);
		}
		return page;
	}

	/**
	 * 批量导入专柜商品与促销标签的关系
	 * 
	 * @param dto
	 * @return
	 */
	@Override
	@Transactional
	public boolean addShoppeProductTagList(PcmShoppeProductQueryDto dto) {
		logger.info("start addShoppeProductTagList(),param:" + dto.toString());
		boolean flag = true;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sid", dto.getTagSid());
		paramMap.put("status", Constants.PUBLIC_0);
		List<PcmTag> tagList = tagMapper.selectListByParam(paramMap);
		if (tagList != null && tagList.size() == 1) {
			Integer start = Constants.PUBLIC_0;
			dto.setStart(start);
			dto.setLimit(Constants.SHOPPEPRODUCT_LIMIT);
			List<Map<String, Object>> shoppeProductList = proTagMapper
					.findShoppeProductByParamForTag(dto);
			if (shoppeProductList != null && shoppeProductList.size() > 0) {
				for (int i = 0; i < shoppeProductList.size(); i++) {
					paramMap.clear();
					String shoppeProSid = shoppeProductList.get(i).get("shoppeProSid") + "";
					String tagSid = dto.getTagSid();
					paramMap.put("productSid", shoppeProSid);
					paramMap.put("tagSid", tagSid);
					List<PcmProductTag> productTagList = proTagMapper.selectListByParam(paramMap);
					PcmProductTag productTag = new PcmProductTag();
					if (productTagList.size() == 0) {
						productTag.setProductSid((shoppeProSid));
						productTag.setTagSid(Long.parseLong(tagSid));
						productTag.setFlag(Constants.PUBLIC_0);
						int result = proTagMapper.insertSelective(productTag);
						if (result != 1) {
							flag = false;
						}
					} else {
						productTag.setSid(productTagList.get(0).getSid());
						productTag.setFlag(Constants.PUBLIC_0);
						int result = proTagMapper.updateByPrimaryKeySelective(productTag);
						if (result != 1) {
							flag = false;
						}
					}
				}
				start = start + Constants.SHOPPEPRODUCT_LIMIT;
				dto.setStart(start);
				dto.setLimit(Constants.SHOPPEPRODUCT_LIMIT);
				shoppeProductList = proTagMapper.findShoppeProductByParamForTag(dto);
			}
		}
		logger.info("end addShoppeProductTagList(),return:" + flag);
		return flag;
	}

	/**
	 * 批量导入商品(SKU)与关键字的关系
	 *
	 * @param dto
	 * @return
	 */
	@Override
	@Transactional
	public boolean addSkuTagList(PcmSkuQueryDto dto) {
		logger.info("start addSkuTagList(),param:" + dto.toString());
		boolean flag = true;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sid", dto.getTagSid());
		paramMap.put("status", Constants.PUBLIC_0);
		List<PcmTag> tagList = tagMapper.selectListByParam(paramMap);
		if (tagList != null && tagList.size() == 1) {
			Integer start = Constants.PUBLIC_0;
			dto.setStart(start);
			dto.setLimit(Constants.SHOPPEPRODUCT_LIMIT);
			List<Map<String, Object>> skuList = proTagMapper.findSkuByParamForTag(dto);
			while (skuList != null && skuList.size() > 0) {
				for (int i = 0; i < skuList.size(); i++) {
					paramMap.clear();
					String skuCode = skuList.get(i).get("skuCode") + "";
					String tagSid = dto.getTagSid();
					paramMap.put("productSid", skuCode);
					paramMap.put("tagSid", tagSid);
					List<PcmProductTag> productTagList = proTagMapper.selectListByParam(paramMap);
					PcmProductTag productTag = new PcmProductTag();
					if (productTagList.size() == 0) {
						productTag.setProductSid((skuCode));
						productTag.setTagSid(Long.parseLong(tagSid));
						productTag.setFlag(Constants.PUBLIC_0);
						int result = proTagMapper.insertSelective(productTag);
						if (result != 1) {
							flag = false;
						}
					} else {
						productTag.setSid(productTagList.get(0).getSid());
						productTag.setFlag(Constants.PUBLIC_0);
						int result = proTagMapper.updateByPrimaryKeySelective(productTag);
						if (result != 1) {
							flag = false;
						}
					}
				}
				start = start + Constants.SHOPPEPRODUCT_LIMIT;
				dto.setStart(start);
				dto.setLimit(Constants.SHOPPEPRODUCT_LIMIT);
				skuList = proTagMapper.findSkuByParamForTag(dto);
			}
		}
		logger.info("end addSkuTagList(),return:" + flag);
		return flag;
	}

	/**
	 * 添加关系
	 */
	@Transactional
	public boolean save(List<PcmProductTag> proTags) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		for (PcmProductTag proTag : proTags) {
			paramMap.put("sid", proTag.getTagSid());
			List<PcmTag> tagList = tagMapper.selectListByParam(paramMap);
			// 判断标签是否存在
			if (tagList != null && tagList.size() == 1) {
				paramMap.clear();
				paramMap.put("productSid", proTag.getProductSid());
				paramMap.put("tagSid", proTag.getTagSid());
				List<PcmProductTag> list = proTagMapper.selectListByParam(paramMap);

				// 添加标签关系(向pcm_product_tag表添加数据)
				if (list.size() == 0) {
					proTag.setFlag(0);
					proTag.setUpdateTime(new Date());
					int i = proTagMapper.insertSelective(proTag);
					if (i < 0) {
						return false;
					}
				} else {
					proTag = list.get(0);
					proTag.setFlag(0);
					proTag.setUpdateTime(new Date());
					int i = proTagMapper.updateByPrimaryKeySelective(proTag);
					if (i < 0) {
						return false;
					}
				}

				// // 判断是否添加的标签类型
				// PcmTag tag = tagList.get(0);
				// Integer tagType = tag.getTagType();
				// if (tagType != null) {
				// // 修改SKU表对应的活动关键字或关键字
				// if (tagType == 2 || tagType == 3) {
				// paramMap.clear();
				// paramMap.put("productDetailSid", proTag.getProductSid());
				// List<PcmProDetail> proDetialList = proDetailMapper
				// .selectListByParam(paramMap);
				// if (proDetialList != null && proDetialList.size() == 1) {
				// String tagName = tag.getTagName();
				// PcmProDetail proDetail = proDetialList.get(0);
				// StringBuilder sb = new StringBuilder();
				// // 关键字
				// if (tagType == 2) {
				// String searchKey = proDetail.getSearchKey();
				// if (com.wangfj.core.utils.StringUtils.isNotEmpty(searchKey))
				// {
				// String[] searchKeyArr = searchKey.split(",");
				// // 判断关键字是否存在
				// boolean searchKeyFlag = false;
				// for (String string : searchKeyArr) {
				// if (tagName.trim().equals(string.trim())) {
				// searchKeyFlag = true;
				// }
				// }
				// if (!searchKeyFlag) {
				// sb.append(searchKey.trim() + "," + tagName.trim());
				// }
				// } else {
				// sb.append(tagName.trim());
				// }
				// proDetail.setSearchKey(sb.toString());
				// }
				// // 活动关键字
				// if (tagType == 3) {
				// String keyWord = proDetail.getKeyWord();
				// if (com.wangfj.core.utils.StringUtils.isNotEmpty(keyWord)) {
				// String[] keyWordArr = keyWord.split(",");
				// // 判断活动关键字是否存在
				// boolean keyWordFlag = false;
				// for (String string : keyWordArr) {
				// if (tagName.trim().equals(string.trim())) {
				// keyWordFlag = true;
				// }
				// }
				// if (!keyWordFlag) {
				// sb.append(keyWord.trim() + "," + tagName.trim());
				// }
				// } else {
				// sb.append(tagName.trim());
				// }
				// proDetail.setKeyWord(sb.toString());
				// }
				// proDetailMapper.updateByPrimaryKeySelective(proDetail);
				// }
				// }
				// }

			}
		}
		return true;
	}

	/**
	 * 删除关系
	 */
	@Transactional
	public boolean delete(List<PcmProductTag> proTags) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		for (PcmProductTag proTag : proTags) {
			paramMap.put("sid", proTag.getTagSid());
			List<PcmTag> tagList = tagMapper.selectListByParam(paramMap);
			// 判断标签是否存在
			if (tagList != null && tagList.size() == 1) {
				paramMap.clear();
				paramMap.put("productSid", proTag.getProductSid());
				paramMap.put("tagSid", proTag.getTagSid());
				List<PcmProductTag> list = proTagMapper.selectListByParam(paramMap);
				// 修改标签关系(向pcm_product_tag表修改数据)
				if (list.size() > 0) {
					proTag = list.get(0);
					proTag.setFlag(1);
					proTag.setUpdateTime(new Date());
					int i = proTagMapper.updateByPrimaryKeySelective(proTag);
					if (i < 0) {
						return false;
					}
				}

				// // 判断是否添加的标签类型
				// PcmTag tag = tagList.get(0);
				// Integer tagType = tag.getTagType();
				// if (tagType != null) {
				// // 修改SKU表对应的活动关键字或关键字
				// if (tagType == 2 || tagType == 3) {
				// paramMap.clear();
				// paramMap.put("productDetailSid", proTag.getProductSid());
				// List<PcmProDetail> proDetialList = proDetailMapper
				// .selectListByParam(paramMap);
				// if (proDetialList != null && proDetialList.size() == 1) {
				// String tagName = tag.getTagName();
				// PcmProDetail proDetail = proDetialList.get(0);
				// StringBuilder sb = new StringBuilder();
				// // 关键字
				// if (tagType == 2) {
				// String searchKey = proDetail.getSearchKey();
				// if (com.wangfj.core.utils.StringUtils.isNotEmpty(searchKey))
				// {
				// String[] searchKeyArr = searchKey.split(",");
				// for (int i = 0; i < searchKeyArr.length; i++) {
				// // 判断关键字是否存在
				// if (!tagName.trim().equals(searchKeyArr[i].trim())) {
				// if (i == (searchKeyArr.length - 1)) {
				// sb.append(searchKeyArr[i]);
				// } else {
				// sb.append(searchKeyArr[i] + ",");
				// }
				// }
				// }
				// proDetail.setSearchKey(sb.toString());
				// }
				// }
				// // 活动关键字
				// if (tagType == 3) {
				// String keyWord = proDetail.getKeyWord();
				// if (com.wangfj.core.utils.StringUtils.isNotEmpty(keyWord)) {
				// String[] keyWordArr = keyWord.split(",");
				// for (int i = 0; i < keyWordArr.length; i++) {
				// // 判断活动关键字是否存在
				// if (!tagName.trim().equals(keyWordArr[i].trim())) {
				// if (i == (keyWordArr.length - 1)) {
				// sb.append(keyWordArr[i]);
				// } else {
				// sb.append(keyWordArr[i] + ",");
				// }
				// }
				// }
				// proDetail.setKeyWord(sb.toString());
				// }
				// }
				// proDetailMapper.updateByPrimaryKeySelective(proDetail);
				// }
				// }
				// }

			}
		}
		return true;
	}

	/**
	 * 专柜商品查询--查询参数处理
	 * 
	 * @Methods Name selectParamprocess
	 * @Create In 2015年7月22日 By zhangxy
	 * @param pageDto
	 * @return Map<String, Object>
	 * @throws Exception
	 */
	public Map<String, Object> paramProcess(ProductPageDto pageDto) throws BleException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sid", pageDto.getSid());
		paramMap.put("sidList", pageDto.getSidList());
		paramMap.put("productCode", pageDto.getProductCode());
		paramMap.put("skuCode", pageDto.getSkuCode());
		paramMap.put("spuCode", pageDto.getSpuCode());
		paramMap.put("erpProductCode", pageDto.getErpProductCode());
		paramMap.put("modelCode", pageDto.getModelCode());
		paramMap.put("articleNum", pageDto.getArticleNum());
		paramMap.put("colorCode", pageDto.getColorCode());
		paramMap.put("stanCode", pageDto.getStanCode());
		paramMap.put("storeCode", pageDto.getStoreCode());
		paramMap.put("counterCode", pageDto.getCounterCode());

		paramMap.put("brandSid", pageDto.getBrandSid());
		paramMap.put("brandCode", pageDto.getBrandCode());
		paramMap.put("brandGroupCode", pageDto.getBrandGroupCode());
		// paramMap.put("erpSkuType", pageDto.getErpSkuType());
		// paramMap.put("manageType", pageDto.getManageType());
		paramMap.put("channelSid", pageDto.getChannelSid());
		paramMap.put("supplierCode", pageDto.getSupplierCode());
		paramMap.put("supplierSid", pageDto.getSupplierSid());

		paramMap.put("skuSelling", pageDto.getSkuSale());
		paramMap.put("spuSelling", pageDto.getSpuSale());
		paramMap.put("negativeStock", pageDto.getNegativeStock());

		paramMap.put("stockMode", pageDto.getStockMode());
		paramMap.put("season", pageDto.getSeason());
		paramMap.put("marketPrice", pageDto.getMarketPrice());
		paramMap.put("stockMode", pageDto.getStockMode());
		paramMap.put("processType", pageDto.getProcessType());
		paramMap.put("originLand", pageDto.getOriginLand());
		paramMap.put("orderType", pageDto.getOrderType());
		paramMap.put("isPromotion", pageDto.getIsPromotion());
		paramMap.put("isAdjustPrice", pageDto.getIsAdjustPrice());
		paramMap.put("primaryAttr", pageDto.getPrimaryAttr());
		paramMap.put("sexSid", pageDto.getSexSid());
		paramMap.put("awesome", pageDto.getAwesome());
		paramMap.put("features", pageDto.getFeatures());
		paramMap.put("manageCategory", pageDto.getManageCategory());
		paramMap.put("productName", pageDto.getProductName());
		if (StringUtils.isNotBlank(pageDto.getSupplierSid())) {
			paramMap.put("supplierCode", pageDto.getSupplierSid());
		}
		if (pageDto.getSupplierIntBarCode() != null && pageDto.getSupplierIntBarCode().size() > 0) {
			paramMap.put("supplyProCode", String.valueOf(pageDto.getSupplierIntBarCode().get(0)));
		}
		return paramMap;
	}

	/**
	 * 专柜商品查询--查询结果 单条记录处理
	 * 
	 * @Methods Name resultProcess
	 * @Create In 2015年7月22日 By zhangxy
	 * @param map
	 * @return Map<String,Object>
	 * @throws Exception
	 */
	public ProductPageDto resultProcess(Map<String, Object> map) {
		if (map.get("isDiscountable") != null) {
			if (Constants.PCMERPPRODUCT_IS_PROMOTION_YES == (Integer) map.get("isDiscountable")) {
				map.put("isDiscountable", Constants.Y);
			} else if (Constants.PCMERPPRODUCT_IS_PROMOTION_NO == (Integer) map
					.get("isDiscountable")) {
				map.put("isDiscountable", Constants.N);
			}
		}
		if (map.get("productStatus") != null) {
			if (Constants.PCMERPPRODUCT_PRO_STATUS_NORMAL == (Integer) map.get("productStatus")) {
				map.put("productStatus", Constants.PCMERPPRODUCT_PRO_STATUS_NORMAL_STR);
			} else if (Constants.PCMERPPRODUCT_PRO_STATUS_STOPSALE == (Integer) map
					.get("productStatus")) {
				map.put("productStatus", Constants.PCMERPPRODUCT_PRO_STATUS_STOPSALE_STR);
			} else if (Constants.PCMERPPRODUCT_PRO_STATUS_STOP == (Integer) map
					.get("productStatus")) {
				map.put("productStatus", Constants.PCMERPPRODUCT_PRO_STATUS_STOP_STR);
			} else if (Constants.PCMERPPRODUCT_PRO_STATUS_DELETE == (Integer) map
					.get("productStatus")) {
				map.put("productStatus", Constants.PCMERPPRODUCT_PRO_STATUS_DELETE_STR);
			} else if (Constants.PCMERPPRODUCT_PRO_STATUS_PASS == (Integer) map
					.get("productStatus")) {
				map.put("productStatus", Constants.PCMERPPRODUCT_PRO_STATUS_PASS_STR);
			} else if (Constants.PCMERPPRODUCT_PRO_STATUS_PAUSE == (Integer) map
					.get("productStatus")) {
				map.put("productStatus", Constants.PCMERPPRODUCT_PRO_STATUS_PAUSE_STR);
			}
		}
		if (map.get("isCOD") != null) {
			if (Constants.PCMSHOPPEPRODECT_IS_COD_YES == (Integer) map.get("isCOD")) {
				map.put("isCOD", Constants.Y);
			} else if (Constants.PCMSHOPPEPRODECT_IS_COD_NO == (Integer) map.get("isCOD")) {
				map.put("isCOD", Constants.N);
			}
		}
		if (map.get("isSale") != null) {
			if (Constants.PCMSHOPPEPRODECT_SALE_STATUS_YES == (Integer) map.get("isSale")) {
				map.put("isSale", Constants.Y);
			} else if (Constants.PCMSHOPPEPRODECT_SALE_STATUS_NO == (Integer) map.get("isSale")) {
				map.put("isSale", Constants.N);
			}
		}
		if (map.get("stockType") != null) {
			if (Constants.PCMSHOPPEPRODECT_IS_STOCK_YE == (Integer) map.get("stockType")) {
				map.put("stockType", Constants.PCMSHOPPEPRODECT_IS_STOCK_Y_STR);
			} else if (Constants.PCMSHOPPEPRODECT_IS_STOCK_NE == (Integer) map.get("stockType")) {
				map.put("stockType", Constants.PCMSHOPPEPRODECT_IS_STOCK_N_STR);
			}
		}
		if (map.get("operateMode") != null) {
			if (Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z1 == (Integer) map.get("operateMode")) {
				map.put("operateMode", Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z1_STR);
			} else if (Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z2 == (Integer) map.get("operateMode")) {
				map.put("operateMode", Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z2_STR);
			} else if (Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z3 == (Integer) map.get("operateMode")) {
				map.put("operateMode", Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z3_STR);
			} else if (Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z4 == (Integer) map.get("operateMode")) {
				map.put("operateMode", Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z4_STR);
			} else if (Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z5 == (Integer) map.get("operateMode")) {
				map.put("operateMode", Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z5_STR);
			}
		}
		if (map.get("tmsParam") != null) {
			if (Constants.PCMSHOPPEPRODECT_TMS_TYPE_Z1 == (Integer) map.get("tmsParam")) {
				map.put("tmsParam", Constants.PCMSHOPPEPRODECT_TMS_TYPE_Z1_STR);
			} else if (Constants.PCMSHOPPEPRODECT_TMS_TYPE_Z2 == (Integer) map.get("tmsParam")) {
				map.put("tmsParam", Constants.PCMSHOPPEPRODECT_TMS_TYPE_Z2_STR);
			} else if (Constants.PCMSHOPPEPRODECT_TMS_TYPE_Z3 == (Integer) map.get("tmsParam")) {
				map.put("tmsParam", Constants.PCMSHOPPEPRODECT_TMS_TYPE_Z3_STR);
			} else if (Constants.PCMSHOPPEPRODECT_TMS_TYPE_Z4 == (Integer) map.get("tmsParam")) {
				map.put("tmsParam", Constants.PCMSHOPPEPRODECT_TMS_TYPE_Z4_STR);
			}
		}
		if (StringUtils.isBlank((String) map.get("channelName"))) {
			map.put("channelName", "全渠道");
		}
		ProductPageDto dto = new ProductPageDto();
		try {
			BeanUtils.copyProperties(dto, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
}
