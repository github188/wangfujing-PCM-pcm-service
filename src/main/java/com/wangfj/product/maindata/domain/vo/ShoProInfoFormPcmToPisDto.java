/**
 * @Probject Name: pcm-service
 * @Path: com.wangfj.product.maindata.domain.voShoProInfoFormPcmToPisDto.java
 * @Create By wangc
 * @Create In 2016-3-29 上午11:04:12
 * TODO
 */
package com.wangfj.product.maindata.domain.vo;

import java.util.List;

import com.wangfj.product.maindata.domain.entity.PcmBarcode;

/**
 * @Class Name ShoProInfoFormPcmToPisDto
 * @Author  wangc
 * @Create In 2016-3-29
 */
public class ShoProInfoFormPcmToPisDto {

	
	private Long sid;
	private String productCode;// 专柜商品编码
	private String skuCode;// 商品表SKU 编码
	private String spuCode;// 产品表SPU 编码
	private String erpProductCode;// 大码
	private String description;// 专柜商品简称
	private String modelCode;// 款号
	private String productName;// 专柜商品名称
	private String materialNum;//货号
	private String supplierCode;// 供应商编码
	private String supplierName;// 供应商名称
	private String brandCode;// 集团品牌编码
	private String brandName;// 集团品牌名称
	private String counterCode;// 专柜编码
	private String storeSid;// 门店编码
	private String storeCode;// 门店编码
	private String storeName;// 门店名称
	private String colorCode;// 色码
	private String colorName;// 颜色名称
	private String sizeName;// 规格名称
	private String manageType;// 管理类型
	private String originSalesUnit;// 销售单位
	private String price;// 零售价
	private String marketPrice;// 市场价
	private String operateMode;// 经营方式编码
	private String productType;// 商品类型
	private List<String> supplierIntBarCode;// 供应商内部自编条码
	private List<PcmBarcode> barcodeList;// 供应商条码List
	private String productCategoryCode;// 工业分类编码
	private String categoryName;// 工业分类名称
	private String industrySid;// 业态方式
	private String industryName;// 业态方式名称
	private Integer currentPage;// 当前页
	private Integer pageSize;// 单页行数
	private String notes;// 备注
	private String sizeCode;//尺码
	private String erpSkuType;//大码类型
	private Integer start;
	private Integer limit;
	public Long getSid() {
		return sid;
	}
	public void setSid(Long sid) {
		this.sid = sid;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getSkuCode() {
		return skuCode;
	}
	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}
	public String getSpuCode() {
		return spuCode;
	}
	public void setSpuCode(String spuCode) {
		this.spuCode = spuCode;
	}
	public String getErpProductCode() {
		return erpProductCode;
	}
	public void setErpProductCode(String erpProductCode) {
		this.erpProductCode = erpProductCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getModelCode() {
		return modelCode;
	}
	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getMaterialNum() {
		return materialNum;
	}
	public void setMaterialNum(String materialNum) {
		this.materialNum = materialNum;
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
	public String getBrandCode() {
		return brandCode;
	}
	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getCounterCode() {
		return counterCode;
	}
	public void setCounterCode(String counterCode) {
		this.counterCode = counterCode;
	}
	public String getStoreSid() {
		return storeSid;
	}
	public void setStoreSid(String storeSid) {
		this.storeSid = storeSid;
	}
	public String getStoreCode() {
		return storeCode;
	}
	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
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
	public String getSizeName() {
		return sizeName;
	}
	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}
	public String getManageType() {
		return manageType;
	}
	public void setManageType(String manageType) {
		this.manageType = manageType;
	}
	public String getOriginSalesUnit() {
		return originSalesUnit;
	}
	public void setOriginSalesUnit(String originSalesUnit) {
		this.originSalesUnit = originSalesUnit;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(String marketPrice) {
		this.marketPrice = marketPrice;
	}
	public String getOperateMode() {
		return operateMode;
	}
	public void setOperateMode(String operateMode) {
		this.operateMode = operateMode;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public List<String> getSupplierIntBarCode() {
		return supplierIntBarCode;
	}
	public void setSupplierIntBarCode(List<String> supplierIntBarCode) {
		this.supplierIntBarCode = supplierIntBarCode;
	}
	public List<PcmBarcode> getBarcodeList() {
		return barcodeList;
	}
	public void setBarcodeList(List<PcmBarcode> barcodeList) {
		this.barcodeList = barcodeList;
	}
	public String getProductCategoryCode() {
		return productCategoryCode;
	}
	public void setProductCategoryCode(String productCategoryCode) {
		this.productCategoryCode = productCategoryCode;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getIndustrySid() {
		return industrySid;
	}
	public void setIndustrySid(String industrySid) {
		this.industrySid = industrySid;
	}
	public String getIndustryName() {
		return industryName;
	}
	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getSizeCode() {
		return sizeCode;
	}
	public void setSizeCode(String sizeCode) {
		this.sizeCode = sizeCode;
	}
	public String getErpSkuType() {
		return erpSkuType;
	}
	public void setErpSkuType(String erpSkuType) {
		this.erpSkuType = erpSkuType;
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
	
	
	
	
}
