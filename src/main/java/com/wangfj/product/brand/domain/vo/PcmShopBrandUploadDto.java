package com.wangfj.product.brand.domain.vo;

/**
 * 门店与门店品牌关系上传参数
 * 
 * @Class Name PcmShopBrandUploadPara
 * @Author wangxuan
 * @Create In 2015-9-18
 */
public class PcmShopBrandUploadDto {

	private String storeCode;// 门店编码

	private String brandCode;// 门店品牌编码

	private String brandName;// 门店品牌名称

	private String storeType;// 门店类型（0-全局，1-电商、2-北京，3-其他门店）

	private String brandNameSecond;// 品牌第二名称

	private String brandNameSpell;// 品牌中文拼音

	private String brandNameEN;// 品牌英文名称

	private String isDisplay;// 是否展示（0：是，1：否）

	private String brandpic1;// logo图片

	private String brandpic2;// banner图片

	private String branddesc;// 品牌描述

	private String ACT_CODE;// 本条记录对应的操作 (A添加；U更新；D删除)

	private String ACT_DATE;// 操作时间

	private String ACT_PERSON;// 操作人

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode == null ? null : storeCode.trim();
	}

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode == null ? null : brandCode.trim();
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName == null ? null : brandName.trim();
	}

	public String getStoreType() {
		return storeType;
	}

	public void setStoreType(String storeType) {
		this.storeType = storeType;
	}

	public String getBrandNameSecond() {
		return brandNameSecond;
	}

	public void setBrandNameSecond(String brandNameSecond) {
		this.brandNameSecond = brandNameSecond;
	}

	public String getBrandNameSpell() {
		return brandNameSpell;
	}

	public void setBrandNameSpell(String brandNameSpell) {
		this.brandNameSpell = brandNameSpell;
	}

	public String getBrandNameEN() {
		return brandNameEN;
	}

	public void setBrandNameEN(String brandNameEN) {
		this.brandNameEN = brandNameEN;
	}

	public String getIsDisplay() {
		return isDisplay;
	}

	public void setIsDisplay(String isDisplay) {
		this.isDisplay = isDisplay;
	}

	public String getBrandpic1() {
		return brandpic1;
	}

	public void setBrandpic1(String brandpic1) {
		this.brandpic1 = brandpic1;
	}

	public String getBrandpic2() {
		return brandpic2;
	}

	public void setBrandpic2(String brandpic2) {
		this.brandpic2 = brandpic2;
	}

	public String getBranddesc() {
		return branddesc;
	}

	public void setBranddesc(String branddesc) {
		this.branddesc = branddesc;
	}

	public String getACT_CODE() {
		return ACT_CODE;
	}

	public void setACT_CODE(String aCT_CODE) {
		ACT_CODE = aCT_CODE;
	}

	public String getACT_DATE() {
		return ACT_DATE;
	}

	public void setACT_DATE(String aCT_DATE) {
		ACT_DATE = aCT_DATE;
	}

	public String getACT_PERSON() {
		return ACT_PERSON;
	}

	public void setACT_PERSON(String aCT_PERSON) {
		ACT_PERSON = aCT_PERSON;
	}

	@Override
	public String toString() {
		return "PcmShopBrandUploadDto [storeCode=" + storeCode + ", brandCode=" + brandCode
				+ ", brandName=" + brandName + ", storeType=" + storeType + ", brandNameSecond="
				+ brandNameSecond + ", brandNameSpell=" + brandNameSpell + ", brandNameEN="
				+ brandNameEN + ", isDisplay=" + isDisplay + ", brandpic1=" + brandpic1
				+ ", brandpic2=" + brandpic2 + ", branddesc=" + branddesc + ", ACT_CODE="
				+ ACT_CODE + ", ACT_DATE=" + ACT_DATE + ", ACT_PERSON=" + ACT_PERSON + "]";
	}

}
