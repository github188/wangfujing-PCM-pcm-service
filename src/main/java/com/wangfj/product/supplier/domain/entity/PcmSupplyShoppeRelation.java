package com.wangfj.product.supplier.domain.entity;

public class PcmSupplyShoppeRelation {
	private Long sid;

	private String shoppeSid;

	private String supplySid;

	private Integer status;

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getShoppeSid() {
		return shoppeSid;
	}

	public void setShoppeSid(String shoppeSid) {
		this.shoppeSid = shoppeSid;
	}

	public String getSupplySid() {
		return supplySid;
	}

	public void setSupplySid(String supplySid) {
		this.supplySid = supplySid;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "PcmSupplyShoppeRelation [sid=" + sid + ", shoppeSid=" + shoppeSid + ", supplySid="
				+ supplySid + ", status=" + status + "]";
	}

}