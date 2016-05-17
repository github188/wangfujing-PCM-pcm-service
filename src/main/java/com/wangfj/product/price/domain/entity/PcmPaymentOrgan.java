package com.wangfj.product.price.domain.entity;

public class PcmPaymentOrgan {

	private Long sid;

	private String shopSid;

	private String code;
	/**
	 * 银行识别码
	 */
	private String bankBin;

	private Integer status;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getShopSid() {
		return shopSid;
	}

	public void setShopSid(String shopSid) {
		this.shopSid = shopSid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getBankBin() {
		return bankBin;
	}

	public void setBankBin(String bankBin) {
		this.bankBin = bankBin;
	}

	@Override
	public String toString() {
		return "PcmPaymentOrgan [sid=" + sid + ", shopSid=" + shopSid + ", code=" + code
				+ ", bankBin=" + bankBin + ", status=" + status + "]";
	}

}