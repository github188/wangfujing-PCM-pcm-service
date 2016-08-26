package com.wangfj.product.maindata.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
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

import com.wangfj.core.constants.ComErrorCodeConstants.ErrorCode;
import com.wangfj.core.framework.base.page.Page;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.core.utils.DateUtil;
import com.wangfj.product.brand.domain.entity.PcmBrand;
import com.wangfj.product.brand.persistence.PcmBrandMapper;
import com.wangfj.product.maindata.domain.entity.PcmErpProduct;
import com.wangfj.product.maindata.domain.entity.PcmErpProductChange;
import com.wangfj.product.maindata.domain.entity.PcmErpProductSupply;
import com.wangfj.product.maindata.domain.entity.PcmShoppeProduct;
import com.wangfj.product.maindata.domain.vo.ChangeProductDto;
import com.wangfj.product.maindata.domain.vo.ErpChangeDto;
import com.wangfj.product.maindata.domain.vo.ErpProPageDto;
import com.wangfj.product.maindata.domain.vo.ErpProductDto;
import com.wangfj.product.maindata.domain.vo.GetErpProductFromEfutureDto;
import com.wangfj.product.maindata.domain.vo.GetSupAndErpInfoDto;
import com.wangfj.product.maindata.domain.vo.ModifyErpProductDto;
import com.wangfj.product.maindata.domain.vo.PcmSearchErpProductDto;
import com.wangfj.product.maindata.domain.vo.ProductCondDto;
import com.wangfj.product.maindata.domain.vo.UpdateProductInfoDto;
import com.wangfj.product.maindata.persistence.PcmErpProductChangeMapper;
import com.wangfj.product.maindata.persistence.PcmErpProductMapper;
import com.wangfj.product.maindata.persistence.PcmErpProductSupplyMapper;
import com.wangfj.product.maindata.persistence.PcmShoppeProductMapper;
import com.wangfj.product.maindata.service.intf.IPcmChangeProductService;
import com.wangfj.product.maindata.service.intf.IPcmErpProductService;
import com.wangfj.product.maindata.service.intf.IPcmProductService;
import com.wangfj.product.maindata.service.intf.IPcmShoppeProductService;
import com.wangfj.product.organization.domain.entity.PcmOrganization;
import com.wangfj.product.organization.domain.entity.PcmShoppe;
import com.wangfj.product.organization.persistence.PcmOrganizationMapper;
import com.wangfj.product.organization.persistence.PcmShoppeMapper;
import com.wangfj.product.price.domain.entity.PcmPrice;
import com.wangfj.product.price.service.intf.IPcmPriceService;
import com.wangfj.product.supplier.domain.entity.PcmSupplyInfo;
import com.wangfj.product.supplier.persistence.PcmSupplyInfoMapper;
import com.wangfj.util.Constants;
import com.wangfj.util.mq.PublishDTO;

/**
 * erp商品信息service实现类
 *
 * @Class Name PcmErpProductServiceImpl
 * @Author zhangxy
 * @Create In 2015年7月14日
 */
@Service
public class PcmErpProductServiceImpl implements IPcmErpProductService {
	private static final Logger logger = LoggerFactory.getLogger(PcmErpProductServiceImpl.class);
	@Autowired
	PcmErpProductMapper erpProductMapper;
	@Autowired
	PcmShoppeMapper shoppeMapper;
	@Autowired
	PcmSupplyInfoMapper supMapper;
	@Autowired
	PcmOrganizationMapper orgMapper;
	@Autowired
	PcmShoppeProductMapper proMapper;
	@Autowired
	IPcmShoppeProductService proService;
	@Autowired
	PcmBrandMapper brMapper;
	@Autowired
	IPcmChangeProductService cpService;
	@Autowired
	IPcmProductService pService;
	// 价格service
	@Autowired
	private IPcmPriceService priceService;
	@Autowired
	private PcmErpProductChangeMapper erpProductChangeMapper;
	@Autowired
	private PcmErpProductSupplyMapper erpProductSupplyMapper;

	/**
	 * 根据DTO创建实体
	 *
	 * @param GetErpProductFromEfutureDto
	 *            dto
	 * @return PcmErpProduct entity
	 * @Methods Name createEntity
	 * @Create In 2015年8月7日 By zhangxy
	 */
	private PcmErpProduct createEntity2(GetErpProductFromEfutureDto dto) {
		PcmErpProduct entity = new PcmErpProduct();
		entity.setOfferNo(dto.getContractCode());
		if (StringUtils.isNotBlank(dto.getSkuType())) {
			entity.setCodeType(Integer.parseInt(dto.getSkuType()));
		}
		if (StringUtils.isNotBlank(dto.getACTION_PERSON())) {
			entity.setOptUserSid(dto.getACTION_PERSON());
		}
		entity.setBrandCode(dto.getBrandCode());
		entity.setArticleNum(dto.getArticleNum());
		entity.setProductCode(dto.getProductCode());
		entity.setShoppeCode(dto.getCounterCode());
		// 根据专柜编码查询专柜名
		Map<String, Object> shoppeMap = new HashMap<String, Object>();
		shoppeMap.put("shoppeCode", dto.getCounterCode());
		List<PcmShoppe> shoppelist = shoppeMapper.selectListByParam(shoppeMap);
		if (shoppelist.size() != 0) {
			entity.setShoppeName(shoppelist.get(Constants.PUBLIC_0).getShoppeName());
		} else {
			throw new BleException(ErrorCode.SHOPPE_NULL.getErrorCode(),
					ErrorCode.SHOPPE_NULL.getMemo());
		}
		entity.setStoreCode(dto.getStoreCode());
		// 根据门店编码查询门店名
		Map<String, Object> orgMap = new HashMap<String, Object>();
		orgMap.put("organizationCode", dto.getStoreCode());
		List<PcmOrganization> orglist = orgMapper.selectListByParam(orgMap);
		if (orglist.size() == 1) {
			entity.setShopName(orglist.get(Constants.PUBLIC_0).getOrganizationName());
		} else {
			throw new BleException(ErrorCode.SHOP_NULL.getMemo());
		}
		entity.setSupplyCode(dto.getSupplierCode());
		PcmSupplyInfo psi = new PcmSupplyInfo();
		psi.setShopSid(dto.getStoreCode());
		psi.setSupplyCode(dto.getSupplierCode());
		List<PcmSupplyInfo> supList = supMapper.selectListByParam(psi);
		if (supList.size() != 1) {
			throw new BleException(ErrorCode.SUPPLYINFO_NULL.getMemo());
		}
		Double tem = Double.parseDouble(dto.getCommissionRate());
		entity.setCommissionRate(new BigDecimal(String.valueOf(tem / 100)));
		Double tem2 = Double.parseDouble(dto.getInputTax());
		entity.setInputTax(new BigDecimal(String.valueOf(tem2 / 100)));
		Double tem3 = Double.parseDouble(dto.getSalesTax());
		entity.setSalesTax(new BigDecimal(String.valueOf(tem3 / 100)));
		Double tem4 = Double.parseDouble(dto.getOutputTax());
		entity.setOutputTax(new BigDecimal(String.valueOf(tem4 / 100)));

		entity.setDiscountLimit(new BigDecimal(dto.getDiscountLimit()));
		if (StringUtils.isNotBlank(dto.getFormatType())) {
			entity.setFormatType(Integer.parseInt(dto.getFormatType()));
		}
		if (Constants.Y.equals(dto.getIsAdjustPrice())) {
			entity.setIsAdjustPrice(Constants.PCMERPPRODUCT_IS_ADJUST_PRICE_YES);
		} else if (Constants.N.equals(dto.getIsAdjustPrice())) {
			entity.setIsAdjustPrice(Constants.PCMERPPRODUCT_IS_ADJUST_PRICE_NO);
		}
		if (Constants.Y.equals(dto.getIsPromotion())) {
			entity.setIsPromotion(Constants.PCMERPPRODUCT_IS_PROMOTION_YES);
		} else if (Constants.N.equals(dto.getIsPromotion())) {
			entity.setIsPromotion(Constants.PCMERPPRODUCT_IS_PROMOTION_NO);
		}
		entity.setManageCategory(dto.getManageCategory());
		entity.setOriginLand(dto.getOriginLand());
		if (StringUtils.isNotBlank(dto.getProductCategory())) {
			entity.setProductCategory(Integer.parseInt(dto.getProductCategory()));
		}
		if (Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z1_STR.equals(dto.getProductType())) {
			entity.setProductType(Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z1);
		} else if (Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z2_STR.equals(dto.getProductType())) {
			entity.setProductType(Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z2);
		} else if (Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z3_STR.equals(dto.getProductType())) {
			entity.setProductType(Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z3);
		} else if (Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z4_STR.equals(dto.getProductType())) {
			entity.setProductType(Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z4);
		} else if (Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z5_STR.equals(dto.getProductType())) {
			entity.setProductType(Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z5);
		} else {
			entity.setProductType(Integer.parseInt(dto.getProductType()));
		}
		entity.setProductName(dto.getProductName());
		// if
		// (Constants.PCMERPPRODUCT_PRO_STATUS_NORMAL_STR.equals(dto.getStatus()))
		// {
		// entity.setProStatus(Constants.PCMERPPRODUCT_PRO_STATUS_NORMAL);
		// } else if
		// (Constants.PCMERPPRODUCT_PRO_STATUS_STOPSALE_STR.equals(dto.getStatus()))
		// {
		// entity.setProStatus(Constants.PCMERPPRODUCT_PRO_STATUS_STOPSALE);
		// } else if
		// (Constants.PCMERPPRODUCT_PRO_STATUS_STOP_STR.equals(dto.getStatus()))
		// {
		// entity.setProStatus(Constants.PCMERPPRODUCT_PRO_STATUS_STOP);
		// } else if
		// (Constants.PCMERPPRODUCT_PRO_STATUS_PAUSE_STR.equals(dto.getStatus()))
		// {
		// entity.setProStatus(Constants.PCMERPPRODUCT_PRO_STATUS_PAUSE);
		// } else if
		// (Constants.PCMERPPRODUCT_PRO_STATUS_DELETE_STR.equals(dto.getStatus()))
		// {
		// entity.setProStatus(Constants.PCMERPPRODUCT_PRO_STATUS_DELETE);
		// } else if
		// (Constants.PCMERPPRODUCT_PRO_STATUS_PASS_STR.equals(dto.getStatus()))
		// {
		// entity.setProStatus(Constants.PCMERPPRODUCT_PRO_STATUS_PASS);
		// }
		String status = dto.getStatus();
		if (com.wangfj.core.utils.StringUtils.isNotEmpty(status)) {
			entity.setProStatus(Integer.parseInt(status));
		}
		entity.setSalesPrice(new BigDecimal(dto.getSalesPrice()));
		entity.setBigCodePrice(new BigDecimal(dto.getSalesPrice()));
		entity.setSupplierBarcode(dto.getSupplierBarcode());
		entity.setStatCategory(dto.getStatCategory());
		entity.setStanName(dto.getSpecName());
		entity.setSalesUnit(dto.getSalesUnit());
		entity.setServiceFeeType(dto.getServiceFeeType());
		entity.setUpdateTime(new Date());
		return entity;
	}

