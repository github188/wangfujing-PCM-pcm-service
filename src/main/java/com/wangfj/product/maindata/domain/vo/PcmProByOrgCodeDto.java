package com.wangfj.product.maindata.domain.vo;

import java.util.List;

public class PcmProByOrgCodeDto {
	/**
	 * 专柜商品编码
	 */
	private String itemCode;
	/**
	 * 所属SKU编码
	 */
	private String skuCode;
	/**
	 * 专柜编码
	 */
	private String shoppeCode;
	/**
	 * 门店编码
	 */
	private String storeCode;
	/**
	 * 供应商编码
	 */
	private String supplierCode;
	/**
	 * 供应商名称
	 */
	private String supplierName;
	/**
	 * 折扣底线（0 - 1.0）
	 */
	private String discountLimit;
	/**
	 * 季节属性
	 */
	private String season;
	/**
	 * 上市日期
	 */
	private String onMarketDate;
	/**
	 * 门店品牌编码
	 */
	private String storeBrandCode;
	/**
	 * 扣率码
	 */
	private String rateCode;
	/**
	 * 专柜商品名称
	 */
	private String shoppeItemName;
	/**
	 * 商品可售状态
	 */
	private String saleStatus;
	/**
	 * 专柜商品类型
	 */
	private String shoppeItemType;
	/**
	 * ERP商品编码，大码
	 */
	private String erpProductCode;
	/**
	 * 大码类型
	 */
	private String erpSkuType;
	/**
	 * 管理类型
	 */
	private String manageType;
	/**
	 * 条码
	 */
	private List<String> barcode;
	/**
	 * 货号
	 */
	private String article_num;

	private String manageCategoryType; // 管理分类
	private String originPrice; // 原价
	private String productCategory;// 1, 商品类别:01 自营单品,3:金额码,4:销售条码,05 金银首饰,06
									// 服务费商品,07 租赁商品,,08 联营单品,09 组包码
	private String unit; // 商品单位

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public String getShoppeCode() {
		return shoppeCode;
	}

	public void setShoppeCode(String shoppeCode) {
		this.shoppeCode = shoppeCode;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getDiscountLimit() {
		return discountLimit;
	}

	public void setDiscountLimit(String discountLimit) {
		this.discountLimit = discountLimit;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public String getOnMarketDate() {
		return onMarketDate;
	}

	public void setOnMarketDate(String onMarketDate) {
		this.onMarketDate = onMarketDate;
	}

	public String getStoreBrandCode() {
		return storeBrandCode;
	}

	public void setStoreBrandCode(String storeBrandCode) {
		this.storeBrandCode = storeBrandCode;
	}

	public String getRateCode() {
		return rateCode;
	}

	public void setRateCode(String rateCode) {
		this.rateCode = rateCode;
	}

	public String getShoppeItemName() {
		return shoppeItemName;
	}

	public void setShoppeItemName(String shoppeItemName) {
		this.shoppeItemName = shoppeItemName;
	}

	public String getSaleStatus() {
		return saleStatus;
	}

	public void setSaleStatus(String saleStatus) {
		this.saleStatus = saleStatus;
	}

	public String getShoppeItemType() {
		return shoppeItemType;
	}

	public void setShoppeItemType(String shoppeItemType) {
		this.shoppeItemType = shoppeItemType;
	}

	public String getErpProductCode() {
		return erpProductCode;
	}

	public void setErpProductCode(String erpProductCode) {
		this.erpProductCode = erpProductCode;
	}

	public String getErpSkuType() {
		return erpSkuType;
	}

	public void setErpSkuType(String erpSkuType) {
		this.erpSkuType = erpSkuType;
	}

	public String getManageType() {
		return manageType;
	}

	public void setManageType(String manageType) {
		this.manageType = manageType;
	}

	public List<String> getBarcode() {
		return barcode;
	}

	public void setBarcode(List<String> barcode) {
		this.barcode = barcode;
	}

	public String getArticle_num() {
		return article_num;
	}

	public void setArticle_num(String article_num) {
		this.article_num = article_num;
	}

	public String getManageCategoryType() {
		return manageCategoryType;
	}

	public void setManageCategoryType(String manageCategoryType) {
		this.manageCategoryType = manageCategoryType;
	}

	public String getOriginPrice() {
		return originPrice;
	}

	public void setOriginPrice(String originPrice) {
		this.originPrice = originPrice;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Override
	public String toString() {
		return "PcmProByOrgCodeDto [itemCode=" + itemCode + ", skuCode=" + skuCode
				+ ", shoppeCode=" + shoppeCode + ", supplierCode=" + supplierCode
				+ ", supplierName=" + supplierName + ", discountLimit=" + discountLimit
				+ ", season=" + season + ", onMarketDate=" + onMarketDate + ", storeBrandCode="
				+ storeBrandCode + ", rateCode=" + rateCode + ", shoppeItemName=" + shoppeItemName
				+ ", saleStatus=" + saleStatus + ", shoppeItemType=" + shoppeItemType
				+ ", erpProductCode=" + erpProductCode + ", erpSkuType=" + erpSkuType
				+ ", manageType=" + manageType + ", barcode=" + barcode + ", article_num="
				+ article_num + ", manageCategoryType=" + manageCategoryType + ", originPrice="
				+ originPrice + ", productCategory=" + productCategory + ", unit=" + unit + "]";
	}

}
