package com.wangfj.product.maindata.domain.vo;

public class PcmContractLogPartDto {

	private Long sid;
	/**
	 * 要约号
	 */
	private String contractCode;
	/**
	 * 门店编码
	 */
	private String storeCode;
	/**
	 * 供应商编码
	 */
	private String supplyCode;
	/**
	 * 经营方式
	 */
	private Integer manageType;

	private Integer businessType;

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getSupplyCode() {
		return supplyCode;
	}

	public void setSupplyCode(String supplyCode) {
		this.supplyCode = supplyCode;
	}

	public Integer getManageType() {
		return manageType;
	}

	public void setManageType(Integer manageType) {
		this.manageType = manageType;
	}

	public Integer getBusinessType() {
		return businessType;
	}

	public void setBusinessType(Integer businessType) {
		this.businessType = businessType;
	}

	@Override
	public String toString() {
		return "PcmContractLogPartDto [sid=" + sid + ", contractCode=" + contractCode
				+ ", storeCode=" + storeCode + ", supplyCode=" + supplyCode + ", manageType="
				+ manageType + ", businessType=" + businessType + "]";
	}

}