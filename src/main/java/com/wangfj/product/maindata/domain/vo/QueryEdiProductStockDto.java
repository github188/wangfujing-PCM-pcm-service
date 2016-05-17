package com.wangfj.product.maindata.domain.vo;

import java.util.List;

public class QueryEdiProductStockDto {
	/**
	 * 专柜商品编码
	 */
	private List<String> shoppeProSids;

	/**
	 * 专柜商品编号
	 */
	private String shoppeProSid;

	/**
	 * 渠道商品编码
	 */
	private String channelCode;

	/**
	 * 库位
	 */
	private Integer stockTypeSid;

	/**
	 * 库存渠道
	 */
	private String stockChannelCode;

	public List<String> getShoppeProSids() {
		return shoppeProSids;
	}

	public void setShoppeProSids(List<String> shoppeProSids) {
		this.shoppeProSids = shoppeProSids;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public Integer getStockTypeSid() {
		return stockTypeSid;
	}

	public void setStockTypeSid(Integer stockTypeSid) {
		this.stockTypeSid = stockTypeSid;
	}

	public String getStockChannelCode() {
		return stockChannelCode;
	}

	public void setStockChannelCode(String stockChannelCode) {
		this.stockChannelCode = stockChannelCode;
	}

	public String getShoppeProSid() {
		return shoppeProSid;
	}

	public void setShoppeProSid(String shoppeProSid) {
		this.shoppeProSid = shoppeProSid;
	}

	@Override
	public String toString() {
		return "QueryEdiProductStockDto [shoppeProSids=" + shoppeProSids + ", shoppeProSid="
				+ shoppeProSid + ", channelCode=" + channelCode + ", stockTypeSid=" + stockTypeSid
				+ ", stockChannelCode=" + stockChannelCode + "]";
	}

}
