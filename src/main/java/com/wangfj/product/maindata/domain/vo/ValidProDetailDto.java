package com.wangfj.product.maindata.domain.vo;

import com.wangfj.core.framework.base.dto.BaseDto;

public class ValidProDetailDto extends BaseDto {
	/**
	 * @Field long serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/* 品牌+款号+颜色码+主属性+特性+规格+颜色+SPU_Sid */
	/**
	 * 产品表编码
	 */
	private String productSid;
	/**
	 * 规格sid(舍弃不添加数据)
	 */
	private String proStanSid;
	/**
	 * 商品颜色
	 */
	private String proColorName;
	/**
	 * 品牌编码
	 */
	private String brandSid;
	/**
	 * 商品sku
	 */
	private String productSku;
	/**
	 * 主属性
	 */
	private String primaryAttr;
	/**
	 * 特性
	 */
	private String features;
	/**
	 * 色系
	 */
	private String proColorSid;
	/**
	 * 色系名称
	 */
	private String colorName;

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public String getProductSid() {
		return productSid;
	}

	public void setProductSid(String productSid) {
		this.productSid = productSid;
	}

	public String getProStanSid() {
		return proStanSid;
	}

	public void setProStanSid(String proStanSid) {
		this.proStanSid = proStanSid;
	}

	public String getProductSku() {
		return productSku;
	}

	public void setProductSku(String productSku) {
		this.productSku = productSku;
	}

	public String getPrimaryAttr() {
		return primaryAttr;
	}

	public void setPrimaryAttr(String primaryAttr) {
		this.primaryAttr = primaryAttr;
	}

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}

	public String getBrandSid() {
		return brandSid;
	}

	public void setBrandSid(String brandSid) {
		this.brandSid = brandSid;
	}

	public String getProColorName() {
		return proColorName;
	}

	public void setProColorName(String proColorName) {
		this.proColorName = proColorName;
	}

	public String getProColorSid() {
		return proColorSid;
	}

	public void setProColorSid(String proColorSid) {
		this.proColorSid = proColorSid;
	}

	@Override
	public String toString() {
		return "ValidProDetailDto [productSid=" + productSid + ", proStanSid=" + proStanSid
				+ ", proColorName=" + proColorName + ", brandSid=" + brandSid + ", productSku="
				+ productSku + ", primaryAttr=" + primaryAttr + ", features=" + features
				+ ", proColorSid=" + proColorSid + "]";
	}

}
