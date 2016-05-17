package com.wangfj.product.brand.domain.vo;

import java.util.Date;

import com.wangfj.core.framework.base.dto.BaseDto;

/**
 * 品牌DTO
 * 
 * @Class Name PcmBrandDto
 * @Author wangx
 * @Create In 2015-8-12
 */
public class PcmBrandDto extends BaseDto {

	/**
	 * @Field long serialVersionUID
	 */
	private static final long serialVersionUID = -812783188815975495L;

	private Long sid;

	private String brandSid;// 门店品牌sid

	private String brandName;// 门店品牌名称

	private String superCode;// 与集团品牌关联的编码

	private String brandFatherName;// 页面展示用的，门店品牌对应的集团品牌名称

	private String pictureUrl;// 图片路径

	private String brandNameSecond;// 品牌第二个名字

	private Long brandActiveBit;//

	private String brandno;

	private String brandcorp;// 品牌公司

	private String brandpic1;// 图片1

	private String brandpic2;// 图片2

	private Long photoBlacklistBit;// 照片黑名单标志

	private Long parentSid;// 与集团品牌关联的sid

	private Long endBit;

	private String spell;// 拼音

	private Long optUserSid;// 操作人sid

	private String optRealName;// 操作人

	private Date optUpdateTimes;// 操作时间

	private String optUpdateTimeStr;// 页面展示用的，操作时间

	private Integer shopType;// 门店类型(0 北京，1 外埠 ，2 电商erp)默认为0

	private Integer brandType;// 品牌类型 0 集团品牌 1 门店品牌 （默认为0）

	private String brandDesc;// 品牌描述

	private Long shopSid;// 门店sid

	private String shopName;// 门店名称

	private Integer status = 0;// 有效标记：0有效，1无效（默认为0）

	private String brandNameEn;// 英文名称

	private String brandSpecialty;// 品牌特点

	private String brandSuitability;// 适合人群

	private Integer isDisplay = 0;// 是否展示（0：是，1：否，默认0）

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

	public String getBrandFatherName() {
		return brandFatherName;
	}

	public void setBrandFatherName(String brandFatherName) {
		this.brandFatherName = brandFatherName == null ? null : brandFatherName.trim();
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl == null ? null : pictureUrl.trim();
	}

	public String getBrandNameSecond() {
		return brandNameSecond;
	}

	public void setBrandNameSecond(String brandNameSecond) {
		this.brandNameSecond = brandNameSecond == null ? null : brandNameSecond.trim();
	}

	public Long getBrandActiveBit() {
		return brandActiveBit;
	}

	public void setBrandActiveBit(Long brandActiveBit) {
		this.brandActiveBit = brandActiveBit;
	}

	public String getBrandno() {
		return brandno;
	}

	public void setBrandno(String brandno) {
		this.brandno = brandno == null ? null : brandno.trim();
	}

	public String getBrandcorp() {
		return brandcorp;
	}

	public void setBrandcorp(String brandcorp) {
		this.brandcorp = brandcorp == null ? null : brandcorp.trim();
	}

	public String getBrandpic1() {
		return brandpic1;
	}

	public void setBrandpic1(String brandpic1) {
		this.brandpic1 = brandpic1 == null ? null : brandpic1.trim();
	}

	public String getBrandpic2() {
		return brandpic2;
	}

	public void setBrandpic2(String brandpic2) {
		this.brandpic2 = brandpic2 == null ? null : brandpic2.trim();
	}

	public Long getPhotoBlacklistBit() {
		return photoBlacklistBit;
	}

	public void setPhotoBlacklistBit(Long photoBlacklistBit) {
		this.photoBlacklistBit = photoBlacklistBit;
	}

	public Long getParentSid() {
		return parentSid;
	}

	public void setParentSid(Long parentSid) {
		this.parentSid = parentSid;
	}

	public String getSuperCode() {
		return superCode;
	}

	public void setSuperCode(String superCode) {
		this.superCode = superCode == null ? null : superCode.trim();
	}