	/**
	 * 根据DTO创建实体
	 *
	 * @param GetErpProductFromEfutureDto
	 *            dto
	 * @return PcmErpProduct entity
	 * @Methods Name createEntity
	 * @Create In 2015年8月7日 By zhangxy
	 */
	private PcmErpProduct createEntity(GetErpProductFromEfutureDto dto) {
		PcmErpProduct entity = new PcmErpProduct();
		entity.setOfferNo(dto.getContractCode());
		if (StringUtils.isNotBlank(dto.getSkuType())) {
			entity.setCodeType(Integer.parseInt(dto.getSkuType()));
		}
		if (StringUtils.isNotBlank(dto.getACTION_PERSON())) {
			entity.setOptUserSid(dto.getACTION_PERSON());
		}
		entity.setBrandCode(dto.getBrandCode());
		entity.setArticleNum(dto.getArticleNum());
		entity.setProductCode(dto.getProductCode());
		entity.setShoppeCode(dto.getCounterCode());
		// 根据专柜编码查询专柜名
		Map<String, Object> shoppeMap = new HashMap<String, Object>();
		shoppeMap.put("shoppeCode", dto.getCounterCode());
		List<PcmShoppe> shoppelist = shoppeMapper.selectListByParam(shoppeMap);
		if (shoppelist.size() != 0) {
			if (dto.getSkuType().equals("4") || dto.getSkuType().equals("6")
					|| dto.getSkuType().equals("5")) {
				if (shoppelist.get(0).getShoppeType().equals(Constants.PCM_SHOPPE_TYPE_ERP)) {
					logger.info(ErrorCode.PCM_SHOPPE_TYP_ERP.getMemo());
					throw new BleException(ErrorCode.PCM_SHOPPE_TYP_ERP.getErrorCode(),
							ErrorCode.PCM_SHOPPE_TYP_ERP.getMemo());
				}
			} else {
				if (shoppelist.get(0).getShoppeType().equals(Constants.PCM_SHOPPE_TYPE_PRO)) {
					logger.info(ErrorCode.PCM_SHOPPE_TYP_PRO.getMemo());
					throw new BleException(ErrorCode.PCM_SHOPPE_TYP_PRO.getErrorCode(),
							ErrorCode.PCM_SHOPPE_TYP_PRO.getMemo());
				}
			}
			entity.setShoppeName(shoppelist.get(Constants.PUBLIC_0).getShoppeName());
		} else {
			throw new BleException(ErrorCode.SHOPPE_NULL.getErrorCode(),
					ErrorCode.SHOPPE_NULL.getMemo());
		}
		entity.setStoreCode(dto.getStoreCode());
		// 根据门店编码查询门店名
		Map<String, Object> orgMap = new HashMap<String, Object>();
		orgMap.put("organizationCode", dto.getStoreCode());
		List<PcmOrganization> orglist = orgMapper.selectListByParam(orgMap);
		if (orglist.size() == 1) {
			entity.setShopName(orglist.get(Constants.PUBLIC_0).getOrganizationName());
		} else {
			throw new BleException(ErrorCode.SHOP_NULL.getMemo());
		}
		entity.setSupplyCode(dto.getSupplierCode());
		PcmSupplyInfo psi = new PcmSupplyInfo();
		psi.setShopSid(dto.getStoreCode());
		psi.setSupplyCode(dto.getSupplierCode());
		List<PcmSupplyInfo> supList = supMapper.selectListByParam(psi);
		if (supList.size() != 1) {
			throw new BleException(ErrorCode.SUPPLYINFO_NULL.getMemo());
		}
		Double tem = Double.parseDouble(dto.getCommissionRate());
		entity.setCommissionRate(new BigDecimal(String.valueOf(tem / 100)));
		Double tem2 = Double.parseDouble(dto.getInputTax());
		entity.setInputTax(new BigDecimal(String.valueOf(tem2 / 100)));
		Double tem3 = Double.parseDouble(dto.getSalesTax());
		entity.setSalesTax(new BigDecimal(String.valueOf(tem3 / 100)));
		Double tem4 = Double.parseDouble(dto.getOutputTax());
		entity.setOutputTax(new BigDecimal(String.valueOf(tem4 / 100)));

		entity.setDiscountLimit(new BigDecimal(dto.getDiscountLimit()));
		if (StringUtils.isNotBlank(dto.getFormatType())) {
			entity.setFormatType(Integer.parseInt(dto.getFormatType()));
		}
		if (Constants.Y.equals(dto.getIsAdjustPrice())) {
			entity.setIsAdjustPrice(Constants.PCMERPPRODUCT_IS_ADJUST_PRICE_YES);
		} else if (Constants.N.equals(dto.getIsAdjustPrice())) {
			entity.setIsAdjustPrice(Constants.PCMERPPRODUCT_IS_ADJUST_PRICE_NO);
		}
		if (Constants.Y.equals(dto.getIsPromotion())) {
			entity.setIsPromotion(Constants.PCMERPPRODUCT_IS_PROMOTION_YES);
		} else if (Constants.N.equals(dto.getIsPromotion())) {
			entity.setIsPromotion(Constants.PCMERPPRODUCT_IS_PROMOTION_NO);
		}
		entity.setManageCategory(dto.getManageCategory());
		entity.setOriginLand(dto.getOriginLand());
		if (StringUtils.isNotBlank(dto.getProductCategory())) {
			entity.setProductCategory(Integer.parseInt(dto.getProductCategory()));
		}
		if (Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z1_STR.equals(dto.getProductType())) {
			entity.setProductType(Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z1);
		} else if (Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z2_STR.equals(dto.getProductType())) {
			entity.setProductType(Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z2);
		} else if (Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z3_STR.equals(dto.getProductType())) {
			entity.setProductType(Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z3);
		} else if (Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z4_STR.equals(dto.getProductType())) {
			entity.setProductType(Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z4);
		} else if (Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z5_STR.equals(dto.getProductType())) {
			entity.setProductType(Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z5);
		} else {
			entity.setProductType(Integer.parseInt(dto.getProductType()));
		}
		entity.setProductName(dto.getProductName());
		// if
		// (Constants.PCMERPPRODUCT_PRO_STATUS_NORMAL_STR.equals(dto.getStatus()))
		// {
		// entity.setProStatus(Constants.PCMERPPRODUCT_PRO_STATUS_NORMAL);
		// } else if
		// (Constants.PCMERPPRODUCT_PRO_STATUS_STOPSALE_STR.equals(dto.getStatus()))
		// {
		// entity.setProStatus(Constants.PCMERPPRODUCT_PRO_STATUS_STOPSALE);
		// } else if
		// (Constants.PCMERPPRODUCT_PRO_STATUS_STOP_STR.equals(dto.getStatus()))
		// {
		// entity.setProStatus(Constants.PCMERPPRODUCT_PRO_STATUS_STOP);
		// } else if
		// (Constants.PCMERPPRODUCT_PRO_STATUS_PAUSE_STR.equals(dto.getStatus()))
		// {
		// entity.setProStatus(Constants.PCMERPPRODUCT_PRO_STATUS_PAUSE);
		// } else if
		// (Constants.PCMERPPRODUCT_PRO_STATUS_DELETE_STR.equals(dto.getStatus()))
		// {
		// entity.setProStatus(Constants.PCMERPPRODUCT_PRO_STATUS_DELETE);
		// } else if
		// (Constants.PCMERPPRODUCT_PRO_STATUS_PASS_STR.equals(dto.getStatus()))
		// {
		// entity.setProStatus(Constants.PCMERPPRODUCT_PRO_STATUS_PASS);
		// }
		String status = dto.getStatus();
		if (com.wangfj.core.utils.StringUtils.isNotEmpty(status)) {
			entity.setProStatus(Integer.parseInt(status));
		}
		entity.setSalesPrice(new BigDecimal(dto.getSalesPrice()));
		entity.setBigCodePrice(new BigDecimal(dto.getSalesPrice()));
		entity.setSupplierBarcode(dto.getSupplierBarcode());
		entity.setStatCategory(dto.getStatCategory());
		entity.setStanName(dto.getSpecName());
		entity.setSalesUnit(dto.getSalesUnit());
		entity.setServiceFeeType(dto.getServiceFeeType());
		entity.setUpdateTime(new Date());
		return entity;
	}

