package com.wangfj.util.mq;

public class RequestHeader {

	private String messageType;

	private String bizType; // 业务类别编码

	private String callbackUrl;

	private String count;

	private String createTime;

	private String destUrl;

	private String priority;

	private String routeKey;

	private String serviceID;

	private String sourceSysID;

	private String token;

	private String version;

	private String messageID;

	private Integer destCallType;

	private String field1;

	private String field2;

	private String field3;

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public String getCallbackUrl() {
		return callbackUrl;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public void setDestUrl(String destUrl) {
		this.destUrl = destUrl;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getRouteKey() {
		return routeKey;
	}

	public void setRouteKey(String routeKey) {
		this.routeKey = routeKey;
	}

	public String getServiceID() {
		return serviceID;
	}

	public void setServiceID(String serviceID) {
		this.serviceID = serviceID;
	}

	public String getSourceSysID() {
		return sourceSysID;
	}

	public void setSourceSysID(String sourceSysID) {
		this.sourceSysID = sourceSysID;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @Return the String messageID
	 */
	public String getMessageID() {
		return messageID;
	}

	/**
	 * @Param String messageID to set
	 */
	public void setMessageID(String messageID) {
		this.messageID = messageID;
	}

	/**
	 * @Return the String field1
	 */
	public String getField1() {
		return field1;
	}

	/**
	 * @Param String field1 to set
	 */
	public void setField1(String field1) {
		this.field1 = field1;
	}

	/**
	 * @Return the String field2
	 */
	public String getField2() {
		return field2;
	}

	/**
	 * @Param String field2 to set
	 */
	public void setField2(String field2) {
		this.field2 = field2;
	}

	/**
	 * @Return the String field3
	 */
	public String getField3() {
		return field3;
	}

	/**
	 * @Param String field3 to set
	 */
	public void setField3(String field3) {
		this.field3 = field3;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getDestCallType() {
		return destCallType;
	}

	public void setDestCallType(Integer destCallType) {
		this.destCallType = destCallType;
	}

	public String getDestUrl() {
		return destUrl;
	}

}