	public Long getEndBit() {
		return endBit;
	}

	public void setEndBit(Long endBit) {
		this.endBit = endBit;
	}

	public String getSpell() {
		return spell;
	}

	public void setSpell(String spell) {
		this.spell = spell == null ? null : spell.trim();
	}

	public Long getOptUserSid() {
		return optUserSid;
	}

	public void setOptUserSid(Long optUserSid) {
		this.optUserSid = optUserSid;
	}

	public String getOptRealName() {
		return optRealName;
	}

	public void setOptRealName(String optRealName) {
		this.optRealName = optRealName == null ? null : optRealName.trim();
	}

	public Date getOptUpdateTimes() {
		return optUpdateTimes;
	}

	public void setOptUpdateTimes(Date optUpdateTimes) {
		this.optUpdateTimes = optUpdateTimes;
	}

	public String getOptUpdateTimeStr() {
		return optUpdateTimeStr;
	}

	public void setOptUpdateTimeStr(String optUpdateTimeStr) {
		this.optUpdateTimeStr = optUpdateTimeStr == null ? null : optUpdateTimeStr.trim();
	}

	public Integer getShopType() {
		return shopType;
	}

	public void setShopType(Integer shopType) {
		this.shopType = shopType;
	}

	public Integer getBrandType() {
		return brandType;
	}

	public void setBrandType(Integer brandType) {
		this.brandType = brandType;
	}

	public String getBrandDesc() {
		return brandDesc;
	}

	public void setBrandDesc(String brandDesc) {
		this.brandDesc = brandDesc == null ? null : brandDesc.trim();
	}

	public Long getShopSid() {
		return shopSid;
	}

	public void setShopSid(Long shopSid) {
		this.shopSid = shopSid;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getBrandNameEn() {
		return brandNameEn;
	}

	public void setBrandNameEn(String brandNameEn) {
		this.brandNameEn = brandNameEn == null ? null : brandNameEn.trim();
	}

	public Integer getIsDisplay() {
		return isDisplay;
	}

	public void setIsDisplay(Integer isDisplay) {
		this.isDisplay = isDisplay;
	}

	public String getBrandSpecialty() {
		return brandSpecialty;
	}

	public void setBrandSpecialty(String brandSpecialty) {
		this.brandSpecialty = brandSpecialty == null ? null : brandSpecialty.trim();
	}

	public String getBrandSuitability() {
		return brandSuitability;
	}

	public void setBrandSuitability(String brandSuitability) {
		this.brandSuitability = brandSuitability == null ? null : brandSuitability.trim();
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName == null ? null : shopName.trim();
	}

	@Override
	public String toString() {
		return "PcmBrandDto [sid=" + sid + ", brandSid=" + brandSid + ", brandName=" + brandName
				+ ", superCode=" + superCode + ", brandFatherName=" + brandFatherName
				+ ", pictureUrl=" + pictureUrl + ", brandNameSecond=" + brandNameSecond
				+ ", brandActiveBit=" + brandActiveBit + ", brandno=" + brandno + ", brandcorp="
				+ brandcorp + ", brandpic1=" + brandpic1 + ", brandpic2=" + brandpic2
				+ ", photoBlacklistBit=" + photoBlacklistBit + ", parentSid=" + parentSid
				+ ", endBit=" + endBit + ", spell=" + spell + ", optUserSid=" + optUserSid
				+ ", optRealName=" + optRealName + ", optUpdateTimes=" + optUpdateTimes
				+ ", optUpdateTimeStr=" + optUpdateTimeStr + ", shopType=" + shopType
				+ ", brandType=" + brandType + ", brandDesc=" + brandDesc + ", shopSid=" + shopSid
				+ ", shopName=" + shopName + ", status=" + status + ", brandNameEn=" + brandNameEn
				+ ", brandSpecialty=" + brandSpecialty + ", brandSuitability=" + brandSuitability
				+ ", isDisplay=" + isDisplay + "]";
	}

}
