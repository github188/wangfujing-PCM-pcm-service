package com.wangfj.product.maindata.domain.vo;

import java.util.List;

public class PcmSearchShoppeProDto {// 门店编码
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

	public String getArticle_num() {
		return article_num;
	}

	public String getDiscountLimit() {
		return discountLimit;
	}

	public String getErpProductCode() {
		return erpProductCode;
	}

	public String getErpSkuType() {
		return erpSkuType;
	}

	public String getItemCode() {
		return itemCode;
	}

	public String getManageType() {
		return manageType;
	}

	public String getOnMarketDate() {
		return onMarketDate;
	}

	public String getRateCode() {
		return rateCode;
	}

	public String getSaleStatus() {
		return saleStatus;
	}

	public String getSeason() {
		return season;
	}

	public String getStoreBrandCode() {
		return storeBrandCode;
	}

	public String getShoppeCode() {
		return shoppeCode;
	}

	public String getShoppeItemName() {
		return shoppeItemName;
	}

	public String getShoppeItemType() {
		return shoppeItemType;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setArticle_num(String article_num) {
		this.article_num = article_num;
	}

	public List<String> getBarcode() {
		return barcode;
	}

	public void setBarcode(List<String> barcode) {
		this.barcode = barcode;
	}

	public void setDiscountLimit(String discountLimit) {
		this.discountLimit = discountLimit;
	}

	public void setErpProductCode(String erpProductCode) {
		this.erpProductCode = erpProductCode;
	}

	public void setErpSkuType(String erpSkuType) {
		this.erpSkuType = erpSkuType;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public void setManageType(String manageType) {
		this.manageType = manageType;
	}

	public void setOnMarketDate(String onMarketDate) {
		this.onMarketDate = onMarketDate;
	}

	public void setRateCode(String rateCode) {
		this.rateCode = rateCode;
	}

	public void setSaleStatus(String saleStatus) {
		this.saleStatus = saleStatus;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public void setStoreBrandCode(String storeBrandCode) {
		this.storeBrandCode = storeBrandCode;
	}

	public void setShoppeCode(String shoppeCode) {
		this.shoppeCode = shoppeCode;
	}

	public void setShoppeItemName(String shoppeItemName) {
		this.shoppeItemName = shoppeItemName;
	}

	public void setShoppeItemType(String shoppeItemType) {
		this.shoppeItemType = shoppeItemType;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	@Override
	public String toString() {
		return "PcmSearchShoppeProDto [itemCode=" + itemCode + ", skuCode=" + skuCode
				+ ", shoppeCode=" + shoppeCode + ", storeCode=" + storeCode + ", supplierCode="
				+ supplierCode + ", supplierName=" + supplierName + ", discountLimit="
				+ discountLimit + ", season=" + season + ", onMarketDate=" + onMarketDate
				+ ", storeBrandCode=" + storeBrandCode + ", rateCode=" + rateCode
				+ ", shoppeItemName=" + shoppeItemName + ", saleStatus=" + saleStatus
				+ ", shoppeItemType=" + shoppeItemType + ", erpProductCode=" + erpProductCode
				+ ", erpSkuType=" + erpSkuType + ", manageType=" + manageType + ", barcode="
				+ barcode + ", article_num=" + article_num + "]";
	}

}
