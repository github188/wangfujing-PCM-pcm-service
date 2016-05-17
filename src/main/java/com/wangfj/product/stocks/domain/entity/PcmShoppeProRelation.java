package com.wangfj.product.stocks.domain.entity;

public class PcmShoppeProRelation {
	private Long sid;
	/*
	 * 子商品编码
	 */
	private Long code;
	/*
	 * 母商品编码
	 */
	private Long parentCode;
	/*
	 * 倍率
	 */
	private Integer amount;

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public Long getParentCode() {
		return parentCode;
	}

	public void setParentCode(Long parentCode) {
		this.parentCode = parentCode;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
}