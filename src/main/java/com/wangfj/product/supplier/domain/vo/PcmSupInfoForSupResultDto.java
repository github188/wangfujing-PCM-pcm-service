/**
 * @Probject Name: pcm-service
 * @Path: com.wangfj.product.supplier.domain.voPcmSupInfoForLicenseNoAndTaxNoResultDto.java
 * @Create By wangc
 * @Create In 2016-4-13 下午6:51:55
 */
package com.wangfj.product.supplier.domain.vo;

/**
 * 根据营业执照号及税号获取供应商信息结果封装类
 * @Class Name PcmSupInfoForLicenseNoAndTaxNoResultDto
 * @Author wangc
 * @Create In 2016-4-13
 */
public class PcmSupInfoForSupResultDto {

	private String supplyCode;//供应商编码
	
	private String supplyName;//供应商名称
	
	private String storeCode;//门店编码
	
	private Integer operaterMode;//经营方式0-(Z001 经销); 1-(Z002 代销); 2-(Z003 联营);3-(Z004 平台服务);4-(Z005 租赁);  

	private Integer apartOrder;//拆单标识0: 不是 1：是
	
	private String supplyStatus;//供应商状态 Y正常；T未批准；N终止；L待审批；3淘汰；4停货；5停款；6冻结。
	
	private String licenseNo;//营业执照号
	
	private String taxNo;//税号
	
	

	public String getSupplyName() {
		return supplyName;
	}

	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	public String getTaxNo() {
		return taxNo;
	}

	public void setTaxNo(String taxNo) {
		this.taxNo = taxNo;
	}

	public String getSupplyCode() {
		return supplyCode;
	}

	public void setSupplyCode(String supplyCode) {
		this.supplyCode = supplyCode;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public Integer getOperaterMode() {
		return operaterMode;
	}

	public void setOperaterMode(Integer operaterMode) {
		this.operaterMode = operaterMode;
	}

	public Integer getApartOrder() {
		return apartOrder;
	}

	public void setApartOrder(Integer apartOrder) {
		this.apartOrder = apartOrder;
	}

	public String getSupplyStatus() {
		return supplyStatus;
	}

	public void setSupplyStatus(String supplyStatus) {
		this.supplyStatus = supplyStatus;
	}
	
	
}
