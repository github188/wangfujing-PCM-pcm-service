/**
 * @Probject Name: pcm-service
 * @Path: com.wangfj.product.supplier.service.implPcmUploadProductServiceImpl.java
 * @Create By wangc
 * @Create In 2016-2-26 下午2:58:01
 * TODO
 */
package com.wangfj.product.supplier.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangfj.core.constants.ComErrorCodeConstants.ErrorCode;
import com.wangfj.core.framework.exception.BleException;
import com.wangfj.product.brand.domain.entity.PcmBrand;
import com.wangfj.product.brand.domain.entity.PcmShopBrandRelation;
import com.wangfj.product.brand.persistence.PcmBrandMapper;
import com.wangfj.product.brand.persistence.PcmShopBrandRelationMapper;
import com.wangfj.product.category.domain.entity.PcmCategory;
import com.wangfj.product.category.persistence.PcmCategoryMapper;
import com.wangfj.product.maindata.domain.entity.PcmBarcode;
import com.wangfj.product.maindata.domain.entity.PcmColorDict;
import com.wangfj.product.maindata.domain.entity.PcmContractLog;
import com.wangfj.product.maindata.domain.entity.PcmErpProduct;
import com.wangfj.product.maindata.domain.entity.PcmProDetail;
import com.wangfj.product.maindata.domain.entity.PcmProduct;
import com.wangfj.product.maindata.domain.entity.PcmSeasonDict;
import com.wangfj.product.maindata.domain.entity.PcmShoppeProduct;
import com.wangfj.product.maindata.domain.entity.PcmShoppeProductExtend;
import com.wangfj.product.maindata.domain.vo.BarcodeDto;
import com.wangfj.product.maindata.domain.vo.CreateShoppePro;
import com.wangfj.product.maindata.domain.vo.CreateSkuDto;
import com.wangfj.product.maindata.domain.vo.CreateSpuDto;
import com.wangfj.product.maindata.domain.vo.PullDataDto;
import com.wangfj.product.maindata.domain.vo.ValidProDetailDto;
import com.wangfj.product.maindata.domain.vo.ValidShoppeProDto;
import com.wangfj.product.maindata.persistence.PcmBarcodeMapper;
import com.wangfj.product.maindata.persistence.PcmColorDictMapper;
import com.wangfj.product.maindata.persistence.PcmContractLogMapper;
import com.wangfj.product.maindata.persistence.PcmErpProductMapper;
import com.wangfj.product.maindata.persistence.PcmSeasonDictMapper;
import com.wangfj.product.maindata.service.intf.IPcmCreateProductService;
import com.wangfj.product.maindata.service.intf.IPcmStanDictService;
import com.wangfj.product.maindata.service.intf.IValidProDrtailService;
import com.wangfj.product.organization.domain.entity.PcmOrganization;
import com.wangfj.product.organization.domain.entity.PcmShoppe;
import com.wangfj.product.organization.persistence.PcmOrganizationMapper;
import com.wangfj.product.organization.persistence.PcmShoppeMapper;
import com.wangfj.product.price.service.intf.IPcmPriceService;
import com.wangfj.product.stocks.domain.entity.PcmStock;
import com.wangfj.product.stocks.domain.vo.PcmProductStockInfoDto;
import com.wangfj.product.stocks.domain.vo.PcmStockDto;
import com.wangfj.product.stocks.persistence.PcmStockMapper;
import com.wangfj.product.stocks.service.intf.IPcmStockChangeRecordService;
import com.wangfj.product.stocks.service.intf.IPcmStockService;
import com.wangfj.product.supplier.domain.entity.PcmShoppeProductSupply;
import com.wangfj.product.supplier.domain.entity.PcmSupplyInfo;
import com.wangfj.product.supplier.domain.vo.PcmShoppeSupplierQueryDto;
import com.wangfj.product.supplier.domain.vo.PcmShoppeSupplierResultDto;
import com.wangfj.product.supplier.persistence.PcmShoppeProductSupplyMapper;
import com.wangfj.product.supplier.persistence.PcmSupplyInfoMapper;
import com.wangfj.product.supplier.persistence.PcmSupplyShoppeRelationMapper;
import com.wangfj.product.supplier.service.intf.IPcmUploadProductService;
import com.wangfj.util.Constants;

/**
 * @Class Name PcmUploadProductServiceImpl
 * @Author wangc
 * @Create In 2016-2-26
 */
@Service
public class PcmUploadProductServiceImpl implements IPcmUploadProductService {

	private static final Logger logger = LoggerFactory.getLogger(PcmUploadProductServiceImpl.class);

	@Autowired
	private IPcmCreateProductService createService;
	@Autowired
	private IValidProDrtailService validService;
	@Autowired
	private IPcmStanDictService stanDictService;
	@Autowired
	private IPcmPriceService priceService;
	@Autowired
	IPcmStockService stockService;
	@Autowired
	IPcmStockChangeRecordService scrsService;

	// 库存表
	@Autowired
	PcmStockMapper stockMapper;
	// 一品多商关系表
	@Autowired
	PcmShoppeProductSupplyMapper pspsMapper;
	// 专柜
	@Autowired
	private PcmShoppeMapper pcmShoppeMapper;
	// 组织机构
	@Autowired
	private PcmOrganizationMapper organizationMapper;
	// 供应商
	@Autowired
	private PcmSupplyInfoMapper supplyInfoMapper;
	// 大码表
	@Autowired
	private PcmErpProductMapper erpProductMapper;
	// 品牌表
	@Autowired
	private PcmBrandMapper brandMapper;
	// 门店-门店品牌关系表
	@Autowired
	private PcmShopBrandRelationMapper psbrMapper;
	// 工业分类表
	@Autowired
	private PcmCategoryMapper categoryMapper;
	// 季节表
	@Autowired
	private PcmSeasonDictMapper seasonDictMapper;
	// 色系字典表
	@Autowired
	private PcmColorDictMapper colorDictMapper;

	// 条码表
	@Autowired
	private PcmBarcodeMapper barcodeMapper;
	// 专柜供应商关系表
	@Autowired
	private PcmSupplyShoppeRelationMapper supplyShoppeMapper;
	// 合同表
	@Autowired
	private PcmContractLogMapper contractLogMapper;

