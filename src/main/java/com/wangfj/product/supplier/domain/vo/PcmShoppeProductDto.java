package com.wangfj.product.supplier.domain.vo;

public class PcmShoppeProductDto {

	private Long sid;

	private String supplySid;// 供应商编码

	private String shopPuductSid;// 专柜商品编码

	private String shopSid;// 门店sid

	private String productSid;// 商品 sku sid

	private String actionCode;

	private String actionDate;

	private String actionPerson;

	private Integer saleStatus; // 销售状态

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getSupplySid() {
		return supplySid;
	}

	public void setSupplySid(String supplySid) {
		this.supplySid = supplySid;
	}

	public String getShopPuductSid() {
		return shopPuductSid;
	}

	public void setShopPuductSid(String shopPuductSid) {
		this.shopPuductSid = shopPuductSid;
	}

	public String getActionCode() {
		return actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	public String getActionDate() {
		return actionDate;
	}

	public void setActionDate(String actionDate) {
		this.actionDate = actionDate;
	}

	public String getActionPerson() {
		return actionPerson;
	}

	public void setActionPerson(String actionPerson) {
		this.actionPerson = actionPerson;
	}

	public Integer getSaleStatus() {
		return saleStatus;
	}

	public void setSaleStatus(Integer saleStatus) {
		this.saleStatus = saleStatus;
	}

	@Override
	public String toString() {
		return "PcmShoppeProductDto [sid=" + sid + ", supplySid=" + supplySid + ", shopPuductSid="
				+ shopPuductSid + ", actionCode=" + actionCode + ", actionDate=" + actionDate
				+ ", actionPerson=" + actionPerson + ", saleStatus=" + saleStatus + "]";
	}

	public String getShopSid() {
		return shopSid;
	}

	public void setShopSid(String shopSid) {
		this.shopSid = shopSid;
	}

	public String getProductSid() {
		return productSid;
	}

	public void setProductSid(String productSid) {
		this.productSid = productSid;
	}

}
