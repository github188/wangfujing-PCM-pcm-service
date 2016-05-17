package com.wangfj.product.organization.domain.vo;

public class PcmRegionQueryDto {

	private Long sid;

	private String regionName;

	private String regionCode;

	private String regionInnerCode;

	private Long parentId;

	private Integer regionLevel;// 等级（0 省，1 市，2 区，3 县，4 镇 ）

	private Integer currentPage = 1;

	private Integer pageSize = 10;

	private Integer start;

	private Integer limit;

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
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

	public Integer getRegionLevel() {
		return regionLevel;
	}

	public void setRegionLevel(Integer regionLevel) {
		this.regionLevel = regionLevel;
	}

	public String getRegionInnerCode() {
		return regionInnerCode;
	}

	public void setRegionInnerCode(String regionInnerCode) {
		this.regionInnerCode = regionInnerCode;
	}

	@Override
	public String toString() {
		return "PcmRegionQueryDto [sid=" + sid + ", regionName=" + regionName + ", regionCode="
				+ regionCode + ", regionInnerCode=" + regionInnerCode + ", parentId=" + parentId
				+ ", regionLevel=" + regionLevel + ", currentPage=" + currentPage + ", pageSize="
				+ pageSize + ", start=" + start + ", limit=" + limit + "]";
	}

}
