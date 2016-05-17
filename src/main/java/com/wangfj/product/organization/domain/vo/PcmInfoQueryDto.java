package com.wangfj.product.organization.domain.vo;

public class PcmInfoQueryDto {

	private String channelCode;// 渠道编码

	private String shopCode;// 门店编码

	private String shoppeCode;// 专柜编码

	private String queryFlag;// 查询标记（0：渠道列表，1：每个渠道下对应的门店，2：每个门店下对应的专柜）

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public String getShoppeCode() {
		return shoppeCode;
	}

	public void setShoppeCode(String shoppeCode) {
		this.shoppeCode = shoppeCode;
	}

	public String getQueryFlag() {
		return queryFlag;
	}

	public void setQueryFlag(String queryFlag) {
		this.queryFlag = queryFlag;
	}

	@Override
	public String toString() {
		return "PcmInfoQueryDto [channelCode=" + channelCode + ", shopCode=" + shopCode
				+ ", shoppeCode=" + shoppeCode + ", queryFlag=" + queryFlag + "]";
	}

}
