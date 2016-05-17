package com.wangfj.product.brand.domain.vo;

public class PcmShopBrandResultDto {

	private Long sid;// 门店品牌sid

	private String brandSid;// 门店品牌编码

	private String brandName;// 门店品牌名称

	private Long shopSid;// 门店sid

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getBrandSid() {
		return brandSid;
	}

	public void setBrandSid(String brandSid) {
		this.brandSid = brandSid == null ? null : brandSid.trim();
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName == null ? null : brandName.trim();
	}

	public Long getShopSid() {
		return shopSid;
	}

	public void setShopSid(Long shopSid) {
		this.shopSid = shopSid;
	}

	@Override
	public String toString() {
		return "PcmShopBrandResultDto [sid=" + sid + ", brandSid=" + brandSid + ", brandName="
				+ brandName + ", shopSid=" + shopSid + "]";
	}

}
