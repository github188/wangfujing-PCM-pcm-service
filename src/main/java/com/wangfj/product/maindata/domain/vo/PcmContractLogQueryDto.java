package com.wangfj.product.maindata.domain.vo;

public class PcmContractLogQueryDto {

	private Long sid;
	/**
	 * 要约号
	 */
	private String contractCode;
	/**
	 * 门店编码
	 */
	private String storeCode;
	/**
	 * 供应商编码
	 */
	private String supplyCode;
	/**
	 * 经营方式
	 */
	private String manageType;

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

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getSupplyCode() {
		return supplyCode;
	}

	public void setSupplyCode(String supplyCode) {
		this.supplyCode = supplyCode;
	}

	public String getManageType() {
		return manageType;
	}

	public void setManageType(String manageType) {
		this.manageType = manageType;
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

	@Override
	public String toString() {
		return "PcmContractLogQueryDto{" + "sid=" + sid + ", contractCode='" + contractCode + '\''
				+ ", storeCode='" + storeCode + '\'' + ", supplyCode='" + supplyCode + '\''
				+ ", manageType='" + manageType + '\'' + ", currentPage=" + currentPage
				+ ", pageSize=" + pageSize + ", start=" + start + ", limit=" + limit + '}';
	}
}
