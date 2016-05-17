package com.wangfj.product.brand.domain.vo;

import java.util.Date;

/**
 * 
 * @Class Name FindPcmBrandDto
 * @Author wangxuan
 * @Create In 2015-8-26
 */
public class FindPcmBrandDto {

	private Long brandSid;// 门店品牌sid

	private String code;// 门店品牌编码

	private String name;// 门店品牌名称

	private String name2;// 门店品牌第二名称

	private Integer brandType;// 品牌类型

	private Integer shopType;// 门店类型

	private String storeType;// (下发，导入终端)门店类型

	private Long brandGroupSid;// 集团品牌sid

	private String superCode;// 集团品牌编码

	private String brandGroupName;// 集团品牌名称

	private String optRealName;// 操作人

	private Date optUpdateTime;// 操作时间

	public Long getBrandSid() {
		return brandSid;
	}

	public void setBrandSid(Long brandSid) {
		this.brandSid = brandSid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public Integer getBrandType() {
		return brandType;
	}

	public void setBrandType(Integer brandType) {
		this.brandType = brandType;
	}

	public Integer getShopType() {
		return shopType;
	}

	public void setShopType(Integer shopType) {
		this.shopType = shopType;
	}

	public Long getBrandGroupSid() {
		return brandGroupSid;
	}

	public void setBrandGroupSid(Long brandGroupSid) {
		this.brandGroupSid = brandGroupSid;
	}

	public String getSuperCode() {
		return superCode;
	}

	public void setSuperCode(String superCode) {
		this.superCode = superCode;
	}

	public String getBrandGroupName() {
		return brandGroupName;
	}

	public void setBrandGroupName(String brandGroupName) {
		this.brandGroupName = brandGroupName;
	}

	public String getOptRealName() {
		return optRealName;
	}

	public void setOptRealName(String optRealName) {
		this.optRealName = optRealName;
	}

	public Date getOptUpdateTime() {
		return optUpdateTime;
	}

	public void setOptUpdateTime(Date optUpdateTime) {
		this.optUpdateTime = optUpdateTime;
	}

	public String getStoreType() {
		return storeType;
	}

	public void setStoreType(String storeType) {
		this.storeType = storeType;
	}

	@Override
	public String toString() {
		return "FindPcmBrandDto [brandSid=" + brandSid + ", code=" + code + ", name=" + name
				+ ", name2=" + name2 + ", brandType=" + brandType + ", shopType=" + shopType
				+ ", brandGroupSid=" + brandGroupSid + ", superCode=" + superCode
				+ ", brandGroupName=" + brandGroupName + ", optRealName=" + optRealName
				+ ", optUpdateTime=" + optUpdateTime + "]";
	}

}
