package com.wangfj.product.maindata.domain.entity;

import com.wangfj.core.framework.base.entity.BaseEntity;

/**
 * 商品描述表
 * 
 * @Class Name PcmProductDesc
 * @Author zhangxy
 * @Create In 2015年7月3日
 */
public class PcmProductDesc extends BaseEntity {
	private Long sid;
	/**
	 * 商品描述
	 */
	private byte[] content;

	private String color;// 色系
	private String productSid;// 产品编码

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	// public String getContent() {
	// return content;
	// }
	//
	// public void setContent(String content) {
	// this.content = content;
	// }

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public String getProductSid() {
		return productSid;
	}

	public void setProductSid(String productSid) {
		this.productSid = productSid;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}