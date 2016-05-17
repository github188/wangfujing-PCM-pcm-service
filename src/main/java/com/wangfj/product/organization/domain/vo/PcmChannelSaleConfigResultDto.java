package com.wangfj.product.organization.domain.vo;

public class PcmChannelSaleConfigResultDto {

	private String channelCode;// 渠道编码

	private String channelName;// 渠道名称

	private Integer saleStauts;// 可售状态

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

	public Integer getSaleStauts() {
		return saleStauts;
	}

	public void setSaleStauts(Integer saleStauts) {
		this.saleStauts = saleStauts;
	}

	@Override
	public String toString() {
		return "PcmChannelSaleConfigResultDto [channelCode=" + channelCode + ", channelName="
				+ channelName + ", saleStauts=" + saleStauts + "]";
	}

}
