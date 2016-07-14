/**
 * @Probject Name: pcm-service
 * @Path: com.wangfj.product.maindata.domain.voGetContractLogResultForSupDto.java
 * @Create By wangc
 * @Create In 2016-3-30 下午2:07:53
 */
package com.wangfj.product.maindata.domain.vo;

/**
 * 供应商查询要约信息封装类
 * @Class Name GetContractLogResultForSupDto
 * @Author  wangc
 * @Create In 2016-3-30
 */
public class GetContractLogResultForSupDto {

	private String contractCode; //合同编号
	
	private String supplyName;//供应商名称
	
	private String storeName;//门店名称
	
	private String businessType;//业态
	
	private String manageType;//经营方式
	
	private String buyType;//采购类别
	
	private String operMode;//管理方式
	
	private String inputTax;//进项税率
	
	private String outputTax;//销项税率
	
	private String commissionRate;//要约扣率
	
	private String categoryCode;//管理分类编码
	
	private String categoryName;//管理分类名称

	@Override
	public String toString() {
		return "GetContractLogResultForSupDto{" + " contractCode='" + contractCode + '\''
				+ ", supplyName='" + supplyName + '\'' + ", storeName='" + storeName + '\''
				+ ", businessType='" + businessType + '\'' + ", manageType=" + manageType
				+ ", buyType=" + buyType + ", operMode=" + operMode + ", inputTax=" + inputTax + ", outputTax=" + outputTax
				+ ", commissionRate=" + commissionRate
				+ ", categoryCode=" + categoryCode
				+ ", categoryName=" + categoryName
				+ '}';
	}
	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	public String getSupplyName() {
		return supplyName;
	}

	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getManageType() {
		return manageType;
	}

	public void setManageType(String manageType) {
		this.manageType = manageType;
	}

	public String getBuyType() {
		return buyType;
	}

	public void setBuyType(String buyType) {
		this.buyType = buyType;
	}

	public String getOperMode() {
		return operMode;
	}

	public void setOperMode(String operMode) {
		this.operMode = operMode;
	}

	public String getInputTax() {
		return inputTax;
	}

	public void setInputTax(String inputTax) {
		this.inputTax = inputTax;
	}

	public String getOutputTax() {
		return outputTax;
	}

	public void setOutputTax(String outputTax) {
		this.outputTax = outputTax;
	}

	public String getCommissionRate() {
		return commissionRate;
	}

	public void setCommissionRate(String commissionRate) {
		this.commissionRate = commissionRate;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
	
}
