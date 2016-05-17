package com.wangfj.product.supplier.domain.vo;

public class PcmShoppeSupplierResultDto {

	private String shopName;// 门店名称

	private String shopCode;// 门店编码

	private String shoppeName;// 专柜名称

	private String shoppeCode;// 专柜编码

	private String shoppeType;// 专柜类型

	private String shoppeStatus;// 专柜状态

	private String industryConditionSid;// 专柜业态

	private String supplyName;// 供应商名称

	private String supplyCode;// 供应商编码

	private String supplyType;// 供应商类型

	private String status;// 供应商状态

	private String businessPattern;// 供应商经营方式

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public String getShoppeName() {
		return shoppeName;
	}

	public void setShoppeName(String shoppeName) {
		this.shoppeName = shoppeName;
	}

	public String getShoppeCode() {
		return shoppeCode;
	}

	public void setShoppeCode(String shoppeCode) {
		this.shoppeCode = shoppeCode;
	}

	public String getShoppeType() {
		return shoppeType;
	}

	public void setShoppeType(String shoppeType) {
		this.shoppeType = shoppeType;
	}

	public String getShoppeStatus() {
		return shoppeStatus;
	}

	public void setShoppeStatus(String shoppeStatus) {
		this.shoppeStatus = shoppeStatus;
	}

	public String getIndustryConditionSid() {
		return industryConditionSid;
	}

	public void setIndustryConditionSid(String industryConditionSid) {
		this.industryConditionSid = industryConditionSid;
	}

	public String getSupplyName() {
		return supplyName;
	}

	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}

	public String getSupplyCode() {
		return supplyCode;
	}

	public void setSupplyCode(String supplyCode) {
		this.supplyCode = supplyCode;
	}

	public String getSupplyType() {
		return supplyType;
	}

	public void setSupplyType(String supplyType) {
		this.supplyType = supplyType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBusinessPattern() {
		return businessPattern;
	}

	public void setBusinessPattern(String businessPattern) {
		this.businessPattern = businessPattern;
	}

	@Override
	public String toString() {
		return "PcmShoppeSupplierResultDto [shopName=" + shopName + ", shopCode=" + shopCode
				+ ", shoppeName=" + shoppeName + ", shoppeCode=" + shoppeCode + ", shoppeType="
				+ shoppeType + ", shoppeStatus=" + shoppeStatus + ", industryConditionSid="
				+ industryConditionSid + ", supplyName=" + supplyName + ", supplyCode="
				+ supplyCode + ", supplyType=" + supplyType + ", status=" + status
				+ ", businessPattern=" + businessPattern + "]";
	}

}
