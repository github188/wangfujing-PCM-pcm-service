package com.wangfj.product.stocks.domain.entity;

import com.wangfj.core.framework.base.entity.BaseEntity;

/**
 * 
 * @Class Name PcmStock
 * @Author yedong
 * @Create In 2015年7月3日
 */
public class PcmStock extends BaseEntity {/* 库存表 */
	/**
	 * @Field long serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private Long sid;

	private String shoppeProSid; /* 专柜商品SID */

	private Integer stockTypeSid; /* 类型SID */

	private Long proSum; /* 库存数量 */

	private String channelSid; /* 渠道 */

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

	public String getChannelSid() {
		return channelSid;
	}

	public void setChannelSid(String channelSid) {
		this.channelSid = channelSid;
	}
}