package com.wangfj.product.core.domain.dto;

import java.util.Date;

/**
 * 分页查询显示属性
 * 
 * @Class Name PcmSelectDictDto
 * @Author niuzf
 * @Create In 2015-9-16
 */
public class PcmSelectDictDto {
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

	private String pidName;

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

	public String getPidName() {
		return pidName;
	}

	public void setPidName(String pidName) {
		this.pidName = pidName;
	}

}
