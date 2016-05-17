package com.wangfj.product.brand.domain.vo;

/**
 * 门店与品牌关系实体DTO
 * 
 * @Class Name PcmShopBrandRelationDto
 * @Author wangxuan
 * @Create In 2015-8-19
 */
public class PcmShopBrandRelationDto {

	private Long sid;

	private String shopCode;// 门店编码

	private String brandCode;// 门店品牌编码

	private String brandGroupCode;// 集团品牌编码

	private String brandName;// 品牌名称

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode == null ? null : shopCode.trim();
	}

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode == null ? null : brandCode.trim();
	}

	public String getBrandGroupCode() {
		return brandGroupCode;
	}

	public void setBrandGroupCode(String brandGroupCode) {
		this.brandGroupCode = brandGroupCode == null ? null : brandGroupCode.trim();
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName == null ? null : brandName.trim();
	}
}