	private Integer addPriceRecord(PcmErpProduct entity) {
		PcmPrice pp = new PcmPrice();
		pp.setShoppeProSid(entity.getProductCode());// 大码商品编码
		pp.setAttribute2(entity.getStoreCode());// 门店编码
		// 价格开始,结束时间
		pp.setPromotionBeginTime(
				DateUtil.formatDate(DateUtil.formatToStr(new Date(), "yyyyMMdd"), "yyyyMMdd")); // 开始时间
		pp.setPromotionEndTime(DateUtil.formatDate("99991231235959", "yyyyMMddHHmmss"));// 结束时间
		pp.setPriceType(Constants.PRICE_TYPE_1);// 永久变价
		pp.setAttribute1(Constants.DEFAULT_CHANGE_CODE);// 变价号
		pp.setChannelSid(Constants.DEFAULT_CHANNEL_SID); // 渠道
		pp.setCurrentPrice(entity.getSalesPrice());// 现价
		pp.setPromotionPrice(entity.getSalesPrice());// 促销价格
		pp.setOriginalPrice(entity.getSalesPrice());// 原价
		pp.setOffValue(new BigDecimal(1));// 折扣值，后台程序计算
		pp.setUnit("RMB");// 货币单位
		int i = priceService.initProductPriceInfo(pp);
		return i;
	}

	/**
	 * 从门店ERP上传到PCM
	 *
	 * @param list
	 * @Methods Name getErpProductFromEFuture
	 * @Create In 2015年7月13日 By zhangxy
	 */
	@Override
	@Transactional
	public PublishDTO saveErpProductFromEFuture(GetErpProductFromEfutureDto dto)
			throws BleException {
		logger.info("start saveErpProduct(),param:" + dto.toString());
		PublishDTO sidDto = new PublishDTO();
		if (Constants.A.equals(dto.getACTION_CODE().toUpperCase())) {
			// 根据erp商品编码,门店编码判重
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("productCode", dto.getProductCode());
			map.put("storeCode", dto.getStoreCode()); // 门店编码
			Integer count = erpProductMapper.getCountByParam(map);
			if (count > Constants.PUBLIC_0) {
				// ERP商品已存在
				logger.error(dto.getStoreCode() + "-" + dto.getProductCode() + "--"
						+ ErrorCode.UPDATE_HAVE_ERROR.getErrorCode()
						+ ErrorCode.UPDATE_HAVE_ERROR.getMemo());
				throw new BleException(ErrorCode.UPDATE_HAVE_ERROR.getErrorCode(),
						ErrorCode.UPDATE_HAVE_ERROR.getMemo());
			} else {
				// 判断门店与门店品牌关系
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("shopCode", dto.getStoreCode());
				paramMap.put("brandCode", dto.getBrandCode());
				List<Map<String, Object>> brandShopInfo = erpProductMapper
						.getBrandShopInfo(paramMap);
				if (brandShopInfo != null && brandShopInfo.size() > 0) {
					// ERP商品不存在
					PcmErpProduct entity = createEntity(dto);
					Integer result = erpProductMapper.insertSelective(entity);// 创建实体
					if (result.intValue() != Constants.PUBLIC_1) {
						logger.error(dto.getStoreCode() + "-" + dto.getProductCode() + "--"
								+ ErrorCode.DATA_OPER_ERROR.getErrorCode()
								+ ErrorCode.DATA_OPER_ERROR.getMemo());// 此处输出逻辑错误
						throw new BleException(ErrorCode.DATA_OPER_ERROR.getErrorCode(),
								ErrorCode.DATA_OPER_ERROR.getMemo());
					}
					Integer resultPrice = addPriceRecord(entity);// 创建价格信息
					if (resultPrice == Constants.PUBLIC_0) {
						throw new BleException(ErrorCode.ADD_PRODUCT_PRICE_ERROR.getErrorCode(),
								ErrorCode.ADD_PRODUCT_PRICE_ERROR.getMemo());
					}
					sidDto.setSid(entity.getSid());
					sidDto.setType(0);
				} else {
					logger.error("门店品牌与门店关系不存在");
					throw new BleException(ErrorCode.BRAND_SHOP_RELATION_IS_NULL.getErrorCode(),
							ErrorCode.BRAND_SHOP_RELATION_IS_NULL.getMemo());
				}
			}
		} else if (Constants.U.equals(dto.getACTION_CODE().toUpperCase())) {
			// 根据erp商品编码查询是否存在
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("productCode", dto.getProductCode());
			map.put("storeCode", dto.getStoreCode()); // 门店编码
			List<PcmErpProduct> listPro = erpProductMapper.selectListByParam(map);
			if (listPro != null && listPro.size() == Constants.PUBLIC_1) {
				// ERP商品已存在
				PcmErpProduct entity = createEntity(dto);// 创建实体
				entity.setSid(listPro.get(0).getSid());// sid
				if (listPro.get(0).getProductType() == 0 || listPro.get(0).getProductType() == 1) {
					entity.setSupplyCode(dto.getSupplierCode());
				} else {
					entity.setSupplyCode(null);
				}
				entity.setBrandCode(null);
				entity.setShoppeCode(null);
				entity.setShoppeName(null);
				Integer result = erpProductMapper.updateByPrimaryKeySelective(entity);
				if (result.intValue() != Constants.PUBLIC_1) {
					logger.error(dto.getStoreCode() + "-" + dto.getProductCode() + "--"
							+ ErrorCode.UPDATE_HAVE_ERROR.getErrorCode()
							+ ErrorCode.UPDATE_HAVE_ERROR.getMemo());// 此处输出逻辑错误
					throw new BleException(ErrorCode.UPDATE_HAVE_ERROR.getErrorCode(),
							ErrorCode.UPDATE_HAVE_ERROR.getMemo());
				}
				sidDto.setSid(entity.getSid());
				sidDto.setType(1);
				if ("4".equals(dto.getSkuType())) {
					sidDto.setSid(Long.parseLong(dto.getProductCode()));
					sidDto.setType(-2);
				}
			} else if ("6".equals(dto.getSkuType())) {
				if (StringUtils.isNotBlank(dto.getStatus())) {
					try {
						UpdateProductInfoDto updto = new UpdateProductInfoDto();
						updto.setStatus(Integer.parseInt(dto.getStatus()));
						updto.setProductCode(dto.getProductCode());
						proService.updateProductInfo(updto);
						sidDto.setSid(Long.parseLong(updto.getProductCode()));
						sidDto.setType(-1);
					} catch (NumberFormatException e) {
						logger.error(dto.getStoreCode() + "-" + dto.getProductCode() + "--"
								+ ErrorCode.PARA_NORULE_ERROR.getErrorCode()
								+ ErrorCode.PARA_NORULE_ERROR.getMemo());
						throw new BleException(ErrorCode.PARA_NORULE_ERROR.getErrorCode(),
								ErrorCode.PARA_NORULE_ERROR.getMemo());
					}
				}

			} else {
				// ERP商品不存在
				logger.error(dto.getStoreCode() + "-" + dto.getProductCode() + "--"
						+ ErrorCode.PRODUCT_NULL.getErrorCode() + ErrorCode.PRODUCT_NULL.getMemo());
				throw new BleException(ErrorCode.PRODUCT_NULL.getErrorCode(),
						ErrorCode.PRODUCT_NULL.getMemo());
			}
		} else if (Constants.D.equals(dto.getACTION_CODE().toUpperCase())) {
			// 组包码可删除
			if (dto.getProductCategory().equals("09")) {
				PcmErpProduct erpEntity = new PcmErpProduct();
				erpEntity.setProductCode(dto.getProductCode());
				erpEntity.setStoreCode(dto.getStoreCode());
				erpEntity.setProStatus(0);
				List<PcmErpProduct> erpList = erpProductMapper.selectListByParam(erpEntity);
				if (erpList != null && erpList.size() > 0) {
					erpEntity.setSid(erpList.get(0).getSid());
					erpEntity.setProStatus(1);
					erpProductMapper.updateByPrimaryKeySelective(erpEntity);
					sidDto.setSid(erpEntity.getSid());
					sidDto.setType(1);
				}
			} else {
				logger.error(dto.getStoreCode() + "-" + dto.getProductCode() + "--"
						+ ErrorCode.UNSUPPORT_ACTIONCODE.getErrorCode()
						+ ErrorCode.UNSUPPORT_ACTIONCODE.getMemo());
				throw new BleException(ErrorCode.UNSUPPORT_ACTIONCODE.getErrorCode(),
						ErrorCode.UNSUPPORT_ACTIONCODE.getMemo());
			}
		}
		logger.info("end saveErpProduct()");
		return sidDto;
	}

