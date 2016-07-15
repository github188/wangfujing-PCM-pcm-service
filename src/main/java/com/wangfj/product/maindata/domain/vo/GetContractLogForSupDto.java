/**
 * @Probject Name: pcm-service
 * @Path: com.wangfj.product.maindata.domain.voGetContractLogForSupDto.java
 * @Create By wangc
 * @Create In 2016-3-30 下午3:08:40
 */
package com.wangfj.product.maindata.domain.vo;

import java.util.List;

/**
 * 供應商查詢要约信息参数类
 * @Class Name GetContractLogForSupDto
 * @Author  wangc
 * @Create In 2016-3-30
 */
public class GetContractLogForSupDto {

	private String supplyCode; //供应商编码
	
	private List<String> supplyCodeList;//供应商编码列表
	
	private String supplyName; //供应商名称
	
	private String manageType; //经营方式
	
	private String contractCode;  //合同编号
	
	private String storeCode; //门店编码

	private Integer currentPage;

	private Integer pageSize;

	private Integer start;

	private Integer limit;
	
	
	
	

	

	public List<String> getSupplyCodeList() {
		return supplyCodeList;
	}

	public void setSupplyCodeList(List<String> supplyCodeList) {
		this.supplyCodeList = supplyCodeList;
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

	public String getSupplyCode() {
		return supplyCode;
	}

	public void setSupplyCode(String supplyCode) {
		this.supplyCode = supplyCode;
	}

	public String getSupplyName() {
		return supplyName;
	}

	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}

	public String getManageType() {
		return manageType;
	}

	public void setManageType(String manageType) {
		this.manageType = manageType;
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
	
	@Override
	public String toString() {
		return "GetContractLogResultForSupDto{" + " contractCode='" + contractCode + '\''
				+ ", supplyName='" + supplyName + '\''  + ", manageType=" + manageType
				
				+ '}';
	}
	
}
