package com.wangfj.product.maindata.domain.vo;

import com.wangfj.core.framework.base.entity.BaseEntity;

/**
 * 包装单位字典表
 * 
 * @Class Name PcmPackUnitDict
 * @Author zhangxy
 * @Create In 2015年7月2日
 */
public class PcmPackUnitDto extends BaseEntity {
	private Long sid;
	/**
	 * 包装单位
	 */
	private String unitName;
	/**
	 * 包装描述
	 */
	private String unitDesc;
	private Integer currentPage;
	private Integer pageSize;
	private Integer start;
	private Integer limit;

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName == null ? null : unitName.trim();
	}

	public String getUnitDesc() {
		return unitDesc;
	}

	public void setUnitDesc(String unitDesc) {
		this.unitDesc = unitDesc == null ? null : unitDesc.trim();
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