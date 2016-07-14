/**
 * @Probject Name: pcm-service
 * @Path: com.wangfj.product.price.domain.voSelectPriceDto.java
 * @Create By duanzhaole
 * @Create In 2015年7月24日 下午5:18:02
 */
package com.wangfj.product.price.domain.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.wangfj.core.framework.base.dto.BaseDto;

/**
 * 价格查询返回信息
 * 
 * @Class Name SelectPriceDto
 * @Author kongqf
 * @Create In 2015年8月3日
 */
public class SelectPricePageDto extends BaseDto {

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

	/**
	 * 货币单位
	 */
	private String unit;

	/**
	 * 价格渠道 1:全渠道;2:电商;3:微信;4:APP;5:线下 其他为促销活动ID
	 */
	private String priceChannel;

	/**
	 * 促销开始时间
	 */
	private Date promotionBeginTime;

	/**
	 * 促销结束时间
	 */
	private Date promotionEndTime;

	/**
	 * 当前页
	 */
	private Integer currentPage;

	/**
	 * 单页行数
	 */
	private Integer pageSize;

	/**
	 * 当前页的起始索引,从1开始
	 */
	private Integer start;

	/**
	 * 当前页结束索引
	 */
	private Integer limit;

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getStart() {
		this.start = 0;
		if (this.currentPage > 1 && this.pageSize > 0) {
			this.start = (this.currentPage - 1) * this.pageSize;
		}
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		if (this.currentPage > 0 && this.pageSize > 0) {
			this.limit = this.currentPage * this.pageSize;
		}
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

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

	public String getPriceChannel() {
		return priceChannel;
	}

	public void setPriceChannel(String priceChannel) {
		this.priceChannel = priceChannel;
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

	@Override
	public String toString() {
		return "SelectPriceDto [shoppeProSid=" + shoppeProSid + ", currentPrice=" + currentPrice
				+ ", originalPrice=" + originalPrice + ", unit=" + unit + ", priceChannel="
				+ priceChannel + ", promotionBeginTime=" + promotionBeginTime
				+ ", promotionEndTime=" + promotionEndTime + "]";
	}

}