	/**
	 * 从门店ERP上传到PCM
	 *
	 * @param list
	 * @Methods Name getErpProductFromEFuture
	 * @Create In 2015年7月13日 By zhangxy
	 */
	@Override
	@Transactional
	public PublishDTO saveErpProductFromEFuture2(GetErpProductFromEfutureDto dto)
			throws BleException {
		logger.info("start saveErpProduct(),param:" + dto.toString());
		PublishDTO sidDto = new PublishDTO();
		if (Constants.A.equals(dto.getACTION_CODE().toUpperCase())) {
			// 根据erp商品编码,门店编码判重
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("productCode", dto.getProductCode());
			map.put("storeCode", dto.getStoreCode()); // 门店编码
			Integer count = erpProductMapper.getCountByParam(map);
			if (count > Constants.PUBLIC_0) {
				// ERP商品已存在
				logger.error(dto.getStoreCode() + "-" + dto.getProductCode() + "--"
						+ ErrorCode.UPDATE_HAVE_ERROR.getErrorCode()
						+ ErrorCode.UPDATE_HAVE_ERROR.getMemo());
				throw new BleException(ErrorCode.UPDATE_HAVE_ERROR.getErrorCode(),
						ErrorCode.UPDATE_HAVE_ERROR.getMemo());
			} else {
				// 判断门店与门店品牌关系
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("shopCode", dto.getStoreCode());
				paramMap.put("brandCode", dto.getBrandCode());
				List<Map<String, Object>> brandShopInfo = erpProductMapper
						.getBrandShopInfo(paramMap);
				if (brandShopInfo != null && brandShopInfo.size() > 0) {
					// ERP商品不存在
					PcmErpProduct entity = createEntity2(dto);
					Integer result = erpProductMapper.insertSelective(entity);// 创建实体
					if (result.intValue() != Constants.PUBLIC_1) {
						logger.error(dto.getStoreCode() + "-" + dto.getProductCode() + "--"
								+ ErrorCode.DATA_OPER_ERROR.getErrorCode()
								+ ErrorCode.DATA_OPER_ERROR.getMemo());// 此处输出逻辑错误
						throw new BleException(ErrorCode.DATA_OPER_ERROR.getErrorCode(),
								ErrorCode.DATA_OPER_ERROR.getMemo());
					}
					sidDto.setSid(entity.getSid());
					sidDto.setType(0);
				} else {
					logger.error("门店品牌与门店关系不存在");
					throw new BleException(ErrorCode.BRAND_SHOP_RELATION_IS_NULL.getErrorCode(),
							ErrorCode.BRAND_SHOP_RELATION_IS_NULL.getMemo());
				}
			}
		} else if (Constants.U.equals(dto.getACTION_CODE().toUpperCase())) {
			// 根据erp商品编码查询是否存在
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("productCode", dto.getProductCode());
			map.put("storeCode", dto.getStoreCode()); // 门店编码
			List<PcmErpProduct> listPro = erpProductMapper.selectListByParam(map);
			if (listPro != null && listPro.size() == Constants.PUBLIC_1) {
				// ERP商品已存在
				if (dto.getBrandCode() != null
						&& !dto.getBrandCode().equals(listPro.get(0).getBrandCode())) {
					logger.error(dto.getStoreCode() + "-" + dto.getProductCode() + "--"
							+ ErrorCode.UNSUPPORT_ACTION.getErrorCode()
							+ ErrorCode.UNSUPPORT_ACTION.getMemo() + "品牌");
					throw new BleException(ErrorCode.UNSUPPORT_ACTION.getErrorCode(),
							ErrorCode.UNSUPPORT_ACTION.getMemo());
				}
				if (dto.getCounterCode() != null
						&& !dto.getCounterCode().equals(listPro.get(0).getShoppeCode())) {
					logger.error(dto.getStoreCode() + "-" + dto.getProductCode() + "--"
							+ ErrorCode.UNSUPPORT_ACTION.getErrorCode()
							+ ErrorCode.UNSUPPORT_ACTION.getMemo() + "专柜");
					throw new BleException(ErrorCode.UNSUPPORT_ACTION.getErrorCode(),
							ErrorCode.UNSUPPORT_ACTION.getMemo());
				}
				if (dto.getSupplierCode() != null
						&& !dto.getSupplierCode().equals(listPro.get(0).getSupplyCode())) {
					logger.error(dto.getStoreCode() + "-" + dto.getProductCode() + "--"
							+ ErrorCode.UNSUPPORT_ACTION.getErrorCode()
							+ ErrorCode.UNSUPPORT_ACTION.getMemo() + "供应商");
					throw new BleException(ErrorCode.UNSUPPORT_ACTION.getErrorCode(),
							ErrorCode.UNSUPPORT_ACTION.getMemo());
				}
				PcmErpProduct entity = createEntity(dto);// 创建实体
				entity.setSid(listPro.get(0).getSid());// sid
				Integer result = erpProductMapper.updateByPrimaryKeySelective(entity);
				if (result.intValue() != Constants.PUBLIC_1) {
					logger.error(dto.getStoreCode() + "-" + dto.getProductCode() + "--"
							+ ErrorCode.UPDATE_HAVE_ERROR.getErrorCode()
							+ ErrorCode.UPDATE_HAVE_ERROR.getMemo());// 此处输出逻辑错误
					throw new BleException(ErrorCode.UPDATE_HAVE_ERROR.getErrorCode(),
							ErrorCode.UPDATE_HAVE_ERROR.getMemo());
				}
				sidDto.setSid(entity.getSid());
				sidDto.setType(1);
				if ("4".equals(dto.getSkuType())) {
					sidDto.setSid(Long.parseLong(dto.getProductCode()));
					sidDto.setType(-2);
				}
			} else if ("6".equals(dto.getSkuType())) {
				if (StringUtils.isNotBlank(dto.getStatus())) {
					try {
						UpdateProductInfoDto updto = new UpdateProductInfoDto();
						updto.setStatus(Integer.parseInt(dto.getStatus()));
						updto.setProductCode(dto.getProductCode());
						proService.updateProductInfo(updto);
						sidDto.setSid(Long.parseLong(updto.getProductCode()));
						sidDto.setType(-1);
					} catch (NumberFormatException e) {
						logger.error(dto.getStoreCode() + "-" + dto.getProductCode() + "--"
								+ ErrorCode.PARA_NORULE_ERROR.getErrorCode()
								+ ErrorCode.PARA_NORULE_ERROR.getMemo());
						throw new BleException(ErrorCode.PARA_NORULE_ERROR.getErrorCode(),
								ErrorCode.PARA_NORULE_ERROR.getMemo());
					}
				}

			} else {
				// ERP商品不存在
				logger.error(dto.getStoreCode() + "-" + dto.getProductCode() + "--"
						+ ErrorCode.PRODUCT_NULL.getErrorCode() + ErrorCode.PRODUCT_NULL.getMemo());
				throw new BleException(ErrorCode.PRODUCT_NULL.getErrorCode(),
						ErrorCode.PRODUCT_NULL.getMemo());
			}
		} else if (Constants.D.equals(dto.getACTION_CODE().toUpperCase())) {
			// 组包码可删除
			if (dto.getProductCategory().equals("09")) {
				PcmErpProduct erpEntity = new PcmErpProduct();
				erpEntity.setProductCode(dto.getProductCode());
				erpEntity.setStoreCode(dto.getStoreCode());
				erpEntity.setProStatus(0);
				List<PcmErpProduct> erpList = erpProductMapper.selectListByParam(erpEntity);
				if (erpList != null && erpList.size() > 0) {
					erpEntity.setSid(erpList.get(0).getSid());
					erpEntity.setProStatus(1);
					erpProductMapper.updateByPrimaryKeySelective(erpEntity);
					sidDto.setSid(erpEntity.getSid());
					sidDto.setType(1);
				}
			} else {
				logger.error(dto.getStoreCode() + "-" + dto.getProductCode() + "--"
						+ ErrorCode.UNSUPPORT_ACTIONCODE.getErrorCode()
						+ ErrorCode.UNSUPPORT_ACTIONCODE.getMemo());
				throw new BleException(ErrorCode.UNSUPPORT_ACTIONCODE.getErrorCode(),
						ErrorCode.UNSUPPORT_ACTIONCODE.getMemo());
			}
		}
		logger.info("end saveErpProduct()");
		return sidDto;
	}

