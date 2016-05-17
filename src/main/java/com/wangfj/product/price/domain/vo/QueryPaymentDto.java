package com.wangfj.product.price.domain.vo;

import com.wangfj.core.framework.base.dto.BaseDto;

/**
 * 支付方式查询条件
 * 
 * @Class Name QueryPaymentDto
 * @Author kongqf
 * @Create In 2015年8月7日
 */
public class QueryPaymentDto extends BaseDto {
	/**
	 * SID
	 */
	private Long sid;
	/**
	 * 支付编码
	 */
	public String payCode;
	/**
	 * 支付名称
	 */
	public String name;
	/**
	 * 上级支付编码
	 */
	public String parentCode;
	/**
	 * 银行识别码
	 */
	public String bankBIN;

	/**
	 * 当前页
	 */
	private Integer currentPage;

	/**
	 * 单页行数
	 */
	private Integer pageSize;

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
		if (this.currentPage > 0 && this.pageSize > 0) {
			this.limit = this.currentPage * this.pageSize;
		}
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getPayCode() {
		return payCode;
	}

	public void setPayCode(String payCode) {
		this.payCode = payCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getBankBIN() {
		return bankBIN;
	}

	public void setBankBIN(String bankBIN) {
		this.bankBIN = bankBIN;
	}
}
