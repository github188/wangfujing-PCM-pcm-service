package com.wangfj.product.stocks.domain.vo;

import java.util.Date;

import com.wangfj.core.framework.base.dto.BaseDto;

/**
 * 
 * @Class Name PcmStockLockRecordDto
 * @Author yedong
 * @Create In 2015年7月3日
 */
public class PcmStockLockRecordDto extends BaseDto {
	private Long sid;

	/**
	 * 销售单号
	 */
	private String saleNo;

	private String billsNo; /* 单据号 */

	private String shoppeProSid; /* 专柜商品 */

	private Long lockSum; /* 锁定数量 */

	private Integer lockTypeSid; /* 锁定类型 */

	private Date lockTime; /* 锁定时间 */

	private String note; /* 备注 */

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

	@Override
	public String toString() {
		return "PcmStockLockRecordDto [sid=" + sid + ", saleNo=" + saleNo + ", billsNo=" + billsNo
				+ ", shoppeProSid=" + shoppeProSid + ", lockSum=" + lockSum + ", lockTypeSid="
				+ lockTypeSid + ", lockTime=" + lockTime + ", note=" + note + "]";
	}

}
