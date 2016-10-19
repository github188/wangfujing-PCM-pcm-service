package com.wangfj.product.maindata.domain.vo;

public class ResultDto {
	private String numIid; // 天猫商品ID 否 string
	private String outerId; // 商家编码 否 string
	private String channelCode; // 渠道编码 否 string
	private String proNum; // 推送库存数量 是 string
	private String status; // EDI是否可创建该商品关联关系（1：可创建，0：不可创建）
	private String subStock;// 支付减，拍下减

	public String getSubStock() {
		return subStock;
	}

	public void setSubStock(String subStock) {
		this.subStock = subStock;
	}

	public String getNumIid() {
		return numIid;
	}

	public void setNumIid(String numIid) {
		this.numIid = numIid;
	}

	public String getOuterId() {
		return outerId;
	}

	public void setOuterId(String outerId) {
		this.outerId = outerId;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getProNum() {
		return proNum;
	}

	public void setProNum(String proNum) {
		this.proNum = proNum;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ResultDto [numIid=" + numIid + ", outerId=" + outerId + ", channelCode="
				+ channelCode + ", proNum=" + proNum + ", status=" + status + ", subStock="
				+ subStock + "]";
	}

}