	/**
	 * ERP商品换品牌、专柜、供应商（添加单据）
	 *
	 * @param dto
	 * @Methods Name modifyErpChangeFromEFutureForBill
	 * @Create In 2016年2月25日 By wangxuan
	 */
	@Override
	@Transactional
	public Integer modifyErpChangeFromEFutureForBill(ErpChangeDto dto) throws BleException {
		logger.info("start modifyErpChangeFromEFutureForBill(),param:" + dto.toString());
		// 判断生效日期是否大于或等于当前日期
		String dateStr = dto.getDATE();
		Date date = DateUtil.formatDate(dateStr, "yyyyMMdd");
		Date nowDate = new Date();
		String nowDateStr = DateUtil.formatToStr(nowDate, "yyyyMMdd");
		Integer result = Constants.PUBLIC_0;
		if (nowDate.before(date) || nowDateStr.equals(dateStr)) {
			// 判断大码信息是否存在
			Map<String, Object> para = new HashMap<String, Object>();
			para.put("storeCode", dto.getSTORE());
			para.put("productCode", dto.getPRODUCT());
			List<PcmErpProduct> list1 = erpProductMapper.selectListByParam(para);
			if (list1.size() == 1) {
				String xglb = "";
				PcmErpProductChange erpProductChange = new PcmErpProductChange();
				// 判断操作代码是否为 1(改品牌)；3（换专柜）5(换供应商）
				if (Constants.PUBLIC_1 == Integer.parseInt(dto.getXGLB())) {
					PcmBrand entity = new PcmBrand();
					entity.setBrandSid(dto.getVALUE());
					entity.setBrandType(1);// 门店品牌
					List<PcmBrand> brList = brMapper.selectListByParam(entity);
					if (brList == null || brList.size() != 1) {
						throw new BleException(ErrorCode.BRAND_NOT_EXIST.getErrorCode(),
								ErrorCode.BRAND_NOT_EXIST.getMemo());
					}
					xglb = "品牌";
					erpProductChange.setOldValue(brList.get(0).getBrandSid());
				} else if (Constants.PUBLIC_3 == Integer.parseInt(dto.getXGLB())) {
					Map<String, Object> shoppeMap = new HashMap<String, Object>();
					shoppeMap.put("shoppeCode", dto.getVALUE());
					List<PcmShoppe> shoppelist = shoppeMapper.selectListByParam(shoppeMap);
					if (shoppelist == null || shoppelist.size() != 1) {
						throw new BleException(ErrorCode.SHOPPE_NULL.getErrorCode(),
								ErrorCode.SHOPPE_NULL.getMemo());
					}
					xglb = "专柜";
					erpProductChange.setOldValue(shoppelist.get(0).getShoppeCode());
				} else if (Constants.PUBLIC_5 == Integer.parseInt(dto.getXGLB())) {
					PcmSupplyInfo psi = new PcmSupplyInfo();
					psi.setShopSid(dto.getSTORE());
					psi.setSupplyCode(dto.getVALUE().substring(0, 7));
					psi.setStatus("Y");
					List<PcmSupplyInfo> supList = supMapper.selectListByParam(psi);
					if (supList == null || supList.size() != 1) {
						throw new BleException(ErrorCode.SUPPLYINFO_NULL.getErrorCode(),
								ErrorCode.SUPPLYINFO_NULL.getMemo());
					}
					xglb = "供应商";
					erpProductChange.setOldValue(supList.get(0).getSupplyCode());
				} else {
					logger.error("modifyErpChangeFromEFutureForBill(),"
							+ ErrorCode.XGLB_ERROR.getMemo() + ":" + dto.toString());
					throw new BleException(ErrorCode.XGLB_ERROR.getErrorCode(),
							ErrorCode.XGLB_ERROR.getMemo());
				}
				erpProductChange.setStoreCode(dto.getSTORE());
				erpProductChange.setActiveTime(date);
				erpProductChange.setBillNo(dto.getSEQNO());
				erpProductChange.setBillType(Integer.parseInt(dto.getXGLB()));
				erpProductChange.setCreateName(dto.getActionPerson());
				erpProductChange.setIsScan(Constants.PUBLIC_0);
				erpProductChange.setJsonText(dto.toString());
				if (dto.getXGLB().equals("5")) {
					erpProductChange
							.setNewValue(dto.getVALUE().substring(0, 7) + ";" + dto.getVALUE());
				} else {
					erpProductChange.setNewValue(dto.getVALUE());
				}
				erpProductChange.setProductCode(dto.getPRODUCT());
				erpProductChange.setRowno(dto.getROWNO());
				result = erpProductChangeMapper.insertSelective(erpProductChange);
				if (result != 1) {
					logger.error("modifyErpChangeFromEFutureForBill(),添加( " + xglb + " )单据信息失败"
							+ dto.toString());
					throw new BleException(ErrorCode.DATA_OPER_ERROR.getErrorCode(),
							ErrorCode.DATA_OPER_ERROR.getMemo());
				}
			} else {
				// 无此结果
				logger.error("modifyErpChangeFromEFutureForBill(),"
						+ ErrorCode.PRODUCT_NULL.getMemo() + ":" + dto.toString());
				throw new BleException(ErrorCode.PRODUCT_NULL.getErrorCode(),
						ErrorCode.PRODUCT_NULL.getMemo());
			}
		} else {
			// 超过生效日期
			logger.error("modifyErpChangeFromEFutureForBill()," + ErrorCode.DATE_ERROR.getMemo()
					+ dto.toString());
			throw new BleException(ErrorCode.DATE_ERROR.getErrorCode(),
					ErrorCode.DATE_ERROR.getMemo());
		}
		logger.info("end modifyErpChangeFromEFutureForBill(),return:" + result);
		return result;
	}

	/**
	 * ERP商品换品牌、专柜、供应商(查询当天需要执行的单据)
	 *
	 * @Methods Name modifyErpChangeFromEFuture
	 * @Create In 2016年2月26日 By wangxuan
	 */
	@Override
	@Transactional
	public List<ErpChangeDto> modifyErpChangeFromEFutureActive() throws BleException {
		logger.info("start modifyErpChangeFromEFuture(),param:");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("isScan", Constants.PUBLIC_0);
		List<PcmErpProductChange> list = erpProductChangeMapper.selectListByCurrentDate(paramMap);
		List<ErpChangeDto> erpChangeDtoList = new ArrayList<ErpChangeDto>();
		if (list != null && list.size() > 0) {
			for (PcmErpProductChange tempEntity : list) {
				ErpChangeDto dto = new ErpChangeDto();
				if (tempEntity.getBillType() == 5) {
					String[] split = tempEntity.getNewValue().split(";");
					if (split.length > 1) {
						dto.setContractCode(split[1]);
						dto.setVALUE(split[0]);
					} else {
						dto.setVALUE(tempEntity.getNewValue());
					}
				} else {
					dto.setVALUE(tempEntity.getNewValue());
				}
				dto.setActionPerson(tempEntity.getCreateName());
				Date activeTime = tempEntity.getActiveTime();
				dto.setDATE(DateUtil.formatToStr(activeTime, "yyyyMMdd"));
				dto.setPRODUCT(tempEntity.getProductCode());
				dto.setROWNO(tempEntity.getRowno());
				dto.setSEQNO(tempEntity.getBillNo());
				dto.setSTORE(tempEntity.getStoreCode());
				// dto.setVALUE(tempEntity.getNewValue());
				dto.setXGLB(tempEntity.getBillType() + "");
				erpChangeDtoList.add(dto);
				PcmErpProductChange entity = new PcmErpProductChange();
				entity.setSid(tempEntity.getSid());
				entity.setIsScan(Constants.PUBLIC_1);
				erpProductChangeMapper.updateByPrimaryKeySelective(entity);
			}
		}
		logger.info("end modifyErpChangeFromEFuture()，return:" + erpChangeDtoList.toString());
		return erpChangeDtoList;
	}

