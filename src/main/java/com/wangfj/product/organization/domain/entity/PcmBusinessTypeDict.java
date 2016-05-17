package com.wangfj.product.organization.domain.entity;

import java.util.Date;

import com.wangfj.core.framework.base.entity.BaseEntity;

/**
 * 
 * @Class Name PcmBusinessTypeDict
 * @Author yedong
 * @Create In 2015年7月3日
 */
public class PcmBusinessTypeDict extends BaseEntity {

	private Integer sid;

	private String businessCode; /* 经营方式码 */

	private String businessName; /* 经营方式 */

	private Date createTime; /* 创建时间 */

	private Long optUserSid; /* 创建人 */
	private Integer status; /* 是否启用 */

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName == null ? null : businessName.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getBusinessCode() {
		return businessCode;
	}

	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
	}

	public Long getOptUserSid() {
		return optUserSid;
	}

	public void setOptUserSid(Long optUserSid) {
		this.optUserSid = optUserSid;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}