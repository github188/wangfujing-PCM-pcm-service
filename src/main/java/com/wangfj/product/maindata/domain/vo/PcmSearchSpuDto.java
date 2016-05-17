package com.wangfj.product.maindata.domain.vo;

public class PcmSearchSpuDto {
	/**
	 * spuCode
	 */
	private String spuCode;
	/**
	 * SPU名称
	 */
	private String productName;
	/**
	 * 款号
	 */
	private String model;
	/**
	 * 产品别名
	 */
	private String aliases;
	/**
	 * 工业分类编码
	 */
	private String factoryCategoryCode;
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getAliases() {
		return aliases;
	}
	public void setAliases(String aliases) {
		this.aliases = aliases;
	}
	public String getFactoryCategoryCode() {
		return factoryCategoryCode;
	}
	public void setFactoryCategoryCode(String factoryCategoryCode) {
		this.factoryCategoryCode = factoryCategoryCode;
	}
	public String getSpuCode() {
		return spuCode;
	}
	public void setSpuCode(String spuCode) {
		this.spuCode = spuCode;
	}
	@Override
	public String toString() {
		return "PcmSearchSpuDto [spuCode=" + spuCode + ", productName="
				+ productName + ", model=" + model + ", aliases=" + aliases
				+ ", factoryCategoryCode=" + factoryCategoryCode + "]";
	}
	
}

