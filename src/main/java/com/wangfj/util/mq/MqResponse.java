package com.wangfj.util.mq;

public class MqResponse {
	/**
	 * 消息编码
	 */
	private String messageID;
	/**
	 * 业务对象服务编码
	 */
	private String serviceID;
	/**
	 * 响应状态
	 */
	private String respStatus;
	/**
	 * 业务响应码
	 */
	private String bizCode;
	/**
	 * 业务响应描述
	 */
	private String bizDesc;

	public String getMessageID() {
		return messageID;
	}

	public void setMessageID(String messageID) {
		this.messageID = messageID;
	}

	public String getServiceID() {
		return serviceID;
	}

	public void setServiceID(String serviceID) {
		this.serviceID = serviceID;
	}

	public String getRespStatus() {
		return respStatus;
	}

	public void setRespStatus(String respStatus) {
		this.respStatus = respStatus;
	}

	public String getBizCode() {
		return bizCode;
	}

	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}

	public String getBizDesc() {
		return bizDesc;
	}

	public void setBizDesc(String bizDesc) {
		this.bizDesc = bizDesc;
	}

}
