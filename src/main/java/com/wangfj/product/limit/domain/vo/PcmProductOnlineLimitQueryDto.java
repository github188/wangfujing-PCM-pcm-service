package com.wangfj.product.limit.domain.vo;

import com.wangfj.core.framework.base.dto.BaseDto;

public class PcmProductOnlineLimitQueryDto extends BaseDto {

	/**
	 * @Field long serialVersionUID
	 */
	private static final long serialVersionUID = 7069378539086593538L;

	private Long brandSid;// 品牌sid

	private Long categorySid;// 分类sid

	private Integer status;// 阀值状态:0启用，1禁用

	private Integer currentPage;

	private Integer pageSize;

	private Integer start;

	private Integer limit;

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

	public Long getBrandSid() {
		return brandSid;
	}

	public void setBrandSid(Long brandSid) {
		this.brandSid = brandSid;
	}

	public Long getCategorySid() {
		return categorySid;
	}

	public void setCategorySid(Long categorySid) {
		this.categorySid = categorySid;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "PcmProductOnlineLimitQueryDto [brandSid=" + brandSid + ", categorySid="
				+ categorySid + ", status=" + status + ", currentPage=" + currentPage
				+ ", pageSize=" + pageSize + ", start=" + start + ", limit=" + limit + "]";
	}

}
