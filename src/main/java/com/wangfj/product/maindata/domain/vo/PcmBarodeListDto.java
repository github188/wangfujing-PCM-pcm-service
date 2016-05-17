package com.wangfj.product.maindata.domain.vo;

import java.util.List;

import com.wangfj.product.maindata.domain.entity.PcmBarcode;

public class PcmBarodeListDto {
	private String productCode;
	private List<PcmBarcode> barcodeList;

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public List<PcmBarcode> getBarcodeList() {
		return barcodeList;
	}

	public void setBarcodeList(List<PcmBarcode> barcodeList) {
		this.barcodeList = barcodeList;
	}

	@Override
	public String toString() {
		return "PcmBarodeListDto [productCode=" + productCode + ", barcodeList=" + barcodeList
				+ "]";
	}

}
