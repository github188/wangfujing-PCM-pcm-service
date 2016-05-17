package com.wangfj.product.maindata.domain.vo;

import java.util.List;

public class BarcodeListDto {
	private String productCode;
	private List<String> barcodeList;

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public List<String> getBarcodeList() {
		return barcodeList;
	}

	public void setBarcodeList(List<String> barcodeList) {
		this.barcodeList = barcodeList;
	}

	@Override
	public String toString() {
		return "BarcodeListDto [productCode=" + productCode + ", barcodeList=" + barcodeList + "]";
	}

}
