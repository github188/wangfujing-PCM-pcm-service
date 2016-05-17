package com.wangfj.product.price.domain.vo;

import com.wangfj.core.framework.base.dto.BaseDto;

/**
 * 一级和二级支付方式查询条件
 * 
 * @Class Name SelectPaymentTypeDto
 * @Author kongqf
 * @Create In 2015年8月8日
 */
public class SelectPaymentTypeDto extends BaseDto {
	/**
	 * 中台门店编码
	 */
	private String storeCode;

	/**
	 * 付款方式编码
	 */
	private String payCode;

	/**
	 * 付款方式名称
	 */
	private String name;

	/**
	 * 上一级编码
	 */
	private String parentCode;

	/**
	 * 银行识别码
	 */
	public String bankBIN;

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

	public String getBankBIN() {
		return bankBIN;
	}

	public void setBankBIN(String bankBIN) {
		this.bankBIN = bankBIN;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getPayCode() {
		return payCode;
	}

	public void setPayCode(String payCode) {
		this.payCode = payCode;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

}
