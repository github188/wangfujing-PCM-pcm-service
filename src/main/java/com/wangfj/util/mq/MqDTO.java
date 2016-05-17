package com.wangfj.util.mq;

import java.util.List;

import com.wangfj.core.framework.base.dto.BaseDto;

public class MqDTO extends BaseDto {

	private String url;// 消息系统路径

	private String callbackUrl; // 回调路径

	private String count; // 消息记录数

	private String destUrl;// 消息目的地URL

	private String serviceID;// 业务对象服务编码（接口编码）

	private String sourceSysID;// 来源系统编码

	private String messageID;// 消息编号

	private List data;// 数据

	private Integer destCallType;// destCallType 消息目的地调用方法

	private String field1;

	private String field2;

	private String field3;

	private String version;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public String getDestUrl() {
		return destUrl;
	}

	public void setDestUrl(String destUrl) {
		this.destUrl = destUrl;
	}

	public String getServiceID() {
		return serviceID;
	}

	public void setServiceID(String serviceID) {
		this.serviceID = serviceID;
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

	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}

	/**
	 * @Return the Integer destCallType
	 */
	public Integer getDestCallType() {
		return destCallType;
	}

	/**
	 * @Param Integer destCallType to set
	 */
	public void setDestCallType(Integer destCallType) {
		this.destCallType = destCallType;
	}

	public String getSourceSysID() {
		return sourceSysID;
	}

	public void setSourceSysID(String sourceSysID) {
		this.sourceSysID = sourceSysID;
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

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
