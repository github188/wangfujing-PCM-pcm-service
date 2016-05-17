package com.wangfj.product.maindata.domain.vo;

public class PcmEdiProductStockDto {
	private Long sid;

	/**
	 * 专柜商品编码
	 */
	private String shoppeProSid;

	/**
	 * 渠道商品编码
	 */
	private String channelProSid;

	/**
	 * 渠道编码
	 */
	private String channelCode;

	/**
	 * 是否预售
	 */
	private Integer isPresell;

	/**
	 * 是否支付后减库
	 */
	private Integer isPayReducestock;

	private Integer ifdel;

	private String field1;

	private String field2;

	private String field3;

	private String field4;

	private String field5;

	/**
	 * 库存
	 */
	private String proSum;
	/**
	 * 库位
	 */
	private Integer stockTypeSid;

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getShoppeProSid() {
		return shoppeProSid;
	}

	public void setShoppeProSid(String shoppeProSid) {
		this.shoppeProSid = shoppeProSid;
	}

	public String getChannelProSid() {
		return channelProSid;
	}

	public void setChannelProSid(String channelProSid) {
		this.channelProSid = channelProSid;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public Integer getIsPresell() {
		return isPresell;
	}

	public void setIsPresell(Integer isPresell) {
		this.isPresell = isPresell;
	}

	public Integer getIsPayReducestock() {
		return isPayReducestock;
	}

	public void setIsPayReducestock(Integer isPayReducestock) {
		this.isPayReducestock = isPayReducestock;
	}

	public Integer getIfdel() {
		return ifdel;
	}

	public void setIfdel(Integer ifdel) {
		this.ifdel = ifdel;
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

	public String getField4() {
		return field4;
	}

	public void setField4(String field4) {
		this.field4 = field4;
	}

	public String getField5() {
		return field5;
	}

	public void setField5(String field5) {
		this.field5 = field5;
	}

	public String getProSum() {
		return proSum;
	}

	public void setProSum(String proSum) {
		this.proSum = proSum;
	}

	public Integer getStockTypeSid() {
		return stockTypeSid;
	}

	public void setStockTypeSid(Integer stockTypeSid) {
		this.stockTypeSid = stockTypeSid;
	}

	@Override
	public String toString() {
		return "PcmEdiProductStockDto [sid=" + sid + ", shoppeProSid=" + shoppeProSid
				+ ", channelProSid=" + channelProSid + ", channelCode=" + channelCode
				+ ", isPresell=" + isPresell + ", isPayReducestock=" + isPayReducestock + ", ifdel="
				+ ifdel + ", field1=" + field1 + ", field2=" + field2 + ", field3=" + field3
				+ ", field4=" + field4 + ", field5=" + field5 + ", proSum=" + proSum
				+ ", stockTypeSid=" + stockTypeSid + "]";
	}

}
