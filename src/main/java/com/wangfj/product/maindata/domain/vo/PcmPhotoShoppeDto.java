package com.wangfj.product.maindata.domain.vo;

public class PcmPhotoShoppeDto {
	/**
	 * 产品编码
	 */
	private String product_sid;
	/**
	 * 尺码
	 */
	private String size;
	/**
	 * 色系编码
	 */
	private String colorCode;
	/**
	 * 门店编码
	 */
	private String storeCode;

	private String productCode;

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getProduct_sid() {
		return product_sid;
	}

	public void setProduct_sid(String product_sid) {
		this.product_sid = product_sid;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getColorCode() {
		return colorCode;
	}

	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}

	@Override
	public String toString() {
		return "PcmPhotoShoppeDto [product_sid=" + product_sid + ", size=" + size + ", colorCode="
				+ colorCode + ", storeCode=" + storeCode + "]";
	}

}
