package com.wangfj.product.organization.domain.vo;

public class PcmRegionResultDto {

	private Long sid;

	/* 区域邮编 */
	private String regionCode;

	private String regionInnerCode;// 行政区域内部编码

	/* 区域名称 */
	private String regionName;

	/* 上级sid */
	private Long parentId;

	/* 等级 */
	private Integer regionLevel;

	/* 顺序 */
	private Integer regionOrder;

	/* 区域名称拼音 */
	private String regionNameEn;

	/* 区域名称拼音简称 */
	private String regionShortnameEn;

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Integer getRegionLevel() {
		return regionLevel;
	}

	public void setRegionLevel(Integer regionLevel) {
		this.regionLevel = regionLevel;
	}

	public Integer getRegionOrder() {
		return regionOrder;
	}

	public void setRegionOrder(Integer regionOrder) {
		this.regionOrder = regionOrder;
	}

	public String getRegionNameEn() {
		return regionNameEn;
	}

	public void setRegionNameEn(String regionNameEn) {
		this.regionNameEn = regionNameEn;
	}

	public String getRegionShortnameEn() {
		return regionShortnameEn;
	}

	public void setRegionShortnameEn(String regionShortnameEn) {
		this.regionShortnameEn = regionShortnameEn;
	}

	public String getRegionInnerCode() {
		return regionInnerCode;
	}

	public void setRegionInnerCode(String regionInnerCode) {
		this.regionInnerCode = regionInnerCode;
	}

	@Override
	public String toString() {
		return "PcmRegionResultDto [sid=" + sid + ", regionCode=" + regionCode
				+ ", regionInnerCode=" + regionInnerCode + ", regionName=" + regionName
				+ ", parentId=" + parentId + ", regionLevel=" + regionLevel + ", regionOrder="
				+ regionOrder + ", regionNameEn=" + regionNameEn + ", regionShortnameEn="
				+ regionShortnameEn + "]";
	}

}
