package com.wangfj.product.brand.domain.vo;

public class PcmShopBrandDto {

	private String parentSid;// 集团品牌的sid

	private String shopSid;// 门店的sid

	private String shopCode;// 门店编码

	private Integer currentPage;

	private Integer pageSize;

	private Integer start;

	private Integer limit;

	public String getParentSid() {
		return parentSid;
	}

	public void setParentSid(String parentSid) {
		this.parentSid = parentSid == null ? null : parentSid.trim();
	}

	public String getShopSid() {
		return shopSid;
	}

	public void setShopSid(String shopSid) {
		this.shopSid = shopSid == null ? null : shopSid.trim();
	}

	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode == null ? null : shopCode.trim();
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
		return "PcmShopBrandDto [parentSid=" + parentSid + ", shopSid=" + shopSid + ", shopCode="
				+ shopCode + ", currentPage=" + currentPage + ", pageSize=" + pageSize + ", start="
				+ start + ", limit=" + limit + "]";
	}

}
