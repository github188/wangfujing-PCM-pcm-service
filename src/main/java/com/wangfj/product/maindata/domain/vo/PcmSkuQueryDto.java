package com.wangfj.product.maindata.domain.vo;

/**
 * Created by wangxuan on 2016-03-07 0007.
 */
public class PcmSkuQueryDto {

	private String brandGroupCode;// 集团品牌编码

	private String colorSid;// 色系的sid

	private String modelCode;// 款号

	private String proType;// 商品类型

	private String skuCode;// sku编码

	private String skuName;// sku名称

	private String isAddTag;// 是否已加入关键字

	private String tagSid;// 关键字的sid

	private String proActiveBit;// 是否启用

	private String photoStatus;// 照片状态

	private String skuSale;// 上架状态

	private Integer start;

	private Integer limit;

	public String getBrandGroupCode() {
		return brandGroupCode;
	}

	public void setBrandGroupCode(String brandGroupCode) {
		this.brandGroupCode = brandGroupCode;
	}

	public String getColorSid() {
		return colorSid;
	}

	public void setColorSid(String colorSid) {
		this.colorSid = colorSid;
	}

	public String getModelCode() {
		return modelCode;
	}

	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}

	public String getProType() {
		return proType;
	}

	public void setProType(String proType) {
		this.proType = proType;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	public String getIsAddTag() {
		return isAddTag;
	}

	public void setIsAddTag(String isAddTag) {
		this.isAddTag = isAddTag;
	}

	public String getTagSid() {
		return tagSid;
	}

	public void setTagSid(String tagSid) {
		this.tagSid = tagSid;
	}

	public String getProActiveBit() {
		return proActiveBit;
	}

	public void setProActiveBit(String proActiveBit) {
		this.proActiveBit = proActiveBit;
	}

	public String getPhotoStatus() {
		return photoStatus;
	}

	public void setPhotoStatus(String photoStatus) {
		this.photoStatus = photoStatus;
	}

	public String getSkuSale() {
		return skuSale;
	}

	public void setSkuSale(String skuSale) {
		this.skuSale = skuSale;
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

	@Override
	public String toString() {
		return "PcmSkuQueryDto{" + "brandGroupCode='" + brandGroupCode + '\'' + ", colorSid='"
				+ colorSid + '\'' + ", modelCode='" + modelCode + '\'' + ", proType='" + proType
				+ '\'' + ", skuCode='" + skuCode + '\'' + ", skuName='" + skuName + '\''
				+ ", isAddTag='" + isAddTag + '\'' + ", tagSid='" + tagSid + '\''
				+ ", proActiveBit='" + proActiveBit + '\'' + ", photoStatus='" + photoStatus + '\''
				+ ", skuSale='" + skuSale + '\'' + ", start=" + start + ", limit=" + limit + '}';
	}
}
