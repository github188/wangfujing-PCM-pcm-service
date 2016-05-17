package com.wangfj.product.maindata.domain.vo;

/**
 * Created by wangxuan on 2016-02-16 0016.
 */
public class PcmProductDescQueryDto {

	private Long sid;

	private String color;// 色系

	private String productSid;// 产品编码

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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getProductSid() {
		return productSid;
	}

	public void setProductSid(String productSid) {
		this.productSid = productSid;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
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

	@Override
	public String toString() {
		return "PcmProductDescQueryDto{" + "sid=" + sid + ", color='" + color + '\''
				+ ", productSid='" + productSid + '\'' + ", currentPage=" + currentPage
				+ ", pageSize=" + pageSize + ", start=" + start + ", limit=" + limit + '}';
	}
}
