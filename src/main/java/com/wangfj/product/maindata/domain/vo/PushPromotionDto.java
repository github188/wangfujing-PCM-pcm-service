package com.wangfj.product.maindata.domain.vo;

public class PushPromotionDto {
	private String productCode;
	private String productName;
	private String brandCode;
	private String modelNumber;
	private String baseNumber;
	private String styleCode;
	private String sizeCode;
	private String productCategory;
	private String listing;
	private String productType;
	private String erpProductCategoryLib;
	private String mgmtType;
	private String longDes;
	private String shortDes;
	private String packDes;
	private String searchKey;
	private String keyword;
	private String specialDes;
	private String isPublished;
	private String sizeImageUrl;
	private String actionCode;
	private String actionDate;
	private String actionPerson;
	private byte[] packDesE;

	public byte[] getPackDesE() {
		return packDesE;
	}

	public void setPackDesE(byte[] packDesE) {
		this.packDesE = packDesE;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}

	public String getBaseNumber() {
		return baseNumber;
	}

	public void setBaseNumber(String baseNumber) {
		this.baseNumber = baseNumber;
	}

	public String getStyleCode() {
		return styleCode;
	}

	public void setStyleCode(String styleCode) {
		this.styleCode = styleCode;
	}

	public String getSizeCode() {
		return sizeCode;
	}

	public void setSizeCode(String sizeCode) {
		this.sizeCode = sizeCode;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getListing() {
		return listing;
	}

	public void setListing(String listing) {
		this.listing = listing;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getErpProductCategoryLib() {
		return erpProductCategoryLib;
	}

	public void setErpProductCategoryLib(String erpProductCategoryLib) {
		this.erpProductCategoryLib = erpProductCategoryLib;
	}

	public String getMgmtType() {
		return mgmtType;
	}

	public void setMgmtType(String mgmtType) {
		this.mgmtType = mgmtType;
	}

	public String getLongDes() {
		return longDes;
	}

	public void setLongDes(String longDes) {
		this.longDes = longDes;
	}

	public String getShortDes() {
		return shortDes;
	}

	public void setShortDes(String shortDes) {
		this.shortDes = shortDes;
	}

	public String getPackDes() {
		return packDes;
	}

	public void setPackDes(String packDes) {
		this.packDes = packDes;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getSpecialDes() {
		return specialDes;
	}

	public void setSpecialDes(String specialDes) {
		this.specialDes = specialDes;
	}

	public String getIsPublished() {
		return isPublished;
	}

	public void setIsPublished(String isPublished) {
		this.isPublished = isPublished;
	}

	public String getSizeImageUrl() {
		return sizeImageUrl;
	}

	public void setSizeImageUrl(String sizeImageUrl) {
		this.sizeImageUrl = sizeImageUrl;
	}

	public String getActionCode() {
		return actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	public String getActionDate() {
		return actionDate;
	}

	public void setActionDate(String actionDate) {
		this.actionDate = actionDate;
	}

	public String getActionPerson() {
		return actionPerson;
	}

	public void setActionPerson(String actionPerson) {
		this.actionPerson = actionPerson;
	}

	@Override
	public String toString() {
		return "PushPromotionDto [productCode=" + productCode + ", productName=" + productName
				+ ", brandCode=" + brandCode + ", modelNumber=" + modelNumber + ", baseNumber="
				+ baseNumber + ", styleCode=" + styleCode + ", sizeCode=" + sizeCode
				+ ", productCategory=" + productCategory + ", listing=" + listing
				+ ", productType=" + productType + ", erpProductCategoryLib="
				+ erpProductCategoryLib + ", mgmtType=" + mgmtType + ", longDes=" + longDes
				+ ", shortDes=" + shortDes + ", packDes=" + packDes + ", searchKey=" + searchKey
				+ ", keyword=" + keyword + ", specialDes=" + specialDes + ", isPublished="
				+ isPublished + ", sizeImageUrl=" + sizeImageUrl + ", actionCode=" + actionCode
				+ ", actionDate=" + actionDate + ", actionPerson=" + actionPerson + "]";
	}

}
