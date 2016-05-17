package com.wangfj.product.stocks.domain.vo;

/**
 * 库位类型变更
 * 
 * @Class Name PcmStockChangeDto
 * @Author kongqf
 * @Create In 2015年8月27日
 */
public class PcmStockChangeDto {

	/**
	 * 专柜商品编码
	 */
	private String shoppeProSid;

	/**
	 * 原库位
	 */
	private Integer stockTypeSid;

	/**
	 * 变更数量
	 */
	private Integer proSum;

	/**
	 * 新库位
	 */
	private Integer newStockType;

	private String channelSid;

	private String fromSystem;

	public String getShoppeProSid() {
		return shoppeProSid;
	}

	public void setShoppeProSid(String shoppeProSid) {
		this.shoppeProSid = shoppeProSid;
	}

	public Integer getStockTypeSid() {
		return stockTypeSid;
	}

	public void setStockTypeSid(Integer stockTypeSid) {
		this.stockTypeSid = stockTypeSid;
	}

	public Integer getProSum() {
		return proSum;
	}

	public void setProSum(Integer proSum) {
		this.proSum = proSum;
	}

	public Integer getNewStockType() {
		return newStockType;
	}

	public void setNewStockType(Integer newStockType) {
		this.newStockType = newStockType;
	}

	public String getChannelSid() {
		return channelSid;
	}

	public void setChannelSid(String channelSid) {
		this.channelSid = channelSid;
	}

	public String getFromSystem() {
		return fromSystem;
	}

	public void setFromSystem(String fromSystem) {
		this.fromSystem = fromSystem;
	}

	@Override
	public String toString() {
		return "PcmStockChangeDto [shoppeProSid=" + shoppeProSid + ", stockTypeSid=" + stockTypeSid
				+ ", proSum=" + proSum + ", newStockType=" + newStockType + ", channelSid="
				+ channelSid + ", fromSystem=" + fromSystem + "]";
	}

}
