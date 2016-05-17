package com.wangfj.product.maindata.domain.vo;

import java.util.List;
import java.util.Map;

import com.wangfj.core.framework.base.dto.BaseDto;
import com.wangfj.product.maindata.domain.entity.PcmBarcode;

/**
 * 专柜商品分页DTO
 * 
 * @Class Name ProductPageDto
 * @Author zhangxy
 * @Create In 2015年7月15日
 */
public class ProductPageProCodesDto extends BaseDto {
	private String cateZSCode;// 展示分类编码
	private String cateZSName;// 展示分类
	private List<String> sidList;
	private List<String> itemIds;
	private Long sid;
	private Long spuSid;
	private Long skuSid;
	private String picUrl;// 主图路径
	private String productCode;// 专柜商品编码
	private String skuCode;// 商品表SKU 编码
	private String spuCode;// 产品表SPU 编码
	private String discountCode;// 扣率码
	private String erpProductCode;// 大码
	private String productAbbr;// 专柜商品简称
	private String modelCode;// 款号
	private String supplyProCode;// 供应商商品编码
	private String productName;// 专柜商品名称
	private String spuName;// 产品名(spu标准品名)
	private String skuName;// 商品名(sku标准品名)
	private String field2;//
	private String supplierSid;// 供应商SID
	private String supplierCode;// 供应商编码
	private String supplierName;// 供应商名称
	private String brandSid;// 门店品牌sid
	private String brandCode;// 门店品牌编码
	private String brandName;// 门店品牌名称
	private String brandGroupCode;// 集团品牌编码
	private String brandGroupName;// 集团品牌名称
	private String counterCode;// 专柜编码
	private String counterName;// 专柜名
	private String storeSid;// 门店编码
	private String storeCode;// 门店编码
	private String storeName;// 门店名称
	private String manageCategory;// 管理分类SID
	private String manageCategoryCode;// 管理分类编码
	private String colorCode;// 颜色编码
	private String colorName;// 颜色名称
	private String colorSid;// 色系SID
	private String proColor;// 色系名
	private String stanCode;// 规格编码
	private String stanName;// 规格名称
	private String isSale;// 专柜商品可售状态
	private String skuSale;// sku上架状态
	private String spuSale;// spu上架状态
	private String manageType;// 管理类型
	private String stockType;// 库存方式
	private String negativeStock;// 是否负库存销售
	private String articleNum;// 货号
	private String isDiscountable;// 是否可打折( 0 是,1 否, 默认0)
	private String isCOD;// 是否支持货到付款(0支持；1不支持)
	private String unitCode;// 销售单位编码
	private String unitName;// 销售单位名
	private String salesPrice;// 零售价
	private String marketPrice;// 市场价
	private String maxDiscountRate;// 折扣底线
	private String productStatus;// erp表中的商品状态0正常；1停售；2停货；3暂停使用；4已删除；5淘汰
	private String operateMode;// 经营方式编码
	private String commissionRate;// 标准扣率
	private String originSalesUnit;// 源系统销售单位
	private String erpSkuType;// 大码类型
	private String statCategory;// 统计分类SID
	private String statCategoryCode;// 统计分类编码
	private String statCategoryName;// 统计分类名称
	private String channelSid;// 渠道sid
	private String channelName;// 渠道名称
	private String productType;// 商品类型
	private String tmsParam;// 物流属性
	private String promotionPrice;// 促销价
	private List<String> supplierIntBarCode;// 供应商内部自编条码
	private List<PcmBarcode> barcodeList;// 供应商条码List
	// private String rate;// 倍率
	private String category;// 工业分类编码
	private String categoryName;// 工业分类名称
	private String season;// 季节
	private List<Map<String, Object>> changePrice;// 变价列表
	private List<Map<String, Object>> attribute;// 属性列表
	private String onMarketDate;// 上市年份
	private String industrySid;// 业态方式
	private String industryName;// 业态方式名称
	// private String priceChannel;// 价格来源
	private String inputTax;// 进项税
	private String outputTax;// 销项税
	private String salesTax;// 销售税
	private String purchasePrice;// 扣率/进价
	private String buyingPrice;// 扣率/含税进价
	private String stockMode;// 库存方式(自库 虚库 不管库)
	private String processType;// 加工方式
	private String originLand;// 产地
	private String originLand2;// 原产地
	private String orderType;// 订货方式
	private String isPromotion;// 是否支持ERP促销
	private String isAdjustPrice;// 是否支持ERP变价
	private String primaryAttr;// 产品主属性
	private String features;// 商品特性
	private String sexSid;// 适合性别编号
	private String awesome;// 被赞次数
	private String saleStock;// 可售库存
	private String edefectiveStock;// 残次品库存
	private String returnStock;// 退货库存
	private String lockedStock;// 锁定库存
	private String priceType;// 价格类型
	private String productCategory;// 商品类别:01 自营单品,3:金额码,4:销售条码,05 金银首饰,06
									// 服务费商品,07 租赁商品,,08 联营单品,09 组包码
	private String contractCode;// 要约号
	private String procurementUserCode;// 采购人员编号
	private String inputUserCode;// 录入人员编号
	private String glCategoryName;// 管理分类名称
	private String billsNo;// 单据号
	private Integer currentPage;// 当前页
	private Integer pageSize;// 单页行数
	private String notes;// 备注
	private Integer start;
	private Integer limit;
	private String field4;// 原系统商品编码
	private String isGift;// 赠品范围
	private String isCard;// 可贺卡-
	private String baseUnitCode;// 基本计量单位
	private String originCountry;// 原产国
	private String isOriginPackage;// 是否有原厂包装
	private String xxhcFlag;// 先销后采
	private String isPacking;// 可包装
	private String counterSid;// 专柜SID

	
	public List<String> getItemIds() {
		return itemIds;
	}

