package com.wangfj.product.core.domain.esb;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Service")
public class Service {
	/**
	 * 消息头
	 */
	@XStreamAlias("Service_Header")
	private ServiceHeader serviceHeader;
	/**
	 * 消息体
	 */
	@XStreamAlias("Service_Body")
	private ServiceBody serviceBody;
	/**
	 * @return the serviceHeader
	 */
	public ServiceHeader getServiceHeader() {
		return serviceHeader;
	}
	/**
	 * @param serviceHeader the serviceHeader to set
	 */
	public void setServiceHeader(ServiceHeader serviceHeader) {
		this.serviceHeader = serviceHeader;
	}
	/**
	 * @return the serviceBody
	 */
	public ServiceBody getServiceBody() {
		return serviceBody;
	}
	/**
	 * @param serviceBody the serviceBody to set
	 */
	public void setServiceBody(ServiceBody serviceBody) {
		this.serviceBody = serviceBody;
	}
}
