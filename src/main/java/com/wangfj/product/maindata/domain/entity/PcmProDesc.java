package com.wangfj.product.maindata.domain.entity;

import com.wangfj.core.framework.base.entity.BaseEntity;

/**
 * 文描
 * @Class Name PcmProDesc
 * @Author  wangc
 * @Create In 2016-1-12
 */
public class PcmProDesc extends BaseEntity {
	private Long sid;//文描sid
	/**
	 * 商品描述
	 */
	private byte[] content;
	private String skuCode;// SKU编码
	public Long getSid() {
		return sid;
	}
	public void setSid(Long sid) {
		this.sid = sid;
	}
	public byte[] getContent() {
		return content;
	}
	public void setContent(byte[] content) {
		this.content = content;
	}
	public String getSkuCode() {
		return skuCode;
	}
	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	
}