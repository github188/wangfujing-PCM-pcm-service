package com.wangfj.product.brand.domain.vo;

import java.util.Date;

/**
 * 下发返回参数
 * 
 * @Class Name PushBrandDto
 * @Author wangxuan
 * @Create In 2015-8-26
 */
public class PushBrandDto {

	private String superCode;// 集团品牌编码

	private String code;// 门店品牌编码

	private String name;// 门店品牌名称

	private String storeType;// (下发)门店类型（0-全局，1-电商、2-北京，3-其他门店）

	private String name2;// 门店品牌第二名称

	private Integer brandType;// 品牌类型

	private Integer shopType;// 门店类型

	private String optRealName;// 操作人

	private Date optUpdateTime;// 操作时间

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

	public String getSuperCode() {
		return superCode;
	}

	public void setSuperCode(String superCode) {
		this.superCode = superCode;
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
		return "PushBrandDto [superCode=" + superCode + ", code=" + code + ", name=" + name
				+ ", storeType=" + storeType + ", name2=" + name2 + ", brandType=" + brandType
				+ ", shopType=" + shopType + ", optRealName=" + optRealName + ", optUpdateTime="
				+ optUpdateTime + "]";
	}

}
