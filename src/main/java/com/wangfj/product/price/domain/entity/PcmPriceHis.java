package com.wangfj.product.price.domain.entity;

import java.math.BigDecimal;
import java.util.Date;

public class PcmPriceHis {
	private Long sid;

	private String shoppeProSid;

	private BigDecimal promotionPrice;

	private BigDecimal currentPrice;

	private BigDecimal originalPrice;

	private BigDecimal offValue;

	private Date promotionBeginTime;

	private Date promotionEndTime;

	private Long productSid;

	private BigDecimal promotionBackup;

	private Integer channelSid;

	private String unit;

	private String priceChannel;

	private Integer promotionNo;

	private Integer priceType;

	private Date systemTime;

	private Integer ifdel;

	private String attribute1;

	private String attribute2;

	private String attribute3;

	private String attribute4;

	private String attribute5;

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

	public BigDecimal getPromotionPrice() {
		return promotionPrice;
	}

	public void setPromotionPrice(BigDecimal promotionPrice) {
		this.promotionPrice = promotionPrice;
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

	public BigDecimal getOffValue() {
		return offValue;
	}

	public void setOffValue(BigDecimal offValue) {
		this.offValue = offValue;
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

	public Long getProductSid() {
		return productSid;
	}

	public void setProductSid(Long productSid) {
		this.productSid = productSid;
	}

	public BigDecimal getPromotionBackup() {
		return promotionBackup;
	}

	public void setPromotionBackup(BigDecimal promotionBackup) {
		this.promotionBackup = promotionBackup;
	}

	public Integer getChannelSid() {
		return channelSid;
	}

	public void setChannelSid(Integer channelSid) {
		this.channelSid = channelSid;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getPriceChannel() {
		return priceChannel;
	}

	public void setPriceChannel(String priceChannel) {
		this.priceChannel = priceChannel;
	}

	public Integer getPromotionNo() {
		return promotionNo;
	}

	public void setPromotionNo(Integer promotionNo) {
		this.promotionNo = promotionNo;
	}

	public Integer getPriceType() {
		return priceType;
	}

	public void setPriceType(Integer priceType) {
		this.priceType = priceType;
	}

	public Date getSystemTime() {
		return systemTime;
	}

	public void setSystemTime(Date systemTime) {
		this.systemTime = systemTime;
	}

	public Integer getIfdel() {
		return ifdel;
	}

	public void setIfdel(Integer ifdel) {
		this.ifdel = ifdel;
	}

	public String getAttribute1() {
		return attribute1;
	}

	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}

	public String getAttribute2() {
		return attribute2;
	}

	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}

	public String getAttribute3() {
		return attribute3;
	}

	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}

	public String getAttribute4() {
		return attribute4;
	}

	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4;
	}

	public String getAttribute5() {
		return attribute5;
	}

	public void setAttribute5(String attribute5) {
		this.attribute5 = attribute5;
	}
}