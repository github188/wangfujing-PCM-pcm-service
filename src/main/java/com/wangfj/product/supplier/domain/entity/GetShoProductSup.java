/**
 * @Probject Name: pcm-service-outer
 * @Path: com.wangfj.product.supplier.domain.voGetShoProductSupDto.java
 * @Create By wangc
 * @Create In 2016-3-3 下午4:39:03
 * TODO
 */
package com.wangfj.product.supplier.domain.entity;

/**
 * @Class Name GetShoProductSupDto
 * @Author wangc
 * @Create In 2016-3-3
 */
public class GetShoProductSup {

	private String supplierCode;//供应商编码
	private String counterProCode;//专柜商品编码
	private String counterName;//专柜名称
	private String countProName;//商品名称
	private String productType;//商品类型
	private String operaterMode;//经营方式
	private String busniessType;//业态
	private String colorCode;//色系
	private String isSell;//上架状态
	private String storeName;//门店名称
	private Integer currentPage = 1;// 当前页数

	private Integer pageSize = 10;// 每页大小

	/** 当前页的起始索引,从0开始 */
	private int start = 0;

	/** mysql 分页 */
	private int limit = 10;
	
	
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getCounterName() {
		return counterName;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	@Override
	public String toString() {
		return "GetShoProductSup : "+"supplierCode = "+supplierCode+",counterProCode = "+counterProCode+",countName ="
				+counterName+",countProName="+countProName+",productType="+productType+",operaterMode="+operaterMode+",busniessType="+
				busniessType+",colorCode="+colorCode+",isSell="+isSell;
	}
	public Integer getStart() {
		this.start = 0;
		if (this.currentPage > 1 && this.pageSize > 0) {
			this.start = (this.currentPage - 1) * this.pageSize;
		}
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		if (this.pageSize != null)
			this.limit = this.pageSize;
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public String getIsSell() {
		return isSell;
	}
	public void setIsSell(String isSell) {
		this.isSell = isSell;
	}
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	public String getCounterProCode() {
		return counterProCode;
	}
	public void setCounterProCode(String counterProCode) {
		this.counterProCode = counterProCode;
	}
	public String getCountName() {
		return counterName;
	}
	public void setCounterName(String counterName) {
		this.counterName = counterName;
	}
	public String getCountProName() {
		return countProName;
	}
	public void setCountProName(String countProName) {
		this.countProName = countProName;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getOperaterMode() {
		return operaterMode;
	}
	public void setOperaterMode(String operaterMode) {
		this.operaterMode = operaterMode;
	}
	public String getBusniessType() {
		return busniessType;
	}
	public void setBusniessType(String busniessType) {
		this.busniessType = busniessType;
	}
	public String getColorCode() {
		return colorCode;
	}
	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
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
	
}
