package com.wangfj.product.stocks.domain.vo;

import java.util.Date;

import com.wangfj.core.framework.base.dto.BaseDto;

/**
 * 查询变价历史记录
 * 
 * @Class Name PcmStockChangeRecordHisDto
 * @Author kongqf
 * @Create In 2015年11月4日
 */
public class PcmStockChangeRecordHisDto extends BaseDto {
	private Long sid;

	private String billsNo; /* 单据号 */

	private String shoppeProSid; /* 专柜商品SID */

	private Long stockSid; /* 库存表SID */

	private Long changeSum; /* 变动数量 */

	private Long changeTypeSid; /* 变动类型SID */

	private String changeTime; /* 变动时间 */

	private String note; /* 备注 */

	private Long proSum; /* 原库存数量 */

	private String channelSid; /* 渠道 */

	private String channelName;/* 渠道名称 */

	private String field1;
	private String field2;

	/**
	 * 当前页
	 */
	private Integer currentPage = 1;

	/**
	 * 单页行数 默认20
	 */
	private Integer pageSize = 10;
	private Integer start;
	private Integer limit;

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

	public String getChangeTime() {
		return changeTime;
	}

	public void setChangeTime(String changeTime) {
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

    public String getChannelSid() {
        return channelSid;
    }

    public void setChannelSid(String channelSid) {
        this.channelSid = channelSid;
    }

    public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
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
		this.start = 0;
		if (this.currentPage > 1 && this.pageSize > 0) {
			this.start = (this.currentPage - 1) * this.pageSize;
		}
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		if (this.pageSize != null)
			this.limit = this.pageSize;
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	@Override
	public String toString() {
		return "PcmStockChangeRecordHisDto [sid=" + sid + ", billsNo=" + billsNo + ", shoppeProSid="
				+ shoppeProSid + ", stockSid=" + stockSid + ", changeSum=" + changeSum
				+ ", changeTypeSid=" + changeTypeSid + ", changeTime=" + changeTime + ", note="
				+ note + ", proSum=" + proSum + ", channelSid=" + channelSid + ", channelName="
				+ channelName + ", field1=" + field1 + ", field2=" + field2 + ", currentPage="
				+ currentPage + ", pageSize=" + pageSize + ", start=" + start + ", limit=" + limit
				+ "]";
	}

}
