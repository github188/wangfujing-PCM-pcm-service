package com.wangfj.product.organization.domain.vo;

public class PcmInfoResultDto {

	private String channelCode;// 渠道编码

	private String channelName;// 渠道名称

	private String shopCode;// 门店编码

	private String shopName;// 门店名称

	private String shoppeCode;// 专柜编码

	private String shoppeName;// 专柜名称

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

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShoppeName() {
		return shoppeName;
	}

	public void setShoppeName(String shoppeName) {
		this.shoppeName = shoppeName;
	}

	@Override
	public String toString() {
		return "PcmInfoQueryDto [channelCode=" + channelCode + ", channelName=" + channelName
				+ ", shopCode=" + shopCode + ", shopName=" + shopName + ", shoppeCode="
				+ shoppeCode + ", shoppeName=" + shoppeName + "]";
	}

}
