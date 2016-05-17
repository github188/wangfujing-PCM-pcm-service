package com.wangfj.product.organization.domain.vo;

import java.util.Date;

/**
 * 组织机构DTO
 * 
 * @Class Name PcmOrganizationResultDto
 * @Author wangx
 * @Create In 2015-8-18
 */
public class PcmOrganizationResultDto {

	private Long sid;

	private String parentSid; /* 所属上级sid */

	private Long groupSid; /* 所属集团sid */

	private String organizationName; /* 机构名称 */

	private String organizationCode; /* 机构编码 */

	private String organizationFatherName; /* 上级机构名称 */

	private String organizationFatherCode; /* 上级机构编码 */

	private Integer organizationType; /* 机构类别 */

	private Integer organizationStatus; /* 机构状态 */

	private String createName; /* 创建人 */

	private String updateName; /* 修改人 */

	private Date createTimes; /* 创建时间 */

	private String createTimeStr; /* 创建时间 */

	private Date updateTimes; /* 修改时间 */

	private String updateTimeStr; /* 修改时间 */

	/*
	 * 门店类型： 1电商 2北京 3其它门店（指明门店类型） 4 集货仓 5 门店物流室
	 */
	private Integer storeType;

	/*
	 * 集货地点编码(对于门店非空，表明对应的集货仓地点编码， 例如电商对应电商百子湾集货仓的组织机构编码。 如果门店没有集货仓，则传自己的组织机构编码)
	 */
	private String shippingPoint;
	/*
	 * 门店所属城市在省市区字典中的编码 （业务要求配置到城市一级， 但是并不需要系统限制和校验）
	 */
	private String areaCode;

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getParentSid() {
		return parentSid;
	}

	public void setParentSid(String parentSid) {
		this.parentSid = parentSid == null ? null : parentSid.trim();
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName == null ? null : organizationName.trim();
	}

	public String getOrganizationFatherName() {
		return organizationFatherName;
	}

	public void setOrganizationFatherName(String organizationFatherName) {
		this.organizationFatherName = organizationFatherName == null ? null
				: organizationFatherName.trim();
	}

	public String getOrganizationCode() {
		return organizationCode;
	}

	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode == null ? null : organizationCode.trim();
	}

	public Integer getOrganizationType() {
		return organizationType;
	}

	public void setOrganizationType(Integer organizationType) {
		this.organizationType = organizationType;
	}

	public Integer getOrganizationStatus() {
		return organizationStatus;
	}

	public void setOrganizationStatus(Integer organizationStatus) {
		this.organizationStatus = organizationStatus;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName == null ? null : createName.trim();
	}

	public String getUpdateName() {
		return updateName;
	}

	public void setUpdateName(String updateName) {
		this.updateName = updateName == null ? null : updateName.trim();
	}

	public Date getCreateTimes() {
		return createTimes;
	}

	public void setCreateTimes(Date createTimes) {
		this.createTimes = createTimes;
	}

	public Date getUpdateTimes() {
		return updateTimes;
	}

	public void setUpdateTimes(Date updateTimes) {
		this.updateTimes = updateTimes;
	}

	public Integer getStoreType() {
		return storeType;
	}

	public void setStoreType(Integer storeType) {
		this.storeType = storeType;
	}

	public String getShippingPoint() {
		return shippingPoint;
	}

	public void setShippingPoint(String shippingPoint) {
		this.shippingPoint = shippingPoint == null ? null : shippingPoint.trim();
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode == null ? null : areaCode.trim();
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr == null ? null : createTimeStr.trim();
	}

	public String getUpdateTimeStr() {
		return updateTimeStr;
	}

	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr == null ? null : updateTimeStr.trim();
	}

	public String getOrganizationFatherCode() {
		return organizationFatherCode;
	}

	public void setOrganizationFatherCode(String organizationFatherCode) {
		this.organizationFatherCode = organizationFatherCode == null ? null
				: organizationFatherCode.trim();
	}

	public Long getGroupSid() {
		return groupSid;
	}

	public void setGroupSid(Long groupSid) {
		this.groupSid = groupSid;
	}

	@Override
	public String toString() {
		return "PcmOrganizationResultDto [sid=" + sid + ", parentSid=" + parentSid + ", groupSid="
				+ groupSid + ", organizationName=" + organizationName + ", organizationCode="
				+ organizationCode + ", organizationFatherName=" + organizationFatherName
				+ ", organizationFatherCode=" + organizationFatherCode + ", organizationType="
				+ organizationType + ", organizationStatus=" + organizationStatus + ", createName="
				+ createName + ", updateName=" + updateName + ", createTimes=" + createTimes
				+ ", createTimeStr=" + createTimeStr + ", updateTimes=" + updateTimes
				+ ", updateTimeStr=" + updateTimeStr + ", storeType=" + storeType
				+ ", shippingPoint=" + shippingPoint + ", areaCode=" + areaCode + "]";
	}

}