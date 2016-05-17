package com.wangfj.product.maindata.domain.vo;

public class TagsPriceStockDto {
	private Long sid;
	private String productName;
	private String price;
	private String stock;
	private String tags;

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
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

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "TagsPriceStockDto [sid=" + sid + ", productName=" + productName + ", price="
				+ price + ", stock=" + stock + ", tags=" + tags + "]";
	}

}
