package com.wangfj.product.maindata.domain.vo;

import java.util.Arrays;

public class PcmProPhotoDto {
	private String shortDesc;// 短描述
	private String longDesc;// 长描述
	private String searchKey;// 关键字
	private String keyWord;// 活动关键词
	private String sellingStatus;// 上架状态:0 未上架，1 已上架，2 已下架
	private String skuCode;// sku编码

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public String getSellingStatus() {
		return sellingStatus;
	}

	public void setSellingStatus(String sellingStatus) {
		this.sellingStatus = sellingStatus;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getShortDesc() {
		return shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public String getLongDesc() {
		return longDesc;
	}

	public void setLongDesc(String longDesc) {
		this.longDesc = longDesc;
	}

	/**
	 * 产品编码
	 */
	private String product_sid;
	/**
	 * 产品名称
	 */
	private String product_name;
	/**
	 * 色系编码
	 */
	private String colorCode;
	/**
	 * 色系名称
	 */
	private String colorName;
	// /**
	// * 门店编码
	// */
	// private String storeCode;
	// /**
	// * 门店名称
	// */
	// private String storeName;
	/**
	 * 款号
	 */
	private String modelCode;
	/**
	 * 集团品牌编码
	 */
	private String brandCode;
	/**
	 * 集团品牌名称
	 */
	private String brandName;
	/**
	 * 工业分类编码
	 */
	private String categoryCode;
	/**
	 * 工业分类名称
	 */
	private String categoryName;
	/**
	 * 适合性别
	 */
	private String sex;
	/**
	 * 富文本
	 */
	private byte[] content;
	/**
	 * 富文本
	 */
	private String contents;

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

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

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	// public String getStoreCode() {
	// return storeCode;
	// }
	//
	// public void setStoreCode(String storeCode) {
	// this.storeCode = storeCode;
	// }
	//
	// public String getStoreName() {
	// return storeName;
	// }
	//
	// public void setStoreName(String storeName) {
	// this.storeName = storeName;
	// }

	public String getModelCode() {
		return modelCode;
	}

	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "PcmProPhotoDto [product_sid=" + product_sid + ", product_name=" + product_name
				+ ", colorCode=" + colorCode + ", colorName=" + colorName + ", modelCode="
				+ modelCode + ", brandCode=" + brandCode + ", brandName=" + brandName
				+ ", categoryCode=" + categoryCode + ", categoryName=" + categoryName + ", sex="
				+ sex + ", content=" + Arrays.toString(content) + ", contents=" + contents + "]";
	}

}
