package com.wangfj.product.price.domain.vo;

import java.util.Date;

public class QueryPriceInfoDto {

	/**
	 * 专柜商品编码
	 */
	private String shoppeProSid;

	/**
	 * 渠道id
	 */
	private String channelSid;

	/**
	 * 开始日期
	 */
	private Date promotionBeginTime;

	/**
	 * 结束时间
	 */
	private Date promotionEndTime;

	/**
	 * 价格类型
	 */
	private Integer priceType;

	public String getShoppeProSid() {
		return shoppeProSid;
	}

	public void setShoppeProSid(String shoppeProSid) {
		this.shoppeProSid = shoppeProSid;
	}

	public String getChannelSid() {
		return channelSid;
	}

	public void setChannelSid(String channelSid) {
		this.channelSid = channelSid;
	}

	public Date getPromotionBeginTime() {
		return promotionBeginTime;
	}

	public void setPromotionBeginTime(Date promotionBeginTime) {
		this.promotionBeginTime = promotionBeginTime;
	}

	public Date getPromotionEndTime() {
		return promotionEndTime;
	}

	public void setPromotionEndTime(Date promotionEndTime) {
		this.promotionEndTime = promotionEndTime;
	}

	public Integer getPriceType() {
		return priceType;
	}

	public void setPriceType(Integer priceType) {
		this.priceType = priceType;
	}
}
