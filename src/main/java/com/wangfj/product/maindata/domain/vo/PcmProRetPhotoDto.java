package com.wangfj.product.maindata.domain.vo;

import java.util.List;

public class PcmProRetPhotoDto {
	private String storeCode;// 门店编码
	private String colorName;// 色系名称
	private String modelCode;// 款号
	private String brandCode;// 集团品牌编码
	private String specialCounterName; // 专柜名称
	private String category;// 工业分类编码
	private String productCode;// 商品编码
	private String photoStatus;// 商品拍照状态
	private String planStatus;// 上线计划状态
	private String colorCode;// 色系编码
	private Integer start;
	private Integer limit;
	private Integer pageSize;// 每页数目
	private Integer currentPage;// 当前页码
	private Integer count;
	private Integer industry;// 0全部、1百货、2非超市
	private String productSid;
	private String productName;
	private String brandName;
	private String sellingStatus;
	private String shoProCode;// 专柜商品编码
	private String sapShoProCode;// 原系统商品编码（SAP）
	private List<String> sapCodeList;

	public List<String> getSapCodeList() {
		return sapCodeList;
	}

	public void setSapCodeList(List<String> sapCodeList) {
		this.sapCodeList = sapCodeList;
	}

	public String getShoProCode() {
		return shoProCode;
	}

	public void setShoProCode(String shoProCode) {
		this.shoProCode = shoProCode;
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

	public String getProductSid() {
		return productSid;
	}

	public void setProductSid(String productSid) {
		this.productSid = productSid;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getPlanStatus() {
		return planStatus;
	}

	public void setPlanStatus(String planStatus) {
		this.planStatus = planStatus;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
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

	public String getSpecialCounterName() {
		return specialCounterName;
	}

	public void setSpecialCounterName(String specialCounterName) {
		this.specialCounterName = specialCounterName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getPhotoStatus() {
		return photoStatus;
	}

	public void setPhotoStatus(String photoStatus) {
		this.photoStatus = photoStatus;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public String getColorCode() {
		return colorCode;
	}

	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getIndustry() {
		return industry;
	}

	public void setIndustry(Integer industry) {
		this.industry = industry;
	}

	@Override
	public String toString() {
		return "PcmProRetPhotoDto [storeCode=" + storeCode + ", colorName=" + colorName
				+ ", modelCode=" + modelCode + ", brandCode=" + brandCode + ", specialCounterName="
				+ specialCounterName + ", category=" + category + ", productCode=" + productCode
				+ ", photoStatus=" + photoStatus + ", planStatus=" + planStatus + ", colorCode="
				+ colorCode + ", start=" + start + ", limit=" + limit + ", pageSize=" + pageSize
				+ ", currentPage=" + currentPage + ", count=" + count + ", industry=" + industry
				+ "]";
	}

}
