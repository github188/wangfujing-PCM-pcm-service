package com.wangfj.product.stocks.domain.vo;

import java.util.Date;

import com.wangfj.core.framework.base.dto.BaseDto;

/**
 * 
 * @Class Name PcmStockChangeRecordDto
 * @Author yedong
 * @Create In 2015年7月3日
 */
public class PcmStockChangeRecordDto extends BaseDto {
	private Long sid;

	private String billsNo; /* 单据号 */

	private String shoppeProSid; /* 专柜商品SID */

	private Integer stockSid; /* 库存表SID */

	private Long changeSum; /* 变动数量 */

	private Integer changeTypeSid; /* 变动类型SID */

	private Date changeTime; /* 变动时间 */

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

	public Integer getStockSid() {
		return stockSid;
	}

	public void setStockSid(Integer stockSid) {
		this.stockSid = stockSid;
	}

	public Long getChangeSum() {
		return changeSum;
	}

	public void setChangeSum(Long changeSum) {
		this.changeSum = changeSum;
	}

	public Integer getChangeTypeSid() {
		return changeTypeSid;
	}

	public void setChangeTypeSid(Integer changeTypeSid) {
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

	@Override
	public String toString() {
		return "PcmStockChangeRecordDto [sid=" + sid + ", billsNo=" + billsNo + ", shoppeProSid="
				+ shoppeProSid + ", stockSid=" + stockSid + ", changeSum=" + changeSum
				+ ", changeTypeSid=" + changeTypeSid + ", changeTime=" + changeTime + ", note="
				+ note + "]";
	}

}
