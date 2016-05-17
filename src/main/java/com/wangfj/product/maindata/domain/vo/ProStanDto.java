package com.wangfj.product.maindata.domain.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProStanDto {
	/**
	 * 色系编码
	 */
	private String colorCode;
	/**
	 * 款号
	 */
	private String product_sid;
	/**
	 * 尺码编码
	 */
	private List<Map<String, Object>> size = new ArrayList<Map<String, Object>>();
	/**
	 * 门店编码
	 */
	private String storeCode;

	private String shoppeCode;

	public String getShoppeCode() {
		return shoppeCode;
	}

	public void setShoppeCode(String shoppeCode) {
		this.shoppeCode = shoppeCode;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public List<Map<String, Object>> getSize() {
		return size;
	}

	public void setSize(List<Map<String, Object>> size) {
		this.size = size;
	}

	public String getColorCode() {
		return colorCode;
	}

	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}

	public String getProduct_sid() {
		return product_sid;
	}

	public void setProduct_sid(String product_sid) {
		this.product_sid = product_sid;
	}

	@Override
	public String toString() {
		return "ProStanDto [colorCode=" + colorCode + ", product_sid=" + product_sid + ", size="
				+ size + ", storeCode=" + storeCode + ", shoppeCode=" + shoppeCode + "]";
	}

}
