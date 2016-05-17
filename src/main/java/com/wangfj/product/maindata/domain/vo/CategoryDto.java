package com.wangfj.product.maindata.domain.vo;

import java.util.List;

public class CategoryDto {
	private String categoryCode;
	private List<ParametersDto> attributeList;// 编辑好的属性对象List

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public List<ParametersDto> getAttributeList() {
		return attributeList;
	}

	public void setAttributeList(List<ParametersDto> attributeList) {
		this.attributeList = attributeList;
	}

	@Override
	public String toString() {
		return "CategoryDto [categoryCode=" + categoryCode + ", attributeList=" + attributeList
				+ "]";
	}

}
