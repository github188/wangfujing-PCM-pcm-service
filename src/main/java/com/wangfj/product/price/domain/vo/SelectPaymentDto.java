package com.wangfj.product.price.domain.vo;

/**
 * 门店支付方式查询参数
 * 
 * @Class Name SelectPaymentDto
 * @Author kongqf
 * @Create In 2015年8月7日
 */
public class SelectPaymentDto {

	/**
	 * 中台门店编码
	 */
	private String storeCode;

	/**
	 * 二级支付方式编码
	 */
	private String code;

	/**
	 * 有效状态 0：有效；1:无效
	 */
	private String status;

	/**
	 * 当前页
	 */
	private Integer currentPage = 1;

	/**
	 * 单页行数
	 */
	private Integer pageSize = 20;

	/**
	 * 当前页的起始索引,从1开始
	 */
	private Integer start = 0;

	/**
	 * 当前页结束索引
	 */
	private Integer limit = 20;

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
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
			pageSize = 20;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
