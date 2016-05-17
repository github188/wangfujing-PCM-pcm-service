package com.wangfj.product.stocks.domain.vo;

public class PcmProductStockInfoDto {
	/**
	 * sku Sid
	 */
	private String skuSid;
	/**
	 * 专柜商品编码
	 */
	private String shoppeProSid;
	/**
	 * 渠道编号
	 */
	private String channelSid;

	/**
	 * 库存类型
	 */
	private String stockTypeSid;

	/**
	 * 可售数量
	 */
	private Integer saleSum;
	/**
	 * 库存状态
	 */
	private String stockStatus;

	public String getSkuSid() {
		return skuSid;
	}

	public void setSkuSid(String skuSid) {
		this.skuSid = skuSid;
	}

	public String getChannelSid() {
		return channelSid;
	}

	public void setChannelSid(String channelSid) {
		this.channelSid = channelSid;
	}

	public String getStockTypeSid() {
		return stockTypeSid;
	}

	public void setStockTypeSid(String stockTypeSid) {
		this.stockTypeSid = stockTypeSid;
	}

	public Integer getSaleSum() {
		return saleSum;
	}

	public void setSaleSum(Integer saleSum) {
		this.saleSum = saleSum;
	}

	public String getShoppeProSid() {
		return shoppeProSid;
	}

	public void setShoppeProSid(String shoppeProSid) {
		this.shoppeProSid = shoppeProSid;
	}

	public String getStockStatus() {
		return stockStatus;
	}

	public void setStockStatus(String stockStatus) {
		this.stockStatus = stockStatus;
	}

	@Override
	public String toString() {
		return "PcmProductStockInfoDto [skuSid=" + skuSid + ", shoppeProSid=" + shoppeProSid
				+ ", channelSid=" + channelSid + ", stockTypeSid=" + stockTypeSid + ", saleSum="
				+ saleSum + ", stockStatus=" + stockStatus + "]";
	}

}
