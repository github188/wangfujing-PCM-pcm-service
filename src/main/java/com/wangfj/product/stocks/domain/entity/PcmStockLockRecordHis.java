package com.wangfj.product.stocks.domain.entity;

import java.util.Date;

public class PcmStockLockRecordHis {
	private Long sid;

	private String saleNo;

	private String billsNo;

	private String shoppeProSid;

	private Long lockSum;

	private Integer lockTypeSid;

	private Date lockTime;

	private String note;
	/**
	 * 门店编号
	 */
	private String storeCode;
	// 备用
	private String field1;
	private String field2;
	private String field3;

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getSaleNo() {
		return saleNo;
	}

	public void setSaleNo(String saleNo) {
		this.saleNo = saleNo;
	}

	public String getBillsNo() {
		return billsNo;
	}

	public void setBillsNo(String billsNo) {
		this.billsNo = billsNo;
	}

	public String getShoppeProSid() {
		return shoppeProSid;
	}

	public void setShoppeProSid(String shoppeProSid) {
		this.shoppeProSid = shoppeProSid;
	}

	public Long getLockSum() {
		return lockSum;
	}

	public void setLockSum(Long lockSum) {
		this.lockSum = lockSum;
	}

	public Integer getLockTypeSid() {
		return lockTypeSid;
	}

	public void setLockTypeSid(Integer lockTypeSid) {
		this.lockTypeSid = lockTypeSid;
	}

	public Date getLockTime() {
		return lockTime;
	}

	public void setLockTime(Date lockTime) {
		this.lockTime = lockTime;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	public String getField3() {
		return field3;
	}

	public void setField3(String field3) {
		this.field3 = field3;
	}

}