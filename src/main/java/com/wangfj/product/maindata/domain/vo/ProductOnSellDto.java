package com.wangfj.product.maindata.domain.vo;

public class ProductOnSellDto {
	private Long skuSid;
	private Long spuSid;
	private String spuCode;
	private Integer photoStatus;
	private Long color;
	private String brandSid;
	private String categorySid;
	private Integer saleSum;
	private Integer status;
	private double orgPrice;
	private String proType;
	private String skuSell;

	public String getSkuSell() {
		return skuSell;
	}

	public void setSkuSell(String skuSell) {
		this.skuSell = skuSell;
	}

	public double getOrgPrice() {
		return orgPrice;
	}

	public void setOrgPrice(double orgPrice) {
		this.orgPrice = orgPrice;
	}

	public String getProType() {
		return proType;
	}

	public void setProType(String proType) {
		this.proType = proType;
	}

	public Long getSkuSid() {
		return skuSid;
	}

	public void setSkuSid(Long skuSid) {
		this.skuSid = skuSid;
	}

	public Long getSpuSid() {
		return spuSid;
	}

	public void setSpuSid(Long spuSid) {
		this.spuSid = spuSid;
	}

	public String getSpuCode() {
		return spuCode;
	}

	public void setSpuCode(String spuCode) {
		this.spuCode = spuCode;
	}

	public Integer getPhotoStatus() {
		return photoStatus;
	}

	public void setPhotoStatus(Integer photoStatus) {
		this.photoStatus = photoStatus;
	}

	public Long getColor() {
		return color;
	}

	public void setColor(Long color) {
		this.color = color;
	}

	public String getBrandSid() {
		return brandSid;
	}

	public void setBrandSid(String brandSid) {
		this.brandSid = brandSid;
	}

	public String getCategorySid() {
		return categorySid;
	}

	public void setCategorySid(String categorySid) {
		this.categorySid = categorySid;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getSaleSum() {
		return saleSum;
	}

	public void setSaleSum(Integer saleSum) {
		this.saleSum = saleSum;
	}

	@Override
	public String toString() {
		return "ProductOnSellDto [skuSid=" + skuSid + ", spuSid=" + spuSid + ", spuCode=" + spuCode
				+ ", photoStatus=" + photoStatus + ", color=" + color + ", brandSid=" + brandSid
				+ ", categorySid=" + categorySid + ", saleSum=" + saleSum + ", status=" + status
				+ ", orgPrice=" + orgPrice + ", proType=" + proType + ", skuSell=" + skuSell + "]";
	}

}
