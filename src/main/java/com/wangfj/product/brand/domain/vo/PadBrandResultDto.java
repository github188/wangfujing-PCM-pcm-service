package com.wangfj.product.brand.domain.vo;

/**
 * 移动工作台调用主数据获取品牌信息(返回参数)
 * 
 * @Class Name PadBrandResultDto
 * @Author wangxuan
 * @Create In 2015-8-27
 */
public class PadBrandResultDto {

	private String superCode;// 集团商标(中台的主品牌统一编码)集团品牌

	private String code;// 门店品牌编码

	private String name;// 中台品牌名称

	private String storeType;// 门店类型（0-全局，1-电商、2-北京，3-其他门店）

	private String Name2;// 中台品牌第二名称

	private String brandType;// 门店品牌的品牌类型

	private String shopType;// 门店品牌的门店类型

	public String getSuperCode() {
		return superCode;
	}

	public void setSuperCode(String superCode) {
		this.superCode = superCode;
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

	public String getStoreType() {
		return storeType;
	}

	public void setStoreType(String storeType) {
		this.storeType = storeType;
	}

	public String getName2() {
		return Name2;
	}

	public void setName2(String name2) {
		Name2 = name2;
	}

	public String getBrandType() {
		return brandType;
	}

	public void setBrandType(String brandType) {
		this.brandType = brandType;
	}

	public String getShopType() {
		return shopType;
	}

	public void setShopType(String shopType) {
		this.shopType = shopType;
	}

	@Override
	public String toString() {
		return "PadBrandResultDto [superCode=" + superCode + ", code=" + code + ", name=" + name
				+ ", storeType=" + storeType + ", Name2=" + Name2 + ", brandType=" + brandType
				+ ", shopType=" + shopType + "]";
	}

}
