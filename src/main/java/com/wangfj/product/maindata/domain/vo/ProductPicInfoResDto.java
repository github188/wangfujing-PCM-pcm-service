package com.wangfj.product.maindata.domain.vo;

import java.util.ArrayList;
import java.util.List;

public class ProductPicInfoResDto {
	private String spuColor;// 重复
	private String product_sid;// 产品编码
	private String product_name;// 产品名称
	private String colorCode;// 色系编码
	private String colorName;// 色系名称
	private String modelCode;// 款号
	private String brandCode;// 品牌编码
	private String brandName;// 品牌名称
	private String categoryCode;// 工业分类编码
	private String categoryName;// 工业分类名称
	private String sellingStatus;// 上架状态
	List<PicInfoResDto> picList = new ArrayList<PicInfoResDto>();// 图片List
	private String colorMa;// 色码
	private String sapShoProCode;// SAP商品编码/照片编码
	private String proCode;// 专柜商品编码

	public String getProCode() {
		return proCode;
	}

	public void setProCode(String proCode) {
		this.proCode = proCode;
	}

	public String getColorMa() {
		return colorMa;
	}

	public void setColorMa(String colorMa) {
		this.colorMa = colorMa;
	}

	public String getSapShoProCode() {
		return sapShoProCode;
	}

	public void setSapShoProCode(String sapShoProCode) {
		this.sapShoProCode = sapShoProCode;
	}

	public String getSellingStatus() {
		return sellingStatus;
	}

	public void setSellingStatus(String sellingStatus) {
		this.sellingStatus = sellingStatus;
	}

	public String getSpuColor() {
		return spuColor;
	}

	public void setSpuColor(String spuColor) {
		this.spuColor = spuColor;
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

	public List<PicInfoResDto> getPicList() {
		return picList;
	}

	public void setPicList(List<PicInfoResDto> picList) {
		this.picList = picList;
	}

	@Override
	public String toString() {
		return "ProductPicInfoResDto [spuColor=" + spuColor + ", product_sid=" + product_sid
				+ ", product_name=" + product_name + ", colorCode=" + colorCode + ", colorName="
				+ colorName + ", modelCode=" + modelCode + ", brandCode=" + brandCode
				+ ", brandName=" + brandName + ", categoryCode=" + categoryCode + ", categoryName="
				+ categoryName + ", sellingStatus=" + sellingStatus + ", picList=" + picList + "]";
	}

}
