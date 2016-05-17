package com.wangfj.product.core.domain.dto;

import java.util.Date;

/**
 * 分页查询查询dto
 * 
 * @Class Name PcmGetDictDto
 * @Author niuzf
 * @Create In 2015-9-17
 */
public class PcmGetDictDto {
	private Long sid;

	private String code;

	private String name;
	/**
	 * 父sid
	 */
	private Long pid;
	/**
	 * 顺序
	 */
	private Integer sort;

	private Integer status;

	private Date createDate;

	/* 当前页数 */
	private Integer currentPage;

	/* 每页显示数量 */
	private Integer pageSize;

	/* 索引 */
	private Integer start;

	/* 查询条数 */
	private Integer limit;

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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
