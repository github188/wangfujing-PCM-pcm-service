package com.wangfj.product.supplier.domain.entity;

public class PcmShoppeProductSupply {

	private Long sid;

	private Long supplySid;// 供应商sid

	private Long shoppeProductSid;// 专柜商品sid

	private String shopSid;// 门店sid

	private String productSid;// 商品 sku sid

	private Integer status;// 有效标记(0有效,1无效)

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public Long getSupplySid() {
		return supplySid;
	}

	public void setSupplySid(Long supplySid) {
		this.supplySid = supplySid == null ? null : supplySid;
	}

	public Long getShoppeProductSid() {
		return shoppeProductSid;
	}

	public void setShoppeProductSid(Long shoppeProductSid) {
		this.shoppeProductSid = shoppeProductSid == null ? null : shoppeProductSid;
	}

	public String getShopSid() {
		return shopSid;
	}

	public void setShopSid(String shopSid) {
		this.shopSid = shopSid == null ? null : shopSid.trim();
	}

	public String getProductSid() {
		return productSid;
	}

	public void setProductSid(String productSid) {
		this.productSid = productSid == null ? null : productSid.trim();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "PcmShoppeProductSupply [sid=" + sid + ", supplySid=" + supplySid
				+ ", shoppeProductSid=" + shoppeProductSid + ", shopSid=" + shopSid
				+ ", productSid=" + productSid + ", status=" + status + "]";
	}

}