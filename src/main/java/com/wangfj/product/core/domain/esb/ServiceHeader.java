package com.wangfj.product.core.domain.esb;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * ESB消息头
 * 
 * @author Jerry Zhang
 *
 */
@XStreamAlias("Service_Header") 
public interface ServiceHeader {
	/**
	 * 获得消息报文类型
	 * 
	 * @return
	 */
	String getMsgType();
}
