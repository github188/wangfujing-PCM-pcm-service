package com.wangfj.product.supplier.domain.vo;

public class PcmSupplyInfoPartDto {

	private Long sid;

	private String supplyName;// 供应商名称

	private String shopSid;// 门店sid

	private String shopCode;// 门店编码(组织结构编码)

	private String supplyCode;// 供应商编码

	private Integer supplyType;// 供应商类型:0，门店供应商；1，电商供应商；2，集团供应商（默认0）

	private String status;// 供应商状态

	private String businessPattern;// 经营方式

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

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

	@Override
	public String toString() {
		return "PcmSupplyInfoPartDto [sid=" + sid + ", supplyName=" + supplyName + ", shopSid="
				+ shopSid + ", shopCode=" + shopCode + ", supplyCode=" + supplyCode
				+ ", supplyType=" + supplyType + ", status=" + status + ", businessPattern="
				+ businessPattern + "]";
	}

}
