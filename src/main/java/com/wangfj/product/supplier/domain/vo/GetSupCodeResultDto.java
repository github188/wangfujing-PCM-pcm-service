/**
 * @Probject Name: pcm-service
 * @Path: com.wangfj.product.supplier.domain.voGetSupCodeResultDto.java
 * @Create By wangc
 * @Create In 2016-4-12 下午3:11:08
 * TODO
 */
package com.wangfj.product.supplier.domain.vo;


/**
 * 根据门店编码及管理分类编码查询
 * @Class Name GetSupCodeResultDto
 * @Author wangc
 * @Create In 2016-4-12
 */
public class GetSupCodeResultDto {
    
	private String shopCode;//门店编码
	
    private String supplierCode;//供应商编码
	
	private String supplierName;//供应商名称
	
	private String manageType;//经营方式
	
	private String supplierType;//供应商类型
	
	private String manageCategoryCode;//管理分类编码
	
	public String getManageCategoryCode() {
		return manageCategoryCode;
	}

	public void setManageCategoryCode(String manageCategoryCode) {
		this.manageCategoryCode = manageCategoryCode;
	}

	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
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

	public String getManageType() {
		return manageType;
	}

	public void setManageType(String manageType) {
		this.manageType = manageType;
	}

	public String getSupplierType() {
		return supplierType;
	}

	public void setSupplierType(String supplierType) {
		this.supplierType = supplierType;
	}

}
