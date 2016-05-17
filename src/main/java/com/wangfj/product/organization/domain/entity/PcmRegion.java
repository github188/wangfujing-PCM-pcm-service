package com.wangfj.product.organization.domain.entity;

import com.wangfj.core.framework.base.entity.BaseEntity;

public class PcmRegion extends BaseEntity {

	private Long sid;

	/* 区域邮编 */
	private String regionCode;

	private String regionInnerCode;// 行政区域内部编码

	/* 区域名称 */
	private String regionName;

	/* 上级编码 */
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
		this.regionCode = regionCode == null ? null : regionCode.trim();
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName == null ? null : regionName.trim();
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
		this.regionNameEn = regionNameEn == null ? null : regionNameEn.trim();
	}

	public String getRegionShortnameEn() {
		return regionShortnameEn;
	}

	public void setRegionShortnameEn(String regionShortnameEn) {
		this.regionShortnameEn = regionShortnameEn == null ? null : regionShortnameEn.trim();
	}

	public String getRegionInnerCode() {
		return regionInnerCode;
	}

	public void setRegionInnerCode(String regionInnerCode) {
		this.regionInnerCode = regionInnerCode;
	}

	@Override
	public String toString() {
		return "PcmRegion [sid=" + sid + ", regionCode=" + regionCode + ", regionInnerCode="
				+ regionInnerCode + ", regionName=" + regionName + ", parentId=" + parentId
				+ ", regionLevel=" + regionLevel + ", regionOrder=" + regionOrder
				+ ", regionNameEn=" + regionNameEn + ", regionShortnameEn=" + regionShortnameEn
				+ "]";
	}
}