	/**
	 * ERP商品换品牌、专柜、供应商
	 *
	 * @param dto
	 * @Methods Name modifyErpChangeFromEFuture
	 * @Create In 2015年7月16日 By zhangxy
	 */
	@Override
	@Transactional
	public ModifyErpProductDto modifyErpChangeFromEFuture(ErpChangeDto dto) throws BleException {
		logger.info("start modifyErpChangeFromEFuture(),param:" + dto.toString());
		// 判断生效日期是等于当前日期
		// String dateStr = dto.getDATE();
		// Date date = DateUtil.formatDate(dateStr, "yyyyMMdd");
		// Date nowDate = new Date();
		// String nowDateStr = DateUtil.formatToStr(nowDate, "yyyyMMdd");
		ModifyErpProductDto proDto = new ModifyErpProductDto();
		boolean conFalg = true;
		// if (nowDateStr.equals(dateStr)) {
		// 判断大码信息是否存在
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("storeCode", dto.getSTORE());
		para.put("productCode", dto.getPRODUCT());
		List<PcmErpProduct> list1 = erpProductMapper.selectListByParam(para);
		if (list1 != null && list1.size() > 0) {
			PcmErpProduct pep = list1.get(0);
			String xglb = "";
			// 判断操作代码是否为 1(改品牌)；3（换专柜）5(换供应商）
			if (Constants.PUBLIC_1 == Integer.parseInt(dto.getXGLB())) {
				PcmBrand entity = new PcmBrand();
				entity.setBrandSid(dto.getVALUE());
				entity.setBrandType(1);// 门店品牌
				List<PcmBrand> brList = brMapper.selectListByParam(entity);
				if (brList == null || brList.size() != 1) {
					throw new BleException(ErrorCode.BRAND_NOT_EXIST.getErrorCode(),
							ErrorCode.BRAND_NOT_EXIST.getMemo());
				}
				pep.setBrandCode(dto.getVALUE());
				xglb = "品牌";
			} else if (Constants.PUBLIC_3 == Integer.parseInt(dto.getXGLB())) {
				Map<String, Object> shoppeMap = new HashMap<String, Object>();
				shoppeMap.put("shoppeCode", dto.getVALUE());
				List<PcmShoppe> shoppelist = shoppeMapper.selectListByParam(shoppeMap);
				if (shoppelist == null || shoppelist.size() != 1) {
					throw new BleException(ErrorCode.SHOPPE_NULL.getErrorCode(),
							ErrorCode.SHOPPE_NULL.getMemo());
				}
				pep.setShoppeCode(dto.getVALUE());
				pep.setShoppeName(shoppelist.get(0).getShoppeName());
				xglb = "专柜";
			} else if (Constants.PUBLIC_5 == Integer.parseInt(dto.getXGLB())) {
				PcmSupplyInfo psi = new PcmSupplyInfo();
				psi.setShopSid(dto.getSTORE());
				psi.setSupplyCode(dto.getVALUE());
				psi.setStatus("Y");
				List<PcmSupplyInfo> supList = supMapper.selectListByParam(psi);
				if (supList == null || supList.size() != 1) {
					throw new BleException(ErrorCode.SUPPLYINFO_NULL.getErrorCode(),
							ErrorCode.SUPPLYINFO_NULL.getMemo());
				}
				if (pep.getSupplyCode().equals(dto.getVALUE())) {
					pep.setOfferNo(dto.getContractCode());
					conFalg = false;
				} else {
					pep.setOfferNo(dto.getContractCode());
					pep.setSupplyCode(dto.getVALUE());
				}
				xglb = "供应商";
			} else {
				logger.error("modifyErpChangeFromEFuture()," + ErrorCode.XGLB_ERROR.getMemo() + ":"
						+ dto.toString());
				throw new BleException(ErrorCode.XGLB_ERROR.getErrorCode(),
						ErrorCode.XGLB_ERROR.getMemo());
			}
			Integer rec = erpProductMapper.updateByPrimaryKeySelective(pep);
			if (rec != 1) {
				logger.error(
						"modifyErpChangeFromEFuture(),更换( " + xglb + " )信息失败" + dto.toString());
				throw new BleException(ErrorCode.DATA_OPER_ERROR.getErrorCode(),
						ErrorCode.DATA_OPER_ERROR.getMemo());
			}
			proDto.setSid(pep.getSid());
			if (list1.get(0).getCodeType() == 4 && conFalg) {// 扣率码变更属性时修改对应专柜商品的属性
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("erpproductcode", dto.getPRODUCT());
				List<PcmShoppeProduct> proList = proMapper.selectListByParam(paramMap);
				proDto.setList(proList);
			}

			// 联营大码换供应商时，修改一品多商关系表
			boolean flag = Constants.PUBLIC_5 == Integer.parseInt(dto.getXGLB())
					&& list1.get(0).getProductType() == Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z3
					&& conFalg;
			if (flag) {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("shopSid", dto.getSTORE());
				paramMap.put("erpProductSid", dto.getPRODUCT());
				List<PcmErpProductSupply> listU = erpProductSupplyMapper
						.selectListByParam(paramMap);
				if (listU.size() == 1) {
					PcmErpProductSupply pcmErpProductSupply = listU.get(0);
					pcmErpProductSupply.setSupplySid(pep.getSupplyCode());
					pcmErpProductSupply.setStatus(Constants.PUBLIC_0);
					int uResult = erpProductSupplyMapper
							.updateByPrimaryKeySelective(pcmErpProductSupply);
					if (uResult != 1) {
						throw new BleException(
								ErrorCode.SHOPPEPRODUCT_SUPPLY_RELATION_ERROR.getErrorCode(),
								"联营大码换供应商时修改一品多商失败！");
					}

					// 下发联营大码一品多商参数封装
					proDto.setErpProductSupplySid(pcmErpProductSupply.getSid());
				} else if (listU.isEmpty()) {
					throw new BleException(
							ErrorCode.SHOPPEPRODUCT_SUPPLY_RELATION_ERROR.getErrorCode(),
							"联营大码换供应商时大码商品与供应商关系不存在！");
				} else if (listU.size() > 1) {
					throw new BleException(
							ErrorCode.SHOPPEPRODUCT_SUPPLY_RELATION_ERROR.getErrorCode(),
							"联营大码换供应商时存在两条大码商品与供应商关系的重复数据！");
				}
			}
		} else {
			// 无此结果
			logger.error("modifyErpChangeFromEFuture()," + ErrorCode.PRODUCT_NULL.getMemo() + ":"
					+ dto.toString());
			throw new BleException(ErrorCode.PRODUCT_NULL.getErrorCode(),
					ErrorCode.PRODUCT_NULL.getMemo());
		}
		// } else {
		// // 超过生效日期
		// logger.error("modifyErpChangeFromEFuture()," +
		// ErrorCode.DATE_ERROR.getMemo()
		// + dto.toString());
		// throw new BleException(ErrorCode.DATE_ERROR.getErrorCode(),
		// ErrorCode.DATE_ERROR.getMemo());
		// }
		logger.info("end modifyErpChangeFromEFuture(),return:" + proDto.toString());
		return proDto;
	}

	/**
	 * ERP商品基本信息修改-扣率码修改时修改对应专柜商品信息
	 *
	 * @param entity
	 * @param dto
	 * @Methods Name modifyShoppeProduct
	 * @Create In 2015年9月21日 By zhangxy
	 */
	public Boolean modifyShoppeProduct(PcmShoppeProduct entity, ErpChangeDto dto)
			throws BleException {
		if (Constants.PUBLIC_1 == Integer.parseInt(dto.getXGLB())) {
			PcmBrand br = new PcmBrand();
			br.setBrandSid(dto.getVALUE());
			br.setBrandType(1);// 门店品牌
			List<PcmBrand> brList = brMapper.selectListByParam(br);
			if (brList == null || brList.size() != 1) {
				throw new BleException(ErrorCode.BRAND_NOT_EXIST.getErrorCode(),
						ErrorCode.BRAND_NOT_EXIST.getMemo());
			}
			ChangeProductDto cpDto = new ChangeProductDto();
			cpDto.setBrandSid(brList.get(0).getSid());// 门店品牌SID
			cpDto.setSid(entity.getSid());// 专柜商品SID
			cpDto.setActionPerson(dto.getActionPerson());
			cpService.changeGroupBrand(cpDto);
		} else if (Constants.PUBLIC_3 == Integer.parseInt(dto.getXGLB())) {
			ProductCondDto pcDto = new ProductCondDto();
			pcDto.setShoppeProSid(entity.getShoppeProSid());// 专柜商品编码
			pcDto.setShoppeSid(dto.getVALUE());// 专柜编码
			pService.updateProductShoppe(pcDto);
		} else if (Constants.PUBLIC_5 == Integer.parseInt(dto.getXGLB())) {
			PcmSupplyInfo psi = new PcmSupplyInfo();
			psi.setShopSid(dto.getSTORE());
			psi.setSupplyCode(dto.getVALUE());
			psi.setStatus("Y");
			List<PcmSupplyInfo> supList = supMapper.selectListByParam(psi);
			if (supList == null || supList.size() != 1) {
				throw new BleException(ErrorCode.SUPPLYINFO_NULL.getErrorCode(),
						ErrorCode.SUPPLYINFO_NULL.getMemo());
			}
			if (supList.get(0).getBusinessPattern() != 2) {
				throw new BleException(ErrorCode.SUPPLYINFO_BUSSINESS_ERROR.getErrorCode(),
						ErrorCode.SUPPLYINFO_BUSSINESS_ERROR.getMemo());
			}
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.clear();
			paramMap.put("shoppeSid", entity.getShoppeSid());
			paramMap.put("productDetailSid", entity.getProductDetailSid());
			paramMap.put("supplySid", supList.get(0).getSid());
			Integer valid = proMapper.getCountByParam(paramMap);
			if (valid == 0) {
				entity.setSupplySid((supList.get(0).getSid()));
				int res = proMapper.updateByPrimaryKeySelective(entity);
				if (res != 1) {
					logger.error("专柜商品编码:" + entity.getShoppeProSid() + "--错误:"
							+ ErrorCode.DATA_OPER_ERROR.getMemo());
					throw new BleException(ErrorCode.DATA_OPER_ERROR.getErrorCode(),
							"专柜商品编码:" + entity.getShoppeProSid() + "--错误:"
									+ ErrorCode.DATA_OPER_ERROR.getMemo());
				}
			} else {
				logger.error("专柜商品编码:" + entity.getShoppeProSid() + "--错误:"
						+ ErrorCode.SHOPP_IS_EXIST.getMemo());
				throw new BleException(ErrorCode.SHOPP_IS_EXIST.getErrorCode(), "专柜商品编码:"
						+ entity.getShoppeProSid() + "--错误:" + ErrorCode.SHOPP_IS_EXIST.getMemo());
			}
		}
		return true;
	}

