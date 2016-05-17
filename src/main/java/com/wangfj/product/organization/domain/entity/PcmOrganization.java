package com.wangfj.product.organization.domain.entity;

import java.util.Date;

import com.wangfj.core.framework.base.entity.BaseEntity;

/**
 * 
 * @Class Name PcmOrganization
 * @Author yedong
 * @Create In 2015年7月3日
 */
public class PcmOrganization extends BaseEntity {

	/**
	 * @Field long serialVersionUID
	 */
	private static final long serialVersionUID = 9108321506916933526L;

	private Long sid;

	private String parentSid; /* 所属上级sid */

	private Long groupSid; /* 所属集团sid */

	private String organizationName; /* 机构名称 */

	private String organizationCode; /* 机构编码 */

	private Integer organizationType; /* 机构类别 */

	private Integer organizationStatus; /* 机构状态 */

	private String createName; /* 创建人 */

	private String updateName; /* 修改人 */

	private Date createTime; /* 创建时间 */

	private Date updateTime; /* 修改时间 */

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
		this.parentSid = parentSid;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName == null ? null : organizationName.trim();
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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
		this.shippingPoint = shippingPoint;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public Long getGroupSid() {
		return groupSid;
	}

	public void setGroupSid(Long groupSid) {
		this.groupSid = groupSid;
	}

	@Override
	public String toString() {
		return "PcmOrganization [sid=" + sid + ", parentSid=" + parentSid + ", groupSid="
				+ groupSid + ", organizationName=" + organizationName + ", organizationCode="
				+ organizationCode + ", organizationType=" + organizationType
				+ ", organizationStatus=" + organizationStatus + ", createName=" + createName
				+ ", updateName=" + updateName + ", createTime=" + createTime + ", updateTime="
				+ updateTime + ", storeType=" + storeType + ", shippingPoint=" + shippingPoint
				+ ", areaCode=" + areaCode + "]";
	}

}