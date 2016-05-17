package com.wangfj.product.maindata.domain.vo;

public class BarcodeDto {
	private String guid;
	private Integer type;// 条码类型
	private String barcode;// 条码
	private String originLand;// 产地

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getOriginLand() {
		return originLand;
	}

	public void setOriginLand(String originLand) {
		this.originLand = originLand;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	@Override
	public String toString() {
		return "BarcodeDto [type=" + type + ", barcode=" + barcode + ", originLand=" + originLand
				+ "]";
	}

}
