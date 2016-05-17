package com.wangfj.product.stocks.domain.entity;

import java.util.Date;

import com.wangfj.core.framework.base.entity.BaseEntity;

/**
 * 
 * @Class Name PcmStockChangeRecord
 * @Author yedong
 * @Create In 2015年7月3日
 */
public class PcmStockChangeRecord extends BaseEntity {/* 库存变动记录表 */
	private Long sid;

	private String billsNo; /* 单据号 */

	private String shoppeProSid; /* 专柜商品SID */

	private Long stockSid; /* 库存表SID */

	private Long changeSum; /* 变动数量 */

	private Long changeTypeSid; /* 变动类型SID */

	private Date changeTime; /* 变动时间 */

	private String note; /* 备注 */

	private Long proSum; /* 原库存数量 */

	private String field1;
	private String field2;

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

	public Long getStockSid() {
		return stockSid;
	}

	public void setStockSid(Long stockSid) {
		this.stockSid = stockSid;
	}

	public Long getChangeSum() {
		return changeSum;
	}

	public void setChangeSum(Long changeSum) {
		this.changeSum = changeSum;
	}

	public Long getChangeTypeSid() {
		return changeTypeSid;
	}

	public void setChangeTypeSid(Long changeTypeSid) {
		this.changeTypeSid = changeTypeSid;
	}

	public Date getChangeTime() {
		return changeTime;
	}

	public void setChangeTime(Date changeTime) {
		this.changeTime = changeTime;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note == null ? null : note.trim();
	}

	public Long getProSum() {
		return proSum;
	}

	public void setProSum(Long proSum) {
		this.proSum = proSum;
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

}