package com.wangfj.product.stocks.domain.vo;

/**
 * 
 * @Class Name PcmStock
 * @Author yedong
 * @Create In 2015年7月3日
 */
public class PcmStockInfoDto {/* 库存表 */

	private Long sid;

	private String shoppeProSid; /* 专柜商品SID */

	private Integer stockTypeSid; /* 类型SID */
	/**
	 * 库位名称
	 */
	private String stockName;

	private Long proSum; /* 库存数量 */

	private Integer channelSid; /* 渠道 */

	private Long lockCount;/* 锁定数量 */

	public Long getLockCount() {
		return lockCount;
	}

	public void setLockCount(Long lockCount) {
		this.lockCount = lockCount;
	}

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

	public Integer getStockTypeSid() {
		return stockTypeSid;
	}

	public void setStockTypeSid(Integer stockTypeSid) {
		this.stockTypeSid = stockTypeSid;
	}

	public Long getProSum() {
		return proSum;
	}

	public void setProSum(Long proSum) {
		this.proSum = proSum;
	}

	public Integer getChannelSid() {
		return channelSid;
	}

	public void setChannelSid(Integer channelSid) {
		this.channelSid = channelSid;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

}