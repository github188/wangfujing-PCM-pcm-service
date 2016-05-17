package com.wangfj.product.organization.domain.vo;

import com.wangfj.core.framework.base.dto.BaseDto;

/**
 * 
 * @Class Name PcmBusinessTypeDictDto
 * @Author yedong
 * @Create In 2015年7月3日
 */
public class PcmBusinessTypeDictDto extends BaseDto {
	private Integer sid;

	private String businessCode; /* 经营方式码 */

	private String businessName;

	private Long optUserSid;
	private Integer currentPage;
	private Integer pageSize;
	private Integer start;
	private Integer limit;

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getBusinessCode() {
		return businessCode;
	}

	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName == null ? null : businessName.trim();
	}

	public Long getOptUserSid() {
		return optUserSid;
	}

	public void setOptUserSid(Long optUserSid) {
		this.optUserSid = optUserSid;
	}

	@Override
	public String toString() {
		return "PcmBusinessTypeDictDto [sid=" + sid + ", businessCode=" + businessCode
				+ ", businessName=" + businessName + ",  optUserSid=" + optUserSid + "]";
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

}
