package com.wangfj.product.price.domain.vo;

import com.wangfj.core.framework.base.dto.BaseDto;

public class SelectPcmPriceLimitDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long sid;

    private String shopSid;//门店号
	
	private Integer currentPage = 1;// 当前页数

	private Integer pageSize = 10;// 每页大小

	/** 当前页的起始索引,从0开始 */
	private Integer start = 0;

	/** mysql 分页 */
	private Integer limit = 10;

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getShopSid() {
		return shopSid;
	}

	public void setShopSid(String shopSid) {
		this.shopSid = shopSid;
	}

	public Integer getCurrentPage() {
		if (currentPage == 0)
			currentPage = 1;
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		if (pageSize == 0)
			pageSize = 10;
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getStart() {
		this.start = 0;
		if (this.currentPage > 1 && this.pageSize > 0) {
			this.start = (this.currentPage - 1) * this.pageSize;
		}
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		if (this.pageSize != null && this.pageSize != 0)
			this.limit = this.pageSize;
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}
}
