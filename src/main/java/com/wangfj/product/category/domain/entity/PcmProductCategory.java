package com.wangfj.product.category.domain.entity;

import java.util.Date;

/**
 * 商品品类关联表
 * 
 * @Class Name PcmProductCategory
 * @Author duanzhaole
 * @Create In 2015年7月2日
 */
public class PcmProductCategory {
	private Long sid;

	/**
	 * 产品SID(外键)
	 */
	private Long productSid;

	/**
	 * 渠道SID(外键)
	 */
	private Long channelSid;

	/**
	 * 品类sid(外键)
	 */
	private Long categorySid;

	/**
	 * 操作人
	 */
	private String optUser;

	/**
	 * 操作日期
	 */
	private Date optDate;

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public Long getProductSid() {
		return productSid;
	}

	public void setProductSid(Long productSid) {
		this.productSid = productSid;
	}

	public Long getChannelSid() {
		return channelSid;
	}

	public void setChannelSid(Long channelSid) {
		this.channelSid = channelSid;
	}

	public Long getCategorySid() {
		return categorySid;
	}

	public void setCategorySid(Long categorySid) {
		this.categorySid = categorySid;
	}

	public String getOptUser() {
		return optUser;
	}

	public void setOptUser(String optUser) {
		this.optUser = optUser == null ? null : optUser.trim();
	}

	public Date getOptDate() {
		return optDate;
	}

	public void setOptDate(Date optDate) {
		this.optDate = optDate;
	}
}