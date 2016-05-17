package com.wangfj.product.maindata.domain.vo;

import java.util.List;

public class ShoppeErpDto {
	private String shoppeCode;
	private String shoppeName;
	private List<String> erpList;

	public String getShoppeCode() {
		return shoppeCode;
	}

	public void setShoppeCode(String shoppeCode) {
		this.shoppeCode = shoppeCode;
	}

	public String getShoppeName() {
		return shoppeName;
	}

	public void setShoppeName(String shoppeName) {
		this.shoppeName = shoppeName;
	}

	public List<String> getErplist() {
		return erpList;
	}

	public void setErplist(List<String> erpList) {
		this.erpList = erpList;
	}

	@Override
	public String toString() {
		return "ShoppeErpDto [shoppeCode=" + shoppeCode + ", shoppeName=" + shoppeName
				+ ", erpList=" + erpList + "]";
	}

}
