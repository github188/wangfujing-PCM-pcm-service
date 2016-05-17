package com.wangfj.product.organization.domain.vo;

public class PcmPadShoppeQueryDto {

	private String counterCode;// 专柜编码

	public String getCounterCode() {
		return counterCode;
	}

	public void setCounterCode(String counterCode) {
		this.counterCode = counterCode;
	}

	@Override
	public String toString() {
		return "PcmPadShoppeQueryDto [counterCode=" + counterCode + "]";
	}

}
