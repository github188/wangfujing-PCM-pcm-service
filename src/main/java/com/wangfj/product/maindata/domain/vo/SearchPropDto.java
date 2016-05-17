package com.wangfj.product.maindata.domain.vo;

public class SearchPropDto {
	private String categoryId;
	private String propertyId;
	private String propertyName;
	private Integer propertyOrder;
	private Boolean enumProperty;
	private String propertyValueId;
	private String propertyValue;

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public Integer getPropertyOrder() {
		return propertyOrder;
	}

	public void setPropertyOrder(Integer propertyOrder) {
		this.propertyOrder = propertyOrder;
	}

	public Boolean getEnumProperty() {
		return enumProperty;
	}

	public void setEnumProperty(Boolean enumProperty) {
		this.enumProperty = enumProperty;
	}

	public String getPropertyValueId() {
		return propertyValueId;
	}

	public void setPropertyValueId(String propertyValueId) {
		this.propertyValueId = propertyValueId;
	}

	public String getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}

	@Override
	public String toString() {
		return "SearchPropDto [categoryId=" + categoryId + ", propertyId=" + propertyId
				+ ", propertyName=" + propertyName + ", propertyOrder=" + propertyOrder
				+ ", enumProperty=" + enumProperty + ", propertyValueId=" + propertyValueId
				+ ", propertyValue=" + propertyValue + "]";
	}

}
