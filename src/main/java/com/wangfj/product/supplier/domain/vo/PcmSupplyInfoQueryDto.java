package com.wangfj.product.supplier.domain.vo;

public class PcmSupplyInfoQueryDto {

	private String sid;// 供应商的sid

	private String supplyName; // 供应商名称

	private String shopSid;// 门店的sid

	private String shopCode;// 门店编码(组织结构编码)

	private String supplyCode; // 供应商编码

	private String supplyType;// 供应商类型:0，门店供应商；1，电商供应商；2，集团供应商（默认0）

	private String status;// 供应商状态：Y正常；T未批准；N终止；L待审批；3淘汰；4停货；5停款；6冻结。

	private String shoppeSid;// 专柜sid

	private String shoppeCode;// 专柜编码

	private String apartOrder;// 拆单标识0: 不是 1：是

	private Integer currentPage = 1;// 当前页数

	private Integer pageSize = 10;// 每页大小

	private Integer start;

	private Integer limit;

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getSupplyName() {
		return supplyName;
	}

	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}

	public String getShopSid() {
		return shopSid;
	}

	public void setShopSid(String shopSid) {
		this.shopSid = shopSid;
	}

	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public String getSupplyCode() {
		return supplyCode;
	}

	public void setSupplyCode(String supplyCode) {
		this.supplyCode = supplyCode;
	}

	public String getSupplyType() {
		return supplyType;
	}

	public void setSupplyType(String supplyType) {
		this.supplyType = supplyType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getShoppeSid() {
		return shoppeSid;
	}

	public void setShoppeSid(String shoppeSid) {
		this.shoppeSid = shoppeSid;
	}

	public String getShoppeCode() {
		return shoppeCode;
	}

	public void setShoppeCode(String shoppeCode) {
		this.shoppeCode = shoppeCode;
	}

	public String getApartOrder() {
		return apartOrder;
	}

	public void setApartOrder(String apartOrder) {
		this.apartOrder = apartOrder;
	}

	@Override
	public String toString() {
		return "PcmSupplyInfoQueryDto [sid=" + sid + ", supplyName=" + supplyName + ", shopSid="
				+ shopSid + ", shopCode=" + shopCode + ", supplyCode=" + supplyCode
				+ ", supplyType=" + supplyType + ", status=" + status + ", shoppeSid=" + shoppeSid
				+ ", shoppeCode=" + shoppeCode + ", apartOrder=" + apartOrder + ", currentPage="
				+ currentPage + ", pageSize=" + pageSize + ", start=" + start + ", limit=" + limit
				+ "]";
	}

}
