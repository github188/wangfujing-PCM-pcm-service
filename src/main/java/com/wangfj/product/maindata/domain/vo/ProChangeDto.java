package com.wangfj.product.maindata.domain.vo;

public class ProChangeDto {
	private String storeCode;// 门店号
	private String billNo;// 单据号
	private String rowNo;// 行号
	private String billType;// 类型
	private String shoppeProSid;// 专柜商品编码
	private String newValue;// 新值
	private String activeTime;// 生效日期
	private String createType;// 操作类型
	private String createTime;// 操作日期
	private String createName;// 操作人

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getRowNo() {
		return rowNo;
	}

	public void setRowNo(String rowNo) {
		this.rowNo = rowNo;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public String getShoppeProSid() {
		return shoppeProSid;
	}

	public void setShoppeProSid(String shoppeProSid) {
		this.shoppeProSid = shoppeProSid;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	public String getCreateType() {
		return createType;
	}

	public void setCreateType(String createType) {
		this.createType = createType;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public String getActiveTime() {
		return activeTime;
	}

	public void setActiveTime(String activeTime) {
		this.activeTime = activeTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "ProChangeDto [storeCode=" + storeCode + ", billNo=" + billNo + ", rowNo=" + rowNo
				+ ", billType=" + billType + ", shoppeProSid=" + shoppeProSid + ", newValue="
				+ newValue + ", activeTime=" + activeTime + ", createType=" + createType
				+ ", createTime=" + createTime + ", createName=" + createName + "]";
	}

}
