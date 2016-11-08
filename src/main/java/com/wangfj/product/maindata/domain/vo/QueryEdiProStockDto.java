package com.wangfj.product.maindata.domain.vo;

import java.util.List;

import com.wangfj.product.stocks.domain.vo.EdiStockDto;

public class QueryEdiProStockDto {
	/**
	 * 专柜商品编码
	 */
	private List<EdiStockDto> shoppeProSids;

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

	public List<EdiStockDto> getShoppeProSids() {
		return shoppeProSids;
	}

	public void setShoppeProSids(List<EdiStockDto> shoppeProSids) {
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
		return "QueryEdiProStockDto [shoppeProSids=" + shoppeProSids + ", shoppeProSid="
				+ shoppeProSid + ", channelCode=" + channelCode + ", stockTypeSid=" + stockTypeSid
				+ ", stockChannelCode=" + stockChannelCode + "]";
	}

}