	public void setItemIds(List<String> itemIds) {
		this.itemIds = itemIds;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getStatCategoryCode() {
		return statCategoryCode;
	}

	public void setStatCategoryCode(String statCategoryCode) {
		this.statCategoryCode = statCategoryCode;
	}

	public String getCounterSid() {
		return counterSid;
	}

	public void setCounterSid(String counterSid) {
		this.counterSid = counterSid;
	}

	public String getIsCard() {
		return isCard;
	}

	public void setIsCard(String isCard) {
		this.isCard = isCard;
	}

	public String getBaseUnitCode() {
		return baseUnitCode;
	}

	public void setBaseUnitCode(String baseUnitCode) {
		this.baseUnitCode = baseUnitCode;
	}

	public String getOriginCountry() {
		return originCountry;
	}

	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}

	public String getIsOriginPackage() {
		return isOriginPackage;
	}

	public void setIsOriginPackage(String isOriginPackage) {
		this.isOriginPackage = isOriginPackage;
	}

	public String getXxhcFlag() {
		return xxhcFlag;
	}

	public void setXxhcFlag(String xxhcFlag) {
		this.xxhcFlag = xxhcFlag;
	}

	public String getIsPacking() {
		return isPacking;
	}

	public void setIsPacking(String isPacking) {
		this.isPacking = isPacking;
	}

	public String getIsGift() {
		return isGift;
	}

	public void setIsGift(String isGift) {
		this.isGift = isGift;
	}

	public String getField4() {
		return field4;
	}

	public void setField4(String field4) {
		this.field4 = field4;
	}

	public List<String> getSidList() {
		return sidList;
	}

	public void setSidList(List<String> sidList) {
		this.sidList = sidList;
	}

	/**
	 * 渠道名称
	 */
	public String getChannelName() {
		return channelName;
	}

	/**
	 * 渠道名称
	 */
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	/**
	 * 专柜商品编码
	 */
	public String getProductCode() {
		return productCode;
	}

	/**
	 * 专柜商品编码
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	/**
	 * 商品表SKU 编码
	 */
	public String getSkuCode() {
		return skuCode;
	}

	/**
	 * 商品表SKU 编码
	 */
	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	/**
	 * 产品编SPU 编码
	 */
	public String getSpuCode() {
		return spuCode;
	}

	/**
	 * 产品编SPU 编码
	 */
	public void setSpuCode(String spuCode) {
		this.spuCode = spuCode;
	}

	/**
	 * 扣率码
	 */
	public String getDiscountCode() {
		return discountCode;
	}

	/**
	 * 扣率码
	 */
	public void setDiscountCode(String discountCode) {
		this.discountCode = discountCode;
	}

	/**
	 * 大码
	 */
	public String getErpProductCode() {
		return erpProductCode;
	}

	/**
	 * 大码
	 */
	public void setErpProductCode(String erpProductCode) {
		this.erpProductCode = erpProductCode;
	}

