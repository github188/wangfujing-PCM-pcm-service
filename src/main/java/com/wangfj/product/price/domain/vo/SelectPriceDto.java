/**
 * @Probject Name: pcm-service
 * @Path: com.wangfj.product.price.domain.voSelectPriceDto.java
 * @Create By duanzhaole
 * @Create In 2015年7月24日 下午5:18:02
 */
package com.wangfj.product.price.domain.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 价格查询返回信息
 * 
 * @Class Name SelectPriceDto
 * @Author kongqf
 * @Create In 2015年8月3日
 */
public class SelectPriceDto {

	/**
	 * 专柜商品sid
	 */
	private String shoppeProSid;

	/**
	 * 现价
	 */
	private BigDecimal currentPrice;

	/**
	 * 原价
	 */
	private BigDecimal originalPrice;
	private BigDecimal salePrice;
	private Long expireDate;
	/**
	 * 货币单位
	 */
	private String unit;

	/**
	 * 促销开始时间
	 */
	private Date promotionBeginTime;

	/**
	 * 促销结束时间
	 */
	private Date promotionEndTime;

	private String channelSid;

	public String getShoppeProSid() {
		return shoppeProSid;
	}

	public void setShoppeProSid(String shoppeProSid) {
		this.shoppeProSid = shoppeProSid;
	}

	public BigDecimal getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(BigDecimal currentPrice) {
		this.currentPrice = currentPrice;
	}

	public BigDecimal getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(BigDecimal originalPrice) {
		this.originalPrice = originalPrice;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
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

	public String getChannelSid() {
		return channelSid;
	}

	public void setChannelSid(String channelSid) {
		this.channelSid = channelSid;
	}

	public Long getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Long expireDate) {
		this.expireDate = expireDate;
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	@Override
	public String toString() {
		return "SelectPriceDto [shoppeProSid=" + shoppeProSid + ", currentPrice=" + currentPrice
				+ ", originalPrice=" + originalPrice + ", salePrice=" + salePrice + ", expireDate="
				+ expireDate + ", unit=" + unit + ", promotionBeginTime=" + promotionBeginTime
				+ ", promotionEndTime=" + promotionEndTime + ", channelSid=" + channelSid + "]";
	}

}
