package com.wangfj.product.maindata.domain.vo;

import java.util.Arrays;

public class PcmProductDescDto {
	private Long sid;
	/**
	 * 商品描述
	 */
	private byte[] content;

	private String color;// 色系
	private String productSid;// 产品编码
	private String contents;// 商品描述

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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getProductSid() {
		return productSid;
	}

	public void setProductSid(String productSid) {
		this.productSid = productSid;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	@Override
	public String toString() {
		return "PcmProductDescDto [sid=" + sid + ", content=" + Arrays.toString(content)
				+ ", color=" + color + ", productSid=" + productSid + ", contents=" + contents
				+ "]";
	}

}
