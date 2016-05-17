package com.wangfj.product.organization.domain.vo;

public class PcmOrgPartInfoDto {

	private Long sid;

	private Long groupSid; /* 所属集团sid */

	private String organizationName; /* 机构名称 */

	private String organizationCode; /* 机构编码 */

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName == null ? null : organizationName.trim();
	}

	public Long getGroupSid() {
		return groupSid;
	}

	public void setGroupSid(Long groupSid) {
		this.groupSid = groupSid;
	}

	public String getOrganizationCode() {
		return organizationCode;
	}

	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}

	@Override
	public String toString() {
		return "PcmOrgPartInfoDto [sid=" + sid + ", groupSid=" + groupSid + ", organizationName="
				+ organizationName + ", organizationCode=" + organizationCode + "]";
	}

}