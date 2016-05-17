package com.wangfj.product.maindata.domain.vo;

public class CmsProductResultDto {
	/**
	 * 商品编码
	 */
	private String productSid;
	/**
	 * 商品名称
	 */
	private String productName;
	/**
	 * 价格
	 */
	private String price;
	/**
	 * 图片地址
	 */
	private String pict;

	public String getProductSid() {
		return productSid;
	}

	public void setProductSid(String productSid) {
		this.productSid = productSid;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPict() {
		return pict;
	}

	public void setPict(String pict) {
		this.pict = pict;
	}

	@Override
	public String toString() {
		return "CmsProductResultDto [productSid=" + productSid + ", productName=" + productName
				+ ", price=" + price + ", pict=" + pict + "]";
	}

}
