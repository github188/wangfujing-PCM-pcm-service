package com.wangfj.product.maindata.domain.vo;

public class PushShoppeProFromPcmDto {
	private String code;// 专柜商品编码
	/**
	 * 商品表SKU 编码
	 */
	private String productCode;
	/**
	 * 产品编SPU 编码
	 */
	private String baseProductCode;
	/**
	 * 专柜商品名称
	 */
	private String productName;
	/**
	 * 供应商编码
	 */
	private String supplierCode;
	/**
	 * 专柜编码
	 */
	private String counterCode;
	/**
	 * 大码
	 */
	private String erpProductCode;
	/**
	 * 门店编码
	 */
	private String storeCode;
	/**
	 * 管理分类编码
	 */
	private String manageCategory;
	/**
	 * 经营方式编码
	 */
	private String operateMode;
	/**
	 * 是否可打折( 0 是,1 否, 默认0)
	 */
	private String isDiscountable;
	/**
	 * 折扣底线
	 */
	private String maxDiscountRate;
	/**
	 * 商品状态
	 */
	private String productStatus;
	/**
	 * 尺码
	 */
	private String sizeCode;
	/**
	 * 色码
	 */
	private String styleCode;
	/**
	 * 销售单位编码
	 */
	private String unitCode;
	/**
	 * 市场价
	 */
	private String marketPrice;
	/**
	 * 零售价
	 */
	private String salesPrice;
	/**
	 * 供应商内部自编条码
	 */
	private String supplierIntBarCode;
	/**
	 * 赠品范围
	 */
	private String isGift;
	/**
	 * 标准扣率
	 */
	private String commissionRate;
	/**
	 * 源系统销售单位
	 */
	private String originSalesUnit;
	/**
	 * 是否支持货到付款(0支持；1不支持)
	 */
	private String isCOD;
	/**
	 * 库存方式
	 */
	private String stockTypeLib;
	/**
	 * 大码类型
	 */
	private String erpSkuType;
	/**
	 * 款号
	 */
	private String modelNumber;
	/**
	 * 货号
	 */
	private String materialNum;
	/**
	 * 简称
	 */
	private String productAbbr;
	/**
	 * 倍率
	 */
	private String rate;
	/**
	 * 管理类型
	 */
	private String manageType;
	/**
	 * 先销后采
	 */
	private String isSelllPurchase;
	/**
	 * 操作标记 A 添加 U修改 D删除
	 */
	private String actionCode;
	private String actionDate;
	private String actionPersin;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getBaseProductCode() {
		return baseProductCode;
	}

