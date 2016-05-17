package com.wangfj.product.brand.domain.vo;

import java.util.Date;

import com.wangfj.core.framework.base.dto.BaseDto;

/**
 * 集团品牌门店品牌的关系dto
 * 
 * @Class Name PcmBrandRelationDto
 * @Author wangx
 * @Create In 2015-8-6
 */
public class PcmBrandRelationDto extends BaseDto {

	private Long sid;

	private String brandSid;// 门店品牌sid

	private String brandRootSid;// 集团品牌sid

	private String optUser;// 操作人

	private Date optDates;// 操作时间

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
		this.brandSid = brandSid == null ? null : brandSid.trim();
	}

	public String getBrandRootSid() {
		return brandRootSid;
	}

	public void setBrandRootSid(String brandRootSid) {
		this.brandRootSid = brandRootSid == null ? null : brandRootSid.trim();
	}

	public String getOptUser() {
		return optUser;
	}

	public void setOptUser(String optUser) {
		this.optUser = optUser == null ? null : optUser.trim();
	}

	public Date getOptDates() {
		return optDates;
	}

	public void setOptDates(Date optDates) {
		this.optDates = optDates;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
