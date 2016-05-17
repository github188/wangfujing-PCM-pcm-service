package com.wangfj.product.maindata.domain.vo;

public class ProPropValueDto {
	private String propName;
	private String valueName;

	public String getPropName() {
		return propName;
	}

	public void setPropName(String propName) {
		this.propName = propName;
	}

	public String getValueName() {
		return valueName;
	}

	public void setValueName(String valueName) {
		this.valueName = valueName;
	}

	@Override
	public String toString() {
		return "ProPropValueDto [propName=" + propName + ", valueName=" + valueName + "]";
	}

}