	public void setBaseProductCode(String baseProductCode) {
		this.baseProductCode = baseProductCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getCounterCode() {
		return counterCode;
	}

	public void setCounterCode(String counterCode) {
		this.counterCode = counterCode;
	}

	public String getErpProductCode() {
		return erpProductCode;
	}

	public void setErpProductCode(String erpProductCode) {
		this.erpProductCode = erpProductCode;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getManageCategory() {
		return manageCategory;
	}

	public void setManageCategory(String manageCategory) {
		this.manageCategory = manageCategory;
	}

	public String getOperateMode() {
		return operateMode;
	}

	public void setOperateMode(String operateMode) {
		this.operateMode = operateMode;
	}

	public String getIsDiscountable() {
		return isDiscountable;
	}

	public void setIsDiscountable(String isDiscountable) {
		this.isDiscountable = isDiscountable;
	}

	public String getMaxDiscountRate() {
		return maxDiscountRate;
	}

	public void setMaxDiscountRate(String maxDiscountRate) {
		this.maxDiscountRate = maxDiscountRate;
	}

	public String getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}

	public String getSizeCode() {
		return sizeCode;
	}

	public void setSizeCode(String sizeCode) {
		this.sizeCode = sizeCode;
	}

	public String getStyleCode() {
		return styleCode;
	}

	public void setStyleCode(String styleCode) {
		this.styleCode = styleCode;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public String getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(String marketPrice) {
		this.marketPrice = marketPrice;
	}

	public String getSalesPrice() {
		return salesPrice;
	}

	public void setSalesPrice(String salesPrice) {
		this.salesPrice = salesPrice;
	}

	public String getSupplierIntBarCode() {
		return supplierIntBarCode;
	}

	public void setSupplierIntBarCode(String supplierIntBarCode) {
		this.supplierIntBarCode = supplierIntBarCode;
	}

	public String getIsGift() {
		return isGift;
	}

	public void setIsGift(String isGift) {
		this.isGift = isGift;
	}

	public String getCommissionRate() {
		return commissionRate;
	}

	public void setCommissionRate(String commissionRate) {
		this.commissionRate = commissionRate;
	}

	public String getOriginSalesUnit() {
		return originSalesUnit;
	}

	public void setOriginSalesUnit(String originSalesUnit) {
		this.originSalesUnit = originSalesUnit;
	}

	public String getIsCOD() {
		return isCOD;
	}

	public void setIsCOD(String isCOD) {
		this.isCOD = isCOD;
	}

	public String getStockTypeLib() {
		return stockTypeLib;
	}

	public void setStockTypeLib(String stockTypeLib) {
		this.stockTypeLib = stockTypeLib;
	}

	public String getErpSkuType() {
		return erpSkuType;
	}

	public void setErpSkuType(String erpSkuType) {
		this.erpSkuType = erpSkuType;
	}

	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}

	public String getMaterialNum() {
		return materialNum;
	}

	public void setMaterialNum(String materialNum) {
		this.materialNum = materialNum;
	}

	public String getProductAbbr() {
		return productAbbr;
	}

	public void setProductAbbr(String productAbbr) {
		this.productAbbr = productAbbr;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getManageType() {
		return manageType;
	}

	public void setManageType(String manageType) {
		this.manageType = manageType;
	}

	public String getIsSelllPurchase() {
		return isSelllPurchase;
	}

	public void setIsSelllPurchase(String isSelllPurchase) {
		this.isSelllPurchase = isSelllPurchase;
	}

	public String getActionCode() {
		return actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	public String getActionDate() {
		return actionDate;
	}

	public void setActionDate(String actionDate) {
		this.actionDate = actionDate;
	}

	public String getActionPersin() {
		return actionPersin;
	}

	public void setActionPersin(String actionPersin) {
		this.actionPersin = actionPersin;
	}

	@Override
	public String toString() {
		return "PushShoppeProFromPcmDto [code=" + code + ", productCode=" + productCode
				+ ", baseProductCode=" + baseProductCode + ", productName=" + productName
				+ ", supplierCode=" + supplierCode + ", counterCode=" + counterCode
				+ ", erpProductCode=" + erpProductCode + ", storeCode=" + storeCode
				+ ", manageCategory=" + manageCategory + ", operateMode=" + operateMode
				+ ", isDiscountable=" + isDiscountable + ", maxDiscountRate=" + maxDiscountRate
				+ ", productStatus=" + productStatus + ", sizeCode=" + sizeCode + ", styleCode="
				+ styleCode + ", unitCode=" + unitCode + ", marketPrice=" + marketPrice
				+ ", salesPrice=" + salesPrice + ", supplierIntBarCode=" + supplierIntBarCode
				+ ", isGift=" + isGift + ", commissionRate=" + commissionRate
				+ ", originSalesUnit=" + originSalesUnit + ", isCOD=" + isCOD + ", stockTypeLib="
				+ stockTypeLib + ", erpSkuType=" + erpSkuType + ", modelNumber=" + modelNumber
				+ ", materialNum=" + materialNum + ", productAbbr=" + productAbbr + ", rate="
				+ rate + ", manageType=" + manageType + ", isSelllPurchase=" + isSelllPurchase
				+ ", actionCode=" + actionCode + ", actionDate=" + actionDate + ", actionPersin="
				+ actionPersin + "]";
	}

}
