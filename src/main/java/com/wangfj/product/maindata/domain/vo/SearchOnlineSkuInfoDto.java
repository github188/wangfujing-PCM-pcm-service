package com.wangfj.product.maindata.domain.vo;

import java.util.ArrayList;
import java.util.List;

public class SearchOnlineSkuInfoDto {
	private String pictureSid;

	public String getPictureSid() {
		return pictureSid;
	}

	public void setPictureSid(String pictureSid) {
		this.pictureSid = pictureSid;
	}

	private String upTime;
	/*
	 * 长标题
	 */
	private String title;
	/*
	 * 短标题
	 */
	private String subTitle;
	/*
	 * 活动关键字
	 */
	private List<String> activeKeywords;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public List<String> getActiveKeywords() {
		return activeKeywords;
	}

	public void setActiveKeywords(List<String> activeKeywords) {
		this.activeKeywords = activeKeywords;
	}

	/**
	 * SKU编码
	 */
	private String skuId;
	/**
	 * SPU编码
	 */
	private String spuId;
	/**
	 * 商品类型
	 */
	private String type;
	/**
	 * 可售状态
	 */
	private boolean onSell;
	/**
	 * 色系编码
	 */
	private String colorId;
	/**
	 * 色系名称
	 */
	private String colorName;
	/**
	 * 商品颜色别名、色码
	 */
	private String colorAlias;
	/**
	 * 规格编码
	 */
	private String standardId;
	/**
	 * 规格名称
	 */
	private String standardName;
	/**
	 * 图片信息
	 */
	List<SearchOnlineUrlInfoDto> pictures = new ArrayList<SearchOnlineUrlInfoDto>();

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public String getSpuId() {
		return spuId;
	}

	public void setSpuId(String spuId) {
		this.spuId = spuId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isOnSell() {
		return onSell;
	}

	public void setOnSell(boolean onSell) {
		this.onSell = onSell;
	}

	public String getColorId() {
		return colorId;
	}

	public void setColorId(String colorId) {
		this.colorId = colorId;
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

	public String getStandardId() {
		return standardId;
	}

	public void setStandardId(String standardId) {
		this.standardId = standardId;
	}

	public String getStandardName() {
		return standardName;
	}

	public void setStandardName(String standardName) {
		this.standardName = standardName;
	}

	public List<SearchOnlineUrlInfoDto> getPictures() {
		return pictures;
	}

	public void setPictures(List<SearchOnlineUrlInfoDto> pictures) {
		this.pictures = pictures;
	}

	public String getUpTime() {
		return upTime;
	}

	public void setUpTime(String upTime) {
		this.upTime = upTime;
	}

	@Override
	public String toString() {
		return "SearchOnlineSkuInfoDto [pictureSid=" + pictureSid + ", upTime=" + upTime
				+ ", skuId=" + skuId + ", spuId=" + spuId + ", type=" + type + ", onSell=" + onSell
				+ ", colorId=" + colorId + ", colorName=" + colorName + ", colorAlias="
				+ colorAlias + ", standardId=" + standardId + ", standardName=" + standardName
				+ ", pictures=" + pictures + "]";
	}

}
