package com.wangfj.product.stocks.domain.entity;

import java.util.Date;

import com.wangfj.core.framework.base.entity.BaseEntity;

/**
 * 
 * @Class Name PcmStockLockRecord
 * @Author yedong
 * @Create In 2015年7月3日
 */
public class PcmStockLockRecord extends BaseEntity { /* 锁定记录表 */
	private Long sid;
	/**
	 * 销售订单
	 */
	private String saleNo;

	private String billsNo; /* 单据号 */

	private String shoppeProSid; /* 专柜商品 */

	private Long lockSum; /* 锁定数量 */

	private Integer lockTypeSid; /* 锁定类型 */

	private Date lockTime; /* 锁定时间 */

	private String note; /* 备注 */

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
		this.note = note == null ? null : note.trim();
	}

	public String getSaleNo() {
		return saleNo;
	}

	public void setSaleNo(String saleNo) {
		this.saleNo = saleNo;
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