	/**
	 * 款号
	 */
	public String getModelCode() {
		return modelCode;
	}

	/**
	 * 款号
	 */
	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}

	/**
	 * 供应商商品编码
	 */
	public String getSupplyProCode() {
		return supplyProCode;
	}

	/**
	 * 供应商商品编码
	 */
	public void setSupplyProCode(String supplyProCode) {
		this.supplyProCode = supplyProCode;
	}

	/**
	 * 产品名称
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * 产品名称
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * 供应商编码
	 */
	public String getSupplierCode() {
		return supplierCode;
	}

	/**
	 * 供应商编码
	 */
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	/**
	 * 门店品牌编码
	 */
	public String getBrandCode() {
		return brandCode;
	}

	/**
	 * 门店品牌编码
	 */
	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	/**
	 * 门店品牌名称
	 */
	public String getBrandName() {
		return brandName;
	}

	/**
	 * 门店品牌名称
	 */
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	/**
	 * 专柜编码
	 */
	public String getCounterCode() {
		return counterCode;
	}

	/**
	 * 专柜编码
	 */
	public void setCounterCode(String counterCode) {
		this.counterCode = counterCode;
	}

	/**
	 * 专柜名
	 */
	public String getCounterName() {
		return counterName;
	}

	/**
	 * 专柜名
	 */
	public void setCounterName(String counterName) {
		this.counterName = counterName;
	}

	/**
	 * 门店编码
	 */
	public String getStoreCode() {
		return storeCode;
	}

	/**
	 * 门店编码
	 */
	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	/**
	 * 管理分类编码
	 */
	public String getManageCategory() {
		return manageCategory;
	}

	/**
	 * 管理分类编码
	 */
	public void setManageCategory(String manageCategory) {
		this.manageCategory = manageCategory;
	}

	/**
	 * 颜色编码
	 */
	public String getColorCode() {
		return colorCode;
	}

	/**
	 * 颜色编码
	 */
	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}

	/**
	 * 颜色名称
	 */
	public String getColorName() {
		return colorName;
	}

	/**
	 * 颜色名称
	 */
	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	/**
	 * 规格编码
	 */
	public String getStanCode() {
		return stanCode;
	}

	/**
	 * 规格编码
	 */
	public void setStanCode(String stanCode) {
		this.stanCode = stanCode;
	}

	/**
	 * 规格名称
	 */
	public String getStanName() {
		return stanName;
	}

	/**
	 * 规格名称
	 */
	public void setStanName(String stanName) {
		this.stanName = stanName;
	}

	/**
	 * 专柜商品可售状态
	 */
	public String getIsSale() {
		return isSale;
	}

	/**
	 * 专柜商品可售状态
	 */
	public void setIsSale(String isSale) {
		this.isSale = isSale;
	}

	/**
	 * 管理类型
	 */
	public String getManageType() {
		return manageType;
	}

	/**
	 * 管理类型
	 */
	public void setManageType(String manageType) {
		this.manageType = manageType;
	}

	/**
	 * 库存方式
	 */
	public String getStockType() {
		return stockType;
	}

	/**
	 * 库存方式
	 */
	public void setStockType(String stockType) {
		this.stockType = stockType;
	}

	/**
	 * 货号
	 */
	public String getArticleNum() {
		return articleNum;
	}

	/**
	 * 货号
	 */
	public void setArticleNum(String marticleNum) {
		this.articleNum = marticleNum;
	}

	/**
	 * 是否可打折( 0 是,1 否, 默认0)
	 */
	public String getIsDiscountable() {
		return isDiscountable;
	}

	/**
	 * 是否可打折( 0 是,1 否, 默认0)
	 */
	public void setIsDiscountable(String isDiscountable) {
		this.isDiscountable = isDiscountable;
	}

	/**
	 * 是否支持货到付款(0支持；1不支持)
	 */
	public String getIsCOD() {
		return isCOD;
	}

	/**
	 * 是否支持货到付款(0支持；1不支持)
	 */
	public void setIsCOD(String isCOD) {
		this.isCOD = isCOD;
	}

	/**
	 * 销售单位编码
	 */
	public String getUnitCode() {
		return unitCode;
	}

	/**
	 * 销售单位编码
	 */
	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	/**
	 * 销售单位名
	 */
	public String getUnitName() {
		return unitName;
	}

	/**
	 * 销售单位名
	 */
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	/**
	 * 零售价
	 */
	public String getSalesPrice() {
		return salesPrice;
	}

	/**
	 * 零售价
	 */
	public void setSalesPrice(String salesPrice) {
		this.salesPrice = salesPrice;
	}

	/**
	 * 市场价
	 */
	public String getMarketPrice() {
		return marketPrice;
	}

	/**
	 * 市场价
	 */
	public void setMarketPrice(String marketPrice) {
		this.marketPrice = marketPrice;
	}

	/**
	 * 折扣底线
	 */
	public String getMaxDiscountRate() {
		return maxDiscountRate;
	}

	/**
	 * 折扣底线
	 */
	public void setMaxDiscountRate(String maxDiscountRate) {
		this.maxDiscountRate = maxDiscountRate;
	}

	/**
	 * erp表中的商品状态0正常；1停售；2停货；3暂停使用；4已删除；5淘汰
	 */
	public String getProductStatus() {
		return productStatus;
	}

	/**
	 * erp表中的商品状态0正常；1停售；2停货；3暂停使用；4已删除；5淘汰
	 */
	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}

	/**
	 * 经营方式编码
	 */
	public String getOperateMode() {
		return operateMode;
	}

	/**
	 * 经营方式编码
	 */
	public void setOperateMode(String operateMode) {
		this.operateMode = operateMode;
	}

	/**
	 * 标准扣率
	 */
	public String getCommissionRate() {
		return commissionRate;
	}

	/**
	 * 标准扣率
	 */
	public void setCommissionRate(String commissionRate) {
		this.commissionRate = commissionRate;
	}

	/**
	 * 源系统销售单位
	 */
	public String getOriginSalesUnit() {
		return originSalesUnit;
	}

	/**
	 * 源系统销售单位
	 */
	public void setOriginSalesUnit(String originSalesUnit) {
		this.originSalesUnit = originSalesUnit;
	}

	/**
	 * 大码类型
	 */
	public String getErpSkuType() {
		return erpSkuType;
	}

	/**
	 * 大码类型
	 */
	public void setErpSkuType(String erpSkuType) {
		this.erpSkuType = erpSkuType;
	}

	/**
	 * 统计分类编码
	 */
	public String getStatCategory() {
		return statCategory;
	}

	/**
	 * 统计分类编码
	 */
	public void setStatCategory(String statCategory) {
		this.statCategory = statCategory;
	}

	/**
	 * 渠道sid
	 */
	public String getChannelSid() {
		return channelSid;
	}

	/**
	 * 渠道sid
	 */
	public void setChannelSid(String channelSid) {
		this.channelSid = channelSid;
	}

	/**
	 * 商品类型 0 普通商品（实物类），1 赠品 ，2 礼品，3 虚拟商品（充值卡，购物卡），4 服务类商品
	 * （礼品包装，购物接送服务，停车服务）（注：礼品不可卖，赠品可卖）
	 */
	public String getProductType() {
		return productType;
	}

	/**
	 * 商品类型 0 普通商品（实物类），1 赠品 ，2 礼品，3 虚拟商品（充值卡，购物卡），4 服务类商品
	 * （礼品包装，购物接送服务，停车服务）（注：礼品不可卖，赠品可卖）
	 */
	public void setProductType(String productType) {
		this.productType = productType;
	}

	/**
	 * 物流属性
	 */
	public String getTmsParam() {
		return tmsParam;
	}

	/**
	 * 物流属性
	 */
	public void setTmsParam(String tmsParam) {
		this.tmsParam = tmsParam;
	}

	/**
	 * 促销价
	 */
	public String getPromotionPrice() {
		return promotionPrice;
	}

	/**
	 * 促销价
	 */
	public void setPromotionPrice(String promotionPrice) {
		this.promotionPrice = promotionPrice;
	}

	/**
	 * 供应商内部自编条码
	 */
	public List<String> getSupplierIntBarCode() {
		return supplierIntBarCode;
	}

	/**
	 * 供应商内部自编条码
	 */
	public void setSupplierIntBarCode(List<String> supplierIntBarCode) {
		this.supplierIntBarCode = supplierIntBarCode;
	}

	// /**
	// * 倍率
	// */
	// public String getRate() {
	// return rate;
	// }
	//
	// /**
	// * 倍率
	// */
	// public void setRate(String rate) {
	// this.rate = rate;
	// }

	/**
	 * 当前页
	 */
	public Integer getCurrentPage() {
		return currentPage;
	}

	/**
	 * 当前页
	 */
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * 单页行数
	 */
	public Integer getPageSize() {
		return pageSize;
	}

	/**
	 * 单页行数
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	/**
	 * 工业分类
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * 工业分类
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * 供应商名
	 */
	public String getSupplierName() {
		return supplierName;
	}

	/**
	 * 供应商名
	 */
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	/**
	 * 季节
	 */
	public String getSeason() {
		return season;
	}

	/**
	 * 季节
	 */
	public void setSeason(String season) {
		this.season = season;
	}

	/**
	 * 变价记录
	 */
	public List<Map<String, Object>> getChangePrice() {
		return changePrice;
	}

	/**
	 * 变价记录
	 */
	public void setChangePrice(List<Map<String, Object>> changePrice) {
		this.changePrice = changePrice;
	}

	/**
	 * 属性列表
	 */
	public List<Map<String, Object>> getAttribute() {
		return attribute;
	}

	/**
	 * 属性列表
	 */
	public void setAttribute(List<Map<String, Object>> attribute) {
		this.attribute = attribute;
	}

	/**
	 * 上市年份
	 */
	public String getOnMarketDate() {
		return onMarketDate;
	}

	/**
	 * 上市年份
	 */
	public void setOnMarketDate(String onMarketDate) {
		this.onMarketDate = onMarketDate;
	}

	/**
	 * 业态方式
	 */
	public String getIndustryName() {
		return industryName;
	}

	/**
	 * 业态方式
	 */
	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	// /**
	// * 价格来源
	// */
	// public String getPriceChannel() {
	// return priceChannel;
	// }
	//
	// /**
	// * 价格来源
	// */
	// public void setPriceChannel(String priceChannel) {
	// this.priceChannel = priceChannel;
	// }

	/**
	 * sku上架状态
	 */
	public String getSkuSale() {
		return skuSale;
	}

	/**
	 * sku上架状态
	 */
	public void setSkuSale(String skuSale) {
		this.skuSale = skuSale;
	}

	/**
	 * spu上架状态
	 */
	public String getSpuSale() {
		return spuSale;
	}

	/**
	 * spu上架状态
	 */
	public void setSpuSale(String spuSale) {
		this.spuSale = spuSale;
	}

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	/**
	 * 是否负库存销售
	 */
	public String getNegativeStock() {
		return negativeStock;
	}

	/**
	 * 是否负库存销售
	 */
	public void setNegativeStock(String negativeStock) {
		this.negativeStock = negativeStock;
	}

	/**
	 * 商品简称
	 */
	public String getProductAbbr() {
		return productAbbr;
	}

	/**
	 * 商品简称
	 */
	public void setProductAbbr(String productAbbr) {
		this.productAbbr = productAbbr;
	}

	/**
	 * 集团品牌编码
	 */
	public String getBrandGroupCode() {
		return brandGroupCode;
	}

	/**
	 * 集团品牌编码
	 */
	public void setBrandGroupCode(String brandGroupCode) {
		this.brandGroupCode = brandGroupCode;
	}

	/**
	 * 集团品牌名称
	 */
	public String getBrandGroupName() {
		return brandGroupName;
	}

	/**
	 * 集团品牌名称
	 */
	public void setBrandGroupName(String brandGroupName) {
		this.brandGroupName = brandGroupName;
	}

	/**
	 * 库存方式(自库 虚库 不管库)
	 */
	public String getStockMode() {
		return stockMode;
	}

	/**
	 * 库存方式(自库 虚库 不管库)
	 */
	public void setStockMode(String stockMode) {
		this.stockMode = stockMode;
	}

	/**
	 * 进项税
	 */
	public String getInputTax() {
		return inputTax;
	}

	/**
	 * 进项税
	 */
	public void setInputTax(String inputTax) {
		this.inputTax = inputTax;
	}

	/**
	 * 销项税
	 */
	public String getOutputTax() {
		return outputTax;
	}

	/**
	 * 销项税
	 */
	public void setOutputTax(String outputTax) {
		this.outputTax = outputTax;
	}

	/**
	 * 销售税
	 */
	public String getSalesTax() {
		return salesTax;
	}

	/**
	 * 销售税
	 */
	public void setSalesTax(String salesTax) {
		this.salesTax = salesTax;
	}

	/**
	 * 扣率/进价
	 */
	public String getPurchasePrice() {
		return purchasePrice;
	}

	/**
	 * 扣率/进价
	 */
	public void setPurchasePrice(String purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	/**
	 * 扣率/含税进价
	 */
	public String getBuyingPrice() {
		return buyingPrice;
	}

	/**
	 * 扣率/含税进价
	 */
	public void setBuyingPrice(String buyingPrice) {
		this.buyingPrice = buyingPrice;
	}

	/**
	 * 加工方式
	 */
	public String getProcessType() {
		return processType;
	}

	/**
	 * 加工方式
	 */
	public void setProcessType(String processType) {
		this.processType = processType;
	}

	/**
	 * 产地
	 */
	public String getOriginLand() {
		return originLand;
	}

	/**
	 * 产地
	 */
	public void setOriginLand(String originLand) {
		this.originLand = originLand;
	}

	/**
	 * 原产地
	 */
	public String getOriginLand2() {
		return originLand2;
	}

	/**
	 * 原产地
	 */
	public void setOriginLand2(String originLand2) {
		this.originLand2 = originLand2;
	}

	/**
	 * 订货方式
	 */
	public String getOrderType() {
		return orderType;
	}

	/**
	 * 订货方式
	 */
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	/**
	 * 是否支持ERP促销
	 */
	public String getIsPromotion() {
		return isPromotion;
	}

	/**
	 * 是否支持ERP促销
	 */
	public void setIsPromotion(String isPromotion) {
		this.isPromotion = isPromotion;
	}

	/**
	 * 是否支持ERP变价
	 */
	public String getIsAdjustPrice() {
		return isAdjustPrice;
	}

	/**
	 * 是否支持ERP变价
	 */
	public void setIsAdjustPrice(String isAdjustPrice) {
		this.isAdjustPrice = isAdjustPrice;
	}

	/**
	 * 产品主属性
	 */
	public String getPrimaryAttr() {
		return primaryAttr;
	}

	/**
	 * 产品主属性
	 */
	public void setPrimaryAttr(String primaryAttr) {
		this.primaryAttr = primaryAttr;
	}

	/**
	 * 商品特性
	 */
	public String getFeatures() {
		return features;
	}

	/**
	 * 商品特性
	 */
	public void setFeatures(String features) {
		this.features = features;
	}

	/**
	 * 适合性别SID
	 */
	public String getSexSid() {
		return sexSid;
	}

	/**
	 * 适合性别SID
	 */
	public void setSexSid(String sexSid) {
		this.sexSid = sexSid;
	}

	/**
	 * 被赞次数
	 */
	public String getAwesome() {
		return awesome;
	}

	/**
	 * 被赞次数
	 */
	public void setAwesome(String awesome) {
		this.awesome = awesome;
	}

	/**
	 * spu标准品名(产品名)
	 */
	public String getSpuName() {
		return spuName;
	}

	/**
	 * spu标准品名(产品名)
	 */
	public void setSpuName(String spuName) {
		this.spuName = spuName;
	}

	/**
	 * sku标准品名(商品名)
	 */
	public String getSkuName() {
		return skuName;
	}

	/**
	 * sku标准品名(商品名)
	 */
	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	/**
	 * 可售库存
	 */
	public String getSaleStock() {
		return saleStock;
	}

	/**
	 * 可售库存
	 */
	public void setSaleStock(String saleStock) {
		this.saleStock = saleStock;
	}

	/**
	 * 残次品库存
	 */
	public String getEdefectiveStock() {
		return edefectiveStock;
	}

	/**
	 * 残次品库存
	 */
	public void setEdefectiveStock(String edefectiveStock) {
		this.edefectiveStock = edefectiveStock;
	}

	/**
	 * 退货库存
	 */
	public String getReturnStock() {
		return returnStock;
	}

	/**
	 * 退货库存
	 */
	public void setReturnStock(String returnStock) {
		this.returnStock = returnStock;
	}

	/**
	 * 锁定库存
	 */
	public String getLockedStock() {
		return lockedStock;
	}

	/**
	 * 锁定库存
	 */
	public void setLockedStock(String lockedStock) {
		this.lockedStock = lockedStock;
	}

	/**
	 * 价格类型
	 */
	public String getPriceType() {
		return priceType;
	}

	/**
	 * 价格类型
	 */
	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	/**
	 * 门店名称
	 */
	public String getStoreName() {
		return storeName;
	}

	/**
	 * 门店名称
	 */
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	/**
	 * 商品类别:01 自营单品,3:金额码,4:销售条码,05 金银首饰,06服务费商品,07 租赁商品,,08 联营单品,09 组包码
	 */
	public String getProductCategory() {
		return productCategory;
	}

	/**
	 * 商品类别:01 自营单品,3:金额码,4:销售条码,05 金银首饰,06服务费商品,07 租赁商品,,08 联营单品,09 组包码
	 */
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	/**
	 * 要约号
	 */
	public String getContractCode() {
		return contractCode;
	}

	/**
	 * 要约号
	 */
	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	/**
	 * 采购人员编号
	 */
	public String getProcurementUserCode() {
		return procurementUserCode;
	}

	/**
	 * 采购人员编号
	 */
	public void setProcurementUserCode(String procurementUserCode) {
		this.procurementUserCode = procurementUserCode;
	}

	/**
	 * 录入人员编号
	 */
	public String getInputUserCode() {
		return inputUserCode;
	}

	/**
	 * 录入人员编号
	 */
	public void setInputUserCode(String inputUserCode) {
		this.inputUserCode = inputUserCode;
	}

	/**
	 * 供应商SID
	 */
	public String getSupplierSid() {
		return supplierSid;
	}

	/**
	 * 供应商SID
	 */
	public void setSupplierSid(String supplierSid) {
		this.supplierSid = supplierSid;
	}

	/**
	 * 管理分类名称
	 */
	public String getGlCategoryName() {
		return glCategoryName;
	}

	/**
	 * 管理分类名称
	 */
	public void setGlCategoryName(String glCategoryName) {
		this.glCategoryName = glCategoryName;
	}

	/**
	 * 备注
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * 备注
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 * 单据号
	 */
	public String getBillsNo() {
		return billsNo;
	}

	/**
	 * 单据号
	 */
	public void setBillsNo(String billsNo) {
		this.billsNo = billsNo;
	}

	/**
	 * 统计分类名称
	 */
	public String getStatCategoryName() {
		return statCategoryName;
	}

	/**
	 * 统计分类名称
	 */
	public void setStatCategoryName(String statCategoryName) {
		this.statCategoryName = statCategoryName;
	}

	public String getCateZSCode() {
		return cateZSCode;
	}

	public void setCateZSCode(String cateZSCode) {
		this.cateZSCode = cateZSCode;
	}

	public String getCateZSName() {
		return cateZSName;
	}

	public void setCateZSName(String cateZSName) {
		this.cateZSName = cateZSName;
	}

	/**
	 * 工业分类名称
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * 工业分类名称
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	public String getColorSid() {
		return colorSid;
	}

	public void setColorSid(String colorSid) {
		this.colorSid = colorSid;
	}

	public String getProColor() {
		return proColor;
	}

	public void setProColor(String proColor) {
		this.proColor = proColor;
	}

	public String getIndustrySid() {
		return industrySid;
	}

	public void setIndustrySid(String industrySid) {
		this.industrySid = industrySid;
	}

	public Long getSpuSid() {
		return spuSid;
	}

	public void setSpuSid(Long spuSid) {
		this.spuSid = spuSid;
	}

	public Long getSkuSid() {
		return skuSid;
	}

	public void setSkuSid(Long skuSid) {
		this.skuSid = skuSid;
	}

	public String getBrandSid() {
		return brandSid;
	}

	public void setBrandSid(String brandSid) {
		this.brandSid = brandSid;
	}

	public String getStoreSid() {
		return storeSid;
	}

	public void setStoreSid(String storeSid) {
		this.storeSid = storeSid;
	}

	public String getManageCategoryCode() {
		return manageCategoryCode;
	}

	public void setManageCategoryCode(String manageCategoryCode) {
		this.manageCategoryCode = manageCategoryCode;
	}

	public List<PcmBarcode> getBarcodeList() {
		return barcodeList;
	}

	public void setBarcodeList(List<PcmBarcode> barcodeList) {
		this.barcodeList = barcodeList;
	}

	@Override
	public String toString() {
		return "ProductPageDto [cateZSCode=" + cateZSCode + ", cateZSName=" + cateZSName
				+ ", sidList=" + sidList + ", sid=" + sid + ", spuSid=" + spuSid + ", skuSid="
				+ skuSid + ", picUrl=" + picUrl + ", productCode=" + productCode + ", skuCode="
				+ skuCode + ", spuCode=" + spuCode + ", discountCode=" + discountCode
				+ ", erpProductCode=" + erpProductCode + ", productAbbr=" + productAbbr
				+ ", modelCode=" + modelCode + ", supplyProCode=" + supplyProCode
				+ ", productName=" + productName + ", spuName=" + spuName + ", skuName=" + skuName
				+ ", field2=" + field2 + ", supplierSid=" + supplierSid + ", supplierCode="
				+ supplierCode + ", supplierName=" + supplierName + ", brandSid=" + brandSid
				+ ", brandCode=" + brandCode + ", brandName=" + brandName + ", brandGroupCode="
				+ brandGroupCode + ", brandGroupName=" + brandGroupName + ", counterCode="
				+ counterCode + ", counterName=" + counterName + ", storeSid=" + storeSid
				+ ", storeCode=" + storeCode + ", storeName=" + storeName + ", manageCategory="
				+ manageCategory + ", manageCategoryCode=" + manageCategoryCode + ", colorCode="
				+ colorCode + ", colorName=" + colorName + ", colorSid=" + colorSid + ", proColor="
				+ proColor + ", stanCode=" + stanCode + ", stanName=" + stanName + ", isSale="
				+ isSale + ", skuSale=" + skuSale + ", spuSale=" + spuSale + ", manageType="
				+ manageType + ", stockType=" + stockType + ", negativeStock=" + negativeStock
				+ ", articleNum=" + articleNum + ", isDiscountable=" + isDiscountable + ", isCOD="
				+ isCOD + ", unitCode=" + unitCode + ", unitName=" + unitName + ", salesPrice="
				+ salesPrice + ", marketPrice=" + marketPrice + ", maxDiscountRate="
				+ maxDiscountRate + ", productStatus=" + productStatus + ", operateMode="
				+ operateMode + ", commissionRate=" + commissionRate + ", originSalesUnit="
				+ originSalesUnit + ", erpSkuType=" + erpSkuType + ", statCategory=" + statCategory
				+ ", statCategoryCode=" + statCategoryCode + ", statCategoryName="
				+ statCategoryName + ", channelSid=" + channelSid + ", channelName=" + channelName
				+ ", productType=" + productType + ", tmsParam=" + tmsParam + ", promotionPrice="
				+ promotionPrice + ", supplierIntBarCode=" + supplierIntBarCode + ", barcodeList="
				+ barcodeList + ", category=" + category + ", categoryName=" + categoryName
				+ ", season=" + season + ", changePrice=" + changePrice + ", attribute="
				+ attribute + ", onMarketDate=" + onMarketDate + ", industrySid=" + industrySid
				+ ", industryName=" + industryName + ", inputTax=" + inputTax + ", outputTax="
				+ outputTax + ", salesTax=" + salesTax + ", purchasePrice=" + purchasePrice
				+ ", buyingPrice=" + buyingPrice + ", stockMode=" + stockMode + ", processType="
				+ processType + ", originLand=" + originLand + ", originLand2=" + originLand2
				+ ", orderType=" + orderType + ", isPromotion=" + isPromotion + ", isAdjustPrice="
				+ isAdjustPrice + ", primaryAttr=" + primaryAttr + ", features=" + features
				+ ", sexSid=" + sexSid + ", awesome=" + awesome + ", saleStock=" + saleStock
				+ ", edefectiveStock=" + edefectiveStock + ", returnStock=" + returnStock
				+ ", lockedStock=" + lockedStock + ", priceType=" + priceType
				+ ", productCategory=" + productCategory + ", contractCode=" + contractCode
				+ ", procurementUserCode=" + procurementUserCode + ", inputUserCode="
				+ inputUserCode + ", glCategoryName=" + glCategoryName + ", billsNo=" + billsNo
				+ ", currentPage=" + currentPage + ", pageSize=" + pageSize + ", notes=" + notes
				+ ", start=" + start + ", limit=" + limit + ", field4=" + field4 + ", isGift="
				+ isGift + ", isCard=" + isCard + ", baseUnitCode=" + baseUnitCode
				+ ", originCountry=" + originCountry + ", isOriginPackage=" + isOriginPackage
				+ ", xxhcFlag=" + xxhcFlag + ", isPacking=" + isPacking + ", counterSid="
				+ counterSid + "]";
	}

}
