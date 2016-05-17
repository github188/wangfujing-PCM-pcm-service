package com.wangfj.product.core.domain.dto;

import java.util.Date;

public class PcmDictDto {
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

	public Date getCreatedate() {
		return createDate;
	}

	public void setCreatedate(Date createdate) {
		this.createDate = createdate;
	}

	@Override
	public String toString() {
		return "PcmDictDto [sid=" + sid + ", code=" + code + ", name=" + name + ", pid=" + pid
				+ ", sort=" + sort + ", status=" + status + ", createDate=" + createDate + "]";
	}

}
