package com.wangfj.product.maindata.domain.vo;

import java.util.List;

/**
 * 回传商品编辑信息DTO
 * 
 * @Class Name ProductEditDto
 * @Author zhangxy
 * @Create In 2015年7月2日
 */
public class ProductEditDto {
	private String product_sid;// 产品表编码
	private String product_name;// 产品名(spu标准品名)
	private String colorCode;// 色系编码
	private String longDescription;// 文本描述
	private String shortDescription;// 短描述
	private String specialDescription;// 特别说明
	private String searchKey;// 关键字
	private String keyWords;// 活动关键词
	private String sizeImageUrl;// 尺码对照表
	private String displayName;// 商品展示名称
	private List<CategoryDto> channelCategory;// 展示分类

	public String getProduct_sid() {
		return product_sid;
	}

	public void setProduct_sid(String product_sid) {
		this.product_sid = product_sid;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getColorCode() {
		return colorCode;
	}

	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getSpecialDescription() {
		return specialDescription;
	}

	public void setSpecialDescription(String specialDescription) {
		this.specialDescription = specialDescription;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public String getSizeImageUrl() {
		return sizeImageUrl;
	}

	public void setSizeImageUrl(String sizeImageUrl) {
		this.sizeImageUrl = sizeImageUrl;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public List<CategoryDto> getChannelCategory() {
		return channelCategory;
	}

	public void setChannelCategory(List<CategoryDto> channelCategory) {
		this.channelCategory = channelCategory;
	}

	@Override
	public String toString() {
		return "ProductEditDto [product_sid=" + product_sid + ", product_name=" + product_name
				+ ", colorCode=" + colorCode + ", longDescription=" + longDescription
				+ ", shortDescription=" + shortDescription + ", specialDescription="
				+ specialDescription + ", searchKey=" + searchKey + ", keyWords=" + keyWords
				+ ", sizeImageUrl=" + sizeImageUrl + ", displayName=" + displayName
				+ ", channelCategory=" + channelCategory + "]";
	}

}