package com.wangfj.product.maindata.domain.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.wangfj.core.framework.base.entity.BaseEntity;

public class PcmContractLog extends BaseEntity {
	private Long sid;

	private String contractCode;

	private String storeCode;

	private String supplyCode;

	private Integer manageType;

	private Integer buyType;

	private Integer operMode;

	private Integer businessType;

	private BigDecimal inputTax;

	private BigDecimal outputTax;

	private BigDecimal commissionRate;

	private Date optTime;

	private String col1;

	private String col2;

	private String col3;

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

	public Integer getManageType() {
		return manageType;
	}

	public void setManageType(Integer manageType) {
		this.manageType = manageType;
	}

	public Integer getBuyType() {
		return buyType;
	}

	public void setBuyType(Integer buyType) {
		this.buyType = buyType;
	}

	public Integer getOperMode() {
		return operMode;
	}

	public void setOperMode(Integer operMode) {
		this.operMode = operMode;
	}

	public Integer getBusinessType() {
		return businessType;
	}

	public void setBusinessType(Integer businessType) {
		this.businessType = businessType;
	}

	public BigDecimal getInputTax() {
		return inputTax;
	}

	public void setInputTax(BigDecimal inputTax) {
		this.inputTax = inputTax;
	}

	public BigDecimal getOutputTax() {
		return outputTax;
	}

	public void setOutputTax(BigDecimal outputTax) {
		this.outputTax = outputTax;
	}

	public BigDecimal getCommissionRate() {
		return commissionRate;
	}

	public void setCommissionRate(BigDecimal commissionRate) {
		this.commissionRate = commissionRate;
	}

	public Date getOptTime() {
		return optTime;
	}

	public void setOptTime(Date optTime) {
		this.optTime = optTime;
	}

	public String getCol1() {
		return col1;
	}

	public void setCol1(String col1) {
		this.col1 = col1;
	}

	public String getCol2() {
		return col2;
	}

	public void setCol2(String col2) {
		this.col2 = col2;
	}

	public String getCol3() {
		return col3;
	}

	public void setCol3(String col3) {
		this.col3 = col3;
	}

	@Override
	public String toString() {
		return "PcmContractLog [sid=" + sid + ", contractCode=" + contractCode + ", storeCode="
				+ storeCode + ", supplyCode=" + supplyCode + ", manageType=" + manageType
				+ ", buyType=" + buyType + ", operMode=" + operMode + ", businessType="
				+ businessType + ", inputTax=" + inputTax + ", outputTax=" + outputTax
				+ ", commissionRate=" + commissionRate + ", optTime=" + optTime + ", col1=" + col1
				+ ", col2=" + col2 + ", col3=" + col3 + "]";
	}

}