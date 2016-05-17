package com.wangfj.product.brand.domain.entity;

import java.util.Date;

import com.wangfj.core.framework.base.entity.BaseEntity;

public class PcmBrandRelation extends BaseEntity {

	private Long sid;

	private String brandSid;// 门店品牌sid

	private String brandRootSid;// 集团品牌sid

	private String optUser;// 操作人

	private Date optDate; // 操作时间

	private Integer status = 0;// 有效标记：0有效，1无效（默认为0）

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
		this.brandSid = brandSid;
	}

	public String getBrandRootSid() {
		return brandRootSid;
	}

	public void setBrandRootSid(String brandRootSid) {
		this.brandRootSid = brandRootSid;
	}

	public String getOptUser() {
		return optUser;
	}

	public void setOptUser(String optUser) {
		this.optUser = optUser == null ? null : optUser.trim();
	}

	public Date getOptDate() {
		return optDate;
	}

	public void setOptDate(Date optDate) {
		this.optDate = optDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}