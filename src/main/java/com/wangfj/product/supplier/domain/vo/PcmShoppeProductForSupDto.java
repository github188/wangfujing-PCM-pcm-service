/**
 * @Probject Name: pcm-service-outer
 * @Path: com.wangfj.product.supplier.domain.voPcmShoppeProductForSup.java
 * @Create By wangc
 * @Create In 2016-3-9 下午5:21:08
 * TODO
 */
package com.wangfj.product.supplier.domain.vo;

import java.util.List;
import java.util.Map;

/**
 * 供应商查询专柜商品详情封装类
 * @Class Name PcmShoppeProductForSup
 * @Author wangc
 * @Create In 2016-3-9
 */
public class PcmShoppeProductForSupDto{

	private String sid;
	private String shortDesc;//短描述
	private String prodCategoryCode;//工业分类编码
	private String prodCategoryName;//工业分类名称
	private String storeName;//门店名称
	private String storeCode;//门店编码
	private String counterName;//专柜名称
	private String counterCode;//专柜编码
	private String articleNum;//供应商货号
	private List<Map<String,Object>> standardBarCodes;//供应商商品条码
	private String marketPrice;//市场价
	private String salePrice;//零售价
	private String purchasePrice;//供货价（采购价）
	private String finalClassiFicationCode;//统计分类
	private String finalClassiFicationName;//统计分类名称
	private String inputTax;//进项税
	private String ouputTax;//销项税
	private String salesTax;//消费税
	private String supplierCode;//供应商编码
	private String originCountry;//原产国编码
	private String originLand;//产地编码
	private String colorCode;//色码
	private String colorName;//色码描述
	private String stanCode;//尺寸码（规格）
	private String stanName;//规格描述
	private String baseUnitCode;//基本计量单位
	private String isCod;//可COD
	private String tmsType;//商品组（物流属性）
	private String TmsTypeName;//包装商品名称
	private String isOriginPackage;//是否原厂包装
	private String yearToMarket;//上市日期
	private String crowdUser;//适用性别
	private String crowdUserName;//适用性别对应的名称
	private String procurementPersonnelNumber;//采购组
	private String supProCode;//供应商商品编码
	private String seasonCode;//商品季节编码
	private String seasonName;//季节名称
	private String proColor;//色系编码
	private String proColorName;//色系名称
	private String offerNumber;//要约号
	private String productName;//专柜商品名称
	private String productCode;//专柜商品编码
	private String productNameAlias;//专柜商品简称
	//private String barCodeType;//条码类型
	private String operateMode;//经营方式编码
	private String operateModeName;//经营方式名称
	private String discount;//扣率
	private String businessType;//业态编码
	private String businessTypeName;//业态名称
	private String manageCateGory;//管理分类
	private String manageCateGoryName;//管理分类名称
	private String discountCode;//扣率码
	private String productType;//商品类型编码
	private String ProductTypeName;//商品类型名称
	
	
	
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getShortDesc() {
		return shortDesc;
	}
	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}
	public String getProdCategoryCode() {
		return prodCategoryCode;
	}
	public void setProdCategoryCode(String prodCategoryCode) {
		this.prodCategoryCode = prodCategoryCode;
	}
	public String getProdCategoryName() {
		return prodCategoryName;
	}
	public void setProdCategoryName(String prodCategoryName) {
		this.prodCategoryName = prodCategoryName;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getStoreCode() {
		return storeCode;
	}
	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}
	public String getCounterName() {
		return counterName;
	}
	public void setCounterName(String counterName) {
		this.counterName = counterName;
	}
	public String getCounterCode() {
		return counterCode;
	}
	public void setCounterCode(String counterCode) {
		this.counterCode = counterCode;
	}
	public String getArticleNum() {
		return articleNum;
	}
	public void setArticleNum(String articleNum) {
		this.articleNum = articleNum;
	}
	public List<Map<String, Object>> getStandardBarCodes() {
		return standardBarCodes;
	}
	public void setStandardBarCodes(List<Map<String, Object>> standardBarCodes) {
		this.standardBarCodes = standardBarCodes;
	}
	public String getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(String marketPrice) {
		this.marketPrice = marketPrice;
	}
	public String getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(String salePrice) {
		this.salePrice = salePrice;
	}
	public String getPurchasePrice() {
		return purchasePrice;
	}
	public void setPurchasePrice(String purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	public String getFinalClassiFicationCode() {
		return finalClassiFicationCode;
	}
	public void setFinalClassiFicationCode(String finalClassiFicationCode) {
		this.finalClassiFicationCode = finalClassiFicationCode;
	}
	public String getFinalClassiFicationName() {
		return finalClassiFicationName;
	}
	public void setFinalClassiFicationName(String finalClassiFicationName) {
		this.finalClassiFicationName = finalClassiFicationName;
	}
	public String getInputTax() {
		return inputTax;
	}
	public void setInputTax(String inputTax) {
		this.inputTax = inputTax;
	}
	public String getOuputTax() {
		return ouputTax;
	}
	public void setOuputTax(String ouputTax) {
		this.ouputTax = ouputTax;
	}
	public String getSalesTax() {
		return salesTax;
	}
	public void setSalesTax(String salesTax) {
		this.salesTax = salesTax;
	}
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	public String getOriginCountry() {
		return originCountry;
	}
	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}
	public String getOriginLand() {
		return originLand;
	}
	public void setOriginLand(String originLand) {
		this.originLand = originLand;
	}
	public String getColorCode() {
		return colorCode;
	}
	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}
	public String getColorName() {
		return colorName;
	}
	public void setColorName(String colorName) {
		this.colorName = colorName;
	}
	public String getStanCode() {
		return stanCode;
	}
	public void setStanCode(String stanCode) {
		this.stanCode = stanCode;
	}
	public String getStanName() {
		return stanName;
	}
	public void setStanName(String stanName) {
		this.stanName = stanName;
	}
	public String getBaseUnitCode() {
		return baseUnitCode;
	}
	public void setBaseUnitCode(String baseUnitCode) {
		this.baseUnitCode = baseUnitCode;
	}
	public String getIsCod() {
		return isCod;
	}
	public void setIsCod(String isCod) {
		this.isCod = isCod;
	}
	public String getTmsType() {
		return tmsType;
	}
	public void setTmsType(String tmsType) {
		this.tmsType = tmsType;
	}
	public String getTmsTypeName() {
		return TmsTypeName;
	}
	public void setTmsTypeName(String tmsTypeName) {
		TmsTypeName = tmsTypeName;
	}
	public String getIsOriginPackage() {
		return isOriginPackage;
	}
	public void setIsOriginPackage(String isOriginPackage) {
		this.isOriginPackage = isOriginPackage;
	}
	public String getYearToMarket() {
		return yearToMarket;
	}
	public void setYearToMarket(String yearToMarket) {
		this.yearToMarket = yearToMarket;
	}
	public String getCrowdUser() {
		return crowdUser;
	}
	public void setCrowdUser(String crowdUser) {
		this.crowdUser = crowdUser;
	}
	public String getCrowdUserName() {
		return crowdUserName;
	}
	public void setCrowdUserName(String crowdUserName) {
		this.crowdUserName = crowdUserName;
	}
	public String getProcurementPersonnelNumber() {
		return procurementPersonnelNumber;
	}
	public void setProcurementPersonnelNumber(String procurementPersonnelNumber) {
		this.procurementPersonnelNumber = procurementPersonnelNumber;
	}
	public String getSupProCode() {
		return supProCode;
	}
	public void setSupProCode(String supProCode) {
		this.supProCode = supProCode;
	}
	public String getSeasonCode() {
		return seasonCode;
	}
	public void setSeasonCode(String seasonCode) {
		this.seasonCode = seasonCode;
	}
	public String getSeasonName() {
		return seasonName;
	}
	public void setSeasonName(String seasonName) {
		this.seasonName = seasonName;
	}
	public String getProColor() {
		return proColor;
	}
	public void setProColor(String proColor) {
		this.proColor = proColor;
	}
	public String getProColorName() {
		return proColorName;
	}
	public void setProColorName(String proColorName) {
		this.proColorName = proColorName;
	}
	public String getOfferNumber() {
		return offerNumber;
	}
	public void setOfferNumber(String offerNumber) {
		this.offerNumber = offerNumber;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductNameAlias() {
		return productNameAlias;
	}
	public void setProductNameAlias(String productNameAlias) {
		this.productNameAlias = productNameAlias;
	}
	public String getOperateMode() {
		return operateMode;
	}
	public void setOperateMode(String operateMode) {
		this.operateMode = operateMode;
	}
	public String getOperateModeName() {
		return operateModeName;
	}
	public void setOperateModeName(String operateModeName) {
		this.operateModeName = operateModeName;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public String getBusinessTypeName() {
		return businessTypeName;
	}
	public void setBusinessTypeName(String businessTypeName) {
		this.businessTypeName = businessTypeName;
	}
	public String getManageCateGory() {
		return manageCateGory;
	}
	public void setManageCateGory(String manageCateGory) {
		this.manageCateGory = manageCateGory;
	}
	public String getManageCateGoryName() {
		return manageCateGoryName;
	}
	public void setManageCateGoryName(String manageCateGoryName) {
		this.manageCateGoryName = manageCateGoryName;
	}
	public String getDiscountCode() {
		return discountCode;
	}
	public void setDiscountCode(String discountCode) {
		this.discountCode = discountCode;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getProductTypeName() {
		return ProductTypeName;
	}
	public void setProductTypeName(String productTypeName) {
		ProductTypeName = productTypeName;
	}
	
	
	
}
