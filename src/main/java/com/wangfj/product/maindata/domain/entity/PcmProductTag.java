package com.wangfj.product.maindata.domain.entity;

import java.util.Date;

import com.wangfj.core.framework.base.entity.BaseEntity;

/**
 * 产品标签关联表
 * 
 * @Class Name PcmProductTag
 * @Author zhangxy
 * @Create In 2015年7月2日
 */
public class PcmProductTag extends BaseEntity {
	private Long sid;
	/**
	 * 产品sid
	 */
	private String productSid;
	/**
	 * 标签sid
	 */
	private Long tagSid;
	/**
	 * 有效标记：0 可用，1 禁用 （默认为0)
	 */
	private Integer flag;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 操作者sid
	 */
	private Long optUserSid;

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getProductSid() {
		return productSid;
	}

	public void setProductSid(String productSid) {
		this.productSid = productSid;
	}

	public Long getTagSid() {
		return tagSid;
	}

	public void setTagSid(Long tagSid) {
		this.tagSid = tagSid;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getOptUserSid() {
		return optUserSid;
	}

	public void setOptUserSid(Long optUserSid) {
		this.optUserSid = optUserSid;
	}
}