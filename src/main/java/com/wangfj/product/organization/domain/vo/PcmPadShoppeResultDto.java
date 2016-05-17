package com.wangfj.product.organization.domain.vo;

public class PcmPadShoppeResultDto {

	private String storeCode;// 门店编码

	private String storeName;// 门店名称

	private String negativeStock;// 是否库存销售

	private String counterCode;// 专柜编码

	private String counterName;// 专柜名称

	private String supplyCode;// 供应商编码

	private String supplyName;// 供应商名称

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getCounterCode() {
		return counterCode;
	}

	public void setCounterCode(String counterCode) {
		this.counterCode = counterCode;
	}

	public String getCounterName() {
		return counterName;
	}

	public void setCounterName(String counterName) {
		this.counterName = counterName;
	}

	public String getNegativeStock() {
		return negativeStock;
	}

	public void setNegativeStock(String negativeStock) {
		this.negativeStock = negativeStock;
	}

	public String getSupplyCode() {
		return supplyCode;
	}

	public void setSupplyCode(String supplyCode) {
		this.supplyCode = supplyCode;
	}

	public String getSupplyName() {
		return supplyName;
	}

	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}

	@Override
	public String toString() {
		return "PcmPadShoppeResultDto{" + "storeCode='" + storeCode + '\'' + ", storeName='"
				+ storeName + '\'' + ", negativeStock='" + negativeStock + '\'' + ", counterCode='"
				+ counterCode + '\'' + ", counterName='" + counterName + '\'' + ", supplyCode='"
				+ supplyCode + '\'' + ", supplyName='" + supplyName + '\'' + '}';
	}
}
