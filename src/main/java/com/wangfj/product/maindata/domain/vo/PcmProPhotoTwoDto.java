package com.wangfj.product.maindata.domain.vo;

import java.util.Arrays;
import java.util.Date;

public class PcmProPhotoTwoDto {
	private String shortDesc;// 短描述
	private String longDesc;// 长描述
	private String sellingStatus;// 上架状态:0 未上架，1 已上架，2 已下架
	private String colorMa;//色码
	
	public String getColorMa() {
		return colorMa;
	}
	public void setColorMa(String colorMa) {
		this.colorMa = colorMa;
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
	/**
	 * 是否有展示分类 (0无 1 有)
	 */
	private String attribute;
	private String shoProCode;// 专柜商品编码
	private String sapShoProCode;// 原系统商品编码（SAP）
	private Date accessTime;// 创建时间
	private String proAccessTime;// 创建时间
	public Date getAccessTime() {
		return accessTime;
	}
	public String getAttribute() {
		return attribute;
	}

	public String getBrandCode() {
		return brandCode;
	}
	public String getBrandName() {
		return brandName;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public String getCategoryName() {
		return categoryName;
	}

	public String getColorCode() {
		return colorCode;
	}

	public String getColorName() {
		return colorName;
	}

	public byte[] getContent() {
		return content;
	}

	public String getContents() {
		return contents;
	}

	public String getLongDesc() {
		return longDesc;
	}

	public String getModelCode() {
		return modelCode;
	}

	public String getProAccessTime() {
		return proAccessTime;
	}

	public String getProduct_name() {
		return product_name;
	}

	public String getProduct_sid() {
		return product_sid;
	}

	public String getSapShoProCode() {
		return sapShoProCode;
	}

	public String getSellingStatus() {
		return sellingStatus;
	}

	public String getSex() {
		return sex;
	}

	public String getShoProCode() {
		return shoProCode;
	}

	public String getShortDesc() {
		return shortDesc;
	}

	public void setAccessTime(Date accessTime) {
		this.accessTime = accessTime;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
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

	public void setContent(byte[] content) {
		this.content = content;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public void setLongDesc(String longDesc) {
		this.longDesc = longDesc;
	}

	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}

	public void setProAccessTime(String proAccessTime) {
		this.proAccessTime = proAccessTime;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public void setProduct_sid(String product_sid) {
		this.product_sid = product_sid;
	}

	public void setSapShoProCode(String sapShoProCode) {
		this.sapShoProCode = sapShoProCode;
	}

	public void setSellingStatus(String sellingStatus) {
		this.sellingStatus = sellingStatus;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setShoProCode(String shoProCode) {
		this.shoProCode = shoProCode;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
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
