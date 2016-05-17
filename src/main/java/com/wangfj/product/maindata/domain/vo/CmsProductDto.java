package com.wangfj.product.maindata.domain.vo;

public class CmsProductDto {
	/**
	 * 款号
	 */
	private String proSku;
	/**
	 * 商品品类名称
	 */
	private String cateName;
	/**
	 * 品排名称
	 */
	private String brandName;
	/**
	 * 上架时间
	 */
	private String proSellongBeginTime;
	/**
	 * 渠道编码
	 */
	private String channelCode;
	/**
	 * 专柜编码
	 */
	private String shoppeCode;
	/**
	 * 门店编码
	 */
	private String shopCode;
	/**
	 * 分类编码
	 */
	private String cateCode;

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getShoppeCode() {
		return shoppeCode;
	}

	public void setShoppeCode(String shoppeCode) {
		this.shoppeCode = shoppeCode;
	}

	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public String getCateCode() {
		return cateCode;
	}

	public void setCateCode(String cateCode) {
		this.cateCode = cateCode;
	}

	public String getProSku() {
		return proSku;
	}

	public void setProSku(String proSku) {
		this.proSku = proSku;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getProSellongBeginTime() {
		return proSellongBeginTime;
	}

	public void setProSellongBeginTime(String proSellongBeginTime) {
		this.proSellongBeginTime = proSellongBeginTime;
	}

	@Override
	public String toString() {
		return "CmsProductDto [proSku=" + proSku + ", cateName=" + cateName + ", brandName="
				+ brandName + ", proSellongBeginTime=" + proSellongBeginTime + ", channelCode="
				+ channelCode + ", shoppeCode=" + shoppeCode + ", shopCode=" + shopCode
				+ ", cateCode=" + cateCode + "]";
	}

}
