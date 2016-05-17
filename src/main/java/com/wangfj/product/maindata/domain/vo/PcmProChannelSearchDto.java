package com.wangfj.product.maindata.domain.vo;

public class PcmProChannelSearchDto {
	private String channelCode;// 渠道编码
	private String channelName;// 渠道名称
	private String channelSid;// 渠道SID

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getChannelSid() {
		return channelSid;
	}

	public void setChannelSid(String channelSid) {
		this.channelSid = channelSid;
	}

	@Override
	public String toString() {
		return "PcmProChannelSearchDto [channelCode=" + channelCode + ", channelName="
				+ channelName + ", channelSid=" + channelSid + "]";
	}

}
