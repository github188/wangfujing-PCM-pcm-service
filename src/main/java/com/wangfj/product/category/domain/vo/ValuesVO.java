package com.wangfj.product.category.domain.vo;

public class ValuesVO {

	private Long valuesSid;// 属性值SID

	private String valuesName;// 属性值

	public Long getValuesSid() {
		return valuesSid;
	}

	public void setValuesSid(Long valuesSid) {
		this.valuesSid = valuesSid;
	}

	public String getValuesName() {
		return valuesName;
	}

	public void setValuesName(String valuesName) {
		this.valuesName = valuesName;
	}

	@Override
	public String toString() {
		return "ValuesVO [valuesSid=" + valuesSid + ", valuesName=" + valuesName + "]";
	}

}