	/**
	 * 供应商商品上传
	 */
	@Override
	@Transactional
	public PcmShoppeProduct uploadProductFromSup(PullDataDto dataDto) throws BleException {
		logger.info("start uploadProductFromSup(),param:" + dataDto.toString());
		// 非空与格式参数校验
		if ("2".equals(dataDto.getType())) {
			validPullDataDtoIsExists(dataDto, true);
		} else {
			validPullDataDtoIsExists(dataDto, false);
		}
		// 特殊字段校验
		validSupParaIsExists(dataDto);
		String source = "SUP"; // 数据源 编号
		Map<String, Object> map = new HashMap<String, Object>();
		// 专柜数据校验
		map.clear();
		map.put("shoppeCode", dataDto.getCounterCode());
		map.put("shoppeType", "01"); // 查找单品专柜
		List<PcmShoppe> shoppeList = pcmShoppeMapper.selectListByParam(map);
		if (shoppeList == null || shoppeList.size() != 1) {
			throw new BleException(ErrorCode.SHOPPE_NULL.getErrorCode(),
					ErrorCode.SHOPPE_NULL.getMemo());
		}
		// 判断专柜状态
		if (shoppeList.get(0).getShoppeStatus() == null
				|| shoppeList.get(0).getShoppeStatus() != Constants.PUBLIC_1
				|| shoppeList.get(0).getIndustryConditionSid() != Integer.parseInt(dataDto
						.getType())) {
			throw new BleException(ErrorCode.SHOPPE_STATUS_ERROR.getErrorCode(),
					ErrorCode.SHOPPE_STATUS_ERROR.getMemo());
		}
		// 获取门店信息
		PcmOrganization org = organizationMapper.get(shoppeList.get(0).getShopSid());
		if (org == null) {
			throw new BleException(ErrorCode.SHOPPE_STATUS_ERROR.getErrorCode(),
					ErrorCode.SHOPPE_STATUS_ERROR.getMemo());
		} else {
			if (!org.getOrganizationCode().equals(dataDto.getShopCode())) {
				throw new BleException(
						ErrorCode.ORGANIZATION_SHOPANDCOUNTER_IS_NULL.getErrorCode(),
						ErrorCode.ORGANIZATION_SHOPANDCOUNTER_IS_NULL.getMemo());
			}
		}
		// 电商之外商品，获取大码信息
		List<PcmErpProduct> erpList = null;
		if (!"2".equals(dataDto.getType())) {
			if (StringUtils.isNotBlank(dataDto.getErpProductCode())) {
				PcmErpProduct erpEntity = new PcmErpProduct();
				erpEntity.setProductCode(dataDto.getErpProductCode());
				erpEntity.setStoreCode(org.getOrganizationCode());
				erpList = erpProductMapper.selectListByParam(erpEntity);
			}
		}
		// 验证要约
		map.clear();
		map.put("contractCode", dataDto.getOfferNumber());
		List<PcmContractLog> PcmContractLogs = contractLogMapper.selectListByParam(map);
		if (PcmContractLogs == null || PcmContractLogs.size() != 1) {
			throw new BleException(ErrorCode.CONTRACT_IS_NULL.getErrorCode(),
					ErrorCode.CONTRACT_IS_NULL.getMemo());
		}
		// 供应商数据校验
		map.clear();
		map.put("shopSid", org.getOrganizationCode());
		map.put("supplyCode", dataDto.getSupplierCode());
		List<PcmSupplyInfo> supplyInfoList = supplyInfoMapper.selectListByParam(map);
		if (supplyInfoList == null || supplyInfoList.size() != 1) {
			throw new BleException(ErrorCode.SUPPLYINFO_NULL.getErrorCode(),
					ErrorCode.SUPPLYINFO_NULL.getMemo());
		}
		// 判断供应商状态
		if (supplyInfoList.get(0).getStatus() == null
				|| !supplyInfoList.get(0).getStatus().equals(Constants.PCMSUPPLYINFO_STATUS_Y_CODE)) {
			throw new BleException(ErrorCode.SUPPLYINFO_STATUS_ERROR.getErrorCode(),
					ErrorCode.SUPPLYINFO_STATUS_ERROR.getMemo());
		}
		// 电商-虚库时判断供应商关系
		if ("2".equals(dataDto.getType()) || "1".equals(dataDto.getStockType())) {// 电商虚库
			PcmShoppeSupplierQueryDto dto = new PcmShoppeSupplierQueryDto();
			dto.setShoppeSid(String.valueOf(shoppeList.get(0).getSid()));// 专柜表SID
			dto.setSupplySid(String.valueOf(supplyInfoList.get(0).getSid()));// 供应商表SID
			List<PcmShoppeSupplierResultDto> ResultDtos = supplyShoppeMapper
					.findShoppeSupplierInfoByParam(dto);
			if (ResultDtos == null || ResultDtos.size() != Constants.PUBLIC_1) {
				throw new BleException(ErrorCode.ADD_PRODUCT_SUPPLY_SHOPPE_ERROR.getErrorCode(),
						ErrorCode.ADD_PRODUCT_SUPPLY_SHOPPE_ERROR.getMemo());
			}
		}
		// 判断供应商经营方式
		if (supplyInfoList.get(0).getBusinessPattern() == null
				|| !String.valueOf(supplyInfoList.get(0).getBusinessPattern()).equals(
						dataDto.getOperateMode())) {
			throw new BleException(ErrorCode.SUPPLYINFO_BUSSINESS_ERROR.getErrorCode(),
					ErrorCode.SUPPLYINFO_BUSSINESS_ERROR.getMemo());
		}
		// 品牌数据校验
		map.clear();
		map.put("brandSid", dataDto.getBrandCode());
		map.put("shopType", org.getStoreType());// 门店类型
		map.put("brandType", 1); // 品牌类型（门店）
		map.put("status", 0); // 状态为有效
		PcmBrand brand = null;
		List<PcmBrand> brandList = brandMapper.selectListByParam(map);// 品牌信息
		if (brandList == null || brandList.size() != 1) {
			// 门店品牌不存在
			throw new BleException(ErrorCode.BRANDGROUP_NULL.getErrorCode(),
					ErrorCode.BRANDGROUP_NULL.getMemo());
		} else {
			// 门店品牌查询到了，查询集团品牌
			brand = brandList.get(0); // 门店品牌信息
			map.clear();
			map.put("sid", brandList.get(Constants.PUBLIC_0).getParentSid());// 集团品牌SID
			map.put("brandType", 0); // 品牌类型（集团）
			map.put("status", 0); // 状态为有效
			brandList = brandMapper.selectListByParam(map);
			if (brandList == null || brandList.size() != 1) {
				throw new BleException(ErrorCode.BRANDGROUP_NULL.getErrorCode(),
						ErrorCode.BRANDGROUP_NULL.getMemo());
			}
			// 门店品牌及门店关系校验
			map.clear();
			map.put("brandSid", brand.getSid());
			map.put("shopSid", org.getSid());
			List<PcmShopBrandRelation> relations = psbrMapper.selectListByParam(map);
			if (relations == null || relations.size() != 1) {
				throw new BleException(ErrorCode.BRAND_SHOP_RELATION_IS_NULL.getErrorCode(),
						ErrorCode.BRAND_SHOP_RELATION_IS_NULL.getMemo());
			}
		}
		// 统计分类字典校验
		map.clear();
		map.put("categoryCode", dataDto.getFinalClassiFicationCode());
		map.put("categoryType", Constants.STATISTICSCATEGORY);// 分类类型为 2 统计分类
		map.put("isLeaf", Constants.Y);// 是否为叶子节点
		map.put("status", Constants.Y);// 是否启用
		List<PcmCategory> tjcateList = categoryMapper.selectListByParam(map);
		if (tjcateList == null || tjcateList.size() != 1) {
			throw new BleException(ErrorCode.CATEGORY_TJ_NULL.getErrorCode(),
					ErrorCode.CATEGORY_TJ_NULL.getMemo());
		}
		// 工业分类字典校验
		map.clear();
		map.put("categorySid", dataDto.getProdCategoryCode());
		map.put("categoryType", Constants.INDUSTRYTCATEGORY);// 分类类型为 0 工业分类
		map.put("isLeaf", Constants.Y);// 是否为叶子节点
		map.put("status", Constants.Y);// 是否启用
		List<PcmCategory> cateList = categoryMapper.selectListByParam(map);
		if (cateList == null || cateList.size() != 1) {
			throw new BleException(ErrorCode.CATEGORY_GY_NULL.getErrorCode(),
					ErrorCode.CATEGORY_GY_NULL.getMemo());
		}
		// 管理分类字典校验
		if (!"2".equals(dataDto.getType())) {// 电商不校验管理分类
			map.clear();
			map.put("categorySid", dataDto.getManageCateGory());
			map.put("categoryType", Constants.MANAGECATEGORY);// 分类类型为 1管理分类
			map.put("isLeaf", Constants.Y);// 是否为叶子节点
			map.put("status", Constants.Y);// 是否启用
			List<PcmCategory> glcateList = categoryMapper.selectListByParam(map);
			if (glcateList == null || glcateList.size() != 1) {
				throw new BleException(ErrorCode.CATEGORY_GL_NULL.getErrorCode(),
						ErrorCode.CATEGORY_GL_NULL.getMemo());
			}
		}
		// 联营百货扣率码数据校验
		if (dataDto.getType().equals(String.valueOf(Constants.PUBLIC_0))
				&& dataDto.getOperateMode().equals(
						String.valueOf(Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z3))) {
			if (erpList == null || erpList.size() != 1) {
				throw new BleException(ErrorCode.DISCOUNTCODE_NULL.getErrorCode(),
						ErrorCode.DISCOUNTCODE_NULL.getMemo());
			}
			if (!erpList.get(0).getShoppeCode().equals(dataDto.getCounterCode())) {
				throw new BleException(
						ErrorCode.DISCOUNTCODE_INFO_ERROR_COUNTERCODE.getErrorCode(),
						ErrorCode.DISCOUNTCODE_INFO_ERROR_COUNTERCODE.getMemo());
			}
			if (!erpList.get(0).getSupplyCode().equals(dataDto.getSupplierCode())) {
				throw new BleException(ErrorCode.DISCOUNTCODE_INFO_ERROR_SUPPLIER.getErrorCode(),
						ErrorCode.DISCOUNTCODE_INFO_ERROR_SUPPLIER.getMemo());
			}
		}
		// 季节字典数据缺失
		List<PcmSeasonDict> seasonList = null;
		if (dataDto.getType().equals(String.valueOf(Constants.PUBLIC_0))) {
			map.clear();
			map.put("seasonCode", dataDto.getSeasonCode());
			seasonList = seasonDictMapper.selectListByParam(map);
			if (seasonList == null || seasonList.size() != 1) {
				throw new BleException(ErrorCode.SEASON_NULL.getErrorCode(),
						ErrorCode.SEASON_NULL.getMemo());
			}
		}
		// 色系
		List<PcmColorDict> colorList = null;
		if (dataDto.getType().equals(String.valueOf(Constants.PUBLIC_0))) {
			// 色系字典数据缺失
			map.clear();
			map.put("colorName", dataDto.getProColor());
			colorList = colorDictMapper.selectListByParam(map);
			if (colorList == null || colorList.size() != 1) {
				throw new BleException(ErrorCode.COLOR_NULL.getErrorCode(),
						ErrorCode.COLOR_NULL.getMemo());
			}
		}
		// 条码校验 同一门店下条码不能重复
		if (StringUtils.isNotBlank(dataDto.getStandardBarCode())) {
			if ("2".equals(dataDto.getType())) {// 电商条码校验
				validBarcodeSap(org.getOrganizationCode(), dataDto.getStandardBarCode());
			} else {// 非电商条码校验
				validBarcode(org.getOrganizationCode(), dataDto.getStandardBarCode());
			}
		} else if (dataDto.getStandardBarCodes() != null
				&& dataDto.getStandardBarCodes().size() != 0) {
			if ("2".equals(dataDto.getType())) {// 电商条码校验
				validBarcodeSap(dataDto.getStandardBarCodes(), org.getOrganizationCode());
			} else {// 非电商条码校验
				validBarcode(dataDto.getStandardBarCodes(), org.getOrganizationCode());
			}
		}
		// 创建专柜商品DTO
		CreateShoppePro cProDto = new CreateShoppePro();
		// 品牌表SID
		cProDto.setBrandSid(String.valueOf(brand.getSid()));
		// 判断是否联营
		if (dataDto.getOperateMode()
				.equals(String.valueOf(Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z3))) {// 联营
			cProDto.setBusinessType(Constants.PUBLIC_1);// 自定义标记自营0,联营1
			cProDto.setOrderType(Constants.PUBLIC_4);// 订货类型
		} else {// 其他
			cProDto.setBusinessType(Constants.PUBLIC_0);// 自定义标记自营0,联营1
			cProDto.setOrderType(Constants.PUBLIC_5);// 订货类型
			cProDto.setField2("WFJ");
		}
		if (StringUtils.isNotBlank(dataDto.getInputTax())) {
			cProDto.setInputTax(new BigDecimal(dataDto.getInputTax()));// 进项税
		}
		if (StringUtils.isNotBlank(dataDto.getOutputTax())) {
			cProDto.setOutputTax(new BigDecimal(dataDto.getOutputTax()));// 销项税
		}
		if (StringUtils.isNotBlank(dataDto.getRate_price())) {
			cProDto.setPurchasePrice(new BigDecimal(dataDto.getRate_price()));// 扣率/进价
		}
		if (StringUtils.isNotBlank(dataDto.getPurchasePrice_taxRebate())) {
			cProDto.setBuyingPrice(new BigDecimal(dataDto.getPurchasePrice_taxRebate()));// 扣率/含税进价
		}
		cProDto.setRateCode(dataDto.getErpProductCode());// 扣率码
		if (dataDto.getConsumptionTax() != null && dataDto.getConsumptionTax() != ""
				&& dataDto.getConsumptionTax() != "null") {
			cProDto.setSalesTax(new BigDecimal(dataDto.getConsumptionTax()));// 消费税
		}
		if (dataDto.getSalesTax() != null && dataDto.getSalesTax() != ""
				&& dataDto.getSalesTax() != "null") {
			cProDto.setSalesTax(new BigDecimal(dataDto.getSalesTax()));// 消费税
		}
		cProDto.setStockMode(Integer.valueOf(dataDto.getStockType()));// 库存方式
		cProDto.setSupplySid(String.valueOf(supplyInfoList.get(0).getSid()));// 供应商SID
		cProDto.setIsStock(Constants.PUBLIC_0);// 是否管库库存 0是
		cProDto.setCategorySid(dataDto.getManageCateGory());// 品牌专柜（管理分类编码）
		cProDto.setDiscountLimit(dataDto.getDiscountLimit());// 折扣底限
		cProDto.setErpproductcode(dataDto.getErpProductCode());// 大码
		cProDto.setFlag(Integer.parseInt(dataDto.getType()));// 百货超市标记,0百货,1超市,2电商
		cProDto.setOfferNo(dataDto.getOfferNumber());// 要约号
		cProDto.setOriginalPrice(new BigDecimal(dataDto.getMarketPrice()));// 吊牌价
		if (StringUtils.isNotBlank(dataDto.getSalePrice())) {
			cProDto.setSalePrice(new BigDecimal(dataDto.getSalePrice()));// 零售价
		}
		cProDto.setOriginLand(dataDto.getPlaceOfOrigin());// 产地
		cProDto.setOriginLand2(dataDto.getCountryOfOrigin());// 原产地
		if (StringUtils.isNotBlank(dataDto.getProcessingType())) {
			cProDto.setProcessType(Integer.parseInt(dataDto.getProcessingType()));// 加工类型
		}
		if (StringUtils.isNotBlank(dataDto.getTmsParam())) {
			cProDto.setTmsParam(Integer.valueOf(dataDto.getTmsParam()));// 物流类型
		}
		cProDto.setSaleUnitCode(dataDto.getUnitCode());// 销售单位
		cProDto.setShopCode(org.getOrganizationCode());// 门店编码
		cProDto.setShoppeCode(dataDto.getCounterCode());// 专柜编码
		cProDto.setShoppeProAlias(dataDto.getProductAbbr());// 商品简称
		if (dataDto.getType().equals(String.valueOf(Constants.PUBLIC_0))
				|| dataDto.getType().equals(String.valueOf(Constants.PUBLIC_2))) {// 百货或电商
			cProDto.setShoppeProName(dataDto.getProductName());// 专柜商品名称
		} else {// 超市
			cProDto.setShoppeProName(dataDto.getRegisteredTradeName());// 专柜商品名称
		}
		cProDto.setShoppeSid(String.valueOf(shoppeList.get(0).getSid()));// 专柜SID
		if (StringUtils.isNotBlank(dataDto.getStandardBarCode())) {
			List<BarcodeDto> barcodeList = new ArrayList<BarcodeDto>();
			BarcodeDto barcodeDto = new BarcodeDto();
			barcodeDto.setBarcode(dataDto.getStandardBarCode());
			barcodeDto.setType(0);// 标准条码
			barcodeDto.setOriginLand(dataDto.getPlaceOfOrigin());
			barcodeList.add(barcodeDto);
			cProDto.setBarcode(barcodeList);
		} else if (dataDto.getStandardBarCodes() != null
				&& dataDto.getStandardBarCodes().size() != 0) {
			List<BarcodeDto> barcodeList = new ArrayList<BarcodeDto>();
			for (Map<String, Object> ba : dataDto.getStandardBarCodes()) {
				BarcodeDto barcodeDto = new BarcodeDto();
				barcodeDto.setBarcode(ba.get("barcode").toString());// 条码
				barcodeDto.setType(Integer.valueOf(ba.get("barCodeType").toString()));// 条码类型
				barcodeDto.setOriginLand(ba.get("originLand").toString());// 产地
				barcodeList.add(barcodeDto);
			}
			cProDto.setBarcode(barcodeList);
		}
		cProDto.setSupplyCode(dataDto.getSupplierCode());// 供应商编码
		cProDto.setSupplyProductCode(dataDto.getSupplyInnerCode());// 供应商商品编码
		cProDto.setRemarks(dataDto.getRemarks());// 备注
		if (dataDto.getIsAdjustPrice() != null && dataDto.getIsAdjustPrice().equals(Constants.Y)) {
			cProDto.setIsAdjustPrice(Constants.PUBLIC_0);// 是否允许ERP调价
		} else if (dataDto.getIsAdjustPrice() != null
				&& dataDto.getIsAdjustPrice().equals(Constants.N)) {
			cProDto.setIsAdjustPrice(Constants.PUBLIC_1);// 是否允许ERP调价
		}
		if (dataDto.getIsPromotion() != null && dataDto.getIsPromotion().equals(Constants.Y)) {
			cProDto.setIsPromotion(Constants.PUBLIC_0);// 是否允许ERP促销
		} else if (dataDto.getIsPromotion() != null && dataDto.getIsPromotion().equals(Constants.N)) {
			cProDto.setIsPromotion(Constants.PUBLIC_1);// 是否允许ERP促销
		}
		cProDto.setField3(dataDto.getModelNum());// 货号
		cProDto.setCategoryTJSid(tjcateList.get(0).getSid());// 统计分类SID
		CreateSkuDto cSkuDto = new CreateSkuDto();
		cSkuDto.setBrandGroupCode(brandList.get(0).getBrandSid());// 集团品牌编码
		cSkuDto.setBrandShopCode(brand.getBrandSid());// 门店品牌编码
		cSkuDto.setCategoryGYSid(cateList.get(0).getSid());// 管理分类编码
		cSkuDto.setFeatures(dataDto.getFeatures());// 特性
		cSkuDto.setFlag(Integer.parseInt(dataDto.getType()));// 超市百货标记_0百货_1超市_2电商
		cSkuDto.setProColorAlias(dataDto.getColorName());// 色码名称
		cSkuDto.setProColorName(dataDto.getColorCode());// 色码
		if (colorList != null) {
			cSkuDto.setProColorSid(colorList.get(0).getSid());// 色系表SID
		}
		cSkuDto.setProStanName(dataDto.getSizeCode());// 规格/尺码名称
		cSkuDto.setProStanSid(dataDto.getSizeCode());// 规格/尺码
		cSkuDto.setProType(Integer.valueOf(dataDto.getProductType()));// 商品类型
																		// （1.普通商品（实物类））
		PcmShoppeProductExtend dsPro = new PcmShoppeProductExtend();
		if ("2".equals(dataDto.getType())) {// 电商扩展表
			dsPro.setBaseUnitCode(dataDto.getBaseUnitCode());// 基本计量单位
			dsPro.setOriginCountry(dataDto.getOriginCountry());// 原产国
		}
		if (cProDto.getBusinessType() == Constants.PUBLIC_1) {// 联营时，扩展表商品类别为8联营单品
			dsPro.setField1("8");
		}
		if (cProDto.getBusinessType() == Constants.PUBLIC_0) {// 自营时，扩展表商品类别为1自营单品
			dsPro.setField1("1");
		}
		dsPro.setField3(dataDto.getDiscount());// 扩展表扣率
		dsPro.setField2(cProDto.getOriginalPrice().toString());// 扩展表原价
		PcmShoppeProduct shoppePro = null;
		// 1.spu验证:
		// 判断超市或百货
		ValidProDetailDto validSpuDto = new ValidProDetailDto();
		validSpuDto.setBrandSid(String.valueOf(brandList.get(0).getBrandSid()));// 品牌SID
		validSpuDto.setProStanSid(dataDto.getSizeCode());// 规格/尺码
		if (!dataDto.getType().equals(String.valueOf(Constants.PUBLIC_1))) {// 非超市字段
			validSpuDto.setProductSku(dataDto.getProductNum());// 款号
			validSpuDto.setProColorName(dataDto.getColorCode());// 色码
			validSpuDto.setProColorSid(colorList.get(0).getSid().toString());// 色系SID
		} else {// 超市字段
			validSpuDto.setPrimaryAttr(dataDto.getMainAttribute());// 主属性
			validSpuDto.setFeatures(dataDto.getFeatures());// 特性
		}
		PcmProduct pro = validService.validSpuBh(validSpuDto);
		Long spuFlag = 0l;
		Long skuFlag = 0l;
		if (pro != null) {// SPU存在
			// 2.sku验证
			validSpuDto.setProductSid(String.valueOf(pro.getSid()));// 产品表SID
			List<PcmProDetail> proDList = validService.validSku(validSpuDto);
			if (proDList != null && proDList.size() == 1) {// SKU存在
				PcmProDetail proD = proDList.get(0);
				// 3.专柜pro验证
				ValidShoppeProDto validShoppeProdto = new ValidShoppeProDto();
				validShoppeProdto.setProductDetailSid(String.valueOf(proD.getSid()));// sku表SID
				validShoppeProdto.setShoppeSid(String.valueOf(shoppeList.get(0).getSid()));// 专柜表SID
				validShoppeProdto.setSupplySid(String.valueOf(supplyInfoList.get(0).getSid()));// 供应商表SID
				if (StringUtils.isNotBlank(dataDto.getStandardBarCode())) {
					validShoppeProdto.setBarcode(dataDto.getStandardBarCode());// 条码
					validShoppeProdto.setStoreCode(org.getOrganizationCode());// 门店编码
				}
				// 超市判重加入销售单位
				if (dataDto.getType().equals(String.valueOf(Constants.PUBLIC_1))) {
					validShoppeProdto.setSaleUnitCode(dataDto.getUnitCode());// 销售单位
				}
				if (dataDto.getOperateMode().equals(
						String.valueOf(Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z3))) {
					// 联营时专柜商品 判重-生成 逻辑
					PcmShoppeProduct shopp = validService.validShoppeProBh(validShoppeProdto);
					if (shopp != null) {// 专柜商品存在
						throw new BleException(ErrorCode.SHOPP_IS_EXIST.getErrorCode(),
								ErrorCode.SHOPP_IS_EXIST.getMemo());
					} else {// 专柜商品不存在/创建SHOPPRO
						cProDto.setProductDetailSid(String.valueOf(proD.getSid()));// sku表SID
						if (!StringUtils.isNotBlank(cProDto.getShoppeProName())) {// 专柜商品名为空
							String shoppeProName = brand.getBrandName() + dataDto.getProductDesc()
									+ dataDto.getColorCode() + dataDto.getSizeCode();
							/*
							 * List<String> paras = new ArrayList<String>();//
							 * paras.add(dataDto.getProductDesc());
							 * paras.add(dataDto.getColorCode());
							 * paras.add(dataDto.getSizeCode()); String
							 * shoppeProName =
							 * generateStandardName(paras,"");//生成标准品名
							 */if ("2".equals(dataDto.getType())) {
								cProDto.setShoppeProName(shoppeProName);// 专柜商品名称
							} else {
								cProDto.setShoppeProName(proD.getProDetailName());
							}
						}
						shoppePro = createService.insertShoppePro(cProDto);
						// 写入库存
						if (StringUtils.isNotBlank(dataDto.getInventory())) {
							saveInventory(shoppePro.getShoppeProSid(), dataDto.getInventory(),
									source, dataDto.getEntryNumber());
						} else {
							saveInventory(shoppePro.getShoppeProSid(), "0", source,
									dataDto.getEntryNumber());
						}
						// 写入临时表
						if (StringUtils.isNotBlank(dataDto.getEntryNumber())
								&& StringUtils.isNotBlank(dataDto.getProcurementPersonnelNumber())
								&& StringUtils.isNotBlank(dataDto.getOfferNumber())) {
							createService.insertProductInput(shoppePro.getSid(),
									dataDto.getOfferNumber(), dataDto.getEntryNumber(),
									dataDto.getProcurementPersonnelNumber(), dsPro);
						}
						// 写入一品多供应商关系表
						PcmShoppeProductSupply psps = new PcmShoppeProductSupply();
						psps.setShoppeProductSid((shoppePro.getSid()));// 专柜商品SID
						psps.setSupplySid(supplyInfoList.get(0).getSid());// 供应商SID
						insertShoppeProductSupply(psps);
					}
				} else {
					// 非联营时专柜商品 判重-生成 逻辑
					PcmShoppeProduct shopp = validService.validShoppePro(validShoppeProdto);
					if (shopp != null) {// 专柜商品存在--判断是否存在一品多供应商关系
						PcmShoppeProductSupply psps = new PcmShoppeProductSupply();
						psps.setShoppeProductSid((shopp.getSid()));// 专柜商品SID
						psps.setSupplySid((supplyInfoList.get(0).getSid()));// 供应商SID
						psps.setStatus(0);
						List<PcmShoppeProductSupply> pspsList = pspsMapper.selectListByParam(psps);
						if (pspsList != null && pspsList.size() > 0) {
							// 一品多供应商关系已存在
							throw new BleException(ErrorCode.SHOPP_IS_EXIST.getErrorCode(),
									ErrorCode.SHOPP_IS_EXIST.getMemo());
						} else {
							// 一品多供应商关系不存在--添加关系
							int res = pspsMapper.insertSelective(psps);
							if (res != 1) {
								throw new BleException(
										ErrorCode.SHOPPEPRODUCT_SUPPLY_RELATION_ERROR
												.getErrorCode(),
										ErrorCode.SHOPPEPRODUCT_SUPPLY_RELATION_ERROR.getMemo());
							}
						}
						shoppePro = shopp;
					} else {// 专柜商品不存在/创建SHOPPRO
						cProDto.setProductDetailSid(String.valueOf(proD.getSid()));// sku表SID
						if (!StringUtils.isNotBlank(cProDto.getShoppeProName())) {// 专柜商品名为空
							String shoppeProName = brand.getBrandName() + dataDto.getProductDesc()
									+ dataDto.getColorCode() + dataDto.getSizeCode();
							if ("2".equals(dataDto.getType())) {
								/*
								 * List<String> paras = new ArrayList<String>();
								 * paras.add(dataDto.getProductDesc());
								 * paras.add(dataDto.getColorCode());
								 * paras.add(dataDto.getSizeCode()); String
								 * shoppeProName =
								 * generateStandardName(paras,"");//生成标准品名
								 */cProDto.setShoppeProName(shoppeProName);// 专柜商品名称
							} else {
								cProDto.setShoppeProName(proD.getProDetailName());
							}
						}
						shoppePro = createService.insertShoppePro(cProDto);
						// 写入库存
						if (StringUtils.isNotBlank(dataDto.getInventory())) {
							saveInventory(shoppePro.getShoppeProSid(), dataDto.getInventory(),
									source, dataDto.getEntryNumber());
						} else {
							saveInventory(shoppePro.getShoppeProSid(), "0", source,
									dataDto.getEntryNumber());
						}
						// 写入临时表
						if (StringUtils.isNotBlank(dataDto.getEntryNumber())
								&& StringUtils.isNotBlank(dataDto.getProcurementPersonnelNumber())
								&& StringUtils.isNotBlank(dataDto.getOfferNumber())) {
							createService.insertProductInput(shoppePro.getSid(),
									dataDto.getOfferNumber(), dataDto.getEntryNumber(),
									dataDto.getProcurementPersonnelNumber(), dsPro);
						}
						// 写入一品多供应商关系表
						PcmShoppeProductSupply psps = new PcmShoppeProductSupply();
						psps.setShoppeProductSid((shoppePro.getSid()));// 专柜商品SID
						psps.setSupplySid((supplyInfoList.get(0).getSid()));// 供应商SID
						insertShoppeProductSupply(psps);
					}
				}
			} else if (proDList != null && proDList.size() == 0) {// SKU没有/创建SKU-SHOPPRO
				cSkuDto.setProductSid(String.valueOf(pro.getSid()));// 产品表SID
				cSkuDto.setProductName(pro.getProductName());
				PcmProDetail sku = createService.insertSKU(cSkuDto);
				skuFlag = sku.getSid();
				cProDto.setProductDetailSid(String.valueOf(sku.getSid()));
				if (!StringUtils.isNotBlank(cProDto.getShoppeProName())) {// 专柜商品名为空
					String shoppeProName = brand.getBrandName() + dataDto.getProductDesc()
							+ dataDto.getColorCode() + dataDto.getSizeCode();
					if ("2".equals(dataDto.getType())) {

						/*
						 * List<String> paras = new ArrayList<String>();//
						 * paras.add(dataDto.getProductDesc());
						 * paras.add(dataDto.getColorCode());
						 * paras.add(dataDto.getSizeCode()); String
						 * shoppeProName =
						 * generateStandardName(paras,"");//生成标准品名
						 */cProDto.setShoppeProName(shoppeProName);// 专柜商品名称
					} else {
						cProDto.setShoppeProName(sku.getProDetailName());
					}
				}
				shoppePro = createService.insertShoppePro(cProDto);
				// 写入库存
				if (StringUtils.isNotBlank(dataDto.getInventory())) {
					saveInventory(shoppePro.getShoppeProSid(), dataDto.getInventory(), source,
							dataDto.getEntryNumber());
				} else {
					saveInventory(shoppePro.getShoppeProSid(), "0", source,
							dataDto.getEntryNumber());
				}
				// 写入临时表
				if (StringUtils.isNotBlank(dataDto.getEntryNumber())
						&& StringUtils.isNotBlank(dataDto.getProcurementPersonnelNumber())
						&& StringUtils.isNotBlank(dataDto.getOfferNumber())) {
					createService.insertProductInput(shoppePro.getSid(), dataDto.getOfferNumber(),
							dataDto.getEntryNumber(), dataDto.getProcurementPersonnelNumber(),
							dsPro);
				}
				// 写入一品多供应商关系表
				PcmShoppeProductSupply psps = new PcmShoppeProductSupply();
				psps.setShoppeProductSid((shoppePro.getSid()));// 专柜商品SID
				psps.setSupplySid((supplyInfoList.get(0).getSid()));// 供应商SID
				insertShoppeProductSupply(psps);
			} else {
				throw new BleException(ErrorCode.SKU_IS_EXIST1.getErrorCode(),
						ErrorCode.SKU_IS_EXIST1.getMemo());
			}
		} else {// SPU没有/创建SPU-SKU-SHOPPRO
			CreateSpuDto cSpuDto = new CreateSpuDto();
			cSpuDto.setBrandName(brandList.get(0).getBrandName());// 集团品牌名称
			cSpuDto.setBrandRootSid(String.valueOf(brandList.get(0).getSid()));// 集团品牌表SID
			cSpuDto.setBrandSid(brandList.get(0).getBrandSid());// 集团品牌编码
			cSpuDto.setCategoryGYSid(cateList.get(0).getSid());// 工业分类SID
			cSpuDto.setFlag(Integer.parseInt(dataDto.getType()));// 超市百货标识_0百货_1超市
			cSpuDto.setPrimaryAttr(dataDto.getMainAttribute());// 主属性
			cSpuDto.setProductSku(dataDto.getProductNum());// 款号
			cSpuDto.setShortDes(dataDto.getShortDesc());// 短描述
			if (seasonList != null) {
				cSpuDto.setSeasonCode(String.valueOf(seasonList.get(0).getSid()));// 季节表SID
			}
			if ("男".equals(dataDto.getCrowdUser())) {
				cSpuDto.setSexSid(0);// 适合人群
			}
			if ("女".equals(dataDto.getCrowdUser())) {
				cSpuDto.setSexSid(1);// 适合人群
			}
			if ("中性".equals(dataDto.getCrowdUser())) {
				cSpuDto.setSexSid(2);// 适合人群
			}
			if ("儿童".equals(dataDto.getCrowdUser())) {
				cSpuDto.setSexSid(3);// 适合人群
			}
			cSpuDto.setYearToMarket(dataDto.getYearToMarket());// 上市年份
			// cSpuDto.setActivityFlg();
			// cSpuDto.setAwesome();
			// cSpuDto.setCategorySid();
			// cSpuDto.setCreateTime();
			// cSpuDto.setEditFlag();
			// cSpuDto.setOptUser();
			// cSpuDto.setProActiveBit();
			// cSpuDto.setProDescSid();
			// cSpuDto.setProductName();
			// cSpuDto.setProductNameAlias();
			// cSpuDto.setProPicture();
			// cSpuDto.setProSelling();
			// cSpuDto.setProSellingTime();
			// cSpuDto.setShortDes();
			// cSpuDto.setSpecialDes();
			PcmProduct spu = createService.insertSPU(cSpuDto);
			spuFlag = spu.getSid();
			cSkuDto.setProductSid(String.valueOf(spu.getSid()));
			cSkuDto.setProductName(spu.getProductName());
			PcmProDetail sku = createService.insertSKU(cSkuDto);
			skuFlag = sku.getSid();
			cProDto.setProductDetailSid(String.valueOf(sku.getSid()));
			if (!StringUtils.isNotBlank(cProDto.getShoppeProName())) {// 专柜商品名为空
				String shoppeProName = brand.getBrandName() + dataDto.getProductDesc()
						+ dataDto.getColorCode() + dataDto.getSizeCode();
				if ("2".equals(dataDto.getType())) {
					/*
					 * List<String> paras = new ArrayList<String>();//
					 * paras.add(dataDto.getProductDesc());
					 * paras.add(dataDto.getColorCode());
					 * paras.add(dataDto.getSizeCode()); String shoppeProName =
					 * generateStandardName(paras,"");//生成标准品名
					 */cProDto.setShoppeProName(shoppeProName);// 专柜商品名称
				} else {
					cProDto.setShoppeProName(sku.getProDetailName());
				}
			}
			shoppePro = createService.insertShoppePro(cProDto);
			// 写入库存
			if (StringUtils.isNotBlank(dataDto.getInventory())) {
				saveInventory(shoppePro.getShoppeProSid(), dataDto.getInventory(), source,
						dataDto.getEntryNumber());
			} else {
				saveInventory(shoppePro.getShoppeProSid(), "0", source, dataDto.getEntryNumber());
			}
			// 写入临时表
			if (StringUtils.isNotBlank(dataDto.getEntryNumber())
					&& StringUtils.isNotBlank(dataDto.getProcurementPersonnelNumber())
					&& StringUtils.isNotBlank(dataDto.getOfferNumber())) {
				createService.insertProductInput(shoppePro.getSid(), dataDto.getOfferNumber(),
						dataDto.getEntryNumber(), dataDto.getProcurementPersonnelNumber(), dsPro);
			}
			// 写入一品多供应商关系表
			PcmShoppeProductSupply psps = new PcmShoppeProductSupply();
			psps.setShoppeProductSid((shoppePro.getSid()));// 专柜商品SID
			psps.setSupplySid((supplyInfoList.get(0).getSid()));// 供应商SID
			insertShoppeProductSupply(psps);
		}
		logger.info("end savePullProductFromEFuture()");
		if (shoppePro != null) {
			shoppePro.setPackUnitDictSid(spuFlag);
			shoppePro.setMeasureUnitDictSid(skuFlag);
		}
		return shoppePro;
	}

