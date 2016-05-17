package com.wangfj.product.maindata.domain.vo;

public class PcmSearchSkuDto {
	/**
	 * SKU Code
	 */
	private String skuCode;
	/**
	 * 所属产品Code
	 */
	private String productCode;
	/**
	 * 规格Code
	 */
	private String standardCode;
	/**
	 * 规格名称
	 */
	private String standardName;
	/**
	 * 色系Code
	 */
	private String colorCode;
	/**
	 * 色系名称
	 */
	private String colorName;
	/**
	 * SKU颜色别名
	 */
	private String colorAlias;
	
	public String getSkuCode() {
		return skuCode;
	}
	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getStandardCode() {
		return standardCode;
	}
	public void setStandardCode(String standardCode) {
		this.standardCode = standardCode;
	}
	public String getStandardName() {
		return standardName;
	}
	public void setStandardName(String standardName) {
		this.standardName = standardName;
	}
	public String getColorCode() {
		return colorCode;
	}
	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}
	public String getColorName() {
		return colorName;
	}
	public void setColorName(String colorName) {
		this.colorName = colorName;
	}
	public String getColorAlias() {
		return colorAlias;
	}
	public void setColorAlias(String colorAlias) {
		this.colorAlias = colorAlias;
	}
	@Override
	public String toString() {
		return "PcmSearchDto [skuCode=" + skuCode + ", productCode=" + productCode + ", standardCode="
				+ standardCode + ", standardName=" + standardName + ", colorCode=" + colorCode
				+ ", colorName=" + colorName + ", colorAlias=" + colorAlias + "]";
	}
}
