/**
 * @Probject Name: pcm-service-outer
 * @Path: com.wangfj.product.supplier.domain.voPcmShoProductSupDataDto.java
 * @Create By wangc
 * @Create In 2016-3-3 下午3:53:26
 */
package com.wangfj.product.supplier.domain.vo;

/**
 * 供应商查询商品列表--查询结果封装
 * @Class Name PcmShoProductSupDataDto
 * @Author wangc
 * @Create In 2016-3-3
 */
public class PcmShoProductSupDataDto {

	private String supplyCode;//供应商编码 
	private String productCode;//专柜商品编码
	private String counterName;//专柜名称
	private String productName;//专柜商品名称
	private String storeName;//门店名称
	private String storeBrandName;//门店品牌名称
	private String manageCategroyCode;//管理分类
	private String manageCategoryName;//管理分类名称
	private String productType;//商品类型
	private String operateMode;//经营方式
	private String businessType;//业态
	private String colorSid;//色系
	private String isSell;//上架状态
	
	
	public String getManageCategoryName() {
		return manageCategoryName;
	}
	public void setManageCategoryName(String manageCategoryName) {
		this.manageCategoryName = manageCategoryName;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getStoreBrandName() {
		return storeBrandName;
	}
	public void setStoreBrandName(String storeBrandName) {
		this.storeBrandName = storeBrandName;
	}
	public String getManageCategroyCode() {
		return manageCategroyCode;
	}
	public void setManageCategroyCode(String manageCategroyCode) {
		this.manageCategroyCode = manageCategroyCode;
	}
	public String getSupplyCode() {
		return supplyCode;
	}
	public void setSupplyCode(String supplyCode) {
		this.supplyCode = supplyCode;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getCounterName() {
		return counterName;
	}
	public void setCounterName(String counterName) {
		this.counterName = counterName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getOperateMode() {
		return operateMode;
	}
	public void setOperateMode(String operateMode) {
		this.operateMode = operateMode;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public String getColorSid() {
		return colorSid;
	}
	public void setColorSid(String colorSid) {
		this.colorSid = colorSid;
	}
	public String getIsSell() {
		return isSell;
	}
	public void setIsSell(String isSell) {
		this.isSell = isSell;
	}
	
	
}
