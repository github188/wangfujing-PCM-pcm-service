package com.wangfj.product.supplier.domain.vo;

public class PcmSupplyInfoResultDto {

	private String sid;// 供应商sid

	private String supplyName;// 供应商名称

	private String shopSid;// 门店sid

	private String shopCode;// 门店编码(组织结构编码)

	private String shoppeSid;// 专柜sid

	private String shoppeCode;// 专柜编码

	private String shoppeName;// 专柜名称

	private String supplyCode;// 供应商编码

	private Integer supplyType;// 供应商类型:0，门店供应商；1，电商供应商；2，集团供应商（默认0）

	private String status;// 供应商状态

	private String businessPattern;// 经营方式

	public String getSupplyName() {
		return supplyName;
	}

	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}

	public String getShopSid() {
		return shopSid;
	}

	public void setShopSid(String shopSid) {
		this.shopSid = shopSid;
	}

	public String getSupplyCode() {
		return supplyCode;
	}

	public void setSupplyCode(String supplyCode) {
		this.supplyCode = supplyCode;
	}

	public Integer getSupplyType() {
		return supplyType;
	}

	public void setSupplyType(Integer supplyType) {
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

	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public String getShoppeSid() {
		return shoppeSid;
	}

	public void setShoppeSid(String shoppeSid) {
		this.shoppeSid = shoppeSid;
	}

	public String getShoppeCode() {
		return shoppeCode;
	}

	public void setShoppeCode(String shoppeCode) {
		this.shoppeCode = shoppeCode;
	}

	public String getShoppeName() {
		return shoppeName;
	}

	public void setShoppeName(String shoppeName) {
		this.shoppeName = shoppeName;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	@Override
	public String toString() {
		return "PcmSupplyInfoResultDto [sid=" + sid + ", supplyName=" + supplyName + ", shopSid="
				+ shopSid + ", shopCode=" + shopCode + ", shoppeSid=" + shoppeSid + ", shoppeCode="
				+ shoppeCode + ", shoppeName=" + shoppeName + ", supplyCode=" + supplyCode
				+ ", supplyType=" + supplyType + ", status=" + status + ", businessPattern="
				+ businessPattern + "]";
	}

}
