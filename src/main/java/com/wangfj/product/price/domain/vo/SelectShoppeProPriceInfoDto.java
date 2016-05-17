/**
 * @Probject Name: pcm-service
 * @Path: com.wangfj.product.price.domain.voSelectPriceDto.java
 * @Create By duanzhaole
 * @Create In 2015年7月24日 下午5:18:02
 * TODO
 */
package com.wangfj.product.price.domain.vo;

/**
 * 价格查询返回信息
 * 
 * @Class Name SelectPriceDto
 * @Author kongqf
 * @Create In 2015年8月3日
 */
public class SelectShoppeProPriceInfoDto {

	/**
	 * 专柜商品sid
	 */
	private String shoppeProSid;

	/**
	 * 现价
	 */
	private String currentPrice;

	/**
	 * 原价
	 */
	private String originalPrice;

	/**
	 * 促销价格
	 */
	private String promotionPrice;

	/**
	 * 货币单位
	 */
	private String unit;

	/**
	 * 渠道
	 */
	private String channelSid;

	/**
	 * 促销开始时间
	 */
	private String promotionBeginTime;

	/**
	 * 促销结束时间
	 */
	private String promotionEndTime;

	/**
	 * 价格类型
	 */
	private String priceType;

	public String getShoppeProSid() {
		return shoppeProSid;
	}

	public void setShoppeProSid(String shoppeProSid) {
		this.shoppeProSid = shoppeProSid;
	}

	public String getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(String currentPrice) {
		this.currentPrice = currentPrice;
	}

	public String getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(String originalPrice) {
		this.originalPrice = originalPrice;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getChannelSid() {
		return channelSid;
	}

	public void setChannelSid(String channelSid) {
		this.channelSid = channelSid;
	}

	public String getPromotionBeginTime() {
		return promotionBeginTime;
	}

	public void setPromotionBeginTime(String promotionBeginTime) {
		this.promotionBeginTime = promotionBeginTime;
	}

	public String getPromotionEndTime() {
		return promotionEndTime;
	}

	public void setPromotionEndTime(String promotionEndTime) {
		this.promotionEndTime = promotionEndTime;
	}

	public String getPriceType() {
		return priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	public String getPromotionPrice() {
		return promotionPrice;
	}

	public void setPromotionPrice(String promotionPrice) {
		this.promotionPrice = promotionPrice;
	}

	@Override
	public String toString() {
		return "SelectShoppeProPriceInfoDto [shoppeProSid=" + shoppeProSid + ", currentPrice="
				+ currentPrice + ", originalPrice=" + originalPrice + ", promotionPrice="
				+ promotionPrice + ", unit=" + unit + ", channelSid=" + channelSid
				+ ", promotionBeginTime=" + promotionBeginTime + ", promotionEndTime="
				+ promotionEndTime + ", priceType=" + priceType + "]";
	}

}
