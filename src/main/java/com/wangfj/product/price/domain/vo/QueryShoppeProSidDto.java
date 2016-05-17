package com.wangfj.product.price.domain.vo;

public class QueryShoppeProSidDto {

	/**
	 * 专柜商品编码
	 */
	private String shoppeProSid;

	public String getShoppeProSid() {
		return shoppeProSid;
	}

	public void setShoppeProSid(String shoppeProSid) {
		this.shoppeProSid = shoppeProSid;
	}

	@Override
	public String toString() {
		return "QueryShoppeProSidDto [shoppeProSid=" + shoppeProSid + "]";
	}

}
