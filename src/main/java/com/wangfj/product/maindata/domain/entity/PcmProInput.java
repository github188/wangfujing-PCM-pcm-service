package com.wangfj.product.maindata.domain.entity;

public class PcmProInput {
	private Long sid;

	private Long contractSid;// 合同SID

	private String contractCode;// 要约号(合同号)

	private String orderNo;// 单据号

	private Long shoppeProSid;// 专柜商品SID

	private String procurementUserCode;// 采购人员编号

	private String inputUserCode;// 录入人员编号

	private String col1;

	private String col2;

	private String col3;

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public Long getContractSid() {
		return contractSid;
	}

	public void setContractSid(Long contractSid) {
		this.contractSid = contractSid;
	}

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	public Long getShoppeProSid() {
		return shoppeProSid;
	}

	public void setShoppeProSid(Long shoppeProSid) {
		this.shoppeProSid = shoppeProSid;
	}

	public String getProcurementUserCode() {
		return procurementUserCode;
	}

	public void setProcurementUserCode(String procurementUserCode) {
		this.procurementUserCode = procurementUserCode;
	}

	public String getInputUserCode() {
		return inputUserCode;
	}

	public void setInputUserCode(String inputUserCode) {
		this.inputUserCode = inputUserCode;
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

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
}