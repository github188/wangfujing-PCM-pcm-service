package com.wangfj.product.maindata.domain.entity;

import java.util.Date;

import com.wangfj.core.framework.base.entity.BaseEntity;

/**
 * 促销扣率码商品
 * 
 * @Class Name PcmPromotionRateProduct
 * @Author zhangxy
 * @Create In 2015年7月2日
 */
public class PcmPromotionRateProduct extends BaseEntity {
	private Long sid;
	/**
	 * 专柜商品SID
	 */
	private Long shoppeProSid;
	/**
	 * 促销扣率码
	 */
	private Long promotionRateCodeSid;
	/**
	 * 开始时间
	 */
	private Date beginTime;
	/**
	 * 结束时间
	 */
	private Date endTime;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 操作人
	 */
	private Long optUserSid;

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public Long getShoppeProSid() {
		return shoppeProSid;
	}

	public void setShoppeProSid(Long shoppeProSid) {
		this.shoppeProSid = shoppeProSid;
	}

	public Long getPromotionRateCodeSid() {
		return promotionRateCodeSid;
	}

	public void setPromotionRateCodeSid(Long promotionRateCodeSid) {
		this.promotionRateCodeSid = promotionRateCodeSid;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getOptUserSid() {
		return optUserSid;
	}

	public void setOptUserSid(Long optUserSid) {
		this.optUserSid = optUserSid;
	}
}