package com.wangfj.product.maindata.domain.vo;

/**
 * 色系DTO
 * 
 * @Class Name ColorDictDto
 * @Author zhangxy
 * @Create In 2015年7月29日
 */
public class ColorDictDto {
	private Long sid;
	/**
	 * 色系
	 */
	private String colorName;
	/**
	 * 色系描述
	 */
	private String colorAlias;
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

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public String getColorAlias() {
		return colorAlias;
	}

	public void setColorAlias(String colorAlias) {
		this.colorAlias = colorAlias;
	}

	@Override
	public String toString() {
		return "ColorDictDto [sid=" + sid + ", colorName=" + colorName + ", colorAlias="
				+ colorAlias + "]";
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
