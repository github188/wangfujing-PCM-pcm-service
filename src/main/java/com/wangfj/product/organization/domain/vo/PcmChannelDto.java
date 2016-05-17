package com.wangfj.product.organization.domain.vo;

import java.util.Date;

import com.wangfj.core.framework.base.dto.BaseDto;

/**
 * 
 * @Class Name PcmChannelDto
 * @Author yedong
 * @Create In 2015年7月3日
 */
public class PcmChannelDto extends BaseDto {
	/**
	 * @Field long serialVersionUID
	 */
	private static final long serialVersionUID = -8715758046419417730L;

	private Long sid;

	private String channelCode;

	private String channelName;

	private Integer status;

	private String optUser;

	private Date optDates;

	private String optDateStr;

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

	public Date getOptDates() {
		return optDates;
	}

	public void setOptDates(Date optDates) {
		this.optDates = optDates;
	}

	public String getOptDateStr() {
		return optDateStr;
	}

	public void setOptDateStr(String optDateStr) {
		this.optDateStr = optDateStr == null ? null : optDateStr.trim();
	}

	@Override
	public String toString() {
		return "PcmChannelDto [sid=" + sid + ", channelName=" + channelName + ", status=" + status
				+ ", optUser=" + optUser + ", optDates=" + optDates + ", optDateStr=" + optDateStr
				+ "]";
	}

}