	/**
	 * 非空字段校验
	 * 
	 * @Methods Name validPullDataDtoIsExists2
	 * @Create In 2015年8月20日 By zhangxy
	 * @param dataDto
	 * @return boolean
	 */
	public boolean validPullDataDtoIsExists(PullDataDto dataDto, Boolean sapErp)
			throws BleException {
		logger.info("start validPullDataDtoIsExists(),param:" + dataDto.toString());
		// 物流类型 包装组
		if (StringUtils.isNotBlank(dataDto.getTmsType())) {
			String tms = dataDto.getTmsType();
			if (Constants.PCMSHOPPEPRODECT_TMS_TYPE_Z1_STR.equals(tms)) {
				dataDto.setTmsParam("1");
			}
			if (Constants.PCMSHOPPEPRODECT_TMS_TYPE_Z2_STR.equals(tms)) {
				dataDto.setTmsParam("2");
			}
			if (Constants.PCMSHOPPEPRODECT_TMS_TYPE_Z3_STR.equals(tms)) {
				dataDto.setTmsParam("3");
			}
			if (Constants.PCMSHOPPEPRODECT_TMS_TYPE_Z4_STR.equals(tms)) {
				dataDto.setTmsParam("4");
			}
		}
		// 条码类型
		if (dataDto.getStandardBarCodes() != null && dataDto.getStandardBarCodes().size() != 0) {
			for (Map<String, Object> mb : dataDto.getStandardBarCodes()) {
				if (!mb.get("barCodeType").toString().matches(Constants.SUPPLIER_BARCODE_TYPE)) {
					logger.info("供应商条码类型应为1");
					throw new BleException(ErrorCode.SAPERP_PCM_ERROR_BARCODE_TYPE.getErrorCode(),
							ErrorCode.SAPERP_PCM_ERROR_BARCODE_TYPE.getMemo());
				}
			}
		}
		// 款号 -非空
		if (!StringUtils.isNotBlank(dataDto.getProductNum())) {
			logger.info("款号参数不可为空");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_KUANCODE.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_KUANCODE.getMemo());
		}
		// 款号 -非汉字
		if (!dataDto.getProductNum().matches("[^\\u4E00-\\u9FA5]+")) {
			logger.info("款号参数不可包含汉字");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_KUANCODE1.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_KUANCODE1.getMemo());
		}
		// 供应商编码-非空
		if (!StringUtils.isNotBlank(dataDto.getSupplierCode())) {
			logger.info("供应商编码不可为空");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_SUPPLIERCODE.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_SUPPLIERCODE.getMemo());
		}
		// 供应商编码-规则
		if (!dataDto.getSupplierCode().matches("\\w+")) {
			logger.info("供应商编码只能包含大小写字母、数字及下划线");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_SUPPLIERCODE1.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_SUPPLIERCODE1.getMemo());
		}
		// 专柜编码-非空
		if (!StringUtils.isNotBlank(dataDto.getCounterCode())) {
			logger.info("专柜编码不可为空");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_COUNTERCODE.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_COUNTERCODE.getMemo());
		}
		// 专柜编码-规则
		if (!dataDto.getCounterCode().matches("\\w+")) {
			logger.info("专柜编码只能包含大小写字母、数字及下划线");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_COUNTERCODE1.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_COUNTERCODE1.getMemo());
		}
		// 经营方式-非空
		if (!StringUtils.isNotBlank(dataDto.getOperateMode())) {
			logger.info("经营方式不能为空");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_OPERATEMODE.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_OPERATEMODE.getMemo());
		}
		// 经营方式-规则
		if (!dataDto.getOperateMode().matches(Constants.SUPPLIER_OPERATEMODE_TYPE)) {
			logger.info("经营方式应为0-4的数字(0 经销)(1 代销)(2 联营)(3 平台服务)(4 租赁)");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_OPERATEMODE1.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_OPERATEMODE1.getMemo());
		}
		// 验证百货货号
		if ("0".equals(dataDto.getType()) && "2".equals(dataDto.getOperateMode())
				&& StringUtils.isBlank(dataDto.getModelNum())) {
			logger.info("百货联营货号不能为空");
			throw new BleException(ErrorCode.PRO_MODELNUM1_NULL.getErrorCode(),
					ErrorCode.PRO_MODELNUM1_NULL.getMemo());
		}
		// 验证电商货号
		if ("2".equals(dataDto.getType()) && "2".equals(dataDto.getOperateMode())
				&& StringUtils.isBlank(dataDto.getModelNum())) {
			logger.info("电商联营货号不能为空");
			throw new BleException(ErrorCode.PRO_MODELNUM_NULL.getErrorCode(),
					ErrorCode.PRO_MODELNUM_NULL.getMemo());
		}
		// 品牌编码 -非空
		if (!StringUtils.isNotBlank(dataDto.getBrandCode())) {
			logger.info("品牌编码不能为空");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_BRANDCODE1.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_BRANDCODE1.getMemo());
		}
		// 品牌编码-规则
		if (!dataDto.getBrandCode().matches("\\d+")) {
			logger.info("品牌编码只能为数字");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_BRANDCODE.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_BRANDCODE.getMemo());
		}
		// 销售单位编码
		if (!StringUtils.isNotBlank(dataDto.getUnitCode())) {
			logger.info("销售单位不能为空");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_UNITCODE.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_UNITCODE.getMemo());
		}
		// 尺码数据
		if (!StringUtils.isNotBlank(dataDto.getSizeCode())) {
			logger.info("规格/尺码不能为空");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_SIZECODE.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_SIZECODE.getMemo());
		}
		// 品牌专柜(管理分类编码)
		if (!StringUtils.isNotBlank(dataDto.getManageCateGory())) {
			if (!sapErp) {
				logger.info("品牌专柜(管理分类)编码参数不能为空");
				throw new BleException(ErrorCode.SAPERP_PCM_ERROR_MANAGECATEGORY.getErrorCode(),
						ErrorCode.SAPERP_PCM_ERROR_MANAGECATEGORY.getMemo());
			}
		}
		// 品牌专柜（管理分类编码）
		if (!dataDto.getManageCateGory().matches("\\d+")) {
			if (!sapErp) {
				logger.info("品牌专柜(管理分类)编码参数只能为数字");
				throw new BleException(ErrorCode.SAPERP_PCM_ERROR_MANAGECATEGORY1.getErrorCode(),
						ErrorCode.SAPERP_PCM_ERROR_MANAGECATEGORY1.getMemo());
			}
		}
		// 要约号
		if (!StringUtils.isNotBlank(dataDto.getOfferNumber())) {
			logger.info("要约号参数不能为空");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_OFFERNUMBER.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_OFFERNUMBER.getMemo());
		}
		// 扣率/进价
		if (StringUtils.isNotBlank(dataDto.getRate_price())) {
			String temp = dataDto.getRate_price();
			if (temp.indexOf("%") != -1) {
				temp = temp.substring(0, temp.length() - 1);
				Double db = Double.parseDouble(temp);
				db = (double) (db / 100);
				dataDto.setRate_price(String.valueOf(db));
			}
			if (!dataDto.getRate_price().matches("\\d+.{0,1}\\d*")) {
				logger.info("扣率/进价参数应为整数数字或小数数字");
				throw new BleException(ErrorCode.SAPERP_PCM_ERROR_RATEPRICE.getErrorCode(),
						ErrorCode.SAPERP_PCM_ERROR_RATEPRICE.getMemo());
			}
		}
		// 扣率/含税进价
		if (StringUtils.isNotBlank(dataDto.getPurchasePrice_taxRebate())) {
			String temp = dataDto.getPurchasePrice_taxRebate();
			if (temp.indexOf("%") != -1) {
				temp = temp.substring(0, temp.length() - 1);
				Double db = Double.parseDouble(temp);
				db = (double) (db / 100);
				dataDto.setPurchasePrice_taxRebate(String.valueOf(db));
			}
			if (!dataDto.getPurchasePrice_taxRebate().matches("\\d+.{0,1}\\d*")) {
				logger.info("扣率/含税进价参数只能为整数数字或小数数字");
				throw new BleException(ErrorCode.SAPERP_PCM_ERROR_RATEPRICE.getErrorCode(),
						ErrorCode.SAPERP_PCM_ERROR_RATEPRICE.getMemo());
			}
		}
		// 消费税
		if (StringUtils.isNotBlank(dataDto.getConsumptionTax())) {
			String temp = dataDto.getConsumptionTax();
			if (temp.indexOf("%") != -1) {
				temp = temp.substring(0, temp.length() - 1);
				Double db = Double.parseDouble(temp);
				db = (double) (db / 100);
				dataDto.setConsumptionTax(String.valueOf(db));
			}
			if (!dataDto.getConsumptionTax().matches("\\d+.{0,1}\\d*")) {
				logger.info("消费税参数只能为整数数字或小数数字");
				throw new BleException(ErrorCode.SAPERP_PCM_ERROR_SALESTAX.getErrorCode(),
						ErrorCode.SAPERP_PCM_ERROR_SALESTAX.getMemo());
			}
		}
		// 供应商接口消费税
		if (StringUtils.isNotBlank(dataDto.getSalesTax())) {
			String temp = dataDto.getSalesTax();
			if (temp.indexOf("%") != -1) {
				temp = temp.substring(0, temp.length() - 1);
				Double db = Double.parseDouble(temp);
				db = (double) (db / 100);
				dataDto.setSalesTax(String.valueOf(db));
			}
			if (!dataDto.getSalesTax().matches("\\d+.{0,1}\\d*")) {
				logger.info("消费税参数不符合规则");
				throw new BleException(ErrorCode.SAPERP_PCM_ERROR_SALESTAX.getErrorCode(),
						ErrorCode.SAPERP_PCM_ERROR_SALESTAX.getMemo());
			}
		}
		// 进项税
		if (StringUtils.isNotBlank(dataDto.getInputTax())) {
			String temp = dataDto.getInputTax();
			if (temp.indexOf("%") != -1) {
				temp = temp.substring(0, temp.length() - 1);
				Double db = Double.parseDouble(temp);
				db = (double) (db / 100);
				dataDto.setInputTax(String.valueOf(db));
			}
			if (!dataDto.getInputTax().matches("\\d+.{0,1}\\d*")) {
				logger.info("进项税参数不符合规则");
				throw new BleException(ErrorCode.SAPERP_PCM_ERROR_INPUTTAX.getErrorCode(),
						ErrorCode.SAPERP_PCM_ERROR_INPUTTAX.getMemo());
			}
		}
		// 销项税
		if (StringUtils.isNotBlank(dataDto.getOutputTax())) {
			String temp = dataDto.getOutputTax();
			if (temp.indexOf("%") != -1) {
				temp = temp.substring(0, temp.length() - 1);
				Double db = Double.parseDouble(temp);
				db = (double) (db / 100);
				dataDto.setOutputTax(String.valueOf(db));
			}
			if (!dataDto.getOutputTax().matches("\\d+.{0,1}\\d*")) {
				logger.info("销项税参数不符合规则");
				throw new BleException(ErrorCode.SAPERP_PCM_ERROR_OUTPUTTAX.getErrorCode(),
						ErrorCode.SAPERP_PCM_ERROR_OUTPUTTAX.getMemo());
			}
		}
		// 产地
		if (!StringUtils.isNotBlank(dataDto.getOriginLand())) {
			if (!sapErp) {
				logger.info("产地参数不能为空");
				throw new BleException(ErrorCode.SAPERP_PCM_ERROR_ORIGIN.getErrorCode(),
						ErrorCode.SAPERP_PCM_ERROR_ORIGIN.getMemo());
			}
		}
		// 商品类别
		if (StringUtils.isBlank(dataDto.getProductType())) {
			logger.info("商品类别参数不能为空");
			throw new BleException(ErrorCode.PRO_SUP_PROTYPE_ISNULL.getErrorCode(),
					ErrorCode.PRO_SUP_PROTYPE_ISNULL.getMemo());
		}
		// 末级工业分类代码-非空
		if (!StringUtils.isNotBlank(dataDto.getProdCategoryCode())) {
			logger.info("末级工业分类代码参数不能为空");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_PRODCATEGORYCODE.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_PRODCATEGORYCODE.getMemo());
		}
		// 末级工业分类代码-规则
		if (!dataDto.getProdCategoryCode().matches("\\d+")) {
			logger.info("末级工业分类代码参数只能为数字");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_PRODCATEGORYCODE1.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_PRODCATEGORYCODE1.getMemo());
		}
		// 末级统计分类代码
		if (!StringUtils.isNotBlank(dataDto.getFinalClassiFicationCode())) {
			logger.info("末级统计分类代码参数不能为空");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_STATCATEGORYCODE.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_STATCATEGORYCODE.getMemo());
		}
		// 末级统计分类编码-规则
		if (!dataDto.getFinalClassiFicationCode().matches("\\d+")) {
			logger.info("末级统计分类代码参数只能为数字");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_STATCATEGORYCODE1.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_STATCATEGORYCODE1.getMemo());
		}
		// 吊牌价
		if (!StringUtils.isNotBlank(dataDto.getMarketPrice())) {
			logger.info("吊牌价参数不能为空");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_MARKETPRICE.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_MARKETPRICE.getMemo());
		}
		// 吊牌价-规则
		if (!dataDto.getMarketPrice().matches("\\d+.{0,1}\\d*")) {
			logger.info("吊牌价参数不符合规则");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_MARKETPRICE1.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_MARKETPRICE1.getMemo());
		}
		// 零售价
		if (StringUtils.isBlank(dataDto.getSalePrice())) {
			logger.info("零售价参数不能为空");
			throw new BleException(ErrorCode.SUPPLIER_PCM_SALEPRICE_ISNULL.getErrorCode(),
					ErrorCode.SUPPLIER_PCM_SALEPRICE_ISNULL.getMemo());
		}
		// 零售价-规则
		if (StringUtils.isNotBlank(dataDto.getSalePrice())) {
			if (!dataDto.getSalePrice().matches("\\d+.{0,1}\\d*")) {
				logger.info("零售价参数只能为整数数字或小数数字");
				throw new BleException(ErrorCode.SUPPLIER_PCM_SALEPRICE_ERROR.getErrorCode(),
						ErrorCode.SUPPLIER_PCM_SALEPRICE_ERROR.getMemo());
			}
		}
		// 库存方式
		if (StringUtils.isBlank(dataDto.getStockType())) {
			logger.info("库存方式不能为空");
			throw new BleException(ErrorCode.SAPERP_PCM_ERROR_STOCK_MODE.getErrorCode(),
					ErrorCode.SAPERP_PCM_ERROR_STOCK_MODE.getMemo());
		}
		logger.info("end validPullDataDtoIsExists()");
		return true;
	}

	/**
	 * 特殊字段校验
	 * 
	 * @Methods Name validSpeParaIsExists
	 * @Create In 2015年7月21日 By wangsy
	 * @param dataDto
	 * @return boolean
	 */
	public void validSupParaIsExists(PullDataDto dataDto) throws BleException {
		logger.info("start validSpeParaIsExists(),param:" + dataDto.toString());
		if (dataDto.getType().equals(String.valueOf(Constants.PUBLIC_0))) {
			// 百货字段 商品名不能为空
			if (!StringUtils.isNotBlank(dataDto.getProductName())) {
				logger.error(ErrorCode.BH_PARAS_ERROR.getErrorCode() + "----"
						+ ErrorCode.BH_PARAS_ERROR.getMemo() + "----" + dataDto.toString());
				throw new BleException(ErrorCode.BH_PARAS_ERROR.getErrorCode(),
						ErrorCode.BH_PARAS_ERROR.getMemo() + ":商品名称");
			}
			// 百货字段 款号不能为空
			if (!StringUtils.isNotBlank(dataDto.getProductNum())) {
				logger.error(ErrorCode.BH_PARAS_ERROR.getErrorCode() + "----"
						+ ErrorCode.BH_PARAS_ERROR.getMemo() + "----" + dataDto.toString());
				throw new BleException(ErrorCode.BH_PARAS_ERROR.getErrorCode(),
						ErrorCode.BH_PARAS_ERROR.getMemo() + ":款号");
			}
			// 百货字段 色系不能为空
			if (!StringUtils.isNotBlank(dataDto.getProColor())) {
				logger.error(ErrorCode.BH_PARAS_ERROR.getErrorCode() + "----"
						+ ErrorCode.BH_PARAS_ERROR.getMemo() + "----" + dataDto.toString());
				throw new BleException(ErrorCode.BH_PARAS_ERROR.getErrorCode(),
						ErrorCode.BH_PARAS_ERROR.getMemo() + ":色系");
			}
			// 色码不能为空
			if (!StringUtils.isNotBlank(dataDto.getColorCode())) {
				logger.error(ErrorCode.BH_PARAS_ERROR.getErrorCode() + "----"
						+ ErrorCode.BH_PARAS_ERROR.getMemo() + "----" + dataDto.toString());
				throw new BleException(ErrorCode.BH_PARAS_ERROR.getErrorCode(),
						ErrorCode.BH_PARAS_ERROR.getMemo() + ":色码");
			}
			// 百货字段 年份不能为空
			if (!StringUtils.isNotBlank(dataDto.getYearToMarket())) {
				logger.error(ErrorCode.BH_PARAS_ERROR.getErrorCode() + "----"
						+ ErrorCode.BH_PARAS_ERROR.getMemo() + "----" + dataDto.toString());
				throw new BleException(ErrorCode.BH_PARAS_ERROR.getErrorCode(),
						ErrorCode.BH_PARAS_ERROR.getMemo() + ":上市年份");
			}
			// 百货字段 季节不能为空
			if (!StringUtils.isNotBlank(dataDto.getSeasonCode())) {
				logger.error(ErrorCode.BH_PARAS_ERROR.getErrorCode() + "----"
						+ ErrorCode.BH_PARAS_ERROR.getMemo() + "----" + dataDto.toString());
				throw new BleException(ErrorCode.BH_PARAS_ERROR.getErrorCode(),
						ErrorCode.BH_PARAS_ERROR.getMemo() + "：季节");
			}
			// 百货非联营 销项税 进项税 不能为空
			if (!dataDto.getOperateMode().equals(
					String.valueOf(Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z3))) {
				if (StringUtils.isBlank(dataDto.getInputTax())
						|| StringUtils.isBlank(dataDto.getOutputTax())) {
					logger.error(ErrorCode.BH_PARAS_ERROR1.getErrorCode() + "----"
							+ ErrorCode.BH_PARAS_ERROR1.getMemo() + "----" + dataDto.toString());
					throw new BleException(ErrorCode.BH_PARAS_ERROR1.getErrorCode(),
							ErrorCode.BH_PARAS_ERROR1.getMemo());
				}
			}
		} else if (dataDto.getType().equals(String.valueOf(Constants.PUBLIC_1))) {
			// 超市字段 主属性不能为空
			if (StringUtils.isBlank(dataDto.getMainAttribute())) {
				logger.error(ErrorCode.CS_PARAS_ERROR.getErrorCode() + "----"
						+ ErrorCode.CS_PARAS_ERROR.getMemo() + "----" + dataDto.toString());
				throw new BleException(ErrorCode.CS_PARAS_ERROR.getErrorCode(),
						ErrorCode.CS_PARAS_ERROR.getMemo() + "：主属性");
			}
			// 超市字段 特性不能为空
			if (StringUtils.isBlank(dataDto.getFeatures())) {
				logger.error(ErrorCode.CS_PARAS_ERROR.getErrorCode() + "----"
						+ ErrorCode.CS_PARAS_ERROR.getMemo() + "----" + dataDto.toString());
				throw new BleException(ErrorCode.CS_PARAS_ERROR.getErrorCode(),
						ErrorCode.CS_PARAS_ERROR.getMemo() + "：特性");
			}
			// 超市字段 注册商标名不能为空
			if (StringUtils.isBlank(dataDto.getRegisteredTradeName())) {
				logger.error(ErrorCode.CS_PARAS_ERROR.getErrorCode() + "----"
						+ ErrorCode.CS_PARAS_ERROR.getMemo() + "----" + dataDto.toString());
				throw new BleException(ErrorCode.CS_PARAS_ERROR.getErrorCode(),
						ErrorCode.CS_PARAS_ERROR.getMemo() + "：商品注册商标名 ");
			}
			// 超市字段进项税 不能为空
			if (StringUtils.isBlank(dataDto.getInputTax())) {
				logger.error(ErrorCode.CS_PARAS_ERROR.getErrorCode() + "----"
						+ ErrorCode.CS_PARAS_ERROR.getMemo() + "----" + dataDto.toString());
				throw new BleException(ErrorCode.CS_PARAS_ERROR.getErrorCode(),
						ErrorCode.CS_PARAS_ERROR.getMemo() + "：进项税");
			}
			// 超市字段销项税不能为空
			if (StringUtils.isBlank(dataDto.getOutputTax())) {
				logger.error(ErrorCode.CS_PARAS_ERROR.getErrorCode() + "----"
						+ ErrorCode.CS_PARAS_ERROR.getMemo() + "----" + dataDto.toString());
				throw new BleException(ErrorCode.CS_PARAS_ERROR.getErrorCode(),
						ErrorCode.CS_PARAS_ERROR.getMemo() + "：销项税");
			}
		} else if (dataDto.getType().equals(String.valueOf(Constants.PUBLIC_2))) {// 电商
			if (!StringUtils.isNotBlank(dataDto.getProductNum())) {// 电商商品款号不能为空
				logger.error(ErrorCode.DS_PARA_ERROR.getErrorCode() + "----"
						+ ErrorCode.DS_PARA_ERROR.getMemo() + "----" + dataDto.toString());
				throw new BleException(ErrorCode.DS_PARA_ERROR.getErrorCode(),
						ErrorCode.DS_PARA_ERROR.getMemo() + "：款号");
			}
			if (!StringUtils.isNotBlank(dataDto.getProColor())) {// 电商商品色系不能为空
				logger.error(ErrorCode.DS_PARA_ERROR.getErrorCode() + "----"
						+ ErrorCode.DS_PARA_ERROR.getMemo() + "----" + dataDto.toString());
				throw new BleException(ErrorCode.DS_PARA_ERROR.getErrorCode(),
						ErrorCode.DS_PARA_ERROR.getMemo() + "：色系");
			}
			if (!StringUtils.isNotBlank(dataDto.getColorCode())) {// 电商商品色码不能为空
				logger.error(ErrorCode.DS_PARA_ERROR.getErrorCode() + "----"
						+ ErrorCode.DS_PARA_ERROR.getMemo() + "----" + dataDto.toString());
				throw new BleException(ErrorCode.DS_PARA_ERROR.getErrorCode(),
						ErrorCode.DS_PARA_ERROR.getMemo() + "：色码");
			}
		}
		if (!dataDto.getType().equals(String.valueOf(Constants.PUBLIC_2))) {
			if (dataDto.getOperateMode().equals(
					String.valueOf(Constants.PCMERPPRODUCT_PRODUCT_TYPE_Z3))) {
			} else {
				// 当经营方式不为联营时 扣率/进价 与 扣率/含税进价 字段不能同时空
				if (!StringUtils.isNotBlank(dataDto.getRate_price())
						&& !StringUtils.isNotBlank(dataDto.getPurchasePrice_taxRebate())) {
					logger.error(ErrorCode.FLY_PARAS_ERROR.getErrorCode() + "----"
							+ ErrorCode.FLY_PARAS_ERROR.getMemo() + "----" + dataDto.toString());
					throw new BleException(ErrorCode.FLY_PARAS_ERROR.getErrorCode(),
							ErrorCode.FLY_PARAS_ERROR.getMemo());
				}
			}
		}
		logger.info("end validSpeParaIsExists()");
	}

	/**
	 * 条码校验 同一门店下条码不能重复
	 * 
	 * @Methods Name validBarcode
	 * @Create In 2015年9月9日 By zhangxy
	 * @param storeCode
	 * @param barcode
	 * @param type
	 */
	void validBarcode(String storeCode, String barcode) {
		if (!barcode.matches("\\d+")) {
			logger.error("条码格式错误");
			throw new BleException(ErrorCode.PARA_NORULE_ERROR.getErrorCode(),
					ErrorCode.PARA_NORULE_ERROR.getMemo());
		}
		// 条码校验 同一门店下条码不能重复
		PcmBarcode barcodeEntity = new PcmBarcode();
		barcodeEntity.setBarcode(barcode);// 条码
		barcodeEntity.setStoreCode(storeCode);// 门店编码
		List<PcmBarcode> barcodeList = barcodeMapper.selectListByParam(barcodeEntity);
		if (barcodeList != null && barcodeList.size() > 0) {
			throw new BleException(ErrorCode.BARCODE_IS_EXIST.getErrorCode(),
					ErrorCode.BARCODE_IS_EXIST.getMemo());
		}
	}

	/**
	 * 条码校验 同一门店下条码不能重复
	 * 
	 * @Methods Name validBarcode
	 * @Create In 2016-3-1 By wangc
	 * @param barcodes
	 * @param storeCode
	 *            void
	 */
	void validBarcode(List<Map<String, Object>> barcodes, String storeCode) {
		for (Map<String, Object> barcode : barcodes) {
			if (!barcode.get("barcode").toString().matches("\\d+")) {
				logger.error("条码格式错误");
				throw new BleException(ErrorCode.PARA_NORULE_ERROR.getErrorCode(),
						ErrorCode.PARA_NORULE_ERROR.getMemo());
			}
		}
		PcmBarcode barcodeEntity = new PcmBarcode();
		barcodeEntity.setStoreCode(storeCode);// 门店编码
		for (Map<String, Object> barcode : barcodes) {
			barcodeEntity.setBarcode(barcode.get("barcode").toString());// 条码
			List<PcmBarcode> barcodeList = barcodeMapper.selectListByParam(barcodeEntity);
			if (barcodeList != null && barcodeList.size() > 0) {
				throw new BleException(ErrorCode.BARCODE_IS_EXIST.getErrorCode(),
						ErrorCode.BARCODE_IS_EXIST.getMemo() + ":" + barcodeEntity.getBarcode());
			}
		}
	}

	/**
	 * 电商条码校验 （不验证条码格式，只做判重）
	 * 
	 * @Methods Name validBarcodeSap
	 * @Create In 2015年12月10日 By wangc
	 * @param String
	 *            storeCode, String barcode
	 * @return Map<String, Object>
	 * @throws Exception
	 */
	void validBarcodeSap(String storeCode, String barcode) {
		// 条码校验 同一门店下条码不能重复
		PcmBarcode barcodeEntity = new PcmBarcode();
		barcodeEntity.setBarcode(barcode);// 条码
		barcodeEntity.setStoreCode(storeCode);// 门店编码
		List<PcmBarcode> barcodeList = barcodeMapper.selectListByParam(barcodeEntity);
		if (barcodeList != null && barcodeList.size() > 0) {
			throw new BleException(ErrorCode.BARCODE_IS_EXIST.getErrorCode(),
					ErrorCode.BARCODE_IS_EXIST.getMemo());
		}
	}

	/**
	 * 电商条码验证，不验证格式，只判重
	 * 
	 * @Methods Name validBarcodeSap
	 * @Create In 2016-3-1 By wangc
	 * @param barcodes
	 * @param storeCode
	 *            void
	 */
	void validBarcodeSap(List<Map<String, Object>> barcodes, String storeCode) {
		PcmBarcode barcodeEntity = new PcmBarcode();
		barcodeEntity.setStoreCode(storeCode);// 门店编码
		for (Map<String, Object> barcode : barcodes) {
			barcodeEntity.setBarcode(barcode.get("barcode").toString());// 条码
			List<PcmBarcode> barcodeList = barcodeMapper.selectListByParam(barcodeEntity);
			if (barcodeList != null && barcodeList.size() > 0) {
				throw new BleException(ErrorCode.BARCODE_IS_EXIST.getErrorCode(),
						ErrorCode.BARCODE_IS_EXIST.getMemo() + ":" + barcodeEntity.getBarcode());
			}
		}
	}

	/**
	 * 写入库存表
	 * 
	 * @Methods Name saveInventory
	 * @Create In 2015年9月7日 By zhangxy
	 * @param sid
	 * @param inventory
	 */
	private void saveInventory(String code, String inventory, String source, String operator) {
		PcmStock record = new PcmStock();
		record.setShoppeProSid(code);
		record.setStockTypeSid(Constants.PCMSTOCK_TYPE_SALE);
		record.setProSum(Long.parseLong(inventory));
		int i = stockMapper.insertSelective(record);
		if (i != 1) {
			throw new BleException(ErrorCode.STOCK_INPUT_ERROR.getErrorCode(),
					ErrorCode.STOCK_INPUT_ERROR.getMemo());
		}
		if (!"0".equals(inventory)) {
			PcmStockDto pcmStockDto = new PcmStockDto();
			pcmStockDto.setProSum(Long.parseLong(inventory));
			pcmStockDto.setSid(record.getSid());
			pcmStockDto.setShoppeProSid(code);
			pcmStockDto.setStockTypeSid(1001);
			pcmStockDto.setSource(source);
			pcmStockDto.setOperator(operator);
			scrsService.changRecord(pcmStockDto, null, 0);
			PcmProductStockInfoDto dto = new PcmProductStockInfoDto();
			dto.setShoppeProSid(code);
			stockService.SelectSkuStockSumBySku(dto);
		}
	}

	/**
	 * 写入一品多供应商关系表
	 * 
	 * @Methods Name insertShoppeProductSupply
	 * @Create In 2015年9月16日 By zhangxy
	 * @param proSid
	 *            supplySid
	 */
	void insertShoppeProductSupply(PcmShoppeProductSupply psps) {
		psps.setStatus(0);
		List<PcmShoppeProductSupply> pspsList = pspsMapper.selectListByParam(psps);
		if (pspsList != null && pspsList.size() > 0) {
			// 一品多供应商关系已存在
			throw new BleException(ErrorCode.SHOPP_IS_EXIST.getErrorCode(),
					ErrorCode.SHOPP_IS_EXIST.getMemo());
		} else {
			// 一品多供应商关系不存在--添加关系
			int res = pspsMapper.insertSelective(psps);
			if (res != 1) {
				throw new BleException(
						ErrorCode.SHOPPEPRODUCT_SUPPLY_RELATION_ERROR.getErrorCode(),
						ErrorCode.SHOPPEPRODUCT_SUPPLY_RELATION_ERROR.getMemo());
			}
		}
	}

	/**
	 * 生成标准品名
	 * 
	 * @Methods Name generateStandardName
	 * @Create In 2016-3-2 By wangc
	 * @param paras
	 * @param cons
	 * @return String
	 */
	public String generateStandardName(List<String> paras, String cons) {
		String result = "";
		for (int i = 0; i < paras.size(); i++) {
			String str = paras.get(i).toString();
			if (i == paras.size() - 1) {
				result = result + str;
			} else {
				result = result + str + cons;
			}
		}
		if (result != null) {
			result = result.trim();
		}
		return result;
	}
}
