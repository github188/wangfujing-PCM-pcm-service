package com.wangfj.product.maindata.domain.vo;

public class PhotoStatusDto {
	private String productCode;
	private String color;
	private Integer status;
	/**
	 * 上线计划
	 */
	private String photoPlanSid;

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPhotoPlanSid() {
		return photoPlanSid;
	}

	public void setPhotoPlanSid(String photoPlanSid) {
		this.photoPlanSid = photoPlanSid;
	}

	@Override
	public String toString() {
		return "PhotoStatusDto [productCode=" + productCode + ", color=" + color + ", status="
				+ status + ", photoPlanSid=" + photoPlanSid + "]";
	}

}
