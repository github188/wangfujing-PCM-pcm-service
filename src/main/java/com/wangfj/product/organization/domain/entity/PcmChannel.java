package com.wangfj.product.organization.domain.entity;

import java.util.Date;

import com.wangfj.core.framework.base.entity.BaseEntity;

/**
 * 
 * @Class Name PcmChannel
 * @Author yedong
 * @Create In 2015年7月3日
 */
public class PcmChannel extends BaseEntity {
	/**
	 * @Field long serialVersionUID
	 */
	private static final long serialVersionUID = 5166476332045453881L;

	private Long sid;

	private String channelCode; /* 渠道名称 */

	private String channelName; /* 渠道名称 */

	private Integer status; /* 状态 */

	private String optUser; /* 操作人 */

	private Date optDate; /* 操作时间 */

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode == null ? null : channelCode.trim();
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName == null ? null : channelName.trim();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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