	/**
	 * ERP商品查询
	 *
	 * @return List<Map<String,Object>>
	 * @throws Exception
	 * @Methods Name selectErpProductPage
	 * @Create In 2015年7月16日 By zhangxy
	 */
	public Page<ErpProPageDto> selectErpProductPage(Map<String, Object> paramMap)
			throws BleException {
		logger.info("start selectShoppeProduct() 分发ERP商品信息");
		Page<ErpProPageDto> page = new Page<ErpProPageDto>();
		if (paramMap.get("currentPage") != null) {
			page.setCurrentPage((Integer) paramMap.get("currentPage"));
		}
		if (paramMap.get("pageSize") != null) {
			page.setPageSize((Integer) paramMap.get("pageSize"));
		}
		if (paramMap.get("supplyCode") != null) {
			if (((String) paramMap.get("supplyCode")).equals("1")) {
				paramMap.put("supplyCode", null);
				paramMap.put("supply", 1);
			}
		}
		if (paramMap.get("isAdjustPrice") != null) {
			if (paramMap.get("isAdjustPrice").equals(Constants.Y)) {
				paramMap.put("isAdjustPrice", Constants.PCMERPPRODUCT_IS_ADJUST_PRICE_YES);
			} else if (paramMap.get("isAdjustPrice").equals(Constants.N)) {
				paramMap.put("isAdjustPrice", Constants.PCMERPPRODUCT_IS_ADJUST_PRICE_NO);
			}
		}
		if (paramMap.get("isPromotion") != null) {
			if (paramMap.get("isPromotion").equals(Constants.Y)) {
				paramMap.put("isPromotion", Constants.PCMERPPRODUCT_IS_PROMOTION_YES);
			} else if (paramMap.get("isPromotion").equals(Constants.N)) {
				paramMap.put("isPromotion", Constants.PCMERPPRODUCT_IS_PROMOTION_NO);
			}
		}
		if (paramMap.get("productType") != null) {
			if (paramMap.get("productType").equals(Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z1_STR)) {
				paramMap.put("productType", Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z1);
			} else if (paramMap.get("productType")
					.equals(Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z2_STR)) {
				paramMap.put("productType", Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z2);
			} else if (paramMap.get("productType")
					.equals(Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z3_STR)) {
				paramMap.put("productType", Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z3);
			} else if (paramMap.get("productType")
					.equals(Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z4_STR)) {
				paramMap.put("productType", Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z4);
			} else if (paramMap.get("productType")
					.equals(Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z5_STR)) {
				paramMap.put("productType", Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z5);
			}
		}
		if (paramMap.get("proStatus") != null) {
			if (paramMap.get("proStatus").equals(Constants.PCMERPPRODUCT_PRO_STATUS_NORMAL_STR)) {
				paramMap.put("proStatus", Constants.PCMERPPRODUCT_PRO_STATUS_NORMAL);
			} else if (paramMap.get("proStatus")
					.equals(Constants.PCMERPPRODUCT_PRO_STATUS_STOPSALE_STR)) {
				paramMap.put("proStatus", Constants.PCMERPPRODUCT_PRO_STATUS_STOPSALE);
			} else
				if (paramMap.get("proStatus").equals(Constants.PCMERPPRODUCT_PRO_STATUS_STOP_STR)) {
				paramMap.put("proStatus", Constants.PCMERPPRODUCT_PRO_STATUS_STOP);
			} else if (paramMap.get("proStatus")
					.equals(Constants.PCMERPPRODUCT_PRO_STATUS_PAUSE_STR)) {
				paramMap.put("proStatus", Constants.PCMERPPRODUCT_PRO_STATUS_PAUSE);
			} else if (paramMap.get("proStatus")
					.equals(Constants.PCMERPPRODUCT_PRO_STATUS_DELETE_STR)) {
				paramMap.put("proStatus", Constants.PCMERPPRODUCT_PRO_STATUS_DELETE);
			} else
				if (paramMap.get("proStatus").equals(Constants.PCMERPPRODUCT_PRO_STATUS_PASS_STR)) {
				paramMap.put("proStatus", Constants.PCMERPPRODUCT_PRO_STATUS_PASS);
			}
		}

		// 查询总数
		Integer count = erpProductMapper.getCountByParam(paramMap);
		page.setCount(count);
		if (paramMap.get("currentPage") != null && paramMap.get("pageSize") != null) {
			paramMap.put("start", page.getStart());
			paramMap.put("limit", page.getLimit());
		} else if (paramMap.get("start") == null) {
			paramMap.put("start", 0);
		}
		List<ErpProPageDto> list = erpProductMapper.selectPageByParamPage(paramMap);
		if (!list.isEmpty()) {
			List<ErpProPageDto> result = new ArrayList<ErpProPageDto>();
			for (ErpProPageDto entity : list) {
				ErpProPageDto dto = new ErpProPageDto();
				try {
					BeanUtils.copyProperties(dto, entity);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				result.add(dto);
			}
			page.setList(result);
			logger.info("end selectShoppeProduct() 分发ERP商品信息, result:" + page.toString());
		}
		return page;
	}

	/**
	 * 搜索查询ERP信息
	 *
	 * @param entity
	 * @return List<PcmSearchErpProductDto>
	 * @Methods Name getErpProductInfoByStoreCodePage
	 * @Create In 2015年12月11日 By yedong
	 */
	public List<PcmSearchErpProductDto> getErpProductInfoByStoreCodePage(PcmErpProduct entity) {
		logger.info("start getErpProductInfoByStoreCodePage() 通过门店编码获取ERP商品信息");
		List<PcmErpProduct> erpList = erpProductMapper.selectListByParam(entity);
		List<PcmSearchErpProductDto> resList = erpProductInfoToSearch(erpList);
		return resList;
	}

	/**
	 * 搜索查询ERP信息Count
	 *
	 * @param entity
	 * @return List<PcmSearchErpProductDto>
	 * @Methods Name getErpProductInfoByStoreCodePage
	 * @Create In 2015年12月11日 By yedong
	 */
	public Integer getCountByStoreCodePage(Map<String, Object> entity) {
		logger.info("start getCountByStoreCodePage() 通过门店编码获取ERP商品信息");
		Integer count = erpProductMapper.getCountByParam(entity);
		return count;
	}

	/**
	 * 搜索ERP信息下发
	 *
	 * @param entity
	 * @return List<PcmSearchErpProductDto>
	 * @Methods Name getErpProductInfoByStoreCodePage
	 * @Create In 2015年12月11日 By yedong
	 */
	public List<PcmSearchErpProductDto> getErpProductInfoBySidList(Map<String, Object> paramMap) {
		logger.info("start getErpProductInfoBySidList() 通过门店编码获取ERP商品信息");
		List<PcmErpProduct> erpList = erpProductMapper.selectPageListByParam1newPrice(paramMap);
		List<PcmSearchErpProductDto> resList = erpProductInfoToSearch(erpList);
		return resList;
	}

	public List<PcmSearchErpProductDto> erpProductInfoToSearch(List<PcmErpProduct> erpList) {
		List<PcmSearchErpProductDto> resList = new ArrayList<PcmSearchErpProductDto>();
		for (PcmErpProduct entity : erpList) {
			if (entity.getCodeType() != 4 && entity.getCodeType() != 5
					&& entity.getCodeType() != 6) {
				PcmSearchErpProductDto dto = new PcmSearchErpProductDto();
				dto.setManagerCateNo(entity.getManageCategory());
				dto.setProductCode(entity.getProductCode());
				dto.setShoppeCode(entity.getShoppeCode());
				dto.setStoreCode(entity.getStoreCode());
				dto.setCodeType(entity.getCodeType());
				if (entity.getBigCodePrice() != null) {
					dto.setBigCodePrice(entity.getBigCodePrice().toPlainString());
				}
				dto.setSupplierCode(entity.getSupplyCode());
				dto.setStoreBrandCode(entity.getBrandCode());
				dto.setProductName(entity.getProductName());
				dto.setProductAbbr(entity.getProductAbbr());
				dto.setProductType(entity.getProductType());
				dto.setProductCategory(entity.getProductCategory());
				dto.setStanName(entity.getStanName());
				dto.setArticleNum(entity.getArticleNum());
				dto.setSalesUnit(entity.getSalesUnit());
				dto.setSupplierBarcode(entity.getSupplierBarcode());
				if (entity.getSalesPrice() != null) {
					dto.setSalesPrice(entity.getSalesPrice().toPlainString());
				}
				dto.setIsPromotion(entity.getIsPromotion());
				dto.setIsAdjustPrice(entity.getIsAdjustPrice());
				if (entity.getDiscountLimit() != null) {
					dto.setDiscountLimit(Double.parseDouble(entity.getDiscountLimit().toString()));
				}
				dto.setOriginLand(entity.getOriginLand());
				dto.setServiceFeeType(entity.getServiceFeeType());
				dto.setFormatType(entity.getFormatType());
				resList.add(dto);
			}
		}
		return resList;
	}

	/**
	 * ERP商品下发
	 *
	 * @return List<Map<String,Object>>
	 * @throws Exception
	 * @Methods Name selectErpProduct
	 * @Create In 2015年7月16日 By zhangxy
	 */
	@Override
	public Page<ErpProductDto> selectErpProduct(Map<String, Object> paramMap) throws BleException {
		logger.info("start selectShoppeProduct() 分发ERP商品信息");
		Page<ErpProductDto> page = new Page<ErpProductDto>();
		if (paramMap.get("currentPage") != null) {
			page.setCurrentPage((Integer) paramMap.get("currentPage"));
		}
		if (paramMap.get("pageSize") != null) {
			page.setPageSize((Integer) paramMap.get("pageSize"));
		}
		if (paramMap.get("isAdjustPrice") != null) {
			if (paramMap.get("isAdjustPrice").equals(Constants.Y)) {
				paramMap.put("isAdjustPrice", Constants.PCMERPPRODUCT_IS_ADJUST_PRICE_YES);
			} else if (paramMap.get("isAdjustPrice").equals(Constants.N)) {
				paramMap.put("isAdjustPrice", Constants.PCMERPPRODUCT_IS_ADJUST_PRICE_NO);
			}
		}
		if (paramMap.get("isPromotion") != null) {
			if (paramMap.get("isPromotion").equals(Constants.Y)) {
				paramMap.put("isPromotion", Constants.PCMERPPRODUCT_IS_PROMOTION_YES);
			} else if (paramMap.get("isPromotion").equals(Constants.N)) {
				paramMap.put("isPromotion", Constants.PCMERPPRODUCT_IS_PROMOTION_NO);
			}
		}
		if (paramMap.get("productType") != null) {
			if (paramMap.get("productType").equals(Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z1_STR)) {
				paramMap.put("productType", Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z1);
			} else if (paramMap.get("productType")
					.equals(Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z2_STR)) {
				paramMap.put("productType", Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z2);
			} else if (paramMap.get("productType")
					.equals(Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z3_STR)) {
				paramMap.put("productType", Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z3);
			} else if (paramMap.get("productType")
					.equals(Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z4_STR)) {
				paramMap.put("productType", Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z4);
			} else if (paramMap.get("productType")
					.equals(Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z5_STR)) {
				paramMap.put("productType", Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z5);
			}
		}
		if (paramMap.get("proStatus") != null) {
			if (paramMap.get("proStatus").equals(Constants.PCMERPPRODUCT_PRO_STATUS_NORMAL_STR)) {
				paramMap.put("proStatus", Constants.PCMERPPRODUCT_PRO_STATUS_NORMAL);
			} else if (paramMap.get("proStatus")
					.equals(Constants.PCMERPPRODUCT_PRO_STATUS_STOPSALE_STR)) {
				paramMap.put("proStatus", Constants.PCMERPPRODUCT_PRO_STATUS_STOPSALE);
			} else
				if (paramMap.get("proStatus").equals(Constants.PCMERPPRODUCT_PRO_STATUS_STOP_STR)) {
				paramMap.put("proStatus", Constants.PCMERPPRODUCT_PRO_STATUS_STOP);
			} else if (paramMap.get("proStatus")
					.equals(Constants.PCMERPPRODUCT_PRO_STATUS_PAUSE_STR)) {
				paramMap.put("proStatus", Constants.PCMERPPRODUCT_PRO_STATUS_PAUSE);
			} else if (paramMap.get("proStatus")
					.equals(Constants.PCMERPPRODUCT_PRO_STATUS_DELETE_STR)) {
				paramMap.put("proStatus", Constants.PCMERPPRODUCT_PRO_STATUS_DELETE);
			} else
				if (paramMap.get("proStatus").equals(Constants.PCMERPPRODUCT_PRO_STATUS_PASS_STR)) {
				paramMap.put("proStatus", Constants.PCMERPPRODUCT_PRO_STATUS_PASS);
			}
		}

		// // 查询总数
		// Integer count = erpProductMapper.getCountByParam(paramMap);
		// page.setCount(count);
		// if (paramMap.get("currentPage") != null && paramMap.get("pageSize")
		// != null) {
		// paramMap.put("start", page.getStart());
		// paramMap.put("limit", page.getLimit());
		// } else if (paramMap.get("start") == null) {
		// paramMap.put("start", 0);
		// }
		List<PcmErpProduct> list = erpProductMapper.selectErpPageByParam(paramMap);
		if (!list.isEmpty()) {
			List<ErpProductDto> result = new ArrayList<ErpProductDto>();
			for (PcmErpProduct entity : list) {
				ErpProductDto dto = new ErpProductDto();
				try {
					BeanUtils.copyProperties(dto, entity);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				result.add(dto);
			}
			page.setList(result);
			logger.info("end selectShoppeProduct() 分发ERP商品信息, result:" + page.toString());
		}
		return page;
	}

	@Override
	public List<Map<String, Object>> pushErpProduct(List<ErpProductDto> list) {
		List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
		Integer type = list.get(0).getType();
		for (ErpProductDto dto : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("statCategory", dto.getStatCategory());
			map.put("storeCode", dto.getStoreCode());
			map.put("erpProductCode", dto.getProductCode());
			map.put("erpSkuType", dto.getCodeType());
			map.put("counterCode", dto.getShoppeCode());
			map.put("supplierCode", dto.getSupplyCode());
			map.put("brandCode", dto.getGroupBrandCode());
			map.put("erpBrandCode", dto.getBrandCode());//
			map.put("productName", dto.getProductName());
			map.put("productCategory", "0" + dto.getProductCategory());
			map.put("productType", dto.getProductType());
			map.put("specName", dto.getStanName());
			map.put("articleNum", dto.getArticleNum());
			map.put("supplierBarcode", dto.getSupplierBarcode());
			if (dto.getProStatus().equals("0")) {
				map.put("status", "Y");
			} else if (dto.getProStatus().equals("1")) {
				map.put("status", "N");
			}
			if (dto.getIsPromotion().equals("0")) {
				map.put("isPromotion", "Y");
			} else {
				map.put("isPromotion", "N");
			}
			if (dto.getIsAdjustPrice().equals("0")) {
				map.put("isAdjustPrice", "Y");
			} else {
				map.put("isAdjustPrice", "N");
			}
			map.put("manageCategory", dto.getManageCategory());
			map.put("discountLimit", Double.parseDouble(dto.getDiscountLimit()));
			map.put("commissionRate", dto.getCommissionRate());
			map.put("originSalesUnit", dto.getSalesUnit());
			map.put("salesPrice", dto.getSalesPrice());
			if (type != null) {
				if (type == 0) {
					map.put("actionCode", "A");
				} else if (type == 1) {
					map.put("actionCode", "U");
				} else {
					map.put("actionCode", "D");
				}
			}
			res.add(map);
		}
		return res;
	}

	/**
	 * 根据ERP编码查询详细信息
	 *
	 * @param productCode
	 * @return PcmErpProduct
	 * @Methods Name selectErpProductByProCode
	 * @Create In 2016年1月25日 By kongqf
	 */
	@Override
	public PcmErpProduct selectErpProductByProCode(String productCode, String storeCode) {
		PcmErpProduct erpProduct = new PcmErpProduct();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("productCode", productCode);
		paramMap.put("storeCode", storeCode);
		erpProduct = erpProductMapper.selectByProCode(paramMap);
		return erpProduct;
	}

	/**
	 * 根据供应商门店查询专柜列表（扣率码信息）
	 *
	 * @param para
	 * @return GetSupAndErpInfoDto
	 * @Methods Name GetShoppeInfoAndErp
	 * @Create In 2016-3-18 By wangc
	 */
	@Override
	public GetSupAndErpInfoDto getShoppeInfoAndErp(Map<String, String> para) throws Exception {
		logger.info("根据供应商门店查询专柜列表（扣率码信息） getShoppeInfoAndErp start，para: " + para.toString());
		try {
			GetSupAndErpInfoDto result = erpProductMapper.GetShoppeInfoAndErp(para);
			logger.info("根据供应商门店查询专柜列表（扣率码信息） getShoppeInfoAndErp end");
			return result;
		} catch (Exception e) {
			throw e;
		}

	}

}
