package com.wangfj.product.price.domain.vo;

/**
 * 价格主数据查询参数
 * 
 * @Class Name QueryPriceDto
 * @Author kongqf
 * @Create In 2015年7月28日
 */
public class QueryPriceDto {

	/**
	 * 专柜商品sid
	 */
	private String shoppeProSid;

	/**
	 * 价格渠道 1:全渠道;2:电商;3:微信;4:APP;5:线下 其他为促销活动ID
	 */
	private String priceChannel;

	/**
	 * 渠道id
	 */
	private String channelSid;

	/**
	 * 门店编号
	 */
	private String storeCode;

	public String getChannelSid() {
		return channelSid;
	}

	public void setChannelSid(String channelSid) {
		this.channelSid = channelSid;
	}

	public String getShoppeProSid() {
		return shoppeProSid;
	}

	public void setShoppeProSid(String shoppeProSid) {
		this.shoppeProSid = shoppeProSid;
	}

	public String getPriceChannel() {
		return priceChannel;
	}

	public void setPriceChannel(String priceChannel) {
		this.priceChannel = priceChannel;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	@Override
	public String toString() {
		return "QueryPriceDto [shoppeProSid=" + shoppeProSid + ", priceChannel=" + priceChannel
				+ ", channelSid=" + channelSid + ", storeCode=" + storeCode + "]";
	}

}
