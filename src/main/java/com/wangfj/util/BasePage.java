package com.wangfj.util;

/**
 * 分页基础类
 * 
 * @Class Name BasePage
 * @Author kongqf
 * @Create In 2016年5月25日
 */
public class BasePage {

	/**
	 * 当前页
	 */
	private Integer currentPage = 1;

	/**
	 * 单页行数 默认200
	 */
	private Integer pageSize = 200;

	/**
	 * 当前页的起始索引,从1开始
	 */
	private Integer start;

	/**
	 * 当前页结束索引
	 */
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
		if (this.pageSize != null)
			this.limit = this.pageSize;
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	@Override
	public String toString() {
		return "BasePage [currentPage=" + currentPage + ", pageSize=" + pageSize + ", start="
				+ start + ", limit=" + limit + "]";
	}

}
