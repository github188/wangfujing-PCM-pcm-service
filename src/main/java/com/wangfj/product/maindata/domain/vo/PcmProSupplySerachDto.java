package com.wangfj.product.maindata.domain.vo;

public class PcmProSupplySerachDto {
	private String supplierName;// 供应商名称
	private String supplierCode;// 供应商编码
	private String businessPattern;// 经营方式

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getBusinessPattern() {
		return businessPattern;
	}

	public void setBusinessPattern(String businessPattern) {
		this.businessPattern = businessPattern;
	}

	@Override
	public String toString() {
		return "PcmProSupplySerachDto [supplierName=" + supplierName + ", supplierCode="
				+ supplierCode + ", businessPattern=" + businessPattern + "]";
	}
}
