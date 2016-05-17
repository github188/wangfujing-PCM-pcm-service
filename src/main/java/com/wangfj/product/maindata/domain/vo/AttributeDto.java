package com.wangfj.product.maindata.domain.vo;

public class AttributeDto {
	private String attrCode;
	private String attrValue;
	private String attrType;

	public String getAttrCode() {
		return attrCode;
	}

	public void setAttrCode(String attrCode) {
		this.attrCode = attrCode;
	}

	public String getAttrValue() {
		return attrValue;
	}

	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}

	public String getAttrType() {
		return attrType;
	}

	public void setAttrType(String attrType) {
		this.attrType = attrType;
	}

	@Override
	public String toString() {
		return "AttributeDto [attrCode=" + attrCode + ", attrValue=" + attrValue + ", attrType="
				+ attrType + "]";
	}

}
