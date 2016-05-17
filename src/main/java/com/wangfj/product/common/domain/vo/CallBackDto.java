/**
 * @Probject Name: pcm-service
 * @Path: com.wangfj.product.common.domain.voCallBackDto.java
 * @Create By kong
 * @Create In 2015年8月26日 下午3:51:44
 * TODO
 */
package com.wangfj.product.common.domain.vo;

/**
 * @Comment
 * @Class Name CallBackDto
 * @Author kong
 * @Create In 2015年8月26日
 */
public class CallBackDto {
	
	  private String messageID;
	  
	  private String serviceID;
	  
	  private String respStatus;
	  
	  private String bizCode;
	  
	  private String bizDesc;

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
	 * @Return the String serviceID
	 */
	public String getServiceID() {
		return serviceID;
	}

	/**
	 * @Param String serviceID to set
	 */
	public void setServiceID(String serviceID) {
		this.serviceID = serviceID;
	}

	/**
	 * @Return the String respStatus
	 */
	public String getRespStatus() {
		return respStatus;
	}

	/**
	 * @Param String respStatus to set
	 */
	public void setRespStatus(String respStatus) {
		this.respStatus = respStatus;
	}

	/**
	 * @Return the String bizCode
	 */
	public String getBizCode() {
		return bizCode;
	}

	/**
	 * @Param String bizCode to set
	 */
	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}

	/**
	 * @Return the String bizDesc
	 */
	public String getBizDesc() {
		return bizDesc;
	}

	/**
	 * @Param String bizDesc to set
	 */
	public void setBizDesc(String bizDesc) {
		this.bizDesc = bizDesc;
	}
	  
	  
}
